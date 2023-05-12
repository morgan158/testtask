/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.controller;

import com.task.dto.CurrencyDto;
import com.task.dto.DriverDto;
import com.task.service.DriverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 333
 */
@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {
    
    private final DriverService driverService;
    
    @GetMapping("/{id}")
    public DriverDto findById(@PathVariable Long id) {
        return driverService.findById(id);
    }
    
    @GetMapping("/")
    public List<DriverDto> getList(@RequestParam(defaultValue = "0") Integer offset, 
            @RequestParam(defaultValue = "10") Integer limit, 
            @RequestParam(defaultValue = "") String search, 
            @RequestParam(defaultValue = "fullName") String sortedBy) {
        return driverService.getList(offset, limit, search, sortedBy);
    }
    
    @PostMapping
    public DriverDto create(@RequestBody DriverDto driverDto) {
        return driverService.create(driverDto);
    }

    @PutMapping
    public DriverDto update(@RequestBody DriverDto driverDto) {
       return driverService.update(driverDto);
    }
        
    @DeleteMapping
    public void delete(@RequestBody DriverDto driverDto) {
        driverService.delete(driverDto);
    }
    
    /**
     * Дефолтное значение валюты - red
     * другие значение: green (1 : 2.5)
     * blue (1 : 1.5) (зеленый к синему - 1 : 0.6, значит красный к синему - 1 : 1.5).
     * @param currency
     * @return 
     */
    @GetMapping("/{driverId}/currency")
    public CurrencyDto getAmount(@PathVariable Long driverId, @RequestParam String currency) {
        return driverService.getAmount(driverId, currency);
    }
    
    @PostMapping("/currency")
    public CurrencyDto addAmount(@RequestBody CurrencyDto currency) {
        return driverService.addAmount(currency);
    }
    
    @PostMapping("/currency")
    public CurrencyDto subtractAmount(@RequestBody CurrencyDto currency) {
        currency.setAmountAccount(0 - currency.getAmountAccount());
        return driverService.addAmount(currency);
    }
}
