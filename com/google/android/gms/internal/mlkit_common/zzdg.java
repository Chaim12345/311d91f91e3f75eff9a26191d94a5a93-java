package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzdg implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzdg f6151a = new zzdg();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("remoteModelOptions");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("localModelOptions");
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(2);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCodes");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(3);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("modelInitializationMs");
        zzbe zzbeVar4 = new zzbe();
        zzbeVar4.zza(4);
        zze = builder4.withProperty(zzbeVar4.zzb()).build();
    }

    private zzdg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzck zzckVar = (zzck) obj;
        throw null;
    }
}
