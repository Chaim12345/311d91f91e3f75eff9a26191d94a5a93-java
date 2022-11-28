package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;
/* loaded from: classes.dex */
public final class zzca extends zzbm implements zzcc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void beginAdUnitExposure(String str, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeLong(j2);
        c(23, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zze(a2, bundle);
        c(9, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void clearMeasurementEnabled(long j2) {
        Parcel a2 = a();
        a2.writeLong(j2);
        c(43, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void endAdUnitExposure(String str, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeLong(j2);
        c(24, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void generateEventId(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(22, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getAppInstanceId(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(20, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getCachedAppInstanceId(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(19, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getConditionalUserProperties(String str, String str2, zzcf zzcfVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zzf(a2, zzcfVar);
        c(10, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getCurrentScreenClass(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(17, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getCurrentScreenName(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(16, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getGmpAppId(zzcf zzcfVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        c(21, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getMaxUserProperties(String str, zzcf zzcfVar) {
        Parcel a2 = a();
        a2.writeString(str);
        zzbo.zzf(a2, zzcfVar);
        c(6, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getTestFlag(zzcf zzcfVar, int i2) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzcfVar);
        a2.writeInt(i2);
        c(38, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void getUserProperties(String str, String str2, boolean z, zzcf zzcfVar) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zzd(a2, z);
        zzbo.zzf(a2, zzcfVar);
        c(5, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void initForTests(Map map) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void initialize(IObjectWrapper iObjectWrapper, zzcl zzclVar, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        zzbo.zze(a2, zzclVar);
        a2.writeLong(j2);
        c(1, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void isDataCollectionEnabled(zzcf zzcfVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zze(a2, bundle);
        zzbo.zzd(a2, z);
        zzbo.zzd(a2, z2);
        a2.writeLong(j2);
        c(2, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzcf zzcfVar, long j2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void logHealthData(int i2, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        Parcel a2 = a();
        a2.writeInt(5);
        a2.writeString(str);
        zzbo.zzf(a2, iObjectWrapper);
        zzbo.zzf(a2, iObjectWrapper2);
        zzbo.zzf(a2, iObjectWrapper3);
        c(33, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        zzbo.zze(a2, bundle);
        a2.writeLong(j2);
        c(27, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeLong(j2);
        c(28, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeLong(j2);
        c(29, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeLong(j2);
        c(30, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzcf zzcfVar, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        zzbo.zzf(a2, zzcfVar);
        a2.writeLong(j2);
        c(31, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeLong(j2);
        c(25, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeLong(j2);
        c(26, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void performAction(Bundle bundle, zzcf zzcfVar, long j2) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        zzbo.zzf(a2, zzcfVar);
        a2.writeLong(j2);
        c(32, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void registerOnMeasurementEventListener(zzci zzciVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzciVar);
        c(35, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void resetAnalyticsData(long j2) {
        Parcel a2 = a();
        a2.writeLong(j2);
        c(12, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setConditionalUserProperty(Bundle bundle, long j2) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        a2.writeLong(j2);
        c(8, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setConsent(Bundle bundle, long j2) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        a2.writeLong(j2);
        c(44, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setConsentThirdParty(Bundle bundle, long j2) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        a2.writeLong(j2);
        c(45, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j2) {
        Parcel a2 = a();
        zzbo.zzf(a2, iObjectWrapper);
        a2.writeString(str);
        a2.writeString(str2);
        a2.writeLong(j2);
        c(15, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setDataCollectionEnabled(boolean z) {
        Parcel a2 = a();
        zzbo.zzd(a2, z);
        c(39, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setDefaultEventParameters(Bundle bundle) {
        Parcel a2 = a();
        zzbo.zze(a2, bundle);
        c(42, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setEventInterceptor(zzci zzciVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzciVar);
        c(34, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setInstanceIdProvider(zzck zzckVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setMeasurementEnabled(boolean z, long j2) {
        Parcel a2 = a();
        zzbo.zzd(a2, z);
        a2.writeLong(j2);
        c(11, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setMinimumSessionDuration(long j2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setSessionTimeoutDuration(long j2) {
        Parcel a2 = a();
        a2.writeLong(j2);
        c(14, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setUserId(String str, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeLong(j2);
        c(7, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j2) {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeString(str2);
        zzbo.zzf(a2, iObjectWrapper);
        zzbo.zzd(a2, z);
        a2.writeLong(j2);
        c(4, a2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public final void unregisterOnMeasurementEventListener(zzci zzciVar) {
        Parcel a2 = a();
        zzbo.zzf(a2, zzciVar);
        c(36, a2);
    }
}
