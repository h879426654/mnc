package org.jeecg.modules.mallGoods.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.mallGoods.entity.MallGood2s;
import org.jeecg.modules.mallGoods.entity.MallGoods;
import org.jeecg.modules.mallGoods.mapper.MallGoodsMapper;
import org.jeecg.modules.mallGoods.service.IMallGoodsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import org.jeecg.modules.mallShopAdvert.mapper.MallShopAdvertMapper;
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
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@RestController
@RequestMapping("/mallGoods/mallGoods")
@Slf4j
public class MallGoodsController {
	@Autowired
	private IMallGoodsService mallGoodsService;
	@Autowired
	private MallGoodsMapper mallGoodsMapper;
	@Autowired
	private MallShopAdvertMapper mallShopAdvertMapper;
	/**
	  * 分页列表查询
	 * @param shopName
	 * @param goods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<MallGood2s>> queryPageList(MallGoods mallGoods, String shopName, String goods,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MallGood2s>> result = new Result<IPage<MallGood2s>>();
		QueryWrapper<MallGoods> queryWrapper = QueryGenerator.initQueryWrapper(mallGoods, req.getParameterMap());
		Page<MallGoods> page = new Page<MallGoods>(pageNo, pageSize);
		if (null != shopName) {
			if (!shopName.isEmpty()) {
				List<MallShopAdvert> list = mallShopAdvertMapper.selectList(new QueryWrapper<MallShopAdvert>().eq("apply_Status",2).eq("flag_del", "0").like("ADVERT_NAME", shopName));
				Set<String> set = new HashSet<String>();
				for (MallShopAdvert mallShopAdvert:list) {
					set.add(mallShopAdvert.getAdvertId());
				}
				if (null != set) {
					queryWrapper.in("advert_id", set);
				}
			}
		}
		if (null != goods && !goods.isEmpty()) {
			queryWrapper.like("goods_Name", goods);
		}
		IPage<MallGoods> pageList = mallGoodsService.page(page, queryWrapper.orderByDesc("create_time"));
		IPage<MallGood2s> pageList2 = new Page<>();
		List<MallGoods> mallGoodses = pageList.getRecords();
		List<MallGood2s> mallGood2sList = new ArrayList<>();
		for (MallGoods goods1: mallGoodses) {
			MallShopAdvert mallShopAdvert = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("advert_id", goods1.getAdvertId()));
			MallGood2s mallGood2s = new MallGood2s();
			mallGood2s.setId(goods1.getId());
			mallGood2s.setGoodsText(goods1.getGoodsText());
			mallGood2s.setShopName(mallShopAdvert.getAdvertName());
			mallGood2s.setGoodsName(goods1.getGoodsName());
			mallGood2s.setMoney(goods1.getMoney());
			mallGood2s.setImage(goods1.getImage());
			mallGood2s.setStatus(goods1.getStatus());
			mallGood2sList.add(mallGood2s);
		}
		pageList2.setRecords(mallGood2sList);
		pageList2.setTotal(pageList.getTotal());
		pageList2.setCurrent(pageList.getCurrent());
		pageList2.setPages(pageList.getPages());
		pageList2.setSize(pageList.getSize());
		result.setSuccess(true);
		result.setResult(pageList2);
		return result;
	}

	/**
	  *   添加
	 * @param mallGoods
	 * @return
	 */
	@GetMapping(value = "/add")
	public Result<MallGoods> add(MallGoods mallGoods) {
		Result<MallGoods> result = new Result<MallGoods>();
		if (null == mallGoods.getCustomerId() || mallGoods.getCustomerId().isEmpty()) {
			result.error500("清重新登录");
			return result;
		}
		mallGoods.setId(UUID.randomUUID().toString().replace("-",""));
		MallShopAdvert mallShopAdvert = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("customer_id", mallGoods.getCustomerId()).eq("apply_status", "2").eq("Flag_del","0"));
		if (mallShopAdvert != null) {
			mallGoods.setAdvertId(mallShopAdvert.getAdvertId());
		} else {
			result.error500("您的还没有商家或者已被下架");
			return result;
		}
		try {
			mallGoodsService.save(mallGoods);
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
	 * @param mallGoods
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MallGoods> edit(@RequestBody MallGoods mallGoods) {
		Result<MallGoods> result = new Result<MallGoods>();
		MallGoods mallGoodsEntity = mallGoodsMapper.selectOne(new QueryWrapper<MallGoods>().eq("id", mallGoods.getId()).eq("del_flag","0"));
		if(mallGoodsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			if (null != mallGoods.getStatus()) {
				if ("3".equals(mallGoods.getStatus())) {
					mallGoodsEntity.setStatus(mallGoods.getStatus());
					mallGoodsEntity.setState("0");
					mallGoodsMapper.updateById(mallGoodsEntity);
					result.success("修改成功!");
				} else {
					if (!"0".equals(mallGoodsEntity.getStatus())) {
						result.error500("审批过得数据不能再次审批");
					} else {
						mallGoodsEntity.setStatus(mallGoods.getStatus());
						mallGoodsMapper.updateById(mallGoodsEntity);
						result.success("修改成功!");
					}
				}
			} else if (null != mallGoods.getState()) {
				mallGoodsEntity.setState(mallGoods.getState());
				mallGoodsMapper.updateById(mallGoodsEntity);
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
	public Result<MallGoods> delete(@RequestParam(name="id",required=true) String id) {
		Result<MallGoods> result = new Result<MallGoods>();
		MallGoods mallGoods = mallGoodsService.getById(id);
		if(mallGoods==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mallGoodsService.removeById(id);
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
	public Result<MallGoods> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MallGoods> result = new Result<MallGoods>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.mallGoodsService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<MallGoods> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MallGoods> result = new Result<MallGoods>();
		MallGoods mallGoods = mallGoodsService.getById(id);
		if(mallGoods==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(mallGoods);
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
      QueryWrapper<MallGoods> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MallGoods mallGoods = JSON.parseObject(deString, MallGoods.class);
              queryWrapper = QueryGenerator.initQueryWrapper(mallGoods, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MallGoods> pageList = mallGoodsService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "商品列表");
      mv.addObject(NormalExcelConstants.CLASS, MallGoods.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品列表数据", "导出人:Jeecg", "导出信息"));
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
              List<MallGoods> listMallGoodss = ExcelImportUtil.importExcel(file.getInputStream(), MallGoods.class, params);
              for (MallGoods mallGoodsExcel : listMallGoodss) {
                  mallGoodsService.save(mallGoodsExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listMallGoodss.size());
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
  @GetMapping(value = "/searchGoods")
  public String searchGoods(String customerId, String name, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
							@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

  	if (null == customerId || customerId.isEmpty()) {
  		return "0";
	}
  	return mallGoodsService.searchGoods(customerId,name,pageNo,pageSize);
  }
}
