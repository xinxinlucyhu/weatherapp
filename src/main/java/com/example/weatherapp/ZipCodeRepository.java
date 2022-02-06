package com.example.weatherapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<Request, Long> {
	List<Request> findTop10ByOrderByIdDesc();
}
