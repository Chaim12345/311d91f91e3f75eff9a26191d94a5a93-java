package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzde implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzde f6149a = new zzde();
    private static final FieldDescriptor zzb;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
    }

    private zzde() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzci zzciVar = (zzci) obj;
        throw null;
    }
}
