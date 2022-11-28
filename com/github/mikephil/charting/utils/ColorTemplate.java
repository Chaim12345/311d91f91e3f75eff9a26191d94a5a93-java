package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public class ColorTemplate {
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb(207, 248, 246), Color.rgb((int) CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, 212, 212), Color.rgb((int) CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, (int) CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, (int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256), Color.rgb(118, (int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, (int) CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384), Color.rgb(42, 109, 130)};
    public static final int[] JOYFUL_COLORS = {Color.rgb(217, 80, (int) CipherSuite.TLS_PSK_WITH_RC4_128_SHA), Color.rgb(254, (int) CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, 7), Color.rgb(254, 247, 120), Color.rgb(106, (int) CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, (int) CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA), Color.rgb(53, (int) CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 209)};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb((int) CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, (int) CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 124), Color.rgb(217, (int) CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, (int) CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256), Color.rgb((int) CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, (int) CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, (int) CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA), Color.rgb((int) CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb((int) CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, (int) CipherSuite.TLS_SM4_CCM_SM3, 0), Color.rgb(106, (int) CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, 31), Color.rgb((int) CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(192, 255, (int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), Color.rgb(255, 247, (int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), Color.rgb(255, 208, (int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), Color.rgb((int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 234, 255), Color.rgb(255, (int) CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, (int) CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};

    public static int colorWithAlpha(int i2, int i3) {
        return (i2 & 16777215) | ((i3 & 255) << 24);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            arrayList.add(Integer.valueOf(resources.getColor(i2)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    public static int getHoloBlue() {
        return Color.rgb(51, (int) CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, 229);
    }

    public static int rgb(String str) {
        int parseLong = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((parseLong >> 16) & 255, (parseLong >> 8) & 255, (parseLong >> 0) & 255);
    }
}
