package com.psa.mym.mycitroenconnect.car_console.screen;

import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarText;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Template;
import androidx.lifecycle.DefaultLifecycleObserver;
import com.psa.mym.mycitroenconnect.car_console.screen.MenuScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class MenuScreen extends Screen implements DefaultLifecycleObserver {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenuScreen(@NotNull CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        getLifecycle().addObserver(this);
    }

    private final Template displayApiResultWithMessage(String str) {
        String string = getCarContext().getString(R.string.lbl_auto_required_login);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s….lbl_auto_required_login)");
        String string2 = getCarContext().getString(R.string.lbl_auto_login_for_access);
        Intrinsics.checkNotNullExpressionValue(string2, "carContext.getString(R.s…bl_auto_login_for_access)");
        String string3 = getCarContext().getString(R.string.confirm);
        Intrinsics.checkNotNullExpressionValue(string3, "carContext.getString(R.string.confirm)");
        MessageTemplate.Builder title = new MessageTemplate.Builder(string2).setTitle(string);
        Action.Builder onClickListener = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.f
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                MenuScreen.m51displayApiResultWithMessage$lambda3(MenuScreen.this);
            }
        });
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        Action.Builder title2 = onClickListener.setTitle(autoUtils.colorize(string3, autoUtils.getWhiteColor(), 0, string3.length()));
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        MessageTemplate build = title.addAction(title2.setBackgroundColor(autoUtils.getRedColor(carContext)).build()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(message)\n//     …d())\n            .build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: displayApiResultWithMessage$lambda-3  reason: not valid java name */
    public static final void m51displayApiResultWithMessage$lambda3(MenuScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtil.Companion companion = AppUtil.Companion;
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        if (companion.isUserLoggedIn(carContext)) {
            this$0.invalidate();
            return;
        }
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext2 = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
        String string = this$0.getCarContext().getString(R.string.lbl_auto_required_login_toast);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…uto_required_login_toast)");
        autoUtils.showToast(carContext2, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-0  reason: not valid java name */
    public static final void m52onGetTemplate$lambda0(MenuScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenManager screenManager = this$0.getScreenManager();
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        screenManager.push(new SOSScreen(carContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m53onGetTemplate$lambda1(MenuScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenManager screenManager = this$0.getScreenManager();
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        screenManager.push(new RSAScreen(carContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-2  reason: not valid java name */
    public static final void m54onGetTemplate$lambda2(MenuScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenManager screenManager = this$0.getScreenManager();
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        screenManager.push(new ChargingStationScreen(carContext));
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        CarContext carContext;
        int i2;
        CarContext carContext2;
        int i3;
        AppUtil.Companion companion = AppUtil.Companion;
        CarContext carContext3 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
        if (!companion.isUserLoggedIn(carContext3)) {
            String string = getCarContext().getString(R.string.lbl_auto_login_for_access);
            Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…bl_auto_login_for_access)");
            return displayApiResultWithMessage(string);
        }
        ItemList.Builder builder = new ItemList.Builder();
        GridItem.Builder builder2 = new GridItem.Builder();
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext4 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext4, "carContext");
        builder.addItem(builder2.setImage(autoUtils.getCarIconBuilder(carContext4, R.drawable.ic_auto_menu_sos), 2).setTitle(getCarContext().getString(R.string.menu_title_sos)).setOnClickListener(new OnClickListener() { // from class: g.d
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                MenuScreen.m52onGetTemplate$lambda0(MenuScreen.this);
            }
        }).build());
        GridItem.Builder builder3 = new GridItem.Builder();
        CarContext carContext5 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext5, "carContext");
        GridItem.Builder image = builder3.setImage(autoUtils.getCarIconBuilder(carContext5, R.drawable.ic_auto_menu_rsa), 2);
        CarContext carContext6 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext6, "carContext");
        builder.addItem(image.setTitle(autoUtils.getCarTextBuilder(carContext6, R.string.lbl_menu_rsa)).setOnClickListener(new OnClickListener() { // from class: g.e
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                MenuScreen.m53onGetTemplate$lambda1(MenuScreen.this);
            }
        }).build());
        CarContext carContext7 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext7, "carContext");
        if (autoUtils.isVehicleTypeEv(carContext7)) {
            carContext = getCarContext();
            Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
            i2 = R.string.lbl_auto_charging_station;
        } else {
            carContext = getCarContext();
            Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
            i2 = R.string.lbl_auto_fuel_station;
        }
        CarText carTextBuilder = autoUtils.getCarTextBuilder(carContext, i2);
        CarContext carContext8 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext8, "carContext");
        if (autoUtils.isVehicleTypeEv(carContext8)) {
            carContext2 = getCarContext();
            Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
            i3 = R.drawable.ic_auto_menu_charging_stn;
        } else {
            carContext2 = getCarContext();
            Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
            i3 = R.drawable.ic_auto_fuel_station;
        }
        builder.addItem(new GridItem.Builder().setImage(autoUtils.getCarIconBuilder(carContext2, i3), 2).setTitle(carTextBuilder).setOnClickListener(new OnClickListener() { // from class: g.c
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                MenuScreen.m54onGetTemplate$lambda2(MenuScreen.this);
            }
        }).build());
        GridTemplate build = new GridTemplate.Builder().setSingleList(builder.build()).setTitle(getCarContext().getString(R.string.lbl_menu)).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder() // .setHeaderA…ACK)\n            .build()");
        return build;
    }
}
