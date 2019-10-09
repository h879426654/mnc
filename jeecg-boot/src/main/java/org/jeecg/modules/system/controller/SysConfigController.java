package org.jeecg.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysConfig;
import org.jeecg.modules.system.service.ISysConfigService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* @Title: Controller
* @Description: 系统配置
* @author： jeecg-boot
* @date：   2019-06-12
* @version： V1.0
*/
@RestController
@RequestMapping("/system/sysConfig")
@Slf4j
public class SysConfigController {
   @Autowired
   private ISysConfigService sysConfigService;

   /**
     * 分页列表查询
    * @param sysConfig
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @GetMapping(value = "/list")
   public Result<IPage<SysConfig>> queryPageList(SysConfig sysConfig,
                                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                 HttpServletRequest req) {
       Result<IPage<SysConfig>> result = new Result<IPage<SysConfig>>();
       QueryWrapper<SysConfig> queryWrapper = QueryGenerator.initQueryWrapper(sysConfig, req.getParameterMap());
       Page<SysConfig> page = new Page<SysConfig>(pageNo, pageSize);
       IPage<SysConfig> pageList = sysConfigService.page(page, queryWrapper);
       result.setSuccess(true);
       result.setResult(pageList);
       return result;
   }

   /**
     *   添加
    * @param sysConfig
    * @return
    */
   @PostMapping(value = "/add")
   public Result<SysConfig> add(@RequestBody SysConfig sysConfig) {
       Result<SysConfig> result = new Result<SysConfig>();
//		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//		sysConfig.setConfigId(uuid);
       try {
           sysConfigService.save(sysConfig);
           result.success("添加成功！");
       } catch (Exception e) {
           e.printStackTrace();
           log.info(e.getMessage());
           result.error500("操作失败");
       }
       return result;
   }

   /**
     *  编辑
    * @param sysConfig
    * @return
    */
   @PutMapping(value = "/edit")
   public Result<SysConfig> edit(@RequestBody SysConfig sysConfig) {
       Result<SysConfig> result = new Result<SysConfig>();
       SysConfig sysConfigEntity = sysConfigService.getById(sysConfig.getId());
       if(sysConfigEntity==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = sysConfigService.updateById(sysConfig);
           //TODO 返回false说明什么？
           if(ok) {
               result.success("修改成功!");
           }
       }

       return result;
   }

   /**
     *   通过id删除
    * @param id
    * @return
    */
   @DeleteMapping(value = "/delete")
   public Result<SysConfig> delete(@RequestParam(name="id",required=true) String id) {
       Result<SysConfig> result = new Result<SysConfig>();
       SysConfig sysConfig = sysConfigService.getById(id);
       if(sysConfig==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = sysConfigService.removeById(id);
           if(ok) {
               result.success("删除成功!");
           }
       }

       return result;
   }

   /**
     *  批量删除
    * @param ids
    * @return
    */
   @DeleteMapping(value = "/deleteBatch")
   public Result<SysConfig> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       Result<SysConfig> result = new Result<SysConfig>();
       if(ids==null || "".equals(ids.trim())) {
           result.error500("参数不识别！");
       }else {
           this.sysConfigService.removeByIds(Arrays.asList(ids.split(",")));
           result.success("删除成功!");
       }
       return result;
   }

   /**
     * 通过id查询
    * @param id
    * @return
    */
   @GetMapping(value = "/queryById")
   public Result<SysConfig> queryById(@RequestParam(name="id",required=true) String id) {
       Result<SysConfig> result = new Result<SysConfig>();
       SysConfig sysConfig = sysConfigService.getById(id);
       if(sysConfig==null) {
           result.error500("未找到对应实体");
       }else {
           result.setResult(sysConfig);
           result.setSuccess(true);
       }
       return result;
   }

 /**
     * 导出excel
  *
  * @param request
  * @param response
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
     // Step.1 组装查询条件
     QueryWrapper<SysConfig> queryWrapper = null;
     try {
         String paramsStr = request.getParameter("paramsStr");
         if (oConvertUtils.isNotEmpty(paramsStr)) {
             String deString = URLDecoder.decode(paramsStr, "UTF-8");
             SysConfig sysConfig = JSON.parseObject(deString, SysConfig.class);
             queryWrapper = QueryGenerator.initQueryWrapper(sysConfig, request.getParameterMap());
         }
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

     //Step.2 AutoPoi 导出Excel
     ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
     List<SysConfig> pageList = sysConfigService.list(queryWrapper);
     //导出文件名称
     mv.addObject(NormalExcelConstants.FILE_NAME, "系统配置列表");
     mv.addObject(NormalExcelConstants.CLASS, SysConfig.class);
     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("系统配置列表数据", "导出人:Jeecg", "导出信息"));
     mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
     return mv;
 }

 /**
     * 通过excel导入数据
  *
  * @param request
  * @param response
  * @return
  */
 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
     Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
     for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
         MultipartFile file = entity.getValue();// 获取上传文件对象
         ImportParams params = new ImportParams();
         params.setTitleRows(2);
         params.setHeadRows(1);
         params.setNeedSave(true);
         try {
             List<SysConfig> listSysConfigs = ExcelImportUtil.importExcel(file.getInputStream(), SysConfig.class, params);
             for (SysConfig sysConfigExcel : listSysConfigs) {
                 sysConfigService.save(sysConfigExcel);
             }
             return Result.ok("文件导入成功！数据行数：" + listSysConfigs.size());
         } catch (Exception e) {
             log.error(e.getMessage());
             return Result.error("文件导入失败！");
         } finally {
             try {
                 file.getInputStream().close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     return Result.ok("文件导入失败！");
 }

}
