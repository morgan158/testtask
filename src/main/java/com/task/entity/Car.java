/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
@Table(name = "car")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
   @Id
   @GeneratedValue
   private long id;
   private String vin;
   private String stateNumber;
   private String producer;
   private String brand;
   @Temporal(TemporalType.DATE)
   private Date yearOfproduction;
   @OneToMany(mappedBy = "id")
   private List<Detail> detailList;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="driver_id")
   private Driver driver;
}