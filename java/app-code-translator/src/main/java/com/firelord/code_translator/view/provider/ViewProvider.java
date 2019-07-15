package com.firelord.code_translator.view.provider;

import com.firelord.code_translator.view.vo.GetCalcCountOutVo;

public class ViewProvider {
    //#region Fields

    private static int COUNT = 1000;

    //#endregion

    //#region getCalcCount

    public static GetCalcCountOutVo getCalcCount() {
        GetCalcCountOutVo oOutVo = new GetCalcCountOutVo();

        oOutVo.setCount(COUNT++);

        return oOutVo;
    }

    //#endregion
}
