package org.bouncycastle.mime;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.bouncycastle.util.Strings;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class LineReader {
    private int lastC = -1;
    private final InputStream src;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LineReader(InputStream inputStream) {
        this.src = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0014 -> B:9:0x001a). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String a() {
        int read;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = this.lastC;
        if (i2 != -1) {
            if (i2 == 13) {
                return "";
            }
            this.lastC = -1;
            if (i2 >= 0 || i2 == 13 || i2 == 10) {
                if (i2 == 13 && (read = this.src.read()) != 10 && read >= 0) {
                    this.lastC = read;
                }
                if (i2 >= 0) {
                    return null;
                }
                return Strings.fromUTF8ByteArray(byteArrayOutputStream.toByteArray());
            }
            byteArrayOutputStream.write(i2);
        }
        i2 = this.src.read();
        if (i2 >= 0) {
        }
        if (i2 == 13) {
            this.lastC = read;
        }
        if (i2 >= 0) {
        }
    }
}
