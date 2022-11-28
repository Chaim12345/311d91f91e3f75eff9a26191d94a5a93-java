package com.psa.mym.mycitroenconnect.car_console.screen;

import android.content.pm.PackageManager;
import androidx.car.app.CarAppPermission;
import androidx.car.app.CarContext;
import androidx.car.app.OnRequestPermissionsListener;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.LongMessageTemplate;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.ParkedOnlyOnClickListener;
import androidx.car.app.model.Template;
import com.psa.mym.mycitroenconnect.car_console.screen.RequestPermissionScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class RequestPermissionScreen extends Screen {
    private final boolean mPreSeedMode;
    @NotNull
    private final Action mRefreshAction;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RequestPermissionScreen(@NotNull CarContext carContext) {
        this(carContext, false, 2, null);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RequestPermissionScreen(@NotNull CarContext carContext, boolean z) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        this.mPreSeedMode = z;
        Action build = new Action.Builder().setTitle(carContext.getString(R.string.lbl_auto_refresh)).setBackgroundColor(CarColor.BLUE).setOnClickListener(new OnClickListener() { // from class: g.k
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                RequestPermissionScreen.m57mRefreshAction$lambda0(RequestPermissionScreen.this);
            }
        }).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n        .setTi…date() }\n        .build()");
        this.mRefreshAction = build;
    }

    public /* synthetic */ RequestPermissionScreen(CarContext carContext, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(carContext, (i2 & 2) != 0 ? false : z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mRefreshAction$lambda-0  reason: not valid java name */
    public static final void m57mRefreshAction$lambda0(RequestPermissionScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m58onGetTemplate$lambda1(RequestPermissionScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-3  reason: not valid java name */
    public static final void m59onGetTemplate$lambda3(final RequestPermissionScreen this$0, List permissions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        this$0.getCarContext().requestPermissions(permissions, new OnRequestPermissionsListener() { // from class: g.i
            @Override // androidx.car.app.OnRequestPermissionsListener
            public final void onRequestPermissionsResult(List list, List list2) {
                RequestPermissionScreen.m60onGetTemplate$lambda3$lambda2(RequestPermissionScreen.this, list, list2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-3$lambda-2  reason: not valid java name */
    public static final void m60onGetTemplate$lambda3$lambda2(RequestPermissionScreen this$0, List list, List list2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list2 == null || !(!list2.isEmpty())) {
            this$0.finish();
            return;
        }
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getCarContext().getString(R.string.lbl_auto_approve_permission);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…_auto_approve_permission)");
        String format = String.format(string, Arrays.copyOf(new Object[0], 0));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        autoUtils.showToast(carContext, format);
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        Template build;
        String str;
        boolean startsWith$default;
        Action action = this.mPreSeedMode ? Action.APP_ICON : Action.BACK;
        Intrinsics.checkNotNullExpressionValue(action, "if (mPreSeedMode) Action.APP_ICON else Action.BACK");
        final ArrayList<String> arrayList = new ArrayList();
        try {
            String[] strArr = getCarContext().getPackageManager().getPackageInfo(getCarContext().getPackageName(), 4096).requestedPermissions;
            Intrinsics.checkNotNullExpressionValue(strArr, "{\n            val info =…stedPermissions\n        }");
            for (String str2 : strArr) {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str2, "androidx.car.app", false, 2, null);
                if (!startsWith$default) {
                    try {
                        CarAppPermission.checkHasPermission(getCarContext(), str2);
                    } catch (SecurityException unused) {
                        arrayList.add(str2);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                build = new MessageTemplate.Builder(getCarContext().getString(R.string.lbl_auto_permission_granted)).setHeaderAction(action).addAction(new Action.Builder().setTitle(getCarContext().getString(R.string.lbl_auto_close)).setOnClickListener(new OnClickListener() { // from class: g.j
                    @Override // androidx.car.app.model.OnClickListener
                    public final void onClick() {
                        RequestPermissionScreen.m58onGetTemplate$lambda1(RequestPermissionScreen.this);
                    }
                }).build()).build();
                str = "Builder(\n               …\n                .build()";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(getCarContext().getString(R.string.lbl_auto_permission_desc));
                for (String str3 : arrayList) {
                    sb.append(str3);
                    sb.append("\n");
                }
                ParkedOnlyOnClickListener create = ParkedOnlyOnClickListener.create(new OnClickListener() { // from class: g.l
                    @Override // androidx.car.app.model.OnClickListener
                    public final void onClick() {
                        RequestPermissionScreen.m59onGetTemplate$lambda3(RequestPermissionScreen.this, arrayList);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(create, "create {\n            car…}\n            }\n        }");
                Action build2 = new Action.Builder().setTitle(getCarContext().getString(R.string.lbl_auto_grant_access)).setBackgroundColor(CarColor.BLUE).setOnClickListener(create).build();
                Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n            .s…   )\n            .build()");
                build = new LongMessageTemplate.Builder(sb).setTitle(getCarContext().getString(R.string.lbl_auto_required_permission)).addAction(build2).setHeaderAction(action).build();
                str = "Builder(message)\n       …ion)\n            .build()";
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            build = new MessageTemplate.Builder(getCarContext().getString(R.string.lbl_auto_package_not_found)).setHeaderAction(action).addAction(this.mRefreshAction).build();
            str = "Builder(carContext.getSt…\n                .build()";
        }
        Intrinsics.checkNotNullExpressionValue(build, str);
        return build;
    }
}
