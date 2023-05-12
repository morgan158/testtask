/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.service;

import com.task.dto.CarDto;
import com.task.dto.DetailDto;
import java.util.List;

/**
 *
 * @author 333
 */

public interface CarService {
    public CarDto findById(Long id);

    public List<CarDto> getList(Integer offset, Integer limit, String search, String sortedBy);
    
    public CarDto create(CarDto carDto);

    public CarDto update(CarDto carDto);
        
    public void delete(CarDto carDto); 
    
}
