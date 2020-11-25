package com.ssm.sys;

import com.domain.SysLog;
import com.github.pagehelper.PageInfo;
import com.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysController {
    @Autowired
    private ISysService sysService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "5")Integer size){
        ModelAndView mv = new ModelAndView();
        List<SysLog> list = sysService.findAll(page,size);
        PageInfo info = new PageInfo(list);
        mv.addObject("pageInfo",info);
        mv.setViewName("syslog-list");
        return mv;
    }
}
