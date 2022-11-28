package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzgh implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzgh f6230a = new zzgh();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appId");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appVersion");
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(2);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(3);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzbe zzbeVar4 = new zzbe();
        zzbeVar4.zza(4);
        zze = builder4.withProperty(zzbeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzbe zzbeVar5 = new zzbe();
        zzbeVar5.zza(5);
        zzf = builder5.withProperty(zzbeVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzbe zzbeVar6 = new zzbe();
        zzbeVar6.zza(6);
        zzg = builder6.withProperty(zzbeVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzbe zzbeVar7 = new zzbe();
        zzbeVar7.zza(7);
        zzh = builder7.withProperty(zzbeVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzbe zzbeVar8 = new zzbe();
        zzbeVar8.zza(8);
        zzi = builder8.withProperty(zzbeVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzbe zzbeVar9 = new zzbe();
        zzbeVar9.zza(9);
        zzj = builder9.withProperty(zzbeVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzbe zzbeVar10 = new zzbe();
        zzbeVar10.zza(10);
        zzk = builder10.withProperty(zzbeVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzbe zzbeVar11 = new zzbe();
        zzbeVar11.zza(11);
        zzl = builder11.withProperty(zzbeVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzbe zzbeVar12 = new zzbe();
        zzbeVar12.zza(12);
        zzm = builder12.withProperty(zzbeVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzbe zzbeVar13 = new zzbe();
        zzbeVar13.zza(13);
        zzn = builder13.withProperty(zzbeVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzbe zzbeVar14 = new zzbe();
        zzbeVar14.zza(14);
        zzo = builder14.withProperty(zzbeVar14.zzb()).build();
    }

    private zzgh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzkb zzkbVar = (zzkb) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzkbVar.zzg());
        objectEncoderContext2.add(zzc, zzkbVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzkbVar.zzj());
        objectEncoderContext2.add(zzf, zzkbVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzkbVar.zza());
        objectEncoderContext2.add(zzj, zzkbVar.zzi());
        objectEncoderContext2.add(zzk, zzkbVar.zzb());
        objectEncoderContext2.add(zzl, zzkbVar.zzd());
        objectEncoderContext2.add(zzm, zzkbVar.zzc());
        objectEncoderContext2.add(zzn, zzkbVar.zze());
        objectEncoderContext2.add(zzo, zzkbVar.zzf());
    }
}
