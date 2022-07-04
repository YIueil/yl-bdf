package cn.yiueil.lang.instance;

import java.io.Serializable;

/**
 * Author:YIueil
 * Date:2022/7/4 22:06
 * Description: 建造者模式 门面
 * @param <T> 创建对象类型
 */
public interface Builder<T> extends Serializable {
	/**
	 * 构建
	 *
	 * @return 被构建的对象
	 */
	T build();
}