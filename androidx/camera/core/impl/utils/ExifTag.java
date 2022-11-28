package androidx.camera.core.impl.utils;
/* loaded from: classes.dex */
class ExifTag {
    public final String name;
    public final int number;
    public final int primaryFormat;
    public final int secondaryFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExifTag(String str, int i2, int i3) {
        this.name = str;
        this.number = i2;
        this.primaryFormat = i3;
        this.secondaryFormat = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExifTag(String str, int i2, int i3, int i4) {
        this.name = str;
        this.number = i2;
        this.primaryFormat = i3;
        this.secondaryFormat = i4;
    }
}
