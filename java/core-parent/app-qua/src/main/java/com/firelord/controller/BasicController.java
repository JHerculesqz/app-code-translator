package com.firelord.controller;

import com.firelord.service.BasicService;
import com.firelord.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    //#region Fields

    @Autowired
    private BasicService basicService;

    //#endregion

    //#region getAccCount

    @PostMapping(value = "/count",produces = "application/json")
    @ResponseBody
    public RespVo getTrafficCount(){
        return this.basicService.getTrafficCount();
    }

    //#endregion
}
