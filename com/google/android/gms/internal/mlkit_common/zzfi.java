package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzfi implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzfi f6205a = new zzfi();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("detectorOptions");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCodes");
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(2);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("totalInitializationMs");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(3);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("loggingInitializationMs");
        zzbe zzbeVar4 = new zzbe();
        zzbeVar4.zza(4);
        zze = builder4.withProperty(zzbeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("otherErrors");
        zzbe zzbeVar5 = new zzbe();
        zzbeVar5.zza(5);
        zzf = builder5.withProperty(zzbeVar5.zzb()).build();
    }

    private zzfi() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzje zzjeVar = (zzje) obj;
        throw null;
    }
}
