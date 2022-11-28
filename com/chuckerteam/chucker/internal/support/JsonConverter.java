package com.chuckerteam.chucker.internal.support;

import com.google.gson.Gson;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0007\u001a\u00020\u00028F@\u0006X\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/JsonConverter;", "", "Lcom/google/gson/Gson;", "instance$delegate", "Lkotlin/Lazy;", "getInstance", "()Lcom/google/gson/Gson;", "instance", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JsonConverter {
    public static final JsonConverter INSTANCE = new JsonConverter();
    @NotNull
    private static final Lazy instance$delegate;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(JsonConverter$instance$2.INSTANCE);
        instance$delegate = lazy;
    }

    private JsonConverter() {
    }

    @NotNull
    public final Gson getInstance() {
        return (Gson) instance$delegate.getValue();
    }
}
