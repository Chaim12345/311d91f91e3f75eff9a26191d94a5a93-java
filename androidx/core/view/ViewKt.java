package androidx.core.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.sequences.SequencesKt__SequencesKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a5\u0010\u0007\u001a\u00020\u0005*\u00020\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0001H\u0086\bø\u0001\u0000\u001a5\u0010\b\u001a\u00020\u0005*\u00020\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0001H\u0086\bø\u0001\u0000\u001a5\u0010\n\u001a\u00020\t*\u00020\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0001H\u0086\bø\u0001\u0000\u001a5\u0010\u000b\u001a\u00020\u0005*\u00020\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0001H\u0086\bø\u0001\u0000\u001a5\u0010\f\u001a\u00020\u0005*\u00020\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00050\u0001H\u0086\bø\u0001\u0000\u001a5\u0010\u0012\u001a\u00020\u0005*\u00020\u00002\b\b\u0003\u0010\u000e\u001a\u00020\r2\b\b\u0003\u0010\u000f\u001a\u00020\r2\b\b\u0003\u0010\u0010\u001a\u00020\r2\b\b\u0003\u0010\u0011\u001a\u00020\rH\u0087\b\u001a5\u0010\u0015\u001a\u00020\u0005*\u00020\u00002\b\b\u0003\u0010\u0013\u001a\u00020\r2\b\b\u0003\u0010\u000f\u001a\u00020\r2\b\b\u0003\u0010\u0014\u001a\u00020\r2\b\b\u0003\u0010\u0011\u001a\u00020\rH\u0086\b\u001a\u0017\u0010\u0017\u001a\u00020\u0005*\u00020\u00002\b\b\u0001\u0010\u0016\u001a\u00020\rH\u0086\b\u001a(\u0010\u001c\u001a\u00020\u001b*\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00182\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0086\bø\u0001\u0000\u001a(\u0010\u001d\u001a\u00020\u001b*\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00182\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0087\bø\u0001\u0000\u001a\u0014\u0010!\u001a\u00020 *\u00020\u00002\b\b\u0002\u0010\u001f\u001a\u00020\u001e\u001a)\u0010%\u001a\u00020\u0005*\u00020\u00002\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0001¢\u0006\u0002\b#H\u0086\bø\u0001\u0000\u001a<\u0010%\u001a\u00020\u0005\"\n\b\u0000\u0010&\u0018\u0001*\u00020\"*\u00020\u00002\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0001¢\u0006\u0002\b#H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\"*\u0010+\u001a\u00020)*\u00020\u00002\u0006\u0010*\u001a\u00020)8Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\"*\u0010/\u001a\u00020)*\u00020\u00002\u0006\u0010*\u001a\u00020)8Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b/\u0010,\"\u0004\b0\u0010.\"*\u00101\u001a\u00020)*\u00020\u00002\u0006\u0010*\u001a\u00020)8Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010,\"\u0004\b2\u0010.\"\u0018\u00105\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b3\u00104\"\u0018\u00107\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b6\u00104\"\u0018\u00109\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b8\u00104\"\u0018\u0010;\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b:\u00104\"\u0018\u0010=\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b<\u00104\"\u0018\u0010?\u001a\u00020\r*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b>\u00104\"\u001d\u0010D\u001a\b\u0012\u0004\u0012\u00020A0@*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\bB\u0010C\"\u001d\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00000@*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\bE\u0010C\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006G"}, d2 = {"Landroid/view/View;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "view", "", "action", "doOnNextLayout", "doOnLayout", "Landroidx/core/view/OneShotPreDrawListener;", "doOnPreDraw", "doOnAttach", "doOnDetach", "", "start", "top", "end", "bottom", "updatePaddingRelative", "left", "right", "updatePadding", "size", "setPadding", "", "delayInMillis", "Lkotlin/Function0;", "Ljava/lang/Runnable;", "postDelayed", "postOnAnimationDelayed", "Landroid/graphics/Bitmap$Config;", "config", "Landroid/graphics/Bitmap;", "drawToBitmap", "Landroid/view/ViewGroup$LayoutParams;", "Lkotlin/ExtensionFunctionType;", "block", "updateLayoutParams", ExifInterface.GPS_DIRECTION_TRUE, "updateLayoutParamsTyped", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "", "value", "isVisible", "(Landroid/view/View;)Z", "setVisible", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isGone", "setGone", "getMarginLeft", "(Landroid/view/View;)I", "marginLeft", "getMarginTop", "marginTop", "getMarginRight", "marginRight", "getMarginBottom", "marginBottom", "getMarginStart", "marginStart", "getMarginEnd", "marginEnd", "Lkotlin/sequences/Sequence;", "Landroid/view/ViewParent;", "getAncestors", "(Landroid/view/View;)Lkotlin/sequences/Sequence;", "ancestors", "getAllViews", "allViews", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class ViewKt {
    public static final void doOnAttach(@NotNull final View doOnAttach, @NotNull final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(doOnAttach, "$this$doOnAttach");
        Intrinsics.checkNotNullParameter(action, "action");
        if (ViewCompat.isAttachedToWindow(doOnAttach)) {
            action.invoke(doOnAttach);
        } else {
            doOnAttach.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.core.view.ViewKt$doOnAttach$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    doOnAttach.removeOnAttachStateChangeListener(this);
                    action.invoke(view);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }
            });
        }
    }

    public static final void doOnDetach(@NotNull final View doOnDetach, @NotNull final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(doOnDetach, "$this$doOnDetach");
        Intrinsics.checkNotNullParameter(action, "action");
        if (ViewCompat.isAttachedToWindow(doOnDetach)) {
            doOnDetach.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.core.view.ViewKt$doOnDetach$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    doOnDetach.removeOnAttachStateChangeListener(this);
                    action.invoke(view);
                }
            });
        } else {
            action.invoke(doOnDetach);
        }
    }

    public static final void doOnLayout(@NotNull View doOnLayout, @NotNull final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(doOnLayout, "$this$doOnLayout");
        Intrinsics.checkNotNullParameter(action, "action");
        if (!ViewCompat.isLaidOut(doOnLayout) || doOnLayout.isLayoutRequested()) {
            doOnLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.core.view.ViewKt$doOnLayout$$inlined$doOnNextLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(@NotNull View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    view.removeOnLayoutChangeListener(this);
                    Function1.this.invoke(view);
                }
            });
        } else {
            action.invoke(doOnLayout);
        }
    }

    public static final void doOnNextLayout(@NotNull View doOnNextLayout, @NotNull final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(doOnNextLayout, "$this$doOnNextLayout");
        Intrinsics.checkNotNullParameter(action, "action");
        doOnNextLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.core.view.ViewKt$doOnNextLayout$1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(@NotNull View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.removeOnLayoutChangeListener(this);
                Function1.this.invoke(view);
            }
        });
    }

    @NotNull
    public static final OneShotPreDrawListener doOnPreDraw(@NotNull final View doOnPreDraw, @NotNull final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(doOnPreDraw, "$this$doOnPreDraw");
        Intrinsics.checkNotNullParameter(action, "action");
        OneShotPreDrawListener add = OneShotPreDrawListener.add(doOnPreDraw, new Runnable() { // from class: androidx.core.view.ViewKt$doOnPreDraw$1
            @Override // java.lang.Runnable
            public final void run() {
                action.invoke(doOnPreDraw);
            }
        });
        Intrinsics.checkNotNullExpressionValue(add, "OneShotPreDrawListener.add(this) { action(this) }");
        return add;
    }

    @NotNull
    public static final Bitmap drawToBitmap(@NotNull View drawToBitmap, @NotNull Bitmap.Config config) {
        Intrinsics.checkNotNullParameter(drawToBitmap, "$this$drawToBitmap");
        Intrinsics.checkNotNullParameter(config, "config");
        if (ViewCompat.isLaidOut(drawToBitmap)) {
            Bitmap createBitmap = Bitmap.createBitmap(drawToBitmap.getWidth(), drawToBitmap.getHeight(), config);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "Bitmap.createBitmap(width, height, config)");
            Canvas canvas = new Canvas(createBitmap);
            canvas.translate(-drawToBitmap.getScrollX(), -drawToBitmap.getScrollY());
            drawToBitmap.draw(canvas);
            return createBitmap;
        }
        throw new IllegalStateException("View needs to be laid out before calling drawToBitmap()");
    }

    public static /* synthetic */ Bitmap drawToBitmap$default(View view, Bitmap.Config config, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            config = Bitmap.Config.ARGB_8888;
        }
        return drawToBitmap(view, config);
    }

    @NotNull
    public static final Sequence<View> getAllViews(@NotNull View allViews) {
        Sequence<View> sequence;
        Intrinsics.checkNotNullParameter(allViews, "$this$allViews");
        sequence = SequencesKt__SequenceBuilderKt.sequence(new ViewKt$allViews$1(allViews, null));
        return sequence;
    }

    @NotNull
    public static final Sequence<ViewParent> getAncestors(@NotNull View ancestors) {
        Sequence<ViewParent> generateSequence;
        Intrinsics.checkNotNullParameter(ancestors, "$this$ancestors");
        generateSequence = SequencesKt__SequencesKt.generateSequence(ancestors.getParent(), ViewKt$ancestors$1.INSTANCE);
        return generateSequence;
    }

    public static final int getMarginBottom(@NotNull View marginBottom) {
        Intrinsics.checkNotNullParameter(marginBottom, "$this$marginBottom");
        ViewGroup.LayoutParams layoutParams = marginBottom.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.bottomMargin;
        }
        return 0;
    }

    public static final int getMarginEnd(@NotNull View marginEnd) {
        Intrinsics.checkNotNullParameter(marginEnd, "$this$marginEnd");
        ViewGroup.LayoutParams layoutParams = marginEnd.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return 0;
    }

    public static final int getMarginLeft(@NotNull View marginLeft) {
        Intrinsics.checkNotNullParameter(marginLeft, "$this$marginLeft");
        ViewGroup.LayoutParams layoutParams = marginLeft.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.leftMargin;
        }
        return 0;
    }

    public static final int getMarginRight(@NotNull View marginRight) {
        Intrinsics.checkNotNullParameter(marginRight, "$this$marginRight");
        ViewGroup.LayoutParams layoutParams = marginRight.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.rightMargin;
        }
        return 0;
    }

    public static final int getMarginStart(@NotNull View marginStart) {
        Intrinsics.checkNotNullParameter(marginStart, "$this$marginStart");
        ViewGroup.LayoutParams layoutParams = marginStart.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return 0;
    }

    public static final int getMarginTop(@NotNull View marginTop) {
        Intrinsics.checkNotNullParameter(marginTop, "$this$marginTop");
        ViewGroup.LayoutParams layoutParams = marginTop.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.topMargin;
        }
        return 0;
    }

    public static final boolean isGone(@NotNull View isGone) {
        Intrinsics.checkNotNullParameter(isGone, "$this$isGone");
        return isGone.getVisibility() == 8;
    }

    public static final boolean isInvisible(@NotNull View isInvisible) {
        Intrinsics.checkNotNullParameter(isInvisible, "$this$isInvisible");
        return isInvisible.getVisibility() == 4;
    }

    public static final boolean isVisible(@NotNull View isVisible) {
        Intrinsics.checkNotNullParameter(isVisible, "$this$isVisible");
        return isVisible.getVisibility() == 0;
    }

    @NotNull
    public static final Runnable postDelayed(@NotNull View postDelayed, long j2, @NotNull final Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(postDelayed, "$this$postDelayed");
        Intrinsics.checkNotNullParameter(action, "action");
        Runnable runnable = new Runnable() { // from class: androidx.core.view.ViewKt$postDelayed$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        };
        postDelayed.postDelayed(runnable, j2);
        return runnable;
    }

    @RequiresApi(16)
    @NotNull
    public static final Runnable postOnAnimationDelayed(@NotNull View postOnAnimationDelayed, long j2, @NotNull final Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(postOnAnimationDelayed, "$this$postOnAnimationDelayed");
        Intrinsics.checkNotNullParameter(action, "action");
        Runnable runnable = new Runnable() { // from class: androidx.core.view.ViewKt$postOnAnimationDelayed$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        };
        postOnAnimationDelayed.postOnAnimationDelayed(runnable, j2);
        return runnable;
    }

    public static final void setGone(@NotNull View isGone, boolean z) {
        Intrinsics.checkNotNullParameter(isGone, "$this$isGone");
        isGone.setVisibility(z ? 8 : 0);
    }

    public static final void setInvisible(@NotNull View isInvisible, boolean z) {
        Intrinsics.checkNotNullParameter(isInvisible, "$this$isInvisible");
        isInvisible.setVisibility(z ? 4 : 0);
    }

    public static final void setPadding(@NotNull View setPadding, @Px int i2) {
        Intrinsics.checkNotNullParameter(setPadding, "$this$setPadding");
        setPadding.setPadding(i2, i2, i2, i2);
    }

    public static final void setVisible(@NotNull View isVisible, boolean z) {
        Intrinsics.checkNotNullParameter(isVisible, "$this$isVisible");
        isVisible.setVisibility(z ? 0 : 8);
    }

    public static final void updateLayoutParams(@NotNull View updateLayoutParams, @NotNull Function1<? super ViewGroup.LayoutParams, Unit> block) {
        Intrinsics.checkNotNullParameter(updateLayoutParams, "$this$updateLayoutParams");
        Intrinsics.checkNotNullParameter(block, "block");
        ViewGroup.LayoutParams layoutParams = updateLayoutParams.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        block.invoke(layoutParams);
        updateLayoutParams.setLayoutParams(layoutParams);
    }

    @JvmName(name = "updateLayoutParamsTyped")
    public static final /* synthetic */ <T extends ViewGroup.LayoutParams> void updateLayoutParamsTyped(View updateLayoutParams, Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(updateLayoutParams, "$this$updateLayoutParams");
        Intrinsics.checkNotNullParameter(block, "block");
        ViewGroup.LayoutParams layoutParams = updateLayoutParams.getLayoutParams();
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        block.invoke(layoutParams);
        updateLayoutParams.setLayoutParams(layoutParams);
    }

    public static final void updatePadding(@NotNull View updatePadding, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        Intrinsics.checkNotNullParameter(updatePadding, "$this$updatePadding");
        updatePadding.setPadding(i2, i3, i4, i5);
    }

    public static /* synthetic */ void updatePadding$default(View updatePadding, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = updatePadding.getPaddingLeft();
        }
        if ((i6 & 2) != 0) {
            i3 = updatePadding.getPaddingTop();
        }
        if ((i6 & 4) != 0) {
            i4 = updatePadding.getPaddingRight();
        }
        if ((i6 & 8) != 0) {
            i5 = updatePadding.getPaddingBottom();
        }
        Intrinsics.checkNotNullParameter(updatePadding, "$this$updatePadding");
        updatePadding.setPadding(i2, i3, i4, i5);
    }

    @RequiresApi(17)
    public static final void updatePaddingRelative(@NotNull View updatePaddingRelative, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        Intrinsics.checkNotNullParameter(updatePaddingRelative, "$this$updatePaddingRelative");
        updatePaddingRelative.setPaddingRelative(i2, i3, i4, i5);
    }

    public static /* synthetic */ void updatePaddingRelative$default(View updatePaddingRelative, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = updatePaddingRelative.getPaddingStart();
        }
        if ((i6 & 2) != 0) {
            i3 = updatePaddingRelative.getPaddingTop();
        }
        if ((i6 & 4) != 0) {
            i4 = updatePaddingRelative.getPaddingEnd();
        }
        if ((i6 & 8) != 0) {
            i5 = updatePaddingRelative.getPaddingBottom();
        }
        Intrinsics.checkNotNullParameter(updatePaddingRelative, "$this$updatePaddingRelative");
        updatePaddingRelative.setPaddingRelative(i2, i3, i4, i5);
    }
}
