package com.basics.or.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.mall.controller.response.MallOrderProductDetailsResponse;
import com.basics.mall.dao.MallProductKindCombinationDao;
import com.basics.mall.dao.MallProductKindDetailDao;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.vo.KindVo;
import com.basics.or.dao.OrOrderDetailsDao;
import com.basics.or.entity.OrOrder;
import com.basics.or.service.OrOrderService;
import com.basics.support.FastjsonUtils;
import com.basics.support.GenericMybatisService;
import com.basics.support.LogUtils;
import com.basics.support.PaginationSupport;
import com.basics.support.ServletUtils;
import com.basics.support.dlshouwen.Pager;

@Service
@Transactional
public class OrOrderMybatisService extends GenericMybatisService<OrOrder> implements OrOrderService {

	@Autowired
	private OrOrderDetailsDao mallOrderDetailsdao;
	@Autowired
	private MallProductKindCombinationDao mallProductKindCombinationDao;
	@Autowired
	private MallProductKindDetailDao mallProductKindDetailDao;
	/**
	 * 商品列表
	 */
	@Override
	public void selectProductListByOrderId(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		Pager pager = new Pager();
		try {
			pager = FastjsonUtils.object(gridPager, Pager.class);
			int pageNum = pager.getNowPage();
			int pageSize = pager.getPageSize();
			PaginationSupport ps = mallOrderDetailsdao.queryPaginationExtend("queryOrderDetailsList", "countProductListByOrderId", pager.getParameters(), pageNum, pageSize);
			List<MallOrderProductDetailsResponse> list = ps.getItems();
			for (MallOrderProductDetailsResponse data : list) {
				MallProductKindCombination combination = mallProductKindCombinationDao.get(data.getCombinationId());
				List<KindVo> kindVos = mallProductKindDetailDao.listAllKinInDetailId(combination.getCombination().split("/"));
				StringBuilder sb = new StringBuilder();
				if (CollectionUtils.isNotEmpty(kindVos)) {
					for (int i = 0; i < kindVos.size(); i++) {
						if (i > 0 && i == kindVos.size() - 1) {
							sb.append("|  " + kindVos.get(i).getKindName() + ":" + kindVos.get(i).getKindValue());
						} else {
							sb.append(kindVos.get(i).getKindName() + ":" + kindVos.get(i).getKindValue());
						}
					}
					data.setCombinationContext(sb.toString());
				}
			}
			pager.setRecordCount(ps.getTotalCount());
			pager.setPageCount(ps.getPageCount());
			pager.setNowPage(ps.getCurrentPageNo());
			pager.setStartRecord(ps.getStartOfPage());
			pager.setExhibitDatas(ps.getItems());
			pager.setIsSuccess(true);
		} catch (Throwable e) {
			LogUtils.performance.error("swgrid:gridPager:{} exception:{}", gridPager, ExceptionUtils.getFullStackTrace(e));
			pager.setIsSuccess(false);
		}
		ServletUtils.renderJsonAsText(response, pager);
		
	}

 }
