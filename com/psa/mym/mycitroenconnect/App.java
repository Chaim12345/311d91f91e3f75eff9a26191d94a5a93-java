package com.psa.mym.mycitroenconnect;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class App extends Application {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static App instance;
    public static FirebaseAnalytics mFirebaseAnalytics;
    public static SharedPreferences sharedPref;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final App getInstance() {
            App app = App.instance;
            if (app != null) {
                return app;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            return null;
        }

        @NotNull
        public final FirebaseAnalytics getMFirebaseAnalytics() {
            FirebaseAnalytics firebaseAnalytics = App.mFirebaseAnalytics;
            if (firebaseAnalytics != null) {
                return firebaseAnalytics;
            }
            Intrinsics.throwUninitializedPropertyAccessException("mFirebaseAnalytics");
            return null;
        }

        @NotNull
        public final SharedPreferences getSharedPref() {
            SharedPreferences sharedPreferences = App.sharedPref;
            if (sharedPreferences != null) {
                return sharedPreferences;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sharedPref");
            return null;
        }

        public final void setInstance(@NotNull App app) {
            Intrinsics.checkNotNullParameter(app, "<set-?>");
            App.instance = app;
        }

        public final void setMFirebaseAnalytics(@NotNull FirebaseAnalytics firebaseAnalytics) {
            Intrinsics.checkNotNullParameter(firebaseAnalytics, "<set-?>");
            App.mFirebaseAnalytics = firebaseAnalytics;
        }

        public final void setSharedPref(@NotNull SharedPreferences sharedPreferences) {
            Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
            App.sharedPref = sharedPreferences;
        }
    }

    private final void initStetho() {
    }

    private final void setupActivityListener() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.psa.mym.mycitroenconnect.App$setupActivityListener$1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                activity.getWindow().setFlags(8192, 8192);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NotNull Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NotNull Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NotNull Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NotNull Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NotNull Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }
        });
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(1);
        Companion companion = Companion;
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(this)");
        companion.setMFirebaseAnalytics(firebaseAnalytics);
        companion.setInstance(this);
        MasterKey build = new MasterKey.Builder(companion.getInstance()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(instance)\n      …GCM)\n            .build()");
        SharedPreferences create = EncryptedSharedPreferences.create(companion.getInstance(), AppConstants.SHARED_PREFERENCE, build, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        Intrinsics.checkNotNullExpressionValue(create, "create(\n            inst…heme.AES256_GCM\n        )");
        companion.setSharedPref(create);
        initStetho();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
    }
}
