package com.DSTA.PJ_BE.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DSTA.PJ_BE.service.SizeService;
import com.DSTA.PJ_BE.utils.DataResponse;

@RestController
@RequestMapping("api/sizes")
public class SizeController {
    private final Logger log = LoggerFactory.getLogger(SizeController.class);

    @Autowired
    SizeService sizeService;

    @GetMapping("/get-all-size")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse getAllSize(){
        log.debug("Controller Request Get All Size");
        DataResponse res = sizeService.getAllSize();
        return res;
    }
}
