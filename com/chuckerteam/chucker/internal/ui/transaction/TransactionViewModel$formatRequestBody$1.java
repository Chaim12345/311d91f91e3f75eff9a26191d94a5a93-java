package com.chuckerteam.chucker.internal.ui.transaction;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u000b\n\u0002\b\u0005\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "requiresEncoding", "encodeUrl", "invoke", "(ZZ)Z", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionViewModel$formatRequestBody$1 extends Lambda implements Function2<Boolean, Boolean, Boolean> {
    public static final TransactionViewModel$formatRequestBody$1 INSTANCE = new TransactionViewModel$formatRequestBody$1();

    TransactionViewModel$formatRequestBody$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool, Boolean bool2) {
        return Boolean.valueOf(invoke(bool.booleanValue(), bool2.booleanValue()));
    }

    public final boolean invoke(boolean z, boolean z2) {
        return (z && z2) ? false : true;
    }
}
