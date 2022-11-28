package com.facebook.stetho.inspector.domstorage;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes.dex */
public class SharedPreferencesHelper {
    private static final String PREFS_SUFFIX = ".xml";

    private SharedPreferencesHelper() {
    }

    public static List<String> getSharedPreferenceTags(Context context) {
        ArrayList arrayList = new ArrayList();
        File file = new File(context.getApplicationInfo().dataDir + "/shared_prefs");
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                String name = file2.getName();
                if (name.endsWith(PREFS_SUFFIX)) {
                    arrayList.add(name.substring(0, name.length() - 4));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Boolean parseBoolean(String str) {
        if ("1".equals(str) || "true".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        }
        if ("0".equals(str) || "false".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Expected boolean, got " + str);
    }

    @Nullable
    public static Object valueFromString(String str, Object obj) {
        if (obj instanceof Integer) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(Long.parseLong(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf(Float.parseFloat(str));
        }
        if (obj instanceof Boolean) {
            return parseBoolean(str);
        }
        if (obj instanceof String) {
            return str;
        }
        if (!(obj instanceof Set)) {
            throw new IllegalArgumentException("Unsupported type: " + obj.getClass().getName());
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            HashSet hashSet = new HashSet(length);
            for (int i2 = 0; i2 < length; i2++) {
                hashSet.add(jSONArray.getString(i2));
            }
            return hashSet;
        } catch (JSONException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static String valueToString(Object obj) {
        if (obj != null) {
            if (obj instanceof Set) {
                JSONArray jSONArray = new JSONArray();
                for (String str : (Set) obj) {
                    jSONArray.put(str);
                }
                return jSONArray.toString();
            }
            return obj.toString();
        }
        return null;
    }
}
