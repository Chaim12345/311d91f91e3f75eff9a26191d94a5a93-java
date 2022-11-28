package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyAlertBody;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.api.body.dashboard.RefreshCommandBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardDetailsResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyAlertResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.GetEmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DashboardService extends BaseService {
    public final void callAddEmergencyContactAPI(@NotNull final Activity mActivity, @NotNull EmergencyContactBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callAddEmergencyContactAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).addEmergencyContact(body).enqueue(new Callback<EmergencyContactResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callAddEmergencyContactAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<EmergencyContactResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<EmergencyContactResponse> call, @NotNull Response<EmergencyContactResponse> response) {
                EmergencyContactResponse emergencyContactResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    DashboardService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                if (response.code() == 201 || response.code() == 200) {
                    EmergencyContactResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    emergencyContactResponse = body2;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    emergencyContactResponse = new ErrorResponse(string, response.code(), null, 4, null);
                }
                BaseService.postResponse$default(DashboardService.this, emergencyContactResponse, false, 2, null);
            }
        });
    }

    public final void callDashboardAPI(@NotNull final Activity mActivity, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.d("callDashboardAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getDashboardInfo(vinNum).enqueue(new Callback<DashboardResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callDashboardAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<DashboardResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<DashboardResponse> call, @NotNull Response<DashboardResponse> response) {
                DashboardResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    DashboardResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else if (response.code() != 204) {
                    DashboardService.this.postHttpErrorResponse(mActivity, response.code(), AppConstants.API_NAME_DASHBOARD);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), AppConstants.API_NAME_DASHBOARD);
                }
                BaseService.postResponse$default(DashboardService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void callDashboardDetailsAPI(@NotNull final Activity mActivity, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.d("callDashboardDetailsAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getDashboardDetails(vinNum).enqueue(new Callback<DashboardDetailsResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callDashboardDetailsAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<DashboardDetailsResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<DashboardDetailsResponse> call, @NotNull Response<DashboardDetailsResponse> response) {
                DashboardDetailsResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    DashboardDetailsResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else if (response.code() != 204) {
                    DashboardService.this.postHttpErrorResponse(mActivity, response.code(), "");
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), null, 4, null);
                }
                BaseService.postResponse$default(DashboardService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void callEmergencyAlertAPI(@NotNull final Activity mActivity, @NotNull EmergencyAlertBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callEmergencyAlertAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).onEmergencyAlert(body).enqueue(new Callback<EmergencyAlertResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callEmergencyAlertAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<EmergencyAlertResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<EmergencyAlertResponse> call, @NotNull Response<EmergencyAlertResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() != 201) {
                    this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                EmergencyAlertResponse emergencyAlertResponse = new EmergencyAlertResponse(null, 0, 3, null);
                String string = mActivity.getString(R.string.sos_request_sent);
                Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.sos_request_sent)");
                emergencyAlertResponse.setMessage(string);
                BaseService.postResponse$default(this, emergencyAlertResponse, false, 2, null);
            }
        });
    }

    public final void callGetEmergencyContactAPI(@NotNull final Activity mActivity, @NotNull String userName) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Logger.INSTANCE.d("callGetEmergencyContactAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).getEmergencyContact(userName).enqueue(new Callback<GetEmergencyContactResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callGetEmergencyContactAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<GetEmergencyContactResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<GetEmergencyContactResponse> call, @NotNull Response<GetEmergencyContactResponse> response) {
                GetEmergencyContactResponse getEmergencyContactResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    DashboardService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                if (response.code() == 200 || response.code() == 201) {
                    GetEmergencyContactResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    getEmergencyContactResponse = body;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    getEmergencyContactResponse = new ErrorResponse(string, response.code(), "EmergencyContacts");
                }
                BaseService.postResponse$default(DashboardService.this, getEmergencyContactResponse, false, 2, null);
            }
        });
    }

    public final void callGetVehicleCurrentLocation(@NotNull final Activity mActivity, @NotNull String userName) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Logger.INSTANCE.d("callGetVehicleCurrentLocation");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getCurrentVehicleLocation(userName).enqueue(new Callback<VehicleLocationResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callGetVehicleCurrentLocation$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VehicleLocationResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VehicleLocationResponse> call, @NotNull Response<VehicleLocationResponse> response) {
                VehicleLocationResponse vehicleLocationResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    DashboardService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                if (response.code() == 200 || response.code() == 201) {
                    VehicleLocationResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    vehicleLocationResponse = body;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    vehicleLocationResponse = new ErrorResponse(string, response.code(), AppConstants.API_VEHICLE_LOCATION);
                }
                BaseService.postResponse$default(DashboardService.this, vehicleLocationResponse, false, 2, null);
            }
        });
    }

    public final void callNewRefreshCommandAPI(@NotNull final Activity mActivity, @NotNull RefreshCommandBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callRefreshCommandAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).newRefreshCommand(body).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callNewRefreshCommandAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() != 200 && response.code() != 201) {
                    DashboardService.this.postHttpErrorResponse(mActivity, response.code(), "RefreshCommand");
                    return;
                }
                PostCommonResponse body2 = response.body();
                Intrinsics.checkNotNull(body2);
                PostCommonResponse postCommonResponse = body2;
                postCommonResponse.setMessage("Refresh Command sent successfully!");
                postCommonResponse.setApiName("RefreshCommand");
                BaseService.postResponse$default(DashboardService.this, postCommonResponse, false, 2, null);
            }
        });
    }

    public final void callRefreshCommandAPI(@NotNull final Activity mActivity, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.d("callRefreshCommandAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).refreshCommand(vinNum).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callRefreshCommandAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200 || response.code() == 201) {
                    PostCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    PostCommonResponse postCommonResponse = body;
                    String string = mActivity.getString(R.string.refresh_command_sent);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.refresh_command_sent)");
                    postCommonResponse.setMessage(string);
                    postCommonResponse.setApiName("RefreshCommand");
                    BaseService.postResponse$default(this, postCommonResponse, false, 2, null);
                    return;
                }
                if (response.code() == 500) {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        PostCommonResponse model = (PostCommonResponse) gson.fromJson(errorBody.string(), (Class<Object>) PostCommonResponse.class);
                        String string2 = mActivity.getString(R.string.refresh_command_sent);
                        Intrinsics.checkNotNullExpressionValue(string2, "mActivity.getString(R.string.refresh_command_sent)");
                        model.setMessage(string2);
                        DashboardService dashboardService = this;
                        Intrinsics.checkNotNullExpressionValue(model, "model");
                        BaseService.postResponse$default(dashboardService, model, false, 2, null);
                        return;
                    } catch (Exception unused) {
                    }
                }
                this.postHttpErrorResponse(mActivity, response.code(), "");
            }
        });
    }

    public final void callUpdateEmergencyContactAPI(@NotNull final Activity mActivity, @NotNull EmergencyContactBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callUpdateEmergencyContactAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).updateEmergencyContact(body).enqueue(new Callback<EmergencyContactResponse>() { // from class: com.psa.mym.mycitroenconnect.services.DashboardService$callUpdateEmergencyContactAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<EmergencyContactResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                DashboardService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<EmergencyContactResponse> call, @NotNull Response<EmergencyContactResponse> response) {
                EmergencyContactResponse emergencyContactResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    DashboardService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                if (response.code() == 200 || response.code() == 201) {
                    EmergencyContactResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    emergencyContactResponse = body2;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    emergencyContactResponse = new ErrorResponse(string, response.code(), null, 4, null);
                }
                BaseService.postResponse$default(DashboardService.this, emergencyContactResponse, false, 2, null);
            }
        });
    }
}
