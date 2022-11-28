package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
/* loaded from: classes2.dex */
public final /* synthetic */ class y implements Transformer {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ y f10120a = new y();

    private /* synthetic */ y() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        return ((MessagingClientEventExtension) obj).toByteArray();
    }
}
