package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CustomProgressDialog extends Dialog {
    @NotNull
    private String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomProgressDialog(@NotNull Context context, @NotNull String message) {
        super(context, R.style.CustomDialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
    }

    public /* synthetic */ CustomProgressDialog(Context context, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? "" : str);
    }

    private final void init() {
        ((AppCompatTextView) findViewById(com.psa.mym.mycitroenconnect.R.id.tvMsg)).setText(this.message);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
            window.setGravity(17);
        }
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @Override // android.app.Dialog
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.custom_progress_dialog);
        init();
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }
}
