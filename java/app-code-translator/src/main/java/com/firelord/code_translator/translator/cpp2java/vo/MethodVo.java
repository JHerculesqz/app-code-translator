package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class MethodVo {
    //#region Fields

    @Setter
    @Getter
    private String accessFlag;

    @Setter
    @Getter
    private String staticFlag;

    @Setter
    @Getter
    private String returnType;

    @Setter
    @Getter
    private String methodName;

    @Setter
    @Getter
    private List<MethodParamVo> methodParamVos = new ArrayList<>();

    //#endregion
}
