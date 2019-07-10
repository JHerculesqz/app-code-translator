package com.firelord.service;

import basic.TrafficProvider;
import com.firelord.vo.RespVo;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    public RespVo getTrafficCount() {
        RespVo oRespVo = new RespVo();
        oRespVo.setRespDataJson(TrafficProvider.getCount());
        return oRespVo;
    }
}
