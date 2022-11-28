package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.psa.mym.mycitroenconnect.api.body.UpdateTripNameBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.trip.DrivingBehaviourResponse;
import com.psa.mym.mycitroenconnect.model.trip.EnergyUsageResponse;
import com.psa.mym.mycitroenconnect.model.trip.EnergyUsageResponseItem;
import com.psa.mym.mycitroenconnect.model.trip.FuelReportResponse;
import com.psa.mym.mycitroenconnect.model.trip.IdleDetailsResponse;
import com.psa.mym.mycitroenconnect.model.trip.OnGoingResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripDetailsResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripListResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class TripService extends BaseService {
    public final void callGetAllTripDetailsAPI(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String starDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(starDate, "starDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Logger.INSTANCE.d("callGetAllTripDetailsAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getAllTripDetails(vinNum, starDate, endDate).enqueue(new Callback<TripListResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetAllTripDetailsAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<TripListResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<TripListResponse> call, @NotNull Response<TripListResponse> response) {
                TripListResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    TripListResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_trips_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_trips_available)");
                    errorResponse = new ErrorResponse(string, response.code(), AppConstants.API_NAME_ALL_TRIP_LIST);
                }
                BaseService.postResponse$default(TripService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void callGetEnergyUsage(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String starDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(starDate, "starDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Logger.INSTANCE.d("callGetEnergyUsage");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getEnergyUsage(vinNum, starDate, endDate).enqueue(new Callback<ArrayList<EnergyUsageResponseItem>>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetEnergyUsage$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ArrayList<EnergyUsageResponseItem>> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ArrayList<EnergyUsageResponseItem>> call, @NotNull Response<ArrayList<EnergyUsageResponseItem>> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    ArrayList<EnergyUsageResponseItem> body = response.body();
                    Intrinsics.checkNotNull(body);
                    BaseService.postResponse$default(TripService.this, new EnergyUsageResponse(body), false, 2, null);
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    BaseService.postResponse$default(TripService.this, new FailResponse(string, response.code(), "EnergyUsage"), false, 2, null);
                }
            }
        });
    }

    public final void callGetFuelReport(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String starDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(starDate, "starDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Logger.INSTANCE.d("callGetTripDetailsByID");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getFuelReport(vinNum, starDate, endDate).enqueue(new Callback<FuelReportResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetFuelReport$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<FuelReportResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<FuelReportResponse> call, @NotNull Response<FuelReportResponse> response) {
                FuelReportResponse failResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    FuelReportResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    failResponse = body;
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    failResponse = new FailResponse(string, response.code(), "FuelReport");
                }
                BaseService.postResponse$default(TripService.this, failResponse, false, 2, null);
            }
        });
    }

    public final void callGetIdleDetails(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String starDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(starDate, "starDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Logger.INSTANCE.d("callGetIdleDetails");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getIdleDetails(vinNum, starDate, endDate).enqueue(new Callback<IdleDetailsResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetIdleDetails$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<IdleDetailsResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<IdleDetailsResponse> call, @NotNull Response<IdleDetailsResponse> response) {
                IdleDetailsResponse failResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    IdleDetailsResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    failResponse = body;
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    failResponse = new FailResponse(string, response.code(), "GetIdleDetails");
                }
                BaseService.postResponse$default(TripService.this, failResponse, false, 2, null);
            }
        });
    }

    public final void callGetTripDetailsByID(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String tripId) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Logger.INSTANCE.d("callGetTripDetailsByID");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getTripDetailsByID(vinNum, tripId).enqueue(new Callback<TripDetailsResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetTripDetailsByID$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<TripDetailsResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<TripDetailsResponse> call, @NotNull Response<TripDetailsResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                TripDetailsResponse body = response.body();
                Intrinsics.checkNotNull(body);
                BaseService.postResponse$default(TripService.this, body, false, 2, null);
            }
        });
    }

    public final void callGetTripSummaryAPI(@NotNull final Activity mActivity, @NotNull String vinNum, @NotNull String starDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(starDate, "starDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Logger.INSTANCE.d("callGetTripSummaryAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getTripSummary(vinNum, starDate, endDate).enqueue(new Callback<DrivingBehaviourResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callGetTripSummaryAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<DrivingBehaviourResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<DrivingBehaviourResponse> call, @NotNull Response<DrivingBehaviourResponse> response) {
                DrivingBehaviourResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    DrivingBehaviourResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, 204, "summary");
                }
                BaseService.postResponse$default(TripService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void callOnGoingAPI(@NotNull final Activity mActivity, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.d("callOnGoingAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).onGoing(vinNum).enqueue(new Callback<OnGoingResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$callOnGoingAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<OnGoingResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<OnGoingResponse> call, @NotNull Response<OnGoingResponse> response) {
                OnGoingResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.code() == 200) {
                    OnGoingResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    errorResponse = body;
                } else if (response.code() != 204) {
                    TripService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), AppConstants.API_NAME_ONGOING);
                }
                BaseService.postResponse$default(TripService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void updateTripName(@NotNull final Context context, @NotNull String vinNum, @NotNull String tripId, @NotNull String tripName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(tripId, "tripId");
        Intrinsics.checkNotNullParameter(tripName, "tripName");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).updateTripName(vinNum, new UpdateTripNameBody(tripId, tripName)).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.TripService$updateTripName$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                TripService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    TripService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    PostCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    PostCommonResponse postCommonResponse = body;
                    postCommonResponse.setApiName(AppConstants.API_NAME_UPDATE_TRIP_NAME);
                    obj = postCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), AppConstants.API_NAME_UPDATE_TRIP_NAME);
                }
                BaseService.postResponse$default(TripService.this, obj, false, 2, null);
            }
        });
    }
}
