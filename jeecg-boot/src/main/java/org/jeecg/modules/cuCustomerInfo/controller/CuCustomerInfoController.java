package org.jeecg.modules.cuCustomerInfo.controller;

import java.util.ArrayList;
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
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerExport;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo2;
import org.jeecg.modules.cuCustomerInfo.mapper.CuCustomerInfoMapper;
import org.jeecg.modules.cuCustomerInfo.service.ICuCustomerInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.walletInfo.entity.WalletInfo;
import org.jeecg.modules.walletInfo.mapper.WalletInfoMapper;
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
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@RestController
@RequestMapping("/cuCustomerInfo/cuCustomerInfo/")
@Slf4j
public class CuCustomerInfoController {
	@Autowired
	private ICuCustomerInfoService cuCustomerInfoService;
	@Autowired
	private WalletInfoMapper walletInfoMapper;
	@Autowired
	private CuCustomerInfoMapper cuCustomerInfoMapper;
	/**
	  * 分页列表查询
	 * @param cuCustomerInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CuCustomerInfo2>> queryPageList(CuCustomerInfo cuCustomerInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CuCustomerInfo2>> result = new Result<>();
		QueryWrapper<CuCustomerInfo> queryWrapper = QueryGenerator.initQueryWrapper(cuCustomerInfo, req.getParameterMap());
		Page<CuCustomerInfo> page = new Page<CuCustomerInfo>(pageNo, pageSize);
		IPage<CuCustomerInfo> pageList = cuCustomerInfoService.page(page, queryWrapper);
		List<CuCustomerInfo> list = pageList.getRecords();
		IPage<CuCustomerInfo2> pageList2 = new Page<>();
		pageList2.setPages(pageList.getPages());
		pageList2.setSize(pageList.getSize());
		pageList2.setCurrent(pageList.getCurrent());
		pageList2.setTotal(pageList.getTotal());
		List<CuCustomerInfo2> list2 = new ArrayList<>();
		for (CuCustomerInfo cuCustomerInfo1: list) {
			CuCustomerInfo2 cuCustomerInfo2 = new CuCustomerInfo2();
			cuCustomerInfo2.setCustomerId(cuCustomerInfo1.getCustomerId());
			cuCustomerInfo2.setCustomerHead(cuCustomerInfo1.getCustomerHead());
			cuCustomerInfo2.setCustomerName(cuCustomerInfo1.getCustomerName());
			cuCustomerInfo2.setCustomerNumber(cuCustomerInfo1.getCustomerNumber());
			cuCustomerInfo2.setCustomerPhone(cuCustomerInfo1.getCustomerPhone());
			cuCustomerInfo2.setCustomerStatus(cuCustomerInfo1.getCustomerStatus());
			cuCustomerInfo2.setRegisterTime(cuCustomerInfo1.getRegisterTime());
			WalletInfo walletInfo = walletInfoMapper.selectOne(new QueryWrapper<WalletInfo>().eq("user_Id", cuCustomerInfo1.getCustomerId()));
			if (null != walletInfo) {
				cuCustomerInfo2.setMnc(walletInfo.getMncNum());
				cuCustomerInfo2.setScore(walletInfo.getScoreNum());
				cuCustomerInfo2.setSuperNum(walletInfo.getSuperNum().add(walletInfo.getReleasedMncNum()));
				cuCustomerInfo2.setMtoken(walletInfo.getMtokenNum());
			}
			list2.add(cuCustomerInfo2);
		}
		pageList2.setRecords(list2);
		result.setSuccess(true);
		result.setResult(pageList2);
		return result;
	}
	
	/**
	  *   添加
	 * @param cuCustomerInfo
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CuCustomerInfo> add(@RequestBody CuCustomerInfo cuCustomerInfo) {
		Result<CuCustomerInfo> result = new Result<CuCustomerInfo>();
		try {
			cuCustomerInfoService.save(cuCustomerInfo);
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
	 * @param cuCustomerInfo
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CuCustomerInfo> edit(@RequestBody CuCustomerInfo cuCustomerInfo) {
		Result<CuCustomerInfo> result = new Result<CuCustomerInfo>();
		CuCustomerInfo cuCustomerInfoEntity = cuCustomerInfoService.getById(cuCustomerInfo.getCustomerId());
		if(cuCustomerInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuCustomerInfoService.updateById(cuCustomerInfo);
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
	public Result<CuCustomerInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<CuCustomerInfo> result = new Result<CuCustomerInfo>();
		CuCustomerInfo cuCustomerInfo = cuCustomerInfoService.getById(id);
		if(cuCustomerInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuCustomerInfoService.removeById(id);
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
	public Result<CuCustomerInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CuCustomerInfo> result = new Result<CuCustomerInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.cuCustomerInfoService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CuCustomerInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CuCustomerInfo> result = new Result<CuCustomerInfo>();
		CuCustomerInfo cuCustomerInfo = cuCustomerInfoService.getById(id);
		if(cuCustomerInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(cuCustomerInfo);
			result.setSuccess(true);
		}
		return result;
	}

  /**
   * 导出excel
   *
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(CuCustomerExport cuCustomerExport) {
      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
	  QueryWrapper<CuCustomerInfo> queryWrapper = new QueryWrapper<CuCustomerInfo>();
	  if (null != cuCustomerExport.getName() && !cuCustomerExport.getName().isEmpty()) {
	  	queryWrapper.eq("customer_Name", cuCustomerExport.getName());
	  }
	  if (null != cuCustomerExport.getPhone()) {
		  queryWrapper.eq("customer_Phone", cuCustomerExport.getPhone());
	  }
      List<CuCustomerInfo> cuCustomerInfos = cuCustomerInfoMapper.selectList(queryWrapper);
      List<CuCustomerExport> pageList = new ArrayList<>();
	  for (CuCustomerInfo cuCustomerInfo : cuCustomerInfos) {
	  	CuCustomerExport cuCustomerExport1 = new CuCustomerExport();
	  	WalletInfo walletInfo = walletInfoMapper.selectOne(new QueryWrapper<WalletInfo>().eq("user_Id", cuCustomerInfo.getCustomerId()));
	  	cuCustomerExport1.setName(cuCustomerInfo.getCustomerName());
	  	cuCustomerExport1.setPhone(cuCustomerInfo.getCustomerPhone());
	  	cuCustomerExport1.setMnc(walletInfo.getMncNum());
	  	cuCustomerExport1.setMtoken(walletInfo.getMtokenNum());
	  	cuCustomerExport1.setScoreNum(walletInfo.getScoreNum());
	  	cuCustomerExport1.setSuperNum(walletInfo.getSuperNum().add(walletInfo.getReleasedMncNum()));
	  	pageList.add(cuCustomerExport1);
	  }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "用户表列表");
      mv.addObject(NormalExcelConstants.CLASS, CuCustomerExport.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("用户表列表数据", "导出人:admin", "导出信息"));
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
              List<CuCustomerInfo> listCuCustomerInfos = ExcelImportUtil.importExcel(file.getInputStream(), CuCustomerInfo.class, params);
              for (CuCustomerInfo cuCustomerInfoExcel : listCuCustomerInfos) {
                  cuCustomerInfoService.save(cuCustomerInfoExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listCuCustomerInfos.size());
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

	/**
	* 统计管理
	* @return
	*/
	@PostMapping(value = "/searchCount")
	public String searchCount() {
	return cuCustomerInfoService.searchCount();
	}

	@GetMapping(value = "/searchCustomer")
	public String searchCustomer (String phone, String name, Integer pageNo, Integer pageSize) {
		return cuCustomerInfoService.searchCustomer(phone, name, pageNo, pageSize);
	}
}
