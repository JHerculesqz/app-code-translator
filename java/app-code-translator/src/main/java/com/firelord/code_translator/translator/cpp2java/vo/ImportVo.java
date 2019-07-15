package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ImportVo {
    //#region Fields

    /**
     * 可选值：0-jdk standard lib;
     */
    @Setter
    @Getter
    private int type;

    @Setter
    @Getter
    private String sentence;

    //#endregion
}
