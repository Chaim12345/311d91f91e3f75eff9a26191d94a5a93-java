package org.bouncycastle.jsse;

import java.util.List;
/* loaded from: classes3.dex */
public interface BCApplicationProtocolSelector<T> {
    String select(T t2, List<String> list);
}
