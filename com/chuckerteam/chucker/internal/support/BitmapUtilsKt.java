package com.chuckerteam.chucker.internal.support;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0016\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u001f\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\t\"\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Landroid/graphics/Bitmap;", "", "calculateLuminance", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", TypedValues.Custom.S_COLOR, "replaceAlphaWithColor", "alphaSubstitute", "getLuminance", "(Landroid/graphics/Bitmap;I)Ljava/lang/Double;", "Landroid/graphics/Paint;", "BITMAP_PAINT", "Landroid/graphics/Paint;", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class BitmapUtilsKt {
    private static final Paint BITMAP_PAINT = new Paint(2);

    @Nullable
    public static final Object calculateLuminance(@NotNull Bitmap bitmap, @NotNull Continuation<? super Double> continuation) {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -65281;
        return BuildersKt.withContext(Dispatchers.getDefault(), new BitmapUtilsKt$calculateLuminance$2(bitmap, intRef, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Double getLuminance(Bitmap bitmap, @ColorInt final int i2) {
        Palette generate = Palette.from(bitmap).clearFilters().addFilter(new Palette.Filter() { // from class: com.chuckerteam.chucker.internal.support.BitmapUtilsKt$getLuminance$imagePalette$1
            @Override // androidx.palette.graphics.Palette.Filter
            public final boolean isAllowed(int i3, @NotNull float[] fArr) {
                Intrinsics.checkNotNullParameter(fArr, "<anonymous parameter 1>");
                return i3 != i2;
            }
        }).generate();
        Intrinsics.checkNotNullExpressionValue(generate, "Palette.from(this)\n     …te) }\n        .generate()");
        Palette.Swatch dominantSwatch = generate.getDominantSwatch();
        if (dominantSwatch != null) {
            return Double.valueOf(ColorUtils.calculateLuminance(dominantSwatch.getRgb()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap replaceAlphaWithColor(Bitmap bitmap, @ColorInt int i2) {
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        result.eraseColor(i2);
        new Canvas(result).drawBitmap(bitmap, new Matrix(), BITMAP_PAINT);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }
}
