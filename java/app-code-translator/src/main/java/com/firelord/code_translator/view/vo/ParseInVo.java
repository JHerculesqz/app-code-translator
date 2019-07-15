package com.firelord.code_translator.view.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ParseInVo {
    //#region Fields

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private String codeSrc;

    //#endregion
}
