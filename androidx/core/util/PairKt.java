package androidx.core.util;

import android.annotation.SuppressLint;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010\u0003\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0087\n¢\u0006\u0004\b\u0003\u0010\u0004\u001a,\u0010\u0005\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0087\n¢\u0006\u0004\b\u0005\u0010\u0004\u001a1\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0086\b\u001a1\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H\u0086\b\u001a,\u0010\u0003\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0087\n¢\u0006\u0004\b\u0003\u0010\n\u001a,\u0010\u0005\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0087\n¢\u0006\u0004\b\u0005\u0010\n\u001a1\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0086\b\u001a1\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H\u0086\b¨\u0006\f"}, d2 = {"F", ExifInterface.LATITUDE_SOUTH, "Landroidx/core/util/Pair;", "component1", "(Landroidx/core/util/Pair;)Ljava/lang/Object;", "component2", "Lkotlin/Pair;", "toKotlinPair", "toAndroidXPair", "Landroid/util/Pair;", "(Landroid/util/Pair;)Ljava/lang/Object;", "toAndroidPair", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class PairKt {
    @SuppressLint({"UnknownNullness"})
    public static final <F, S> F component1(@NotNull android.util.Pair<F, S> component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return (F) component1.first;
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> F component1(@NotNull Pair<F, S> component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.first;
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> S component2(@NotNull android.util.Pair<F, S> component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return (S) component2.second;
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> S component2(@NotNull Pair<F, S> component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.second;
    }

    @NotNull
    public static final <F, S> android.util.Pair<F, S> toAndroidPair(@NotNull kotlin.Pair<? extends F, ? extends S> toAndroidPair) {
        Intrinsics.checkNotNullParameter(toAndroidPair, "$this$toAndroidPair");
        return new android.util.Pair<>(toAndroidPair.getFirst(), toAndroidPair.getSecond());
    }

    @NotNull
    public static final <F, S> Pair<F, S> toAndroidXPair(@NotNull kotlin.Pair<? extends F, ? extends S> toAndroidXPair) {
        Intrinsics.checkNotNullParameter(toAndroidXPair, "$this$toAndroidXPair");
        return new Pair<>(toAndroidXPair.getFirst(), toAndroidXPair.getSecond());
    }

    @NotNull
    public static final <F, S> kotlin.Pair<F, S> toKotlinPair(@NotNull android.util.Pair<F, S> toKotlinPair) {
        Intrinsics.checkNotNullParameter(toKotlinPair, "$this$toKotlinPair");
        return new kotlin.Pair<>(toKotlinPair.first, toKotlinPair.second);
    }

    @NotNull
    public static final <F, S> kotlin.Pair<F, S> toKotlinPair(@NotNull Pair<F, S> toKotlinPair) {
        Intrinsics.checkNotNullParameter(toKotlinPair, "$this$toKotlinPair");
        return new kotlin.Pair<>(toKotlinPair.first, toKotlinPair.second);
    }
}
