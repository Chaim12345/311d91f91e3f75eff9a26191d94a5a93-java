package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
class Store {
    private static final String SCOPE_ALL = "*";
    private static final String STORE_KEY_TOKEN = "|T|";

    /* renamed from: a  reason: collision with root package name */
    final SharedPreferences f10066a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Token {
        private static final String KEY_APP_VERSION = "appVersion";
        private static final String KEY_TIMESTAMP = "timestamp";
        private static final String KEY_TOKEN = "token";
        private static final long REFRESH_PERIOD_MILLIS = TimeUnit.DAYS.toMillis(7);

        /* renamed from: a  reason: collision with root package name */
        final String f10067a;

        /* renamed from: b  reason: collision with root package name */
        final String f10068b;

        /* renamed from: c  reason: collision with root package name */
        final long f10069c;

        private Token(String str, String str2, long j2) {
            this.f10067a = str;
            this.f10068b = str2;
            this.f10069c = j2;
        }

        static String a(String str, String str2, long j2) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(KEY_TOKEN, str);
                jSONObject.put(KEY_APP_VERSION, str2);
                jSONObject.put(KEY_TIMESTAMP, j2);
                return jSONObject.toString();
            } catch (JSONException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to encode token: ");
                sb.append(e2);
                return null;
            }
        }

        static Token c(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith("{")) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    return new Token(jSONObject.getString(KEY_TOKEN), jSONObject.getString(KEY_APP_VERSION), jSONObject.getLong(KEY_TIMESTAMP));
                } catch (JSONException e2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to parse token: ");
                    sb.append(e2);
                    return null;
                }
            }
            return new Token(str, null, 0L);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean b(String str) {
            return System.currentTimeMillis() > this.f10069c + REFRESH_PERIOD_MILLIS || !str.equals(this.f10068b);
        }
    }

    public Store(Context context) {
        this.f10066a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        checkForRestore(context, "com.google.android.gms.appid-no-backup");
    }

    private void checkForRestore(Context context, String str) {
        File file = new File(ContextCompat.getNoBackupFilesDir(context), str);
        if (file.exists()) {
            return;
        }
        try {
            if (!file.createNewFile() || isEmpty()) {
                return;
            }
            deleteAll();
        } catch (IOException e2) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error creating file in no backup dir: ");
                sb.append(e2.getMessage());
            }
        }
    }

    private String createTokenKey(String str, String str2) {
        return str + STORE_KEY_TOKEN + str2 + "|*";
    }

    public synchronized void deleteAll() {
        this.f10066a.edit().clear().commit();
    }

    public synchronized void deleteToken(String str, String str2) {
        String createTokenKey = createTokenKey(str, str2);
        SharedPreferences.Editor edit = this.f10066a.edit();
        edit.remove(createTokenKey);
        edit.commit();
    }

    public synchronized Token getToken(String str, String str2) {
        return Token.c(this.f10066a.getString(createTokenKey(str, str2), null));
    }

    public synchronized boolean isEmpty() {
        return this.f10066a.getAll().isEmpty();
    }

    public synchronized void saveToken(String str, String str2, String str3, String str4) {
        String a2 = Token.a(str3, str4, System.currentTimeMillis());
        if (a2 == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f10066a.edit();
        edit.putString(createTokenKey(str, str2), a2);
        edit.commit();
    }
}
