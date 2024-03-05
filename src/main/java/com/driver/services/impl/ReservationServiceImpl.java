package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        //Exceptional Handling Remaining
        if(numberOfWheels==4){
            Optional<ParkingLot> park = parkingLotRepository3.findById(parkingLotId);
            List<Spot> spotsFound = spotRepository3.findSpotsByNumberOfWheelsAndParkingLotId(SpotType.FOUR_WHEELER,park.get());
            int minRs = Integer.MAX_VALUE;
            int spotId = -1;
            for(int i=0;i<spotsFound.size();i++){
                if(minRs>spotsFound.get(i).getPricePerHour()){
                    spotId = spotsFound.get(i).getId();
                    minRs = spotsFound.get(i).getPricePerHour();
                }
            }
            Optional<User> op = userRepository3.findById(userId);
            Reservation reservation = new Reservation();
            Spot spot = spotRepository3.findById(spotId).get();
            reservation.setSpot(spot);
            reservation.setUser(op.get());
            reservation.setNumberOfHours(timeInHours);
            return reservationRepository3.save(reservation);
        }
        else if(numberOfWheels==2){
            Optional<ParkingLot> park = parkingLotRepository3.findById(parkingLotId);
            List<Spot> spotsFound = spotRepository3.findSpotsByNumberOfWheelsAndParkingLotId(SpotType.TWO_WHEELER,park.get());
            int minRs = Integer.MAX_VALUE;
            int spotId = -1;
            for(int i=0;i<spotsFound.size();i++){
                if(minRs>spotsFound.get(i).getPricePerHour()){
                    spotId = spotsFound.get(i).getId();
                    minRs = spotsFound.get(i).getPricePerHour();
                }
            }
            Optional<User> op = userRepository3.findById(userId);
            Reservation reservation = new Reservation();
            Spot spot = spotRepository3.findById(spotId).get();
            reservation.setSpot(spot);
            reservation.setUser(op.get());
            reservation.setNumberOfHours(timeInHours);
            return reservationRepository3.save(reservation);
        }
        Optional<ParkingLot> park = parkingLotRepository3.findById(parkingLotId);
        List<Spot> spotsFound = spotRepository3.findSpotsByNumberOfWheelsAndParkingLotId(SpotType.OTHERS,park.get());
        int minRs = Integer.MAX_VALUE;
        int spotId = -1;
        for(int i=0;i<spotsFound.size();i++){
            if(minRs>spotsFound.get(i).getPricePerHour()){
                spotId = spotsFound.get(i).getId();
                minRs = spotsFound.get(i).getPricePerHour();
            }
        }
        Optional<User> op = userRepository3.findById(userId);
        Reservation reservation = new Reservation();
        Spot spot = spotRepository3.findById(spotId).get();
        reservation.setSpot(spot);
        reservation.setUser(op.get());
        reservation.setNumberOfHours(timeInHours);
        return reservationRepository3.save(reservation);
    }
}
