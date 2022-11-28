package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.utils.LimitedSizeInputStreamKt;
import com.appmattus.certificatetransparency.loglist.LogListService;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.util.Iterator;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListZipNetworkDataSource implements DataSource<RawLogListResult> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long LOG_LIST_JSON_MAX_SIZE = 1048576;
    private static final long LOG_LIST_SIG_MAX_SIZE = 512;
    @NotNull
    private final LogListService logListService;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class Data {

        /* loaded from: classes.dex */
        public static final class Invalid extends Data {
            @NotNull
            private final RawLogListResult error;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Invalid(@NotNull RawLogListResult error) {
                super(null);
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final RawLogListResult getError() {
                return this.error;
            }
        }

        /* loaded from: classes.dex */
        public static final class Valid extends Data {
            @NotNull
            private final byte[] bytes;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Valid(@NotNull byte[] bytes) {
                super(null);
                Intrinsics.checkNotNullParameter(bytes, "bytes");
                this.bytes = bytes;
            }

            @NotNull
            public final byte[] getBytes() {
                return this.bytes;
            }
        }

        private Data() {
        }

        public /* synthetic */ Data(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LogListZipNetworkDataSource(@NotNull LogListService logListService) {
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        this.logListService = logListService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ac A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:13:0x0044, B:32:0x00e7, B:40:0x0113, B:24:0x00a5, B:26:0x00ac, B:28:0x00c7, B:33:0x00ea, B:35:0x00f2, B:41:0x0117, B:18:0x006d, B:23:0x008c), top: B:60:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0117 A[Catch: all -> 0x0072, TRY_LEAVE, TryCatch #0 {all -> 0x0072, blocks: (B:13:0x0044, B:32:0x00e7, B:40:0x0113, B:24:0x00a5, B:26:0x00ac, B:28:0x00c7, B:33:0x00ea, B:35:0x00f2, B:41:0x0117, B:18:0x006d, B:23:0x008c), top: B:60:0x0022 }] */
    /* JADX WARN: Type inference failed for: r14v19, types: [T] */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.Closeable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00e5 -> B:32:0x00e7). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00f0 -> B:40:0x0113). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0110 -> B:32:0x00e7). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readZip(byte[] bArr, Continuation<? super RawLogListResult> continuation) {
        LogListZipNetworkDataSource$readZip$1 logListZipNetworkDataSource$readZip$1;
        Object coroutine_suspended;
        int i2;
        Sequence generateSequence;
        Sequence filter;
        LogListZipNetworkDataSource logListZipNetworkDataSource;
        Ref.ObjectRef objectRef;
        ZipInputStream zipInputStream;
        Iterator it;
        Ref.ObjectRef objectRef2;
        ZipInputStream zipInputStream2;
        Ref.ObjectRef objectRef3;
        ?? r14;
        ZipInputStream zipInputStream3;
        try {
            if (continuation instanceof LogListZipNetworkDataSource$readZip$1) {
                logListZipNetworkDataSource$readZip$1 = (LogListZipNetworkDataSource$readZip$1) continuation;
                int i3 = logListZipNetworkDataSource$readZip$1.f4624j;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    logListZipNetworkDataSource$readZip$1.f4624j = i3 - Integer.MIN_VALUE;
                    Object obj = logListZipNetworkDataSource$readZip$1.f4622h;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = logListZipNetworkDataSource$readZip$1.f4624j;
                    if (i2 == 0) {
                        if (i2 == 1) {
                            objectRef2 = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4621g;
                            it = (Iterator) logListZipNetworkDataSource$readZip$1.f4620f;
                            zipInputStream = (ZipInputStream) logListZipNetworkDataSource$readZip$1.f4619e;
                            ?? r6 = (Closeable) logListZipNetworkDataSource$readZip$1.f4618d;
                            objectRef3 = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4617c;
                            objectRef = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4616b;
                            logListZipNetworkDataSource = (LogListZipNetworkDataSource) logListZipNetworkDataSource$readZip$1.f4615a;
                            ResultKt.throwOnFailure(obj);
                            zipInputStream3 = r6;
                            r14 = obj;
                        } else if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            objectRef2 = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4621g;
                            it = (Iterator) logListZipNetworkDataSource$readZip$1.f4620f;
                            zipInputStream = (ZipInputStream) logListZipNetworkDataSource$readZip$1.f4619e;
                            ?? r62 = (Closeable) logListZipNetworkDataSource$readZip$1.f4618d;
                            objectRef3 = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4617c;
                            objectRef = (Ref.ObjectRef) logListZipNetworkDataSource$readZip$1.f4616b;
                            logListZipNetworkDataSource = (LogListZipNetworkDataSource) logListZipNetworkDataSource$readZip$1.f4615a;
                            ResultKt.throwOnFailure(obj);
                            zipInputStream3 = r62;
                            r14 = obj;
                        }
                        objectRef2.element = r14;
                        objectRef2 = objectRef3;
                        ZipInputStream zipInputStream4 = zipInputStream3;
                        zipInputStream.closeEntry();
                        zipInputStream2 = zipInputStream4;
                        if (!it.hasNext()) {
                            String name = new File(((ZipEntry) it.next()).getName()).getName();
                            if (Intrinsics.areEqual(name, "log_list.json")) {
                                RawLogListZipFailedJsonTooBig rawLogListZipFailedJsonTooBig = RawLogListZipFailedJsonTooBig.INSTANCE;
                                LogListZipNetworkDataSource$readZip$2$3$1 logListZipNetworkDataSource$readZip$2$3$1 = new LogListZipNetworkDataSource$readZip$2$3$1(zipInputStream, null);
                                logListZipNetworkDataSource$readZip$1.f4615a = logListZipNetworkDataSource;
                                logListZipNetworkDataSource$readZip$1.f4616b = objectRef;
                                logListZipNetworkDataSource$readZip$1.f4617c = objectRef2;
                                logListZipNetworkDataSource$readZip$1.f4618d = zipInputStream2;
                                logListZipNetworkDataSource$readZip$1.f4619e = zipInputStream;
                                logListZipNetworkDataSource$readZip$1.f4620f = it;
                                logListZipNetworkDataSource$readZip$1.f4621g = objectRef;
                                logListZipNetworkDataSource$readZip$1.f4624j = 1;
                                Object wrap = logListZipNetworkDataSource.wrap(rawLogListZipFailedJsonTooBig, logListZipNetworkDataSource$readZip$2$3$1, logListZipNetworkDataSource$readZip$1);
                                if (wrap == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                objectRef3 = objectRef2;
                                objectRef2 = objectRef;
                                zipInputStream3 = zipInputStream2;
                                r14 = wrap;
                            } else {
                                zipInputStream4 = zipInputStream2;
                                if (Intrinsics.areEqual(name, "log_list.sig")) {
                                    RawLogListZipFailedSigTooBig rawLogListZipFailedSigTooBig = RawLogListZipFailedSigTooBig.INSTANCE;
                                    LogListZipNetworkDataSource$readZip$2$3$2 logListZipNetworkDataSource$readZip$2$3$2 = new LogListZipNetworkDataSource$readZip$2$3$2(zipInputStream, null);
                                    logListZipNetworkDataSource$readZip$1.f4615a = logListZipNetworkDataSource;
                                    logListZipNetworkDataSource$readZip$1.f4616b = objectRef;
                                    logListZipNetworkDataSource$readZip$1.f4617c = objectRef2;
                                    logListZipNetworkDataSource$readZip$1.f4618d = zipInputStream2;
                                    logListZipNetworkDataSource$readZip$1.f4619e = zipInputStream;
                                    logListZipNetworkDataSource$readZip$1.f4620f = it;
                                    logListZipNetworkDataSource$readZip$1.f4621g = objectRef2;
                                    logListZipNetworkDataSource$readZip$1.f4624j = 2;
                                    Object wrap2 = logListZipNetworkDataSource.wrap(rawLogListZipFailedSigTooBig, logListZipNetworkDataSource$readZip$2$3$2, logListZipNetworkDataSource$readZip$1);
                                    if (wrap2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    objectRef3 = objectRef2;
                                    zipInputStream3 = zipInputStream2;
                                    r14 = wrap2;
                                }
                                zipInputStream.closeEntry();
                                zipInputStream2 = zipInputStream4;
                                if (!it.hasNext()) {
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(zipInputStream2, null);
                                    Object obj2 = objectRef.element;
                                    if (obj2 == null) {
                                        return RawLogListZipFailedJsonMissing.INSTANCE;
                                    }
                                    Object obj3 = objectRef2.element;
                                    if (obj3 == null) {
                                        return RawLogListZipFailedSigMissing.INSTANCE;
                                    }
                                    if (obj2 instanceof Data.Invalid) {
                                        Objects.requireNonNull(obj2, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource.Data.Invalid");
                                        return ((Data.Invalid) obj2).getError();
                                    } else if (obj3 instanceof Data.Invalid) {
                                        Objects.requireNonNull(obj3, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource.Data.Invalid");
                                        return ((Data.Invalid) obj3).getError();
                                    } else {
                                        Objects.requireNonNull(obj2, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource.Data.Valid");
                                        byte[] bytes = ((Data.Valid) obj2).getBytes();
                                        Object obj4 = objectRef2.element;
                                        Objects.requireNonNull(obj4, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource.Data.Valid");
                                        return new RawLogListResult.Success(bytes, ((Data.Valid) obj4).getBytes());
                                    }
                                }
                            }
                            objectRef2.element = r14;
                            objectRef2 = objectRef3;
                            ZipInputStream zipInputStream42 = zipInputStream3;
                            zipInputStream.closeEntry();
                            zipInputStream2 = zipInputStream42;
                            if (!it.hasNext()) {
                            }
                        }
                    } else {
                        ResultKt.throwOnFailure(obj);
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                        ZipInputStream zipInputStream5 = new ZipInputStream(new ByteArrayInputStream(bArr));
                        generateSequence = SequencesKt__SequencesKt.generateSequence(new LogListZipNetworkDataSource$readZip$2$1(zipInputStream5));
                        filter = SequencesKt___SequencesKt.filter(generateSequence, LogListZipNetworkDataSource$readZip$2$2.INSTANCE);
                        logListZipNetworkDataSource = this;
                        objectRef = objectRef4;
                        zipInputStream = zipInputStream5;
                        it = filter.iterator();
                        objectRef2 = objectRef5;
                        zipInputStream2 = zipInputStream5;
                        if (!it.hasNext()) {
                        }
                    }
                }
            }
            if (i2 == 0) {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipInputStream2, th);
                throw th2;
            }
        }
        logListZipNetworkDataSource$readZip$1 = new LogListZipNetworkDataSource$readZip$1(this, continuation);
        Object obj5 = logListZipNetworkDataSource$readZip$1.f4622h;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = logListZipNetworkDataSource$readZip$1.f4624j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object wrap(RawLogListResult rawLogListResult, Function1<? super Continuation<? super byte[]>, ? extends Object> function1, Continuation<? super Data> continuation) {
        LogListZipNetworkDataSource$wrap$1 logListZipNetworkDataSource$wrap$1;
        Object coroutine_suspended;
        int i2;
        try {
            if (continuation instanceof LogListZipNetworkDataSource$wrap$1) {
                logListZipNetworkDataSource$wrap$1 = (LogListZipNetworkDataSource$wrap$1) continuation;
                int i3 = logListZipNetworkDataSource$wrap$1.f4633d;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    logListZipNetworkDataSource$wrap$1.f4633d = i3 - Integer.MIN_VALUE;
                    Object obj = logListZipNetworkDataSource$wrap$1.f4631b;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = logListZipNetworkDataSource$wrap$1.f4633d;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        logListZipNetworkDataSource$wrap$1.f4630a = rawLogListResult;
                        logListZipNetworkDataSource$wrap$1.f4633d = 1;
                        obj = function1.invoke(logListZipNetworkDataSource$wrap$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        rawLogListResult = (RawLogListResult) logListZipNetworkDataSource$wrap$1.f4630a;
                        ResultKt.throwOnFailure(obj);
                    }
                    return new Data.Valid((byte[]) obj);
                }
            }
            if (i2 != 0) {
            }
            return new Data.Valid((byte[]) obj);
        } catch (Exception e2) {
            return LimitedSizeInputStreamKt.isTooBigException(e2) ? new Data.Invalid(rawLogListResult) : new Data.Invalid(new RawLogListZipFailedLoadingWithException(e2));
        }
        logListZipNetworkDataSource$wrap$1 = new LogListZipNetworkDataSource$wrap$1(this, continuation);
        Object obj2 = logListZipNetworkDataSource$wrap$1.f4631b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = logListZipNetworkDataSource$wrap$1.f4633d;
    }

    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @NotNull
    public DataSource<RawLogListResult> compose(@NotNull DataSource<RawLogListResult> dataSource) {
        return DataSource.DefaultImpls.compose(this, dataSource);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b  */
    @Override // com.appmattus.certificatetransparency.datasource.DataSource
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object get(@NotNull Continuation<? super RawLogListResult> continuation) {
        LogListZipNetworkDataSource$get$1 logListZipNetworkDataSource$get$1;
        Object coroutine_suspended;
        int i2;
        LogListZipNetworkDataSource logListZipNetworkDataSource;
        Data data;
        if (continuation instanceof LogListZipNetworkDataSource$get$1) {
            logListZipNetworkDataSource$get$1 = (LogListZipNetworkDataSource$get$1) continuation;
            int i3 = logListZipNetworkDataSource$get$1.f4612d;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                logListZipNetworkDataSource$get$1.f4612d = i3 - Integer.MIN_VALUE;
                Object obj = logListZipNetworkDataSource$get$1.f4610b;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = logListZipNetworkDataSource$get$1.f4612d;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    RawLogListZipFailedTooBig rawLogListZipFailedTooBig = RawLogListZipFailedTooBig.INSTANCE;
                    LogListZipNetworkDataSource$get$logListZip$1 logListZipNetworkDataSource$get$logListZip$1 = new LogListZipNetworkDataSource$get$logListZip$1(this, null);
                    logListZipNetworkDataSource$get$1.f4609a = this;
                    logListZipNetworkDataSource$get$1.f4612d = 1;
                    obj = wrap(rawLogListZipFailedTooBig, logListZipNetworkDataSource$get$logListZip$1, logListZipNetworkDataSource$get$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    logListZipNetworkDataSource = this;
                } else if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                } else {
                    logListZipNetworkDataSource = (LogListZipNetworkDataSource) logListZipNetworkDataSource$get$1.f4609a;
                    ResultKt.throwOnFailure(obj);
                }
                data = (Data) obj;
                if (data instanceof Data.Valid) {
                    if (data instanceof Data.Invalid) {
                        return ((Data.Invalid) data).getError();
                    }
                    throw new NoWhenBranchMatchedException();
                }
                byte[] bytes = ((Data.Valid) data).getBytes();
                logListZipNetworkDataSource$get$1.f4609a = null;
                logListZipNetworkDataSource$get$1.f4612d = 2;
                obj = logListZipNetworkDataSource.readZip(bytes, logListZipNetworkDataSource$get$1);
                return obj == coroutine_suspended ? coroutine_suspended : obj;
            }
        }
        logListZipNetworkDataSource$get$1 = new LogListZipNetworkDataSource$get$1(this, continuation);
        Object obj2 = logListZipNetworkDataSource$get$1.f4610b;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = logListZipNetworkDataSource$get$1.f4612d;
        if (i2 != 0) {
        }
        data = (Data) obj2;
        if (data instanceof Data.Valid) {
        }
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
