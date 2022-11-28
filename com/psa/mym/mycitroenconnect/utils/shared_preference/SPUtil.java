package com.psa.mym.mycitroenconnect.utils.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.psa.mym.mycitroenconnect.App;
import java.security.GeneralSecurityException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SPUtil {
    @NotNull
    public static final SPUtil INSTANCE = new SPUtil();
    @NotNull
    private static String LOGIN_MODE = "login_mode";

    private SPUtil() {
    }

    public final void clearALLPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        App.Companion.getSharedPref().edit().clear().apply();
    }

    @NotNull
    public final String getString(@NotNull Context c2, @NotNull String key) {
        Intrinsics.checkNotNullParameter(c2, "c");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            String string = App.Companion.getSharedPref().getString(key, "");
            Intrinsics.checkNotNull(string);
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    public final void registerChangeListener(@NotNull Context context, @NonNull @NotNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        App.Companion.getSharedPref().registerOnSharedPreferenceChangeListener(listener);
    }

    public final void removeFromSharedPreferences(@NotNull Context context, @NotNull String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        App.Companion.getSharedPref().edit().remove(key).apply();
    }

    public final void setSession(@NotNull Context c2, boolean z) {
        Intrinsics.checkNotNullParameter(c2, "c");
        SharedPreferences.Editor edit = App.Companion.getSharedPref().edit();
        edit.putBoolean(LOGIN_MODE, z);
        edit.apply();
    }

    public final void setString(@NotNull Context c2, @NotNull String key, @NotNull String value) {
        Intrinsics.checkNotNullParameter(c2, "c");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            SharedPreferences.Editor edit = App.Companion.getSharedPref().edit();
            edit.putString(key, value);
            edit.apply();
        } catch (GeneralSecurityException e2) {
            e2.printStackTrace();
        }
    }

    public final void unRegisterChangeListener(@NotNull Context context, @NonNull @NotNull SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        App.Companion.getSharedPref().unregisterOnSharedPreferenceChangeListener(listener);
    }
}
