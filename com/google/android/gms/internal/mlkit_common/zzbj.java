package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.util.Map;
/* loaded from: classes.dex */
public final /* synthetic */ class zzbj implements ObjectEncoder {
    public static final /* synthetic */ zzbj zza = new zzbj();

    private /* synthetic */ zzbj() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzbk.g((Map.Entry) obj, objectEncoderContext);
    }
}
