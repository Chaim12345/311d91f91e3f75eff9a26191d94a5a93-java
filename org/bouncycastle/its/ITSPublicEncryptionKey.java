package org.bouncycastle.its;

import org.bouncycastle.oer.its.PublicEncryptionKey;
import org.bouncycastle.oer.its.SymmAlgorithm;
/* loaded from: classes3.dex */
public class ITSPublicEncryptionKey {

    /* renamed from: a  reason: collision with root package name */
    protected final PublicEncryptionKey f13606a;

    /* loaded from: classes3.dex */
    public enum symmAlgorithm {
        aes128Ccm(SymmAlgorithm.aes128Ccm.intValueExact());
        
        private final int tagValue;

        symmAlgorithm(int i2) {
            this.tagValue = i2;
        }
    }

    public ITSPublicEncryptionKey(PublicEncryptionKey publicEncryptionKey) {
        this.f13606a = publicEncryptionKey;
    }

    public PublicEncryptionKey toASN1Structure() {
        return this.f13606a;
    }
}
