package com.DSTA.PJ_BE.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DSTA.PJ_BE.service.ColorService;
import com.DSTA.PJ_BE.utils.DataResponse;

@RestController
@RequestMapping("api/colors")
public class ColorController {
    private final Logger log = LoggerFactory.getLogger(ColorController.class);
    
    @Autowired
    private ColorService colorService;

    @GetMapping("/get-all-colors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse getAllColors (){
        log.debug("Controller Request Get All Colors");
        DataResponse res = colorService.getAllColors();
        return res;
    }
}
