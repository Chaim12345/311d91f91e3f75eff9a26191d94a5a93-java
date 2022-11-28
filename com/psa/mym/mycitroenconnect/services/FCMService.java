package com.psa.mym.mycitroenconnect.services;

import android.app.Activity;
import android.content.Context;
import com.psa.mym.mycitroenconnect.api.body.fcm.RegisterFCMBody;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.notification.NotificationResponse;
import com.psa.mym.mycitroenconnect.model.notification.UnreadNotificationCount;
import com.psa.mym.mycitroenconnect.model.notification_settings.NotificationSettings;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FCMService extends BaseService {

    /* loaded from: classes3.dex */
    public static final class NotificationServiceResponse {
        @NotNull
        private String apiName;
        @NotNull
        private String message;
        private int position;

        public NotificationServiceResponse() {
            this(null, null, 0, 7, null);
        }

        public NotificationServiceResponse(@NotNull String apiName, @NotNull String message, int i2) {
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(message, "message");
            this.apiName = apiName;
            this.message = message;
            this.position = i2;
        }

        public /* synthetic */ NotificationServiceResponse(String str, String str2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? -1 : i2);
        }

        public static /* synthetic */ NotificationServiceResponse copy$default(NotificationServiceResponse notificationServiceResponse, String str, String str2, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = notificationServiceResponse.apiName;
            }
            if ((i3 & 2) != 0) {
                str2 = notificationServiceResponse.message;
            }
            if ((i3 & 4) != 0) {
                i2 = notificationServiceResponse.position;
            }
            return notificationServiceResponse.copy(str, str2, i2);
        }

        @NotNull
        public final String component1() {
            return this.apiName;
        }

        @NotNull
        public final String component2() {
            return this.message;
        }

        public final int component3() {
            return this.position;
        }

        @NotNull
        public final NotificationServiceResponse copy(@NotNull String apiName, @NotNull String message, int i2) {
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(message, "message");
            return new NotificationServiceResponse(apiName, message, i2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof NotificationServiceResponse) {
                NotificationServiceResponse notificationServiceResponse = (NotificationServiceResponse) obj;
                return Intrinsics.areEqual(this.apiName, notificationServiceResponse.apiName) && Intrinsics.areEqual(this.message, notificationServiceResponse.message) && this.position == notificationServiceResponse.position;
            }
            return false;
        }

        @NotNull
        public final String getApiName() {
            return this.apiName;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        public final int getPosition() {
            return this.position;
        }

        public int hashCode() {
            return (((this.apiName.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.position);
        }

        public final void setApiName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.apiName = str;
        }

        public final void setMessage(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.message = str;
        }

        public final void setPosition(int i2) {
            this.position = i2;
        }

        @NotNull
        public String toString() {
            return "NotificationServiceResponse(apiName=" + this.apiName + ", message=" + this.message + ", position=" + this.position + ')';
        }
    }

    public static /* synthetic */ void getNotificationList$default(FCMService fCMService, Context context, String str, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i2 = 0;
        }
        int i6 = i2;
        if ((i5 & 8) != 0) {
            i3 = 100;
        }
        int i7 = i3;
        if ((i5 & 16) != 0) {
            i4 = -1;
        }
        fCMService.getNotificationList(context, str, i6, i7, i4);
    }

    public static /* synthetic */ void registerToken$default(FCMService fCMService, Context context, RegisterFCMBody registerFCMBody, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        fCMService.registerToken(context, registerFCMBody, z);
    }

    public final void createNotificationSettings(@NotNull final Context context, @NotNull NotificationSettings settings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Logger.INSTANCE.e("PUT Notification Settings");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).createNotificationSettings(settings).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$createNotificationSettings$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code != 200 && code != 201) {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    BaseService.postResponse$default(FCMService.this, new ErrorResponse(errorBody.string(), response.code(), "CreateNotificationSettings"), false, 2, null);
                    return;
                }
                PostCommonResponse body = response.body();
                Intrinsics.checkNotNull(body);
                PostCommonResponse postCommonResponse = body;
                postCommonResponse.setApiName("CreateNotificationSettings");
                FCMService.this.postResponse(postCommonResponse, false);
            }
        });
    }

    public final void deleteNotification(@NotNull final Context context, @NotNull String notificationId, final int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationId, "notificationId");
        Logger.INSTANCE.e("deleteNotification");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).deleteNotification(notificationId).enqueue(new Callback<ResponseBody>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$deleteNotification$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                Logger.INSTANCE.e("FCM Delete", t2);
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200) {
                    String string = context.getString(R.string.unable_to_delete_notification);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…e_to_delete_notification)");
                    BaseService.postResponse$default(FCMService.this, new ErrorResponse(string, response.code(), "DeleteNotification"), false, 2, null);
                } else {
                    ResponseBody body = response.body();
                    String string2 = body != null ? body.string() : null;
                    Intrinsics.checkNotNull(string2);
                    BaseService.postResponse$default(FCMService.this, new FCMService.NotificationServiceResponse("DeleteNotification", string2, i2), false, 2, null);
                }
            }
        });
    }

    public final void deleteToken(@NotNull final Context context, @NotNull String deviceId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Logger.INSTANCE.e("deleteToken");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).deleteFCMToken(deviceId).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$deleteToken$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200 && response.code() != 201) {
                    FCMService fCMService = FCMService.this;
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    fCMService.postErrorResponse(errorBody.string(), response.code(), "FCMDeleteToken");
                } else {
                    PostCommonResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    PostCommonResponse postCommonResponse = body;
                    postCommonResponse.setApiName("FCMDeleteToken");
                    BaseService.postResponse$default(FCMService.this, postCommonResponse, false, 2, null);
                }
            }
        });
    }

    public final void getNotificationList(@NotNull final Context mActivity, @NotNull String vinNum, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("pageNumber", Integer.valueOf(i2));
        linkedHashMap.put("pageSize", Integer.valueOf(i3));
        if (i4 != -1) {
            linkedHashMap.put(LogFactory.PRIORITY_KEY, Integer.valueOf(i4));
        }
        Logger logger = Logger.INSTANCE;
        logger.e("getNotificationList: pageNumber: " + i2 + ", pageSize: " + i3 + ",  priority: " + i4 + ", params: " + linkedHashMap);
        ApiClient.INSTANCE.getApiInterfaceWithToken(mActivity).getAllNotifications(vinNum, linkedHashMap).enqueue(new Callback<NotificationResponse>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$getNotificationList$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<NotificationResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(mActivity, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<NotificationResponse> call, @NotNull Response<NotificationResponse> response) {
                NotificationResponse notificationResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) mActivity);
                    return;
                }
                if (response.code() == 200 || response.code() == 201) {
                    NotificationResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    notificationResponse = body;
                } else {
                    String string = mActivity.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "mActivity.getString(R.string.no_data_available)");
                    notificationResponse = new ErrorResponse(string, response.code(), AppConstants.API_NAME_NOTIF_LIST);
                }
                BaseService.postResponse$default(FCMService.this, notificationResponse, false, 2, null);
            }
        });
    }

    public final void getNotificationSettings(@NotNull final Context context, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.e("GET Notification Settings");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).getNotificationSettings(vinNum).enqueue(new Callback<NotificationSettings>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$getNotificationSettings$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<NotificationSettings> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<NotificationSettings> call, @NotNull Response<NotificationSettings> response) {
                ErrorResponse errorResponse;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    FCMService fCMService = FCMService.this;
                    NotificationSettings body = response.body();
                    Intrinsics.checkNotNull(body);
                    BaseService.postResponse$default(fCMService, body, false, 2, null);
                    return;
                }
                if (code != 204) {
                    String string = context.getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                    errorResponse = new ErrorResponse(string, response.code(), "GetNotificationSettings");
                } else {
                    errorResponse = new ErrorResponse("No Notification Settings Available.", response.code(), "GetNotificationSettings");
                }
                BaseService.postResponse$default(FCMService.this, errorResponse, false, 2, null);
            }
        });
    }

    public final void getUnreadNotificationCount(@NotNull final Context context, @NotNull String vinNum) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Logger.INSTANCE.e("GetUnreadNotificationCount");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).getUnreadNotificationCount(vinNum).enqueue(new Callback<UnreadNotificationCount>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$getUnreadNotificationCount$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<UnreadNotificationCount> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<UnreadNotificationCount> call, @NotNull Response<UnreadNotificationCount> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code == 200 || code == 201) {
                    FCMService fCMService = FCMService.this;
                    UnreadNotificationCount body = response.body();
                    Intrinsics.checkNotNull(body);
                    BaseService.postResponse$default(fCMService, body, false, 2, null);
                    return;
                }
                String string = context.getString(R.string.no_data_available);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_data_available)");
                BaseService.postResponse$default(FCMService.this, new ErrorResponse(string, response.code(), "UnReadCount"), false, 2, null);
            }
        });
    }

    public final void readNotification(@NotNull final Context context, @NotNull String notificationId, final int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationId, "notificationId");
        Logger.INSTANCE.e("readNotification");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).readNotification(notificationId).enqueue(new Callback<ResponseBody>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$readNotification$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200) {
                    String string = context.getString(R.string.unable_to_read_notification);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ble_to_read_notification)");
                    BaseService.postResponse$default(FCMService.this, new ErrorResponse(string, response.code(), "ReadNotification"), false, 2, null);
                } else {
                    ResponseBody body = response.body();
                    String string2 = body != null ? body.string() : null;
                    Intrinsics.checkNotNull(string2);
                    BaseService.postResponse$default(FCMService.this, new FCMService.NotificationServiceResponse("ReadNotification", string2, i2), false, 2, null);
                }
            }
        });
    }

    public final void registerToken(@NotNull final Context context, @NotNull RegisterFCMBody body, final boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        Logger.INSTANCE.e("registerToken");
        ApiClient.INSTANCE.getProfileApiInterfaceWithToken(context).registerFCMToken(body).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$registerToken$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                } else if (response.code() != 200 && response.code() != 201) {
                    FCMService fCMService = FCMService.this;
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    fCMService.postErrorResponse(errorBody.string(), response.code(), "FCMRegisterToken");
                } else {
                    PostCommonResponse body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    PostCommonResponse postCommonResponse = body2;
                    postCommonResponse.setApiName("FCMRegisterToken");
                    FCMService.this.postResponse(postCommonResponse, z);
                }
            }
        });
    }

    public final void updateNotificationSettings(@NotNull final Context context, @NotNull String vinNum, @NotNull NotificationSettings settings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vinNum, "vinNum");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Logger.INSTANCE.e("PUT Notification Settings");
        ApiClient.INSTANCE.getApiInterfaceWithToken(context).updateNotificationSettings(vinNum, settings).enqueue(new Callback<PostCommonResponse>() { // from class: com.psa.mym.mycitroenconnect.services.FCMService$updateNotificationSettings$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<PostCommonResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                FCMService.this.showFailureToast(context, t2);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<PostCommonResponse> call, @NotNull Response<PostCommonResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    FCMService.this.showHttpErrorToast(response.code(), (Activity) context);
                    return;
                }
                int code = response.code();
                if (code != 200 && code != 201) {
                    ResponseBody errorBody = response.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    BaseService.postResponse$default(FCMService.this, new ErrorResponse(errorBody.string(), response.code(), "UpdateNotificationSettings"), false, 2, null);
                    return;
                }
                PostCommonResponse body = response.body();
                Intrinsics.checkNotNull(body);
                PostCommonResponse postCommonResponse = body;
                postCommonResponse.setApiName("UpdateNotificationSettings");
                FCMService.this.postResponse(postCommonResponse, false);
            }
        });
    }
}
