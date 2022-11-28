package com.chuckerteam.chucker.internal.ui;

import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import com.chuckerteam.chucker.internal.data.repository.HttpTransactionRepository;
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider;
import com.chuckerteam.chucker.internal.support.NotificationHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR%\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R%\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "getAllTransactions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "searchQuery", "", "updateItemsFilter", "clearTransactions", "clearThrowables", "Landroidx/lifecycle/MutableLiveData;", "currentFilter", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "transactions", "Landroidx/lifecycle/LiveData;", "getTransactions", "()Landroidx/lifecycle/LiveData;", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "throwables", "getThrowables", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class MainViewModel extends ViewModel {
    private final MutableLiveData<String> currentFilter;
    @NotNull
    private final LiveData<List<RecordedThrowableTuple>> throwables;
    @NotNull
    private final LiveData<List<HttpTransactionTuple>> transactions;

    public MainViewModel() {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>("");
        this.currentFilter = mutableLiveData;
        LiveData<List<HttpTransactionTuple>> switchMap = Transformations.switchMap(mutableLiveData, new Function<String, LiveData<List<? extends HttpTransactionTuple>>>() { // from class: com.chuckerteam.chucker.internal.ui.MainViewModel$$special$$inlined$switchMap$1
            /* JADX WARN: Removed duplicated region for block: B:10:0x0016  */
            /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
            @Override // androidx.arch.core.util.Function
            @NotNull
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final LiveData<List<? extends HttpTransactionTuple>> apply(String str) {
                boolean z;
                boolean isBlank;
                String str2 = str;
                HttpTransactionRepository transaction = RepositoryProvider.INSTANCE.transaction();
                if (str2 != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(str2);
                    if (!isBlank) {
                        z = false;
                        return !z ? transaction.getSortedTransactionTuples() : TextUtils.isDigitsOnly(str2) ? transaction.getFilteredTransactionTuples(str2, "") : transaction.getFilteredTransactionTuples("", str2);
                    }
                }
                z = true;
                if (!z) {
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(switchMap, "Transformations.switchMap(this) { transform(it) }");
        this.transactions = switchMap;
        this.throwables = RepositoryProvider.INSTANCE.throwable().getSortedThrowablesTuples();
    }

    public final void clearThrowables() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$clearThrowables$1(null), 3, null);
    }

    public final void clearTransactions() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$clearTransactions$1(null), 3, null);
        NotificationHelper.Companion.clearBuffer();
    }

    @Nullable
    public final Object getAllTransactions(@NotNull Continuation<? super List<HttpTransaction>> continuation) {
        return RepositoryProvider.INSTANCE.transaction().getAllTransactions(continuation);
    }

    @NotNull
    public final LiveData<List<RecordedThrowableTuple>> getThrowables() {
        return this.throwables;
    }

    @NotNull
    public final LiveData<List<HttpTransactionTuple>> getTransactions() {
        return this.transactions;
    }

    public final void updateItemsFilter(@NotNull String searchQuery) {
        Intrinsics.checkNotNullParameter(searchQuery, "searchQuery");
        this.currentFilter.setValue(searchQuery);
    }
}
