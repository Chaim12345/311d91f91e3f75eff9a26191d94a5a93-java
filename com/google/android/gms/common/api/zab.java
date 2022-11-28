package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zab implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Batch f5721a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zab(Batch batch) {
        this.f5721a = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Object obj;
        int i2;
        int i3;
        boolean z;
        boolean z2;
        PendingResult[] pendingResultArr;
        obj = this.f5721a.zai;
        synchronized (obj) {
            if (this.f5721a.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                this.f5721a.zag = true;
            } else if (!status.isSuccess()) {
                this.f5721a.zaf = true;
            }
            Batch batch = this.f5721a;
            i2 = batch.zae;
            batch.zae = i2 - 1;
            Batch batch2 = this.f5721a;
            i3 = batch2.zae;
            if (i3 == 0) {
                z = batch2.zag;
                if (z) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    z2 = batch2.zaf;
                    Status status2 = z2 ? new Status(13) : Status.RESULT_SUCCESS;
                    Batch batch3 = this.f5721a;
                    pendingResultArr = batch3.zah;
                    batch3.setResult(new BatchResult(status2, pendingResultArr));
                }
            }
        }
    }
}
