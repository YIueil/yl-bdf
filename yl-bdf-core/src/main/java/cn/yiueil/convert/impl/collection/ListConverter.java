package cn.yiueil.convert.impl.collection;

import cn.yiueil.convert.CollectionConverter;
import cn.yiueil.convert.ConverterHolder;
import cn.yiueil.lang.reflect.TypeReference;

import java.util.*;

public class ListConverter<T> extends CollectionConverter<List<T>> {
    private final TypeReference<T> typeReference;

    public ListConverter(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    protected List<T> convertInternal(Object obj) {
        ArrayList<T> result = new ArrayList<>();
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
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
