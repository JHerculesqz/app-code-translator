package com.firelord.code_translator.translator.cpp2java;

import com.firelord.antlr.g4.cpp.CPPLexer;
import com.firelord.antlr.g4.cpp.CPPParser;
import com.firelord.code_translator.translator.cpp2java.vo.TranslateCache;
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
        String strRes = TranslateCache.getInstace().getClassFileSetVo().toString();

        return strRes;
    }

    //#endregion
}
