package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListZipFailedSigTooBig extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListZipFailedSigTooBig INSTANCE = new RawLogListZipFailedSigTooBig();

    private RawLogListZipFailedSigTooBig() {
    }

    @NotNull
    public String toString() {
        return "log-list.zip contains too large log-list.sig file";
    }
}
