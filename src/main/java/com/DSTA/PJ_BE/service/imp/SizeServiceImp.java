package com.DSTA.PJ_BE.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.entity.Size;
import com.DSTA.PJ_BE.repository.SizeRepository;
import com.DSTA.PJ_BE.service.SizeService;
import com.DSTA.PJ_BE.utils.DataResponse;

@Service
@Transactional
public class SizeServiceImp implements SizeService{
    private final Logger log = LoggerFactory.getLogger(SizeServiceImp.class);

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public DataResponse getAllSize() {
        log.debug("Request Get All Size");
        DataResponse res = new DataResponse();
       try {
        List<Size> list =  sizeRepository.getSize();
        if(list.isEmpty() || list == null){
            res.setResult(Constants.NOT_FOUND);
            res.setMessage(Constants.NOT_FOUND);
            return res;
        }
        res.setStatus(Constants.SUCCESS);
        res.setResult(list);
        return res;
       } catch (Exception ex) {
        res.setStatus(Constants.ERROR);
        res.setMessage(Constants.SYSTEM_ERROR);
        return res;
       }
    }
}
