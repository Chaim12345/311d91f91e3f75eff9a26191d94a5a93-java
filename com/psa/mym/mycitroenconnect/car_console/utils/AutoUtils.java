package com.psa.mym.mycitroenconnect.car_console.utils;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.text.SpannableString;
import androidx.car.app.CarContext;
import androidx.car.app.CarToast;
import androidx.car.app.HostException;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.ClickableSpan;
import androidx.car.app.model.ForegroundCarColorSpan;
import androidx.car.app.model.OnClickListener;
import androidx.core.graphics.drawable.IconCompat;
import com.google.maps.model.PlacesSearchResult;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.io.IOException;
import java.net.SocketTimeoutException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AutoUtils {
    @NotNull
    public static final AutoUtils INSTANCE = new AutoUtils();

    private AutoUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clickable$lambda-0  reason: not valid java name */
    public static final void m68clickable$lambda0(Runnable action) {
        Intrinsics.checkNotNullParameter(action, "$action");
        action.run();
    }

    @NotNull
    public final CharSequence clickable(@NotNull String s2, int i2, int i3, @NotNull final Runnable action) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Intrinsics.checkNotNullParameter(action, "action");
        SpannableString spannableString = new SpannableString(s2);
        spannableString.setSpan(ClickableSpan.create(new OnClickListener() { // from class: h.c
            @Override // androidx.car.app.model.OnClickListener
            public final void onClick() {
                AutoUtils.m68clickable$lambda0(action);
            }
        }), i2, i3 + i2, 33);
        return spannableString;
    }

    @NotNull
    public final CharSequence colorize(@NotNull String s2, @NotNull CarColor color, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        SpannableString spannableString = new SpannableString(s2);
        spannableString.setSpan(ForegroundCarColorSpan.create(color), i2, i3 + i2, 33);
        return spannableString;
    }

    public final void colorize(@NotNull SpannableString s2, @NotNull CarColor color, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        s2.setSpan(ForegroundCarColorSpan.create(color), i2, i3 + i2, 33);
    }

    @NotNull
    public final CarIcon getCarIconBuilder(@NotNull CarContext carContext, int i2) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        CarIcon build = new CarIcon.Builder(IconCompat.createWithResource(carContext, i2)).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n            Ico…      )\n        ).build()");
        return build;
    }

    @NotNull
    public final CarText getCarTextBuilder(@NotNull CarContext carContext, int i2) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        CarText build = new CarText.Builder(carContext.getString(i2)).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(carContext.getSt…ce))\n            .build()");
        return build;
    }

    @NotNull
    public final CarColor getDarkSlateGreyColor(@NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        CarColor createCustom = CarColor.createCustom(carContext.getColor(R.color.dark_slate_grey), carContext.getColor(R.color.dark_slate_grey));
        Intrinsics.checkNotNullExpressionValue(createCustom, "createCustom(\n          …ark_slate_grey)\n        )");
        return createCustom;
    }

    public final int getDistanceFromSearchCenter(@NotNull Location location, @NotNull Location mSearchCenter) {
        int roundToInt;
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(mSearchCenter, "mSearchCenter");
        roundToInt = MathKt__MathJVMKt.roundToInt(mSearchCenter.distanceTo(location));
        return roundToInt;
    }

    @NotNull
    public final String getErrorMessageFromErrorCode(@NotNull CarContext carContext, int i2) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        if (i2 != 400) {
            if (i2 != 401) {
                if (i2 != 408) {
                    if (i2 != 412) {
                        if (i2 != 500) {
                            switch (i2) {
                                case 403:
                                    return "Forbidden";
                                case 404:
                                    return "Resource Not Found";
                                case 405:
                                    return "Method Not Allowed";
                                default:
                                    switch (i2) {
                                        case 503:
                                            break;
                                        case 504:
                                            return "Gateway Timeout";
                                        case 505:
                                            return "Http version not supported";
                                        default:
                                            return "Unknown error occurred";
                                    }
                            }
                        }
                        String string = carContext.getString(R.string.err_server_not_reachable);
                        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…err_server_not_reachable)");
                        return string;
                    }
                    return "";
                }
                return "Request Time-out";
            }
            return "Unauthorized";
        }
        return "API Not found";
    }

    @NotNull
    public final String getHttpErrorToast(int i2, @NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        return getErrorMessageFromErrorCode(carContext, i2);
    }

    @NotNull
    public final CarColor getRedColor(@NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        CarColor createCustom = CarColor.createCustom(carContext.getColor(R.color.dark_red), carContext.getColor(R.color.dark_red));
        Intrinsics.checkNotNullExpressionValue(createCustom, "createCustom(\n          …color.dark_red)\n        )");
        return createCustom;
    }

    @NotNull
    public final CarColor getWhiteColor() {
        CarColor createCustom = CarColor.createCustom(-1, -1);
        Intrinsics.checkNotNullExpressionValue(createCustom, "createCustom(\n          …    Color.WHITE\n        )");
        return createCustom;
    }

    public final boolean isVehicleTypeEv(@NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        return !Intrinsics.areEqual(SharedPref.Companion.getVehicleType(carContext), AppConstants.ICE);
    }

    public final void onClickPlace(@NotNull CarContext carContext, @NotNull PlacesSearchResult place) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        Intrinsics.checkNotNullParameter(place, "place");
        try {
            carContext.startCarApp(new Intent(CarContext.ACTION_NAVIGATE, Uri.parse("geo:0,0?q=" + place.geometry.location.lat + AbstractJsonLexerKt.COMMA + place.geometry.location.lng)));
        } catch (HostException unused) {
            CarToast.makeText(carContext, "Failure starting navigation", 1).show();
        }
    }

    public final void showFailureToast(@NotNull CarContext context, @NotNull Throwable t2) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(t2, "t");
        if (t2 instanceof SocketTimeoutException) {
            string = context.getString(R.string.time_out);
            str = "context.getString(R.string.time_out)";
        } else if (!(t2 instanceof IOException)) {
            return;
        } else {
            string = context.getString(R.string.internet_issue);
            str = "context.getString(R.string.internet_issue)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        showToast(context, string);
    }

    public final void showToast(@NotNull CarContext context, @NotNull String msg) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(msg, "msg");
        CarToast.makeText(context, msg, 1).show();
    }
}
