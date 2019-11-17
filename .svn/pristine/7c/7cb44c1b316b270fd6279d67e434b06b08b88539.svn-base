package com.kfwy.hkp.common.easyexcel.export.domain.common;



import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;

import java.io.OutputStream;

/**
 * Created by Administrator on 2015/11/6.
 */
public abstract class ExportResult {
    private String fileName;

    public abstract Object getResult();

    public abstract void export(OutputStream outputStream) throws FileExportException;

    public abstract ExportType getExportType();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
