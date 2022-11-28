package com.google.firebase.crashlytics.internal.send;

import com.google.android.datatransport.Transformer;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
/* loaded from: classes2.dex */
public final /* synthetic */ class a implements Transformer {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f10024a = new a();

    private /* synthetic */ a() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        byte[] lambda$static$0;
        lambda$static$0 = DataTransportCrashlyticsReportSender.lambda$static$0((CrashlyticsReport) obj);
        return lambda$static$0;
    }
}
