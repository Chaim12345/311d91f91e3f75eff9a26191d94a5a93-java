package org.bouncycastle.jsse;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import org.bouncycastle.jsse.provider.IDNUtil;
import org.bouncycastle.tls.NameType;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public final class BCSNIHostName extends BCSNIServerName {
    private final String hostName;

    /* loaded from: classes3.dex */
    private static final class BCSNIHostNameMatcher extends BCSNIMatcher {
        private final Pattern pattern;

        BCSNIHostNameMatcher(String str) {
            super(0);
            this.pattern = Pattern.compile(str, 2);
        }

        private String getAsciiHostName(BCSNIServerName bCSNIServerName) {
            return bCSNIServerName instanceof BCSNIHostName ? ((BCSNIHostName) bCSNIServerName).getAsciiName() : BCSNIHostName.normalizeHostName(Strings.fromUTF8ByteArray(bCSNIServerName.getEncoded()));
        }

        @Override // org.bouncycastle.jsse.BCSNIMatcher
        public boolean matches(BCSNIServerName bCSNIServerName) {
            String asciiHostName;
            Objects.requireNonNull(bCSNIServerName, "'serverName' cannot be null");
            if (bCSNIServerName.getType() != 0) {
                return false;
            }
            try {
                asciiHostName = getAsciiHostName(bCSNIServerName);
            } catch (RuntimeException unused) {
            }
            if (this.pattern.matcher(asciiHostName).matches()) {
                return true;
            }
            String unicode = IDNUtil.toUnicode(asciiHostName, 0);
            return !asciiHostName.equals(unicode) && this.pattern.matcher(unicode).matches();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public BCSNIHostName(String str) {
        super(0, Strings.toByteArray(r3));
        String normalizeHostName = normalizeHostName(str);
        this.hostName = normalizeHostName;
    }

    public BCSNIHostName(byte[] bArr) {
        super(0, bArr);
        this.hostName = normalizeHostName(Strings.fromUTF8ByteArray(bArr));
    }

    public static BCSNIMatcher createSNIMatcher(String str) {
        Objects.requireNonNull(str, "'regex' cannot be null");
        return new BCSNIHostNameMatcher(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeHostName(String str) {
        Objects.requireNonNull(str, "'hostName' cannot be null");
        String ascii = IDNUtil.toASCII(str, IDNUtil.USE_STD3_ASCII_RULES);
        if (ascii.length() >= 1) {
            if (ascii.endsWith(".")) {
                throw new IllegalArgumentException("SNI host_name cannot end with a separator");
            }
            return ascii;
        }
        throw new IllegalArgumentException("SNI host_name cannot be empty");
    }

    @Override // org.bouncycastle.jsse.BCSNIServerName
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BCSNIHostName) {
            return this.hostName.equalsIgnoreCase(((BCSNIHostName) obj).hostName);
        }
        return false;
    }

    public String getAsciiName() {
        return this.hostName;
    }

    @Override // org.bouncycastle.jsse.BCSNIServerName
    public int hashCode() {
        return this.hostName.toUpperCase(Locale.ENGLISH).hashCode();
    }

    @Override // org.bouncycastle.jsse.BCSNIServerName
    public String toString() {
        return "{type=" + NameType.getText((short) 0) + ", value=" + this.hostName + "}";
    }
}
