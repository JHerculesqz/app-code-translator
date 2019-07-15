package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class ClassFileSetVo {
    //#region Fields

    @Setter
    @Getter
    private List<ClassFileVo> classFileVos = new ArrayList<>();

    //#endregion
}
