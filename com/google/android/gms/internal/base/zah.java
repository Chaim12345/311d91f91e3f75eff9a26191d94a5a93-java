package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
final class zah extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    int f5844a;

    /* renamed from: b  reason: collision with root package name */
    int f5845b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zah(@Nullable zah zahVar) {
        if (zahVar != null) {
            this.f5844a = zahVar.f5844a;
            this.f5845b = zahVar.f5845b;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f5844a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zai(this);
    }
}
