package com.kfwy.hkp.common.easyexcel.importfile;



import com.kfwy.hkp.common.easyexcel.importfile.domain.common.Configuration;
import com.kfwy.hkp.common.easyexcel.importfile.domain.common.ImportResult;
import com.kfwy.hkp.common.easyexcel.importfile.exception.FileImportException;
import com.kfwy.hkp.common.easyexcel.importfile.impl.ExcelImportor;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/11/28.
 */
public class FileImportExecutor {

    public static ImportResult<Object> importFile(Configuration configuration, InputStream is, String fileName) throws FileImportException {
        FileImportor fileImportor = getFileImportor(configuration);
        return fileImportor.getImportResult(is, fileName);
    }

    public static ImportResult<Object> importFile(Configuration configuration, File file, String fileName) throws FileImportException {
        FileImportor fileImportor = getFileImportor(configuration);
        return fileImportor.getImportResult(file, fileName);
    }

    /**
     * 根据configuration里面 imporFileType返回
     * 默认 返回 ExcelImportor
     *
     * @param configuration
     * @return
     */
    private static FileImportor getFileImportor(Configuration configuration) throws FileImportException {
        if (configuration.getImportFileType() == Configuration.ImportFileType.EXCEL) {
            return new ExcelImportor(configuration);
        }
        throw new FileImportException("Can not find Importor. Please check importFileType in configuration");
    }
}
