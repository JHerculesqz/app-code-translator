package com.firelord.service;

import com.firelord.vo.ReqVo;
import com.firelord.vo.RespVo;
import org.springframework.stereotype.Service;
import parser.gs.GsProvider;

@Service
public class ParserService {
    public RespVo parser(ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();
        try {
            if (oReqVo.getLan().equals("mysql")) {
                oRespVo.setRespDataJson(GsProvider.converSql(oReqVo.getStrResBody()));
            } else {
                oRespVo.setRespDataJson(GsProvider.converMybatis(oReqVo.getStrResBody()));
            }
        } catch (Exception ex) {
            oRespVo.setRes(false);
            oRespVo.setRespDataJson("输入为空或不合法..");
        }
        return oRespVo;
    }
}
