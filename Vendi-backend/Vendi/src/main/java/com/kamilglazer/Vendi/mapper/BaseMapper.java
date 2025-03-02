package com.kamilglazer.Vendi.mapper;

public class BaseMapper{
    public static <T> T returnNullIfNull(T object) {
        return object;
    }
}
