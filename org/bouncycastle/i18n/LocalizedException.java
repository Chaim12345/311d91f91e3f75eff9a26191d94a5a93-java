package org.bouncycastle.i18n;

import java.util.Locale;
/* loaded from: classes3.dex */
public class LocalizedException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    protected ErrorBundle f13581a;
    private Throwable cause;

    public LocalizedException(ErrorBundle errorBundle) {
        super(errorBundle.getText(Locale.getDefault()));
        this.f13581a = errorBundle;
    }

    public LocalizedException(ErrorBundle errorBundle, Throwable th) {
        super(errorBundle.getText(Locale.getDefault()));
        this.f13581a = errorBundle;
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ErrorBundle getErrorMessage() {
        return this.f13581a;
    }
}
