package org.bouncycastle.cert.dane;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public class DANEEntrySelectorFactory {
    private final DigestCalculator digestCalculator;

    public DANEEntrySelectorFactory(DigestCalculator digestCalculator) {
        this.digestCalculator = digestCalculator;
    }

    public DANEEntrySelector createSelector(String str) {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(str.substring(0, str.indexOf(64)));
        try {
            OutputStream outputStream = this.digestCalculator.getOutputStream();
            outputStream.write(uTF8ByteArray);
            outputStream.close();
            byte[] digest = this.digestCalculator.getDigest();
            return new DANEEntrySelector(Strings.fromByteArray(Hex.encode(digest)) + "._smimecert." + str.substring(str.indexOf(64) + 1));
        } catch (IOException e2) {
            throw new DANEException("Unable to calculate digest string: " + e2.getMessage(), e2);
        }
    }
}
