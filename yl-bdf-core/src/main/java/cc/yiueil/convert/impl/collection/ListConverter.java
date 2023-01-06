package cc.yiueil.convert.impl.collection;

import cc.yiueil.convert.CollectionConverter;
import cc.yiueil.convert.ConverterHolder;
import cc.yiueil.lang.reflect.TypeReference;

import java.util.*;

public class ListConverter<T> extends CollectionConverter<List<T>> {
    private final TypeReference<T> typeReference;

    public ListConverter(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<T> convertInternal(Object obj) {
        ArrayList<T> result = new ArrayList<>();
        if (obj instanceof Collection) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object item : collection) {
                T resultItem = ConverterHolder.getConverter(typeReference).convert(item, null);
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
