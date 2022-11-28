package com.google.api.client.util.store;

import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public final class DataStoreUtils {
    private DataStoreUtils() {
    }

    public static String toString(DataStore<?> dataStore) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            boolean z = true;
            for (String str : dataStore.keySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                sb.append(str);
                sb.append('=');
                sb.append(dataStore.get(str));
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
            return sb.toString();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}
