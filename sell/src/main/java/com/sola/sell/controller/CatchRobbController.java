package com.sola.sell.controller;


import com.sola.sell.repository.ProductInfoRepository;
import com.sola.sell.service.RedRunService;
import com.sola.sell.service.RedRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Map;

@RestController
@RequestMapping("/robbing")
@Slf4j
public class CatchRobbController implements Serializable {
    private static final long serialVersionUID = -8840060387892253259L;

   // public static int tmp=0;

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private RedRunService redRunService;

    @RequestMapping("/ebony")
    public ModelAndView ebony(@RequestParam("productId") String productId,
                                           Map<String ,Object> map){
       redRunService.redRobbingMobon(productId);
       String result=redRunService.searchRobbingProductInfoDetail(productId);
       map.put("tmp",result);
       return new ModelAndView("robbing/ebony",map);
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("productId") String productId,
                                Map<String ,Object> map
                               ){

        String result=redRunService.searchRobbingProductInfoDetail(productId);
        map.put("tmp",result);
        return new ModelAndView("robbing/ebony",map);
    }
}
