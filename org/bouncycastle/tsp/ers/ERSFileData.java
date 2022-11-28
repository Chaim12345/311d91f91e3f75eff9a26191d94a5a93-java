package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes4.dex */
public class ERSFileData extends ERSCachingData {
    private final File content;

    public ERSFileData(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("directory not allowed as ERSFileData");
        }
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + " does not exist");
        } else if (file.canRead()) {
            this.content = file;
        } else {
            throw new FileNotFoundException(file.getAbsolutePath() + " is not readable");
        }
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] a(DigestCalculator digestCalculator) {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.content);
            byte[] e2 = ERSUtil.e(digestCalculator, fileInputStream);
            fileInputStream.close();
            return e2;
        } catch (IOException unused) {
            throw new IllegalStateException("unable to process " + this.content.getAbsolutePath());
        }
    }
}
