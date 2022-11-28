package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListZipFailedTooBig extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListZipFailedTooBig INSTANCE = new RawLogListZipFailedTooBig();

    private RawLogListZipFailedTooBig() {
    }

    @NotNull
    public String toString() {
        return "log-list.zip is too large";
    }
}
