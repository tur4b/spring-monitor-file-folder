package com.practice.config;

import com.practice.entity.enums.MonitorDirectoryPriority;
import com.practice.service.MonitorDirectoryNotificationService;
import com.practice.service.MonitorDirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FileSystemMonitorConfig {

    private final MonitorDirectoryService monitorDirectoryService;

    @Bean
    public FileSystemWatcher fileSystemWatcher(FileChangeListener fileChangeListener) {
        FileSystemWatcher watcher = new FileSystemWatcher();
        monitorDirectoryService.findAll()
                .forEach(md -> watcher.addSourceDirectory(Paths.get(md.getPath()).toFile()));
        watcher.addListener(fileChangeListener);
        watcher.start();

        return watcher;
    }

}

@Slf4j
@RequiredArgsConstructor
@Component
class CustomFileChangeListener implements FileChangeListener {

    private final MonitorDirectoryNotificationService monitorDirectoryNotificationService;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {

        changeSet.forEach(cf -> {
            final Path root = cf.getSourceDirectory().toPath();
            cf.getFiles().forEach(file -> {
                log.info(">> type: {}; path: {}", file.getType().name(), file.getFile().toPath());

                MonitorDirectoryPriority priority;
                if(file.getType() == ChangedFile.Type.ADD)
                    priority = MonitorDirectoryPriority.INFO;
                else if(file.getType() == ChangedFile.Type.MODIFY)
                    priority = MonitorDirectoryPriority.HIGH;
                else if(file.getType() == ChangedFile.Type.DELETE)
                    priority = MonitorDirectoryPriority.CRITICAL;
                else priority = MonitorDirectoryPriority.LOW;

                monitorDirectoryNotificationService.createNotification(
                        root,
                        "operated on: " + root.relativize(file.getFile().toPath()),
                        file.getType().name(),
                        priority
                );

            });
        });

    }

}

