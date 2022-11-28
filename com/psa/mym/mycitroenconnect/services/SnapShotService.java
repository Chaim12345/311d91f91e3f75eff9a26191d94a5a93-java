package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SnapShotService extends BaseService {
    public final void getVehicleStatus(@NotNull final Context context, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.e("Vehicle Status");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).getVehicleStatus(vinNum).enqueue(new Callback<VehicleStatus>() { // from class: com.psa.mym.mycitroenconnect.services.SnapShotService$getVehicleStatus$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VehicleStatus> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SnapShotService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VehicleStatus> call, @NotNull Response<VehicleStatus> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SnapShotService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200) {
                    VehicleStatus body = response.body();
                    if (body != null) {
                        BaseService.postResponse$default(SnapShotService.this, body, false, 2, null);
                    }
                } else if (code != 204) {
                    SnapShotService.this.postHttpErrorResponse((Activity) context, response.code(), AppConstants.API_NAME_SNAP_VEHICLE_STATUS);
                } else {
                    SnapShotService snapShotService = SnapShotService.this;
                    String string = context.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                    BaseService.postResponse$default(snapShotService, new ErrorResponse(string, response.code(), AppConstants.API_NAME_SNAP_VEHICLE_STATUS), false, 2, null);
                }
            }
        });
    }
}
