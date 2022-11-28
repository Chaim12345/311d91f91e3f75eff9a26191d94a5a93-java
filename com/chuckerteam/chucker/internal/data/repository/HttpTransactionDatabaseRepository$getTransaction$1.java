package com.chuckerteam.chucker.internal.data.repository;

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "old", "new", "", "invoke", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;)Z", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class HttpTransactionDatabaseRepository$getTransaction$1 extends Lambda implements Function2<HttpTransaction, HttpTransaction, Boolean> {
    public static final HttpTransactionDatabaseRepository$getTransaction$1 INSTANCE = new HttpTransactionDatabaseRepository$getTransaction$1();

    HttpTransactionDatabaseRepository$getTransaction$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(HttpTransaction httpTransaction, HttpTransaction httpTransaction2) {
        return Boolean.valueOf(invoke2(httpTransaction, httpTransaction2));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@Nullable HttpTransaction httpTransaction, @Nullable HttpTransaction httpTransaction2) {
        return httpTransaction == null || httpTransaction.hasTheSameContent(httpTransaction2);
    }
}
