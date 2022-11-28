package org.bouncycastle.tls;

import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.SRP6StandardGroups;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
/* loaded from: classes4.dex */
public class DefaultTlsSRPConfigVerifier implements TlsSRPConfigVerifier {
    private static final Vector DEFAULT_GROUPS;

    /* renamed from: a  reason: collision with root package name */
    protected final Vector f14757a;

    static {
        Vector vector = new Vector();
        DEFAULT_GROUPS = vector;
        vector.addElement(SRP6StandardGroups.rfc5054_1024);
        vector.addElement(SRP6StandardGroups.rfc5054_1536);
        vector.addElement(SRP6StandardGroups.rfc5054_2048);
        vector.addElement(SRP6StandardGroups.rfc5054_3072);
        vector.addElement(SRP6StandardGroups.rfc5054_4096);
        vector.addElement(SRP6StandardGroups.rfc5054_6144);
        vector.addElement(SRP6StandardGroups.rfc5054_8192);
    }

    public DefaultTlsSRPConfigVerifier() {
        this(DEFAULT_GROUPS);
    }

    public DefaultTlsSRPConfigVerifier(Vector vector) {
        this.f14757a = new Vector(vector);
    }

    protected boolean a(TlsSRPConfig tlsSRPConfig, SRP6Group sRP6Group) {
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        return b(explicitNG[0], sRP6Group.getN()) && b(explicitNG[1], sRP6Group.getG());
    }

    @Override // org.bouncycastle.tls.TlsSRPConfigVerifier
    public boolean accept(TlsSRPConfig tlsSRPConfig) {
        for (int i2 = 0; i2 < this.f14757a.size(); i2++) {
            if (a(tlsSRPConfig, (SRP6Group) this.f14757a.elementAt(i2))) {
                return true;
            }
        }
        return false;
    }

    protected boolean b(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger == bigInteger2 || bigInteger.equals(bigInteger2);
    }
}
