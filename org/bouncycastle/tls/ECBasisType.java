package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class ECBasisType {
    public static final short ec_basis_pentanomial = 2;
    public static final short ec_basis_trinomial = 1;

    public static boolean isValid(short s2) {
        return s2 >= 1 && s2 <= 2;
    }
}
