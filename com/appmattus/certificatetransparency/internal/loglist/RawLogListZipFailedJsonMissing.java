package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListZipFailedJsonMissing extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListZipFailedJsonMissing INSTANCE = new RawLogListZipFailedJsonMissing();

    private RawLogListZipFailedJsonMissing() {
    }

    @NotNull
    public String toString() {
        return "log-list.zip missing log-list.json file";
    }
}
