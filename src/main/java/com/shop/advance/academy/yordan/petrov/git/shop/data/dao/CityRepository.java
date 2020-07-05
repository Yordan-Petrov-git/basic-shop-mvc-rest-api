package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findById(Long id);


    Optional<City> findCityByName(String name);

}

