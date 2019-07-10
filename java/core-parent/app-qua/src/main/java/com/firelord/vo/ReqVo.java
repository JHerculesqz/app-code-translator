package com.firelord.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ReqVo {
    //#region Fields

    @Setter
    @Getter
    private String lan;

    @Setter
    @Getter
    private String strResBody;

    //#endregion
}
