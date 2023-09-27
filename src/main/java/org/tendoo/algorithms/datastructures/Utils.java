package org.tendoo.algorithms.datastructures;

import org.tendoo.algorithms.api.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public final class Utils {

    public static <T extends Comparable<T>> T min(T lhs, T rhs) {
        int comparison = lhs.compareTo(rhs);
        if (comparison <= 0)
            return lhs;
        return rhs;
    }

    public static <E> void print(Collection<E> collection) {
        for (E e : collection)
            System.out.printf("%s ", e);
    }

    public static <E> List<E> readTokens(String line, Converter<String, E> converter, int count) {
        List<E> elems = new ArrayList<>(count);
        StringTokenizer tokens = new StringTokenizer(line);
        while (tokens.hasMoreTokens())
            elems.add(converter.convert(tokens.nextToken()));
        return elems;
    }

    public static List<Integer> readIntTokens(String line, int count) {
        return readTokens(line, Integer::parseInt, count);
    }
}
