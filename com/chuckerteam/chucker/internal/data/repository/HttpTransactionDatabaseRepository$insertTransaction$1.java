package com.chuckerteam.chucker.internal.data.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "Lkotlin/coroutines/Continuation;", "", "continuation", "", "insertTransaction"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.data.repository.HttpTransactionDatabaseRepository", f = "HttpTransactionDatabaseRepository.kt", i = {0, 0}, l = {32}, m = "insertTransaction", n = {"this", "transaction"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class HttpTransactionDatabaseRepository$insertTransaction$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f4857a;

    /* renamed from: b  reason: collision with root package name */
    int f4858b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ HttpTransactionDatabaseRepository f4859c;

    /* renamed from: d  reason: collision with root package name */
    Object f4860d;

    /* renamed from: e  reason: collision with root package name */
    Object f4861e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpTransactionDatabaseRepository$insertTransaction$1(HttpTransactionDatabaseRepository httpTransactionDatabaseRepository, Continuation continuation) {
        super(continuation);
        this.f4859c = httpTransactionDatabaseRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f4857a = obj;
        this.f4858b |= Integer.MIN_VALUE;
        return this.f4859c.insertTransaction(null, this);
    }
}
