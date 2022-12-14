package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
@DoNotMock("Implement with a lambda")
@Beta
/* loaded from: classes2.dex */
public interface Funnel<T> extends Serializable {
    void funnel(T t2, PrimitiveSink primitiveSink);
}
