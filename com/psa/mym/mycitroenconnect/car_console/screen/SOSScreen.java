package com.psa.mym.mycitroenconnect.car_console.screen;

import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Template;
import androidx.lifecycle.DefaultLifecycleObserver;
import com.psa.mym.mycitroenconnect.car_console.screen.SOSScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class SOSScreen extends Screen implements DefaultLifecycleObserver {
    @NotNull
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SOSScreen(@NotNull CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        String simpleName = SOSScreen.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SOSScreen::class.java.simpleName");
        this.TAG = simpleName;
        getLifecycle().addObserver(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-0  reason: not valid java name */
    public static final void m64onGetTemplate$lambda0(SOSScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getScreenManager().popToRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m65onGetTemplate$lambda1(SOSScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenManager screenManager = this$0.getScreenManager();
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        screenManager.push(new SOSLoadingScreen(carContext));
    }

    @NotNull
    public final String getTAG() {
        return this.TAG;
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        String string = getCarContext().getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.string.cancel)");
        String string2 = getCarContext().getString(R.string.menu_title_sos);
        Intrinsics.checkNotNullExpressionValue(string2, "carContext.getString(R.string.menu_title_sos)");
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        MessageTemplate.Builder builder = new MessageTemplate.Builder(autoUtils.getCarTextBuilder(carContext, R.string.lbl_auto_sos_desc));
        CarContext carContext2 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
        MessageTemplate.Builder title = builder.setIcon(autoUtils.getCarIconBuilder(carContext2, R.drawable.ic_auto_sos)).setHeaderAction(Action.BACK).setTitle(getCarContext().getString(R.string.label_emergency_sos));
        Action.Builder title2 = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.q
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                SOSScreen.m64onGetTemplate$lambda0(SOSScreen.this);
            }
        }).setTitle(autoUtils.colorize(string, autoUtils.getWhiteColor(), 0, string.length()));
        CarContext carContext3 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
        MessageTemplate.Builder addAction = title.addAction(title2.setBackgroundColor(autoUtils.getDarkSlateGreyColor(carContext3)).build());
        Action.Builder title3 = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.p
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                SOSScreen.m65onGetTemplate$lambda1(SOSScreen.this);
            }
        }).setTitle(autoUtils.colorize(string2, autoUtils.getWhiteColor(), 0, string2.length()));
        CarContext carContext4 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext4, "carContext");
        MessageTemplate build = addAction.addAction(title3.setBackgroundColor(autoUtils.getRedColor(carContext4)).build()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n            ite…())\n\n            .build()");
        return build;
    }
}
