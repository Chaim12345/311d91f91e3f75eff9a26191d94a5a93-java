package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class ExtendedKeyUsage extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    Hashtable f12954a = new Hashtable();

    /* renamed from: b  reason: collision with root package name */
    ASN1Sequence f12955b;

    public ExtendedKeyUsage(Vector vector) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(vector.size());
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            KeyPurposeId keyPurposeId = KeyPurposeId.getInstance(elements.nextElement());
            aSN1EncodableVector.add(keyPurposeId);
            this.f12954a.put(keyPurposeId, keyPurposeId);
        }
        this.f12955b = new DERSequence(aSN1EncodableVector);
    }

    private ExtendedKeyUsage(ASN1Sequence aSN1Sequence) {
        this.f12955b = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) objects.nextElement();
            if (!(aSN1Encodable.toASN1Primitive() instanceof ASN1ObjectIdentifier)) {
                throw new IllegalArgumentException("Only ASN1ObjectIdentifiers allowed in ExtendedKeyUsage.");
            }
            this.f12954a.put(aSN1Encodable, aSN1Encodable);
        }
    }

    public ExtendedKeyUsage(KeyPurposeId keyPurposeId) {
        this.f12955b = new DERSequence(keyPurposeId);
        this.f12954a.put(keyPurposeId, keyPurposeId);
    }

    public ExtendedKeyUsage(KeyPurposeId[] keyPurposeIdArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(keyPurposeIdArr.length);
        for (int i2 = 0; i2 != keyPurposeIdArr.length; i2++) {
            aSN1EncodableVector.add(keyPurposeIdArr[i2]);
            this.f12954a.put(keyPurposeIdArr[i2], keyPurposeIdArr[i2]);
        }
        this.f12955b = new DERSequence(aSN1EncodableVector);
    }

    public static ExtendedKeyUsage fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.extendedKeyUsage));
    }

    public static ExtendedKeyUsage getInstance(Object obj) {
        if (obj instanceof ExtendedKeyUsage) {
            return (ExtendedKeyUsage) obj;
        }
        if (obj != null) {
            return new ExtendedKeyUsage(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ExtendedKeyUsage getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public KeyPurposeId[] getUsages() {
        KeyPurposeId[] keyPurposeIdArr = new KeyPurposeId[this.f12955b.size()];
        Enumeration objects = this.f12955b.getObjects();
        int i2 = 0;
        while (objects.hasMoreElements()) {
            keyPurposeIdArr[i2] = KeyPurposeId.getInstance(objects.nextElement());
            i2++;
        }
        return keyPurposeIdArr;
    }

    public boolean hasKeyPurposeId(KeyPurposeId keyPurposeId) {
        return this.f12954a.get(keyPurposeId) != null;
    }

    public int size() {
        return this.f12954a.size();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12955b;
    }
}
