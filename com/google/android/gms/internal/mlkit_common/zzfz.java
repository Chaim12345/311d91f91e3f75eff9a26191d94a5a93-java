package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzfz implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzfz f6222a = new zzfz();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(3);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(4);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("sdkVersion");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(5);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
    }

    private zzfz() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzjv zzjvVar = (zzjv) obj;
        throw null;
    }
}
