package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public class SingleCustomDialog extends Dialog implements DialogInterface.OnDismissListener {
    private final Dialog dialog;
    private final Handler handler;
    private final OnDialogOkClickListener listener;
    private final Runnable runnable;
    private final TextView tvCancel;
    private TextView tvConfirm;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b6, code lost:
        if (r10.equals("1") == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SingleCustomDialog(@NonNull Context context, final MyNotificationModel myNotificationModel, String str, final OnDialogOkClickListener onDialogOkClickListener) {
        super(context);
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.dialog.c
            @Override // java.lang.Runnable
            public final void run() {
                SingleCustomDialog.this.lambda$new$2();
            }
        };
        this.listener = onDialogOkClickListener;
        this.dialog = this;
        char c2 = 1;
        requestWindowFeature(1);
        setCancelable(false);
        setContentView(R.layout.layout_single_dialog);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linTitle);
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById(R.id.ivTitle);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        TextView textView2 = (TextView) findViewById(R.id.tv_msg);
        TextView textView3 = (TextView) findViewById(R.id.tvDate);
        this.tvConfirm = (TextView) findViewById(R.id.tvConfirm);
        this.tvCancel = (TextView) findViewById(R.id.tvCancel);
        if (myNotificationModel.getTitle().equals("")) {
            linearLayout.setVisibility(8);
        } else {
            String priority = myNotificationModel.getPriority();
            priority.hashCode();
            switch (priority.hashCode()) {
                case -1097329270:
                    if (priority.equals("logout")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 49:
                    break;
                case 50:
                    if (priority.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 51:
                    if (priority.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 52:
                    if (priority.equals("4")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                    linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_red));
                    appCompatImageView.setImageResource(R.drawable.ic_critical_alert);
                    textView.setTextColor(context.getColor(R.color.white));
                    this.tvConfirm.setTextColor(context.getColor(R.color.dark_red));
                    linearLayout.setVisibility(0);
                    startAutoDismissCallBack();
                    break;
                case 1:
                    linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_red));
                    appCompatImageView.setImageResource(R.drawable.ic_critical_alert);
                    textView.setTextColor(context.getColor(R.color.white));
                    this.tvConfirm.setTextColor(context.getColor(R.color.dark_red));
                    break;
                case 2:
                case 3:
                case 4:
                    linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_yellow));
                    appCompatImageView.setImageResource(R.drawable.ic_general_alert);
                    textView.setTextColor(context.getColor(R.color.dark_grey));
                    this.tvConfirm.setTextColor(context.getColor(R.color.dark_yellow));
                    break;
            }
            linearLayout.setVisibility(0);
            textView.setText(myNotificationModel.getTitle());
        }
        displayBtnsBasedOnAlertType(context, myNotificationModel);
        textView2.setText(myNotificationModel.getBody());
        if (myNotificationModel.getAlertTime() == null || myNotificationModel.getAlertTime().isEmpty() || myNotificationModel.getAlertTime().length() <= 0) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(myNotificationModel.getAlertTime());
        }
        this.tvConfirm.setText(str);
        this.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.dialog.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleCustomDialog.this.lambda$new$0(onDialogOkClickListener, myNotificationModel, view);
            }
        });
        getWindow().setBackgroundDrawableResource(17170445);
        show();
        setOnDismissListener(this);
        getWindow().setLayout(-1, -2);
        getWindow().setGravity(48);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0085, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void displayBtnsBasedOnAlertType(Context context, final MyNotificationModel myNotificationModel) {
        char c2 = 0;
        this.tvCancel.setVisibility(0);
        String alertState = myNotificationModel.getAlertState();
        alertState.hashCode();
        switch (alertState.hashCode()) {
            case -894483947:
                break;
            case -778729028:
                if (alertState.equals(AppConstants.Notification_GeoFenceAlert)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -337523735:
                if (alertState.equals(AppConstants.Notification_ValetModeAlert)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 93415538:
                if (alertState.equals(AppConstants.Notification_LowFuelAlert)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 190548681:
                if (alertState.equals("ACIdlingAlert")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 654786679:
                if (alertState.equals(AppConstants.Notification_StolenVehicleAlert)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 970664341:
                if (alertState.equals(AppConstants.Notification_IntrusionAlert)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1066474995:
                if (alertState.equals(AppConstants.Notification_UpdateAlert)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1881199922:
                if (alertState.equals(AppConstants.Notification_TowAwayAlert)) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 2026364241:
                if (alertState.equals(AppConstants.Notification_NoMobileNetworkAlert)) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 1:
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 2:
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 3:
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvConfirm.setText(context.getString(R.string.alert_title_call_helpline));
                this.tvCancel.setVisibility(8);
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 4:
                this.tvCancel.setText(context.getString(R.string.alert_title_change_idling_setting));
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvConfirm.setText(context.getString(R.string.alert_title_call_helpline));
                this.tvCancel.setVisibility(8);
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 5:
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvConfirm.setText(context.getString(R.string.alert_title_call_helpline));
                this.tvCancel.setVisibility(8);
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 6:
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case 7:
                this.tvCancel.setVisibility(8);
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case '\b':
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvConfirm.setText(context.getString(R.string.alert_title_call_helpline));
                this.tvCancel.setVisibility(8);
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
            case '\t':
                this.tvCancel.setVisibility(8);
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                this.tvCancel.setText(context.getString(R.string.alert_title_locate_car));
                break;
        }
        this.tvCancel.setVisibility(8);
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.dialog.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleCustomDialog.this.lambda$displayBtnsBasedOnAlertType$1(myNotificationModel, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$displayBtnsBasedOnAlertType$1(MyNotificationModel myNotificationModel, View view) {
        this.listener.onDialogCancelClick(this.dialog, myNotificationModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(OnDialogOkClickListener onDialogOkClickListener, MyNotificationModel myNotificationModel, View view) {
        onDialogOkClickListener.onDialogOkClick(this.dialog, myNotificationModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        this.tvConfirm.performClick();
    }

    private void startAutoDismissCallBack() {
        setOnDismissListener(this);
        this.handler.postDelayed(this.runnable, 5000L);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
