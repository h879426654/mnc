package org.jeecg.modules.mallShopAdvert.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo;
import org.jeecg.modules.cuCustomerInfo.mapper.CuCustomerInfoMapper;
import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import org.jeecg.modules.mallShopAdvert.mapper.MallShopAdvertMapper;
import org.jeecg.modules.mallShopAdvert.service.IMallShopAdvertService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.mallUser.entity.MallUser;
import org.jeecg.modules.mallUser.mapper.MallUserMapper;
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
 * @Description: 商家表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@RestController
@RequestMapping("/mallShopAdvert/mallShopAdvert")
@Slf4j
public class MallShopAdvertController {
	@Autowired
	private IMallShopAdvertService mallShopAdvertService;
	@Autowired
	private MallShopAdvertMapper mallShopAdvertMapper;
	@Autowired
	private CuCustomerInfoMapper cuCustomerInfoMapper;
	@Autowired
	private MallUserMapper mallUserMapper;
	/**
	  * 分页列表查询
	 * @param mallShopAdvert
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<MallShopAdvert>> queryPageList(MallShopAdvert mallShopAdvert,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MallShopAdvert>> result = new Result<IPage<MallShopAdvert>>();
		QueryWrapper<MallShopAdvert> queryWrapper = QueryGenerator.initQueryWrapper(mallShopAdvert, req.getParameterMap()).orderByAsc("apply_Status");
		Page<MallShopAdvert> page = new Page<MallShopAdvert>(pageNo, pageSize);
		IPage<MallShopAdvert> pageList = mallShopAdvertService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param mallShopAdvert
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MallShopAdvert> add(@RequestBody MallShopAdvert mallShopAdvert) {
		Result<MallShopAdvert> result = new Result<MallShopAdvert>();
		try {
			mallShopAdvertService.save(mallShopAdvert);
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
	 * @param mallShopAdvert
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MallShopAdvert> edit(@RequestBody MallShopAdvert mallShopAdvert) {
		Result<MallShopAdvert> result = new Result<MallShopAdvert>();
		MallShopAdvert mallShopAdvertEntity = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("advert_id", mallShopAdvert.getAdvertId()));
		if(mallShopAdvertEntity==null) {
			result.error500("未找到对应实体");
		}else {
			if (null != mallShopAdvert.getApplyStatus()) {
				mallShopAdvertMapper.updateByIdAndApplyStatus(mallShopAdvert.getAdvertId(), mallShopAdvert.getApplyStatus());
				if ("2".equals(mallShopAdvert.getApplyStatus())){
					CuCustomerInfo cuCustomerInfo = cuCustomerInfoMapper.selectOne(new QueryWrapper<CuCustomerInfo>().eq("customer_id", mallShopAdvertEntity.getCustomerId()));
					MallUser mu = mallUserMapper.selectOne(new QueryWrapper<MallUser>().eq("user_name", cuCustomerInfo.getCustomerPhone()));
					if (null != mu) {
						if ("1".equals(mu.getState())) {
							mu.setState("0");
							mallUserMapper.updateById(mu);
						}
					} else {
						MallUser mallUser = new MallUser();
						mallUser.setUserName(cuCustomerInfo.getCustomerPhone());
						int random = (int) ((Math.random() * 9 + 1) * 100000);
						mallUser.setPassWord(String.valueOf(random));
						mallUser.setCustomerId(cuCustomerInfo.getCustomerId());
						mallUser.setCreateTime(new Date());
						mallUser.setId(UUID.randomUUID().toString().replace("-",""));
						mallUserMapper.insert(mallUser);
					}
				}
				result.success("成功");
			} else if (null != mallShopAdvert.getHot()) {
				if (!"2".equals(mallShopAdvertEntity.getApplyStatus())) {
					result.error500("审批通过的商家才能设置热门");
				} else {
					mallShopAdvertMapper.updateByIdAndHot(mallShopAdvert.getAdvertId(), mallShopAdvert.getHot());
					result.success("成功");
				}
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
	public Result<MallShopAdvert> delete(@RequestParam(name="id",required=true) String id) {
		Result<MallShopAdvert> result = new Result<MallShopAdvert>();
		MallShopAdvert mallShopAdvert = mallShopAdvertService.getById(id);
		if(mallShopAdvert==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mallShopAdvertService.removeById(id);
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
	public Result<MallShopAdvert> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MallShopAdvert> result = new Result<MallShopAdvert>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.mallShopAdvertService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<MallShopAdvert> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MallShopAdvert> result = new Result<MallShopAdvert>();
		MallShopAdvert mallShopAdvert = mallShopAdvertService.getById(id);
		if(mallShopAdvert==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(mallShopAdvert);
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
      QueryWrapper<MallShopAdvert> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MallShopAdvert mallShopAdvert = JSON.parseObject(deString, MallShopAdvert.class);
              queryWrapper = QueryGenerator.initQueryWrapper(mallShopAdvert, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MallShopAdvert> pageList = mallShopAdvertService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "商家表列表");
      mv.addObject(NormalExcelConstants.CLASS, MallShopAdvert.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商家表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<MallShopAdvert> listMallShopAdverts = ExcelImportUtil.importExcel(file.getInputStream(), MallShopAdvert.class, params);
              for (MallShopAdvert mallShopAdvertExcel : listMallShopAdverts) {
                  mallShopAdvertService.save(mallShopAdvertExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listMallShopAdverts.size());
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

	 @GetMapping(value = "/searchOperate")
	 public String searchOperate(String id) {
		 return mallShopAdvertService.searchOperate(id);
	 }


	 /**
	  * 查询自己的商家信息
	  * @param customerId
	  * @return
	  */
	 @GetMapping(value = "/searchStore")
	 public String searchStore(String customerId) {
		 return mallShopAdvertService.searchStore(customerId);
	 }


	@PutMapping(value = "/edit1")
	public String edit1(@RequestBody MallShopAdvert mallShopAdvert) {
	 	if (null == mallShopAdvert.getCustomerId() || mallShopAdvert.getCustomerId().isEmpty()) {
	 		return "重新登录";
		}
	 	MallShopAdvert mallShopAdvert1 = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("advert_id", mallShopAdvert.getAdvertId()));
	 	mallShopAdvert1.setAdvertImage(mallShopAdvert.getAdvertImage());
	 	mallShopAdvert1.setAdvertName(mallShopAdvert.getAdvertName());
	 	mallShopAdvert1.setPerson(mallShopAdvert.getPerson());
	 	mallShopAdvert1.setAdvertPhone(mallShopAdvert.getAdvertPhone());
	 	mallShopAdvert1.setShopLicence(mallShopAdvert.getShopLicence());
	 	mallShopAdvert1.setAdvertContext(mallShopAdvert.getAdvertContext());
	 	mallShopAdvert1.setCreateTime(new Date());
	 	mallShopAdvert1.setApplyStatus("1");
	 	mallShopAdvertMapper.update(mallShopAdvert1, new QueryWrapper<MallShopAdvert>().eq("advert_id", mallShopAdvert.getAdvertId()));
	 	MallUser mallUser = mallUserMapper.selectOne(new QueryWrapper<MallUser>().eq("customer_id", mallShopAdvert.getCustomerId()));
	 	mallUser.setState("1");
	 	mallUserMapper.updateById(mallUser);
	 	return "成功";
	}
}
