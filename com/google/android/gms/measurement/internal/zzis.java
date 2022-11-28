package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
@WorkerThread
/* loaded from: classes2.dex */
public final class zzis implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzit f6895a;
    private final URL zzb;
    private final String zzc;
    private final zzgi zzd;

    public zzis(zzit zzitVar, String str, URL url, byte[] bArr, Map map, zzgi zzgiVar, byte[] bArr2) {
        this.f6895a = zzitVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzgiVar);
        this.zzb = url;
        this.zzd = zzgiVar;
        this.zzc = str;
    }

    private final void zzb(final int i2, final Exception exc, final byte[] bArr, final Map map) {
        this.f6895a.f6809a.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzir
            @Override // java.lang.Runnable
            public final void run() {
                zzis.this.a(i2, exc, bArr, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a(int i2, Exception exc, byte[] bArr, Map map) {
        zzgi zzgiVar = this.zzd;
        zzgiVar.zza.c(this.zzc, i2, exc, bArr, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009b  */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.google.android.gms.measurement.internal.zzis] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        HttpURLConnection httpURLConnection;
        ?? r4;
        ?? r42;
        int i2;
        Throwable th;
        IOException e2;
        InputStream inputStream;
        this.f6895a.zzax();
        try {
            zzit zzitVar = this.f6895a;
            URLConnection openConnection = this.zzb.openConnection();
            if (!(openConnection instanceof HttpURLConnection)) {
                throw new IOException("Failed to obtain HTTP connection");
            }
            httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            zzitVar.f6809a.zzf();
            r4 = 60000;
            r42 = 60000;
            httpURLConnection.setConnectTimeout(60000);
            zzitVar.f6809a.zzf();
            httpURLConnection.setReadTimeout(61000);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            try {
                i2 = httpURLConnection.getResponseCode();
            } catch (IOException e3) {
                e = e3;
                r42 = 0;
                IOException iOException = e;
                i2 = 0;
                e2 = iOException;
                if (httpURLConnection != null) {
                }
                zzb(i2, e2, null, r42);
            } catch (Throwable th2) {
                th = th2;
                r4 = 0;
                Throwable th3 = th;
                i2 = 0;
                th = th3;
                if (httpURLConnection != null) {
                }
                zzb(i2, null, null, r4);
                throw th;
            }
            try {
                try {
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                                    inputStream.close();
                                    httpURLConnection.disconnect();
                                    zzb(i2, null, byteArray, headerFields);
                                    return;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        inputStream = null;
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    r42 = 0;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i2, e2, null, r42);
                } catch (Throwable th6) {
                    th = th6;
                    r4 = 0;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i2, null, null, r4);
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                if (httpURLConnection != null) {
                }
                zzb(i2, e2, null, r42);
            } catch (Throwable th7) {
                th = th7;
                if (httpURLConnection != null) {
                }
                zzb(i2, null, null, r4);
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            httpURLConnection = null;
            r42 = 0;
        } catch (Throwable th8) {
            th = th8;
            httpURLConnection = null;
            r4 = 0;
        }
    }
}
