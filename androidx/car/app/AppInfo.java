package androidx.car.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.versioning.CarAppApiLevels;
import java.util.Objects;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes.dex */
public final class AppInfo {
    private static final String LIBRARY_VERSION = "1.1.0-alpha01";
    public static final String MIN_API_LEVEL_METADATA_KEY = "androidx.car.app.minCarApiLevel";
    @Keep
    private final int mLatestCarAppApiLevel;
    @Nullable
    @Keep
    private final String mLibraryVersion;
    @Keep
    private final int mMinCarAppApiLevel;

    private AppInfo() {
        this.mMinCarAppApiLevel = 0;
        this.mLibraryVersion = null;
        this.mLatestCarAppApiLevel = 0;
    }

    @VisibleForTesting
    public AppInfo(int i2, int i3, @NonNull String str) {
        this.mMinCarAppApiLevel = i2;
        this.mLibraryVersion = str;
        this.mLatestCarAppApiLevel = i3;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static AppInfo create(@NonNull Context context) {
        int retrieveMinCarAppApiLevel = retrieveMinCarAppApiLevel(context);
        if (retrieveMinCarAppApiLevel < CarAppApiLevels.getOldest() || retrieveMinCarAppApiLevel > CarAppApiLevels.getLatest()) {
            throw new IllegalArgumentException("Min API level (androidx.car.app.minCarApiLevel=" + retrieveMinCarAppApiLevel + ") is out of range (" + CarAppApiLevels.getOldest() + HelpFormatter.DEFAULT_OPT_PREFIX + CarAppApiLevels.getLatest() + ")");
        }
        return new AppInfo(retrieveMinCarAppApiLevel, CarAppApiLevels.getLatest(), LIBRARY_VERSION);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    public static int retrieveMinCarAppApiLevel(@NonNull Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            int i2 = bundle != null ? bundle.getInt(MIN_API_LEVEL_METADATA_KEY, 0) : 0;
            if (i2 != 0) {
                return i2;
            }
            throw new IllegalArgumentException("Min API level not declared in manifest (androidx.car.app.minCarApiLevel)");
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalArgumentException("Unable to read min API level from manifest");
        }
    }

    public int getLatestCarAppApiLevel() {
        return this.mLatestCarAppApiLevel;
    }

    @NonNull
    public String getLibraryDisplayVersion() {
        String str = this.mLibraryVersion;
        Objects.requireNonNull(str);
        return str;
    }

    public int getMinCarAppApiLevel() {
        return this.mMinCarAppApiLevel;
    }

    public String toString() {
        return "Library version: [" + getLibraryDisplayVersion() + "] Min Car Api Level: [" + getMinCarAppApiLevel() + "] Latest Car App Api Level: [" + getLatestCarAppApiLevel() + "]";
    }
}
