package com.practice.service.dto;

import com.practice.entity.MonitorDirectoryNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MonitorDirectoryDTO {

    private Integer id;
    private String path;
    private String comment;
    private LocalDateTime createdAt;

    private List<MonitorDirectoryNotificationDTO> notifications;

}
