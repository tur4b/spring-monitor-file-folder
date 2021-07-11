package com.practice.controller;

import com.practice.service.MonitorDirectoryNotificationService;
import com.practice.service.dto.MonitorDirectoryNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final MonitorDirectoryNotificationService monitorDirectoryNotificationService;

    @GetMapping("/for-directory")
    public ResponseEntity directoryNotifications() {
//        monitorDirectoryNotificationService
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/for-directory/{dirId}")
    public ResponseEntity directoryNotifications(@PathVariable Integer dirId) {
//        monitorDirectoryNotificationService.notificationsByDirectoryId(dirId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MonitorDirectoryNotificationDTO> notificationDetails(@PathVariable Integer id) {
        return ResponseEntity.<MonitorDirectoryNotificationDTO>ok()
                .body(monitorDirectoryNotificationService.viewNotification(id));
    }
}
