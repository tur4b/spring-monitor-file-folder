package com.practice.entity.repo;

import com.practice.entity.MonitorDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Repository
public interface MonitorDirectoryRepository extends JpaRepository<MonitorDirectory, Integer> {

    @Query("select md " +
        "from MonitorDirectory md " +
        "left join fetch md.monitorDirectoryNotifications n " +
        "where md.id=:id"
    )
    Optional<MonitorDirectory> findById(Integer id);

    boolean existsByPath(String path);

    @Query("select md from MonitorDirectory md left join fetch md.monitorDirectoryNotifications")
    List<MonitorDirectory> listOfMonitorDirectory();

    @Query("select md from MonitorDirectory md where md.path=:path")
    Optional<MonitorDirectory> findByPath(String path);
}
