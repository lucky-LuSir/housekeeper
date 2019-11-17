package com.kfwy.hkp.controller.file;

import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create By hjh on 2018/12/29
 */
@Controller
@RequestMapping(path = "/fileApi")
public class FileApi extends SpringRestService {

    @Autowired
    private IFileManager fileManager;

    @RequestMapping(path = "/read/{fileCode}", method = RequestMethod.GET)
    @ResponseBody
    public FileEntity read(@PathVariable String fileCode, HttpServletRequest request, HttpServletResponse response) {
        FileEntity file = fileManager.findByCode(fileCode);
        file.setFileType(null);
        return file;
    }

}
