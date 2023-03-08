package com.travel.blog.repositories;

import com.travel.blog.models.Continents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentsRepository extends JpaRepository<Continents, Long> {
}

