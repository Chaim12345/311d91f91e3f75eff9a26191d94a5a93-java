package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.utils.LimitedSizeInputStreamKt;
import com.appmattus.certificatetransparency.loglist.LogListService;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListNetworkDataSource implements DataSource<RawLogListResult> {
    @NotNull
    private final LogListService logListService;

    public LogListNetworkDataSource(@NotNull LogListService logListService) {
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        this.logListService = logListService;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<RawLogListResult> compose(@NotNull DataSource<RawLogListResult> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object get(@NotNull Continuation<? super RawLogListResult> continuation) {
        LogListNetworkDataSource$get$1 logListNetworkDataSource$get$1;
        Object coroutine_suspended;
        int i2;
        LogListNetworkDataSource logListNetworkDataSource;
        Object logListSignature;
        byte[] bArr;
        try {
            try {
                if (continuation instanceof LogListNetworkDataSource$get$1) {
                    logListNetworkDataSource$get$1 = (LogListNetworkDataSource$get$1) continuation;
                    int i3 = logListNetworkDataSource$get$1.f4608d;
                    if ((i3 & Integer.MIN_VALUE) != 0) {
                        logListNetworkDataSource$get$1.f4608d = i3 - Integer.MIN_VALUE;
                        Object obj = logListNetworkDataSource$get$1.f4606b;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i2 = logListNetworkDataSource$get$1.f4608d;
                        if (i2 != 0) {
                            ResultKt.throwOnFailure(obj);
                            LogListService logListService = this.logListService;
                            logListNetworkDataSource$get$1.f4605a = this;
                            logListNetworkDataSource$get$1.f4608d = 1;
                            obj = logListService.getLogList(logListNetworkDataSource$get$1);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            logListNetworkDataSource = this;
                        } else if (i2 != 1) {
                            if (i2 == 2) {
                                bArr = (byte[]) logListNetworkDataSource$get$1.f4605a;
                                ResultKt.throwOnFailure(obj);
                                return new RawLogListResult.Success(bArr, (byte[]) obj);
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            logListNetworkDataSource = (LogListNetworkDataSource) logListNetworkDataSource$get$1.f4605a;
                            ResultKt.throwOnFailure(obj);
                        }
                        byte[] bArr2 = (byte[]) obj;
                        LogListService logListService2 = logListNetworkDataSource.logListService;
                        logListNetworkDataSource$get$1.f4605a = bArr2;
                        logListNetworkDataSource$get$1.f4608d = 2;
                        logListSignature = logListService2.getLogListSignature(logListNetworkDataSource$get$1);
                        if (logListSignature != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        bArr = bArr2;
                        obj = logListSignature;
                        return new RawLogListResult.Success(bArr, (byte[]) obj);
                    }
                }
                if (i2 != 0) {
                }
                byte[] bArr22 = (byte[]) obj;
                LogListService logListService22 = logListNetworkDataSource.logListService;
                logListNetworkDataSource$get$1.f4605a = bArr22;
                logListNetworkDataSource$get$1.f4608d = 2;
                logListSignature = logListService22.getLogListSignature(logListNetworkDataSource$get$1);
                if (logListSignature != coroutine_suspended) {
                }
            } catch (Exception e2) {
                return LimitedSizeInputStreamKt.isTooBigException(e2) ? RawLogListSigFailedTooBig.INSTANCE : new RawLogListSigFailedLoadingWithException(e2);
            }
        } catch (Exception e3) {
            return LimitedSizeInputStreamKt.isTooBigException(e3) ? RawLogListJsonFailedTooBig.INSTANCE : new RawLogListJsonFailedLoadingWithException(e3);
        }
        logListNetworkDataSource$get$1 = new LogListNetworkDataSource$get$1(this, continuation);
        Object obj2 = logListNetworkDataSource$get$1.f4606b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = logListNetworkDataSource$get$1.f4608d;
    }

    @Nullable
    /* renamed from: isValid  reason: avoid collision after fix types in other method */
    public Object isValid2(@Nullable RawLogListResult rawLogListResult, @NotNull Continuation<? super Boolean> continuation) {
        return Boxing.boxBoolean(rawLogListResult instanceof RawLogListResult.Success);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    public /* bridge */ /* synthetic */ Object isValid(RawLogListResult rawLogListResult, Continuation continuation) {
        return isValid2(rawLogListResult, (Continuation<? super Boolean>) continuation);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public <MappedValue> DataSource<MappedValue> oneWayTransform(@NotNull Function1<? super RawLogListResult, ? extends MappedValue> function1) {
        return DataSource.DefaultImpls.oneWayTransform(this, function1);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<RawLogListResult> plus(@NotNull DataSource<RawLogListResult> dataSource) {
        return DataSource.DefaultImpls.plus(this, dataSource);
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<RawLogListResult> reuseInflight() {
        return DataSource.DefaultImpls.reuseInflight(this);
    }

    @Nullable
    /* renamed from: set  reason: avoid collision after fix types in other method */
    public Object set2(@NotNull RawLogListResult rawLogListResult, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    public /* bridge */ /* synthetic */ Object set(RawLogListResult rawLogListResult, Continuation continuation) {
        return set2(rawLogListResult, (Continuation<? super Unit>) continuation);
    }
}
