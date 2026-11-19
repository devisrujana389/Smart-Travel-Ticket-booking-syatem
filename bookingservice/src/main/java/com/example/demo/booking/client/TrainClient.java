package com.example.demo.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.TrainResponse;

@FeignClient(name = "TRAIN-SERVICE")
public interface TrainClient {

    @GetMapping("/train/fetch/{id}")
    TrainResponse getTrainById(@PathVariable("id") int id);

    @PutMapping("/train/reserve/{id}")
    TrainResponse reserveTrainSeats(
            @PathVariable("id") int id,
            @RequestParam("seats") int seats
    );
}