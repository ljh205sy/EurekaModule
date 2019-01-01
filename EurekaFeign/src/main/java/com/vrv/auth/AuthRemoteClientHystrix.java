package com.vrv.auth;

import org.springframework.stereotype.Component;

import com.vrv.EurekaProvider.vo.AuthQuery;
import com.vrv.common.entity.ResponseData;

@Component
public class AuthRemoteClientHystrix implements AuthRemoteClient {

    @Override
    public ResponseData auth(AuthQuery query) {
        return ResponseData.ok("");
    }
}



