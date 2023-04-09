package cc.yiueil.controller;

import cc.yiueil.entity.OrderEntity;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "order")
public class OrderController implements LoggedController{
    @Autowired
    PayService payService;

    @GetMapping(value = "list")
    public String listOrder(HttpServletRequest request) {
        UserEntity user = getUser(request);
        List<OrderEntity> orderEntities = payService.listOrder(user.getId());
        return success(orderEntities);
    }
}
