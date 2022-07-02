package cn.yiueil.lang.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Type类型参考<br>
 * 通过构建一个类型参考子类，可以获取其泛型参数中的Type类型。例如：
 *
 * 定义为抽象类的原因：在继承了泛型类型的情况下，子类才可以获取父类的泛型类型
 *
 * <pre>
 * TypeReference&lt;List&lt;String&gt;&gt; list = new TypeReference&lt;List&lt;String&gt;&gt;() {};
 * Type t = tr.getType();
 * </pre>
 *
 * 此类无法应用于通配符泛型参数（wildcard parameters），比如：{@code Class<?>} 或者 {@code List? extends CharSequence>}
 *
 * @author YIueil
 *
 * @param <T> 需要自定义的参考类型
 */
public abstract class TypeReference<T> implements Type {

	/** 泛型参数 */
	private Type type;

	/**
	 * 通过类型直接构造TypeReference对象
	 * @param type 内部type泛型值
	 * @return 泛型类型保存实例
	 */
	@SuppressWarnings("all")
	public static TypeReference of(Type type) {
		TypeReference typeReference = new TypeReference() {};
		typeReference.type = type;
		return typeReference;
	}

	/**
	 * 构造
	 */
	public TypeReference() {
		this.type = getTypeArgument(getClass());
	}

	/**
	 * 获取到当前class的第一个泛型的类型
	 * @param clazz 类
	 * @return 泛型类型的值
	 */
	private Type getTypeArgument(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) t;
			Type[] types = pt.getActualTypeArguments(); // 可能有多个泛型类型, 但对于该类有且只有一个类型
			return types[0];
		}
		return Object.class;
	}

	/**
	 * 获取用户定义的泛型参数
	 *
	 * @return 泛型参数
	 */
	public Type getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return this.type.toString();
	}
}