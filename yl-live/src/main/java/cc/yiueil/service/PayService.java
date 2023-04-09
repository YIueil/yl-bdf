package cc.yiueil.service;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.OrderEntity;
import cc.yiueil.entity.UserEntity;
import com.alipay.api.domain.AlipayTradePagePayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PayService {
    @Autowired
    JpaBaseDao baseDao;

    @Transactional
    public void createOrder(AlipayTradePagePayModel model, UserEntity user) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSerialNumber(model.getOutTradeNo());
        orderEntity.setPayLimit(model.getTotalAmount());
        orderEntity.setCreateUserId(user.getId());
        orderEntity.setCreateTime(LocalDateTime.now());
        orderEntity.setState(OrderEntity.UN_PAY);
        baseDao.save(orderEntity);
    }
}
