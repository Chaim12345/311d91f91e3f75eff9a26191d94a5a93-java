package org.bouncycastle.pqc.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class Utils {

    /* renamed from: a  reason: collision with root package name */
    static final AlgorithmIdentifier f14606a;

    /* renamed from: b  reason: collision with root package name */
    static final AlgorithmIdentifier f14607b;

    /* renamed from: c  reason: collision with root package name */
    static final AlgorithmIdentifier f14608c;

    /* renamed from: d  reason: collision with root package name */
    static final AlgorithmIdentifier f14609d;

    /* renamed from: e  reason: collision with root package name */
    static final AlgorithmIdentifier f14610e;

    /* renamed from: f  reason: collision with root package name */
    static final AlgorithmIdentifier f14611f;

    /* renamed from: g  reason: collision with root package name */
    static final AlgorithmIdentifier f14612g;

    /* renamed from: h  reason: collision with root package name */
    static final AlgorithmIdentifier f14613h;

    /* renamed from: i  reason: collision with root package name */
    static final Map f14614i;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PQCObjectIdentifiers.qTESLA_p_I;
        f14606a = new AlgorithmIdentifier(aSN1ObjectIdentifier);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PQCObjectIdentifiers.qTESLA_p_III;
        f14607b = new AlgorithmIdentifier(aSN1ObjectIdentifier2);
        f14608c = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
        f14609d = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
        f14610e = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        f14611f = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        f14612g = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
        f14613h = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
        HashMap hashMap = new HashMap();
        f14614i = hashMap;
        hashMap.put(aSN1ObjectIdentifier, Integers.valueOf(5));
        hashMap.put(aSN1ObjectIdentifier2, Integers.valueOf(6));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Digest a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier b(int i2) {
        if (i2 != 5) {
            if (i2 == 6) {
                return f14607b;
            }
            throw new IllegalArgumentException("unknown security category: " + i2);
        }
        return f14606a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(AlgorithmIdentifier algorithmIdentifier) {
        return ((Integer) f14614i.get(algorithmIdentifier.getAlgorithm())).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier d(String str) {
        if (str.equals("SHA3-256")) {
            return f14608c;
        }
        if (str.equals(SPHINCSKeyParameters.SHA512_256)) {
            return f14609d;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(SPHINCS256KeyParams sPHINCS256KeyParams) {
        AlgorithmIdentifier treeDigest = sPHINCS256KeyParams.getTreeDigest();
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) f14608c.getAlgorithm())) {
            return "SHA3-256";
        }
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) f14609d.getAlgorithm())) {
            return SPHINCSKeyParameters.SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + treeDigest.getAlgorithm());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier f(String str) {
        if (str.equals("SHA-256")) {
            return f14610e;
        }
        if (str.equals("SHA-512")) {
            return f14611f;
        }
        if (str.equals("SHAKE128")) {
            return f14612g;
        }
        if (str.equals("SHAKE256")) {
            return f14613h;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }

    public static AlgorithmIdentifier getAlgorithmIdentifier(String str) {
        if (str.equals("SHA-1")) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (str.equals("SHA-224")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
        }
        if (str.equals("SHA-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        }
        if (str.equals("SHA-384")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        if (str.equals("SHA-512")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) OIWObjectIdentifiers.idSHA1)) {
            return "SHA-1";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha224)) {
            return "SHA-224";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return "SHA-256";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha384)) {
            return "SHA-384";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return "SHA-512";
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + aSN1ObjectIdentifier);
    }
}
