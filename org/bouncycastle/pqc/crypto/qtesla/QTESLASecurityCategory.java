package org.bouncycastle.pqc.crypto.qtesla;
/* loaded from: classes4.dex */
public class QTESLASecurityCategory {
    public static final int PROVABLY_SECURE_I = 5;
    public static final int PROVABLY_SECURE_III = 6;

    private QTESLASecurityCategory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2) {
        if (i2 != 5) {
            if (i2 == 6) {
                return 12392;
            }
            throw new IllegalArgumentException("unknown security category: " + i2);
        }
        return 5224;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i2) {
        if (i2 != 5) {
            if (i2 == 6) {
                return 38432;
            }
            throw new IllegalArgumentException("unknown security category: " + i2);
        }
        return 14880;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i2) {
        if (i2 != 5) {
            if (i2 == 6) {
                return 5664;
            }
            throw new IllegalArgumentException("unknown security category: " + i2);
        }
        return 2592;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(int i2) {
        if (i2 == 5 || i2 == 6) {
            return;
        }
        throw new IllegalArgumentException("unknown security category: " + i2);
    }

    public static String getName(int i2) {
        if (i2 != 5) {
            if (i2 == 6) {
                return "qTESLA-p-III";
            }
            throw new IllegalArgumentException("unknown security category: " + i2);
        }
        return "qTESLA-p-I";
    }
}
