package com.basics.gty.controller.backend;

import com.basics.gty.entity.GtyWallet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend/gty/wallet/")
public class GtyWalletBackendController extends BaseBackendController<GtyWallet> {
}
