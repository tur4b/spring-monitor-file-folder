package com.practice.controller;

import com.practice.service.MonitorDirectoryService;
import com.practice.service.dto.MonitorDirectoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private final MonitorDirectoryService monitorDirectoryService;

    @GetMapping("/directory/configs")
    public ResponseEntity<List<MonitorDirectoryDTO>> directoryConfigs() {
        return ResponseEntity.<List<MonitorDirectoryDTO>>ok()
                .body(monitorDirectoryService.findAll());
    }

    @GetMapping("/directory/{dirId}")
    public ResponseEntity<MonitorDirectoryDTO> directoryMonitoring(@PathVariable Integer dirId) {
        return ResponseEntity.<MonitorDirectoryDTO>ok()
                .body(monitorDirectoryService.findById(dirId));
    }

    @PostMapping("/directory")
    public ResponseEntity<MonitorDirectoryDTO> directoryMonitoringCreate(
                @RequestParam String path,
                @RequestParam String comment
    ) {
        return ResponseEntity.<MonitorDirectoryDTO>ok()
                .body(monitorDirectoryService.createMonitorDirectory(path, comment));
    }

}
