package org.bouncycastle.cms;
/* loaded from: classes3.dex */
public interface SignerInformationVerifierProvider {
    SignerInformationVerifier get(SignerId signerId);
}
