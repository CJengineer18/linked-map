package io.github.cjengineer18.linkedmap.util;

import java.util.Map;

import io.github.cjengineer18.linkedmap.LinkedMap;
import io.github.cjengineer18.linkedmap.interfaces.functional.IThrowableFunction;
import io.github.cjengineer18.linkedmap.interfaces.functional.IThrowableUnaryOperator;

public final class LinkedMapUtilities {

    private LinkedMapUtilities() {
        throw new UnsupportedOperationException();
    }

    /* ** Re-mapping functions ** */

    // T = Old Key
    // U = Old Value
    // V = New Key
    // W = New Value

    /* All different classes */

    public <T, U, V> LinkedMap<V, U> remapKeys(Map<T, U> map, IThrowableFunction<T, V> keyFunction) throws Exception {
        return remap(map, keyFunction, IThrowableUnaryOperator.identity());
    }

    public <T, U, W> LinkedMap<T, W> remapValues(Map<T, U> map, IThrowableFunction<U, W> valueFunction)
            throws Exception {
        return remap(map, IThrowableUnaryOperator.identity(), valueFunction);
    }

    public <T, U, V, W> LinkedMap<V, W> remap(Map<T, U> map, IThrowableFunction<T, V> keyFunction,
            IThrowableFunction<U, W> valueFunction) throws Exception {
        LinkedMap<T, U> oldCopy = new LinkedMap<T, U>(map);
        LinkedMap<V, W> newMap = new LinkedMap<V, W>();

        oldCopy.forEach((k, v, idx) -> {
            newMap.put(keyFunction.apply(k), valueFunction.apply(v));
        });

        return newMap;
    }

    /* Same class pair (Unary operation) */

    public <T, U> LinkedMap<T, U> remapKeys(Map<T, U> map, IThrowableUnaryOperator<T> keyFunction) throws Exception {
        return remap(map, keyFunction, IThrowableUnaryOperator.identity());
    }

    public <T, U> LinkedMap<T, U> remapValues(Map<T, U> map, IThrowableUnaryOperator<U> valueFunction)
            throws Exception {
        return remap(map, IThrowableUnaryOperator.identity(), valueFunction);
    }

    public <T, U> LinkedMap<T, U> remap(Map<T, U> map, IThrowableUnaryOperator<T> keyFunction,
            IThrowableUnaryOperator<U> valueFunction) throws Exception {
        return remap(map, (IThrowableFunction<T, T>) keyFunction, (IThrowableFunction<U, U>) valueFunction);
    }

}
