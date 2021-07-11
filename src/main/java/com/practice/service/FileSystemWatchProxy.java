package com.practice.service;

import com.practice.service.dto.MonitorDirectoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileSystemWatchProxy {

    private final FileSystemWatcher watcher;
    private final MonitorDirectoryService monitorDirectoryService;

    // TODO: can't add directory dynamically
    public MonitorDirectoryDTO watchDirectory(Integer mdId) {

//        MonitorDirectoryDTO md = monitorDirectoryService.findById(mdId);
//        if(Objects.nonNull(watcher)) watcher.stop();
//        watcher.addSourceDirectory(Paths.get(md.getPath()).toFile());
//        watcher.stop();
//        return md;

        throw new RuntimeException("Can't add directory dynamically!");

    }


}
