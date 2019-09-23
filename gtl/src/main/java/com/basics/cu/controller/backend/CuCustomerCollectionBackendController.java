package com.basics.cu.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.cu.entity.CuCustomerCollection;

@Controller
@RequestMapping("/backend/cu/cuCustomerCollection/")
public class CuCustomerCollectionBackendController extends BaseBackendController<CuCustomerCollection> {

}
