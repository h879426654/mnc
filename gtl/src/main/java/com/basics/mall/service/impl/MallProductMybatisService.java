package com.basics.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.basics.app.dao.AppImageDao;
import com.basics.app.entity.AppImage;
import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.cu.dao.CuCustomerInfoDao;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.mall.controller.request.AddProductRequest;
import com.basics.mall.dao.MallProductDao;
import com.basics.mall.dao.MallProductKindCombinationDao;
import com.basics.mall.dao.MallProductKindContrastDao;
import com.basics.mall.dao.MallProductKindDao;
import com.basics.mall.dao.MallProductKindDetailDao;
import com.basics.mall.dao.MallShopDao;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallProductKind;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.entity.MallProductKindContrast;
import com.basics.mall.entity.MallProductKindDetail;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallProductService;
import com.basics.mall.vo.CombinationVo;
import com.basics.mall.vo.KindDetailVo;
import com.basics.mall.vo.KindsVo;
import com.basics.mall.vo.ProductCombinationVo;
import com.basics.support.CommonSupport;
import com.basics.support.FastjsonUtils;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericMybatisService;
import com.basics.support.GuidUtils;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.dlshouwen.Pager;

@Service
@Transactional
public class MallProductMybatisService extends GenericMybatisService<MallProduct> implements MallProductService {

	@Autowired
	private MallProductKindDao mallProductKindDao;
	@Autowired
	private AppImageDao appImageDao;
	@Autowired
	private MallProductKindDetailDao mallProductKindDetailDao;
	@Autowired
	private MallProductKindContrastDao mallProductKindContrastDao;
	@Autowired
	private MallProductKindCombinationDao mallProductKindCombinationDao;
	@Autowired
	private MallProductDao mallProductDao;
	@Autowired
	private MallShopDao mallShopDao;
	@Autowired
	private CuCustomerInfoDao cuCustomerInfoDao;

	@Override
	public FormResultSupport doAddGoods2(AddProductRequest vo) {
		UserSupport userSupport = AppUserUtils.getCurrentUserSupport();
		FormResultSupport formResultSupport = new FormResultSupport();
		if (vo.getProductCost().doubleValue() < 0 || vo.getProductFreight().doubleValue() < 0 || vo.getProductPrice().doubleValue() < 0) {
			formResultSupport.onException("价格不能为负数！");
			return formResultSupport;
		}
		MallShop shop = mallShopDao.get(userSupport.getId());
		CommonSupport.checkNotNull(shop, "店铺不存在，无法添加商品");
		
		
		// 成本价不能大于售价
		/*if (vo.getProductCost().doubleValue() > vo.getProductPrice().doubleValue()) {
			formResultSupport.onException("成本价不能大于售价！");
			return formResultSupport;
		}*/
		if (StringUtils.isBlank(vo.getKinds())) {
			formResultSupport.onException("商品规格不能为空！");
			return formResultSupport;
		}
		List<KindsVo> kinds = JSON.parseArray(vo.getKinds(), KindsVo.class);
		if (CollectionUtils.isEmpty(kinds)) {
			formResultSupport.onException("商品规格不能为空！");
			return formResultSupport;
		}
		List<CombinationVo> combinations = JSON.parseArray(vo.getCombinations(), CombinationVo.class);
		if (CollectionUtils.isEmpty(combinations)) {
			formResultSupport.onException("商品规格不能为空！");
			return formResultSupport;
		}
		String productId = vo.getProductId();
		MallProduct product = new MallProduct();
		product.id(productId).shopId(shop.getId()).productName(vo.getProductName()).productImg(vo.getProductImg()).productPrice(vo.getProductPrice()).productFirstClassify(vo.getProductFirstClassify())
				.productSecondClassify(vo.getProductSecondClassify()).productCost(vo.getProductCost()).productFreight(vo.getProductFreight()).productContext(vo.getProductContext())
				.createTime(new Date()).createUser(userSupport.getId()).countryId(shop.getCountryId());
		// 商品总库存
		Integer totalStock = 0;
		// 保存主规格
		for (KindsVo kind : kinds) {
			MallProductKind mallProductKind = new MallProductKind();
			mallProductKind.setId(kind.getId());
			mallProductKind.setProductKindName(kind.getName());
			mallProductKind.setProductKindMosaicOrder(kind.getOrder());
			mallProductKindDao.save(mallProductKind);
			for (int i = 0; i < kind.getKindDetail().size(); i++) {
				MallProductKindDetail kindDetail = new MallProductKindDetail();
				KindDetailVo kindDetailVo = kind.getKindDetail().get(i);
				kindDetail.setId(kindDetailVo.getId());
				kindDetail.setDetailKindId(kind.getId());
				kindDetail.setDetailName(kindDetailVo.getValue());
				kindDetail.setDetailKindValue(kindDetailVo.getValue());
				kindDetail.setDetailDescription(i + "");
				mallProductKindDetailDao.save(kindDetail);
				// 保存商品规格信息
				MallProductKindContrast mallProductKindContrast = new MallProductKindContrast();
				mallProductKindContrast.setId(GuidUtils.generateSimpleGuid());
				mallProductKindContrast.setKindId(kind.getId());
				mallProductKindContrast.setKindDetailId(kindDetailVo.getId());
				mallProductKindContrast.setProductId(productId);
				mallProductKindContrastDao.save(mallProductKindContrast);
			}
		}
		// 保存商品combination
		for (CombinationVo combination : combinations) {
			MallProductKindCombination mallProductKindCombination = new MallProductKindCombination();
			mallProductKindCombination.setId(GuidUtils.generateSimpleGuid());
			mallProductKindCombination.setProductId(productId);
			mallProductKindCombination.setCombinationStockNum(combination.getCombinationStock());
			mallProductKindCombination.combinationImg(combination.getCombinationImg());
			mallProductKindCombination.setCombinationPrice(combination.getCombinationPrice());
			mallProductKindCombination.setCombination(combination.getCombinationStr());
			mallProductKindCombinationDao.save(mallProductKindCombination);
			totalStock += combination.getCombinationStock();
		}
		product.setProductPrice(combinations.get(0).getCombinationPrice());
		product.setProductStock(totalStock);
		product.setProductSale(0);
		this.dao.save(product);
		// 保存商品轮播图
		List<String> carouselImg = JSON.parseArray(vo.getCarouselImg(), String.class);
		if (CollectionUtils.isNotEmpty(carouselImg)) {
			AppImage appImage = null;
			for (String image : carouselImg) {
				appImage = new AppImage();
				appImage.setOwnerId(product.getId());
				appImage.setOwnerClass("MallProduct");
				appImage.setUrl(image);
				appImageDao.save(appImage);
			}
		}
		formResultSupport.onSuccess("添加成功！");
		return formResultSupport;
	}

	@Override
	public Pager getProductDetail(String gridPager) {
		Pager pager = FastjsonUtils.object(gridPager, Pager.class);
		int pageNum = pager.getNowPage();
		int pageSize = pager.getPageSize();
		List<ProductCombinationVo> data = new ArrayList<>();
		MallProduct mallProduct = mallProductDao.get(pager.getParameters().get("id").toString());
		CommonSupport.checkNotNull(mallProduct, "商品信息错误");
		// 设置规格库存
		PaginationSupport combinationsStocks = mallProductKindCombinationDao.queryPagination(new QueryFilterBuilder().put("productId", mallProduct.getId()).build().getParams(), pageNum, pageSize);
		if (CollectionUtils.isNotEmpty(combinationsStocks.getItems())) {
			ProductCombinationVo combinationVo;
			for (Object productKindCombination : combinationsStocks.getItems()) {
				MallProductKindCombination mallProductKindCombination = (MallProductKindCombination) productKindCombination;
				combinationVo = new ProductCombinationVo();
				combinationVo.setProuductId(mallProductKindCombination.getProductId());
				combinationVo.setCombinationId(mallProductKindCombination.getId());
				combinationVo.setStock(mallProductKindCombination.getCombinationStockNum());
				String[] combinations = mallProductKindCombination.getCombination().split("/");
				StringBuilder combinationDetail = new StringBuilder();
				for (String detailId : combinations) {
					MallProductKindDetail mallProductKindDetail = mallProductKindDetailDao.get(detailId);
					combinationDetail.append(mallProductKindDetail.getDetailName()).append("-");
				}
				combinationVo.setCombination(combinationDetail.substring(0, combinationDetail.length() - 1));
				combinationVo.setCombinationPrice(mallProductKindCombination.getCombinationPrice());
				combinationVo.setCombinationImg(mallProductKindCombination.getCombinationImg());
				combinationVo.setProductName(mallProduct.getProductName());
				data.add(combinationVo);
			}
		}
		pager.setRecordCount(combinationsStocks.getTotalCount());
		pager.setPageCount(combinationsStocks.getPageCount());
		pager.setNowPage(combinationsStocks.getCurrentPageNo());
		pager.setStartRecord(combinationsStocks.getStartOfPage());
		pager.setExhibitDatas(data);
		pager.setIsSuccess(true);
		return pager;
	}

	@Override
	public FormResultSupport doProductModify(ProductCombinationVo combinationVo) {
		FormResultSupport formResultSupport = new FormResultSupport();
		MallProductKindCombination kindCombination = mallProductKindCombinationDao.get(combinationVo.getCombinationId());
		CommonSupport.checkNotNull(kindCombination, "商品规格错误");
		MallProduct mallProduct = mallProductDao.get(kindCombination.getProductId());
		CommonSupport.checkNotNull(mallProduct, "商品错误");
		// 此规格价格
		kindCombination.setCombinationPrice(combinationVo.getCombinationPrice());
		// 此规格库存
		kindCombination.setCombinationStockNum(combinationVo.getStock());
		mallProductKindCombinationDao.save(kindCombination);
		// 商品库存
		Integer queryStock = mallProductKindCombinationDao.getExtend(kindCombination.getProductId(), "queryStock");
		// 商品名称
		mallProduct.setProductName(combinationVo.getProductName());
		mallProduct.setProductPrice(combinationVo.getCombinationPrice());
		mallProduct.setProductStock(queryStock);
		mallProductDao.save(mallProduct);
		formResultSupport.onSuccess("添加成功！");
		return formResultSupport;
	}
}
