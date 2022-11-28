package com.appmattus.certificatetransparency.internal.serialization;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class CTConstants {
    @NotNull
    public static final CTConstants INSTANCE = new CTConstants();
    public static final int KEY_ID_LENGTH = 32;
    public static final int LOG_ENTRY_TYPE_LENGTH = 2;
    public static final int MAX_CERTIFICATE_LENGTH = 16777215;
    public static final int MAX_EXTENSIONS_LENGTH = 65535;
    public static final int MAX_SIGNATURE_LENGTH = 65535;
    @NotNull
    public static final String POISON_EXTENSION_OID = "1.3.6.1.4.1.11129.2.4.3";
    @NotNull
    public static final String PRECERTIFICATE_SIGNING_OID = "1.3.6.1.4.1.11129.2.4.4";
    @NotNull
    public static final String SCT_CERTIFICATE_OID = "1.3.6.1.4.1.11129.2.4.2";
    public static final int TIMESTAMP_LENGTH = 8;
    public static final int VERSION_LENGTH = 1;

    private CTConstants() {
    }
}
