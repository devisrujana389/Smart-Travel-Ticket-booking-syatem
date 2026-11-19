package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;


    // ADD TRAIN
    @PostMapping("/add")
    public TrainEntity addTrain(@RequestBody TrainEntity train) {

        return trainService.addTrain(train);
    }


    // FETCH ALL TRAINS
    @GetMapping("/fetch")
    public List<TrainEntity> getAllTrains() {

        return trainService.getAllTrains();
    }


    // FETCH TRAIN BY ID
    @GetMapping("/fetch/{trainId}")
    public TrainEntity getTrainById(
            @PathVariable int trainId) {

        return trainService.getTrainById(trainId);
    }


    // UPDATE TRAIN
    @PutMapping("/update/{trainId}")
    public TrainEntity updateTrain(
            @PathVariable int trainId,
            @RequestBody TrainEntity train) {

        return trainService.updateTrain(trainId, train);
    }


    // DELETE TRAIN
    @DeleteMapping("/delete/{trainId}")
    public ResponseEntity<String> deleteTrain(
            @PathVariable int trainId) {

        return ResponseEntity.ok(
                trainService.deleteTrain(trainId)
        );
    }


    // SEARCH TRAIN BY ROUTE
    @GetMapping("/search")
    public List<TrainEntity> searchTrains(
            @RequestParam String boardingPoint,
            @RequestParam String droppingPoint) {

        return trainService.searchTrains(
                boardingPoint,
                droppingPoint
        );
    }
    
    @PutMapping("/reserve/{id}")
    public TrainEntity reserveSeats(
            @PathVariable int id,
            @RequestParam int seats) {

        return trainService.reserveSeats(id, seats);
    }
}