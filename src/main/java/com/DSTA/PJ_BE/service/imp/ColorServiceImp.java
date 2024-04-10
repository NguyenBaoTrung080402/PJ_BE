package com.DSTA.PJ_BE.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DSTA.PJ_BE.entity.Color;
import com.DSTA.PJ_BE.repository.ColorRepository;
import com.DSTA.PJ_BE.service.ColorService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;

@Service

public class ColorServiceImp implements ColorService{
    private final Logger log = LoggerFactory.getLogger(ColorServiceImp.class);

    @Autowired
    private ColorRepository colorRepository;
    @Override
    public DataResponse getAllColors() {
        log.debug("Request Get All Colors");
        DataResponse res = new DataResponse();
        try {
            List<Color> listColor = colorRepository.getAll();
            if(listColor == null || listColor.isEmpty() ){
                res.setResult(Constants.NOT_FOUND);
                res.setMessage(Constants.NOT_FOUND);
                return res;
            }
            
            res.setStatus(Constants.SUCCESS);
            res.setResult(listColor);
            return res;
        } catch (Exception e) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
        
    }
}
