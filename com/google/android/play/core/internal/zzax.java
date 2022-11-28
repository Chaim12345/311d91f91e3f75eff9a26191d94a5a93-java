package com.google.android.play.core.internal;

import android.content.res.AssetManager;
import androidx.annotation.Nullable;
import java.io.File;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public final class zzax {
    private final com.google.android.play.core.splitcompat.zza zza;
    @Nullable
    private XmlPullParser zzb;

    public zzax(com.google.android.play.core.splitcompat.zza zzaVar) {
        this.zza = zzaVar;
    }

    public final long zza() {
        if (this.zzb != null) {
            while (true) {
                int next = this.zzb.next();
                if (next != 2) {
                    if (next == 1) {
                        break;
                    }
                } else if (this.zzb.getName().equals("manifest")) {
                    String attributeValue = this.zzb.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCode");
                    String attributeValue2 = this.zzb.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCodeMajor");
                    if (attributeValue != null) {
                        try {
                            int parseInt = Integer.parseInt(attributeValue);
                            if (attributeValue2 == null) {
                                return parseInt;
                            }
                            try {
                                return (Integer.parseInt(attributeValue2) << 32) | (parseInt & BodyPartID.bodyIdMax);
                            } catch (NumberFormatException e2) {
                                throw new XmlPullParserException(String.format("Couldn't parse versionCodeMajor to int: %s", e2.getMessage()));
                            }
                        } catch (NumberFormatException e3) {
                            throw new XmlPullParserException(String.format("Couldn't parse versionCode to int: %s", e3.getMessage()));
                        }
                    }
                    throw new XmlPullParserException("Manifest entry doesn't contain 'versionCode' attribute.");
                }
            }
            throw new XmlPullParserException("Couldn't find manifest entry at top-level.");
        }
        throw new XmlPullParserException("Manifest file needs to be loaded before parsing.");
    }

    public final void zzb(AssetManager assetManager, File file) {
        this.zzb = assetManager.openXmlResourceParser(com.google.android.play.core.splitcompat.zza.zzc(assetManager, file), "AndroidManifest.xml");
    }
}
