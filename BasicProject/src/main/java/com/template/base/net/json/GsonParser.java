package com.template.base.net.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsonParser implements IJsonParser {
    private Gson mGson;

    private GsonParser() {
        mGson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
    }

    public static GsonParser getInstance() {
        return InnerHolder.sInstance;
    }

    private static class InnerHolder {
        private static final GsonParser sInstance = new GsonParser();
    }

    @Override
    public <T> T parseJsonObject(String json, Class<T> classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    @Override
    public <T> T parseJsonObject(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

    @Override
    public <T> List<T> parseJsonArray(String json, Class<T> classOfT) {
        List<T> lst = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(mGson.fromJson(elem, classOfT));
        }
        return lst;
    }

    @Override
    public <T> List<T> parseJsonArray(String json, Type typeOfT) {
        List<T> lst = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(mGson.<T>fromJson(elem, typeOfT));
        }
        return lst;
    }

    @Override
    public String toJson(Object o) {
        return mGson.toJson(o);
    }

}