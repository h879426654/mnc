package com.basics.app.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.basics.app.entity.AppCode;
@Controller
@RequestMapping("/backend/app/appCode/")
public class AppCodeBackendController extends BaseBackendController<AppCode> {

}
