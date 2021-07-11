package com.practice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MonitorDirectoryNotificationDTO {

    private Integer id;
    private String notifyCode;
    private String message;
    private String identifier;
    private LocalDateTime createdAt;
    private MonitorDirectoryPriorityDTO priority;
    private Integer directory;

    @JsonProperty("is_viewed")
    private boolean viewed;

}

