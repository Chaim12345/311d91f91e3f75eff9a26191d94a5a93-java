package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public final class RecordPreview {
    private final int contentLimit;
    private final int recordSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordPreview(int i2, int i3) {
        this.recordSize = i2;
        this.contentLimit = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RecordPreview a(RecordPreview recordPreview, RecordPreview recordPreview2) {
        return new RecordPreview(recordPreview.getRecordSize() + recordPreview2.getRecordSize(), recordPreview.getContentLimit() + recordPreview2.getContentLimit());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RecordPreview b(RecordPreview recordPreview, int i2) {
        return new RecordPreview(recordPreview.getRecordSize() + i2, recordPreview.getContentLimit());
    }

    public int getApplicationDataLimit() {
        return this.contentLimit;
    }

    public int getContentLimit() {
        return this.contentLimit;
    }

    public int getRecordSize() {
        return this.recordSize;
    }
}
