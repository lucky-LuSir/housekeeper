package com.kfwy.hkp.common.easyexcel.importfile;


import com.kfwy.hkp.common.easyexcel.importfile.domain.common.Configuration;
import com.kfwy.hkp.common.easyexcel.importfile.exception.FileImportException;
import com.kfwy.hkp.common.easyexcel.importfile.impl.XmlConfigParser;

/**
 * Created by Administrator on 2015/11/28.
 */
public class ConfigurationParserFactory {
    public static ConfigParser getConfigParser(Configuration.ParserType parserType) throws FileImportException {
        if (parserType == null) {
            throw new FileImportException("parserType is null");
        }
        if (parserType == Configuration.ParserType.XML) {
            return new XmlConfigParser();
        }
        return new XmlConfigParser();
    }


}
