package com.chuckerteam.chucker.internal.ui.transaction;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import androidx.core.text.HtmlCompat;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.BitmapUtilsKt;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadItem;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$processPayload$2", f = "TransactionPayloadFragment.kt", i = {0, 0, 0, 0, 0, 0}, l = {271}, m = "invokeSuspend", n = {"$this$withContext", "result", "headersString", "isBodyPlainText", "bodyString", "responseBitmap"}, s = {"L$0", "L$1", "L$2", "Z$0", "L$3", "L$4"})
/* loaded from: classes.dex */
final class TransactionPayloadFragment$processPayload$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<TransactionPayloadItem>>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f5000a;

    /* renamed from: b  reason: collision with root package name */
    Object f5001b;

    /* renamed from: c  reason: collision with root package name */
    Object f5002c;

    /* renamed from: d  reason: collision with root package name */
    Object f5003d;

    /* renamed from: e  reason: collision with root package name */
    Object f5004e;

    /* renamed from: f  reason: collision with root package name */
    boolean f5005f;

    /* renamed from: g  reason: collision with root package name */
    int f5006g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ TransactionPayloadFragment f5007h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ PayloadType f5008i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ HttpTransaction f5009j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ boolean f5010k;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionPayloadFragment$processPayload$2(TransactionPayloadFragment transactionPayloadFragment, PayloadType payloadType, HttpTransaction httpTransaction, boolean z, Continuation continuation) {
        super(2, continuation);
        this.f5007h = transactionPayloadFragment;
        this.f5008i = payloadType;
        this.f5009j = httpTransaction;
        this.f5010k = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        TransactionPayloadFragment$processPayload$2 transactionPayloadFragment$processPayload$2 = new TransactionPayloadFragment$processPayload$2(this.f5007h, this.f5008i, this.f5009j, this.f5010k, completion);
        transactionPayloadFragment$processPayload$2.p$ = (CoroutineScope) obj;
        return transactionPayloadFragment$processPayload$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<TransactionPayloadItem>> continuation) {
        return ((TransactionPayloadFragment$processPayload$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        List arrayList;
        String responseHeadersString;
        boolean isResponseBodyPlainText;
        String formattedResponseBody;
        boolean isBlank;
        boolean isBlank2;
        List<String> lines;
        Bitmap bitmap;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f5006g;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            arrayList = new ArrayList();
            if (this.f5008i == PayloadType.REQUEST) {
                responseHeadersString = this.f5009j.getRequestHeadersString(true);
                isResponseBodyPlainText = this.f5009j.isRequestBodyPlainText();
                if (this.f5010k) {
                    formattedResponseBody = this.f5009j.getFormattedRequestBody();
                } else {
                    formattedResponseBody = this.f5009j.getRequestBody();
                    if (formattedResponseBody == null) {
                        formattedResponseBody = "";
                    }
                }
            } else {
                responseHeadersString = this.f5009j.getResponseHeadersString(true);
                isResponseBodyPlainText = this.f5009j.isResponseBodyPlainText();
                formattedResponseBody = this.f5009j.getFormattedResponseBody();
            }
            isBlank = StringsKt__StringsJVMKt.isBlank(responseHeadersString);
            if (!isBlank) {
                Spanned fromHtml = HtmlCompat.fromHtml(responseHeadersString, 0);
                Intrinsics.checkNotNullExpressionValue(fromHtml, "HtmlCompat.fromHtml(\n   …                        )");
                arrayList.add(new TransactionPayloadItem.HeaderItem(fromHtml));
            }
            Bitmap responseImageBitmap = this.f5009j.getResponseImageBitmap();
            if (this.f5008i != PayloadType.RESPONSE || responseImageBitmap == null) {
                if (isResponseBodyPlainText) {
                    isBlank2 = StringsKt__StringsJVMKt.isBlank(formattedResponseBody);
                    if (!isBlank2) {
                        lines = StringsKt__StringsKt.lines(formattedResponseBody);
                        for (String str : lines) {
                            SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(str);
                            Intrinsics.checkNotNullExpressionValue(valueOf, "SpannableStringBuilder.valueOf(it)");
                            arrayList.add(new TransactionPayloadItem.BodyLineItem(valueOf));
                        }
                    }
                } else {
                    SpannableStringBuilder valueOf2 = SpannableStringBuilder.valueOf(this.f5007h.requireContext().getString(R.string.chucker_body_omitted));
                    Intrinsics.checkNotNullExpressionValue(valueOf2, "SpannableStringBuilder.valueOf(it)");
                    Boxing.boxBoolean(arrayList.add(new TransactionPayloadItem.BodyLineItem(valueOf2)));
                }
                return arrayList;
            }
            this.f5000a = coroutineScope;
            this.f5001b = arrayList;
            this.f5002c = responseHeadersString;
            this.f5005f = isResponseBodyPlainText;
            this.f5003d = formattedResponseBody;
            this.f5004e = responseImageBitmap;
            this.f5006g = 1;
            obj = BitmapUtilsKt.calculateLuminance(responseImageBitmap, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            bitmap = responseImageBitmap;
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            bitmap = (Bitmap) this.f5004e;
            String str2 = (String) this.f5003d;
            String str3 = (String) this.f5002c;
            arrayList = (List) this.f5001b;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.f5000a;
            ResultKt.throwOnFailure(obj);
        }
        arrayList.add(new TransactionPayloadItem.ImageItem(bitmap, (Double) obj));
        return arrayList;
    }
}
