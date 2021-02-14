package com.spring.restaurant.services;

import com.spring.restaurant.exceptions.BookingException;
import com.spring.restaurant.json.CreateReservationRest;
import com.spring.restaurant.json.ReservationRest;

public interface ReservationService {

	ReservationRest getReservation(Long reservationId) throws BookingException;

	String createReservation(CreateReservationRest CreateReservationRest) throws BookingException;
}
