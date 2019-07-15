package com.firelord.code_translator.translator.cpp2java;

import com.firelord.antlr.g4.cpp.CPPBaseVisitor;
import com.firelord.antlr.g4.cpp.CPPParser;
import com.firelord.code_translator.translator.cpp2java.vo.ClassFileVo;
import com.firelord.code_translator.translator.cpp2java.vo.TranslateCache;

public class CppVisitorEx extends CPPBaseVisitor<String> {
    //#region using

    @Override
    public String visitUsingdeclaration(CPPParser.UsingdeclarationContext ctx) {
        String strSentence = ctx.getText();

        //[rule]using std::
        if (strSentence.startsWith("usingstd::")) {
            ClassFileVo oClassFileVo = TranslateCache.getInstace().getClassFileSetVo().getClassFileVos().get(0);
            oClassFileVo.addImport(0, "import java.utils.*;");
        }

        return super.visitUsingdeclaration(ctx);
    }

    @Override
    public String visitUsingdirective(CPPParser.UsingdirectiveContext ctx) {
        String strSentence = ctx.getText();

        //[rule]using namespace
        if (strSentence.startsWith("usingnamespace")) {
            //do nothing
        }

        return super.visitUsingdirective(ctx);
    }

    //#endregion

    //#region typedef

    @Override
    public String visitSimpledeclaration(CPPParser.SimpledeclarationContext ctx) {


        return super.visitSimpledeclaration(ctx);
    }


    //#endregion
}
