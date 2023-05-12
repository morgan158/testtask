/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author 333
 */
@Data
@Builder
public class DetailDto {
    Long id;
    String number;
    Long carId;
}
