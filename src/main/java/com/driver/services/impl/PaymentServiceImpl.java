package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Optional<Reservation> r = reservationRepository2.findById(reservationId);
        Reservation reservation = r.get();
        Payment  n = new Payment();
        System.out.println(reservation.getId());
        if(reservation!=null){
            System.out.println(mode);
            if(mode.equals("cash")){
                Payment p = new Payment();
                Spot spot = reservation.getSpot();
                int reqAmount = reservation.getNumberOfHours()*spot.getPricePerHour();

                p.setPaymentCompleted(true);
                p.setPaymentMode(PaymentMode.CASH);
                p.setReservation(reservation);
                return paymentRepository2.save(p);

            }
            else if(mode == "upi"){
                Payment p = new Payment();
                Spot spot = reservation.getSpot();
                int reqAmount = reservation.getNumberOfHours()*spot.getPricePerHour();
                if(amountSent<reqAmount){
                    // to be implemented
                }
                p.setPaymentCompleted(true);
                p.setPaymentMode(PaymentMode.UPI);
                p.setReservation(reservation);
                return paymentRepository2.save(p);
            }
            else if(mode == "card"){
                Payment p = new Payment();
                Spot spot = reservation.getSpot();
                int reqAmount = reservation.getNumberOfHours()*spot.getPricePerHour();
                if(amountSent<reqAmount){
                    // to be implemented
                }
                p.setPaymentCompleted(true);
                p.setPaymentMode(PaymentMode.CARD);
                p.setReservation(reservation);
                return paymentRepository2.save(p);
            }
        }
        System.out.println("ExitPoint");
        return n;
    }
}
