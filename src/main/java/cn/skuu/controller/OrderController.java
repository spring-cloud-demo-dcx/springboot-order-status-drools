package cn.skuu.controller;

import cn.skuu.entity.Order;
import cn.skuu.entity.User;
import cn.skuu.enums.CallStatusEnum;
import cn.skuu.enums.StatusEnum;
import cn.skuu.pojo.vo.ReturnVO;
import cn.skuu.pojo.vo.UserCallStatusVo;
import cn.skuu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dcx
 * @since 2022-08-28
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private KieBase kieBase;

    @Operation(summary = "订单修改")
    @GetMapping("/change")
    public ReturnVO<UserCallStatusVo> getUserIno(@RequestParam @Valid @NotBlank String orderId) {
        Order order = new Order();
        //开始会话
        KieSession kieSession = kieBase.newKieSession();
        //规则返回
        int res = 0;
        try {
            //设置返回
            kieSession.setGlobal("res",res);
            //设置入参
            kieSession.insert(order);
            //触发规则
            kieSession.fireAllRules();
        } finally {
            kieSession.dispose();
        }
        return ReturnVO.ok();
    }

}
