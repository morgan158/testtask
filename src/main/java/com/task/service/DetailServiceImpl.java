/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.task.service;


import com.task.dto.DetailDto;
import com.task.entity.Car;
import com.task.entity.Detail;
import com.task.exceptions.NotFoundException;
import com.task.repository.CarRepository;
import com.task.repository.DetailRepository;
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
public class DetailServiceImpl implements DetailService {
    
    private final DetailRepository detailRepository;
    private final CarRepository carRepository;
    
    @Override
    public DetailDto findById(Long id) {
        return doDtoFromEntity(detailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public List<DetailDto> getList(Integer offset, Integer limit, String search, String sortedBy){
        Pageable pageable = PageRequest.of(offset, limit);
        List<Detail> entityList = detailRepository.getList(pageable, search, sortedBy).getContent();        
        List<DetailDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            DetailDto dto = doDtoFromEntity(entity);
            dtoList.add(dto);
        });
        return dtoList;
    }
    
    @Override
    public DetailDto create(DetailDto detailDto) {
        return doDtoFromEntity(detailRepository.save(doEntityFromDto(detailDto)));
    }

    @Override
    public DetailDto update(DetailDto detailDto) {
        return doDtoFromEntity(detailRepository.save(doEntityFromDto(detailDto)));
    }
        
    @Override
    public void delete(DetailDto detailDto){
        detailRepository.delete(doEntityFromDto(detailDto));
    }  
    
    @Override
    public DetailDto setDetail(DetailDto detailDto) {
        Detail detail = doEntityFromDto(detailDto);
        if (detailDto.getCarId() != null) {
            Car car = carRepository.findById(detailDto.getCarId()).get();
            List<Detail> detailList = car.getDetailList();
            detailList.forEach(en -> {
                if (en.getNumber().equals(detail.getNumber())) {
                    detailRepository.delete(en);
                }
            });
        }
        return doDtoFromEntity(detailRepository.save(detail));
    }
 
    private DetailDto doDtoFromEntity(Detail entity){
        DetailDto dto = DetailDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .build();
        if (entity.getCar() != null) {
            dto.setCarId(entity.getCar().getId());
        }
        return dto;
    }
    
    private Detail doEntityFromDto(DetailDto dto){
        Detail entity = Detail.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .build();
        if (dto.getCarId() != null) {
            Car car = carRepository.findById(dto.getCarId())
                    .orElseThrow(() -> new NotFoundException(dto.getCarId()));
            entity.setCar(car);
        }
        return entity;
    }

}
