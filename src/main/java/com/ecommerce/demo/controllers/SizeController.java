package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dto.SizeDto;
import com.ecommerce.demo.models.Sizes;
import com.ecommerce.demo.service.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") //

public class SizeController {

    @Autowired
    private SizesService sizesService;

    @GetMapping("/sizes")
    public List<SizeDto> getSizes() {
        return sizesService.getSizes();
    }

}
