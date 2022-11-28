package com.chuckerteam.chucker.internal.ui.transaction;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import com.chuckerteam.chucker.internal.support.LiveDataUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\rR\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0012\u0010\rR\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\rR!\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\rR\u001f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0019\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "Landroidx/lifecycle/ViewModel;", "", "switchUrlEncoding", "", "encode", "encodeUrl", "Landroidx/lifecycle/MutableLiveData;", "mutableEncodeUrl", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/LiveData;", "Landroidx/lifecycle/LiveData;", "getEncodeUrl", "()Landroidx/lifecycle/LiveData;", "", "transactionTitle", "getTransactionTitle", "doesUrlRequireEncoding", "getDoesUrlRequireEncoding", "doesRequestBodyRequireEncoding", "getDoesRequestBodyRequireEncoding", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "getTransaction", "formatRequestBody", "getFormatRequestBody", "", "transactionId", "<init>", "(J)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionViewModel extends ViewModel {
    @NotNull
    private final LiveData<Boolean> doesRequestBodyRequireEncoding;
    @NotNull
    private final LiveData<Boolean> doesUrlRequireEncoding;
    @NotNull
    private final LiveData<Boolean> encodeUrl;
    @NotNull
    private final LiveData<Boolean> formatRequestBody;
    private final MutableLiveData<Boolean> mutableEncodeUrl;
    @NotNull
    private final LiveData<HttpTransaction> transaction;
    @NotNull
    private final LiveData<String> transactionTitle;

    public TransactionViewModel(long j2) {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>(Boolean.FALSE);
        this.mutableEncodeUrl = mutableLiveData;
        this.encodeUrl = mutableLiveData;
        RepositoryProvider repositoryProvider = RepositoryProvider.INSTANCE;
        this.transactionTitle = LiveDataUtilsKt.combineLatest(repositoryProvider.transaction().getTransaction(j2), mutableLiveData, TransactionViewModel$transactionTitle$1.INSTANCE);
        LiveData<Boolean> map = Transformations.map(repositoryProvider.transaction().getTransaction(j2), new Function<HttpTransaction, Boolean>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionViewModel$$special$$inlined$map$1
            @Override // androidx.arch.core.util.Function
            public final Boolean apply(HttpTransaction httpTransaction) {
                HttpTransaction httpTransaction2 = httpTransaction;
                return Boolean.valueOf(httpTransaction2 != null ? !Intrinsics.areEqual(httpTransaction2.getFormattedPath(true), httpTransaction2.getFormattedPath(false)) : false);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(this) { transform(it) }");
        this.doesUrlRequireEncoding = map;
        LiveData<Boolean> map2 = Transformations.map(repositoryProvider.transaction().getTransaction(j2), new Function<HttpTransaction, Boolean>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionViewModel$$special$$inlined$map$2
            @Override // androidx.arch.core.util.Function
            public final Boolean apply(HttpTransaction httpTransaction) {
                String requestContentType;
                HttpTransaction httpTransaction2 = httpTransaction;
                return Boolean.valueOf((httpTransaction2 == null || (requestContentType = httpTransaction2.getRequestContentType()) == null) ? false : StringsKt__StringsKt.contains((CharSequence) requestContentType, (CharSequence) "x-www-form-urlencoded", true));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map2, "Transformations.map(this) { transform(it) }");
        this.doesRequestBodyRequireEncoding = map2;
        this.transaction = repositoryProvider.transaction().getTransaction(j2);
        this.formatRequestBody = LiveDataUtilsKt.combineLatest(map2, mutableLiveData, TransactionViewModel$formatRequestBody$1.INSTANCE);
    }

    public final void encodeUrl(boolean z) {
        this.mutableEncodeUrl.setValue(Boolean.valueOf(z));
    }

    @NotNull
    public final LiveData<Boolean> getDoesRequestBodyRequireEncoding() {
        return this.doesRequestBodyRequireEncoding;
    }

    @NotNull
    public final LiveData<Boolean> getDoesUrlRequireEncoding() {
        return this.doesUrlRequireEncoding;
    }

    @NotNull
    public final LiveData<Boolean> getEncodeUrl() {
        return this.encodeUrl;
    }

    @NotNull
    public final LiveData<Boolean> getFormatRequestBody() {
        return this.formatRequestBody;
    }

    @NotNull
    public final LiveData<HttpTransaction> getTransaction() {
        return this.transaction;
    }

    @NotNull
    public final LiveData<String> getTransactionTitle() {
        return this.transactionTitle;
    }

    public final void switchUrlEncoding() {
        Boolean value = this.encodeUrl.getValue();
        Intrinsics.checkNotNull(value);
        encodeUrl(!value.booleanValue());
    }
}
