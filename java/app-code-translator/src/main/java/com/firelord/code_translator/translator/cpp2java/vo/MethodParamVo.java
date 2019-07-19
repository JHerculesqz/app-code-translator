package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class MethodParamVo {
    //#region Fields

    @Setter
    @Getter
    private String paramType;

    @Setter
    @Getter
    private String paramName;

    //#endregion
}
