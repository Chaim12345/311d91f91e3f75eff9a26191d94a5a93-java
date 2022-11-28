package org.bouncycastle.asn1.eac;

import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public class CertificateHolderAuthorization extends ASN1Object {
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;
    public static final int IS = 0;
    public static final int RADG3 = 1;
    public static final int RADG4 = 2;

    /* renamed from: a  reason: collision with root package name */
    ASN1ObjectIdentifier f12778a;

    /* renamed from: b  reason: collision with root package name */
    ASN1ApplicationSpecific f12779b;
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");

    /* renamed from: c  reason: collision with root package name */
    static Hashtable f12776c = new Hashtable();

    /* renamed from: d  reason: collision with root package name */
    static BidirectionalMap f12777d = new BidirectionalMap();

    static {
        new Hashtable();
        f12776c.put(Integers.valueOf(2), "RADG4");
        f12776c.put(Integers.valueOf(1), "RADG3");
        f12777d.put(Integers.valueOf(192), "CVCA");
        f12777d.put(Integers.valueOf(128), "DV_DOMESTIC");
        f12777d.put(Integers.valueOf(64), "DV_FOREIGN");
        f12777d.put(Integers.valueOf(0), "IS");
    }

    public CertificateHolderAuthorization(ASN1ApplicationSpecific aSN1ApplicationSpecific) {
        if (aSN1ApplicationSpecific.getApplicationTag() == 76) {
            setPrivateData(new ASN1InputStream(aSN1ApplicationSpecific.getContents()));
        }
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i2) {
        setOid(aSN1ObjectIdentifier);
        setAccessRights((byte) i2);
    }

    public static int getFlag(String str) {
        Integer num = (Integer) f12777d.getReverse(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Unknown value " + str);
    }

    public static String getRoleDescription(int i2) {
        return (String) f12777d.get(Integers.valueOf(i2));
    }

    private void setAccessRights(byte b2) {
        this.f12779b = new DERApplicationSpecific(19, new byte[]{b2});
    }

    private void setOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f12778a = aSN1ObjectIdentifier;
    }

    private void setPrivateData(ASN1InputStream aSN1InputStream) {
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (!(readObject instanceof ASN1ObjectIdentifier)) {
            throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
        }
        this.f12778a = (ASN1ObjectIdentifier) readObject;
        ASN1Primitive readObject2 = aSN1InputStream.readObject();
        if (!(readObject2 instanceof ASN1ApplicationSpecific)) {
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        this.f12779b = (ASN1ApplicationSpecific) readObject2;
    }

    public int getAccessRights() {
        return this.f12779b.getContents()[0] & 255;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.f12778a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12778a);
        aSN1EncodableVector.add(this.f12779b);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }
}
