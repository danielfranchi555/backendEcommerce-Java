package com.ecommerce.demo.service.sizes;

import com.ecommerce.demo.dto.SizeDto;
import com.ecommerce.demo.models.Sizes;
import com.ecommerce.demo.repositories.SizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizesService implements IsizesService {

    @Autowired
    SizesRepository sizesRepository;

    @Override
    public List<SizeDto> getSizes() {
        Iterable<Sizes> listSizes = sizesRepository.findAll();
        List<SizeDto> list = new ArrayList<>();

        for (Sizes s : listSizes) {
            SizeDto sizeDto = new SizeDto();
            sizeDto.setName(s.getSize_name());
            list.add(sizeDto);
        }
        return list;

    }

    @Override
    public void addSize(Sizes sizes) {

    }

    @Override
    public void deleteSize(int id) {

    }
}
