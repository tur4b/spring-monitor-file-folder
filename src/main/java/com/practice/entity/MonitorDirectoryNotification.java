package com.practice.entity;

import com.practice.entity.enums.MonitorDirectoryPriority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "monitor_directory_notification")
public class MonitorDirectoryNotification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(name = "notify_code", length = 60, unique = true)
    private String notifyCode;

    @Column(length = 500)
    private String message;

    @Column(length = 60, updatable = false)
    private String identifier;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id")
    private MonitorDirectory monitorDirectory;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "priority", length = 1)
    private MonitorDirectoryPriority priority;

    @Column(nullable = false, columnDefinition = "INT(1) default 0")
    private boolean viewed;

}


