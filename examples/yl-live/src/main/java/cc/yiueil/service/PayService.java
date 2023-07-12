package cc.yiueil.service;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.OrderEntity;
import cc.yiueil.entity.UserEntity;
import cc.yiueil.repository.OrderRepository;
import com.alipay.api.domain.AlipayTradePagePayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PayService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    OrderRepository orderRepository;

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

    @Transactional
    public void tradeOrderSettle(String out_trade_no) {
        OrderEntity orderEntity = orderRepository.findOrderEntityBySerialNumber(out_trade_no);
        orderEntity.setState(OrderEntity.SU_PAY);
        orderEntity.setPayTime(LocalDateTime.now());
        Long createUserId = orderEntity.getCreateUserId();
        baseDao.findById(UserEntity.class, createUserId).ifPresent(userEntity -> {
            String balance = userEntity.getExtendProperty1();
            BigDecimal bigDecimal = new BigDecimal(balance);
            BigDecimal addBalance = bigDecimal.add(new BigDecimal(orderEntity.getPayLimit()));
            userEntity.setExtendProperty1(addBalance.toString());
            baseDao.save(userEntity);
        });
    }

    @Transactional
    public List<OrderEntity> listOrder(Long userId) {
        return orderRepository.findOrderEntityByCreateUserId(userId);
    }

    @Transactional
    public void tradeRefund(OrderEntity orderEntity) {
        orderEntity.setState(OrderEntity.REFUND);
        baseDao.save(orderEntity);
        baseDao.findById(UserEntity.class, orderEntity.getCreateUserId()).ifPresent(userEntity -> {
            String extendProperty1 = userEntity.getExtendProperty1();
            BigDecimal oldBalance = new BigDecimal(extendProperty1);
            userEntity.setExtendProperty1(oldBalance.subtract(new BigDecimal(orderEntity.getPayLimit())).toString());
            baseDao.save(userEntity);
        });
    }
}