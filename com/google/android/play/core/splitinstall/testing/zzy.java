package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes2.dex */
public final class zzy {
    private static final zzag zza = new zzag("LocalTestingConfigParser");
    private final XmlPullParser zzb;
    private final zzs zzc = zzt.a();

    zzy(XmlPullParser xmlPullParser) {
        this.zzb = xmlPullParser;
    }

    public static zzt zza(File file) {
        File file2 = new File(file, "local_testing_config.xml");
        if (file2.exists()) {
            try {
                FileReader fileReader = new FileReader(file2);
                try {
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setInput(fileReader);
                    final zzy zzyVar = new zzy(newPullParser);
                    zzyVar.zze("local-testing-config", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzu
                        @Override // com.google.android.play.core.splitinstall.testing.zzx
                        public final void zza() {
                            zzy.this.a();
                        }
                    });
                    zzt e2 = zzyVar.zzc.e();
                    fileReader.close();
                    return e2;
                } catch (Throwable th) {
                    try {
                        fileReader.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (IOException | RuntimeException | XmlPullParserException e3) {
                zza.zze("%s can not be parsed, using default. Error: %s", "local_testing_config.xml", e3.getMessage());
                return zzt.zza;
            }
        }
        return zzt.zza;
    }

    public static /* synthetic */ void zzb(final zzy zzyVar) {
        for (int i2 = 0; i2 < zzyVar.zzb.getAttributeCount(); i2++) {
            if ("defaultErrorCode".equals(zzyVar.zzb.getAttributeName(i2))) {
                zzyVar.zzc.a(com.google.android.play.core.splitinstall.model.zza.zza(zzyVar.zzb.getAttributeValue(i2)));
            }
        }
        zzyVar.zze("split-install-error", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzw
            @Override // com.google.android.play.core.splitinstall.testing.zzx
            public final void zza() {
                zzy.zzc(zzy.this);
            }
        });
    }

    public static /* synthetic */ void zzc(zzy zzyVar) {
        String str = null;
        String str2 = null;
        for (int i2 = 0; i2 < zzyVar.zzb.getAttributeCount(); i2++) {
            if ("module".equals(zzyVar.zzb.getAttributeName(i2))) {
                str = zzyVar.zzb.getAttributeValue(i2);
            }
            if ("errorCode".equals(zzyVar.zzb.getAttributeName(i2))) {
                str2 = zzyVar.zzb.getAttributeValue(i2);
            }
        }
        if (str == null || str2 == null) {
            throw new XmlPullParserException(String.format("'%s' element does not contain 'module'/'errorCode' attributes.", "split-install-error"), zzyVar.zzb, null);
        }
        zzyVar.zzc.d().put(str, Integer.valueOf(com.google.android.play.core.splitinstall.model.zza.zza(str2)));
        do {
        } while (zzyVar.zzb.next() != 3);
    }

    private final void zze(String str, zzx zzxVar) {
        while (true) {
            int next = this.zzb.next();
            if (next == 3 || next == 1) {
                return;
            }
            if (this.zzb.getEventType() == 2) {
                if (!this.zzb.getName().equals(str)) {
                    throw new XmlPullParserException(String.format("Expected '%s' tag but found '%s'.", str, this.zzb.getName()), this.zzb, null);
                }
                zzxVar.zza();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void a() {
        zze("split-install-errors", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzv
            @Override // com.google.android.play.core.splitinstall.testing.zzx
            public final void zza() {
                zzy.zzb(zzy.this);
            }
        });
    }
}
