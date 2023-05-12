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

public interface DetailService {
    
    public DetailDto findById(Long id);

    public List<DetailDto> getList(Integer offset, Integer limit, String search, String sortedBy);
    
    public DetailDto create(DetailDto detailDto);

    public DetailDto update(DetailDto detailDto);
        
    public void delete(DetailDto detailDto);     

    public DetailDto setDetail(DetailDto detailDto);
}
