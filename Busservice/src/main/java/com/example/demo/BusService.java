package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResouceNotFoundException;

//import com.example.demo.exception.ResourceNotFoundException;
@Service
public class BusService {
	
	@Autowired
	BusRepository busrepo;

	public BusEntity addBus(BusEntity bus) {
		System.out.println("bus added");
		return busrepo.save(bus);
	}

	public List<BusEntity> getAllBus() {
		return busrepo.findAll();
	}

	 public BusEntity getBusById(int busId) {

	        return busrepo.findById(busId)
	                .orElseThrow(() ->
	                    new ResouceNotFoundException(
	                        "Bus not found with id: " + busId
	                    )
	                );
	 }
				
	public BusEntity updateBus(BusEntity bus) {

        BusEntity existingBus = busrepo.findById(bus.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + bus.getBusId()));

        existingBus.setBusName(bus.getBusName());
        existingBus.setBusNumber(bus.getBusNumber());
        existingBus.setBoardingPoint(bus.getBoardingPoint());
        existingBus.setDroppingPoint(bus.getDroppingPoint());
        existingBus.setDepartureTime(bus.getDepartureTime());
        existingBus.setArrivalTime(bus.getArrivalTime());
        existingBus.setAvailableSeats(bus.getAvailableSeats());
        existingBus.setTicketPrice(bus.getTicketPrice());

        return busrepo.save(existingBus);
    }

    // Delete Bus
    public String deleteBus(Integer busId) {

    	  if (!busrepo.existsById(busId)) {
              throw new ResouceNotFoundException(
                  "Bus not found with id: " + busId
              );
          }
    	  
    	  busrepo.deleteById(busId);

    	  return "Bus deleted successfully.";
    }

	public List<BusEntity> searchBus(String boardingPoint, String droppingPoint) {
		
		return busrepo.findByBoardingPointAndDroppingPoint(boardingPoint, droppingPoint);
	}
	public BusEntity reserveSeats(int busId, int seats) {

	    BusEntity bus = busrepo.findById(busId)
	            .orElseThrow(() -> new RuntimeException("Bus not found"));

	    if (bus.getAvailableSeats() < seats) {
	        throw new RuntimeException("Not enough seats available");
	    }

	    bus.setAvailableSeats(
	            bus.getAvailableSeats() - seats
	    );

	    return busrepo.save(bus);
	}
    
}
	


