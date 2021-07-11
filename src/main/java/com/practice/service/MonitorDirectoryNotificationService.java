package com.practice.service;

import com.practice.entity.MonitorDirectory;
import com.practice.entity.MonitorDirectoryNotification;
import com.practice.entity.enums.MonitorDirectoryPriority;
import com.practice.entity.repo.MonitorDirectoryNotificationRepository;
import com.practice.entity.repo.MonitorDirectoryRepository;
import com.practice.service.dto.MonitorDirectoryDTO;
import com.practice.service.dto.MonitorDirectoryNotificationDTO;
import com.practice.service.dto.MonitorDirectoryPriorityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MonitorDirectoryNotificationService {

    private final MonitorDirectoryNotificationRepository monitorDirectoryNotificationRepository;
    private final MonitorDirectoryRepository monitorDirectoryRepository;

    public MonitorDirectoryNotificationDTO createNotification(Path root, String message, String identifier, MonitorDirectoryPriority priority) {
        MonitorDirectory md = monitorDirectoryRepository.findByPath(root.toString())
                .orElseThrow(() -> new RuntimeException("MonitorDirectory not found with path: " + root));

        MonitorDirectoryNotification notification = new MonitorDirectoryNotification();
        notification.setMessage(message);
        notification.setNotifyCode(UUID.randomUUID().toString());
        notification.setIdentifier(identifier);
        notification.setMonitorDirectory(md);
        notification.setPriority(priority);

        // save notification
        notification = monitorDirectoryNotificationRepository.save(notification);
        return toMonitorDirectoryNotificationDTO(notification);
    }

    @Transactional(readOnly = true)
    public MonitorDirectoryNotificationDTO findById(Integer id) {
        return monitorDirectoryNotificationRepository.findById(id)
                .map(this::toMonitorDirectoryNotificationDTO)
                .orElseThrow(() -> new RuntimeException("Md-Notification not found with id: " + id));
    }

    public MonitorDirectoryNotificationDTO viewNotification(Integer id) {
        if(!isViewed(id)) {
            int result = monitorDirectoryNotificationRepository.viewNotification(id);
            if(result != 1) throw new RuntimeException("Md-Notification can't be viewed!");
        }
        return findById(id);
    }

    public boolean isViewed(Integer id) {
        return monitorDirectoryNotificationRepository.isViewed(id)
                .orElseThrow(() -> new RuntimeException("Md-Notification not found with id: " + id));
    }

    public boolean existsById(Integer id) {
        return monitorDirectoryNotificationRepository.existsById(id);
    }

    public boolean existsByIdElseThrow(Integer id) {
         if(existsById(id)) return true;
         throw new RuntimeException("Md-Notification not found with id: " + id);
    }

    // ******************************* helper methods ****************************

    private MonitorDirectoryNotificationDTO toMonitorDirectoryNotificationDTO(MonitorDirectoryNotification mdn) {
        return new MonitorDirectoryNotificationDTO(
                mdn.getId(),
                mdn.getNotifyCode(),
                mdn.getMessage(),
                mdn.getIdentifier(),
                mdn.getCreatedAt(),
                new MonitorDirectoryPriorityDTO(
                        mdn.getPriority().getPriority(),
                        mdn.getPriority().getName()
                ),
                mdn.getMonitorDirectory().getId(),
                mdn.isViewed()
        );
    }



}
