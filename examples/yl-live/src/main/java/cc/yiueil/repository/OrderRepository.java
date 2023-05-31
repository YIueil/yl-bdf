package cc.yiueil.repository;

import cc.yiueil.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findOrderEntityBySerialNumber(String serialNumber);

    List<OrderEntity> findOrderEntityByCreateUserId(Long createUserId);
}