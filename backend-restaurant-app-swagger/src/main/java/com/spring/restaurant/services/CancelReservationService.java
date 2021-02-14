package com.spring.restaurant.services;

import com.spring.restaurant.exceptions.BookingException;

public interface CancelReservationService {

	public String deleteReservation(String locator) throws BookingException;
}
