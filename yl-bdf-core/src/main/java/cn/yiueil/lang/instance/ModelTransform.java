package cn.yiueil.lang.instance;

/**
 * Author:YIueil
 * Date:2022/8/23 20:51
 * Description: 作为 Model Transform 类 具备两点功能：
 * 1、转换为 entity 的方法
 * 2、使用 entity 来构造实例的能力
 */
public interface ModelTransform<DTO, Entity> {
    Entity dto2Entity(DTO dto);

    DTO entity2Dto(Entity t);
}
