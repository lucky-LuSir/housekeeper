package com.kfwy.hkp.common.easyexcel.export.service;



import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportCell;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface IFileExportor {
    /**
     * 数据导出
     *
     * @param data
     * @param exportCells
     * @return
     * @throws
     */
    public Object getExportResult(List<?> data, List<ExportCell> exportCells) throws FileExportException;
    public Object getExportResult(List<?> data) throws FileExportException;

}
