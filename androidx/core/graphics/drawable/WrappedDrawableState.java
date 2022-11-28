package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class WrappedDrawableState extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    int f2529a;

    /* renamed from: b  reason: collision with root package name */
    Drawable.ConstantState f2530b;

    /* renamed from: c  reason: collision with root package name */
    ColorStateList f2531c;

    /* renamed from: d  reason: collision with root package name */
    PorterDuff.Mode f2532d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WrappedDrawableState(@Nullable WrappedDrawableState wrappedDrawableState) {
        this.f2531c = null;
        this.f2532d = WrappedDrawableApi14.f2526c;
        if (wrappedDrawableState != null) {
            this.f2529a = wrappedDrawableState.f2529a;
            this.f2530b = wrappedDrawableState.f2530b;
            this.f2531c = wrappedDrawableState.f2531c;
            this.f2532d = wrappedDrawableState.f2532d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.f2530b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.f2529a;
        Drawable.ConstantState constantState = this.f2530b;
        return i2 | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable(@Nullable Resources resources) {
        return Build.VERSION.SDK_INT >= 21 ? new WrappedDrawableApi21(this, resources) : new WrappedDrawableApi14(this, resources);
    }
}
