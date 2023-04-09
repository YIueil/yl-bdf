package cc.yiueil.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "t_order")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    public static final String UN_PAY = "未支付";
    public static final String SU_PAY = "已支付";
    public static final String EXPIRE_PAY = "超期未支付";
    public static final String REFUND = "已退款";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_order")
    @SequenceGenerator(name = "g_order", sequenceName = "s_order", allocationSize = 1)
    private Long id;

    private String payLimit;

    private LocalDateTime createTime;

    private LocalDateTime payTime;

    private String state;

    private String serialNumber;

    private Long createUserId;
}
