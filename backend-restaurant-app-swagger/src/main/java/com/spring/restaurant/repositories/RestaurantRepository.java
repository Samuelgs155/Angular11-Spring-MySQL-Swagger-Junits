package com.spring.restaurant.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
	Optional<Restaurant> findById(Long id);
	Optional<Restaurant> findByName(String name);
	
	@Query("select rest from Restaurant rest")
	public List<Restaurant> findRestaurants();

}
