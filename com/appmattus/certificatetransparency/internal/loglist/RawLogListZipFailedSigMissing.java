package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListZipFailedSigMissing extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListZipFailedSigMissing INSTANCE = new RawLogListZipFailedSigMissing();

    private RawLogListZipFailedSigMissing() {
    }

    @NotNull
    public String toString() {
        return "log-list.zip missing log-list.sig file";
    }
}
