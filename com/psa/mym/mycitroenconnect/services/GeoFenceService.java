package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.PostGeoFenceResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class GeoFenceService extends BaseService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String activeDeactiveGeoFence = "ActiveDeactiveGeoFence";
    @NotNull
    public static final String createGeoFence = "CreateGeoFence";
    @NotNull
    public static final String deleteGeoFence = "DeleteGeoFence";
    @NotNull
    public static final String disableAllGeoFence = "DisableAllGeoFence";
    @NotNull
    public static final String getGeoFence = "GetGeoFence";
    @NotNull
    public static final String updateGeoFence = "UpdateGeoFence";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void activeDeactiveGeoFence(@NotNull final Context context, @NotNull String vinNum, @NotNull String fenceId, @NotNull String fenceStatus) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(fenceId, "fenceId");
        Intrinsics.checkNotNullParameter(fenceStatus, "fenceStatus");
        Logger.INSTANCE.e(activeDeactiveGeoFence);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).activeDeactiveFence(vinNum, fenceId, fenceStatus).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$activeDeactiveGeoFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200) {
                    GeoFenceService geoFenceService = GeoFenceService.this;
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    geoFenceService.postErrorResponse(errorBody.string(), response.code(), GeoFenceService.activeDeactiveGeoFence);
                } else {
                    PostCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    body.setApiName(GeoFenceService.activeDeactiveGeoFence);
                    GeoFenceService geoFenceService2 = GeoFenceService.this;
                    PostCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    geoFenceService2.postResponse(body2, false);
                }
            }
        });
    }

    public final void createGeoFence(@NotNull final Context context, @NotNull String vinNum, @NotNull GeoFenceBody geoFenceBody) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(geoFenceBody, "geoFenceBody");
        Logger.INSTANCE.e("PostGeoFence");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).createGeoFence(vinNum, geoFenceBody).enqueue(new Callback<PostGeoFenceResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$createGeoFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostGeoFenceResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostGeoFenceResponse> call, @NotNull Response<PostGeoFenceResponse> response) {
                FailResponse errorResponse;
                GeoFenceService geoFenceService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    int code = response.code();
                    if (code == 201) {
                        PostGeoFenceResponse body = response.body();
                        Intrinsics.checkNotNull(body);
                        body.setApiName(GeoFenceService.createGeoFence);
                        GeoFenceService geoFenceService2 = GeoFenceService.this;
                        PostGeoFenceResponse body2 = response.body();
                        Intrinsics.checkNotNull(body2);
                        geoFenceService2.postResponse(body2, false);
                        return;
                    }
                    if (code == 400) {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                        errorResponse.setApiName(GeoFenceService.createGeoFence);
                        geoFenceService = GeoFenceService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    }
                    GeoFenceService geoFenceService3 = GeoFenceService.this;
                    ResponseBody errorBody2 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody2);
                    geoFenceService3.postErrorResponse(errorBody2.string(), response.code(), GeoFenceService.createGeoFence);
                    return;
                } else if (response.code() != 400) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                } else {
                    Gson gson2 = new Gson();
                    ResponseBody errorBody3 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody3);
                    errorResponse = (FailResponse) gson2.fromJson(errorBody3.string(), (Class<Object>) FailResponse.class);
                    errorResponse.setApiName(GeoFenceService.createGeoFence);
                    geoFenceService = GeoFenceService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                }
                BaseService.postResponse$default(geoFenceService, errorResponse, false, 2, null);
            }
        });
    }

    public final void deleteFence(@NotNull final Context context, @NotNull String vinNum, @NotNull String fenceId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(fenceId, "fenceId");
        Logger.INSTANCE.e(deleteGeoFence);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).deleteFence(vinNum, fenceId).enqueue(new Callback<PostGeoFenceResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$deleteFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostGeoFenceResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostGeoFenceResponse> call, @NotNull Response<PostGeoFenceResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200) {
                    GeoFenceService geoFenceService = GeoFenceService.this;
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    geoFenceService.postErrorResponse(errorBody.string(), response.code(), GeoFenceService.deleteGeoFence);
                } else {
                    PostGeoFenceResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    body.setApiName(GeoFenceService.deleteGeoFence);
                    GeoFenceService geoFenceService2 = GeoFenceService.this;
                    PostGeoFenceResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    BaseService.postResponse$default(geoFenceService2, body2, false, 2, null);
                }
            }
        });
    }

    public final void disableAllGeoFence(@NotNull final Context context, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.e(disableAllGeoFence);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).disableAllGeoFence(vinNum).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$disableAllGeoFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200) {
                    GeoFenceService geoFenceService = GeoFenceService.this;
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    geoFenceService.postErrorResponse(errorBody.string(), response.code(), GeoFenceService.disableAllGeoFence);
                } else {
                    PostCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    body.setApiName(GeoFenceService.disableAllGeoFence);
                    GeoFenceService geoFenceService2 = GeoFenceService.this;
                    PostCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    BaseService.postResponse$default(geoFenceService2, body2, false, 2, null);
                }
            }
        });
    }

    public final void getGeoFence(@NotNull final Context context, @NotNull String vinNum, @NotNull final String type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(type, "type");
        Logger.INSTANCE.e(getGeoFence);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).getGeofence(vinNum, type).enqueue(new Callback<GetGeoFenceResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$getGeoFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<GetGeoFenceResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<GetGeoFenceResponse> call, @NotNull Response<GetGeoFenceResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                int code = response.code();
                if (code == 200) {
                    GeoFenceService geoFenceService = GeoFenceService.this;
                    GetGeoFenceResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    BaseService.postResponse$default(geoFenceService, body, false, 2, null);
                } else if (code != 204) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else {
                    GeoFenceService geoFenceService2 = GeoFenceService.this;
                    String string = context.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                    BaseService.postResponse$default(geoFenceService2, new ErrorResponse(string, response.code(), type), false, 2, null);
                }
            }
        });
    }

    public final void updateGeoFence(@NotNull final Context context, @NotNull String vinNum, @NotNull String fenceId, @NotNull GeoFenceBody geoFenceBody) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(fenceId, "fenceId");
        Intrinsics.checkNotNullParameter(geoFenceBody, "geoFenceBody");
        Logger.INSTANCE.e(updateGeoFence);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).updateGeoFence(vinNum, fenceId, geoFenceBody).enqueue(new Callback<PostGeoFenceResponse>() { // from class: com.psa.mym.mycitroenconnect.services.GeoFenceService$updateGeoFence$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostGeoFenceResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                GeoFenceService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostGeoFenceResponse> call, @NotNull Response<PostGeoFenceResponse> response) {
                FailResponse errorResponse;
                GeoFenceService geoFenceService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    int code = response.code();
                    if (code == 200) {
                        PostGeoFenceResponse body = response.body();
                        Intrinsics.checkNotNull(body);
                        body.setApiName(GeoFenceService.updateGeoFence);
                        GeoFenceService geoFenceService2 = GeoFenceService.this;
                        PostGeoFenceResponse body2 = response.body();
                        Intrinsics.checkNotNull(body2);
                        geoFenceService2.postResponse(body2, false);
                        return;
                    }
                    if (code == 400) {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                        errorResponse.setApiName(GeoFenceService.updateGeoFence);
                        geoFenceService = GeoFenceService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    }
                    GeoFenceService geoFenceService3 = GeoFenceService.this;
                    ResponseBody errorBody2 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody2);
                    geoFenceService3.postErrorResponse(errorBody2.string(), response.code(), GeoFenceService.updateGeoFence);
                    return;
                } else if (response.code() != 400) {
                    GeoFenceService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                } else {
                    Gson gson2 = new Gson();
                    ResponseBody errorBody3 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody3);
                    errorResponse = (FailResponse) gson2.fromJson(errorBody3.string(), (Class<Object>) FailResponse.class);
                    errorResponse.setApiName(GeoFenceService.updateGeoFence);
                    geoFenceService = GeoFenceService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                }
                BaseService.postResponse$default(geoFenceService, errorResponse, false, 2, null);
            }
        });
    }
}
