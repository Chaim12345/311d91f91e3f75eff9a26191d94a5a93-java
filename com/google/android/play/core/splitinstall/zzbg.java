package com.google.android.play.core.splitinstall;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
final class zzbg {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static final zzk a(XmlPullParser xmlPullParser, zzi zziVar) {
        String zzb;
        while (xmlPullParser.next() != 1) {
            try {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("splits")) {
                        while (xmlPullParser.next() != 3) {
                            if (xmlPullParser.getEventType() == 2) {
                                if (!xmlPullParser.getName().equals("module") || (zzb = zzb(AppMeasurementSdk.ConditionalUserProperty.NAME, xmlPullParser, zziVar)) == null) {
                                    zzc(xmlPullParser, zziVar);
                                } else {
                                    while (xmlPullParser.next() != 3) {
                                        if (xmlPullParser.getEventType() == 2) {
                                            if (xmlPullParser.getName().equals("language")) {
                                                while (xmlPullParser.next() != 3) {
                                                    if (xmlPullParser.getEventType() == 2) {
                                                        if (xmlPullParser.getName().equals("entry")) {
                                                            String zzb2 = zzb("key", xmlPullParser, zziVar);
                                                            String zzb3 = zzb("split", xmlPullParser, zziVar);
                                                            zzc(xmlPullParser, zziVar);
                                                            if (zzb2 != null && zzb3 != null) {
                                                                zziVar.zza(zzb, zzb2, zzb3);
                                                            }
                                                        } else {
                                                            zzc(xmlPullParser, zziVar);
                                                        }
                                                    }
                                                }
                                            } else {
                                                zzc(xmlPullParser, zziVar);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        zzc(xmlPullParser, zziVar);
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e2) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e2);
                return null;
            }
        }
        return zziVar.zzb();
    }

    @Nullable
    private static final String zzb(String str, XmlPullParser xmlPullParser, zzi zziVar) {
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            if (xmlPullParser.getAttributeName(i2).equals(str)) {
                return xmlPullParser.getAttributeValue(i2);
            }
        }
        return null;
    }

    private static final void zzc(XmlPullParser xmlPullParser, zzi zziVar) {
        int i2 = 1;
        while (i2 != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }
}
