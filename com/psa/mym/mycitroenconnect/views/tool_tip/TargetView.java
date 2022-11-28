package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TargetView extends AppCompatImageView {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    private final int[] position;
    @NotNull
    private final int[] size;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TargetView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.position = new int[4];
        this.size = new int[2];
    }

    private final Bitmap getBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // android.view.View
    public void layout(int i2, int i3, int i4, int i5) {
        int[] iArr = this.position;
        super.layout(iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.size[0], 1073741824), View.MeasureSpec.makeMeasureSpec(this.size[1], 1073741824));
    }

    public final void setTarget(@Nullable View view) {
        if (view == null) {
            return;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = this.position;
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[0] + view.getWidth();
        this.position[3] = iArr[1] + view.getHeight();
        Bitmap bitmapFromView = getBitmapFromView(view);
        if (bitmapFromView == null) {
            return;
        }
        setImageBitmap(bitmapFromView);
    }
}
