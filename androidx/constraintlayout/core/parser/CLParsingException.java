package androidx.constraintlayout.core.parser;

import androidx.core.os.EnvironmentCompat;
/* loaded from: classes.dex */
public class CLParsingException extends Exception {
    private final String mElementClass;
    private final int mLineNumber;
    private final String mReason;

    public CLParsingException(String str, CLElement cLElement) {
        int i2;
        this.mReason = str;
        if (cLElement != null) {
            this.mElementClass = cLElement.c();
            i2 = cLElement.getLine();
        } else {
            this.mElementClass = EnvironmentCompat.MEDIA_UNKNOWN;
            i2 = 0;
        }
        this.mLineNumber = i2;
    }

    public String reason() {
        return this.mReason + " (" + this.mElementClass + " at line " + this.mLineNumber + ")";
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "CLParsingException (" + hashCode() + ") : " + reason();
    }
}
