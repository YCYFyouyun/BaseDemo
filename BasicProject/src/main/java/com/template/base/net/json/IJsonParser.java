package com.template.base.net.json;

import java.lang.reflect.Type;
import java.util.List;

public interface IJsonParser {
    <T> T parseJsonObject(String json, Class<T> classOfT);

    <T> T parseJsonObject(String json, Type typeOfT);

    <T> List<T> parseJsonArray(String json, Class<T> classOfT);

    <T> List<T> parseJsonArray(String json, Type typeOfT);

    String toJson(Object o);
}
