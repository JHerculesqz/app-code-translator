package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;

public class TranslateCache {
    //#region Fields

    private static TranslateCache m_oInstance;

    @Setter
    @Getter
    private ClassFileSetVo classFileSetVo;

    //#endregion

    //#region Construction

    private TranslateCache() {
    }

    public static TranslateCache getInstace() {
        if (null == m_oInstance) {
            m_oInstance = new TranslateCache();
        }

        return m_oInstance;
    }

    //#endregion

    //#region init

    public void init(){
        classFileSetVo = new ClassFileSetVo();
    }

    //#endregion
}
