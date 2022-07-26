package cn.yiueil.lang.instance;

import javax.persistence.Column;
import java.time.LocalDateTime;

public interface HasTime {
    void setCreateTime(LocalDateTime createTime);

    @Column(name = "create_time")
    LocalDateTime getCreateTime();

    void setModifyTime(LocalDateTime modifyTime);

    @Column(name = "modify_time")
    LocalDateTime getModifyTime();
}
