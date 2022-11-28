package org.bouncycastle.tls;

import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.tls.crypto.DHGroup;
import org.bouncycastle.tls.crypto.DHStandardGroups;
/* loaded from: classes4.dex */
public class DefaultTlsDHGroupVerifier implements TlsDHGroupVerifier {
    private static final Vector DEFAULT_GROUPS = new Vector();
    public static final int DEFAULT_MINIMUM_PRIME_BITS = 2048;

    /* renamed from: a  reason: collision with root package name */
    protected Vector f14755a;

    /* renamed from: b  reason: collision with root package name */
    protected int f14756b;

    static {
        addDefaultGroup(DHStandardGroups.rfc3526_2048);
        addDefaultGroup(DHStandardGroups.rfc3526_3072);
        addDefaultGroup(DHStandardGroups.rfc3526_4096);
        addDefaultGroup(DHStandardGroups.rfc3526_6144);
        addDefaultGroup(DHStandardGroups.rfc3526_8192);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe2048);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe3072);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe4096);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe6144);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe8192);
    }

    public DefaultTlsDHGroupVerifier() {
        this(2048);
    }

    public DefaultTlsDHGroupVerifier(int i2) {
        this(DEFAULT_GROUPS, i2);
    }

    public DefaultTlsDHGroupVerifier(Vector vector, int i2) {
        this.f14755a = new Vector(vector);
        this.f14756b = i2;
    }

    private static void addDefaultGroup(DHGroup dHGroup) {
        DEFAULT_GROUPS.addElement(dHGroup);
    }

    protected boolean a(DHGroup dHGroup, DHGroup dHGroup2) {
        return dHGroup == dHGroup2 || (b(dHGroup.getP(), dHGroup2.getP()) && b(dHGroup.getG(), dHGroup2.getG()));
    }

    @Override // org.bouncycastle.tls.TlsDHGroupVerifier
    public boolean accept(DHGroup dHGroup) {
        return d(dHGroup) && c(dHGroup);
    }

    protected boolean b(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger == bigInteger2 || bigInteger.equals(bigInteger2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean c(DHGroup dHGroup) {
        for (int i2 = 0; i2 < this.f14755a.size(); i2++) {
            if (a(dHGroup, (DHGroup) this.f14755a.elementAt(i2))) {
                return true;
            }
        }
        return false;
    }

    protected boolean d(DHGroup dHGroup) {
        return dHGroup.getP().bitLength() >= getMinimumPrimeBits();
    }

    public int getMinimumPrimeBits() {
        return this.f14756b;
    }
}
