package cn.yiueil.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author:YIueil
 * Date:2022/8/21 23:02
 * Description: SpaceDTO
 */
@Getter
@Setter
public class SpaceDTO implements Serializable {
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    @NotNull
    private String name;

    private String iconUrl;
}
