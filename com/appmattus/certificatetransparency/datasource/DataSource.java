package com.appmattus.certificatetransparency.datasource;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public interface DataSource<Value> {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        @NotNull
        public static <Value> DataSource<Value> compose(@NotNull DataSource<Value> dataSource, @NotNull DataSource<Value> b2) {
            Intrinsics.checkNotNullParameter(b2, "b");
            return new DataSource$compose$1(dataSource, b2);
        }

        @Nullable
        public static <Value> Object isValid(@NotNull DataSource<Value> dataSource, @Nullable Value value, @NotNull Continuation<? super Boolean> continuation) {
            return Boxing.boxBoolean(value != null);
        }

        @NotNull
        public static <Value, MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull DataSource<Value> dataSource, @NotNull Function1<? super Value, ? extends MappedValue> transform) {
            Intrinsics.checkNotNullParameter(transform, "transform");
            return new DataSource$oneWayTransform$1(dataSource, transform);
        }

        @NotNull
        public static <Value> DataSource<Value> plus(@NotNull DataSource<Value> dataSource, @NotNull DataSource<Value> b2) {
            Intrinsics.checkNotNullParameter(b2, "b");
            return dataSource.compose(b2);
        }

        @NotNull
        public static <Value> DataSource<Value> reuseInflight(@NotNull DataSource<Value> dataSource) {
            return new DataSource$reuseInflight$1(dataSource);
        }
    }

    @NotNull
    DataSource<Value> compose(@NotNull DataSource<Value> dataSource);

    @Nullable
    Object get(@NotNull Continuation<? super Value> continuation);

    @Nullable
    Object isValid(@Nullable Value value, @NotNull Continuation<? super Boolean> continuation);

    @NotNull
    <MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull Function1<? super Value, ? extends MappedValue> function1);

    @NotNull
    DataSource<Value> plus(@NotNull DataSource<Value> dataSource);

    @NotNull
    DataSource<Value> reuseInflight();

    @Nullable
    Object set(@NotNull Value value, @NotNull Continuation<? super Unit> continuation);
}
