package org.jeecg.modules.cuLooks.controller;

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
import org.jeecg.modules.cuLooks.entity.CuLooks;
import org.jeecg.modules.cuLooks.service.ICuLooksService;

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
 * @Description: 看看
 * @author： jeecg-boot
 * @date：   2019-08-01
 * @version： V1.0
 */
@RestController
@RequestMapping("/cuLooks/cuLooks")
@Slf4j
public class CuLooksController {
	@Autowired
	private ICuLooksService cuLooksService;

	 /**
	  * 根据ID获取看看
	  */
	 @GetMapping(value = "/getOne")
	 public Result<CuLooks> getOne(String looksId){
	 	CuLooks cuLooks = cuLooksService.getById(looksId);
	 	Result<CuLooks> result = new Result<>();
	 	result.setSuccess(true);
	 	result.setResult(cuLooks);
	 	return result;
	 }

	/**
	  * 分页列表查询
	 * @param cuLooks
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CuLooks>> queryPageList(CuLooks cuLooks,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CuLooks>> result = new Result<IPage<CuLooks>>();
		//QueryWrapper<CuLooks> queryWrapper = QueryGenerator.initQueryWrapper(cuLooks, req.getParameterMap());
		Page<CuLooks> page = new Page<CuLooks>(pageNo, pageSize);

		QueryWrapper<CuLooks> queryWrapper = new QueryWrapper<>();
		String looksId = req.getParameter("looksId");
		if(oConvertUtils.isNotEmpty(looksId)) {
			queryWrapper.like("looks_id",looksId);
		}
		queryWrapper.orderByDesc("create_time");

		IPage<CuLooks> pageList = cuLooksService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param cuLooks
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CuLooks> add(@RequestBody CuLooks cuLooks) {
		Result<CuLooks> result = new Result<CuLooks>();
		try {
			cuLooksService.save(cuLooks);
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
	 * @param cuLooks
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CuLooks> edit(@RequestBody CuLooks cuLooks) {
		Result<CuLooks> result = new Result<CuLooks>();
		CuLooks cuLooksEntity = cuLooksService.getById(cuLooks.getId());
		if(cuLooksEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuLooksService.updateById(cuLooks);
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
	public Result<CuLooks> delete(@RequestParam(name="id",required=true) String id) {
		Result<CuLooks> result = new Result<CuLooks>();
		CuLooks cuLooks = cuLooksService.getById(id);
		if(cuLooks==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuLooksService.removeById(id);
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
	public Result<CuLooks> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CuLooks> result = new Result<CuLooks>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.cuLooksService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CuLooks> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CuLooks> result = new Result<CuLooks>();
		CuLooks cuLooks = cuLooksService.getById(id);
		if(cuLooks==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(cuLooks);
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
      QueryWrapper<CuLooks> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CuLooks cuLooks = JSON.parseObject(deString, CuLooks.class);
              queryWrapper = QueryGenerator.initQueryWrapper(cuLooks, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CuLooks> pageList = cuLooksService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "看看列表");
      mv.addObject(NormalExcelConstants.CLASS, CuLooks.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("看看列表数据", "导出人:Jeecg", "导出信息"));
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
              List<CuLooks> listCuLookss = ExcelImportUtil.importExcel(file.getInputStream(), CuLooks.class, params);
              for (CuLooks cuLooksExcel : listCuLookss) {
                  cuLooksService.save(cuLooksExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listCuLookss.size());
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
