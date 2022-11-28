package com.archit.calendardaterangepicker.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;
import com.archit.calendardaterangepicker.R;
import com.archit.calendardaterangepicker.customviews.CustomTextView;
import com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u001f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u0006\u0010\u0005\u001a\u00020\u0002R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog;", "Landroid/app/Dialog;", "", "initView", "setListeners", "showDialog", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "tvDialogDone", "Lcom/archit/calendardaterangepicker/customviews/CustomTextView;", "tvDialogCancel", "", "hours", "I", "minutes", "", "mTitle", "Ljava/lang/String;", "Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog$TimePickerCallback;", "onTimeChangedListener", "Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog$TimePickerCallback;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog$TimePickerCallback;)V", "Companion", "TimePickerCallback", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class AwesomeTimePickerDialog extends Dialog {
    public static final Companion Companion = new Companion(null);
    private static final String LOG_TAG = AwesomeTimePickerDialog.class.getSimpleName();
    private int hours;
    private final String mTitle;
    private int minutes;
    private final TimePickerCallback onTimeChangedListener;
    private CustomTextView tvDialogCancel;
    private CustomTextView tvDialogDone;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u001e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog$Companion;", "", "", "kotlin.jvm.PlatformType", "LOG_TAG", "Ljava/lang/String;", "<init>", "()V", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/archit/calendardaterangepicker/timepicker/AwesomeTimePickerDialog$TimePickerCallback;", "", "", "hours", "mins", "", "onTimeSelected", "onCancel", "awesome-calendar_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public interface TimePickerCallback {
        void onCancel();

        void onTimeSelected(int i2, int i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AwesomeTimePickerDialog(@NotNull Context context, @NotNull String mTitle, @NotNull TimePickerCallback onTimeChangedListener) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(mTitle, "mTitle");
        Intrinsics.checkParameterIsNotNull(onTimeChangedListener, "onTimeChangedListener");
        this.mTitle = mTitle;
        this.onTimeChangedListener = onTimeChangedListener;
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setGravity(80);
        setCanceledOnTouchOutside(false);
        initView();
        setListeners();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        window.setAttributes(layoutParams);
    }

    private final void initView() {
        setContentView(R.layout.dialog_time_picker);
        View findViewById = findViewById(R.id.tvHeaderTitle);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(id.tvHeaderTitle)");
        View findViewById2 = findViewById(R.id.tvHeaderDone);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(id.tvHeaderDone)");
        this.tvDialogDone = (CustomTextView) findViewById2;
        View findViewById3 = findViewById(R.id.tvHeaderCancel);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(id.tvHeaderCancel)");
        this.tvDialogCancel = (CustomTextView) findViewById3;
        ((TimePicker) findViewById(R.id.timePicker)).setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog$initView$1
            @Override // android.widget.TimePicker.OnTimeChangedListener
            public final void onTimeChanged(TimePicker timePicker, int i2, int i3) {
                AwesomeTimePickerDialog.this.hours = i2;
                AwesomeTimePickerDialog.this.minutes = i3;
            }
        });
        ((CustomTextView) findViewById).setText(this.mTitle);
    }

    private final void setListeners() {
        CustomTextView customTextView = this.tvDialogCancel;
        if (customTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDialogCancel");
        }
        customTextView.setOnClickListener(new View.OnClickListener() { // from class: com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AwesomeTimePickerDialog.TimePickerCallback timePickerCallback;
                timePickerCallback = AwesomeTimePickerDialog.this.onTimeChangedListener;
                timePickerCallback.onCancel();
                AwesomeTimePickerDialog.this.dismiss();
            }
        });
        CustomTextView customTextView2 = this.tvDialogDone;
        if (customTextView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDialogDone");
        }
        customTextView2.setOnClickListener(new View.OnClickListener() { // from class: com.archit.calendardaterangepicker.timepicker.AwesomeTimePickerDialog$setListeners$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AwesomeTimePickerDialog.TimePickerCallback timePickerCallback;
                int i2;
                int i3;
                timePickerCallback = AwesomeTimePickerDialog.this.onTimeChangedListener;
                i2 = AwesomeTimePickerDialog.this.hours;
                i3 = AwesomeTimePickerDialog.this.minutes;
                timePickerCallback.onTimeSelected(i2, i3);
                AwesomeTimePickerDialog.this.dismiss();
            }
        });
    }

    public final void showDialog() {
        this.hours = Calendar.getInstance().get(11);
        this.minutes = Calendar.getInstance().get(12);
        show();
    }
}
