package com.firelord.code_translator.translator.cpp2java.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class ClassFileVo {
    //#region Fields

    @Setter
    @Getter
    private PackageVo packageVo;

    @Setter
    @Getter
    private List<ImportVo> importVos = new ArrayList<>();

    @Setter
    @Getter
    private String className;

    @Setter
    @Getter
    private List<MethodVo> methodVos = new ArrayList<>();

    //#endregion

    //#region addImportCommon

    public void addImport(int iImportType, String strSentence) {
        if (!isExistsImport(iImportType)) {
            ImportVo oNew = new ImportVo();
            oNew.setType(iImportType);
            oNew.setSentence(strSentence);
            importVos.add(oNew);
        }
    }
    private boolean isExistsImport(int iImportType) {
        for (ImportVo oImportVo : importVos) {
            if (oImportVo.getType() == iImportType) {
                return true;
            }
        }

        return false;
    }

    //#endregion
}
