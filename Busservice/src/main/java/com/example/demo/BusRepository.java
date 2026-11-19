package com.example.demo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusEntity, Integer> {
	List<BusEntity> findByBoardingPointAndDroppingPoint(
	        String boardingPoint,
	        String droppingPoint);
}
