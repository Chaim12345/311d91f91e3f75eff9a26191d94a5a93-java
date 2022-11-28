package com.psa.mym.mycitroenconnect.controller.activities.trips;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.widget.AppCompatImageView;
import com.psa.mym.mycitroenconnect.GlideApp;
import com.psa.mym.mycitroenconnect.GlideRequests;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2Direction;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
/* loaded from: classes2.dex */
public final class TripDetailsActivity$route$handler$1 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TripDetailsActivity f10444a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TripDetailsActivity$route$handler$1(TripDetailsActivity tripDetailsActivity, Looper looper) {
        super(looper);
        this.f10444a = tripDetailsActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleMessage$lambda-0  reason: not valid java name */
    public static final void m115handleMessage$lambda0(TripDetailsActivity this$0) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtil.Companion.dismissDialog();
        GlideRequests with = GlideApp.with(this$0.getApplicationContext());
        str = this$0.mapURL;
        with.load(str).into((AppCompatImageView) this$0._$_findCachedViewById(R.id.ivTdMap));
    }

    @Override // android.os.Handler
    public void handleMessage(@NotNull Message msg) {
        boolean z;
        StringBuilder sb;
        String str;
        String str2;
        CharSequence trim;
        String str3;
        String str4;
        String str5;
        CharSequence trim2;
        Intrinsics.checkNotNullParameter(msg, "msg");
        try {
            Object obj = msg.obj;
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.w3c.dom.Document");
            }
            String points = new GMapV2Direction().getPoints((Document) obj);
            TripDetailsActivity tripDetailsActivity = this.f10444a;
            z = tripDetailsActivity.isOngoingTrip;
            if (z) {
                sb = new StringBuilder();
                sb.append("http://maps.googleapis.com/maps/api/staticmap?sensor=false&size=400x200&markers=icon:https%3A%2F%2Fi.ibb.co%2Fd4tLxcT%2Fcar-top.png%7C");
                str4 = this.f10444a.endLanLong;
                sb.append(str4);
                sb.append("&markers=size:tiny%7Ccolor:green%7Clabel:S%7C");
                str5 = this.f10444a.startLanLong;
                sb.append(str5);
                sb.append("&scale=2&path=weight:4%7Ccolor:blue%7Cenc:");
                Intrinsics.checkNotNullExpressionValue(points, "points");
                trim2 = StringsKt__StringsKt.trim((CharSequence) points);
                sb.append(trim2.toString());
                sb.append("&key=AIzaSyDlBcXQ8tRCtgzdBJqVHfdNa4KDGu0mivA");
            } else {
                sb = new StringBuilder();
                sb.append("http://maps.googleapis.com/maps/api/staticmap?sensor=false&size=400x200&markers=size:tiny%7Ccolor:red%7Clabel:S%7C");
                str = this.f10444a.endLanLong;
                sb.append(str);
                sb.append("&markers=size:tiny%7Ccolor:green%7Clabel:S%7C");
                str2 = this.f10444a.startLanLong;
                sb.append(str2);
                sb.append("&scale=2&path=weight:4%7Ccolor:blue%7Cenc:");
                Intrinsics.checkNotNullExpressionValue(points, "points");
                trim = StringsKt__StringsKt.trim((CharSequence) points);
                sb.append(trim.toString());
                sb.append("&key=AIzaSyDlBcXQ8tRCtgzdBJqVHfdNa4KDGu0mivA");
            }
            tripDetailsActivity.mapURL = sb.toString();
            Logger logger = Logger.INSTANCE;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Static Map URL: ");
            str3 = this.f10444a.mapURL;
            sb2.append(str3);
            logger.e(sb2.toString());
            final TripDetailsActivity tripDetailsActivity2 = this.f10444a;
            tripDetailsActivity2.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.e
                @Override // java.lang.Runnable
                public final void run() {
                    TripDetailsActivity$route$handler$1.m115handleMessage$lambda0(TripDetailsActivity.this);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
