package com.example.booking_hotels.service;

import com.example.booking_hotels.repository.kafka.RoomBookingRepository;
import com.example.booking_hotels.repository.kafka.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Service
@RequiredArgsConstructor
public class KafkaStatisticsService {

    private final UserRegistrationRepository userRegistrationRepository;
    private final RoomBookingRepository roomBookingRepository;

    public void exportStatisticsToCsv(OutputStream outputStream) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(new PrintWriter(outputStream),
                CSVFormat.DEFAULT.withHeader("Type", "UserId", "Details"))) {
            userRegistrationRepository.findAll().forEach(entity -> {
                try {
                    printer.printRecord("UserRegistration", entity.getUserId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            roomBookingRepository.findAll().forEach(entity -> {
                try {
                    printer.printRecord("RoomBooking", entity.getUserId(), "Room: " + entity.getRoomId() +
                            ", CheckIn: " + entity.getCheckInDate() + ", CheckOut: " + entity.getCheckOutDate());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
