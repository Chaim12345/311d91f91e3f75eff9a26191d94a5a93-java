package org.bouncycastle.tls;

import java.util.Vector;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public final class ProtocolVersion {
    public static final ProtocolVersion DTLSv10;
    public static final ProtocolVersion DTLSv12;
    public static final ProtocolVersion SSLv3;
    public static final ProtocolVersion TLSv10;
    public static final ProtocolVersion TLSv11;
    public static final ProtocolVersion TLSv12;
    public static final ProtocolVersion TLSv13;

    /* renamed from: a  reason: collision with root package name */
    static final ProtocolVersion f14782a;

    /* renamed from: b  reason: collision with root package name */
    static final ProtocolVersion f14783b;

    /* renamed from: c  reason: collision with root package name */
    static final ProtocolVersion f14784c;

    /* renamed from: d  reason: collision with root package name */
    static final ProtocolVersion f14785d;

    /* renamed from: e  reason: collision with root package name */
    static final ProtocolVersion f14786e;

    /* renamed from: f  reason: collision with root package name */
    static final ProtocolVersion f14787f;

    /* renamed from: g  reason: collision with root package name */
    static final ProtocolVersion f14788g;

    /* renamed from: h  reason: collision with root package name */
    static final ProtocolVersion f14789h;
    private String name;
    private int version;

    static {
        ProtocolVersion protocolVersion = new ProtocolVersion(768, "SSL 3.0");
        SSLv3 = protocolVersion;
        TLSv10 = new ProtocolVersion(769, "TLS 1.0");
        TLSv11 = new ProtocolVersion(770, "TLS 1.1");
        TLSv12 = new ProtocolVersion(771, "TLS 1.2");
        ProtocolVersion protocolVersion2 = new ProtocolVersion(772, "TLS 1.3");
        TLSv13 = protocolVersion2;
        ProtocolVersion protocolVersion3 = new ProtocolVersion(65279, "DTLS 1.0");
        DTLSv10 = protocolVersion3;
        ProtocolVersion protocolVersion4 = new ProtocolVersion(65277, "DTLS 1.2");
        DTLSv12 = protocolVersion4;
        f14782a = protocolVersion3;
        f14783b = protocolVersion;
        f14784c = protocolVersion4;
        f14785d = protocolVersion2;
        f14786e = protocolVersion3;
        f14787f = protocolVersion;
        f14788g = protocolVersion4;
        f14789h = protocolVersion2;
    }

    private ProtocolVersion(int i2, String str) {
        this.version = i2 & 65535;
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(ProtocolVersion protocolVersion) {
        return protocolVersion != null && protocolVersion.isEqualOrLaterVersionOf(f14782a) && protocolVersion.isEqualOrEarlierVersionOf(f14784c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(ProtocolVersion protocolVersion) {
        return protocolVersion != null && protocolVersion.isEqualOrLaterVersionOf(f14786e) && protocolVersion.isEqualOrEarlierVersionOf(f14788g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(ProtocolVersion protocolVersion) {
        int fullVersion;
        return protocolVersion != null && (fullVersion = protocolVersion.getFullVersion()) >= f14783b.getFullVersion() && fullVersion <= f14785d.getFullVersion();
    }

    private static void checkUint8(int i2) {
        if (!TlsUtils.isValidUint8(i2)) {
            throw new IllegalArgumentException("'versionOctet' is not a valid octet");
        }
    }

    public static boolean contains(ProtocolVersion[] protocolVersionArr, ProtocolVersion protocolVersion) {
        if (protocolVersionArr != null && protocolVersion != null) {
            for (ProtocolVersion protocolVersion2 : protocolVersionArr) {
                if (protocolVersion.equals(protocolVersion2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(ProtocolVersion protocolVersion) {
        int fullVersion;
        return protocolVersion != null && (fullVersion = protocolVersion.getFullVersion()) >= f14787f.getFullVersion() && fullVersion <= f14789h.getFullVersion();
    }

    public static ProtocolVersion get(int i2, int i3) {
        String str;
        if (i2 != 3) {
            if (i2 == 254) {
                switch (i3) {
                    case 253:
                        return DTLSv12;
                    case 254:
                        throw new IllegalArgumentException("{0xFE, 0xFE} is a reserved protocol version");
                    case 255:
                        return DTLSv10;
                    default:
                        str = "DTLS";
                        break;
                }
            } else {
                str = "UNKNOWN";
            }
        } else if (i3 == 0) {
            return SSLv3;
        } else {
            if (i3 == 1) {
                return TLSv10;
            }
            if (i3 == 2) {
                return TLSv11;
            }
            if (i3 == 3) {
                return TLSv12;
            }
            if (i3 == 4) {
                return TLSv13;
            }
            str = "TLS";
        }
        return getUnknownVersion(i2, i3, str);
    }

    public static ProtocolVersion getEarliestDTLS(ProtocolVersion[] protocolVersionArr) {
        ProtocolVersion protocolVersion = null;
        if (protocolVersionArr != null) {
            for (ProtocolVersion protocolVersion2 : protocolVersionArr) {
                if (protocolVersion2 != null && protocolVersion2.isDTLS() && (protocolVersion == null || protocolVersion2.getMinorVersion() > protocolVersion.getMinorVersion())) {
                    protocolVersion = protocolVersion2;
                }
            }
        }
        return protocolVersion;
    }

    public static ProtocolVersion getEarliestTLS(ProtocolVersion[] protocolVersionArr) {
        ProtocolVersion protocolVersion = null;
        if (protocolVersionArr != null) {
            for (ProtocolVersion protocolVersion2 : protocolVersionArr) {
                if (protocolVersion2 != null && protocolVersion2.isTLS() && (protocolVersion == null || protocolVersion2.getMinorVersion() < protocolVersion.getMinorVersion())) {
                    protocolVersion = protocolVersion2;
                }
            }
        }
        return protocolVersion;
    }

    public static ProtocolVersion getLatestDTLS(ProtocolVersion[] protocolVersionArr) {
        ProtocolVersion protocolVersion = null;
        if (protocolVersionArr != null) {
            for (ProtocolVersion protocolVersion2 : protocolVersionArr) {
                if (protocolVersion2 != null && protocolVersion2.isDTLS() && (protocolVersion == null || protocolVersion2.getMinorVersion() < protocolVersion.getMinorVersion())) {
                    protocolVersion = protocolVersion2;
                }
            }
        }
        return protocolVersion;
    }

    public static ProtocolVersion getLatestTLS(ProtocolVersion[] protocolVersionArr) {
        ProtocolVersion protocolVersion = null;
        if (protocolVersionArr != null) {
            for (ProtocolVersion protocolVersion2 : protocolVersionArr) {
                if (protocolVersion2 != null && protocolVersion2.isTLS() && (protocolVersion == null || protocolVersion2.getMinorVersion() > protocolVersion.getMinorVersion())) {
                    protocolVersion = protocolVersion2;
                }
            }
        }
        return protocolVersion;
    }

    private static ProtocolVersion getUnknownVersion(int i2, int i3, String str) {
        checkUint8(i2);
        checkUint8(i3);
        int i4 = (i2 << 8) | i3;
        String upperCase = Strings.toUpperCase(Integer.toHexString(65536 | i4).substring(1));
        return new ProtocolVersion(i4, str + " 0x" + upperCase);
    }

    public ProtocolVersion[] downTo(ProtocolVersion protocolVersion) {
        if (isEqualOrLaterVersionOf(protocolVersion)) {
            Vector vector = new Vector();
            vector.addElement(this);
            ProtocolVersion protocolVersion2 = this;
            while (!protocolVersion2.equals(protocolVersion)) {
                protocolVersion2 = protocolVersion2.getPreviousVersion();
                vector.addElement(protocolVersion2);
            }
            ProtocolVersion[] protocolVersionArr = new ProtocolVersion[vector.size()];
            for (int i2 = 0; i2 < vector.size(); i2++) {
                protocolVersionArr[i2] = (ProtocolVersion) vector.elementAt(i2);
            }
            return protocolVersionArr;
        }
        throw new IllegalArgumentException("'min' must be an equal or earlier version of this one");
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ProtocolVersion) && equals((ProtocolVersion) obj));
    }

    public boolean equals(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.version == protocolVersion.version;
    }

    public ProtocolVersion getEquivalentTLSVersion() {
        int majorVersion = getMajorVersion();
        if (majorVersion != 3) {
            if (majorVersion != 254) {
                return null;
            }
            int minorVersion = getMinorVersion();
            if (minorVersion != 253) {
                if (minorVersion != 255) {
                    return null;
                }
                return TLSv11;
            }
            return TLSv12;
        }
        return this;
    }

    public int getFullVersion() {
        return this.version;
    }

    public int getMajorVersion() {
        return this.version >> 8;
    }

    public int getMinorVersion() {
        return this.version & 255;
    }

    public String getName() {
        return this.name;
    }

    public ProtocolVersion getNextVersion() {
        int i2;
        int majorVersion = getMajorVersion();
        int minorVersion = getMinorVersion();
        if (majorVersion != 3) {
            if (majorVersion != 254 || minorVersion == 0) {
                return null;
            }
            if (minorVersion == 255) {
                return DTLSv12;
            }
            i2 = minorVersion - 1;
        } else if (minorVersion == 255) {
            return null;
        } else {
            i2 = minorVersion + 1;
        }
        return get(majorVersion, i2);
    }

    public ProtocolVersion getPreviousVersion() {
        int i2;
        int majorVersion = getMajorVersion();
        int minorVersion = getMinorVersion();
        if (majorVersion != 3) {
            if (majorVersion != 254) {
                return null;
            }
            if (minorVersion == 253) {
                return DTLSv10;
            }
            if (minorVersion == 255) {
                return null;
            }
            i2 = minorVersion + 1;
        } else if (minorVersion == 0) {
            return null;
        } else {
            i2 = minorVersion - 1;
        }
        return get(majorVersion, i2);
    }

    public int hashCode() {
        return this.version;
    }

    public boolean isDTLS() {
        return getMajorVersion() == 254;
    }

    public boolean isEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (protocolVersion == null || getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = getMinorVersion() - protocolVersion.getMinorVersion();
        if (isDTLS()) {
            if (minorVersion <= 0) {
                return false;
            }
        } else if (minorVersion >= 0) {
            return false;
        }
        return true;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (protocolVersion == null || getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = getMinorVersion() - protocolVersion.getMinorVersion();
        if (isDTLS()) {
            if (minorVersion < 0) {
                return false;
            }
        } else if (minorVersion > 0) {
            return false;
        }
        return true;
    }

    public boolean isEqualOrLaterVersionOf(ProtocolVersion protocolVersion) {
        if (protocolVersion == null || getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = getMinorVersion() - protocolVersion.getMinorVersion();
        if (isDTLS()) {
            if (minorVersion > 0) {
                return false;
            }
        } else if (minorVersion < 0) {
            return false;
        }
        return true;
    }

    public boolean isLaterVersionOf(ProtocolVersion protocolVersion) {
        if (protocolVersion == null || getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = getMinorVersion() - protocolVersion.getMinorVersion();
        if (isDTLS()) {
            if (minorVersion >= 0) {
                return false;
            }
        } else if (minorVersion <= 0) {
            return false;
        }
        return true;
    }

    public boolean isSSL() {
        return this == SSLv3;
    }

    public boolean isTLS() {
        return getMajorVersion() == 3;
    }

    public ProtocolVersion[] only() {
        return new ProtocolVersion[]{this};
    }

    public String toString() {
        return this.name;
    }
}
