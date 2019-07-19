package com.firelord.code_translator.translator.cpp2java;

import com.firelord.antlr.g4.cpp.CPPBaseVisitor;
import com.firelord.antlr.g4.cpp.CPPParser;
import com.firelord.code_translator.translator.cpp2java.vo.ClassFileVo;
import com.firelord.code_translator.translator.cpp2java.vo.MethodParamVo;
import com.firelord.code_translator.translator.cpp2java.vo.MethodVo;
import com.firelord.code_translator.translator.cpp2java.vo.TranslateCache;
import org.antlr.v4.runtime.tree.ParseTree;

public class CppVisitorEx extends CPPBaseVisitor<String> {
    //#region Fields

    private String m_strCurrentAccessFlag = "public";

    //#endregion

    //#region using

    @Override
    public String visitUsingdeclaration(CPPParser.UsingdeclarationContext ctx) {
        String strSentence = ctx.getText();

        //[rule]using std::
        if (strSentence.startsWith("usingstd::")) {
            TranslateCache.getInstace().getClassFileSetVo().
                    addImportCommon(0, "import java.utils.*;");
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
        //if is typedef
        String strTypeDef = ctx.declspecifierseq().declspecifier().getText();
        if (strTypeDef.equals("typedef")) {
            String strKeyName = ctx.declspecifierseq().declspecifierseq().declspecifier().
                    typespecifier().trailingtypespecifier().simpletypespecifier().getText();
            String strValueName = ctx.initdeclaratorlist().initdeclarator().declarator().ptrdeclarator().
                    noptrdeclarator().declaratorid().idexpression().unqualifiedid().getText();
            TranslateCache.getInstace().getClassFileSetVo().addTypeDef(strKeyName, strValueName);
        }

        return super.visitSimpledeclaration(ctx);
    }

    //#endregion

    //#region class

    @Override
    public String visitClassspecifier(CPPParser.ClassspecifierContext ctx) {
        //gen oClassFileVo
        String strClassName = ctx.classhead().classheadname().classname().getText();
        ClassFileVo oClassFileVo = TranslateCache.getInstace().getClassFileSetVo().addClassFile(strClassName);

        //gen member for oClassFileVo
        _visitMemberSpecificationNode(oClassFileVo, ctx.memberspecification());

        return super.visitClassspecifier(ctx);
    }

    private void _visitMemberSpecificationNode(ClassFileVo oClassFileVo,
                                               CPPParser.MemberspecificationContext oNode) {
        for (ParseTree oChildNode : oNode.children) {
            //public/private/protected
            if (oChildNode instanceof CPPParser.AccessspecifierContext) {
                this.m_strCurrentAccessFlag = oChildNode.getText();
            }
            //MemberspecificationContext
            else if (oChildNode instanceof CPPParser.MemberspecificationContext) {
                _visitMemberSpecificationNode(oClassFileVo,
                        (CPPParser.MemberspecificationContext) oChildNode);
            }
            //MemberdeclarationContext
            else if (oChildNode instanceof CPPParser.MemberdeclarationContext) {
                CPPParser.MemberdeclarationContext oChildNodeEx = (CPPParser.MemberdeclarationContext) oChildNode;

                MethodVo oMethodVo = new MethodVo();
                oClassFileVo.getMethodVos().add(oMethodVo);

                //accessFlag
                oMethodVo.setAccessFlag(this.m_strCurrentAccessFlag);
                //static
                String strStaticFlag = oChildNodeEx.declspecifierseq().declspecifier().storageclassspecifier().getText();
                oMethodVo.setStaticFlag(strStaticFlag);
                //returnType
                String strReturnType = oChildNodeEx.declspecifierseq().declspecifierseq().declspecifier().
                        typespecifier().trailingtypespecifier().simpletypespecifier().getText();
                oMethodVo.setReturnType(strReturnType);
                //methodName
                String strMethodName = oChildNodeEx.memberdeclaratorlist().memberdeclarator().declarator().
                        ptrdeclarator().noptrdeclarator().noptrdeclarator().declaratorid().idexpression().
                        unqualifiedid().getText();
                oMethodVo.setMethodName(strMethodName);
                //paramList
                CPPParser.ParameterdeclarationlistContext lstNode4Param = oChildNodeEx.memberdeclaratorlist().
                        memberdeclarator().declarator().ptrdeclarator().noptrdeclarator().
                        parametersandqualifiers().parameterdeclarationclause().parameterdeclarationlist();
                if (null != lstNode4Param){
                    for (ParseTree oNode4Param : lstNode4Param.children) {
                        //ParameterdeclarationlistContext
                        if (oNode4Param instanceof CPPParser.ParameterdeclarationlistContext){
                            CPPParser.ParameterdeclarationlistContext oNode4ParamEx =
                                    (CPPParser.ParameterdeclarationlistContext)oNode4Param;
                            String strParamType = oNode4ParamEx.parameterdeclaration().declspecifierseq().declspecifier().
                                    typespecifier().trailingtypespecifier().simpletypespecifier().getText();
                            String strParamName = oNode4ParamEx.parameterdeclaration().declarator().ptrdeclarator().
                                    noptrdeclarator().declaratorid().idexpression().unqualifiedid().getText();

                            MethodParamVo oMethodParamVo = new MethodParamVo();
                            oMethodParamVo.setParamType(strParamType);
                            oMethodParamVo.setParamName(strParamName);
                            oMethodVo.getMethodParamVos().add(oMethodParamVo);
                        }
                        //ParameterdeclarationContext
                        else if(oNode4Param instanceof CPPParser.ParameterdeclarationContext){
                            CPPParser.ParameterdeclarationContext oNode4ParamEx =
                                    (CPPParser.ParameterdeclarationContext)oNode4Param;
                            String strParamType = oNode4ParamEx.declspecifierseq().declspecifier().
                                    typespecifier().trailingtypespecifier().simpletypespecifier().getText();
                            String strParamName = oNode4ParamEx.declarator().ptrdeclarator().
                                    noptrdeclarator().declaratorid().idexpression().unqualifiedid().getText();

                            MethodParamVo oMethodParamVo = new MethodParamVo();
                            oMethodParamVo.setParamType(strParamType);
                            oMethodParamVo.setParamName(strParamName);
                            oMethodVo.getMethodParamVos().add(oMethodParamVo);
                        }
                    }
                }
            }
        }
    }

    //#endregion
}
