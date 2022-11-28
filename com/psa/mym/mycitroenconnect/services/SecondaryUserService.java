package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CancelSecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CarAccessBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.NewVerifySecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.UpdateSecondaryUserNameBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiInterface;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.DeleteCarResponse;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.secondary_user.AddSecondaryUserError;
import com.psa.mym.mycitroenconnect.model.secondary_user.AddSecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserCommonResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserVerify;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SecondaryUserService extends BaseService {
    public final void addChildAccount(@NotNull final Context context, @NotNull SecondaryUserBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e("addChildAccount");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).addChildAccount(body).enqueue(new Callback<AddSecondaryUserResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$addChildAccount$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<AddSecondaryUserResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<AddSecondaryUserResponse> call, @NotNull Response<AddSecondaryUserResponse> response) {
                AddSecondaryUserResponse addSecondaryUserResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                try {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (!response.isSuccessful()) {
                    if (response.code() == 409) {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        AddSecondaryUserError errorResponse = (AddSecondaryUserError) gson.fromJson(errorBody.string(), (Class<Object>) AddSecondaryUserError.class);
                        SecondaryUserService secondaryUserService = SecondaryUserService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(secondaryUserService, errorResponse, false, 2, null);
                    }
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    AddSecondaryUserResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    addSecondaryUserResponse = body2;
                } else if (code != 409) {
                    ResponseBody errorBody2 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody2);
                    addSecondaryUserResponse = new ErrorResponse(errorBody2.string(), response.code(), "AddChildAccount");
                } else {
                    Gson gson2 = new Gson();
                    ResponseBody errorBody3 = response.errorBody();
                    Intrinsics.checkNotNull(errorBody3);
                    AddSecondaryUserError errorResponse2 = (AddSecondaryUserError) gson2.fromJson(errorBody3.string(), (Class<Object>) AddSecondaryUserError.class);
                    SecondaryUserService secondaryUserService2 = SecondaryUserService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse2, "errorResponse");
                    BaseService.postResponse$default(secondaryUserService2, errorResponse2, false, 2, null);
                }
                BaseService.postResponse$default(SecondaryUserService.this, addSecondaryUserResponse, false, 2, null);
            }
        });
    }

    public final void callDeleteSecondaryUser(@NotNull final Context context, @NotNull String userName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Logger.INSTANCE.e(AppConstants.API_NAME_DELETE_SECONDARY_USER);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).deleteSecondaryUserName(userName).enqueue(new Callback<SecondaryUserCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$callDeleteSecondaryUser$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Response<SecondaryUserCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    SecondaryUserCommonResponse secondaryUserCommonResponse = body;
                    secondaryUserCommonResponse.setApiName(AppConstants.API_NAME_DELETE_SECONDARY_USER);
                    obj = secondaryUserCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), AppConstants.API_NAME_DELETE_SECONDARY_USER);
                }
                BaseService.postResponse$default(SecondaryUserService.this, obj, false, 2, null);
            }
        });
    }

    public final void callGiveAccessSecondaryUser(@NotNull final Context context, @Body @NotNull CarAccessBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e(AppConstants.API_GIVE_ACCESS_SECONDARY_USER);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).giveAccessSecondaryUser(body).enqueue(new Callback<SecondaryUserCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$callGiveAccessSecondaryUser$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Response<SecondaryUserCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    SecondaryUserCommonResponse secondaryUserCommonResponse = body2;
                    secondaryUserCommonResponse.setApiName(AppConstants.API_GIVE_ACCESS_SECONDARY_USER);
                    obj = secondaryUserCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), AppConstants.API_GIVE_ACCESS_SECONDARY_USER);
                }
                BaseService.postResponse$default(SecondaryUserService.this, obj, false, 2, null);
            }
        });
    }

    public final void callRevokeSecondaryUser(@NotNull final Context context, @Body @NotNull CarAccessBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e(AppConstants.API_REVOKE_ACCESS_SECONDARY_USER);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).revokeAccessSecondaryUser(body).enqueue(new Callback<SecondaryUserCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$callRevokeSecondaryUser$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Response<SecondaryUserCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    SecondaryUserCommonResponse secondaryUserCommonResponse = body2;
                    secondaryUserCommonResponse.setApiName(AppConstants.API_REVOKE_ACCESS_SECONDARY_USER);
                    obj = secondaryUserCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), AppConstants.API_REVOKE_ACCESS_SECONDARY_USER);
                }
                BaseService.postResponse$default(SecondaryUserService.this, obj, false, 2, null);
            }
        });
    }

    public final void callUpdateSecondaryUserName(@NotNull final Context context, @Body @NotNull UpdateSecondaryUserNameBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e(AppConstants.API_NAME_UPDATE_SECONDARY_USER_NAME);
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).updateSecondaryUserName(body).enqueue(new Callback<SecondaryUserCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$callUpdateSecondaryUserName$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserCommonResponse> call, @NotNull Response<SecondaryUserCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    SecondaryUserCommonResponse secondaryUserCommonResponse = body2;
                    secondaryUserCommonResponse.setApiName(AppConstants.API_NAME_UPDATE_SECONDARY_USER_NAME);
                    obj = secondaryUserCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), AppConstants.API_NAME_UPDATE_SECONDARY_USER_NAME);
                }
                BaseService.postResponse$default(SecondaryUserService.this, obj, false, 2, null);
            }
        });
    }

    public final void cancelChildAccount(@NotNull final Context context, @Body @NotNull CancelSecondaryUserBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e("cancelChildAccountInvitation");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).cancelSecondaryUserInvitation(body).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$cancelChildAccount$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Object obj;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    PostCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    PostCommonResponse postCommonResponse = body2;
                    postCommonResponse.setApiName("CancelChildAccount");
                    obj = postCommonResponse;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    obj = new ErrorResponse(errorBody.string(), response.code(), "CancelChildAccount");
                }
                BaseService.postResponse$default(SecondaryUserService.this, obj, false, 2, null);
            }
        });
    }

    public final void deleteMyCar(@NotNull final Context context, @NotNull String mobileNumber, @NotNull String vinNumber) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(vinNumber, "vinNumber");
        Logger.INSTANCE.e("Delete My Car");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).deleteMyCar(mobileNumber, vinNumber).enqueue(new Callback<DeleteCarResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$deleteMyCar$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<DeleteCarResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<DeleteCarResponse> call, @NotNull Response<DeleteCarResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 400) {
                    SecondaryUserService secondaryUserService = SecondaryUserService.this;
                    DeleteCarResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    BaseService.postResponse$default(secondaryUserService, body, false, 2, null);
                    return;
                }
                Gson gson = new Gson();
                ResponseBody errorBody = response.errorBody();
                Intrinsics.checkNotNull(errorBody);
                ValidationErrorResponse errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                SecondaryUserService secondaryUserService2 = SecondaryUserService.this;
                Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                BaseService.postResponse$default(secondaryUserService2, errorResponse, false, 2, null);
            }
        });
    }

    public final void getChildAccountList(@NotNull final Context context, @NotNull String userName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Logger.INSTANCE.e("ChildAccountList");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).getChildAccountList(userName).enqueue(new Callback<SecondaryUserResponse>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$getChildAccountList$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserResponse> call, @NotNull Response<SecondaryUserResponse> response) {
                SecondaryUserResponse secondaryUserResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    secondaryUserResponse = body;
                } else {
                    String string = context.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                    secondaryUserResponse = new ErrorResponse(string, response.code(), "ChildAccountList");
                }
                BaseService.postResponse$default(SecondaryUserService.this, secondaryUserResponse, false, 2, null);
            }
        });
    }

    public final void getMyCarList(@NotNull final Context context, @NotNull String mobileNumber, @NotNull final String screenName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        Logger.INSTANCE.e("getMyCarList");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(context).getMyCarList(mobileNumber).enqueue(new Callback<MyCars>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$getMyCarList$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<MyCars> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<MyCars> call, @NotNull Response<MyCars> response) {
                String str;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code != 200 && code != 201) {
                    String string = context.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                    BaseService.postResponse$default(this, new ErrorResponse(string, response.code(), "MyCarsList"), false, 2, null);
                    return;
                }
                MyCars body = response.body();
                Intrinsics.checkNotNull(body);
                MyCars myCars = body;
                if (myCars != null && (!myCars.isEmpty()) && myCars.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList<MyCar> arrayList2 = new ArrayList();
                    for (MyCar myCar : myCars) {
                        String userType = myCar.getUserType();
                        if (userType != null) {
                            str = userType.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        } else {
                            str = null;
                        }
                        if (Intrinsics.areEqual(str, "p")) {
                            arrayList2.add(myCar);
                        }
                    }
                    for (MyCar myCar2 : arrayList2) {
                        RegisteredVehicleItem registeredVehicleItem = new RegisteredVehicleItem(null, null, null, null, null, false, 0, false, false, null, 1023, null);
                        registeredVehicleItem.setCarModelName(myCar2.getCarModelName());
                        registeredVehicleItem.setVehicleNumber(myCar2.getVehicleRegNo());
                        registeredVehicleItem.setVinNum(myCar2.getVinNum());
                        registeredVehicleItem.setVehicleType(String.valueOf(myCar2.getVehicleType()));
                        registeredVehicleItem.setVehicleImage(myCar2.getVehicleImage());
                        registeredVehicleItem.setVehicleSelected(myCar2.isVehicleSelected());
                        registeredVehicleItem.setAccessible(myCar2.isAccessible());
                        registeredVehicleItem.setViewOnly(myCar2.isViewOnly());
                        registeredVehicleItem.setInviteStatus(myCar2.getInviteStatus());
                        arrayList.add(registeredVehicleItem);
                    }
                    SharedPref.Companion.saveRegisteredVehicleList(context, arrayList);
                }
                BaseService.postResponse$default(this, new MyCarResponse(myCars, screenName), false, 2, null);
            }
        });
    }

    public final void verifyChildAccount(@NotNull final Context context, @Body @NotNull NewVerifySecondaryUserBody body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e("verifyChildAccount");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).verifySecondaryUserInvitation(body).enqueue(new Callback<SecondaryUserVerify>() { // from class: com.psa.mym.mycitroenconnect.services.SecondaryUserService$verifyChildAccount$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SecondaryUserVerify> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                SecondaryUserService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SecondaryUserVerify> call, @NotNull Response<SecondaryUserVerify> response) {
                SecondaryUserVerify secondaryUserVerify;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    SecondaryUserService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    SecondaryUserVerify body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    secondaryUserVerify = body2;
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    secondaryUserVerify = new ErrorResponse(errorBody.string(), response.code(), "VerifyChildAccount");
                }
                BaseService.postResponse$default(SecondaryUserService.this, secondaryUserVerify, false, 2, null);
            }
        });
    }
}
