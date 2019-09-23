package com.basics.app.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.basics.app.entity.AppLog;
@Controller
@RequestMapping("/backend/app/appLog/")
public class AppLogBackendController extends BaseBackendController<AppLog> {

}
