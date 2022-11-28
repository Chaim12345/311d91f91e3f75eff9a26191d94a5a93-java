package androidx.core.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.StyleRes;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\"\u0010\u0003\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u0002H\u0086\b¢\u0006\u0004\b\u0003\u0010\u0004\u001aQ\u0010\u0011\u001a\u00020\u000e*\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0003\u0010\u000b\u001a\u00020\t2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0086\bø\u0001\u0000\u001a;\u0010\u0011\u001a\u00020\u000e*\u00020\u00022\b\b\u0001\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/content/Context;", "getSystemService", "(Landroid/content/Context;)Ljava/lang/Object;", "Landroid/util/AttributeSet;", "set", "", "attrs", "", "defStyleAttr", "defStyleRes", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "", "Lkotlin/ExtensionFunctionType;", "block", "withStyledAttributes", "resourceId", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class ContextKt {
    public static final /* synthetic */ <T> T getSystemService(Context getSystemService) {
        Intrinsics.checkNotNullParameter(getSystemService, "$this$getSystemService");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(getSystemService, Object.class);
    }

    public static final void withStyledAttributes(@NotNull Context withStyledAttributes, @StyleRes int i2, @NotNull int[] attrs, @NotNull Function1<? super TypedArray, Unit> block) {
        Intrinsics.checkNotNullParameter(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(i2, attrs);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(resourceId, attrs)");
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(@NotNull Context withStyledAttributes, @Nullable AttributeSet attributeSet, @NotNull int[] attrs, @AttrRes int i2, @StyleRes int i3, @NotNull Function1<? super TypedArray, Unit> block) {
        Intrinsics.checkNotNullParameter(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(attributeSet, attrs, i2, i3);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(s…efStyleAttr, defStyleRes)");
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public static /* synthetic */ void withStyledAttributes$default(Context withStyledAttributes, AttributeSet attributeSet, int[] attrs, int i2, int i3, Function1 block, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            attributeSet = null;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        Intrinsics.checkNotNullParameter(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(attributeSet, attrs, i2, i3);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(s…efStyleAttr, defStyleRes)");
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }
}
