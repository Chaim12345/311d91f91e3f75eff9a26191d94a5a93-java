package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes.dex */
public final class MemorySizeCalculator {
    private static final int LOW_MEMORY_BYTE_ARRAY_POOL_DIVISOR = 2;
    private static final String TAG = "MemorySizeCalculator";
    private final int arrayPoolSize;
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: i  reason: collision with root package name */
        static final int f4767i;

        /* renamed from: a  reason: collision with root package name */
        final Context f4768a;

        /* renamed from: b  reason: collision with root package name */
        ActivityManager f4769b;

        /* renamed from: c  reason: collision with root package name */
        ScreenDimensions f4770c;

        /* renamed from: e  reason: collision with root package name */
        float f4772e;

        /* renamed from: d  reason: collision with root package name */
        float f4771d = 2.0f;

        /* renamed from: f  reason: collision with root package name */
        float f4773f = 0.4f;

        /* renamed from: g  reason: collision with root package name */
        float f4774g = 0.33f;

        /* renamed from: h  reason: collision with root package name */
        int f4775h = 4194304;

        static {
            f4767i = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public Builder(Context context) {
            this.f4772e = f4767i;
            this.f4768a = context;
            this.f4769b = (ActivityManager) context.getSystemService("activity");
            this.f4770c = new DisplayMetricsScreenDimensions(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !MemorySizeCalculator.a(this.f4769b)) {
                return;
            }
            this.f4772e = 0.0f;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }

        public Builder setArrayPoolSize(int i2) {
            this.f4775h = i2;
            return this;
        }

        public Builder setBitmapPoolScreens(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.f4772e = f2;
            return this;
        }

        public Builder setLowMemoryMaxSizeMultiplier(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.f4774g = f2;
            return this;
        }

        public Builder setMaxSizeMultiplier(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Size multiplier must be between 0 and 1");
            this.f4773f = f2;
            return this;
        }

        public Builder setMemoryCacheScreens(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.f4771d = f2;
            return this;
        }
    }

    /* loaded from: classes.dex */
    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics) {
            this.displayMetrics = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }
    }

    /* loaded from: classes.dex */
    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    MemorySizeCalculator(Builder builder) {
        this.context = builder.f4768a;
        int i2 = a(builder.f4769b) ? builder.f4775h / 2 : builder.f4775h;
        this.arrayPoolSize = i2;
        int maxSize = getMaxSize(builder.f4769b, builder.f4773f, builder.f4774g);
        float widthPixels = builder.f4770c.getWidthPixels() * builder.f4770c.getHeightPixels() * 4;
        int round = Math.round(builder.f4772e * widthPixels);
        int round2 = Math.round(widthPixels * builder.f4771d);
        int i3 = maxSize - i2;
        int i4 = round2 + round;
        if (i4 <= i3) {
            this.memoryCacheSize = round2;
            this.bitmapPoolSize = round;
        } else {
            float f2 = i3;
            float f3 = builder.f4772e;
            float f4 = builder.f4771d;
            float f5 = f2 / (f3 + f4);
            this.memoryCacheSize = Math.round(f4 * f5);
            this.bitmapPoolSize = Math.round(f5 * builder.f4772e);
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(toMb(this.memoryCacheSize));
            sb.append(", pool size: ");
            sb.append(toMb(this.bitmapPoolSize));
            sb.append(", byte array size: ");
            sb.append(toMb(i2));
            sb.append(", memory class limited? ");
            sb.append(i4 > maxSize);
            sb.append(", max size: ");
            sb.append(toMb(maxSize));
            sb.append(", memoryClass: ");
            sb.append(builder.f4769b.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(a(builder.f4769b));
        }
    }

    @TargetApi(19)
    static boolean a(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    private static int getMaxSize(ActivityManager activityManager, float f2, float f3) {
        boolean a2 = a(activityManager);
        float memoryClass = activityManager.getMemoryClass() * 1024 * 1024;
        if (a2) {
            f2 = f3;
        }
        return Math.round(memoryClass * f2);
    }

    private String toMb(int i2) {
        return Formatter.formatFileSize(this.context, i2);
    }

    public int getArrayPoolSizeInBytes() {
        return this.arrayPoolSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }
}
