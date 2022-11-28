package com.google.maps.internal;

import com.google.maps.errors.ApiException;
import java.util.HashSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public final class ExceptionsAllowedToRetry extends HashSet<Class<? extends ApiException>> {
    private static final long serialVersionUID = 5283992240187266422L;

    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExceptionsAllowedToRetry[");
        Object[] array = toArray();
        for (int i2 = 0; i2 < array.length; i2++) {
            sb.append(array[i2]);
            if (i2 < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
