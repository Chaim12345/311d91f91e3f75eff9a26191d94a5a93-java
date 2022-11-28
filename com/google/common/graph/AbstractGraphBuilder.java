package com.google.common.graph;

import com.google.common.base.Optional;
/* loaded from: classes2.dex */
abstract class AbstractGraphBuilder<N> {

    /* renamed from: a  reason: collision with root package name */
    final boolean f9133a;

    /* renamed from: b  reason: collision with root package name */
    boolean f9134b = false;

    /* renamed from: c  reason: collision with root package name */
    ElementOrder f9135c = ElementOrder.insertion();

    /* renamed from: d  reason: collision with root package name */
    ElementOrder f9136d = ElementOrder.unordered();

    /* renamed from: e  reason: collision with root package name */
    Optional f9137e = Optional.absent();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractGraphBuilder(boolean z) {
        this.f9133a = z;
    }
}
