package com.facebook.stetho.common.android;

import android.content.res.Resources;
import com.facebook.stetho.common.LogUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public class ResourcesUtil {
    private ResourcesUtil() {
    }

    private static String getFallbackIdString(int i2) {
        return "#" + Integer.toHexString(i2);
    }

    public static String getIdString(@Nullable Resources resources, int i2) {
        String str;
        if (resources == null) {
            return getFallbackIdString(i2);
        }
        String str2 = "";
        if (getResourcePackageId(i2) != 127) {
            str2 = resources.getResourcePackageName(i2);
            str = ":";
        } else {
            str = "";
        }
        String resourceTypeName = resources.getResourceTypeName(i2);
        String resourceEntryName = resources.getResourceEntryName(i2);
        StringBuilder sb = new StringBuilder(str2.length() + 1 + str.length() + resourceTypeName.length() + 1 + resourceEntryName.length());
        sb.append("@");
        sb.append(str2);
        sb.append(str);
        sb.append(resourceTypeName);
        sb.append("/");
        sb.append(resourceEntryName);
        return sb.toString();
    }

    @Nonnull
    public static String getIdStringQuietly(Object obj, @Nullable Resources resources, int i2) {
        try {
            return getIdString(resources, i2);
        } catch (Resources.NotFoundException unused) {
            String fallbackIdString = getFallbackIdString(i2);
            LogUtil.w("Unknown identifier encountered on " + obj + ": " + fallbackIdString);
            return fallbackIdString;
        }
    }

    private static int getResourcePackageId(int i2) {
        return (i2 >>> 24) & 255;
    }
}
