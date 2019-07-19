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

    @Setter
    @Getter
    private List<ImportVo> importVoCommons = new ArrayList<>();

    @Setter
    @Getter
    private List<TypeDefMapVo> typeDefMapVos = new ArrayList<>();

    //#endregion

    //#region addTypeDef

    public void addTypeDef(String strKeyName, String strValueName) {
        for(TypeDefMapVo oTypeDefMapVo : typeDefMapVos){
            if(oTypeDefMapVo.getKeyName().equals(strKeyName)){
                throw new RuntimeException("typedef conflict...");
            }
        }

        TypeDefMapVo oTypeDefMapVo = new TypeDefMapVo();
        oTypeDefMapVo.setKeyName(strKeyName);
        oTypeDefMapVo.setValueName(strValueName);
        this.typeDefMapVos.add(oTypeDefMapVo);
    }

    //#endregion

    //#region addImportCommon

    public void addImportCommon(int iImportType, String strSentence) {
        if (!isExistsImportCommon(iImportType)) {
            ImportVo oImportVo = new ImportVo();
            oImportVo.setType(iImportType);
            oImportVo.setSentence(strSentence);
            importVoCommons.add(oImportVo);
        }
    }
    private boolean isExistsImportCommon(int iImportType) {
        for (ImportVo oImportVo : importVoCommons) {
            if (oImportVo.getType() == iImportType) {
                return true;
            }
        }

        return false;
    }

    //#endregion

    //#region addClassFile

    public ClassFileVo addClassFile(String strClassName) {
        for(ClassFileVo oClassFileVo : this.classFileVos){
            if(oClassFileVo.getClassName().equals(strClassName)){
                throw new RuntimeException("class file conflict...");
            }
        }

        ClassFileVo oClassFileVo = new ClassFileVo();
        oClassFileVo.setClassName(strClassName);
        this.classFileVos.add(oClassFileVo);
        return oClassFileVo;
    }

    //#endregion
}
