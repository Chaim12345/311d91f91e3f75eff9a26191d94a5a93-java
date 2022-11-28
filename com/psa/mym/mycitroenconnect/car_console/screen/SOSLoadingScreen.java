package com.psa.mym.mycitroenconnect.car_console.screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.car.app.CarContext;
import androidx.car.app.CarToast;
import androidx.car.app.HostException;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.psa.mym.mycitroenconnect.car_console.screen.SOSLoadingScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class SOSLoadingScreen extends Screen implements DefaultLifecycleObserver {
    private final String TAG;
    @NotNull
    private final Handler mHandler;
    private boolean mIsFinishedLoading;
    @NotNull
    private String mState;
    @NotNull
    private String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SOSLoadingScreen(@NotNull CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        getLifecycle().addObserver(this);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mState = AutoConstants.STATE_LOADING;
        this.message = "";
        this.TAG = SOSLoadingScreen.class.getSimpleName();
    }

    private final void checkForPermissionAndMakeCall() {
        if (getCarContext().checkSelfPermission("android.permission.CALL_PHONE") == 0) {
            onClickCallSupport();
            return;
        }
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        String string = getCarContext().getString(R.string.lbl_call_permission_msg);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s….lbl_call_permission_msg)");
        autoUtils.showToast(carContext, string);
    }

    private final void onClickCallSupport() {
        CharSequence trim;
        String string = getCarContext().getString(R.string.label_customer_care_number);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…bel_customer_care_number)");
        trim = StringsKt__StringsKt.trim((CharSequence) string);
        String obj = trim.toString();
        try {
            getCarContext().startCarApp(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
        } catch (HostException unused) {
            CarToast.makeText(getCarContext(), "Failure starting dialer", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m61onGetTemplate$lambda1(SOSLoadingScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getScreenManager().pop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-2  reason: not valid java name */
    public static final void m62onGetTemplate$lambda2(SOSLoadingScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getScreenManager().popToRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onStart$lambda-0  reason: not valid java name */
    public static final void m63onStart$lambda0(SOSLoadingScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mIsFinishedLoading = true;
        this$0.mState = AutoConstants.STATE_LOADING_FINISHED;
        this$0.invalidate();
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        PaneTemplate paneTemplate;
        PaneTemplate.Builder headerAction;
        String string = getCarContext().getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.string.cancel)");
        Pane.Builder builder = new Pane.Builder();
        String string2 = getCarContext().getString(R.string.label_emergency_sos);
        Intrinsics.checkNotNullExpressionValue(string2, "carContext.getString(R.string.label_emergency_sos)");
        Action.Builder onClickListener = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.m
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                SOSLoadingScreen.m61onGetTemplate$lambda1(SOSLoadingScreen.this);
            }
        });
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarColor RED = CarColor.RED;
        Intrinsics.checkNotNullExpressionValue(RED, "RED");
        Action build = onClickListener.setTitle(autoUtils.colorize(string, RED, 0, string.length())).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().setOnClickList…th))\n            .build()");
        ActionStrip build2 = new ActionStrip.Builder().addAction(build).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n            .a…   )\n            .build()");
        if (!this.mIsFinishedLoading) {
            builder.setLoading(true);
            headerAction = new PaneTemplate.Builder(builder.build()).setTitle(string2).setHeaderAction(Action.BACK).setActionStrip(build2);
        } else if (!Intrinsics.areEqual(this.mState, AutoConstants.STATE_LOADING_FINISHED)) {
            paneTemplate = null;
            Intrinsics.checkNotNull(paneTemplate);
            return paneTemplate;
        } else {
            checkForPermissionAndMakeCall();
            String string3 = getCarContext().getString(R.string.lbl_auto_sos_req_sent);
            Intrinsics.checkNotNullExpressionValue(string3, "carContext.getString(R.s…ng.lbl_auto_sos_req_sent)");
            this.message = string3;
            builder.addRow(new Row.Builder().setTitle(this.message).build());
            this.mHandler.postDelayed(new Runnable() { // from class: g.n
                @Override // java.lang.Runnable
                public final void run() {
                    SOSLoadingScreen.m62onGetTemplate$lambda2(SOSLoadingScreen.this);
                }
            }, 500L);
            headerAction = new PaneTemplate.Builder(builder.build()).setTitle(getCarContext().getString(R.string.label_emergency_sos)).setHeaderAction(Action.BACK);
        }
        paneTemplate = headerAction.build();
        Intrinsics.checkNotNull(paneTemplate);
        return paneTemplate;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.mHandler.postDelayed(new Runnable() { // from class: g.o
            @Override // java.lang.Runnable
            public final void run() {
                SOSLoadingScreen.m63onStart$lambda0(SOSLoadingScreen.this);
            }
        }, 3000L);
    }
}
