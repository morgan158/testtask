/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.controller;

import com.task.dto.CarDto;
import com.task.dto.DetailDto;
import com.task.service.CarService;
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
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    
    private final CarService carService;
    
    @GetMapping("/{id}")
    public CarDto findById(@PathVariable Long id) {
        return carService.findById(id);
    }
    
    @GetMapping("/")
    public List<CarDto> getList(@RequestParam(defaultValue = "0") Integer offset, 
            @RequestParam(defaultValue = "10") Integer limit, 
            @RequestParam(defaultValue = "") String search, 
            @RequestParam(defaultValue = "id") String sortedBy) {
        return carService.getList(offset, limit, search, sortedBy);
    }
    
    @PostMapping
    public CarDto create(@RequestBody CarDto carDto) {
        return carService.create(carDto);
    }

    @PutMapping
    public CarDto update(@RequestBody CarDto carDto) {
       return carService.update(carDto);
    }
        
    @DeleteMapping
    public void delete(@RequestBody CarDto carDto) {
        carService.delete(carDto);
    }
    
    /**
     * Метод для установки водителя владельцем машины.
     * id водителя добавляется в JSON
     * Интерфейс полностью совпадает с обновлением (update)
     * @param carDto
     * @return 
     */
    @PutMapping("/set_driver")
    public CarDto setDriver(@RequestBody CarDto carDto) {
       return carService.update(carDto); 
    }
    

}
