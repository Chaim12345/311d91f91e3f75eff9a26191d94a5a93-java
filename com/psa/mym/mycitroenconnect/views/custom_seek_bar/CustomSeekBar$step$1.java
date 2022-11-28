package com.psa.mym.mycitroenconnect.views.custom_seek_bar;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CustomSeekBar$step$1 extends Lambda implements Function0<Integer> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CustomSeekBar f10775a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSeekBar$step$1(CustomSeekBar customSeekBar) {
        super(0);
        this.f10775a = customSeekBar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final Integer invoke() {
        double d2;
        double d3;
        d2 = this.f10775a.mMax;
        d3 = this.f10775a.mMin;
        double d4 = d2 - d3;
        return Integer.valueOf(d4 > 100000.0d ? 1000 : d4 > 1000.0d ? 100 : d4 > 500.0d ? 50 : d4 > 200.0d ? 10 : d4 > 50.0d ? 5 : 1);
    }
}
