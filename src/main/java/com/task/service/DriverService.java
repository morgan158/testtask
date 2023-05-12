/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.service;

import com.task.dto.CurrencyDto;
import com.task.dto.DriverDto;
import java.util.List;

/**
 *
 * @author 333
 */

public interface DriverService {
        
    public DriverDto findById(Long id);

    public List<DriverDto> getList(Integer offset, Integer limit, String search, String sortedBy);
    
    public DriverDto create(DriverDto driverDto);

    public DriverDto update(DriverDto driverDto);
        
    public void delete(DriverDto driverDto); 

    public CurrencyDto addAmount(CurrencyDto currency);

    public CurrencyDto getAmount(Long driverId, String currency);
 
}
