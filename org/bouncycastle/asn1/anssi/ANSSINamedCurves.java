package org.bouncycastle.asn1.anssi;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECParametersHolder;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public class ANSSINamedCurves {

    /* renamed from: a  reason: collision with root package name */
    static X9ECParametersHolder f12750a = new X9ECParametersHolder() { // from class: org.bouncycastle.asn1.anssi.ANSSINamedCurves.1
        @Override // org.bouncycastle.asn1.x9.X9ECParametersHolder
        protected X9ECParameters a() {
            BigInteger fromHex = ANSSINamedCurves.fromHex("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C03");
            BigInteger fromHex2 = ANSSINamedCurves.fromHex("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C00");
            BigInteger fromHex3 = ANSSINamedCurves.fromHex("EE353FCA5428A9300D4ABA754A44C00FDFEC0C9AE4B1A1803075ED967B7BB73F");
            BigInteger fromHex4 = ANSSINamedCurves.fromHex("F1FD178C0B3AD58F10126DE8CE42435B53DC67E140D2BF941FFDD459C6D655E1");
            BigInteger valueOf = BigInteger.valueOf(1L);
            ECCurve configureCurve = ANSSINamedCurves.configureCurve(new ECCurve.Fp(fromHex, fromHex2, fromHex3, fromHex4, valueOf));
            return new X9ECParameters(configureCurve, ANSSINamedCurves.configureBasepoint(configureCurve, "04B6B3D4C356C139EB31183D4749D423958C27D2DCAF98B70164C97A2DD98F5CFF6142E0F7C8B204911F9271F0F3ECEF8C2701C307E8E4C9E183115A1554062CFB"), fromHex4, valueOf, null);
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static final Hashtable f12751b = new Hashtable();

    /* renamed from: c  reason: collision with root package name */
    static final Hashtable f12752c = new Hashtable();

    /* renamed from: d  reason: collision with root package name */
    static final Hashtable f12753d = new Hashtable();

    static {
        d("FRP256v1", ANSSIObjectIdentifiers.FRP256v1, f12750a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X9ECPoint configureBasepoint(ECCurve eCCurve, String str) {
        X9ECPoint x9ECPoint = new X9ECPoint(eCCurve, Hex.decodeStrict(str));
        WNafUtil.configureBasepoint(x9ECPoint.getPoint());
        return x9ECPoint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ECCurve configureCurve(ECCurve eCCurve) {
        return eCCurve;
    }

    static void d(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, X9ECParametersHolder x9ECParametersHolder) {
        f12751b.put(Strings.toLowerCase(str), aSN1ObjectIdentifier);
        f12753d.put(aSN1ObjectIdentifier, str);
        f12752c.put(aSN1ObjectIdentifier, x9ECParametersHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BigInteger fromHex(String str) {
        return new BigInteger(1, Hex.decodeStrict(str));
    }

    public static X9ECParameters getByName(String str) {
        ASN1ObjectIdentifier oid = getOID(str);
        if (oid == null) {
            return null;
        }
        return getByOID(oid);
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParametersHolder x9ECParametersHolder = (X9ECParametersHolder) f12752c.get(aSN1ObjectIdentifier);
        if (x9ECParametersHolder == null) {
            return null;
        }
        return x9ECParametersHolder.getParameters();
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) f12753d.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return f12753d.elements();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) f12751b.get(Strings.toLowerCase(str));
    }
}
