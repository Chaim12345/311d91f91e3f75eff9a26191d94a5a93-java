package androidx.camera.camera2.internal;

import android.media.CamcorderProfile;
/* loaded from: classes.dex */
public final /* synthetic */ class h0 implements CamcorderProfileHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ h0 f865a = new h0();

    private /* synthetic */ h0() {
    }

    @Override // androidx.camera.camera2.internal.CamcorderProfileHelper
    public final boolean hasProfile(int i2, int i3) {
        return CamcorderProfile.hasProfile(i2, i3);
    }
}
