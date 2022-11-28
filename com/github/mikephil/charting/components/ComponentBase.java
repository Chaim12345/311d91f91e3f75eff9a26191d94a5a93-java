package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes.dex */
public abstract class ComponentBase {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f5306a = true;

    /* renamed from: b  reason: collision with root package name */
    protected float f5307b = 5.0f;

    /* renamed from: c  reason: collision with root package name */
    protected float f5308c = 5.0f;

    /* renamed from: d  reason: collision with root package name */
    protected Typeface f5309d = null;

    /* renamed from: e  reason: collision with root package name */
    protected float f5310e = Utils.convertDpToPixel(10.0f);

    /* renamed from: f  reason: collision with root package name */
    protected int f5311f = ViewCompat.MEASURED_STATE_MASK;

    public int getTextColor() {
        return this.f5311f;
    }

    public float getTextSize() {
        return this.f5310e;
    }

    public Typeface getTypeface() {
        return this.f5309d;
    }

    public float getXOffset() {
        return this.f5307b;
    }

    public float getYOffset() {
        return this.f5308c;
    }

    public boolean isEnabled() {
        return this.f5306a;
    }

    public void setEnabled(boolean z) {
        this.f5306a = z;
    }

    public void setTextColor(int i2) {
        this.f5311f = i2;
    }

    public void setTextSize(float f2) {
        if (f2 > 24.0f) {
            f2 = 24.0f;
        }
        if (f2 < 6.0f) {
            f2 = 6.0f;
        }
        this.f5310e = Utils.convertDpToPixel(f2);
    }

    public void setTypeface(Typeface typeface) {
        this.f5309d = typeface;
    }

    public void setXOffset(float f2) {
        this.f5307b = Utils.convertDpToPixel(f2);
    }

    public void setYOffset(float f2) {
        this.f5308c = Utils.convertDpToPixel(f2);
    }
}
