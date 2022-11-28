package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes4.dex */
public interface ERSData {
    byte[] getHash(DigestCalculator digestCalculator);
}
