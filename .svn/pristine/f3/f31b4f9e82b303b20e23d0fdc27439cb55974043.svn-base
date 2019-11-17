package com.kfwy.hkp.common.easyexcel.export;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportCell;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportType;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.easyexcel.importfile.ConfigParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyuanxin on 2016/3/25.
 */
public class ExportConfigFactory {


    public static void exportMethod(List<?> data, HttpServletResponse httpServletResponse, InputStream inputStream, String exportName) throws IOException, FileExportException {
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(inputStream);
        StringBuffer name = new StringBuffer();
        try {
            workbook = FileExportor.getExportWorkbook(exportConfig, data);
            String _name = name.toString() +exportName+".xlsx";
            String filename = new String(_name.getBytes("UTF-8"), "ISO8859-1");
            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        } catch (Exception e) {

        } finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }
        }
    }

    public static ExportConfig getExportConfig(InputStream inputStream, List<String> column) throws FileExportException {
        return getExportCells(inputStream,column);
    }
    public static ExportConfig getExportConfig(InputStream inputStream) throws FileExportException {
        return getExportCells(inputStream,null);
    }

    private static ExportConfig getExportCells(InputStream inputStream,List<String> column) throws FileExportException {

        ExportConfig exportConfig = new ExportConfig();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document document = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(inputStream);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new FileExportException(e, "pares xml error");
        }


        Element root = document.getDocumentElement();
        NodeList elements = root.getElementsByTagName("cell");
        List<ExportCell> exportCells=new ArrayList<>();
        if(null==column || column.size()==0){
            exportCells = initElement(elements);
        }else {
            exportCells =initElement(elements,column);
        }

        String fileName = "";
        String exportType1 = "";
        try {
            fileName = ConfigParser.getNodeText(root, "fileName");
            exportType1 = ConfigParser.getNodeText(root, "exportType");
        } catch (Exception e) {
            throw new FileExportException(e);
        }
        if (StringUtils.isEmpty(fileName)) {
            throw new FileExportException("用于导出的xml文档 <fileName> 为空");
        }

        if (StringUtils.isEmpty(exportType1) || !StringUtils.isNumeric(exportType1)) {
            throw new FileExportException("用于导出的xml文档 <exportType> 为空");
        }

        exportConfig.setFileName(fileName);
        ExportType exportType = ExportType.getExportType(Integer.valueOf(exportType1));
        if (exportType == null) {
            throw new FileExportException("找不到相应的ExportType 解析xml得到的exportType 是" + exportType1);
        }
        exportConfig.setExportType(exportType);
        exportConfig.setExportCells(exportCells);

        return exportConfig;
    }

    private static List<ExportCell> initElement(NodeList elements) throws FileExportException {

        List<ExportCell> exportCells = new ArrayList<ExportCell>(elements.getLength());
        for (int i = 0; i < elements.getLength(); i++) {
            ExportCell exportCell = new ExportCell();
            Element node = (Element) elements.item(i);
            String titleText = "";
            String aliasText = "";
            try {
                titleText = ConfigParser.getNodeText(node, "title");
                aliasText = ConfigParser.getNodeText(node, "alias");
            } catch (Exception e) {
                throw new FileExportException(e);
            }
            if (StringUtils.isEmpty(titleText)) {
                throw new FileExportException("用于导出的xml文档 <title> 为空");
            }
            exportCell.setTitle(titleText);

            if (StringUtils.isEmpty(aliasText)) {
                throw new FileExportException("用于导出的xml文档 <alias> 为空");
            }
            exportCell.setAlias(aliasText);

            exportCells.add(exportCell);
        }

        if (exportCells.isEmpty()) {
            throw new FileExportException("用于导出的xml文档解析内容为空");
        }

        return exportCells;
    }

    private static List<ExportCell> initElement(NodeList elements,List<String> column) throws FileExportException {

        List<ExportCell> exportCells = new ArrayList<ExportCell>(elements.getLength());
        for (int i = 0; i < elements.getLength(); i++) {
            ExportCell exportCell = new ExportCell();
            Element node = (Element) elements.item(i);
            String titleText = "";
            String aliasText = "";
            try {
                if(null==column||column.size()==0){
                    titleText = ConfigParser.getNodeText(node, "title");
                    aliasText = ConfigParser.getNodeText(node, "alias");
                }else {
                    aliasText = ConfigParser.getNodeText(node, "alias");
                    if (!column.contains(aliasText)){
                        continue;
                    }
                    titleText = ConfigParser.getNodeText(node, "title");
                }

            } catch (Exception e) {
                throw new FileExportException(e);
            }
            if (StringUtils.isEmpty(titleText)) {
                throw new FileExportException("用于导出的xml文档 <title> 为空");
            }
            exportCell.setTitle(titleText);

            if (StringUtils.isEmpty(aliasText)) {
                throw new FileExportException("用于导出的xml文档 <alias> 为空");
            }
            exportCell.setAlias(aliasText);

            exportCells.add(exportCell);
        }

        if (exportCells.isEmpty()) {
            throw new FileExportException("用于导出的xml文档解析内容为空");
        }

        return exportCells;
    }

    /**
     * title:动态导出
     * @param
     * @param
     * @return
     * @throws FileExportException
     */

    public static ExportConfig getDynamicExportCells(LinkedHashMap map) throws FileExportException {

        ExportConfig exportConfig = new ExportConfig();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document document = null;

        ExportType exportType = ExportType.getExportType(Integer.valueOf("0"));
        List<ExportCell> exportCells=new ArrayList<>();
        exportCells =initDynamicElement(map);
        exportConfig.setExportType(exportType);
        exportConfig.setFileName("");
        exportConfig.setExportCells(exportCells);

        return exportConfig;
    }

    /**
     * title:动态
     * @param
     * @param
     * @return
     * @throws FileExportException
     */
    private static List<ExportCell> initDynamicElement(Map<String,Object>map) throws FileExportException {
        List<ExportCell> exportCells = new ArrayList<ExportCell>(map.size());
        ExportCell exportCell = null;
        String titleText = "";
        String aliasText = "";

        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                exportCell = new ExportCell();
                aliasText =entry.getKey();
                titleText =String.valueOf(entry.getValue());
                exportCell.setTitle(titleText);
                exportCell.setAlias(aliasText);
                exportCells.add(exportCell);
            }

        } catch (Exception e) {

            throw new FileExportException(e);
        }

        if (exportCells.isEmpty()) {
            throw new FileExportException("用于导出的xml文档解析内容为空");
        }

        return exportCells;
    }

}
