package org.bouncycastle.pkcs.bc;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.io.MacOutputStream;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
class PKCS12PBEUtils {
    private static Map keySizes = new HashMap();
    private static Set noIvAlgs = new HashSet();
    private static Set desAlgs = new HashSet();

    static {
        Map map = keySizes;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4;
        map.put(aSN1ObjectIdentifier, Integers.valueOf(128));
        Map map2 = keySizes;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4;
        map2.put(aSN1ObjectIdentifier2, Integers.valueOf(40));
        Map map3 = keySizes;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC;
        map3.put(aSN1ObjectIdentifier3, Integers.valueOf(192));
        Map map4 = keySizes;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC;
        map4.put(aSN1ObjectIdentifier4, Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        noIvAlgs.add(aSN1ObjectIdentifier);
        noIvAlgs.add(aSN1ObjectIdentifier2);
        desAlgs.add(aSN1ObjectIdentifier4);
        desAlgs.add(aSN1ObjectIdentifier3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CipherParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ExtendedDigest extendedDigest, int i2, PKCS12PBEParams pKCS12PBEParams, char[] cArr) {
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(extendedDigest);
        pKCS12ParametersGenerator.init(PBEParametersGenerator.PKCS12PasswordToBytes(cArr), pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
        if (e(aSN1ObjectIdentifier)) {
            return pKCS12ParametersGenerator.generateDerivedParameters(d(aSN1ObjectIdentifier));
        }
        CipherParameters generateDerivedParameters = pKCS12ParametersGenerator.generateDerivedParameters(d(aSN1ObjectIdentifier), i2 * 8);
        if (f(aSN1ObjectIdentifier)) {
            DESParameters.setOddParity(((KeyParameter) ((ParametersWithIV) generateDerivedParameters).getParameters()).getKey());
        }
        return generateDerivedParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MacCalculator b(final ASN1ObjectIdentifier aSN1ObjectIdentifier, ExtendedDigest extendedDigest, final PKCS12PBEParams pKCS12PBEParams, final char[] cArr) {
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(extendedDigest);
        pKCS12ParametersGenerator.init(PBEParametersGenerator.PKCS12PasswordToBytes(cArr), pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
        final HMac hMac = new HMac(extendedDigest);
        hMac.init((KeyParameter) pKCS12ParametersGenerator.generateDerivedMacParameters(extendedDigest.getDigestSize() * 8));
        return new MacCalculator() { // from class: org.bouncycastle.pkcs.bc.PKCS12PBEUtils.1
            @Override // org.bouncycastle.operator.MacCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return new AlgorithmIdentifier(ASN1ObjectIdentifier.this, pKCS12PBEParams);
            }

            @Override // org.bouncycastle.operator.MacCalculator
            public GenericKey getKey() {
                return new GenericKey(getAlgorithmIdentifier(), PBEParametersGenerator.PKCS12PasswordToBytes(cArr));
            }

            @Override // org.bouncycastle.operator.MacCalculator
            public byte[] getMac() {
                byte[] bArr = new byte[hMac.getMacSize()];
                hMac.doFinal(bArr, 0);
                return bArr;
            }

            @Override // org.bouncycastle.operator.MacCalculator
            public OutputStream getOutputStream() {
                return new MacOutputStream(hMac);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PaddedBufferedBlockCipher c(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        BlockCipher dESedeEngine;
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC) || aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC)) {
            dESedeEngine = new DESedeEngine();
        } else if (!aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC) && !aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC)) {
            throw new IllegalStateException("unknown algorithm");
        } else {
            dESedeEngine = new RC2Engine();
        }
        return new PaddedBufferedBlockCipher(new CBCBlockCipher(dESedeEngine), new PKCS7Padding());
    }

    static int d(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ((Integer) keySizes.get(aSN1ObjectIdentifier)).intValue();
    }

    static boolean e(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return noIvAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return desAlgs.contains(aSN1ObjectIdentifier);
    }
}
