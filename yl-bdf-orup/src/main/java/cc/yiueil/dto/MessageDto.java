package cc.yiueil.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * MessageDto 消息数据传输对象
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/4/5 18:39
 */
@Getter
@Setter
@ToString
@ApiModel(value = "消息数据传输对象")
public class MessageDto {
    private Long id;
    private String guid;
    @ApiModelProperty(value = "发送人id")
    private Long senderId;
    @ApiModelProperty(value = "接收人id")
    private Long receiverId;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "消息创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
