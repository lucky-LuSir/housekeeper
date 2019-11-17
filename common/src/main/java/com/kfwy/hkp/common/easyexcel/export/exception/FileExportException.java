package com.kfwy.hkp.common.easyexcel.export.exception;

/**
 * Created by Administrator on 2015/11/2.
 */
public class FileExportException extends Exception {

    public FileExportException(String message) {
        super(message);
    }

    public FileExportException(Throwable throwable, String message) {
        super(message, throwable);
    }

    public FileExportException(Throwable throwable) {
        super(throwable);
    }
}
