package com.basics.support;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * AbstractClientSupport.
 * 
 * @author yuwenfeng
 */
public abstract class AbstractClientSupport {

 /** The base url. */
 protected String baseUrl;

 /** The client. */
 protected Client client;

 /**
  * Gets the client.
  *
  * @return the client
  */
 public Client getClient() {
  if (this.client == null) {
   this.client = this.onBuildClient();
  }
  return client;
 }

 /**
  * Sets the client.
  *
  * @param client
  *         the client
  */
 public void setClient(Client client) {
  this.client = client;
 }

 /**
  * On build client.
  *
  * @return the client
  */
 public abstract Client onBuildClient();

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
  *         the base url
  */
 public void setBaseUrl(String baseUrl) {
  this.baseUrl = baseUrl;
 }

 /**
  * Post json entity.
  *
  * @param path
  *         the path
  * @param entity
  *         the entity
  * @return the response
  */
 public Response postJsonEntity(String path, Object entity) {
  WebTarget target = this.getClient().target(this.getBaseUrl());
  Entity<?> entityWrapper = Entity.json(entity);
  Response response = target.path(path).request().post(entityWrapper);
  return response;
 }

 /**
  * Close response.
  *
  * @param response
  *         the response
  */
 public void closeResponse(Response response) {
  if (response != null) {
   response.close();
  }
 }
}
