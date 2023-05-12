/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.service;

import com.task.dto.CarDto;
import com.task.dto.DetailDto;
import com.task.entity.Car;
import com.task.entity.Detail;
import com.task.entity.Driver;
import com.task.exceptions.NotFoundException;
import com.task.repository.CarRepository;
import com.task.repository.DriverRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author 333
 */

@Service
@RequiredArgsConstructor
public class CarServiseImpl implements CarService {
    
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    
    @Override
    public CarDto findById(Long id) {
        return doDtoFromEntity(carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public List<CarDto> getList(Integer offset, Integer limit, String search, String sortedBy){
        Pageable pageable = PageRequest.of(offset, limit);
        List<Car> entityList = carRepository.getList(pageable, search, sortedBy).getContent();        
        List<CarDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            CarDto dto = doDtoFromEntity(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }
    
    @Override
    public CarDto create(CarDto carDto) {
        return doDtoFromEntity(carRepository.save(doEntityFromDto(carDto)));
    }

    @Override
    public CarDto update(CarDto carDto) {
        return doDtoFromEntity(carRepository.save(doEntityFromDto(carDto)));
    }
        
    @Override
    public void delete(CarDto carDto){
        carRepository.delete(doEntityFromDto(carDto));
    }  
 
    private CarDto doDtoFromEntity(Car entity){
        CarDto dto = CarDto.builder()
                .id(entity.getId())
                .vin(entity.getVin())
                .stateNumber(entity.getStateNumber())
                .producer(entity.getProducer())
                .brand(entity.getBrand())
                .yearOfproduction(entity.getYearOfproduction())
                .build();
        if (entity.getDriver() != null) {
            dto.setDriver(entity.getDriver().getId());
        }
        List<DetailDto> detailDtoList = new ArrayList();
        entity.getDetailList().forEach(x -> {
            DetailDto detailDto = DetailDto.builder()
                    .id(x.getId())
                    .number(x.getNumber())
                    .build();
            detailDtoList.add(detailDto);                    
        });
        dto.setDetailList(detailDtoList);
        return dto;
    }
    
    private Car doEntityFromDto(CarDto dto){
        
        Car entity = Car.builder()
                .id(dto.getId())
                .vin(dto.getVin())
                .stateNumber(dto.getStateNumber())
                .producer(dto.getProducer())
                .brand(dto.getBrand())
                .yearOfproduction(dto.getYearOfproduction())
                .build();
        if (dto.getDriver() != null) {
            Driver driver = driverRepository.findById(dto.getDriver()).
                    orElseThrow(() -> new NotFoundException(dto.getDriver()));
            entity.setDriver(driver);
        }
        List<Detail> detailList = new ArrayList();
        if (dto.getDetailList()!= null) {
            dto.getDetailList().forEach(x -> {
                Detail detail = Detail.builder()
                        .id(x.getId())
                        .number(x.getNumber())
                        .build();
                detailList.add(detail);
            });
        }
        entity.setDetailList(detailList);
        return entity;
    }


}
