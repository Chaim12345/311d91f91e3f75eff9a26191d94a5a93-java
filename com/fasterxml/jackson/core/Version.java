package com.fasterxml.jackson.core;

import java.io.Serializable;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes.dex */
public class Version implements Comparable<Version>, Serializable {
    private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected final int f5076a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f5077b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f5078c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f5079d;

    /* renamed from: e  reason: collision with root package name */
    protected final String f5080e;

    /* renamed from: f  reason: collision with root package name */
    protected final String f5081f;

    @Deprecated
    public Version(int i2, int i3, int i4, String str) {
        this(i2, i3, i4, str, null, null);
    }

    public Version(int i2, int i3, int i4, String str, String str2, String str3) {
        this.f5076a = i2;
        this.f5077b = i3;
        this.f5078c = i4;
        this.f5081f = str;
        this.f5079d = str2 == null ? "" : str2;
        this.f5080e = str3 == null ? "" : str3;
    }

    public static Version unknownVersion() {
        return UNKNOWN_VERSION;
    }

    @Override // java.lang.Comparable
    public int compareTo(Version version) {
        if (version == this) {
            return 0;
        }
        int compareTo = this.f5079d.compareTo(version.f5079d);
        if (compareTo == 0) {
            int compareTo2 = this.f5080e.compareTo(version.f5080e);
            if (compareTo2 == 0) {
                int i2 = this.f5076a - version.f5076a;
                if (i2 == 0) {
                    int i3 = this.f5077b - version.f5077b;
                    return i3 == 0 ? this.f5078c - version.f5078c : i3;
                }
                return i2;
            }
            return compareTo2;
        }
        return compareTo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            Version version = (Version) obj;
            return version.f5076a == this.f5076a && version.f5077b == this.f5077b && version.f5078c == this.f5078c && version.f5080e.equals(this.f5080e) && version.f5079d.equals(this.f5079d);
        }
        return false;
    }

    public String getArtifactId() {
        return this.f5080e;
    }

    public String getGroupId() {
        return this.f5079d;
    }

    public int getMajorVersion() {
        return this.f5076a;
    }

    public int getMinorVersion() {
        return this.f5077b;
    }

    public int getPatchLevel() {
        return this.f5078c;
    }

    public int hashCode() {
        return this.f5080e.hashCode() ^ (((this.f5079d.hashCode() + this.f5076a) - this.f5077b) + this.f5078c);
    }

    public boolean isSnapshot() {
        String str = this.f5081f;
        return str != null && str.length() > 0;
    }

    @Deprecated
    public boolean isUknownVersion() {
        return isUnknownVersion();
    }

    public boolean isUnknownVersion() {
        return this == UNKNOWN_VERSION;
    }

    public String toFullString() {
        return this.f5079d + JsonPointer.SEPARATOR + this.f5080e + JsonPointer.SEPARATOR + toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5076a);
        sb.append('.');
        sb.append(this.f5077b);
        sb.append('.');
        sb.append(this.f5078c);
        if (isSnapshot()) {
            sb.append(Soundex.SILENT_MARKER);
            sb.append(this.f5081f);
        }
        return sb.toString();
    }
}
