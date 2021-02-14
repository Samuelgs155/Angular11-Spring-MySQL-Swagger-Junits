package com.spring.restaurant.services;


import java.util.List;

import com.spring.restaurant.exceptions.BookingException;
import com.spring.restaurant.json.RestaurantRest;

public interface RestaurantService {

	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
	
	public List<RestaurantRest> getRestaurants() throws BookingException;
}
