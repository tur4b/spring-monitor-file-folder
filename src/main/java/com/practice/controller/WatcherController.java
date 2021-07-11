package com.practice.controller;

import com.practice.service.FileSystemWatchProxy;
import com.practice.service.MonitorDirectoryService;
import com.practice.service.dto.MonitorDirectoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/watcher")
public class WatcherController {

    private final FileSystemWatchProxy fileSystemWatchProxy;

    @PostMapping("/file-system")
    public ResponseEntity<MonitorDirectoryDTO> monitorDirectory(@RequestParam Integer mdId) {
        return ResponseEntity.ok()
                .body(fileSystemWatchProxy.watchDirectory(mdId));
    }

}
