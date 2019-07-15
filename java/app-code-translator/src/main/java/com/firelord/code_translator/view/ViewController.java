package com.firelord.code_translator.view;

import com.firelord.spring.component.rpc.http.vo.ReqVo;
import com.firelord.spring.component.rpc.http.vo.RespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ViewController {
    //#region Fields

    @Autowired
    private ViewService viewService;

    //#endregion

    //#region getCalcCount

    @RequestMapping("/getCalcCount")
    @ResponseBody
    public RespVo getCalcCount(){
        return this.viewService.getCalcCount();
    }

    //#endregion

    //#region translate

    @RequestMapping("/translate")
    @ResponseBody
    public RespVo translate(@RequestBody ReqVo oReqVo){
        return this.viewService.translate(oReqVo);
    }

    //#endregion
}
