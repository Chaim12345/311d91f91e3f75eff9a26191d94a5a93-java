package org.hamcrest.internal;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
/* loaded from: classes4.dex */
public class SelfDescribingValue<T> implements SelfDescribing {
    private T value;

    public SelfDescribingValue(T t2) {
        this.value = t2;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendValue(this.value);
    }
}
