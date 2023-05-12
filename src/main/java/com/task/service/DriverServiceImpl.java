/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.service;

import com.task.dto.CarDto;
import com.task.dto.CurrencyDto;
import com.task.dto.DriverDto;
import com.task.entity.Car;
import com.task.entity.Driver;
import com.task.exceptions.NotFoundException;
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
public class DriverServiceImpl implements DriverService {
    
    private final DriverRepository driverRepository;
    
    @Override
    public DriverDto findById(Long id) {
        return doDtoFromEntity(driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public List<DriverDto> getList(Integer offset, Integer limit, String search, String sortedBy){
        Pageable pageable = PageRequest.of(offset, limit);
        List<Driver> entityList = driverRepository.getList(pageable, search, sortedBy).getContent();        
        List<DriverDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            DriverDto dto = doDtoFromEntity(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }
    
    @Override
    public DriverDto create(DriverDto driverDto) {
        return doDtoFromEntity(driverRepository.save(doEntityFromDto(driverDto)));
    }

    @Override
    public DriverDto update(DriverDto driverDto) {
        return doDtoFromEntity(driverRepository.save(doEntityFromDto(driverDto)));
    }
        
    @Override
    public void delete(DriverDto driverDto){
        driverRepository.delete(doEntityFromDto(driverDto));
    }  
 
    @Override
    public CurrencyDto getAmount(Long driverId, String currency) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException(driverId));
        Double amountAccount = translate(driver.getAmountAccount(), currency);       
        return CurrencyDto.builder()
                .driverId(driverId)
                .currency(currency)
                .amountAccount(amountAccount)
                .build();      
    }
    
    @Override
    public CurrencyDto addAmount(CurrencyDto currencyDto) {
        Driver driver = driverRepository.findById(currencyDto.getDriverId())
                .orElseThrow(() -> new NotFoundException(currencyDto.getDriverId()));
        Double amountAccount = translate(driver.getAmountAccount(), currencyDto.getCurrency());
        driver.setAmountAccount(driver.getAmountAccount() + amountAccount);
        Driver savedDriver = driverRepository.save(driver);
        return CurrencyDto.builder()
                .amountAccount(reTranslate(savedDriver.getAmountAccount(), currencyDto.getCurrency()))
                .currency(currencyDto.getCurrency())
                .driverId(savedDriver.getId())
                .build();
    }
    
    private Double translate (Double amountAccount, String currency) {
        if (currency.equals("green")) {
            amountAccount *= 2.5;
        } else if (currency.equals("blue")) {
            amountAccount *= 1.5;
        }
        return amountAccount;
    }
    
     private Double reTranslate (Double amountAccount, String currency) {
        if (currency.equals("green")) {
            amountAccount /= 2.5;
        } else if (currency.equals("blue")) {
            amountAccount /= 1.5;
        }
        return amountAccount;
    }
    
    private DriverDto doDtoFromEntity(Driver entity){
        DriverDto dto = DriverDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .fullNumberOfPassport(entity.getFullNumberOfPassport())
                .birthday(entity.getBirthday())
                .amountAccount(entity.getAmountAccount())
                .experience(entity.getExperience())
                .driverLicenseCategory(entity.getFullName())
                .build();
        List<CarDto> carDtoList = new ArrayList();
        entity.getCarList().forEach(x -> {
            CarDto carDto = CarDto.builder()
                    .id(x.getId())
                    .vin(x.getVin())
                    .producer(x.getProducer())
                    .stateNumber(x.getStateNumber())
                    .yearOfproduction(x.getYearOfproduction())
                    .brand(x.getBrand())
                    .driver(dto.getId())
                    .build();
            carDtoList.add(carDto);                    
        });
        dto.setCarList(carDtoList);
        return dto;
    }
    
    private Driver doEntityFromDto(DriverDto dto){
        Driver entity = Driver.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .fullNumberOfPassport(dto.getFullNumberOfPassport())
                .birthday(dto.getBirthday())
                .amountAccount(dto.getAmountAccount())
                .experience(dto.getExperience())
                .driverLicenseCategory(dto.getFullName())
                .build();
        List<Car> carList = new ArrayList();
        if (dto.getCarList() != null) {
            dto.getCarList().forEach(x -> {
                Car car = Car.builder()
                        .id(x.getId())
                        .vin(x.getVin())
                        .producer(x.getProducer())
                        .stateNumber(x.getStateNumber())
                        .yearOfproduction(x.getYearOfproduction())
                        .brand(x.getBrand())
                        .driver(entity)
                        .build();
                carList.add(car);
            });
        }
        entity.setCarList(carList);
        return entity;
    }


}
