package com.google.android.gms.common.server.response;

import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.math.BigDecimal;
/* loaded from: classes.dex */
final class zah implements zai<BigDecimal> {
    @Override // com.google.android.gms.common.server.response.zai
    @Nullable
    public final /* synthetic */ BigDecimal zaa(FastParser fastParser, BufferedReader bufferedReader) {
        BigDecimal zas;
        zas = fastParser.zas(bufferedReader);
        return zas;
    }
}
