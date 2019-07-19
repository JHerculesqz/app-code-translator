package com.firelord.code_translator.translator.cpp2java;

import com.firelord.antlr.g4.cpp.CPPLexer;
import com.firelord.antlr.g4.cpp.CPPParser;
import com.firelord.code_translator.translator.cpp2java.vo.ClassFileSetVo;
import com.firelord.code_translator.translator.cpp2java.vo.TranslateCache;
import com.firelord.component.seri.json.JsonUtils;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Cpp2JavaExecutor {
    //#region execute

    public static String execute(String strInput) {
        ANTLRInputStream oInput = new ANTLRInputStream(strInput);
        CPPLexer oLexer = new CPPLexer(oInput);
        CommonTokenStream oTokens = new CommonTokenStream(oLexer);
        CPPParser oParser = new CPPParser(oTokens);
        ParseTree oTree = oParser.translationunit();
        CppVisitorEx oVisitorEx = new CppVisitorEx();
        oVisitorEx.visit(oTree);
        ClassFileSetVo oClassFileSetVo = TranslateCache.getInstace().getClassFileSetVo();
        String strRes = JsonUtils.toStr(oClassFileSetVo);

        return strRes;
    }

    //#endregion
}
