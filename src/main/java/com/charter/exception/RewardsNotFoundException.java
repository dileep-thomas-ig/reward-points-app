package com.charter.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class RewardsNotFoundException extends RuntimeException {
    public RewardsNotFoundException(Class clazz, String... searchParamsMap) {
        super(RewardsNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class,
                String.class, searchParamsMap)));
    }

    /**
     *
     * @param entity string
     * @param searchParams Map<String, String> searchParams
     * @return String
     */
    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    /**
     * Returns a Generic map of Key with type K and
     * value with type V.
     * example : toMap(String.class,String.class, "CustomerID:", new String("1"))
     * returns : {CustomerID:=1}
     */

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2)
                .map(index -> index * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
