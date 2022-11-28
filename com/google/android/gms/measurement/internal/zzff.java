package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
@WorkerThread
/* loaded from: classes2.dex */
public final class zzff implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzfg f6728a;
    private final URL zzb;
    private final byte[] zzc;
    private final zzfc zzd;
    private final String zze;
    private final Map zzf;

    public zzff(zzfg zzfgVar, String str, URL url, byte[] bArr, Map map, zzfc zzfcVar) {
        this.f6728a = zzfgVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfcVar);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzfcVar;
        this.zze = str;
        this.zzf = map;
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00fc: MOVE  (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:42:0x00fa */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0102: MOVE  (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:44:0x00ff */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0168 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        Throwable th;
        int i2;
        HttpURLConnection httpURLConnection;
        Map map;
        IOException e2;
        int i3;
        Map map2;
        zzfe zzfeVar;
        zzgh zzghVar;
        IOException iOException;
        zzfg zzfgVar;
        URLConnection openConnection;
        int responseCode;
        Map map3;
        Map map4;
        InputStream inputStream;
        this.f6728a.zzax();
        OutputStream outputStream = null;
        try {
            zzfgVar = this.f6728a;
            openConnection = this.zzb.openConnection();
        } catch (IOException e3) {
            e2 = e3;
            i3 = 0;
            httpURLConnection = null;
            map2 = null;
        } catch (Throwable th2) {
            th = th2;
            i2 = 0;
            httpURLConnection = null;
            map = null;
        }
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain HTTP connection");
        }
        httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        zzfgVar.f6809a.zzf();
        httpURLConnection.setConnectTimeout(60000);
        zzfgVar.f6809a.zzf();
        httpURLConnection.setReadTimeout(61000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setDoInput(true);
        try {
            Map map5 = this.zzf;
            if (map5 != null) {
                for (Map.Entry entry : map5.entrySet()) {
                    httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            if (this.zzc != null) {
                byte[] y = this.f6728a.f7018b.zzu().y(this.zzc);
                zzey zzj = this.f6728a.f6809a.zzay().zzj();
                int length = y.length;
                zzj.zzb("Uploading data. size", Integer.valueOf(length));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.addRequestProperty("Content-Encoding", "gzip");
                httpURLConnection.setFixedLengthStreamingMode(length);
                httpURLConnection.connect();
                OutputStream outputStream2 = httpURLConnection.getOutputStream();
                try {
                    outputStream2.write(y);
                    outputStream2.close();
                } catch (IOException e4) {
                    e2 = e4;
                    i3 = 0;
                    map2 = null;
                    outputStream = outputStream2;
                    iOException = e2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e5) {
                            this.f6728a.f6809a.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzfa.g(this.zze), e5);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzghVar = this.f6728a.f6809a.zzaz();
                    zzfeVar = new zzfe(this.zze, this.zzd, i3, iOException, null, map2, null);
                    zzghVar.zzp(zzfeVar);
                } catch (Throwable th3) {
                    th = th3;
                    i2 = 0;
                    map = null;
                    outputStream = outputStream2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e6) {
                            this.f6728a.f6809a.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzfa.g(this.zze), e6);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.f6728a.f6809a.zzaz().zzp(new zzfe(this.zze, this.zzd, i2, null, null, map, null));
                    throw th;
                }
            }
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e7) {
            i3 = 0;
            map2 = null;
            iOException = e7;
        } catch (Throwable th4) {
            i2 = 0;
            map = null;
            th = th4;
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
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        zzghVar = this.f6728a.f6809a.zzaz();
                        zzfeVar = new zzfe(this.zze, this.zzd, responseCode, null, byteArray, headerFields, null);
                    } catch (Throwable th5) {
                        th = th5;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = null;
                }
            } catch (IOException e8) {
                map2 = null;
                iOException = e8;
                i3 = responseCode;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                zzghVar = this.f6728a.f6809a.zzaz();
                zzfeVar = new zzfe(this.zze, this.zzd, i3, iOException, null, map2, null);
                zzghVar.zzp(zzfeVar);
            } catch (Throwable th7) {
                th = th7;
                map = null;
                i2 = responseCode;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                this.f6728a.f6809a.zzaz().zzp(new zzfe(this.zze, this.zzd, i2, null, null, map, null));
                throw th;
            }
        } catch (IOException e9) {
            iOException = e9;
            i3 = responseCode;
            map2 = map4;
            if (outputStream != null) {
            }
            if (httpURLConnection != null) {
            }
            zzghVar = this.f6728a.f6809a.zzaz();
            zzfeVar = new zzfe(this.zze, this.zzd, i3, iOException, null, map2, null);
            zzghVar.zzp(zzfeVar);
        } catch (Throwable th8) {
            th = th8;
            i2 = responseCode;
            map = map3;
            if (outputStream != null) {
            }
            if (httpURLConnection != null) {
            }
            this.f6728a.f6809a.zzaz().zzp(new zzfe(this.zze, this.zzd, i2, null, null, map, null));
            throw th;
        }
        zzghVar.zzp(zzfeVar);
    }
}
