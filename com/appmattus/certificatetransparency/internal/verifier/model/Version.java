package com.appmattus.certificatetransparency.internal.verifier.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public enum Version {
    V1(0),
    UNKNOWN_VERSION(256);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int number;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Version forNumber(int i2) {
            Version version;
            Version[] values = Version.values();
            int length = values.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    version = null;
                    break;
                }
                version = values[i3];
                if (version.getNumber() == i2) {
                    break;
                }
                i3++;
            }
            return version == null ? Version.UNKNOWN_VERSION : version;
        }
    }

    Version(int i2) {
        this.number = i2;
    }

    public final int getNumber() {
        return this.number;
    }
}
