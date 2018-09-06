package com.smag.androidlearning.helper;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConverterHelper {

    // Converter for ListCours
    @TypeConverter
    public String fromCoursList(List<Cours> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Cours>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    // Converter for ListCours
    @TypeConverter
    public List<Cours> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Cours>>() {
        }.getType();
        List<Cours> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    // Converter for ListExercices
    @TypeConverter
    public String fromExerciceList(List<Exercice> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Exercice>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    // Converter for ListExercices
    @TypeConverter
    public List<Exercice> toOptionValuesListExercice(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Exercice>>() {
        }.getType();
        List<Exercice> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    // Converter for Date
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    // Converter for Date
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
