package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiInterface;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ProfileService extends BaseService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String deleteProfilePic = "delete_profile_pic";
    @NotNull
    public static final String getProfilePic = "get_profile_pic";
    @NotNull
    public static final String uploadProfilePic = "upload_profile_pic";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void deleteProfilePic(@NotNull final Context context, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).deleteProfilePic(mobileNumber).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.ProfileService$deleteProfilePic$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                ProfileService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    ProfileService profileService = ProfileService.this;
                    int code = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    profileService.showResponseUnsuccessful(code, errorBody.string(), ProfileService.deleteProfilePic, (Activity) context);
                    return;
                }
                int code2 = response.code();
                if (code2 == 200) {
                    PostCommonResponse body = response.body();
                    if (body != null) {
                        ProfileService profileService2 = ProfileService.this;
                        body.setApiName(ProfileService.deleteProfilePic);
                        profileService2.postResponse(body, false);
                    }
                } else if (code2 != 400) {
                    String string = context.getString(R.string.profile_pic_not_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…rofile_pic_not_available)");
                    BaseService.postResponse$default(ProfileService.this, new ErrorResponse(string, response.code(), ProfileService.deleteProfilePic), false, 2, null);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody2 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody2);
                        FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody2.string(), (Class<Object>) FailResponse.class);
                        errorResponse.setApiName(ProfileService.deleteProfilePic);
                        ProfileService profileService3 = ProfileService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(profileService3, errorResponse, false, 2, null);
                    } catch (Exception unused) {
                        ProfileService profileService4 = ProfileService.this;
                        ResponseBody errorBody3 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody3);
                        profileService4.postErrorResponse(errorBody3.string(), response.code(), ProfileService.deleteProfilePic);
                    }
                }
            }
        });
    }

    public final void getProfilePic(@NotNull final Context context, @NotNull String mobileNumber) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).getProfilePic(mobileNumber).enqueue(new Callback<ResponseBody>() { // from class: com.psa.mym.mycitroenconnect.services.ProfileService$getProfilePic$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                ProfileService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                ResponseBody body;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    ProfileService profileService = ProfileService.this;
                    int code = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    profileService.showResponseUnsuccessful(code, errorBody.string(), ProfileService.getProfilePic, (Activity) context);
                    return;
                }
                int code2 = response.code();
                if (code2 == 200) {
                    body = response.body();
                    if (body == null) {
                        return;
                    }
                } else if (code2 == 400) {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody2 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody2);
                        FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody2.string(), (Class<Object>) FailResponse.class);
                        errorResponse.setApiName(ProfileService.getProfilePic);
                        ProfileService profileService2 = ProfileService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(profileService2, errorResponse, false, 2, null);
                        return;
                    } catch (Exception unused) {
                        ProfileService profileService3 = ProfileService.this;
                        ResponseBody errorBody3 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody3);
                        profileService3.postErrorResponse(errorBody3.string(), response.code(), ProfileService.getProfilePic);
                        return;
                    }
                } else {
                    String string = context.getString(R.string.profile_pic_not_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…rofile_pic_not_available)");
                    body = new ErrorResponse(string, response.code(), ProfileService.getProfilePic);
                }
                BaseService.postResponse$default(ProfileService.this, body, false, 2, null);
            }
        });
    }

    public final void uploadProfilePic(@NotNull final Context context, @NotNull String mobileNumber, @NotNull String profilePicPath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
        Intrinsics.checkNotNullParameter(profilePicPath, "profilePicPath");
        Logger logger = Logger.INSTANCE;
        logger.e("Upload Profile Picture");
        logger.e("Mobile Number: " + mobileNumber + " Profile Picture: " + profilePicPath);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType.Companion companion2 = MediaType.Companion;
        RequestBody create = companion.create(mobileNumber, companion2.parse("multipart/form-data"));
        File file = new File(profilePicPath);
        ((ApiInterface) ApiClient.getClient().create(ApiInterface.class)).uploadProfilePic(create, MultipartBody.Part.Companion.createFormData("image", file.getName(), companion.create(file, companion2.parse("multipart/form-data")))).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.ProfileService$uploadProfilePic$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                ProfileService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    ProfileService profileService = ProfileService.this;
                    int code = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    profileService.showResponseUnsuccessful(code, errorBody.string(), ProfileService.uploadProfilePic, (Activity) context);
                    return;
                }
                int code2 = response.code();
                if (code2 == 200) {
                    PostCommonResponse body = response.body();
                    if (body != null) {
                        ProfileService profileService2 = ProfileService.this;
                        body.setApiName(ProfileService.uploadProfilePic);
                        profileService2.postResponse(body, false);
                    }
                } else if (code2 != 400) {
                    String string = context.getString(R.string.profile_pic_not_uploaded);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…profile_pic_not_uploaded)");
                    BaseService.postResponse$default(ProfileService.this, new ErrorResponse(string, response.code(), ProfileService.uploadProfilePic), false, 2, null);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseBody errorBody2 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody2);
                        FailResponse errorResponse = (FailResponse) gson.fromJson(errorBody2.string(), (Class<Object>) FailResponse.class);
                        errorResponse.setApiName(ProfileService.uploadProfilePic);
                        ProfileService profileService3 = ProfileService.this;
                        Intrinsics.checkNotNullExpressionValue(errorResponse, "errorResponse");
                        BaseService.postResponse$default(profileService3, errorResponse, false, 2, null);
                    } catch (Exception unused) {
                        ProfileService profileService4 = ProfileService.this;
                        ResponseBody errorBody3 = response.errorBody();
                        Intrinsics.checkNotNull(errorBody3);
                        profileService4.postErrorResponse(errorBody3.string(), response.code(), ProfileService.uploadProfilePic);
                    }
                }
            }
        });
    }
}
