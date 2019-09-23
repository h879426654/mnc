package com.basics.cu.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.cu.entity.CuCustomerAddress;

@Controller
@RequestMapping("/backend/cu/cuCustomerAddress/")
public class CuCustomerAddressBackendController extends BaseBackendController<CuCustomerAddress> {

}
