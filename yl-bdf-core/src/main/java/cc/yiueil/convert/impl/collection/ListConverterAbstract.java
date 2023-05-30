package cc.yiueil.convert.impl.collection;

import cc.yiueil.convert.AbstractCollectionConverter;
import cc.yiueil.convert.ConverterHolder;
import cc.yiueil.lang.reflect.AbstractTypeReference;

import java.util.*;

/**
 * ListConverter
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/30 22:49
 * @version 1.0
 */
public class ListConverterAbstract<T> extends AbstractCollectionConverter<List<T>> {
    private final AbstractTypeReference<T> abstractTypeReference;

    public ListConverterAbstract(AbstractTypeReference<T> abstractTypeReference) {
        this.abstractTypeReference = abstractTypeReference;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<T> convertInternal(Object obj) {
        ArrayList<T> result = new ArrayList<>();
        if (obj instanceof Collection) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object item : collection) {
                T resultItem = ConverterHolder.getConverter(abstractTypeReference).convert(item, null);
                if (resultItem != null) {
                    result.add(resultItem);
                }
            }
        } else if (obj instanceof CharSequence) {
            String string = obj.toString();
            return (List<T>) Arrays.asList(string.split(","));
        }
        return result;
    }
}
