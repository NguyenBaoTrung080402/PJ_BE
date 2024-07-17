package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.otp.OtpDTO;
import com.DSTA.PJ_BE.utils.DataResponse;

public interface OtpService {
    public String create(String email);

    public DataResponse check(OtpDTO otpCheck);
}
