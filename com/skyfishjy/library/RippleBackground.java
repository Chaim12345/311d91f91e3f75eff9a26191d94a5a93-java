package com.skyfishjy.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes3.dex */
public class RippleBackground extends RelativeLayout {
    private static final int DEFAULT_DURATION_TIME = 3000;
    private static final int DEFAULT_FILL_TYPE = 0;
    private static final int DEFAULT_RIPPLE_COUNT = 6;
    private static final float DEFAULT_SCALE = 6.0f;
    private boolean animationRunning;
    private ArrayList<Animator> animatorList;
    private AnimatorSet animatorSet;
    private Paint paint;
    private int rippleAmount;
    private int rippleColor;
    private int rippleDelay;
    private int rippleDurationTime;
    private RelativeLayout.LayoutParams rippleParams;
    private float rippleRadius;
    private float rippleScale;
    private float rippleStrokeWidth;
    private int rippleType;
    private ArrayList<RippleView> rippleViewList;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            setVisibility(4);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            float min = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle(min, min, min - RippleBackground.this.rippleStrokeWidth, RippleBackground.this.paint);
        }
    }

    public RippleBackground(Context context) {
        super(context);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
        init(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Paint paint;
        Paint.Style style;
        if (isInEditMode()) {
            return;
        }
        if (attributeSet == null) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
        this.rippleColor = obtainStyledAttributes.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.rippelColor));
        this.rippleStrokeWidth = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
        this.rippleRadius = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_radius, getResources().getDimension(R.dimen.rippleRadius));
        this.rippleDurationTime = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_duration, 3000);
        this.rippleAmount = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_rippleAmount, 6);
        this.rippleScale = obtainStyledAttributes.getFloat(R.styleable.RippleBackground_rb_scale, DEFAULT_SCALE);
        this.rippleType = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_type, 0);
        obtainStyledAttributes.recycle();
        this.rippleDelay = this.rippleDurationTime / this.rippleAmount;
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        if (this.rippleType == 0) {
            this.rippleStrokeWidth = 0.0f;
            paint = this.paint;
            style = Paint.Style.FILL;
        } else {
            paint = this.paint;
            style = Paint.Style.STROKE;
        }
        paint.setStyle(style);
        this.paint.setColor(this.rippleColor);
        float f2 = this.rippleRadius;
        float f3 = this.rippleStrokeWidth;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((f2 + f3) * 2.0f), (int) ((f2 + f3) * 2.0f));
        this.rippleParams = layoutParams;
        layoutParams.addRule(13, -1);
        AnimatorSet animatorSet = new AnimatorSet();
        this.animatorSet = animatorSet;
        animatorSet.setDuration(this.rippleDurationTime);
        this.animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animatorList = new ArrayList<>();
        for (int i2 = 0; i2 < this.rippleAmount; i2++) {
            RippleView rippleView = new RippleView(getContext());
            addView(rippleView, this.rippleParams);
            this.rippleViewList.add(rippleView);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(rippleView, "ScaleX", 1.0f, this.rippleScale);
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(1);
            ofFloat.setStartDelay(this.rippleDelay * i2);
            this.animatorList.add(ofFloat);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(rippleView, "ScaleY", 1.0f, this.rippleScale);
            ofFloat2.setRepeatCount(-1);
            ofFloat2.setRepeatMode(1);
            ofFloat2.setStartDelay(this.rippleDelay * i2);
            this.animatorList.add(ofFloat2);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(rippleView, "Alpha", 1.0f, 0.0f);
            ofFloat3.setRepeatCount(-1);
            ofFloat3.setRepeatMode(1);
            ofFloat3.setStartDelay(this.rippleDelay * i2);
            this.animatorList.add(ofFloat3);
        }
        this.animatorSet.playTogether(this.animatorList);
    }

    public boolean isRippleAnimationRunning() {
        return this.animationRunning;
    }

    public void startRippleAnimation() {
        if (isRippleAnimationRunning()) {
            return;
        }
        Iterator<RippleView> it = this.rippleViewList.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(0);
        }
        this.animatorSet.start();
        this.animationRunning = true;
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            this.animatorSet.end();
            this.animationRunning = false;
        }
    }
}
