package com.google.android.gms.maps;
/* loaded from: classes2.dex */
final class zzs extends com.google.android.gms.maps.internal.zzj {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ LocationSource f6669a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(GoogleMap googleMap, LocationSource locationSource) {
        this.f6669a = locationSource;
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void activate(com.google.android.gms.maps.internal.zzaj zzajVar) {
        this.f6669a.activate(new zzl(this, zzajVar));
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void deactivate() {
        this.f6669a.deactivate();
    }
}
