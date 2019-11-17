package com.kfwy.hkp.common.easyexcel.export;


import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportResult;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportType;
import com.kfwy.hkp.common.easyexcel.export.domain.excel.ExportCSVResult;
import com.kfwy.hkp.common.easyexcel.export.domain.excel.ExportExcelResult;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.easyexcel.export.impl.CSVExportor;
import com.kfwy.hkp.common.easyexcel.export.impl.ExcelExportor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public class FileExportor {
    public final static String EXPORT_XML_BASE_PATH = "/properties/framework/export/";

    /**
     * 通过list<T> T可为bean或者map<String, Object>  导出文件
     *
     * @param exportConfig
     * @param data
     * @return
     * @throws
     */
    public static ExportResult getExportResult(ExportConfig exportConfig, List<?> data) throws FileExportException {
        ExportType exportType = exportConfig.getExportType();
        switch (exportType) {
            case EXCEL2007:
                Workbook workbook = new ExcelExportor().getExportResult(data, exportConfig.getExportCells());
                ExportExcelResult exportExcelResult = new ExportExcelResult();
                exportExcelResult.setWorkbook(workbook);
                exportExcelResult.setFileName(exportConfig.getFileName());
                return exportExcelResult;
            case CSV:
                StringBuilder stringBuilder = new CSVExportor().getExportResult(data, exportConfig.getExportCells());
                ExportCSVResult exportCSVResult = new ExportCSVResult();
                exportCSVResult.setResult(stringBuilder.toString());
                exportCSVResult.setFileName(exportConfig.getFileName());
                return exportCSVResult;
        }
        throw new FileExportException("找不到对应的export type, export type is " + exportType.getNumber());
    }
    public static ExportResult getExportResultsheet(ExportConfig exportConfig, List<?> data) throws FileExportException {
        ExportType exportType = exportConfig.getExportType();
        switch (exportType) {
            case EXCEL2007:
                Workbook workbook = new ExcelExportor().getExportResult(data);
                ExportExcelResult exportExcelResult = new ExportExcelResult();
                exportExcelResult.setWorkbook(workbook);
                exportExcelResult.setFileName(exportConfig.getFileName());
                return exportExcelResult;
            case CSV:
                throw new FileExportException("csv文件不支持分sheet样式 " + exportType.getNumber());
        }
        throw new FileExportException("找不到对应的export type, export type is " + exportType.getNumber());
    }
    public static Workbook getExportWorkbook(ExportConfig exportConfig, List<?> data) throws FileExportException {
        ExportType exportType = exportConfig.getExportType();
        switch (exportType) {
            case EXCEL2007:
                Workbook workbook = new ExcelExportor().getExportResult(data, exportConfig.getExportCells());
                return workbook;

        }
        throw new FileExportException("找不到对应的export type, export type is " + exportType.getNumber());
    }

}
