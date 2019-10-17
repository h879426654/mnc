package org.jeecg.modules.adminLogs.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.adminLogs.entity.AdminLogs;
import org.jeecg.modules.adminLogs.service.IAdminLogsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
import com.alibaba.fastjson.JSON;

 /**
 * @Title: Controller
 * @Description: 后台操作人员日志
 * @author： jeecg-boot
 * @date：   2019-10-16
 * @version： V1.0
 */
@RestController
@RequestMapping("/adminLogs/adminLogs")
@Slf4j
public class AdminLogsController {
	@Autowired
	private IAdminLogsService adminLogsService;
	
	/**
	  * 分页列表查询
	 * @param adminLogs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<AdminLogs>> queryPageList(AdminLogs adminLogs,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<AdminLogs>> result = new Result<IPage<AdminLogs>>();
		QueryWrapper<AdminLogs> queryWrapper = QueryGenerator.initQueryWrapper(adminLogs, req.getParameterMap());
		Page<AdminLogs> page = new Page<AdminLogs>(pageNo, pageSize);
		IPage<AdminLogs> pageList = adminLogsService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param adminLogs
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<AdminLogs> add(@RequestBody AdminLogs adminLogs) {
		Result<AdminLogs> result = new Result<AdminLogs>();
		try {
			adminLogsService.save(adminLogs);
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
	 * @param adminLogs
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<AdminLogs> edit(@RequestBody AdminLogs adminLogs) {
		Result<AdminLogs> result = new Result<AdminLogs>();
		AdminLogs adminLogsEntity = adminLogsService.getById(adminLogs.getId());
		if(adminLogsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = adminLogsService.updateById(adminLogs);
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
	public Result<AdminLogs> delete(@RequestParam(name="id",required=true) String id) {
		Result<AdminLogs> result = new Result<AdminLogs>();
		AdminLogs adminLogs = adminLogsService.getById(id);
		if(adminLogs==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = adminLogsService.removeById(id);
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
	public Result<AdminLogs> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<AdminLogs> result = new Result<AdminLogs>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.adminLogsService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<AdminLogs> queryById(@RequestParam(name="id",required=true) String id) {
		Result<AdminLogs> result = new Result<AdminLogs>();
		AdminLogs adminLogs = adminLogsService.getById(id);
		if(adminLogs==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(adminLogs);
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
      QueryWrapper<AdminLogs> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              AdminLogs adminLogs = JSON.parseObject(deString, AdminLogs.class);
              queryWrapper = QueryGenerator.initQueryWrapper(adminLogs, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<AdminLogs> pageList = adminLogsService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "后台操作人员日志列表");
      mv.addObject(NormalExcelConstants.CLASS, AdminLogs.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("后台操作人员日志列表数据", "导出人:Jeecg", "导出信息"));
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
              List<AdminLogs> listAdminLogss = ExcelImportUtil.importExcel(file.getInputStream(), AdminLogs.class, params);
              for (AdminLogs adminLogsExcel : listAdminLogss) {
                  adminLogsService.save(adminLogsExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listAdminLogss.size());
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
