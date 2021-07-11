package com.practice.entity.repo;

import com.practice.entity.MonitorDirectoryNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitorDirectoryNotificationRepository extends JpaRepository<MonitorDirectoryNotification, Integer> {

    @Modifying
    @Query("update MonitorDirectoryNotification md set md.viewed=true where md.id=:id")
    int viewNotification(Integer id);

    @Query("select md.viewed from MonitorDirectoryNotification md where md.id=:id")
    Optional<Boolean> isViewed(Integer id);

}
