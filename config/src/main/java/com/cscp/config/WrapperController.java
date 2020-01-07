package com.cscp.config;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
/**
 * @author chen kezhuo
 * @discription
 * @date 2019/10/7 - 13:12
 */
@RestController
public class WrapperController {
    @PostMapping("/actuator/bus-refresh2")
    @ResponseBody
    public Object busRefresh2(HttpServletRequest request, @RequestBody(required = false) String s) {
        return new ModelAndView("/actuator/bus-refresh");
    }
}
