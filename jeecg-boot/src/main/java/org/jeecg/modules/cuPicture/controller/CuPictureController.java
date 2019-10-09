package org.jeecg.modules.cuPicture.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.OSSClient;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.GuidUtils;
import org.jeecg.common.util.MD5Util1;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.cuPicture.entity.CuPicture;
import org.jeecg.modules.cuPicture.service.ICuPictureService;

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
 * @Description: 轮播图背景图表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@RestController
@RequestMapping("/cuPicture/cuPicture")
@Slf4j
public class CuPictureController {
	@Autowired
	private ICuPictureService cuPictureService;
	
	/**
	  * 分页列表查询
	 * @param cuPicture
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<CuPicture>> queryPageList(CuPicture cuPicture,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<CuPicture>> result = new Result<IPage<CuPicture>>();
		QueryWrapper<CuPicture> queryWrapper = QueryGenerator.initQueryWrapper(cuPicture, req.getParameterMap());
		Page<CuPicture> page = new Page<CuPicture>(pageNo, pageSize);
		IPage<CuPicture> pageList = cuPictureService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param cuPicture
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<CuPicture> add(@RequestBody CuPicture cuPicture) {
		Result<CuPicture> result = new Result<CuPicture>();
		try {
			cuPictureService.save(cuPicture);
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
	 * @param cuPicture
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<CuPicture> edit(@RequestBody CuPicture cuPicture) {
		Result<CuPicture> result = new Result<CuPicture>();
		CuPicture cuPictureEntity = cuPictureService.getById(cuPicture.getId());
		if(cuPictureEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuPictureService.updateById(cuPicture);
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
	public Result<CuPicture> delete(@RequestParam(name="id",required=true) String id) {
		Result<CuPicture> result = new Result<CuPicture>();
		CuPicture cuPicture = cuPictureService.getById(id);
		if(cuPicture==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = cuPictureService.removeById(id);
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
	public Result<CuPicture> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<CuPicture> result = new Result<CuPicture>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.cuPictureService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<CuPicture> queryById(@RequestParam(name="id",required=true) String id) {
		Result<CuPicture> result = new Result<CuPicture>();
		CuPicture cuPicture = cuPictureService.getById(id);
		if(cuPicture==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(cuPicture);
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
      QueryWrapper<CuPicture> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              CuPicture cuPicture = JSON.parseObject(deString, CuPicture.class);
              queryWrapper = QueryGenerator.initQueryWrapper(cuPicture, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<CuPicture> pageList = cuPictureService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "轮播图背景图表列表");
      mv.addObject(NormalExcelConstants.CLASS, CuPicture.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("轮播图背景图表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<CuPicture> listCuPictures = ExcelImportUtil.importExcel(file.getInputStream(), CuPicture.class, params);
              for (CuPicture cuPictureExcel : listCuPictures) {
                  cuPictureService.save(cuPictureExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listCuPictures.size());
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
  @PostMapping(value = "/uploadImage")
  public Result<List<String>> uploadImage(MultipartFile file) {
		 Result<List<String>> response = new Result<>();
		 String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
		 String accessKeyId = "LTAIPy06f50b9tO7";
		 String accessKeySecret = "33jqyaySmZ5v2Tfm0fpotQR9WswlsL";
// 创建OSSClient实例。
		 OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		 try {
			 if (null != file) {
				 List<String> images = new ArrayList<>();
				 if (!file.isEmpty()) {
					 String path = "voucher/";
					 String fileName = file.getOriginalFilename();
					 path += GuidUtils.generateSimpleGuid() + MD5Util1.random(6) + fileName.substring(fileName.lastIndexOf("."));
					 //this.ftpFileStoreService.write(path, file.getInputStream());
					 ossClient.putObject("mylove2019", path	, file.getInputStream());

					 String url = "https://mylove2019.oss-cn-beijing.aliyuncs.com/"+path;
					 images.add(url);
				 }
				 ossClient.shutdown();
				 response.setResult(images);
				 response.setSuccess(true);
				 return response;
			 }
		 } catch (Exception e) {
			 response.error500(e.getMessage());
		 }
		 response.error500("上传失败");
		 return response;
	 }
}
