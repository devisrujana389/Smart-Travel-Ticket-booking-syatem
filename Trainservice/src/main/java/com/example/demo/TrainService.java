package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundEx;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;


    // ADD TRAIN
    public TrainEntity addTrain(TrainEntity train) {

        return trainRepo.save(train);
    }


    // FETCH ALL TRAINS
    public List<TrainEntity> getAllTrains() {

        return trainRepo.findAll();
    }


    // FETCH TRAIN BY ID
    public TrainEntity getTrainById(int trainId) {

        return trainRepo.findById(trainId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Train not found with id: " + trainId
                    )
                );
    }


    // UPDATE TRAIN
    public TrainEntity updateTrain(int trainId, TrainEntity trainDetails) {

        TrainEntity existingTrain = trainRepo.findById(trainId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Train not found with id: " + trainId
                    )
                );

        existingTrain.setTrainName(trainDetails.getTrainName());
        existingTrain.setTrainNumber(trainDetails.getTrainNumber());
        existingTrain.setBoardingPoint(trainDetails.getBoardingPoint());
        existingTrain.setDroppingPoint(trainDetails.getDroppingPoint());
        existingTrain.setJourneyDate(trainDetails.getJourneyDate());
        existingTrain.setDepartureTime(trainDetails.getDepartureTime());
        existingTrain.setArrivalTime(trainDetails.getArrivalTime());
        existingTrain.setAvailableSeats(trainDetails.getAvailableSeats());
        existingTrain.setTicketPrice(trainDetails.getTicketPrice());

        return trainRepo.save(existingTrain);
    }


    // DELETE TRAIN
    public String deleteTrain(int trainId) {

        TrainEntity train = trainRepo.findById(trainId)
                .orElseThrow(() ->
                    new ResourceNotFoundEx(
                        "Train not found with id: " + trainId
                    )
                );

        trainRepo.delete(train);

        return "Train deleted successfully with id: " + trainId;
    }


    // SEARCH TRAINS BY ROUTE
    public List<TrainEntity> searchTrains(
            String boardingPoint,
            String droppingPoint) {

        return trainRepo
                .findByBoardingPointAndDroppingPoint(
                        boardingPoint,
                        droppingPoint
                );
    }
    public TrainEntity reserveSeats(int trainId, int seats) {

        TrainEntity train = trainRepo.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        if (train.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats available");
        }

        train.setAvailableSeats(
                train.getAvailableSeats() - seats
        );

        return trainRepo.save(train);
    }
}