package com.basics.cu.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.cu.entity.CuCustomerMessage;

@Controller
@RequestMapping("/backend/cu/cuCustomerMessage/")
public class CuCustomerMessageBackendController extends BaseBackendController<CuCustomerMessage> {

}
