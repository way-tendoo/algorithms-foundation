package org.tendoo.algorithms.api;

@FunctionalInterface
public interface Converter<A, B> {
    B convert(A elem);
}