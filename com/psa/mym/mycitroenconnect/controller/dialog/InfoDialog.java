package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.psa.mym.mycitroenconnect.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InfoDialog extends Dialog implements View.OnClickListener {
    @NotNull
    private String message;
    @Nullable
    private OnInfoDialogDismiss onDialogDismiss;
    @NotNull
    private String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InfoDialog(@NotNull Context context, @NotNull String title, @NotNull String message) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.title = title;
        this.message = message;
    }

    public /* synthetic */ InfoDialog(Context context, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2);
    }

    private final void init() {
        ((AppCompatImageView) findViewById(R.id.ivInfoClose)).setOnClickListener(this);
        if (this.title.length() > 0) {
            ((AppCompatTextView) findViewById(R.id.tvInfoTitle)).setText(this.title);
        }
        if (this.message.length() > 0) {
            ((AppCompatTextView) findViewById(R.id.tvInfoDesc)).setText(this.message);
        }
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) findViewById(R.id.ivInfoClose))) {
            OnInfoDialogDismiss onInfoDialogDismiss = this.onDialogDismiss;
            if (onInfoDialogDismiss != null) {
                onInfoDialogDismiss.onDialogDismiss();
            }
            dismiss();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.layout_info_dlg);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_dialog_rounded_background);
        }
        init();
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setOnDialogDismiss(@NotNull OnInfoDialogDismiss OnInfoDialogDismiss) {
        Intrinsics.checkNotNullParameter(OnInfoDialogDismiss, "OnInfoDialogDismiss");
        setCancelable(false);
        this.onDialogDismiss = OnInfoDialogDismiss;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }
}
