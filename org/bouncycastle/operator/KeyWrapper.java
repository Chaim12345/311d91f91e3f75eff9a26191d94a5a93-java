package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes4.dex */
public interface KeyWrapper {
    byte[] generateWrappedKey(GenericKey genericKey);

    AlgorithmIdentifier getAlgorithmIdentifier();
}
