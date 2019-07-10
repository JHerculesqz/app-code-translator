package com.firelord.controller;

import com.firelord.service.ParserService;
import com.firelord.vo.ReqVo;
import com.firelord.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ParserController
 */
@RestController
public class ParserController {
    //#region  Fields

    @Autowired
    private ParserService parserService;

    //#endregion

    //#region parser

    @PostMapping(value = "/parser",produces = "appliction/json")
    public RespVo parser(@RequestBody ReqVo reqBuReqVo){
       return this.parserService.parser(reqBuReqVo);
    }

    //#endregion
}
