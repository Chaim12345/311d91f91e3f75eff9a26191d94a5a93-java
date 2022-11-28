package com.psa.mym.mycitroenconnect.views.recycler_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.views.recycler_view.SwipeHelper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SuppressLint({"ClickableViewAccessibility"})
/* loaded from: classes3.dex */
public abstract class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    @NotNull
    private final Map<Integer, List<UnderlayButton>> buttonsBuffer;
    @NotNull
    private final SwipeHelper$recoverQueue$1 recoverQueue;
    @NotNull
    private final RecyclerView recyclerView;
    private int swipedPosition;
    @SuppressLint({"ClickableViewAccessibility"})
    @NotNull
    private final View.OnTouchListener touchListener;

    /* loaded from: classes3.dex */
    public static final class UnderlayButton {
        private final int backgroundColor;
        @NotNull
        private final UnderlayButtonClickListener clickListener;
        @Nullable
        private RectF clickableRegion;
        @NotNull
        private final Context context;
        private final float horizontalPadding;
        private final int icon;
        private final float intrinsicWidth;
        private final float textSizeInPixel;

        public UnderlayButton(@NotNull Context context, @NotNull String title, float f2, @DrawableRes int i2, @ColorRes int i3, @NotNull UnderlayButtonClickListener clickListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(clickListener, "clickListener");
            this.context = context;
            this.icon = i2;
            this.backgroundColor = i3;
            this.clickListener = clickListener;
            float f3 = f2 * context.getResources().getDisplayMetrics().density;
            this.textSizeInPixel = f3;
            this.horizontalPadding = 50.0f;
            Paint paint = new Paint();
            paint.setTextSize(f3);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextAlign(Paint.Align.LEFT);
            Rect rect = new Rect();
            paint.getTextBounds(title, 0, title.length(), rect);
            this.intrinsicWidth = rect.width() + (2 * 50.0f);
        }

        public final void draw(@NotNull Canvas canvas, @NotNull RectF rect) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(rect, "rect");
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(this.context, this.backgroundColor));
            canvas.drawRect(rect, paint);
            Drawable drawable = ContextCompat.getDrawable(this.context, this.icon);
            if (drawable != null) {
                float f2 = 50;
                drawable.setBounds((int) (rect.left + f2), (int) (rect.top + (rect.height() / 2.0f)), (int) (rect.right - f2), (int) (rect.bottom - (rect.height() / 10.0f)));
            }
            if (drawable != null) {
                drawable.draw(canvas);
            }
            this.clickableRegion = rect;
        }

        public final float getIntrinsicWidth() {
            return this.intrinsicWidth;
        }

        public final void handle(@NotNull MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            RectF rectF = this.clickableRegion;
            if (rectF == null || !rectF.contains(event.getX(), event.getY())) {
                return;
            }
            this.clickListener.onClick();
        }
    }

    /* loaded from: classes3.dex */
    public interface UnderlayButtonClickListener {
        void onClick();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeHelper(@NotNull RecyclerView recyclerView) {
        super(0, 4);
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this.recyclerView = recyclerView;
        this.swipedPosition = -1;
        this.buttonsBuffer = new LinkedHashMap();
        this.recoverQueue = new SwipeHelper$recoverQueue$1();
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: q.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean m173touchListener$lambda1;
                m173touchListener$lambda1 = SwipeHelper.m173touchListener$lambda1(SwipeHelper.this, view, motionEvent);
                return m173touchListener$lambda1;
            }
        };
        this.touchListener = onTouchListener;
        recyclerView.setOnTouchListener(onTouchListener);
    }

    private final void drawButtons(Canvas canvas, List<UnderlayButton> list, View view, float f2) {
        float intrinsicWidth;
        int right = view.getRight();
        for (UnderlayButton underlayButton : list) {
            float intrinsicWidth2 = underlayButton.getIntrinsicWidth();
            intrinsicWidth = SwipeHelperKt.intrinsicWidth(list);
            float f3 = right;
            float abs = f3 - ((intrinsicWidth2 / intrinsicWidth) * Math.abs(f2));
            underlayButton.draw(canvas, new RectF(abs, view.getTop(), f3, view.getBottom()));
            right = (int) abs;
        }
    }

    private final void recoverSwipedItem() {
        Integer poll;
        while (!this.recoverQueue.isEmpty() && (poll = this.recoverQueue.poll()) != null) {
            int intValue = poll.intValue();
            RecyclerView.Adapter adapter = this.recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemChanged(intValue);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: touchListener$lambda-1  reason: not valid java name */
    public static final boolean m173touchListener$lambda1(SwipeHelper this$0, View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i2 = this$0.swipedPosition;
        if (i2 < 0) {
            return false;
        }
        List<UnderlayButton> list = this$0.buttonsBuffer.get(Integer.valueOf(i2));
        if (list != null) {
            for (UnderlayButton underlayButton : list) {
                Intrinsics.checkNotNullExpressionValue(event, "event");
                underlayButton.handle(event);
            }
        }
        this$0.recoverQueue.add(this$0.swipedPosition);
        this$0.swipedPosition = -1;
        this$0.recoverSwipedItem();
        return true;
    }

    @NotNull
    public abstract List<UnderlayButton> instantiateUnderlayButton(int i2);

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(@NotNull Canvas c2, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
        float intrinsicWidth;
        float f4 = f2;
        Intrinsics.checkNotNullParameter(c2, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
        View view = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "viewHolder.itemView");
        if (i2 == 1 && f4 < 0.0f) {
            if (!this.buttonsBuffer.containsKey(Integer.valueOf(absoluteAdapterPosition))) {
                this.buttonsBuffer.put(Integer.valueOf(absoluteAdapterPosition), instantiateUnderlayButton(absoluteAdapterPosition));
            }
            List<UnderlayButton> list = this.buttonsBuffer.get(Integer.valueOf(absoluteAdapterPosition));
            if (list == null || list.isEmpty()) {
                return;
            }
            intrinsicWidth = SwipeHelperKt.intrinsicWidth(list);
            f4 = Math.max(-intrinsicWidth, f2);
            drawButtons(c2, list, view, f4);
        }
        super.onChildDraw(c2, recyclerView, viewHolder, f4, f3, i2, z);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i2) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
        int i3 = this.swipedPosition;
        if (i3 != absoluteAdapterPosition) {
            this.recoverQueue.add(i3);
        }
        this.swipedPosition = absoluteAdapterPosition;
        recoverSwipedItem();
    }
}
