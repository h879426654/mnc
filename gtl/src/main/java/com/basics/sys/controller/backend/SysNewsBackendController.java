package com.basics.sys.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.basics.sys.entity.SysNews;
@Controller
@RequestMapping("/backend/sys/sysNews/")
public class SysNewsBackendController extends BaseBackendController<SysNews> {

}
