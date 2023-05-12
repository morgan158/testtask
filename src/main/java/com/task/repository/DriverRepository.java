/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.repository;

import com.task.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 333
 */
public interface DriverRepository extends JpaRepository<Driver, Long>{

    @Query("SELECT d FROM Driver d WHERE d.fullName LIKE %:search% OR d.fullNumberOfPassport "
            + "LIKE %:search% ORDER BY CASE WHEN :sortedBy = 'fullName' THEN d.fullName END ASC, "
            + "CASE WHEN :sortedBy = 'fullNumberOfPassport' THEN d.fullNumberOfPassport END ASC, "
            + "CASE WHEN :sortedBy = 'driverLicenseCategory' THEN d.driverLicenseCategory END ASC, "
            + "CASE WHEN :sortedBy = 'birthday' THEN d.birthday END ASC, "
            + "CASE WHEN :sortedBy = 'experience' THEN d.experience END ASC, "
            + "CASE WHEN :sortedBy = 'amountAccount' THEN d.amountAccount END ASC")
    Page<Driver> getList(Pageable pageable, @Param("search") String search, 
            @Param("sortedBy") String sortedBy);    
}
