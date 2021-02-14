package com.spring.restaurant.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.entities.Reservation;
import com.spring.restaurant.entities.Restaurant;
import com.spring.restaurant.entities.Turn;
import com.spring.restaurant.exceptions.BookingException;
import com.spring.restaurant.exceptions.InternalServeErrorException;
import com.spring.restaurant.exceptions.NotFoundException;
import com.spring.restaurant.json.CreateReservationRest;
import com.spring.restaurant.json.ReservationRest;
import com.spring.restaurant.repositories.ReservationRepository;
import com.spring.restaurant.repositories.RestaurantRepository;
import com.spring.restaurant.repositories.TurnRepository;
import com.spring.restaurant.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private TurnRepository turnRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(Long reservationId) throws BookingException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	public String createReservation(final CreateReservationRest createReservationRest) throws BookingException {

		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));

		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));

		/*if (reservationRepository.findByTurnAndRestaurant(turn.getName(), restaurantId.getId()).isPresent()) {
			throw new NotFoundException("RESERVATION_ALREADT_EXIST", "RESERVATION_ALREADT_EXIST");
		}*/

		String locator = generateLocator(restaurantId, createReservationRest);

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());

		try {
			reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServeErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return locator;
	}

	private String generateLocator(final Restaurant restaurantId, final CreateReservationRest createReservationRest)
			throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}
}
