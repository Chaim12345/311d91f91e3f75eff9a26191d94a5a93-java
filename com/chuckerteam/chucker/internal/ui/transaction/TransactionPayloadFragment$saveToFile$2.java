package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$saveToFile$2", f = "TransactionPayloadFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TransactionPayloadFragment$saveToFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f5011a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ TransactionPayloadFragment f5012b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Uri f5013c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ PayloadType f5014d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ HttpTransaction f5015e;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionPayloadFragment$saveToFile$2(TransactionPayloadFragment transactionPayloadFragment, Uri uri, PayloadType payloadType, HttpTransaction httpTransaction, Continuation continuation) {
        super(2, continuation);
        this.f5012b = transactionPayloadFragment;
        this.f5013c = uri;
        this.f5014d = payloadType;
        this.f5015e = httpTransaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        TransactionPayloadFragment$saveToFile$2 transactionPayloadFragment$saveToFile$2 = new TransactionPayloadFragment$saveToFile$2(this.f5012b, this.f5013c, this.f5014d, this.f5015e, completion);
        transactionPayloadFragment$saveToFile$2.p$ = (CoroutineScope) obj;
        return transactionPayloadFragment$saveToFile$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((TransactionPayloadFragment$saveToFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0065, code lost:
        if (r3 != null) goto L17;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Long boxLong;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f5011a == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                Context requireContext = this.f5012b.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                ParcelFileDescriptor it = requireContext.getContentResolver().openFileDescriptor(this.f5013c, "w");
                if (it != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    FileOutputStream fileOutputStream = new FileOutputStream(it.getFileDescriptor());
                    int i2 = TransactionPayloadFragment.WhenMappings.$EnumSwitchMapping$1[this.f5014d.ordinal()];
                    if (i2 == 1) {
                        String requestBody = this.f5015e.getRequestBody();
                        if (requestBody != null) {
                            byte[] bytes = requestBody.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                            boxLong = Boxing.boxLong(ByteStreamsKt.copyTo$default(new ByteArrayInputStream(bytes), fileOutputStream, 0, 2, null));
                            if (boxLong != null) {
                            }
                        }
                        throw new IOException("Transaction not ready");
                    } else if (i2 == 2) {
                        String responseBody = this.f5015e.getResponseBody();
                        if (responseBody != null) {
                            byte[] bytes2 = responseBody.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes2, "(this as java.lang.String).getBytes(charset)");
                            boxLong = Boxing.boxLong(ByteStreamsKt.copyTo$default(new ByteArrayInputStream(bytes2), fileOutputStream, 0, 2, null));
                        }
                        throw new IOException("Transaction not ready");
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                    Long boxLong2 = Boxing.boxLong(boxLong.longValue());
                    CloseableKt.closeFinally(fileOutputStream, null);
                    Boxing.boxLong(boxLong2.longValue());
                    CloseableKt.closeFinally(it, null);
                }
                return Boxing.boxBoolean(true);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return Boxing.boxBoolean(false);
            } catch (IOException e3) {
                e3.printStackTrace();
                return Boxing.boxBoolean(false);
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
