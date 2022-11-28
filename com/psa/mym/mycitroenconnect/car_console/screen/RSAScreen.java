package com.psa.mym.mycitroenconnect.car_console.screen;

import android.content.Intent;
import android.net.Uri;
import androidx.car.app.CarContext;
import androidx.car.app.CarToast;
import androidx.car.app.HostException;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarText;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Template;
import androidx.lifecycle.DefaultLifecycleObserver;
import com.psa.mym.mycitroenconnect.car_console.screen.RSAScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class RSAScreen extends Screen implements DefaultLifecycleObserver {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RSAScreen(@NotNull CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
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
    /* renamed from: onGetTemplate$lambda-0  reason: not valid java name */
    public static final void m55onGetTemplate$lambda0(RSAScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getScreenManager().popToRoot();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m56onGetTemplate$lambda1(RSAScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkForPermissionAndMakeCall();
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        String string = getCarContext().getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.string.cancel)");
        String string2 = getCarContext().getString(R.string.menu_title_roadside_assistance);
        Intrinsics.checkNotNullExpressionValue(string2, "carContext.getString(R.s…itle_roadside_assistance)");
        CarText build = new CarText.Builder(getCarContext().getString(R.string.lbl_auto_rsa_long_variant)).addVariant(getCarContext().getString(R.string.lbl_auto_rsa_short_variant)).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(carContext.getSt…\n                .build()");
        MessageTemplate.Builder builder = new MessageTemplate.Builder(build);
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        MessageTemplate.Builder title = builder.setIcon(autoUtils.getCarIconBuilder(carContext, R.drawable.ic_auto_rsa)).setHeaderAction(Action.BACK).setTitle(string2);
        Action.Builder title2 = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.g
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                RSAScreen.m55onGetTemplate$lambda0(RSAScreen.this);
            }
        }).setTitle(autoUtils.colorize(string, autoUtils.getWhiteColor(), 0, string.length()));
        CarContext carContext2 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
        MessageTemplate.Builder addAction = title.addAction(title2.setBackgroundColor(autoUtils.getDarkSlateGreyColor(carContext2)).build());
        Action.Builder title3 = new Action.Builder().setOnClickListener(new OnClickListener() { // from class: g.h
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                RSAScreen.m56onGetTemplate$lambda1(RSAScreen.this);
            }
        }).setTitle(getCarContext().getString(R.string.label_call_support));
        CarContext carContext3 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
        MessageTemplate build2 = addAction.addAction(title3.setBackgroundColor(autoUtils.getRedColor(carContext3)).build()).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder(itemTitle)\n     …   )\n            .build()");
        return build2;
    }
}
