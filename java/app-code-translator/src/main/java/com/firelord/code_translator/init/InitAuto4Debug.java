package com.firelord.code_translator.init;

import com.firelord.code_translator.translator.cpp2java.vo.TranslateCache;
import com.firelord.spring.component.mvc.startup.IInitAuto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * InitAuto4Debug
 */
@Component
@Log4j2
public class InitAuto4Debug implements IInitAuto {
    //#region Const

    /**
     * LOG_INIT_AUTO4DEBUG
     */
    static final String LOG_INIT_AUTO4DEBUG = "[CODE_TRANSLATOR.InitAuto4Debug]";

    //#endregion

    //#region run

    @Override
    public void run() {
        log.info(LOG_INIT_AUTO4DEBUG + "start...");

        //TODO:临时，稍后删除
        TranslateCache.getInstace().init();

        log.info(LOG_INIT_AUTO4DEBUG + "end...");
    }

    //#endregion
}
