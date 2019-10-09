package org.jeecg.modules.cuReatilMoney.controller;

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
import org.jeecg.modules.cuReatilMoney.entity.CuReatilMoney;
import org.jeecg.modules.cuReatilMoney.service.ICuReatilMoneyService;

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
 * @Description: mp制度规则
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@RestController
@RequestMapping("/cuReatilMoney/cuReatilMoney")
@Slf4j
public class CuReatilMoneyController {
	@Autowired
	private ICuReatilMoneyService cuReatilMoneyService;
	
	/**
	  * 分页列表查询
	 * @param cuReatilMoney
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CuReatilMoney>> queryPageList(CuReatilMoney cuReatilMoney,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CuReatilMoney>> result = new Result<IPage<CuReatilMoney>>();
		QueryWrapper<CuReatilMoney> queryWrapper = QueryGenerator.initQueryWrapper(cuReatilMoney, req.getParameterMap()).orderByAsc("id");
		Page<CuReatilMoney> page = new Page<CuReatilMoney>(pageNo, pageSize);
		IPage<CuReatilMoney> pageList = cuReatilMoneyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param cuReatilMoney
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CuReatilMoney> add(@RequestBody CuReatilMoney cuReatilMoney) {
		Result<CuReatilMoney> result = new Result<CuReatilMoney>();
		try {
			cuReatilMoneyService.save(cuReatilMoney);
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
	 * @param cuReatilMoney
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CuReatilMoney> edit(@RequestBody CuReatilMoney cuReatilMoney) {
		Result<CuReatilMoney> result = new Result<CuReatilMoney>();
		CuReatilMoney cuReatilMoneyEntity = cuReatilMoneyService.getById(cuReatilMoney.getId());
		if(cuReatilMoneyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuReatilMoneyService.updateById(cuReatilMoney);
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
	public Result<CuReatilMoney> delete(@RequestParam(name="id",required=true) String id) {
		Result<CuReatilMoney> result = new Result<CuReatilMoney>();
		CuReatilMoney cuReatilMoney = cuReatilMoneyService.getById(id);
		if(cuReatilMoney==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuReatilMoneyService.removeById(id);
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
	public Result<CuReatilMoney> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CuReatilMoney> result = new Result<CuReatilMoney>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.cuReatilMoneyService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CuReatilMoney> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CuReatilMoney> result = new Result<CuReatilMoney>();
		CuReatilMoney cuReatilMoney = cuReatilMoneyService.getById(id);
		if(cuReatilMoney==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(cuReatilMoney);
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
      QueryWrapper<CuReatilMoney> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CuReatilMoney cuReatilMoney = JSON.parseObject(deString, CuReatilMoney.class);
              queryWrapper = QueryGenerator.initQueryWrapper(cuReatilMoney, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CuReatilMoney> pageList = cuReatilMoneyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "mp制度规则列表");
      mv.addObject(NormalExcelConstants.CLASS, CuReatilMoney.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("mp制度规则列表数据", "导出人:Jeecg", "导出信息"));
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
              List<CuReatilMoney> listCuReatilMoneys = ExcelImportUtil.importExcel(file.getInputStream(), CuReatilMoney.class, params);
              for (CuReatilMoney cuReatilMoneyExcel : listCuReatilMoneys) {
                  cuReatilMoneyService.save(cuReatilMoneyExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listCuReatilMoneys.size());
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
