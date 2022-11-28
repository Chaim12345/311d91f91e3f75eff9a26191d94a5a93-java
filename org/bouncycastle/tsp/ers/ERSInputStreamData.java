package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes4.dex */
public class ERSInputStreamData extends ERSCachingData {
    private final InputStream content;

    public ERSInputStreamData(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("directory not allowed");
        }
        this.content = new FileInputStream(file);
    }

    public ERSInputStreamData(InputStream inputStream) {
        this.content = inputStream;
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] a(DigestCalculator digestCalculator) {
        return ERSUtil.e(digestCalculator, this.content);
    }
}
