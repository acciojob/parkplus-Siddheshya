package com.driver.repository;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{
    Optional<Spot> findById(Integer id);

    @Query(value = "SELECT s FROM Spot s WHERE s.spotType = :spotType AND s.parkingLot = :parkingLot AND s.occupied = false")
    List<Spot> findSpotsByNumberOfWheelsAndParkingLotId(@Param("spotType") SpotType spotType,@Param("parkingLot") ParkingLot parkingLot);
}
