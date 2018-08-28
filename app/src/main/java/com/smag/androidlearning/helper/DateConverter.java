package com.smag.androidlearning.helper;

import android.arch.persistence.room.TypeConverter;
import android.location.Location;

import java.util.Date;
import java.util.Locale;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
