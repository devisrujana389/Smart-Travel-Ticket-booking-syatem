package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/bus")
public class BusController {

	@Autowired
	BusService busservice;
	
	@PostMapping("/add")
	public BusEntity addBus(@RequestBody BusEntity bus) {
		return busservice.addBus(bus);
	}
	
	@GetMapping("/fetch")
	public List<BusEntity> getAllBus() {
		return busservice.getAllBus();
	}
	
	@GetMapping("/fetch/{BusId}")
	public BusEntity getBusById(@PathVariable int BusId) {
		return busservice.getBusById(BusId);
	}
	@PutMapping("/update")
	public BusEntity updateBus(@RequestBody BusEntity bus) {
		return busservice.updateBus(bus);
	}
	@DeleteMapping("/delete/{BusId}")
	public String deleteBus(@PathVariable int BusId) {
		return busservice.deleteBus(BusId);
	}
	@GetMapping("/search")
	public  List<BusEntity> searchBus(
			@RequestParam String boardingPoint,
            @RequestParam String droppingPoint){
		return busservice.searchBus(boardingPoint,droppingPoint);
	}
	@PutMapping("/reserve/{id}")
	public BusEntity reserveSeats(
	        @PathVariable int id,
	        @RequestParam int seats) {

	    return busservice.reserveSeats(id, seats);
	}


}
