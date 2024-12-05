package com.ecommerce.demo.service.sizes;

import com.ecommerce.demo.dto.SizeDto;
import com.ecommerce.demo.models.Sizes;

import java.util.List;

public interface IsizesService {
    public List<SizeDto> getSizes();
    public void addSize(Sizes sizes);
    public void deleteSize(int id);
}
