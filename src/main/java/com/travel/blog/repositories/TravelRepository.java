package com.travel.blog.repositories;

import com.travel.blog.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}

