package com.kfwy.hkp.common.easyexcel.export.impl;


import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportCell;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.easyexcel.export.service.IFileExportor;
import com.kfwy.hkp.common.easyexcel.util.ReflectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ExcelExportor implements IFileExportor {
    @Override
    public Workbook getExportResult(List<?> data, List<ExportCell> exportCells) throws FileExportException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row titleRow = sheet.createRow(0);
        createTitleRow(workbook, titleRow, exportCells, sheet);
        if (List.class.isAssignableFrom(data.getClass())) {
            if (!data.isEmpty()) {
                if (data.get(0) instanceof Map) {
                    createContentRowsByMap(workbook, (List<Map>) data, exportCells, sheet);
                } else {
                    createContentRowsByBean(workbook, (List<Object>) data, exportCells, sheet);
                }
            }
        } else {
            throw new FileExportException("传入的data数据格式有误，请检查是否属于list");
        }
        return workbook;
    }

//    @Override
//    public HSSFWorkbook getExportResultH(List<?> data, List<ExportCell> exportCells) throws FileExportException {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//        Row titleRow = sheet.createRow(0);
//        createTitleRow(workbook, titleRow, exportCells, sheet);
//        if (List.class.isAssignableFrom(data.getClass())) {
//            if (!data.isEmpty()) {
//                if (data.get(0) instanceof Map) {
//                    createContentRowsByMap(workbook, (List<Map>) data, exportCells, sheet);
//                } else {
//                    createContentRowsByBeanH(workbook, (List<Object>) data, exportCells, sheet);
//                }
//            }
//        } else {
//            throw new FileExportException("传入的data数据格式有误，请检查是否属于list");
//        }
//        return workbook;
//    }

    @Override
    public Workbook getExportResult(List<?> dataList) throws FileExportException {
        Workbook workbook = new XSSFWorkbook();
        for (int a=0;a<dataList.size();a++){
            Map<String,Object> map= (Map<String, Object>) dataList.get(a);
            List<?> data= (List<?>) map.get("data");
            Sheet sheet = workbook.createSheet();
            workbook.setSheetName(a, (String) map.get("sheetName"));
            Row titleRow = sheet.createRow(0);
            ExportConfig exportConfig= (ExportConfig) map.get("exportConfig");
            createTitleRow(workbook, titleRow,  exportConfig.getExportCells(), sheet);
            if (List.class.isAssignableFrom(data.getClass())) {
                if (!data.isEmpty()) {
                    if (data.get(0) instanceof Map) {
                        createContentRowsByMap(workbook, (List<Map>) data, exportConfig.getExportCells(), sheet);
                    } else {
                        createContentRowsByBean(workbook, (List<Object>) data, exportConfig.getExportCells(), sheet);
                    }
                }
            } else {
                throw new FileExportException("传入的data数据格式有误，请检查是否属于list");
            }
        }
        return workbook;
    }


    private Workbook createContentRowsByMap(Workbook workbook, List<Map> dataList, List<ExportCell> exportCells, Sheet sheet) throws FileExportException {
        if (!dataList.isEmpty()) {
            int rowNum = 1;
            CellStyle cellStyle = createCellStyle(workbook);
            cellStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("0.00"));
            for (Map map : dataList) {
                Row row = sheet.createRow(rowNum);
                row.setHeightInPoints(23.0F);
                for (int colNum = 0; colNum < exportCells.size(); colNum++) {
                    Cell cell = row.createCell(colNum);
                    cell.setCellStyle(cellStyle);
                    ExportCell exportCell = exportCells.get(colNum);
                    Object obj = null;
                    obj = map.get(exportCell.getAlias());
                    setCellValue(obj, cell,workbook);
                }
                rowNum++;
            }
        }
        return workbook;
    }


    private static void createContentRowsByBean(Workbook workbook, List<Object> dataList, List<ExportCell> exportCells, Sheet sheet) throws FileExportException {
        int rowNum = 1;
        if (!dataList.isEmpty()) {
            CellStyle cellStyle = createCellStyle(workbook);
            for (Object t : dataList) {
                Row row = sheet.createRow(rowNum);
                row.setHeightInPoints(23.0F);
                for (int colNum = 0; colNum < exportCells.size(); colNum++) {
                    Cell cell = row.createCell(colNum);
                    cell.setCellStyle(cellStyle);
                    ExportCell exportCell = exportCells.get(colNum);
                    Object obj = null;
                    try {
                        obj = ReflectionUtils.executeMethod(t, ReflectionUtils.returnGetMethodName(exportCell.getAlias()));
                    } catch (Exception e) {
                        throw new FileExportException("执行executeMethod  出错 Alias is " + exportCell.getAlias() + " at " + e.getMessage());
                    }
                    setCellValue(obj, cell,workbook);
                }
                ++rowNum;
            }

        }
    }
    private static void createContentRowsByBeanH(Workbook workbook, List<Object> dataList, List<ExportCell> exportCells, Sheet sheet) throws FileExportException {
        int rowNum = 1;
        if (!dataList.isEmpty()) {
            CellStyle cellStyle = createCellStyle(workbook);
            for (Object t : dataList) {
                Row row = sheet.createRow(rowNum);
                row.setHeightInPoints(23.0F);
                for (int colNum = 0; colNum < exportCells.size(); colNum++) {
                    Cell cell = row.createCell(colNum);
                    cell.setCellStyle(cellStyle);
                    ExportCell exportCell = exportCells.get(colNum);
                    Object obj = null;
                    try {
                        obj = ReflectionUtils.executeMethod(t, ReflectionUtils.returnGetMethodName(exportCell.getAlias()));
                    } catch (Exception e) {
                        throw new FileExportException("执行executeMethod  出错 Alias is " + exportCell.getAlias() + " at " + e.getMessage());
                    }
                    setCellValue(obj, cell,workbook);
                }
                ++rowNum;
            }

        }
    }


    private static void setCellValue(Object obj, Cell cell,Workbook workbook) throws FileExportException {
        if (obj != null) {
            BigDecimal bigDecimal = null;
            String classType = obj.getClass().getName();
            if (classType.endsWith("String"))
                cell.setCellValue((String) obj);
            else if (("int".equals(classType)) || (classType.equals("java.lang.Integer")))
                cell.setCellValue(((Integer) obj).intValue());
            else if (("double".equals(classType)) || (classType.equals("java.lang.Double"))) {
                bigDecimal = new BigDecimal(((Double) obj).doubleValue());
                XSSFCellStyle cellStyle= (XSSFCellStyle) createCellStyle(workbook);
                cellStyle.setDataFormat(BuiltinFormats.getBuiltinFormat("0.00"));
                cell.setCellStyle(cellStyle);
                cell.setCellValue(bigDecimal.doubleValue());
            } else if (("float".equals(classType)) || (classType.equals("java.lang.Float"))) {
                bigDecimal = new BigDecimal(((Float) obj).floatValue());
                cell.setCellValue(bigDecimal.doubleValue());
            }else if ("Boolean".equals(classType) ||(classType.equals("java.lang.Boolean"))){
                cell.setCellValue(((Boolean) obj).booleanValue());
            }else if ((classType.equals("java.util.Date")) || (classType.endsWith("Date")))
                cell.setCellValue(com.kfwy.hkp.common.easyexcel.util.DateUtil.dateToString((Date) obj, com.kfwy.hkp.common.easyexcel.util.DateUtil.YYYYMMDDHHMMSS));
            else if (classType.equals("java.util.Calendar"))
                cell.setCellValue((Calendar) obj);
            else if (("char".equals(classType)) || (classType.equals("java.lang.Character")))
                cell.setCellValue(obj.toString());
            else if (("long".equals(classType)) || (classType.equals("java.lang.Long")))
                cell.setCellValue(((Long) obj).longValue());
            else if (("short".equals(classType)) || (classType.equals("java.lang.Short")))
                cell.setCellValue(((Short) obj).shortValue());
            else if (classType.equals("java.math.BigDecimal")) {
                bigDecimal = new BigDecimal(((BigDecimal)obj).doubleValue());
//                bigDecimal = new BigDecimal(bigDecimal.doubleValue());
                XSSFCellStyle cellStyle= (XSSFCellStyle) createCellStyle(workbook);
                cellStyle.setDataFormat(BuiltinFormats.getBuiltinFormat("0.00"));
                cell.setCellStyle(cellStyle);
                cell.setCellValue(bigDecimal.doubleValue());
            } else {
                throw new FileExportException("data type error !  obj is " + obj);
            }
        }
    }

    private static void createTitleRow(Workbook workbook, Row row, List<ExportCell> exportCells, Sheet sheet) {
        CellStyle style = createHeadStyle(workbook);
        row.setHeightInPoints(25.0F);
        Font font = workbook.createFont();
        //font.setColor((short) 0);
        font.setBoldweight((short) 700);
        style.setFont(font);
       style.setFillBackgroundColor((short) 0);

        int i = 0;
        for (ExportCell exportCell : exportCells) {
            sheet.setColumnWidth(i, 3200);
            Cell cell = row.createCell(i);
            cell.setCellValue(exportCell.getTitle());
            cell.setCellStyle(style);
            ++i;
        }
    }

    private static CellStyle createHeadStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 2);
        style.setVerticalAlignment((short) 1);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());//填充前景色
        style.setFillPattern((short) 1);//填充图案
        style.setBorderBottom((short) 1);//边框底部
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setWrapText(true);
        return style;
    }

    private static CellStyle createCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 2);
        style.setVerticalAlignment((short) 1);
        style.setFillForegroundColor((short) 9);
        Font font = workbook.createFont();
        font.setColor((short) 8);
        font.setFontHeightInPoints((short) 12);
        style.setWrapText(true);
        return style;
    }
}
