package com.example.booking_hotels.web.controller;

import com.example.booking_hotels.service.KafkaStatisticsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final KafkaStatisticsService statisticsService;

    @GetMapping("/export")
    @PreAuthorize("hasRole('ADMIN')")
    public void exportStatistics(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=statistics.csv");
        try (OutputStream outputStream = response.getOutputStream()) {
            statisticsService.exportStatisticsToCsv(outputStream);
        }
    }
}