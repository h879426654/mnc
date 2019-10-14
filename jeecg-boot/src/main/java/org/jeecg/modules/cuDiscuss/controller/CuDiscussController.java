package org.jeecg.modules.cuDiscuss.controller;

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
import org.jeecg.modules.cuDiscuss.entity.CuDiscuss;
import org.jeecg.modules.cuDiscuss.service.ICuDiscussService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import org.jeecg.modules.mallShopAdvert.mapper.MallShopAdvertMapper;
import org.jeecg.modules.mallShopAdvert.service.impl.MallShopAdvertServiceImpl;
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
 * @Description: 评论
 * @author： jeecg-boot
 * @date：   2019-10-13
 * @version： V1.0
 */
@RestController
@RequestMapping("/cuDiscuss/cuDiscuss")
@Slf4j
public class CuDiscussController {
	@Autowired
	private ICuDiscussService cuDiscussService;
	@Autowired
	private MallShopAdvertMapper mallShopAdvertMapper;
	@Autowired
	private CuCustomerInfoMapper cuCustomerInfoMapper;
	/**
	  * 分页列表查询
	 * @param cuDiscuss
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CuDiscuss>> queryPageList(CuDiscuss cuDiscuss,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CuDiscuss>> result = new Result<IPage<CuDiscuss>>();
		QueryWrapper<CuDiscuss> queryWrapper = new QueryWrapper<>();
		if (null != cuDiscuss.getShopName()) {
			if (!cuDiscuss.getShopName().isEmpty()) {
				List<MallShopAdvert> list = mallShopAdvertMapper.selectList(new QueryWrapper<MallShopAdvert>().eq("apply_Status",2).eq("flag_del", "0").like("ADVERT_NAME", cuDiscuss.getShopName()));
				Set<String> set = new HashSet<String>();
				for (MallShopAdvert mallShopAdvert:list) {
					set.add(mallShopAdvert.getAdvertId());
				}
				if (null != set) {
					queryWrapper.in("shop_id", set);
				}
			}
		}
		if (null != cuDiscuss.getUserName()) {
			if (!cuDiscuss.getUserName().isEmpty()) {
				List<CuCustomerInfo> list = cuCustomerInfoMapper.selectList(new QueryWrapper<CuCustomerInfo>().like("customer_name", cuDiscuss.getUserName()));
				Set<String> set = new HashSet<String>();
				for (CuCustomerInfo cuCustomerInfo:list) {
					set.add(cuCustomerInfo.getCustomerId());
				}
				if (null != set) {
					queryWrapper.in("customer_id", set);
				}
			}
		}
		Page<CuDiscuss> page = new Page<CuDiscuss>(pageNo, pageSize);
		IPage<CuDiscuss> pageList = cuDiscussService.page(page, queryWrapper);
		List<CuDiscuss> cuDiscusses = pageList.getRecords();
		for (CuDiscuss cuDiscuss1:cuDiscusses) {
			CuCustomerInfo cuCustomerInfo = cuCustomerInfoMapper.selectOne(new QueryWrapper<CuCustomerInfo>().eq("customer_id", cuDiscuss1.getCustomerId()));
			cuDiscuss1.setUserName(cuCustomerInfo.getCustomerName());
			MallShopAdvert mallShopAdvert = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("advert_id", cuDiscuss1.getShopId()));
			cuDiscuss1.setShopName(mallShopAdvert.getAdvertName());
		}
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param cuDiscuss
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CuDiscuss> add(@RequestBody CuDiscuss cuDiscuss) {
		Result<CuDiscuss> result = new Result<CuDiscuss>();
		try {
			cuDiscussService.save(cuDiscuss);
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
	 * @param cuDiscuss
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CuDiscuss> edit(@RequestBody CuDiscuss cuDiscuss) {
		Result<CuDiscuss> result = new Result<CuDiscuss>();
		CuDiscuss cuDiscussEntity = cuDiscussService.getById(cuDiscuss.getId());
		if(cuDiscussEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuDiscussService.updateById(cuDiscuss);
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
	public Result<CuDiscuss> delete(@RequestParam(name="id",required=true) String id) {
		Result<CuDiscuss> result = new Result<CuDiscuss>();
		CuDiscuss cuDiscuss = cuDiscussService.getById(id);
		if(cuDiscuss==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuDiscussService.removeById(id);
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
	public Result<CuDiscuss> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CuDiscuss> result = new Result<CuDiscuss>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.cuDiscussService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CuDiscuss> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CuDiscuss> result = new Result<CuDiscuss>();
		CuDiscuss cuDiscuss = cuDiscussService.getById(id);
		if(cuDiscuss==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(cuDiscuss);
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
      QueryWrapper<CuDiscuss> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CuDiscuss cuDiscuss = JSON.parseObject(deString, CuDiscuss.class);
              queryWrapper = QueryGenerator.initQueryWrapper(cuDiscuss, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CuDiscuss> pageList = cuDiscussService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "评论列表");
      mv.addObject(NormalExcelConstants.CLASS, CuDiscuss.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("评论列表数据", "导出人:Jeecg", "导出信息"));
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
              List<CuDiscuss> listCuDiscusss = ExcelImportUtil.importExcel(file.getInputStream(), CuDiscuss.class, params);
              for (CuDiscuss cuDiscussExcel : listCuDiscusss) {
                  cuDiscussService.save(cuDiscussExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listCuDiscusss.size());
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
