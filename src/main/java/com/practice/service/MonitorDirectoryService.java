package com.practice.service;

import com.practice.entity.MonitorDirectory;
import com.practice.entity.MonitorDirectoryNotification;
import com.practice.entity.repo.MonitorDirectoryRepository;
import com.practice.service.dto.MonitorDirectoryDTO;
import com.practice.service.dto.MonitorDirectoryNotificationDTO;
import com.practice.service.dto.MonitorDirectoryPriorityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MonitorDirectoryService {

    private final MonitorDirectoryRepository monitorDirectoryRepository;

    public MonitorDirectoryDTO findById(Integer id) {
        return monitorDirectoryRepository.findById(id)
                .map(this::toMonitorDirectoryDTO)
                .orElseThrow(() -> new RuntimeException("Directory not found: " + id));
    }

    public MonitorDirectoryDTO createMonitorDirectory(String path, String comment) {
        if(!StringUtils.hasText(path))
            throw new IllegalArgumentException("MonitorDirectory path must be not blank!");

        if(monitorDirectoryRepository.existsByPath(path))
            throw new IllegalArgumentException("MonitorDirectory path: " + path + " already exists!");

        MonitorDirectory monitorDirectory = new MonitorDirectory();
        monitorDirectory.setPath(path);
        monitorDirectory.setComment(comment);
        // save
        monitorDirectory = monitorDirectoryRepository.save(monitorDirectory);
        return toMonitorDirectoryDTO(monitorDirectory);
    }

    public List<MonitorDirectoryDTO> findAll() {
        return monitorDirectoryRepository.listOfMonitorDirectory()
                .stream()
                .map(this::toMonitorDirectoryDTO)
                .collect(Collectors.toList());
    }

    // ************************ helper methods ************************

    private MonitorDirectoryDTO toMonitorDirectoryDTO (MonitorDirectory md) {
        return new MonitorDirectoryDTO(
                md.getId(),
                md.getPath(),
                md.getComment(),
                md.getCreatedAt(),
                md.getMonitorDirectoryNotifications() == null ? null :
                        md.getMonitorDirectoryNotifications()
                            .stream()
                            .map(mdn ->
                                    new MonitorDirectoryNotificationDTO(
                                            mdn.getId(),
                                            mdn.getNotifyCode(),
                                            mdn.getMessage(),
                                            mdn.getIdentifier(),
                                            mdn.getCreatedAt(),
                                            new MonitorDirectoryPriorityDTO(
                                                    mdn.getPriority().getPriority(),
                                                    mdn.getPriority().getName()
                                            ),
                                            md.getId(),
                                            mdn.isViewed()
                                    )
                            ).collect(Collectors.toList())
        );
    }

}
