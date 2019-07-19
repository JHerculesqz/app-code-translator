package com.firelord.code_translator.view;

import com.firelord.code_translator.translator.cpp2java.Cpp2JavaExecutor;
import com.firelord.code_translator.view.provider.ViewProvider;
import com.firelord.code_translator.translator.mysql2oracle.MySql2OracleExecutor;
import com.firelord.code_translator.view.vo.GetCalcCountOutVo;
import com.firelord.code_translator.view.vo.ParseInVo;
import com.firelord.code_translator.view.vo.ParseOutVo;
import com.firelord.component.io.file.FileUtilsEx;
import com.firelord.spring.component.rpc.http.vo.ReqVo;
import com.firelord.spring.component.rpc.http.vo.RespVo;
import org.springframework.stereotype.Service;

@Service
public class ViewService {
    //#region Const

    private static final String TYPE_MYSQL_2_ORACLE = "TYPE_MYSQL_2_ORACLE";
    private static final String TYPE_CPP_2_JAVA = "TYPE_CPP_2_JAVA";
    private static final String TYPE_TCL_2_JAVA = "TYPE_TCL_2_JAVA";

    //#endregion

    //#region getCalcCount

    public RespVo getCalcCount() {
        RespVo oRespVo = new RespVo();

        //Provider
        GetCalcCountOutVo oOutVo = ViewProvider.getCalcCount();

        //OutVo
        oRespVo.setResultObj(oOutVo);
        return oRespVo;
    }

    //#endregion

    //#region translate

    public RespVo translate(ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ParseInVo oParseInVo = oReqVo.getReqBuVo(ParseInVo.class);
        String strCodeSrc = FileUtilsEx.readAllText(oParseInVo.getCodeSrc());

        //Provider
        String strCodeDst = "";
        try {
            //TYPE_MYSQL_2_ORACLE
            if (oParseInVo.getType().equals(TYPE_MYSQL_2_ORACLE)) {
                strCodeDst = MySql2OracleExecutor.execute(strCodeSrc);
            }
            //TYPE_CPP_2_JAVA
            else if (oParseInVo.getType().equals(TYPE_CPP_2_JAVA)) {
                strCodeDst = Cpp2JavaExecutor.execute(strCodeSrc);
            }
            //TYPE_TCL_2_JAVA
            else if (oParseInVo.getType().equals(TYPE_TCL_2_JAVA)){

            }
            //else
            else {
                //do nothing
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            RespVo.genRespVo4Err("转换失败...", oRespVo);
            return oRespVo;
        }

        //OutVo
        ParseOutVo oOutVo = new ParseOutVo();
        oOutVo.setCodeDst(strCodeDst);
        oRespVo.setResultObj(oOutVo);
        return oRespVo;
    }

    //#endregion
}
