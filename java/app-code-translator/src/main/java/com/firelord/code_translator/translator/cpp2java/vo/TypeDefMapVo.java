package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TypeDefMapVo {
    //#region Fields

    @Setter
    @Getter
    private String keyName;

    @Setter
    @Getter
    private String valueName;

    //#endregion
}
