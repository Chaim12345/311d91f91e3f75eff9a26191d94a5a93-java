package com.psa.mym.mycitroenconnect.car_console.session;

import android.content.Intent;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.Session;
import com.psa.mym.mycitroenconnect.car_console.screen.MenuScreen;
import com.psa.mym.mycitroenconnect.car_console.screen.RequestPermissionScreen;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class MenuScreenSession extends Session {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String TAG;

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getTAG() {
            return MenuScreenSession.TAG;
        }
    }

    static {
        String simpleName = MenuScreenSession.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "MenuScreenSession::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // androidx.car.app.Session
    @NotNull
    public Screen onCreateScreen(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        MenuScreen menuScreen = new MenuScreen(carContext);
        if (getCarContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && getCarContext().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && getCarContext().checkSelfPermission("android.permission.CALL_PHONE") == 0) {
            return menuScreen;
        }
        ((ScreenManager) getCarContext().getCarService(ScreenManager.class)).push(menuScreen);
        CarContext carContext2 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
        return new RequestPermissionScreen(carContext2, false);
    }
}
