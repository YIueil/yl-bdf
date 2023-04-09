package cc.yiueil.repository;

import cc.yiueil.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findOrderEntityBySerialNumber(String serialNumber);
}