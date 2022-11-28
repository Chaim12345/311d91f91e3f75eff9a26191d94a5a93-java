package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.api.body.onboarding.AddVehicleBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.CreateUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.LoginBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserPassword;
import com.psa.mym.mycitroenconnect.api.body.onboarding.SetPinBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.UpdateProfileBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.VerifyOTPBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiInterface;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.AddVehicleResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePassRequest;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.CreateUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.LoginResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPinResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.VerifyOTPResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class OnBoardingService extends BaseService {
    public static /* synthetic */ void callGetUserTokenAPI$default(OnBoardingService onBoardingService, Activity activity, GetTokenBody getTokenBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callGetUserTokenAPI(activity, getTokenBody, z);
    }

    public static /* synthetic */ void callVerifyOTPAPI$default(OnBoardingService onBoardingService, Activity activity, VerifyOTPBody verifyOTPBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callVerifyOTPAPI(activity, verifyOTPBody, z);
    }

    public static /* synthetic */ void callVerifyOtpChangePassword$default(OnBoardingService onBoardingService, Activity activity, VerifyOTPBody verifyOTPBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callVerifyOtpChangePassword(activity, verifyOTPBody, z);
    }

    public static /* synthetic */ void callVerifyOtpChildInvite$default(OnBoardingService onBoardingService, Activity activity, VerifyOTPBody verifyOTPBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callVerifyOtpChildInvite(activity, verifyOTPBody, z);
    }

    public static /* synthetic */ void callVerifyOtpForgotPassword$default(OnBoardingService onBoardingService, Activity activity, VerifyOTPBody verifyOTPBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callVerifyOtpForgotPassword(activity, verifyOTPBody, z);
    }

    public static /* synthetic */ void callVerifyOtpLoginPassword$default(OnBoardingService onBoardingService, Activity activity, VerifyOTPBody verifyOTPBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        onBoardingService.callVerifyOtpLoginPassword(activity, verifyOTPBody, z);
    }

    public final void callAddVehicleAPI(@NotNull final Activity mActivity, @NotNull AddVehicleBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callAddVehicleAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).addVehicle(body).enqueue(new Callback<AddVehicleResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callAddVehicleAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<AddVehicleResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<AddVehicleResponse> call, @NotNull Response<AddVehicleResponse> response) {
                AddVehicleResponse errorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    AddVehicleResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    errorResponse = body2;
                    onBoardingService = OnBoardingService.this;
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                }
                BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
            }
        });
    }

    public final void callCreateUserAPI(@NotNull final Activity mActivity, @NotNull CreateUserBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callCreateUserAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).createUser(SharedPref.Companion.getTokenMap(mActivity), body).enqueue(new Callback<CreateUserResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callCreateUserAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<CreateUserResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<CreateUserResponse> call, @NotNull Response<CreateUserResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                CreateUserResponse body2 = response.body();
                Intrinsics.checkNotNull(body2);
                BaseService.postResponse$default(OnBoardingService.this, body2, false, 2, null);
            }
        });
    }

    public final void callGetUserProfileAPI(@NotNull final Activity mActivity, @NotNull String body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callGetUserProfileAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).getUserProfile(body).enqueue(new Callback<UserProfileResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callGetUserProfileAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<UserProfileResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<UserProfileResponse> call, @NotNull Response<UserProfileResponse> response) {
                UserProfileResponse userProfileResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                if (response.code() == 200 || response.code() == 201) {
                    UserProfileResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    userProfileResponse = body2;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    userProfileResponse = new ErrorResponse(string, response.code(), "GetUserProfile");
                }
                BaseService.postResponse$default(OnBoardingService.this, userProfileResponse, false, 2, null);
            }
        });
    }

    public final void callGetUserTokenAPI(@NotNull final Activity mActivity, @NotNull GetTokenBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callGetUserTokenAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).getUserToken(body).enqueue(new Callback<Token>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callGetUserTokenAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<Token> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Logger logger = Logger.INSTANCE;
                logger.e("request : " + call.request() + ", response : " + response);
                if (response.isSuccessful()) {
                    Token body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    String string = mActivity.getString(R.string.user_does_not_exist);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.user_does_not_exist)");
                    BaseService.postResponse$default(OnBoardingService.this, new ErrorResponse(string, 0, null, 6, null), false, 2, null);
                }
            }
        });
    }

    public final void callLoginUserAPI(@NotNull final Activity mActivity, @NotNull LoginBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callLoginUserAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).loginUser(body).enqueue(new Callback<LoginResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callLoginUserAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                Gson gson;
                LoginResponse errorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    LoginResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    errorResponse = body2;
                    onBoardingService = OnBoardingService.this;
                } else {
                    if (response.code() == 400) {
                        gson = new Gson();
                    } else if (response.code() != 500) {
                        OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                        return;
                    } else {
                        gson = new Gson();
                    }
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                }
                BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
            }
        });
    }

    public final void callSendOTPAPI(@NotNull final Activity mActivity, @NotNull RegisterUserBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callSendOTPAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).sendOTP(body).enqueue(new Callback<SendOtpResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callSendOTPAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SendOtpResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SendOtpResponse> call, @NotNull Response<SendOtpResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                }
                SendOtpResponse body2 = response.body();
                Intrinsics.checkNotNull(body2);
                BaseService.postResponse$default(OnBoardingService.this, body2, false, 2, null);
            }
        });
    }

    public final void callSetPinAPI(@NotNull final Activity mActivity, @NotNull SetPinBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callSetPinAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).setPin(SharedPref.Companion.getTokenMap(mActivity), body).enqueue(new Callback<SetPinResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callSetPinAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SetPinResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SetPinResponse> call, @NotNull Response<SetPinResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    SetPinResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    BaseService.postResponse$default(OnBoardingService.this, body2, false, 2, null);
                    return;
                }
                if (response.code() == 500 || response.code() == 400) {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        ValidationErrorResponse errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                        OnBoardingService onBoardingService = OnBoardingService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                        return;
                    } catch (Exception unused) {
                    }
                }
                OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
            }
        });
    }

    public final void callUpdateUserProfileAPI(@NotNull final Activity mActivity, @NotNull UpdateProfileBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callUpdateUserProfileAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).updateUser(body).enqueue(new Callback<UpdateUserProfileResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callUpdateUserProfileAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<UpdateUserProfileResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<UpdateUserProfileResponse> call, @NotNull Response<UpdateUserProfileResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    UpdateUserProfileResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    BaseService.postResponse$default(OnBoardingService.this, body2, false, 2, null);
                    return;
                }
                if (response.code() == 400) {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody = response.errorBody();
                        Intrinsics.checkNotNull(errorBody);
                        ValidationErrorResponse errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                        OnBoardingService onBoardingService = OnBoardingService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                        return;
                    } catch (Exception unused) {
                    }
                }
                OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
            }
        });
    }

    public final void callUserRegisterAPI(@NotNull final Activity mActivity, @NotNull RegisterUserBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callUserRegisterAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).registerUser(body).enqueue(new Callback<RegisterUserResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callUserRegisterAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<RegisterUserResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<RegisterUserResponse> call, @NotNull Response<RegisterUserResponse> response) {
                RegisterUserResponse registerErrorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    RegisterUserResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    registerErrorResponse = body2;
                    onBoardingService = OnBoardingService.this;
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    registerErrorResponse = (RegisterErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) RegisterErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(registerErrorResponse, "registerErrorResponse");
                }
                BaseService.postResponse$default(onBoardingService, registerErrorResponse, false, 2, null);
            }
        });
    }

    public final void callVerifyOTPAPI(@NotNull final Activity mActivity, @NotNull VerifyOTPBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callVerifyOTPAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).verifyOTPRegistration(body).enqueue(new Callback<VerifyOTPResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callVerifyOTPAPI$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VerifyOTPResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VerifyOTPResponse> call, @NotNull Response<VerifyOTPResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    VerifyOTPResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                    OnBoardingService onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                }
            }
        });
    }

    public final void callVerifyOtpChangePassword(@NotNull final Activity mActivity, @NotNull VerifyOTPBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callVerifyOTPAPI");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(mActivity).verifyOtpWithChangePassword(body).enqueue(new Callback<VerifyOTPResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callVerifyOtpChangePassword$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VerifyOTPResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VerifyOTPResponse> call, @NotNull Response<VerifyOTPResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    VerifyOTPResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                    OnBoardingService onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                }
            }
        });
    }

    public final void callVerifyOtpChildInvite(@NotNull final Activity mActivity, @NotNull VerifyOTPBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("verify otp for child invite");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).verifyOTP(body).enqueue(new Callback<VerifyOTPResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callVerifyOtpChildInvite$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VerifyOTPResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VerifyOTPResponse> call, @NotNull Response<VerifyOTPResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    VerifyOTPResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                    OnBoardingService onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                }
            }
        });
    }

    public final void callVerifyOtpForgotPassword(@NotNull final Activity mActivity, @NotNull VerifyOTPBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callVerifyOTPAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).setNewForgotPassword(body).enqueue(new Callback<VerifyOTPResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callVerifyOtpForgotPassword$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VerifyOTPResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VerifyOTPResponse> call, @NotNull Response<VerifyOTPResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    VerifyOTPResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                    OnBoardingService onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                }
            }
        });
    }

    public final void callVerifyOtpLoginPassword(@NotNull final Activity mActivity, @NotNull VerifyOTPBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callVerifyOTPAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).verifyOtpWithPassword(body).enqueue(new Callback<VerifyOTPResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$callVerifyOtpLoginPassword$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VerifyOTPResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VerifyOTPResponse> call, @NotNull Response<VerifyOTPResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    VerifyOTPResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    OnBoardingService.this.postResponse(body2, z);
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody.string(), (Class<Object>) FailResponse.class);
                    OnBoardingService onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                }
            }
        });
    }

    public final void changePassword(@NotNull final Activity mActivity, @NotNull ChangePassRequest body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callSendOTPAPI");
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).changePassword(body).enqueue(new Callback<ChangePasswordResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$changePassword$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ChangePasswordResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ChangePasswordResponse> call, @NotNull Response<ChangePasswordResponse> response) {
                ChangePasswordResponse registerErrorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    ChangePasswordResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    registerErrorResponse = body2;
                    onBoardingService = OnBoardingService.this;
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    registerErrorResponse = (RegisterErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) RegisterErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(registerErrorResponse, "registerErrorResponse");
                }
                BaseService.postResponse$default(onBoardingService, registerErrorResponse, false, 2, null);
            }
        });
    }

    public final void sendForgotPasswordOtp(@NotNull final Activity mActivity, @NotNull RegisterUserBody body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("sendForgotPasswordOtp");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).forgotPassword(body).enqueue(new Callback<SendOtpResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$sendForgotPasswordOtp$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SendOtpResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SendOtpResponse> call, @NotNull Response<SendOtpResponse> response) {
                SendOtpResponse registerErrorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    SendOtpResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    registerErrorResponse = body2;
                    onBoardingService = OnBoardingService.this;
                } else if (response.code() != 400) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    registerErrorResponse = (RegisterErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) RegisterErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(registerErrorResponse, "registerErrorResponse");
                }
                BaseService.postResponse$default(onBoardingService, registerErrorResponse, false, 2, null);
            }
        });
    }

    public final void setNewPasswordForgotPassword(@NotNull final Activity mActivity, @NotNull RegisterUserPassword body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callLoginUserAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).setNewPasswordForgotPassword(body).enqueue(new Callback<SetPasswordResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$setNewPasswordForgotPassword$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SetPasswordResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SetPasswordResponse> call, @NotNull Response<SetPasswordResponse> response) {
                SetPasswordResponse errorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    SetPasswordResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    errorResponse = body2;
                } else if (response.code() == 400) {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                } else if (response.code() != 500) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.user_does_not_exist);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.user_does_not_exist)");
                    errorResponse = new ErrorResponse(string, 0, null, 6, null);
                }
                onBoardingService = OnBoardingService.this;
                BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
            }
        });
    }

    public final void setNewPasswordRegistration(@NotNull final Activity mActivity, @NotNull RegisterUserPassword body) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.d("callLoginUserAPI");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).setNewPasswordRegister(body).enqueue(new Callback<SetPasswordResponse>() { // from class: com.psa.mym.mycitroenconnect.services.OnBoardingService$setNewPasswordRegistration$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<SetPasswordResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                OnBoardingService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<SetPasswordResponse> call, @NotNull Response<SetPasswordResponse> response) {
                SetPasswordResponse errorResponse;
                OnBoardingService onBoardingService;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    SetPasswordResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    errorResponse = body2;
                } else if (response.code() == 400) {
                    Gson gson = new Gson();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    errorResponse = (ValidationErrorResponse) gson.fromJson(errorBody.string(), (Class<Object>) ValidationErrorResponse.class);
                    onBoardingService = OnBoardingService.this;
                    Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                    BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
                } else if (response.code() != 500) {
                    OnBoardingService.this.showHttpErrorToast(response.code(), mActivity);
                    return;
                } else {
                    String string = mActivity.getString(R.string.user_does_not_exist);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.user_does_not_exist)");
                    errorResponse = new ErrorResponse(string, 0, null, 6, null);
                }
                onBoardingService = OnBoardingService.this;
                BaseService.postResponse$default(onBoardingService, errorResponse, false, 2, null);
            }
        });
    }
}
