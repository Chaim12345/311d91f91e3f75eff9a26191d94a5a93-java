package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes2.dex */
public final class zzlt extends zzhe {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private static final String[] zzb = {"_err"};
    private SecureRandom zzc;
    private final AtomicLong zzd;
    private int zze;
    private Integer zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlt(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzf = null;
        this.zzd = new AtomicLong(0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean B(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean C(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean D(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) != null) {
                if (receiverInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean E(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return zzat(context, Build.VERSION.SDK_INT >= 24 ? "com.google.android.gms.measurement.AppMeasurementJobService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    static final boolean H(Bundle bundle, int i2) {
        if (bundle.getLong("_err") == 0) {
            bundle.putLong("_err", i2);
            return true;
        }
        return false;
    }

    @VisibleForTesting
    static final boolean I(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static long N(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int length = bArr.length;
        int i2 = 0;
        Preconditions.checkState(length > 0);
        long j2 = 0;
        for (int i3 = length - 1; i3 >= 0 && i3 >= bArr.length - 8; i3--) {
            j2 += (bArr[i3] & 255) << i2;
            i2 += 8;
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageDigest h() {
        MessageDigest messageDigest;
        for (int i2 = 0; i2 < 2; i2++) {
            try {
                messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }

    public static ArrayList zzH(List list) {
        if (list == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzac zzacVar = (zzac) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzacVar.zza);
            bundle.putString("origin", zzacVar.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, zzacVar.zzd);
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzacVar.zzc.zzb);
            zzhg.zzb(bundle, Preconditions.checkNotNull(zzacVar.zzc.zza()));
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, zzacVar.zze);
            String str = zzacVar.zzf;
            if (str != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str);
            }
            zzaw zzawVar = zzacVar.zzg;
            if (zzawVar != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzawVar.zza);
                zzau zzauVar = zzawVar.zzb;
                if (zzauVar != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzauVar.zzc());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, zzacVar.zzh);
            zzaw zzawVar2 = zzacVar.zzi;
            if (zzawVar2 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzawVar2.zza);
                zzau zzauVar2 = zzawVar2.zzb;
                if (zzauVar2 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzauVar2.zzc());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, zzacVar.zzc.zzc);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, zzacVar.zzj);
            zzaw zzawVar3 = zzacVar.zzk;
            if (zzawVar3 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, zzawVar3.zza);
                zzau zzauVar3 = zzawVar3.zzb;
                if (zzauVar3 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzauVar3.zzc());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    @WorkerThread
    public static void zzK(zziw zziwVar, Bundle bundle, boolean z) {
        if (bundle != null && zziwVar != null) {
            if (!bundle.containsKey("_sc") || z) {
                String str = zziwVar.zza;
                if (str != null) {
                    bundle.putString("_sn", str);
                } else {
                    bundle.remove("_sn");
                }
                String str2 = zziwVar.zzb;
                if (str2 != null) {
                    bundle.putString("_sc", str2);
                } else {
                    bundle.remove("_sc");
                }
                bundle.putLong("_si", zziwVar.zzc);
                return;
            }
            z = false;
        }
        if (bundle != null && zziwVar == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    public static boolean zzal(String str) {
        return !zzb[0].equals(str);
    }

    private final int zzaq(String str) {
        if ("_ldl".equals(str)) {
            this.f6809a.zzf();
            return 2048;
        } else if ("_id".equals(str)) {
            this.f6809a.zzf();
            return 256;
        } else if ("_lgclid".equals(str)) {
            this.f6809a.zzf();
            return 100;
        } else {
            this.f6809a.zzf();
            return 36;
        }
    }

    private final Object zzar(int i2, Object obj, boolean z, boolean z2) {
        Parcelable[] parcelableArr;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(true != ((Boolean) obj).booleanValue() ? 0L : 1L);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zzD(obj.toString(), i2, z);
            }
            if (z2 && ((obj instanceof Bundle[]) || (obj instanceof Parcelable[]))) {
                ArrayList arrayList = new ArrayList();
                for (Parcelable parcelable : (Parcelable[]) obj) {
                    if (parcelable instanceof Bundle) {
                        Bundle P = P((Bundle) parcelable);
                        if (!P.isEmpty()) {
                            arrayList.add(P);
                        }
                    }
                }
                return arrayList.toArray(new Bundle[arrayList.size()]);
            }
            return null;
        }
    }

    private static boolean zzas(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String str2 : strArr) {
            if (zzlr.zza(str, str2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzat(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) != null) {
                if (serviceInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public final boolean A(Context context, String str) {
        zzey zzd;
        String str2;
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            zzd = this.f6809a.zzay().zzd();
            str2 = "Package name not found";
            zzd.zzb(str2, e);
            return true;
        } catch (CertificateException e3) {
            e = e3;
            zzd = this.f6809a.zzay().zzd();
            str2 = "Error obtaining certificate";
            zzd.zzb(str2, e);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean F(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            Preconditions.checkNotNull(str);
            return !str.equals(str2);
        } else if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        } else if (isEmpty) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        } else if (TextUtils.isEmpty(str4)) {
            return false;
        } else {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] G(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int J(String str, Object obj) {
        int zzaq;
        String str2;
        if ("_ldl".equals(str)) {
            zzaq = zzaq(str);
            str2 = "user property referrer";
        } else {
            zzaq = zzaq(str);
            str2 = "user property";
        }
        return u(str2, str, zzaq, obj) ? 0 : 7;
    }

    final int K(String str) {
        if (v("event param", str)) {
            if (s("event param", null, null, str)) {
                this.f6809a.zzf();
                return !r("event param", 40, str) ? 3 : 0;
            }
            return 14;
        }
        return 3;
    }

    final int L(String str) {
        if (w("event param", str)) {
            if (s("event param", null, null, str)) {
                this.f6809a.zzf();
                return !r("event param", 40, str) ? 3 : 0;
            }
            return 14;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int M(String str) {
        if (v("user property", str)) {
            if (s("user property", zzhj.zza, null, str)) {
                this.f6809a.zzf();
                return !r("user property", 24, str) ? 6 : 0;
            }
            return 15;
        }
        return 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle O(Uri uri, boolean z, boolean z2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    str = uri.getQueryParameter("utm_campaign");
                    str2 = uri.getQueryParameter("utm_source");
                    str3 = uri.getQueryParameter("utm_medium");
                    str4 = uri.getQueryParameter("gclid");
                    if (z) {
                        str5 = uri.getQueryParameter("utm_id");
                        str6 = uri.getQueryParameter("dclid");
                    } else {
                        str5 = null;
                        str6 = null;
                    }
                    str7 = z2 ? uri.getQueryParameter("srsltid") : null;
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                    str5 = null;
                    str6 = null;
                    str7 = null;
                }
                if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4) && ((!z || (TextUtils.isEmpty(str5) && TextUtils.isEmpty(str6))) && (!z2 || TextUtils.isEmpty(str7)))) {
                    return null;
                }
                Bundle bundle = new Bundle();
                if (!TextUtils.isEmpty(str)) {
                    bundle.putString("campaign", str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putString("source", str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    bundle.putString("medium", str3);
                }
                if (!TextUtils.isEmpty(str4)) {
                    bundle.putString("gclid", str4);
                }
                String queryParameter = uri.getQueryParameter("utm_term");
                if (!TextUtils.isEmpty(queryParameter)) {
                    bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter);
                }
                String queryParameter2 = uri.getQueryParameter("utm_content");
                if (!TextUtils.isEmpty(queryParameter2)) {
                    bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter2);
                }
                String queryParameter3 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                if (!TextUtils.isEmpty(queryParameter3)) {
                    bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter3);
                }
                String queryParameter4 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
                if (!TextUtils.isEmpty(queryParameter4)) {
                    bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter4);
                }
                String queryParameter5 = uri.getQueryParameter("anid");
                if (!TextUtils.isEmpty(queryParameter5)) {
                    bundle.putString("anid", queryParameter5);
                }
                if (z) {
                    if (!TextUtils.isEmpty(str5)) {
                        bundle.putString("campaign_id", str5);
                    }
                    if (!TextUtils.isEmpty(str6)) {
                        bundle.putString("dclid", str6);
                    }
                    String queryParameter6 = uri.getQueryParameter("utm_source_platform");
                    if (!TextUtils.isEmpty(queryParameter6)) {
                        bundle.putString("source_platform", queryParameter6);
                    }
                    String queryParameter7 = uri.getQueryParameter("utm_creative_format");
                    if (!TextUtils.isEmpty(queryParameter7)) {
                        bundle.putString("creative_format", queryParameter7);
                    }
                    String queryParameter8 = uri.getQueryParameter("utm_marketing_tactic");
                    if (!TextUtils.isEmpty(queryParameter8)) {
                        bundle.putString("marketing_tactic", queryParameter8);
                    }
                }
                if (z2 && !TextUtils.isEmpty(str7)) {
                    bundle.putString("srsltid", str7);
                }
                return bundle;
            } catch (UnsupportedOperationException e2) {
                this.f6809a.zzay().zzk().zzb("Install referrer url isn't a hierarchical URI", e2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle P(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object e2 = e(str, bundle.get(str));
                if (e2 == null) {
                    this.f6809a.zzay().zzl().zzb("Param value can't be null", this.f6809a.zzj().e(str));
                } else {
                    o(bundle2, str, e2);
                }
            }
        }
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0108 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle Q(String str, String str2, Bundle bundle, List list, boolean z) {
        int i2;
        int i3;
        String str3;
        boolean zzas = zzas(str2, zzhh.zzd);
        if (bundle != null) {
            Bundle bundle2 = new Bundle(bundle);
            int zzc = this.f6809a.zzf().zzc();
            int i4 = 0;
            for (String str4 : new TreeSet(bundle.keySet())) {
                if (list == null || !list.contains(str4)) {
                    int L = !z ? L(str4) : 0;
                    if (L == 0) {
                        L = K(str4);
                    }
                    i2 = L;
                } else {
                    i2 = 0;
                }
                if (i2 != 0) {
                    k(bundle2, i2, str4, str4, i2 == 3 ? str4 : null);
                    bundle2.remove(str4);
                    i3 = zzc;
                } else {
                    i3 = zzc;
                    int t2 = t(str, str2, str4, bundle.get(str4), bundle2, list, z, zzas);
                    if (t2 == 17) {
                        k(bundle2, 17, str4, str4, Boolean.FALSE);
                    } else if (t2 != 0) {
                        str3 = str4;
                        if (!"_ev".equals(str3)) {
                            k(bundle2, t2, t2 == 21 ? str2 : str3, str3, bundle.get(str3));
                            bundle2.remove(str3);
                        }
                        if (C(str3)) {
                            int i5 = i4 + 1;
                            if (i5 > i3) {
                                this.f6809a.zzay().zze().zzc("Event can't contain more than " + i3 + " params", this.f6809a.zzj().d(str2), this.f6809a.zzj().b(bundle));
                                H(bundle2, 5);
                                bundle2.remove(str3);
                            }
                            i4 = i5;
                        }
                    }
                    str3 = str4;
                    if (C(str3)) {
                    }
                }
                zzc = i3;
            }
            return bundle2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzaw R(String str, String str2, Bundle bundle, String str3, long j2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzh(str2) != 0) {
            this.f6809a.zzay().zzd().zzb("Invalid conditional property event name", this.f6809a.zzj().f(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        Bundle Q = Q(str, str2, bundle2, CollectionUtils.listOf("_o"), true);
        if (z) {
            Q = P(Q);
        }
        Preconditions.checkNotNull(Q);
        return new zzaw(str2, new zzau(Q), str3, j2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    @WorkerThread
    protected final void a() {
        zzg();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                this.f6809a.zzay().zzk().zza("Utils falling back to Random for random id");
            }
        }
        this.zzd.set(nextLong);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    protected final boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object e(String str, Object obj) {
        int i2 = 256;
        if ("_ev".equals(str)) {
            this.f6809a.zzf();
            return zzar(256, obj, true, true);
        }
        if (B(str)) {
            this.f6809a.zzf();
        } else {
            this.f6809a.zzf();
            i2 = 100;
        }
        return zzar(i2, obj, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object f(String str, Object obj) {
        boolean equals = "_ldl".equals(str);
        int zzaq = zzaq(str);
        return equals ? zzar(zzaq, obj, true, false) : zzar(zzaq, obj, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String g() {
        byte[] bArr = new byte[16];
        i().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @EnsuresNonNull({"this.secureRandom"})
    @WorkerThread
    public final SecureRandom i() {
        zzg();
        if (this.zzc == null) {
            this.zzc = new SecureRandom();
        }
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void j(Bundle bundle, long j2) {
        long j3 = bundle.getLong("_et");
        if (j3 != 0) {
            this.f6809a.zzay().zzk().zzb("Params already contained engagement", Long.valueOf(j3));
        } else {
            j3 = 0;
        }
        bundle.putLong("_et", j2 + j3);
    }

    final void k(Bundle bundle, int i2, String str, String str2, Object obj) {
        if (H(bundle, i2)) {
            this.f6809a.zzf();
            bundle.putString("_ev", zzD(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", obj.toString().length());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(Bundle bundle, Bundle bundle2) {
        if (bundle2 == null) {
            return;
        }
        for (String str : bundle2.keySet()) {
            if (!bundle.containsKey(str)) {
                this.f6809a.zzv().o(bundle, str, bundle2.get(str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void m(zzfb zzfbVar, int i2) {
        int i3 = 0;
        for (String str : new TreeSet(zzfbVar.zzd.keySet())) {
            if (C(str) && (i3 = i3 + 1) > i2) {
                this.f6809a.zzay().zze().zzc("Event can't contain more than " + i2 + " params", this.f6809a.zzj().d(zzfbVar.zza), this.f6809a.zzj().b(zzfbVar.zzd));
                H(zzfbVar.zzd, 5);
                zzfbVar.zzd.remove(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void n(zzls zzlsVar, String str, int i2, String str2, String str3, int i3) {
        Bundle bundle = new Bundle();
        H(bundle, i2);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i2 == 6 || i2 == 7 || i2 == 2) {
            bundle.putLong("_el", i3);
        }
        zzlsVar.zza(str, "_err", bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void o(Bundle bundle, String str, Object obj) {
        if (bundle == null) {
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String) {
            bundle.putString(str, String.valueOf(obj));
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            bundle.putParcelableArray(str, (Bundle[]) obj);
        } else if (str != null) {
            this.f6809a.zzay().zzl().zzc("Not putting event parameter. Invalid value type. name, type", this.f6809a.zzj().e(str), obj != null ? obj.getClass().getSimpleName() : null);
        }
    }

    final void p(String str, String str2, String str3, Bundle bundle, List list, boolean z) {
        int i2;
        String str4;
        int t2;
        if (bundle == null) {
            return;
        }
        this.f6809a.zzf();
        int i3 = 0;
        for (String str5 : new TreeSet(bundle.keySet())) {
            if (list == null || !list.contains(str5)) {
                int L = !z ? L(str5) : 0;
                if (L == 0) {
                    L = K(str5);
                }
                i2 = L;
            } else {
                i2 = 0;
            }
            if (i2 != 0) {
                k(bundle, i2, str5, str5, i2 == 3 ? str5 : null);
                bundle.remove(str5);
            } else {
                if (z(bundle.get(str5))) {
                    this.f6809a.zzay().zzl().zzd("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str2, str3, str5);
                    t2 = 22;
                    str4 = str5;
                } else {
                    str4 = str5;
                    t2 = t(str, str2, str5, bundle.get(str5), bundle, list, z, false);
                }
                if (t2 != 0 && !"_ev".equals(str4)) {
                    k(bundle, t2, str4, str4, bundle.get(str4));
                } else if (C(str4) && !zzas(str4, zzhi.zzd) && (i3 = i3 + 1) > 0) {
                    this.f6809a.zzay().zze().zzc("Item cannot contain custom parameters", this.f6809a.zzj().d(str2), this.f6809a.zzj().b(bundle));
                    H(bundle, 23);
                }
                bundle.remove(str4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean q(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (I(str)) {
                return true;
            }
            if (this.f6809a.zzL()) {
                this.f6809a.zzay().zze().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzfa.g(str));
            }
            return false;
        } else if (TextUtils.isEmpty(str2)) {
            if (this.f6809a.zzL()) {
                this.f6809a.zzay().zze().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (I(str2)) {
            return true;
        } else {
            this.f6809a.zzay().zze().zzb("Invalid admob_app_id. Analytics disabled.", zzfa.g(str2));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean r(String str, int i2, String str2) {
        if (str2 == null) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) > i2) {
            this.f6809a.zzay().zze().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i2), str2);
            return false;
        } else {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean s(String str, String[] strArr, String[] strArr2, String str2) {
        if (str2 == null) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr3 = zza;
        for (int i2 = 0; i2 < 3; i2++) {
            if (str2.startsWith(strArr3[i2])) {
                this.f6809a.zzay().zze().zzc("Name starts with reserved prefix. Type, name", str, str2);
                return false;
            }
        }
        if (strArr == null || !zzas(str2, strArr)) {
            return true;
        }
        if (strArr2 == null || !zzas(str2, strArr2)) {
            this.f6809a.zzay().zze().zzc("Name is reserved. Type, name", str, str2);
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00de  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int t(String str, String str2, String str3, Object obj, Bundle bundle, List list, boolean z, boolean z2) {
        int i2;
        int i3;
        zzey zzl;
        String str4;
        String str5;
        Parcelable[] parcelableArr;
        int size;
        zzg();
        if (z(obj)) {
            if (!z2) {
                return 21;
            }
            if (!zzas(str3, zzhi.zzc)) {
                return 20;
            }
            zzke zzt = this.f6809a.zzt();
            zzt.zzg();
            zzt.zza();
            if (zzt.m() && zzt.f6809a.zzv().zzm() < 200900) {
                return 25;
            }
            this.f6809a.zzf();
            boolean z3 = obj instanceof Parcelable[];
            if (z3) {
                size = ((Parcelable[]) obj).length;
            } else if (obj instanceof ArrayList) {
                size = ((ArrayList) obj).size();
            }
            if (size > 200) {
                this.f6809a.zzay().zzl().zzd("Parameter array is too long; discarded. Value kind, name, array length", "param", str3, Integer.valueOf(size));
                this.f6809a.zzf();
                if (z3) {
                    Parcelable[] parcelableArr2 = (Parcelable[]) obj;
                    if (parcelableArr2.length > 200) {
                        bundle.putParcelableArray(str3, (Parcelable[]) Arrays.copyOf(parcelableArr2, 200));
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList.size() > 200) {
                        bundle.putParcelableArrayList(str3, new ArrayList<>(arrayList.subList(0, 200)));
                    }
                }
                i2 = 17;
                if ((this.f6809a.zzf().zzs(str, zzen.zzS) || !B(str2)) && !B(str3)) {
                    this.f6809a.zzf();
                    i3 = 100;
                } else {
                    this.f6809a.zzf();
                    i3 = 256;
                }
                if (u("param", str3, i3, obj)) {
                    if (z2) {
                        if (obj instanceof Bundle) {
                            p(str, str2, str3, (Bundle) obj, list, z);
                        } else if (obj instanceof Parcelable[]) {
                            for (Parcelable parcelable : (Parcelable[]) obj) {
                                if (!(parcelable instanceof Bundle)) {
                                    zzl = this.f6809a.zzay().zzl();
                                    str4 = parcelable.getClass();
                                    str5 = "All Parcelable[] elements must be of type Bundle. Value type, name";
                                    zzl.zzc(str5, str4, str3);
                                    return 4;
                                }
                                p(str, str2, str3, (Bundle) parcelable, list, z);
                            }
                        } else if (!(obj instanceof ArrayList)) {
                            return 4;
                        } else {
                            ArrayList arrayList2 = (ArrayList) obj;
                            int size2 = arrayList2.size();
                            for (int i4 = 0; i4 < size2; i4++) {
                                Object obj2 = arrayList2.get(i4);
                                if (!(obj2 instanceof Bundle)) {
                                    zzl = this.f6809a.zzay().zzl();
                                    str4 = obj2 != null ? obj2.getClass() : "null";
                                    str5 = "All ArrayList elements must be of type Bundle. Value type, name";
                                    zzl.zzc(str5, str4, str3);
                                    return 4;
                                }
                                p(str, str2, str3, (Bundle) obj2, list, z);
                            }
                        }
                        return i2;
                    }
                    return 4;
                }
                return i2;
            }
        }
        i2 = 0;
        if (this.f6809a.zzf().zzs(str, zzen.zzS)) {
        }
        this.f6809a.zzf();
        i3 = 100;
        if (u("param", str3, i3, obj)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean u(String str, String str2, int i2, Object obj) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String obj2 = obj.toString();
            if (obj2.codePointCount(0, obj2.length()) > i2) {
                this.f6809a.zzay().zzl().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(obj2.length()));
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean v(String str, String str2) {
        if (str2 == null) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                if (codePointAt != 95) {
                    this.f6809a.zzay().zze().zzc("Name must start with a letter or _ (underscore). Type, name", str, str2);
                    return false;
                }
                codePointAt = 95;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 != 95 && !Character.isLetterOrDigit(codePointAt2)) {
                    this.f6809a.zzay().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
                charCount += Character.charCount(codePointAt2);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean w(String str, String str2) {
        if (str2 == null) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            this.f6809a.zzay().zze().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                this.f6809a.zzay().zze().zzc("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 != 95 && !Character.isLetterOrDigit(codePointAt2)) {
                    this.f6809a.zzay().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
                charCount += Character.charCount(codePointAt2);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean x(String str) {
        zzg();
        if (Wrappers.packageManager(this.f6809a.zzau()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        this.f6809a.zzay().zzc().zzb("Permission not granted", str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean y(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzl = this.f6809a.zzf().zzl();
        this.f6809a.zzaw();
        return zzl.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean z(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    public final String zzD(String str, int i2, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) > i2) {
            if (z) {
                return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i2))).concat("...");
            }
            return null;
        }
        return str;
    }

    public final URL zzE(long j2, String str, String str2, long j3) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String format = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", 64000L, Integer.valueOf(zzm())), str2, str, Long.valueOf(j3));
            if (str.equals(this.f6809a.zzf().zzm())) {
                format = format.concat("&ddl_test=1");
            }
            return new URL(format);
        } catch (IllegalArgumentException | MalformedURLException e2) {
            this.f6809a.zzay().zzd().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e2.getMessage());
            return null;
        }
    }

    public final void zzP(com.google.android.gms.internal.measurement.zzcf zzcfVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning boolean value to wrapper", e2);
        }
    }

    public final void zzQ(com.google.android.gms.internal.measurement.zzcf zzcfVar, ArrayList arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning bundle list to wrapper", e2);
        }
    }

    public final void zzR(com.google.android.gms.internal.measurement.zzcf zzcfVar, Bundle bundle) {
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning bundle value to wrapper", e2);
        }
    }

    public final void zzS(com.google.android.gms.internal.measurement.zzcf zzcfVar, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning byte array to wrapper", e2);
        }
    }

    public final void zzT(com.google.android.gms.internal.measurement.zzcf zzcfVar, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i2);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning int value to wrapper", e2);
        }
    }

    public final void zzU(com.google.android.gms.internal.measurement.zzcf zzcfVar, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j2);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning long value to wrapper", e2);
        }
    }

    public final void zzV(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6809a.zzay().zzk().zzb("Error returning string value to wrapper", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzh(String str) {
        if (v(NotificationCompat.CATEGORY_EVENT, str)) {
            if (s(NotificationCompat.CATEGORY_EVENT, zzhh.zza, zzhh.zzb, str)) {
                this.f6809a.zzf();
                return !r(NotificationCompat.CATEGORY_EVENT, 40, str) ? 2 : 0;
            }
            return 13;
        }
        return 2;
    }

    @EnsuresNonNull({"this.apkVersion"})
    public final int zzm() {
        if (this.zzf == null) {
            this.zzf = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.f6809a.zzau()) / 1000);
        }
        return this.zzf.intValue();
    }

    public final int zzo(int i2) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this.f6809a.zzau(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final long zzq() {
        long andIncrement;
        long j2;
        if (this.zzd.get() != 0) {
            synchronized (this.zzd) {
                this.zzd.compareAndSet(-1L, 1L);
                andIncrement = this.zzd.getAndIncrement();
            }
            return andIncrement;
        }
        synchronized (this.zzd) {
            long nextLong = new Random(System.nanoTime() ^ this.f6809a.zzav().currentTimeMillis()).nextLong();
            int i2 = this.zze + 1;
            this.zze = i2;
            j2 = nextLong + i2;
        }
        return j2;
    }

    public final long zzr(long j2, long j3) {
        return (j2 + (j3 * AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS)) / 86400000;
    }
}
