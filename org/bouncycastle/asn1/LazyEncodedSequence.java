package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyEncodedSequence(byte[] bArr) {
        Objects.requireNonNull(bArr, "'encoded' cannot be null");
        this.encoded = bArr;
    }

    private synchronized void force() {
        if (this.encoded != null) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(this.encoded, true);
            try {
                ASN1EncodableVector j2 = aSN1InputStream.j();
                aSN1InputStream.close();
                this.f12709a = j2.c();
                this.encoded = null;
            } catch (IOException e2) {
                throw new ASN1ParsingException("malformed ASN.1: " + e2, e2);
            }
        }
    }

    private synchronized byte[] getContents() {
        return this.encoded;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        byte[] contents = getContents();
        if (contents != null) {
            aSN1OutputStream.m(z, 48, contents);
        } else {
            super.g().c(aSN1OutputStream, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        byte[] contents = getContents();
        return contents != null ? ASN1OutputStream.e(z, contents.length) : super.g().e(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        force();
        return super.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        force();
        return super.g();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1Encodable getObjectAt(int i2) {
        force();
        return super.getObjectAt(i2);
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public Enumeration getObjects() {
        byte[] contents = getContents();
        return contents != null ? new LazyConstructionEnumeration(contents) : super.getObjects();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        force();
        return super.hashCode();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        force();
        return super.iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1BitString j() {
        return ((ASN1Sequence) g()).j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1External k() {
        return ((ASN1Sequence) g()).k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1OctetString l() {
        return ((ASN1Sequence) g()).l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1Set m() {
        return ((ASN1Sequence) g()).m();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public int size() {
        force();
        return super.size();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public ASN1Encodable[] toArray() {
        force();
        return super.toArray();
    }
}
