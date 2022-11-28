package com.appmattus.certificatetransparency.cache;

import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public interface DiskCache extends DataSource<RawLogListResult> {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        @NotNull
        public static DataSource<RawLogListResult> compose(@NotNull DiskCache diskCache, @NotNull DataSource<RawLogListResult> b2) {
            Intrinsics.checkNotNullParameter(b2, "b");
            return DataSource.DefaultImpls.compose(diskCache, b2);
        }

        @Nullable
        public static Object isValid(@NotNull DiskCache diskCache, @Nullable RawLogListResult rawLogListResult, @NotNull Continuation<? super Boolean> continuation) {
            return DataSource.DefaultImpls.isValid(diskCache, rawLogListResult, continuation);
        }

        @NotNull
        public static <MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull DiskCache diskCache, @NotNull Function1<? super RawLogListResult, ? extends MappedValue> transform) {
            Intrinsics.checkNotNullParameter(transform, "transform");
            return DataSource.DefaultImpls.oneWayTransform(diskCache, transform);
        }

        @NotNull
        public static DataSource<RawLogListResult> plus(@NotNull DiskCache diskCache, @NotNull DataSource<RawLogListResult> b2) {
            Intrinsics.checkNotNullParameter(b2, "b");
            return DataSource.DefaultImpls.plus(diskCache, b2);
        }

        @NotNull
        public static DataSource<RawLogListResult> reuseInflight(@NotNull DiskCache diskCache) {
            return DataSource.DefaultImpls.reuseInflight(diskCache);
        }
    }
}
