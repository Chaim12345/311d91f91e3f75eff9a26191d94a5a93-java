package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TrustedAuthority {

    /* renamed from: a  reason: collision with root package name */
    protected short f14903a;

    /* renamed from: b  reason: collision with root package name */
    protected Object f14904b;

    public TrustedAuthority(short s2, Object obj) {
        if (!b(s2, obj)) {
            throw new IllegalArgumentException("'identifier' is not an instance of the correct type");
        }
        this.f14903a = s2;
        this.f14904b = obj;
    }

    protected static boolean b(short s2, Object obj) {
        if (s2 == 0) {
            return obj == null;
        }
        if (s2 != 1) {
            if (s2 == 2) {
                return obj instanceof X500Name;
            }
            if (s2 != 3) {
                throw new IllegalArgumentException("'identifierType' is an unsupported IdentifierType");
            }
        }
        return c(obj);
    }

    protected static boolean c(Object obj) {
        return (obj instanceof byte[]) && ((byte[]) obj).length == 20;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [org.bouncycastle.asn1.x500.X500Name, org.bouncycastle.asn1.ASN1Object] */
    public static TrustedAuthority parse(InputStream inputStream) {
        byte[] bArr;
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (readUint8 != 0) {
            if (readUint8 != 1) {
                if (readUint8 == 2) {
                    byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream, 1);
                    ?? x500Name = X500Name.getInstance(TlsUtils.readASN1Object(readOpaque16));
                    TlsUtils.requireDEREncoding(x500Name, readOpaque16);
                    bArr = x500Name;
                } else if (readUint8 != 3) {
                    throw new TlsFatalAlert((short) 50);
                }
            }
            bArr = TlsUtils.readFully(20, inputStream);
        } else {
            bArr = null;
        }
        return new TrustedAuthority(readUint8, bArr);
    }

    protected void a(short s2) {
        if (this.f14903a == s2 && b(s2, this.f14904b)) {
            return;
        }
        throw new IllegalStateException("TrustedAuthority is not of type " + IdentifierType.getName(s2));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14903a, outputStream);
        short s2 = this.f14903a;
        if (s2 != 0) {
            if (s2 != 1) {
                if (s2 == 2) {
                    TlsUtils.writeOpaque16(((X500Name) this.f14904b).getEncoded(ASN1Encoding.DER), outputStream);
                    return;
                } else if (s2 != 3) {
                    throw new TlsFatalAlert((short) 80);
                }
            }
            outputStream.write((byte[]) this.f14904b);
        }
    }

    public byte[] getCertSHA1Hash() {
        return Arrays.clone((byte[]) this.f14904b);
    }

    public Object getIdentifier() {
        return this.f14904b;
    }

    public short getIdentifierType() {
        return this.f14903a;
    }

    public byte[] getKeySHA1Hash() {
        return Arrays.clone((byte[]) this.f14904b);
    }

    public X500Name getX509Name() {
        a((short) 2);
        return (X500Name) this.f14904b;
    }
}
