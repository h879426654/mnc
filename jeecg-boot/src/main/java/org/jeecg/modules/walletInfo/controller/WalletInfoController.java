package org.jeecg.modules.walletInfo.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.adminLogs.entity.AdminLogs;
import org.jeecg.modules.adminLogs.mapper.AdminLogsMapper;
import org.jeecg.modules.walletInfo.entity.MtokenMnc;
import org.jeecg.modules.walletInfo.entity.WalletInfo;
import org.jeecg.modules.walletInfo.mapper.WalletInfoMapper;
import org.jeecg.modules.walletInfo.service.IWalletInfoService;

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
 * @Description: mnc管理
 * @author： jeecg-boot
 * @date：   2019-10-11
 * @version： V1.0
 */
@RestController
@RequestMapping("/walletInfo/walletInfo")
@Slf4j
public class WalletInfoController {
	@Autowired
	private IWalletInfoService walletInfoService;
	 @Autowired
	 private WalletInfoMapper walletInfoMapper;
	@Autowired
	private AdminLogsMapper adminLogsMapper;
	 /**
	  * 分页列表查询
	 * @param walletInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WalletInfo>> queryPageList(WalletInfo walletInfo,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WalletInfo>> result = new Result<IPage<WalletInfo>>();
		if (null == walletInfo.getUserId() || walletInfo.getUserId().isEmpty()) {
			result.error500("重新登陆");
			return result;
		}
		QueryWrapper<WalletInfo> queryWrapper = QueryGenerator.initQueryWrapper(walletInfo, req.getParameterMap());
		Page<WalletInfo> page = new Page<WalletInfo>(pageNo, pageSize);
		IPage<WalletInfo> pageList = walletInfoService.page(page, queryWrapper);
		List<WalletInfo> list = pageList.getRecords();
		for (WalletInfo walletInfo1 : list) {
			walletInfo.setSuperNum(walletInfo1.getSuperNum().add(walletInfo1.getReleasedMncNum()));
		}
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param walletInfo
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WalletInfo> add(@RequestBody WalletInfo walletInfo) {
		Result<WalletInfo> result = new Result<WalletInfo>();
		try {
			walletInfoService.save(walletInfo);
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
	 * @param walletInfo
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WalletInfo> edit(@RequestBody WalletInfo walletInfo) {
		Result<WalletInfo> result = new Result<WalletInfo>();
		WalletInfo walletInfoEntity = walletInfoService.getById(walletInfo.getUserId());
		if(walletInfoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = walletInfoService.updateById(walletInfo);
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
	public Result<WalletInfo> delete(@RequestParam(name="id",required=true) String id) {
		Result<WalletInfo> result = new Result<WalletInfo>();
		WalletInfo walletInfo = walletInfoService.getById(id);
		if(walletInfo==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = walletInfoService.removeById(id);
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
	public Result<WalletInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WalletInfo> result = new Result<WalletInfo>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.walletInfoService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WalletInfo> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WalletInfo> result = new Result<WalletInfo>();
		WalletInfo walletInfo = walletInfoService.getById(id);
		if(walletInfo==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(walletInfo);
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
		 QueryWrapper<WalletInfo> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 WalletInfo walletInfo = JSON.parseObject(deString, WalletInfo.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(walletInfo, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<WalletInfo> pageList = walletInfoService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "mnc管理列表");
		 mv.addObject(NormalExcelConstants.CLASS, WalletInfo.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("mnc管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WalletInfo> listWalletInfos = ExcelImportUtil.importExcel(file.getInputStream(), WalletInfo.class, params);
              for (WalletInfo walletInfoExcel : listWalletInfos) {
                  walletInfoService.save(walletInfoExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listWalletInfos.size());
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
	 @RequestMapping(value = "/searchDate")
	 public String searchDate() {
		 String url = "http://47.75.47.121:8080/gtl/api/gty/wallet/getLimitInfo.do";
		 return walletInfoService.httpClient(url);
	 }
	 @RequestMapping(value = "/searchDatet")
	 public String searchDatet(String num, String type, String id) {
		 String url = "http://47.75.47.121:8080/gtl/api/gty/wallet/setReleasePointLimit.do?num="+new BigDecimal(num)+"&type="+type+"&id="+id;
		 return walletInfoService.httpClient(url);
	 }
	 @RequestMapping(value = "/searchDatets")
	 public String searchDatets(String type, String upNum, String downNum, String id) {
		 String url = "http://47.75.47.121:8080/gtl/api/gty/wallet/setReleaseLimit.do?type="+type+"&upNum="+new BigDecimal(upNum)+"&downNum="+new BigDecimal(downNum)+"&id="+id;
		 return walletInfoService.httpClient(url);
	 }
	 @PutMapping(value = "/updateMTokenAndMnc")
	 public Result<WalletInfo> edit(@RequestBody MtokenMnc mtokenMnc, ProceedingJoinPoint joinPoint) {
		 String className = "admin";

		 Result<WalletInfo> result = new Result<WalletInfo>();
		 WalletInfo walletInfoEntity = walletInfoMapper.selectOne(new QueryWrapper<WalletInfo>().eq("user_id", mtokenMnc.getUserId()));
		 if(walletInfoEntity==null) {
			 result.error500("未找到对应实体");
		 }else {
		 	 //加
		 	 if ("0".equals(mtokenMnc.getState())) {
				if ("1".equals(mtokenMnc.getType())) {
					walletInfoEntity.setMncNum(walletInfoEntity.getMncNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "1", "增加mnc:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				} else if ("2".equals(mtokenMnc.getType())) {
					walletInfoEntity.setMtokenNum(walletInfoEntity.getMtokenNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "2", "增加mtoken:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				} else if ("3".equals(mtokenMnc.getType())) {
					walletInfoEntity.setRecordNum(walletInfoEntity.getRecordNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "3", "增加记账钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				} else if ("4".equals(mtokenMnc.getType())) {
					walletInfoEntity.setScoreNum(walletInfoEntity.getScoreNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "4", "增加创业积分:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				} else if ("5".equals(mtokenMnc.getType())) {
					walletInfoEntity.setMoveNum(walletInfoEntity.getMoveNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "5", "增加流通钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				} else if ("6".equals(mtokenMnc.getType())) {
					walletInfoEntity.setReleasedMncNum(walletInfoEntity.getReleasedMncNum().add(mtokenMnc.getMoney()));
					insertAdminLogs(className, "6", "增加超级钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
				}
			 }
		 	 //减
			 if ("1".equals(mtokenMnc.getState())) {
				 if ("1".equals(mtokenMnc.getType())) {
				 	BigDecimal mnc = walletInfoEntity.getMncNum().subtract(mtokenMnc.getMoney());
				 	if (mnc.compareTo(BigDecimal.ZERO) >= 0) {
						walletInfoEntity.setMncNum(mnc);
						insertAdminLogs(className, "1", "减少mnc:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					}  else {
				 		result.error500("扣除的mnc不能大于当前mnc");
				 		return result;
					}
				 } else if ("2".equals(mtokenMnc.getType())) {
				 	BigDecimal mtoken = walletInfoEntity.getMtokenNum().subtract(mtokenMnc.getMoney());
					 if (mtoken.compareTo(BigDecimal.ZERO) >= 0) {
						 walletInfoEntity.setMtokenNum(mtoken);
						 insertAdminLogs(className, "2", "减少mtoken:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					 }  else {
						 result.error500("扣除的mtoken不能大于当前mtoen");
						 return result;
					 }
				 } else if ("3".equals(mtokenMnc.getType())) {
					 BigDecimal recordNum = walletInfoEntity.getRecordNum().subtract(mtokenMnc.getMoney());
					 if (recordNum.compareTo(BigDecimal.ZERO) >= 0) {
						 walletInfoEntity.setRecordNum(recordNum);
						 insertAdminLogs(className, "3", "减少记账钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					 }  else {
						 result.error500("扣除的记账钱包不能大于当前记账钱包");
						 return result;
					 }
				 } else if ("4".equals(mtokenMnc.getType())) {
					 BigDecimal score = walletInfoEntity.getScoreNum().subtract(mtokenMnc.getMoney());
					 if (score.compareTo(BigDecimal.ZERO) >= 0) {
						 walletInfoEntity.setScoreNum(score);
						 insertAdminLogs(className, "4", "减少创业积分:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					 }  else {
						 result.error500("扣除的创业积分不能大于当前创业积分");
						 return result;
					 }
				 } else if ("5".equals(mtokenMnc.getType())) {
					 BigDecimal move = walletInfoEntity.getMoveNum().subtract(mtokenMnc.getMoney());
					 if (move.compareTo(BigDecimal.ZERO) >= 0) {
						 walletInfoEntity.setMoveNum(move);
						 insertAdminLogs(className, "5", "减少流通钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					 }  else {
						 result.error500("扣除的流通钱包不能大于当前流通钱包");
						 return result;
					 }
				 } else if ("6".equals(mtokenMnc.getType())) {
					 BigDecimal rSuper = walletInfoEntity.getReleasedMncNum().subtract(mtokenMnc.getMoney());
					 if (rSuper.compareTo(BigDecimal.ZERO) >= 0) {
						 walletInfoEntity.setReleasedMncNum(rSuper);
						 insertAdminLogs(className, "6", "减少超级钱包:"+mtokenMnc.getMoney(), mtokenMnc.getUserId());
					 }  else {
						 result.error500("扣除的超级钱包不能大于当前超级钱包");
						 return result;
					 }
				 }
			 }
			 walletInfoService.update(walletInfoEntity, new QueryWrapper<WalletInfo>().eq("user_id", mtokenMnc.getUserId()));
			 result.success("修改成功!");
		 }

		 return result;
	 }

	 private void insertAdminLogs(String className, String type, String remark, String customerId) {
		 AdminLogs adminLogs = new AdminLogs();
		 adminLogs.setId(UUID.randomUUID().toString().replace("-",""));
		 adminLogs.setCreateTime(new Date());
		 adminLogs.setCustomerId(customerId);
		 adminLogs.setMan(className);
		 adminLogs.setType(type);
		 adminLogs.setRemark(remark);
		 adminLogsMapper.insert(adminLogs);
	 }
}
