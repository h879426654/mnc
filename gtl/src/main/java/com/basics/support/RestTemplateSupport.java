package com.basics.support;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.basics.common.DataItemResponse;

/**
 * RestTemplateSupport.
 * 
 * @author yuwenfeng
 *
 */
public class RestTemplateSupport {

 /** The mapper. */
 protected final ObjectMapper mapper = new ObjectMapper();
 /** The client. */
 private RestTemplate client;

 /** The base url. */
 private String baseUrl;

 /**
  * Instantiates a new rest template support.
  */
 public RestTemplateSupport() {
  super();
 }

 /**
  * Gets the base url.
  *
  * @return the base url
  */
 public String getBaseUrl() {
  return baseUrl;
 }

 /**
  * Sets the base url.
  *
  * @param baseUrl
  *         the new base url
  */
 public void setBaseUrl(String baseUrl) {
  this.baseUrl = baseUrl;
 }

 /**
  * Gets the client.
  *
  * @return the client
  */
 public RestTemplate getClient() {
  if (this.client == null) {
   this.client = this.onBuildRestTemplate();
  }
  return client;
 }

 /**
  * Sets the client.
  *
  * @param client
  *         the client
  */
 public void setClient(RestTemplate client) {
  this.client = client;
 }

 /**
  * On build rest template.
  *
  * @return the rest template
  */
 @SuppressWarnings("rawtypes")
 public RestTemplate onBuildRestTemplate() {
  RestTemplate client = new RestTemplate();
  client.setRequestFactory(new SimpleClientHttpRequestFactory() {

   protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
    super.prepareConnection(connection, httpMethod);
    connection.setInstanceFollowRedirects(false);
   }
  });
  client.setErrorHandler(new ResponseErrorHandler() {
   // Pass errors through in response entity for status code analysis
   public boolean hasError(ClientHttpResponse response) throws IOException {
    return false;
   }

   public void handleError(ClientHttpResponse response) throws IOException {
   }
  });
  DefaultUriTemplateHandler uriUriTemplateHandler = new DefaultUriTemplateHandler();
  uriUriTemplateHandler.setStrictEncoding(true);
  client.setUriTemplateHandler(uriUriTemplateHandler);
  for (HttpMessageConverter messageConverter : client.getMessageConverters()) {
   if (messageConverter instanceof StringHttpMessageConverter) {
    StringHttpMessageConverter shmc = (StringHttpMessageConverter) messageConverter;
    shmc.setDefaultCharset(Charset.forName("UTF-8"));
   }
  }
  return client;
 }

 /**
  * Gets the collection type.
  *
  * @param collectionClass
  *         the collection class
  * @param elementClasses
  *         the element classes
  * @return the collection type
  */
 @SuppressWarnings("deprecation")
 public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
  return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
 }

 /**
  * Data item.
  *
  * @param <T>
  *         the generic type
  * @param path
  *         the path
  * @param urlVariables
  *         the url variables
  * @param itemClass
  *         the item class
  * @return the data item response< t>
  */
 public <T> DataItemResponse<T> getItem(String path, Map<String, String> urlVariables, Class<T> itemClass) {
  DataItemResponse<T> result = new DataItemResponse<T>();
  try {
   String url = this.getUrl(path);
   String json = this.getClient().getForObject(url, String.class, urlVariables);
   LogUtils.performance.info("url:{} urlVariables:{} json:{}", url, urlVariables, json);
   T item = FastjsonUtils.object(json, itemClass);
   result.setItem(item);
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
 }

 /**
  * Gets the url.
  *
  * @param path
  *         the path
  * @return the url
  */
 public String getUrl(String path) {
  StringBuilder sb = new StringBuilder();
  sb.append(this.getBaseUrl());
  sb.append(path);
  return sb.toString();
 }

 /**
  * Gets the items.
  *
  * @param <T>
  *         the generic type
  * @param path
  *         the path
  * @param urlVariables
  *         the url variables
  * @param itemClass
  *         the item class
  * @return the items
  */
 @SuppressWarnings("unchecked")
 public <T> DataItemResponse<List<T>> getItems(String path, Map<String, String> urlVariables, Class<T> itemClass) {
  DataItemResponse<List<T>> result = new DataItemResponse<List<T>>();
  try {
   String url = this.getUrl(path);
   String json = this.getClient().getForObject(url, String.class, urlVariables);
   LogUtils.performance.info("url:{} urlVariables:{} json:{}", url, urlVariables, json);
   JavaType javaType = getCollectionType(ArrayList.class, itemClass);
   Object object = mapper.readValue(json, javaType);
   result.setItem((List<T>) object);
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
 }

}