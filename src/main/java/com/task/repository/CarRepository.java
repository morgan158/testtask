/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.repository;

import com.task.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 333
 */
public interface CarRepository extends JpaRepository<Car, Long>{
     @Query("SELECT c FROM Car c WHERE c.vin LIKE %:search% "
            + "OR c.producer LIKE %:search% "
            + "OR c.stateNumber LIKE %:search% "
            + "OR c.brand LIKE %:search% "
            + "ORDER BY CASE WHEN :sortedBy = 'vin' THEN c.vin END ASC, "
            + "CASE WHEN :sortedBy = 'producer' THEN c.producer END ASC, "
            + "CASE WHEN :sortedBy = 'id' THEN c.id END ASC, "
            + "CASE WHEN :sortedBy = 'stateNumber' THEN c.stateNumber END ASC, "
            + "CASE WHEN :sortedBy = 'brand' THEN c.brand END ASC, "
             + "CASE WHEN :sortedBy = 'yearOfproduction' THEN c.brand END ASC")
    Page<Car> getList(Pageable pageable, @Param("search") String search, 
            @Param("sortedBy") String sortedBy); 
}
