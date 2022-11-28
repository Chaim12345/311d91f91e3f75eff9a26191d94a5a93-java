package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\u0005\u001a\u00020\u0002*\u00020\u00002\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a=\u0010\t\u001a\u00020\u0002*\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001aG\u0010\r\u001a\u00020\u0002*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001aQ\u0010\u000e\u001a\u00020\u0002*\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a=\u0010\u000f\u001a\u00020\u0002*\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a3\u0010\u0012\u001a\u00020\u0002*\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a1\u0010\u0015\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a1\u0010\u0015\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00162\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001aI\u0010\u0015\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001aI\u0010\u0015\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u001a1\u0010\u0015\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\b\u0003H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"Landroid/graphics/Canvas;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "block", "withSave", "", "x", "y", "withTranslation", "degrees", "pivotX", "pivotY", "withRotation", "withScale", "withSkew", "Landroid/graphics/Matrix;", "matrix", "withMatrix", "Landroid/graphics/Rect;", "clipRect", "withClip", "Landroid/graphics/RectF;", "", "left", "top", "right", "bottom", "Landroid/graphics/Path;", "clipPath", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class CanvasKt {
    public static final void withClip(@NotNull Canvas withClip, float f2, float f3, float f4, float f5, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withClip, "$this$withClip");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withClip.save();
        withClip.clipRect(f2, f3, f4, f5);
        try {
            block.invoke(withClip);
        } finally {
            InlineMarker.finallyStart(1);
            withClip.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClip(@NotNull Canvas withClip, int i2, int i3, int i4, int i5, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withClip, "$this$withClip");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withClip.save();
        withClip.clipRect(i2, i3, i4, i5);
        try {
            block.invoke(withClip);
        } finally {
            InlineMarker.finallyStart(1);
            withClip.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClip(@NotNull Canvas withClip, @NotNull Path clipPath, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withClip, "$this$withClip");
        Intrinsics.checkNotNullParameter(clipPath, "clipPath");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withClip.save();
        withClip.clipPath(clipPath);
        try {
            block.invoke(withClip);
        } finally {
            InlineMarker.finallyStart(1);
            withClip.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClip(@NotNull Canvas withClip, @NotNull Rect clipRect, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withClip, "$this$withClip");
        Intrinsics.checkNotNullParameter(clipRect, "clipRect");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withClip.save();
        withClip.clipRect(clipRect);
        try {
            block.invoke(withClip);
        } finally {
            InlineMarker.finallyStart(1);
            withClip.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClip(@NotNull Canvas withClip, @NotNull RectF clipRect, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withClip, "$this$withClip");
        Intrinsics.checkNotNullParameter(clipRect, "clipRect");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withClip.save();
        withClip.clipRect(clipRect);
        try {
            block.invoke(withClip);
        } finally {
            InlineMarker.finallyStart(1);
            withClip.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withMatrix(@NotNull Canvas withMatrix, @NotNull Matrix matrix, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withMatrix, "$this$withMatrix");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withMatrix.save();
        withMatrix.concat(matrix);
        try {
            block.invoke(withMatrix);
        } finally {
            InlineMarker.finallyStart(1);
            withMatrix.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void withMatrix$default(Canvas withMatrix, Matrix matrix, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            matrix = new Matrix();
        }
        Intrinsics.checkNotNullParameter(withMatrix, "$this$withMatrix");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withMatrix.save();
        withMatrix.concat(matrix);
        try {
            block.invoke(withMatrix);
        } finally {
            InlineMarker.finallyStart(1);
            withMatrix.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withRotation(@NotNull Canvas withRotation, float f2, float f3, float f4, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withRotation, "$this$withRotation");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withRotation.save();
        withRotation.rotate(f2, f3, f4);
        try {
            block.invoke(withRotation);
        } finally {
            InlineMarker.finallyStart(1);
            withRotation.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void withRotation$default(Canvas withRotation, float f2, float f3, float f4, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 0.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        if ((i2 & 4) != 0) {
            f4 = 0.0f;
        }
        Intrinsics.checkNotNullParameter(withRotation, "$this$withRotation");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withRotation.save();
        withRotation.rotate(f2, f3, f4);
        try {
            block.invoke(withRotation);
        } finally {
            InlineMarker.finallyStart(1);
            withRotation.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withSave(@NotNull Canvas withSave, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withSave, "$this$withSave");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withSave.save();
        try {
            block.invoke(withSave);
        } finally {
            InlineMarker.finallyStart(1);
            withSave.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withScale(@NotNull Canvas withScale, float f2, float f3, float f4, float f5, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withScale, "$this$withScale");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withScale.save();
        withScale.scale(f2, f3, f4, f5);
        try {
            block.invoke(withScale);
        } finally {
            InlineMarker.finallyStart(1);
            withScale.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void withScale$default(Canvas withScale, float f2, float f3, float f4, float f5, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 1.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 1.0f;
        }
        if ((i2 & 4) != 0) {
            f4 = 0.0f;
        }
        if ((i2 & 8) != 0) {
            f5 = 0.0f;
        }
        Intrinsics.checkNotNullParameter(withScale, "$this$withScale");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withScale.save();
        withScale.scale(f2, f3, f4, f5);
        try {
            block.invoke(withScale);
        } finally {
            InlineMarker.finallyStart(1);
            withScale.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withSkew(@NotNull Canvas withSkew, float f2, float f3, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withSkew, "$this$withSkew");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withSkew.save();
        withSkew.skew(f2, f3);
        try {
            block.invoke(withSkew);
        } finally {
            InlineMarker.finallyStart(1);
            withSkew.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void withSkew$default(Canvas withSkew, float f2, float f3, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 0.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        Intrinsics.checkNotNullParameter(withSkew, "$this$withSkew");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withSkew.save();
        withSkew.skew(f2, f3);
        try {
            block.invoke(withSkew);
        } finally {
            InlineMarker.finallyStart(1);
            withSkew.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withTranslation(@NotNull Canvas withTranslation, float f2, float f3, @NotNull Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(withTranslation, "$this$withTranslation");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withTranslation.save();
        withTranslation.translate(f2, f3);
        try {
            block.invoke(withTranslation);
        } finally {
            InlineMarker.finallyStart(1);
            withTranslation.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void withTranslation$default(Canvas withTranslation, float f2, float f3, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 0.0f;
        }
        if ((i2 & 2) != 0) {
            f3 = 0.0f;
        }
        Intrinsics.checkNotNullParameter(withTranslation, "$this$withTranslation");
        Intrinsics.checkNotNullParameter(block, "block");
        int save = withTranslation.save();
        withTranslation.translate(f2, f3);
        try {
            block.invoke(withTranslation);
        } finally {
            InlineMarker.finallyStart(1);
            withTranslation.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }
}
