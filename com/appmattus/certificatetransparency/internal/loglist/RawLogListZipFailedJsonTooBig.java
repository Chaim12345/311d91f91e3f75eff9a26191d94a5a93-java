package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListZipFailedJsonTooBig extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListZipFailedJsonTooBig INSTANCE = new RawLogListZipFailedJsonTooBig();

    private RawLogListZipFailedJsonTooBig() {
    }

    @NotNull
    public String toString() {
        return "log-list.zip contains too large log-list.json file";
    }
}
