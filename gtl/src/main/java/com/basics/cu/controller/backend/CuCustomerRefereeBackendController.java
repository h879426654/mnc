package com.basics.cu.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.service.CuCustomerRefereeService;
import com.basics.support.ItemResultSupport;
import com.basics.support.ServletUtils;

@Controller
@RequestMapping("/backend/cu/cuCustomerReferee/")
public class CuCustomerRefereeBackendController extends BaseBackendController<CuCustomerReferee> {

    @Autowired
    private CuCustomerRefereeService cuCustomerRefereeService;

    @RequestMapping(value = "/ztree")
    public void ztree(HttpServletResponse response, String customerPhone) {
        ItemResultSupport<List<JSONObject>> result = new ItemResultSupport<>();
        try {
            result=cuCustomerRefereeService.doZTree(customerPhone);
        } catch (Throwable e) {
            result.onException(e);
        }
        ServletUtils.renderJsonAsText(response, result);
    }
}
