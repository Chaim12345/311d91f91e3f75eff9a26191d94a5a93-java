package androidx.core.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import org.apache.commons.cli.HelpFormatter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FontRequestWorker {

    /* renamed from: a  reason: collision with root package name */
    static final LruCache f2567a = new LruCache(16);
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.a("fonts-androidx", 10, ModuleDescriptor.MODULE_VERSION);

    /* renamed from: b  reason: collision with root package name */
    static final Object f2568b = new Object();
    @GuardedBy("LOCK")

    /* renamed from: c  reason: collision with root package name */
    static final SimpleArrayMap f2569c = new SimpleArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class TypefaceResult {

        /* renamed from: a  reason: collision with root package name */
        final Typeface f2580a;

        /* renamed from: b  reason: collision with root package name */
        final int f2581b;

        TypefaceResult(int i2) {
            this.f2580a = null;
            this.f2581b = i2;
        }

        @SuppressLint({"WrongConstant"})
        TypefaceResult(@NonNull Typeface typeface) {
            this.f2580a = typeface;
            this.f2581b = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @SuppressLint({"WrongConstant"})
        public boolean a() {
            return this.f2581b == 0;
        }
    }

    private FontRequestWorker() {
    }

    @NonNull
    static TypefaceResult a(@NonNull String str, @NonNull Context context, @NonNull FontRequest fontRequest, int i2) {
        LruCache lruCache = f2567a;
        Typeface typeface = (Typeface) lruCache.get(str);
        if (typeface != null) {
            return new TypefaceResult(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult a2 = FontProvider.a(context, fontRequest, null);
            int fontFamilyResultStatus = getFontFamilyResultStatus(a2);
            if (fontFamilyResultStatus != 0) {
                return new TypefaceResult(fontFamilyResultStatus);
            }
            Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, a2.getFonts(), i2);
            if (createFromFontInfo != null) {
                lruCache.put(str, createFromFontInfo);
                return new TypefaceResult(createFromFontInfo);
            }
            return new TypefaceResult(-3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface b(@NonNull final Context context, @NonNull final FontRequest fontRequest, final int i2, @Nullable Executor executor, @NonNull final CallbackWithHandler callbackWithHandler) {
        final String createCacheId = createCacheId(fontRequest, i2);
        Typeface typeface = (Typeface) f2567a.get(createCacheId);
        if (typeface != null) {
            callbackWithHandler.a(new TypefaceResult(typeface));
            return typeface;
        }
        Consumer<TypefaceResult> consumer = new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.2
            @Override // androidx.core.util.Consumer
            public void accept(TypefaceResult typefaceResult) {
                CallbackWithHandler.this.a(typefaceResult);
            }
        };
        synchronized (f2568b) {
            SimpleArrayMap simpleArrayMap = f2569c;
            ArrayList arrayList = (ArrayList) simpleArrayMap.get(createCacheId);
            if (arrayList != null) {
                arrayList.add(consumer);
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(consumer);
            simpleArrayMap.put(createCacheId, arrayList2);
            Callable<TypefaceResult> callable = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public TypefaceResult call() {
                    return FontRequestWorker.a(createCacheId, context, fontRequest, i2);
                }
            };
            if (executor == null) {
                executor = DEFAULT_EXECUTOR_SERVICE;
            }
            RequestExecutor.c(executor, callable, new Consumer<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.4
                @Override // androidx.core.util.Consumer
                public void accept(TypefaceResult typefaceResult) {
                    synchronized (FontRequestWorker.f2568b) {
                        SimpleArrayMap simpleArrayMap2 = FontRequestWorker.f2569c;
                        ArrayList arrayList3 = (ArrayList) simpleArrayMap2.get(createCacheId);
                        if (arrayList3 == null) {
                            return;
                        }
                        simpleArrayMap2.remove(createCacheId);
                        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                            ((Consumer) arrayList3.get(i3)).accept(typefaceResult);
                        }
                    }
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface c(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull CallbackWithHandler callbackWithHandler, final int i2, int i3) {
        final String createCacheId = createCacheId(fontRequest, i2);
        Typeface typeface = (Typeface) f2567a.get(createCacheId);
        if (typeface != null) {
            callbackWithHandler.a(new TypefaceResult(typeface));
            return typeface;
        } else if (i3 == -1) {
            TypefaceResult a2 = a(createCacheId, context, fontRequest, i2);
            callbackWithHandler.a(a2);
            return a2.f2580a;
        } else {
            try {
                TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.d(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontRequestWorker.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    public TypefaceResult call() {
                        return FontRequestWorker.a(createCacheId, context, fontRequest, i2);
                    }
                }, i3);
                callbackWithHandler.a(typefaceResult);
                return typefaceResult.f2580a;
            } catch (InterruptedException unused) {
                callbackWithHandler.a(new TypefaceResult(-3));
                return null;
            }
        }
    }

    private static String createCacheId(@NonNull FontRequest fontRequest, int i2) {
        return fontRequest.a() + HelpFormatter.DEFAULT_OPT_PREFIX + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d() {
        f2567a.evictAll();
    }

    @SuppressLint({"WrongConstant"})
    private static int getFontFamilyResultStatus(@NonNull FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i2 = 1;
        if (fontFamilyResult.getStatusCode() != 0) {
            return fontFamilyResult.getStatusCode() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
        if (fonts != null && fonts.length != 0) {
            i2 = 0;
            for (FontsContractCompat.FontInfo fontInfo : fonts) {
                int resultCode = fontInfo.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    }
                    return resultCode;
                }
            }
        }
        return i2;
    }
}
