package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;
import org.apache.commons.cli.HelpFormatter;
@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class TypefaceCompat {
    private static final LruCache<String, Typeface> sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {
        @Nullable
        private ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(@Nullable ResourcesCompat.FontCallback fontCallback) {
            this.mFontCallback = fontCallback;
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRequestFailed(int i2) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrievalFailed(i2);
            }
        }

        @Override // androidx.core.provider.FontsContractCompat.FontRequestCallback
        public void onTypefaceRetrieved(@NonNull Typeface typeface) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        sTypefaceCompatImpl = i2 >= 29 ? new TypefaceCompatApi29Impl() : i2 >= 28 ? new TypefaceCompatApi28Impl() : i2 >= 26 ? new TypefaceCompatApi26Impl() : (i2 < 24 || !TypefaceCompatApi24Impl.isUsable()) ? i2 >= 21 ? new TypefaceCompatApi21Impl() : new TypefaceCompatBaseImpl() : new TypefaceCompatApi24Impl();
        sTypefaceCache = new LruCache<>(16);
    }

    private TypefaceCompat() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @VisibleForTesting
    public static void clearCache() {
        sTypefaceCache.evictAll();
    }

    @NonNull
    public static Typeface create(@NonNull Context context, @Nullable Typeface typeface, int i2) {
        Typeface bestFontFromFamily;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (bestFontFromFamily = getBestFontFromFamily(context, typeface, i2)) == null) ? Typeface.create(typeface, i2) : bestFontFromFamily;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface createFromFontInfo(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i2);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface createFromResourcesFamilyXml(@NonNull Context context, @NonNull FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, @NonNull Resources resources, int i2, int i3, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Typeface createFromFontFamilyFilesResourceEntry;
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry;
            Typeface systemFontFamily = getSystemFontFamily(providerResourceEntry.getSystemFontFamilyName());
            if (systemFontFamily != null) {
                if (fontCallback != null) {
                    fontCallback.callbackSuccessAsync(systemFontFamily, handler);
                }
                return systemFontFamily;
            }
            boolean z2 = !z ? fontCallback != null : providerResourceEntry.getFetchStrategy() != 0;
            int timeout = z ? providerResourceEntry.getTimeout() : -1;
            createFromFontFamilyFilesResourceEntry = FontsContractCompat.requestFont(context, providerResourceEntry.getRequest(), i3, z2, timeout, ResourcesCompat.FontCallback.getHandler(handler), new ResourcesCallbackAdapter(fontCallback));
        } else {
            createFromFontFamilyFilesResourceEntry = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i3);
            if (fontCallback != null) {
                if (createFromFontFamilyFilesResourceEntry != null) {
                    fontCallback.callbackSuccessAsync(createFromFontFamilyFilesResourceEntry, handler);
                } else {
                    fontCallback.callbackFailAsync(-3, handler);
                }
            }
        }
        if (createFromFontFamilyFilesResourceEntry != null) {
            sTypefaceCache.put(createResourceUid(resources, i2, i3), createFromFontFamilyFilesResourceEntry);
        }
        return createFromFontFamilyFilesResourceEntry;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface createFromResourcesFontFile(@NonNull Context context, @NonNull Resources resources, int i2, String str, int i3) {
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i2, str, i3);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i2, i3), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    private static String createResourceUid(Resources resources, int i2, int i3) {
        return resources.getResourcePackageName(i2) + HelpFormatter.DEFAULT_OPT_PREFIX + i2 + HelpFormatter.DEFAULT_OPT_PREFIX + i3;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface findFromCache(@NonNull Resources resources, int i2, int i3) {
        return sTypefaceCache.get(createResourceUid(resources, i2, i3));
    }

    @Nullable
    private static Typeface getBestFontFromFamily(Context context, Typeface typeface, int i2) {
        TypefaceCompatBaseImpl typefaceCompatBaseImpl = sTypefaceCompatImpl;
        FontResourcesParserCompat.FontFamilyFilesResourceEntry c2 = typefaceCompatBaseImpl.c(typeface);
        if (c2 == null) {
            return null;
        }
        return typefaceCompatBaseImpl.createFromFontFamilyFilesResourceEntry(context, c2, context.getResources(), i2);
    }

    private static Typeface getSystemFontFamily(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Typeface create = Typeface.create(str, 0);
        Typeface create2 = Typeface.create(Typeface.DEFAULT, 0);
        if (create == null || create.equals(create2)) {
            return null;
        }
        return create;
    }
}
