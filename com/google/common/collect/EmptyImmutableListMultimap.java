package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {

    /* renamed from: c  reason: collision with root package name */
    static final EmptyImmutableListMultimap f8501c = new EmptyImmutableListMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableListMultimap() {
        super(ImmutableMap.of(), 0);
    }

    private Object readResolve() {
        return f8501c;
    }
}
