package com.psa.mym.mycitroenconnect.views.recycler_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public abstract class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    @NotNull
    private final ColorDrawable background;
    private final int backgroundColor;
    @NotNull
    private final Paint clearPaint;
    @Nullable
    private final Drawable deleteIcon;
    private final int intrinsicHeight;
    private final int intrinsicWidth;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeToDeleteCallback(@NotNull Context context) {
        super(0, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_without_background);
        this.deleteIcon = drawable;
        this.intrinsicWidth = drawable != null ? drawable.getIntrinsicWidth() : 0;
        this.intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
        this.background = new ColorDrawable();
        this.backgroundColor = ContextCompat.getColor(context, R.color.special_color_1);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.clearPaint = paint;
    }

    private final void clearCanvas(Canvas canvas, float f2, float f3, float f4, float f5) {
        if (canvas != null) {
            canvas.drawRect(f2, f3, f4, f5, this.clearPaint);
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        if (viewHolder.getAbsoluteAdapterPosition() == 10) {
            return 0;
        }
        return super.getMovementFlags(recyclerView, viewHolder);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(@NotNull Canvas c2, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(c2, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        View view = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "viewHolder.itemView");
        int bottom = view.getBottom() - view.getTop();
        boolean z2 = true;
        if (!(f2 == 0.0f) || z) {
            z2 = false;
        }
        if (z2) {
            clearCanvas(c2, view.getRight() + f2, view.getTop(), view.getRight(), view.getBottom());
            super.onChildDraw(c2, recyclerView, viewHolder, f2, f3, i2, z);
            return;
        }
        this.background.setColor(this.backgroundColor);
        this.background.setBounds(view.getRight() + ((int) f2), view.getTop(), view.getRight(), view.getBottom());
        this.background.draw(c2);
        int top = view.getTop();
        int i3 = this.intrinsicHeight;
        int i4 = top + ((bottom - i3) / 2);
        int i5 = (bottom - i3) / 2;
        int right = (view.getRight() - i5) - this.intrinsicWidth;
        int right2 = view.getRight() - i5;
        int i6 = this.intrinsicHeight + i4;
        Drawable drawable = this.deleteIcon;
        if (drawable != null) {
            drawable.setBounds(right, i4, right2, i6);
        }
        Drawable drawable2 = this.deleteIcon;
        if (drawable2 != null) {
            drawable2.draw(c2);
        }
        super.onChildDraw(c2, recyclerView, viewHolder, f2, f3, i2, z);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        return false;
    }
}
