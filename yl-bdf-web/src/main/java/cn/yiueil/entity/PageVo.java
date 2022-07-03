package cn.yiueil.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Author:YIueil
 * Date:2022/7/3 20:05
 * Description: 分页包装类
 */
@Getter
@Setter
public class PageVo {
    @NotNull
    @Min(value = 1, message = "页码必须是正整数")
    private int pageIndex; // 页码
    @Min(value = 1, message = "每页数量必须是正整数")
    private int pageSize; // 每页数量
    private int pageTotal; // 页面总数
    private int itemCounts; //元素总数

    private Object body; // 页面内容
}
