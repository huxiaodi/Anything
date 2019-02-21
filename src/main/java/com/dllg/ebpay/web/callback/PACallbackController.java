package com.dllg.ebpay.web.callback;

import com.dllg.ebpay.service.callback.PACallbackService;
import com.dllg.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback/pa")
public class PACallbackController extends BaseController {

    @Autowired
    private PACallbackService paCallbackService;

    /**
     *  子账户交易，验密回调
     */
    @PostMapping("/trade/inspection")
    public void notifyUrl(@RequestParam("orderid") String orderid, @RequestParam("P2PCode") String P2PCode, @RequestParam("flag")String flag, @RequestParam("orig") String orig, @RequestParam("sign") String sign){
        paCallbackService.executeTrade(orderid,flag,sign);
    }

}
