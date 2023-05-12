/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.controller;

import com.task.dto.CarDto;
import com.task.dto.DetailDto;
import com.task.service.DetailService;
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
@RequestMapping("/api/v1/detail")
@RequiredArgsConstructor
public class DetailController {
        
    private final DetailService detailService;
    
    @GetMapping("/{id}")
    public DetailDto findById(@PathVariable Long id) {
        return detailService.findById(id);
    }
    
    @GetMapping("/")
    public List<DetailDto> getList(@RequestParam(defaultValue = "0") Integer offset, 
            @RequestParam(defaultValue = "10") Integer limit, 
            @RequestParam(defaultValue = "") String search, 
            @RequestParam(defaultValue = "id") String sortedBy) {
        return detailService.getList(offset, limit, search, sortedBy);
    }
    
    @PostMapping
    public DetailDto create(@RequestBody DetailDto detailDto) {
        return detailService.create(detailDto);
    }

    @PutMapping
    public DetailDto update(@RequestBody DetailDto detailDto) {
       return detailService.update(detailDto);
    }
      
    @DeleteMapping
    public void delete(@RequestBody DetailDto detailDto) {
        detailService.delete(detailDto);
    }
    
     /**
     * Установка запчастей:
     * сначала проверяем, есть ли запчасть с данным серийным номером у автомобиля.
     * Если есть, производим "замену": удаляем информацию о текущей детали и добавляем новую.
     * Если нет, добавляем.
     * По умолчанию считается, что серийные номера деталей одного типа одинаковы.
     * Если они разные, добавляем  поле type и производим поиск по нему.
     * carId указывается в JSON
     * @param detailDto
     * @return 
     */
    @PutMapping("/set_detail")
    public DetailDto setDetail(@RequestBody DetailDto detailDto) {
        return detailService.setDetail(detailDto);
    }
}
