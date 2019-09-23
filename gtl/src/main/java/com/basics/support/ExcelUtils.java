package com.basics.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * Excel工具类
 * 
 * @author zhaidong
 *
 */
public class ExcelUtils {

 public static void generatePayOfflineExcel(List<Object> apoList, OutputStream os) {
  InputStream is = ExcelUtils.class.getResourceAsStream("template_pay_offline.xlsx");
  Configuration config = new Configuration();
  Map<String, List<Object>> map = new HashMap<String, List<Object>>();
  map.put("items", apoList);
  XLSTransformer trans = new XLSTransformer(config);
  XSSFWorkbook resultWorkbook;
  try {
   resultWorkbook = (XSSFWorkbook) trans.transformXLS(is, map);
   resultWorkbook.write(os);
  } catch (ParsePropertyException e) {
   e.printStackTrace();
  } catch (InvalidFormatException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   if (os != null)
    try {
     os.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
  }
 }
}
