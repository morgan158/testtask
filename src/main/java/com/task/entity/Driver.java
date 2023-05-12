/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 333
 */

@Data
@Entity
@Builder
@Table(name = "driver")
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue
    private long id;
    private String fullName;
    private String fullNumberOfPassport;
    private String driverLicenseCategory;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private int experience;
    private Double amountAccount;
    @OneToMany(mappedBy = "id")
    private List<Car> carList;
}
