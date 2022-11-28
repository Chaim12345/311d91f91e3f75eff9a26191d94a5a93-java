package com.psa.mym.mycitroenconnect.views.page_indicator_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.BaseDrawer;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.DrawerProxy;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.AttrsController;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class IndicatorView extends BaseIndicatorView {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    private DrawerProxy mDrawerProxy;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public IndicatorView(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public IndicatorView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public IndicatorView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        AttrsController.INSTANCE.initAttrs(context, attributeSet, getMIndicatorOptions());
        this.mDrawerProxy = new DrawerProxy(getMIndicatorOptions());
    }

    public /* synthetic */ IndicatorView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final void rotateCanvas(Canvas canvas) {
        float f2;
        float width;
        int height;
        if (getMIndicatorOptions().getOrientation() == 1) {
            f2 = 90.0f;
            width = getWidth() / 2.0f;
            height = getWidth();
        } else if (getMIndicatorOptions().getOrientation() != 3) {
            return;
        } else {
            f2 = 180.0f;
            width = getWidth() / 2.0f;
            height = getHeight();
        }
        canvas.rotate(f2, width, height / 2.0f);
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView, com.psa.mym.mycitroenconnect.views.page_indicator_view.base.IIndicator
    public void notifyDataChanged() {
        this.mDrawerProxy = new DrawerProxy(getMIndicatorOptions());
        super.notifyDataChanged();
    }

    @Override // android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        rotateCanvas(canvas);
        this.mDrawerProxy.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mDrawerProxy.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        BaseDrawer.MeasureResult onMeasure = this.mDrawerProxy.onMeasure(i2, i3);
        setMeasuredDimension(onMeasure.getMeasureWidth(), onMeasure.getMeasureHeight());
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.base.BaseIndicatorView, com.psa.mym.mycitroenconnect.views.page_indicator_view.base.IIndicator
    public void setIndicatorOptions(@NotNull IndicatorOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        super.setIndicatorOptions(options);
        this.mDrawerProxy.setIndicatorOptions(options);
    }

    public final void setOrientation(int i2) {
        getMIndicatorOptions().setOrientation(i2);
    }
}
