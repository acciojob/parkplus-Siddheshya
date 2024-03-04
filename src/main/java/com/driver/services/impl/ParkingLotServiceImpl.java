package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot newParking = new ParkingLot();
        newParking.setName(name);
        newParking.setAddress(address);
        ParkingLot savedParking = parkingLotRepository1.save(newParking);
        return savedParking;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Optional<ParkingLot> req = parkingLotRepository1.findById(parkingLotId);
        Spot s = new Spot();
        if(req.isPresent()){
            ParkingLot r = req.get();
            System.out.println(r);
            s.setParkingLot(r);
        }
        else{
            System.out.println("kjdksa");
        }




        s.setPricePerHour(pricePerHour);
        s.setOccupied(false);
        if(numberOfWheels==4){
            s.setSpotType(SpotType.FOUR_WHEELER);
        }
        else if(numberOfWheels==2){
            s.setSpotType(SpotType.TWO_WHEELER);
        }
        else{
            s.setSpotType(SpotType.OTHERS);
        }

        return spotRepository1.save(s);
    }

    @Override
    public void deleteSpot(int spotId) {
        Integer id = spotId;
        spotRepository1.deleteById(id);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot s = new Spot();
        return s;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        Integer id = parkingLotId;
        parkingLotRepository1.deleteById(id);
    }
}
