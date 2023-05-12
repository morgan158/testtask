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
import jakarta.persistence.Table;
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
@Table(name = "detail")
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
   @Id
   @GeneratedValue
   private long id;
   private String number;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="car_id")
   private Car car;
}
