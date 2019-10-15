package org.jeecg.modules.mallUser.controller;

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
import org.jeecg.modules.mallUser.entity.MallUser;
import org.jeecg.modules.mallUser.mapper.MallUserMapper;
import org.jeecg.modules.mallUser.service.IMallUserService;

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
 * @Description: 商家总后台账号密码
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@RestController
@RequestMapping("/mallUser/mallUser")
@Slf4j
public class MallUserController {
	@Autowired
	private IMallUserService mallUserService;
	@Autowired
	private MallUserMapper mallUserMapper;
	/**
	  * 分页列表查询
	 * @param mallUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<MallUser>> queryPageList(MallUser mallUser,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MallUser>> result = new Result<IPage<MallUser>>();
		QueryWrapper<MallUser> queryWrapper = QueryGenerator.initQueryWrapper(mallUser, req.getParameterMap());
		Page<MallUser> page = new Page<MallUser>(pageNo, pageSize);
		IPage<MallUser> pageList = mallUserService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param mallUser
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MallUser> add(@RequestBody MallUser mallUser) {
		Result<MallUser> result = new Result<MallUser>();
		try {
			mallUserService.save(mallUser);
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
	 * @param mallUser
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MallUser> edit(@RequestBody MallUser mallUser) {
		Result<MallUser> result = new Result<MallUser>();
		MallUser mallUserEntity = mallUserService.getById(mallUser.getId());
		if(mallUserEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mallUserService.updateById(mallUser);
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
	public Result<MallUser> delete(@RequestParam(name="id",required=true) String id) {
		Result<MallUser> result = new Result<MallUser>();
		MallUser mallUser = mallUserService.getById(id);
		if(mallUser==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mallUserService.removeById(id);
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
	public Result<MallUser> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MallUser> result = new Result<MallUser>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.mallUserService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<MallUser> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MallUser> result = new Result<MallUser>();
		MallUser mallUser = mallUserService.getById(id);
		if(mallUser==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(mallUser);
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
      QueryWrapper<MallUser> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MallUser mallUser = JSON.parseObject(deString, MallUser.class);
              queryWrapper = QueryGenerator.initQueryWrapper(mallUser, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MallUser> pageList = mallUserService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "商家总后台账号密码列表");
      mv.addObject(NormalExcelConstants.CLASS, MallUser.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商家总后台账号密码列表数据", "导出人:Jeecg", "导出信息"));
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
              List<MallUser> listMallUsers = ExcelImportUtil.importExcel(file.getInputStream(), MallUser.class, params);
              for (MallUser mallUserExcel : listMallUsers) {
                  mallUserService.save(mallUserExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listMallUsers.size());
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
	  * 登录
	  * @param mallUser
	  * @return
	  */
  @PostMapping(value = "/login")
  public String login(@RequestBody MallUser mallUser) {
	 if (null != mallUser.getUserName() && null != mallUser.getPassWord()) {
		 MallUser mallUser1 = mallUserMapper.selectOne(new QueryWrapper<MallUser>().eq("user_name", mallUser.getUserName()).eq("pass_word", mallUser.getPassWord()).eq("state",0));
		 if (null != mallUser1) {
			return mallUser1.getCustomerId();
		 }
	 }
	 return "0";
  }
}
