package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepo extends JpaRepository<TrainEntity, Integer> {

    List<TrainEntity> findByBoardingPointAndDroppingPoint(
            String boardingPoint,
            String droppingPoint
    );
}