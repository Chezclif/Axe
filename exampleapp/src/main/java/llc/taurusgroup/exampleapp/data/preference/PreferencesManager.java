package llc.taurusgroup.exampleapp.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Admin on 26.11.2014.
 */
public class PreferencesManager {
    public static final String ACCOUNT = "Account";

    public static void saveData(Context context, String Key, String Value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, Value);
        editor.apply();
    }

    public static String loadData(Context context, String Key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key, "");
    }

    public static void saveIntData(Context context, String Key, int Value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Key, Value);
        editor.apply();
    }

    public static int loadIntData(Context context, String Key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key, -1);
    }

    public static void saveLongData(Context context, String Key, long Value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(Key, Value);
        editor.apply();
    }

    public static long loadLongData(Context context, String Key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getLong(Key, -1);
    }

    public static void saveBooleanData(Context context, String Key, boolean Value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key, Value);
        editor.apply();
    }

    public static void saveObjectData(Context context, String Key, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson gson = gb.create();
        String json = gson.toJson(object);
        editor.putString(Key, json);
        editor.apply();
    }

    public static Object loadObjectData(Context context, String Key, Class aClass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        sharedPreferences.getString(Key, "");
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson gson = gb.create();
        String json = sharedPreferences.getString(Key, "");
        if(json.equals("")){
            return null;
        }else {
            return gson.fromJson(json, aClass);
        }
    }

    public static boolean loadBooleanData(Context context, String Key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Key, false);
    }

    public static void clearPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
