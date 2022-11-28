package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class zzgz {

    /* renamed from: a  reason: collision with root package name */
    static HashMap f6065a;
    private static Object zzl;
    private static boolean zzm;
    public static final Uri zza = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzb = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzc = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzd = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzk = new AtomicBoolean();

    /* renamed from: b  reason: collision with root package name */
    static final HashMap f6066b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    static final HashMap f6067c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    static final HashMap f6068d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    static final HashMap f6069e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    static final String[] f6070f = new String[0];

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzgz.class) {
            if (f6065a == null) {
                zzk.set(false);
                f6065a = new HashMap();
                zzl = new Object();
                zzm = false;
                contentResolver.registerContentObserver(zza, true, new zzgy(null));
            } else if (zzk.getAndSet(false)) {
                f6065a.clear();
                f6066b.clear();
                f6067c.clear();
                f6068d.clear();
                f6069e.clear();
                zzl = new Object();
                zzm = false;
            }
            Object obj = zzl;
            if (f6065a.containsKey(str)) {
                String str3 = (String) f6065a.get(str);
                if (str3 != null) {
                    r3 = str3;
                }
                return r3;
            }
            int length = f6070f.length;
            Cursor query = contentResolver.query(zza, null, null, new String[]{str}, null);
            if (query == null) {
                return null;
            }
            try {
                if (!query.moveToFirst()) {
                    zzc(obj, str, null);
                    return null;
                }
                String string = query.getString(1);
                if (string != null && string.equals(null)) {
                    string = null;
                }
                zzc(obj, str, string);
                return string != null ? string : null;
            } finally {
                query.close();
            }
        }
    }

    private static void zzc(Object obj, String str, String str2) {
        synchronized (zzgz.class) {
            if (obj == zzl) {
                f6065a.put(str, str2);
            }
        }
    }
}
