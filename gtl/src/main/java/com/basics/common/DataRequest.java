package com.basics.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 数据请求.
 * 
 * @author yuwenfeng
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRequest implements java.io.Serializable {

 private static final long serialVersionUID = 1L;

}
