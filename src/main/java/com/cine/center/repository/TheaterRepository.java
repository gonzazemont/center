package com.cine.center.repository;

import com.cine.center.model.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    //find theater by name
    Optional<Theater> findByName(String name);

    //find theaters with capacity greater than specified value
    List<Theater> findByCapacityGreaterThanEqual(Integer capacity);

    //check if theater exists by name
    boolean existsByName(String name);
}
