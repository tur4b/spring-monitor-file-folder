package com.practice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "monitor_directory")
public class MonitorDirectory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(length = 200, nullable = false, unique = true)
    private String path;

    @Column(length = 500)
    private String comment;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "monitorDirectory", fetch = FetchType.LAZY)
    private Set<MonitorDirectoryNotification> monitorDirectoryNotifications;

    @Override
    public String toString() {
        return "MonitorDirectory{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", monitorDirectoryNotifications=" + monitorDirectoryNotifications +
                '}';
    }
}
