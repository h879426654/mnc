package com.basics.mall.entity;
    import java.math.BigDecimal;
public class MallProductKindCombinationBase extends BaseBean{
    /**
    * 组合编号
    */
    private String id;

    /**
    * 商品编号
    */
    private String productId;

    /**
    * 维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开
    */
    private String combination;

    /**
    * 库存数量
    */
    private Integer combinationStockNum;

    /**
    * 已售数量
    */
    private Integer combinationSellNum;

    /**
    * 
    */
    private BigDecimal combinationPrice;

    /**
    * 规格图片
    */
    private String combinationImg;


    public String getId(){
     return this.id;
    }

    public void setId(String id){
     this.id=id;
    }
    public String getProductId(){
     return this.productId;
    }

    public void setProductId(String productId){
     this.productId=productId;
    }
    public String getCombination(){
     return this.combination;
    }

    public void setCombination(String combination){
     this.combination=combination;
    }
    public Integer getCombinationStockNum(){
     return this.combinationStockNum;
    }

    public void setCombinationStockNum(Integer combinationStockNum){
     this.combinationStockNum=combinationStockNum;
    }
    public Integer getCombinationSellNum(){
     return this.combinationSellNum;
    }

    public void setCombinationSellNum(Integer combinationSellNum){
     this.combinationSellNum=combinationSellNum;
    }
    public BigDecimal getCombinationPrice(){
     return this.combinationPrice;
    }

    public void setCombinationPrice(BigDecimal combinationPrice){
     this.combinationPrice=combinationPrice;
    }
    public String getCombinationImg(){
     return this.combinationImg;
    }

    public void setCombinationImg(String combinationImg){
     this.combinationImg=combinationImg;
    }
}