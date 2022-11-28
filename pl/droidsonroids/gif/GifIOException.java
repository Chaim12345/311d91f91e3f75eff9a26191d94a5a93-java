package pl.droidsonroids.gif;

import androidx.annotation.NonNull;
import java.io.IOException;
/* loaded from: classes4.dex */
public class GifIOException extends IOException {
    private static final long serialVersionUID = 13038402904505L;
    private final String mErrnoMessage;
    @NonNull
    public final GifError reason;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifIOException(int i2, String str) {
        this.reason = GifError.a(i2);
        this.mErrnoMessage = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifIOException a(int i2) {
        if (i2 == GifError.NO_ERROR.f15264a) {
            return null;
        }
        return new GifIOException(i2, null);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.mErrnoMessage == null) {
            return this.reason.b();
        }
        return this.reason.b() + ": " + this.mErrnoMessage;
    }
}
