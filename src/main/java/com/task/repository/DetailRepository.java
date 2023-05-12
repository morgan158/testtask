/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.repository;

import com.task.entity.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 333
 */
public interface DetailRepository extends JpaRepository<Detail, Long>{
    @Query("SELECT d FROM Detail d WHERE d.number LIKE %:search% "
            + "ORDER BY CASE WHEN :sortedBy = 'number' THEN d.number END ASC, "
            + "CASE WHEN :sortedBy = 'id' THEN d.id END ASC")
    Page<Detail> getList(Pageable pageable, @Param("search") String search, 
            @Param("sortedBy") String sortedBy); 
}
