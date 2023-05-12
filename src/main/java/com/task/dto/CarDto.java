/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.dto;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author 333
 */
@Data
@Builder
public class CarDto {
   long id;
   String vin;
   String stateNumber;
   String producer;
   String brand;
   Date yearOfproduction;
   List<DetailDto> detailList;
   Long driver;
}
