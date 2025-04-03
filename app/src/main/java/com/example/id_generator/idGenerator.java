package com.example.id_generator;

import android.content.Context;
import android.content.SharedPreferences;

public class idGenerator {

    public static String generateUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int lastId = prefs.getInt("last_id", 0);
        int newId = lastId + 1;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("last_id", newId);
        editor.apply();

        return String.format("USR%03d", newId);
    }
}
