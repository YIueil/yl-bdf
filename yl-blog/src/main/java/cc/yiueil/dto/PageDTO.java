package cc.yiueil.dto;

import cc.yiueil.entity.TagEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PageDTO implements Serializable {
    private Integer id;

    private String guid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer createUser;

    private Integer parentId;

    private String iconUrl;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private List<TagEntity> tags;
}
