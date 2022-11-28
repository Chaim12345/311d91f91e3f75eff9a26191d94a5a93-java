package com.psa.mym.mycitroenconnect.api.retrofit;

import com.psa.mym.mycitroenconnect.api.body.UpdateTripNameBody;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyAlertBody;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.api.body.dashboard.RefreshCommandBody;
import com.psa.mym.mycitroenconnect.api.body.fcm.RegisterFCMBody;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.AddVehicleBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.CreateUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.LoginBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RefreshTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserPassword;
import com.psa.mym.mycitroenconnect.api.body.onboarding.SetPinBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.UpdateProfileBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.VerifyOTPBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CancelSecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CarAccessBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.NewVerifySecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.UpdateSecondaryUserNameBody;
import com.psa.mym.mycitroenconnect.model.DeleteCarResponse;
import com.psa.mym.mycitroenconnect.model.JWSRequest;
import com.psa.mym.mycitroenconnect.model.JwsResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardDetailsResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyAlertResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.GetEmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.PostGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.notification.NotificationResponse;
import com.psa.mym.mycitroenconnect.model.notification.UnreadNotificationCount;
import com.psa.mym.mycitroenconnect.model.notification_settings.NotificationSettings;
import com.psa.mym.mycitroenconnect.model.onboarding.AddVehicleResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePassRequest;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.CreateUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.LoginResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPinResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.VerifyOTPResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.AddSecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserCommonResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserVerify;
import com.psa.mym.mycitroenconnect.model.trip.DrivingBehaviourResponse;
import com.psa.mym.mycitroenconnect.model.trip.EnergyUsageResponseItem;
import com.psa.mym.mycitroenconnect.model.trip.FuelReportResponse;
import com.psa.mym.mycitroenconnect.model.trip.IdleDetailsResponse;
import com.psa.mym.mycitroenconnect.model.trip.OnGoingResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripDetailsResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripListResponse;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/* loaded from: classes.dex */
public interface ApiInterface {
    @PUT("fence/{vinNum}/{fenceId}/{fenceStatus}")
    @NotNull
    Call<PostCommonResponse> activeDeactiveFence(@Path(encoded = true, value = "vinNum") @NotNull String str, @Path(encoded = true, value = "fenceId") @NotNull String str2, @Path(encoded = true, value = "fenceStatus") @NotNull String str3);

    @POST("app/secondaryuser")
    @NotNull
    Call<AddSecondaryUserResponse> addChildAccount(@Body @NotNull SecondaryUserBody secondaryUserBody);

    @POST("users/emergencycontact")
    @NotNull
    Call<EmergencyContactResponse> addEmergencyContact(@Body @NotNull EmergencyContactBody emergencyContactBody);

    @POST("app/register/vin")
    @NotNull
    Call<AddVehicleResponse> addVehicle(@Body @NotNull AddVehicleBody addVehicleBody);

    @POST("app/invite/cancel")
    @NotNull
    Call<PostCommonResponse> cancelSecondaryUserInvitation(@Body @NotNull CancelSecondaryUserBody cancelSecondaryUserBody);

    @POST("app/users/changePassword")
    @NotNull
    Call<ChangePasswordResponse> changePassword(@Body @NotNull ChangePassRequest changePassRequest);

    @POST("fence/{vinNum}")
    @NotNull
    Call<PostGeoFenceResponse> createGeoFence(@Path(encoded = true, value = "vinNum") @NotNull String str, @Body @NotNull GeoFenceBody geoFenceBody);

    @POST("notification/setting")
    @NotNull
    Call<PostCommonResponse> createNotificationSettings(@Body @NotNull NotificationSettings notificationSettings);

    @POST("app/users")
    @NotNull
    Call<CreateUserResponse> createUser(@HeaderMap @NotNull Map<String, String> map, @Body @NotNull CreateUserBody createUserBody);

    @DELETE("pushnotification/fcm/{device_id}")
    @NotNull
    Call<PostCommonResponse> deleteFCMToken(@Path(encoded = true, value = "device_id") @NotNull String str);

    @DELETE("fence/{vinNum}/{fenceId}")
    @NotNull
    Call<PostGeoFenceResponse> deleteFence(@Path(encoded = true, value = "vinNum") @NotNull String str, @Path(encoded = true, value = "fenceId") @NotNull String str2);

    @DELETE("app/vehicle/{userName}")
    @NotNull
    Call<DeleteCarResponse> deleteMyCar(@Path(encoded = true, value = "userName") @NotNull String str, @NotNull @Query("vinNum") String str2);

    @DELETE("notification/{notificationId}")
    @NotNull
    Call<ResponseBody> deleteNotification(@Path(encoded = true, value = "notificationId") @NotNull String str);

    @DELETE("profile/image/{mobileNumber}")
    @NotNull
    Call<PostCommonResponse> deleteProfilePic(@Path(encoded = true, value = "mobileNumber") @NotNull String str);

    @DELETE("app/secondaryuser/{userName}")
    @NotNull
    Call<SecondaryUserCommonResponse> deleteSecondaryUserName(@Path(encoded = true, value = "userName") @NotNull String str);

    @PUT("fence/deactivate/vin/{vinNum}")
    @NotNull
    Call<PostCommonResponse> disableAllGeoFence(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @POST("app/users/forgotPassword")
    @NotNull
    Call<SendOtpResponse> forgotPassword(@Body @NotNull RegisterUserBody registerUserBody);

    @GET("notification/vin/{vinNum}")
    @NotNull
    Call<NotificationResponse> getAllNotifications(@Path(encoded = true, value = "vinNum") @NotNull String str, @QueryMap @NotNull Map<String, Integer> map);

    @GET("trip/{vinNum}/details")
    @NotNull
    Call<TripListResponse> getAllTripDetails(@Path(encoded = true, value = "vinNum") @NotNull String str, @NotNull @Query(encoded = true, value = "starttime") String str2, @NotNull @Query(encoded = true, value = "endtime") String str3);

    @GET("app/secondaryusers")
    @NotNull
    Call<SecondaryUserResponse> getChildAccountList(@NotNull @Query("userName") String str);

    @GET("fence/currentLocation/vin/{vinNo}")
    @NotNull
    Call<VehicleLocationResponse> getCurrentVehicleLocation(@Path(encoded = true, value = "vinNo") @NotNull String str);

    @GET("app/vin/{vinNum}/details")
    @NotNull
    Call<DashboardDetailsResponse> getDashboardDetails(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @GET("app/vin/{vinNum}/dashboard")
    @NotNull
    Call<DashboardResponse> getDashboardInfo(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @GET
    @NotNull
    Call<ResponseBody> getDistanceValues(@Url @NotNull String str);

    @GET("users/emergencycontact")
    @NotNull
    Call<GetEmergencyContactResponse> getEmergencyContact(@NotNull @Query(encoded = true, value = "userName") String str);

    @GET("trip/{vinNum}/energyUsage")
    @NotNull
    Call<ArrayList<EnergyUsageResponseItem>> getEnergyUsage(@Path(encoded = true, value = "vinNum") @NotNull String str, @NotNull @Query(encoded = true, value = "starttime") String str2, @NotNull @Query(encoded = true, value = "endtime") String str3);

    @GET("trip/{vinNum}/fuelReport")
    @NotNull
    Call<FuelReportResponse> getFuelReport(@Path(encoded = true, value = "vinNum") @NotNull String str, @NotNull @Query(encoded = true, value = "starttime") String str2, @NotNull @Query(encoded = true, value = "endtime") String str3);

    @GET("fence/{vinNum}/fenceType/{type}")
    @NotNull
    Call<GetGeoFenceResponse> getGeofence(@Path(encoded = true, value = "vinNum") @NotNull String str, @Path(encoded = true, value = "type") @NotNull String str2);

    @GET("trip/{vinNum}/idleDetails")
    @NotNull
    Call<IdleDetailsResponse> getIdleDetails(@Path(encoded = true, value = "vinNum") @NotNull String str, @NotNull @Query(encoded = true, value = "starttime") String str2, @NotNull @Query(encoded = true, value = "endtime") String str3);

    @GET("v2/app/users/permission")
    @NotNull
    Call<MyCars> getMyCarList(@NotNull @Query("userName") String str);

    @GET("notification/setting/{vinNum}")
    @NotNull
    Call<NotificationSettings> getNotificationSettings(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @GET("profile/image/{mobileNumber}")
    @NotNull
    Call<ResponseBody> getProfilePic(@Path(encoded = true, value = "mobileNumber") @NotNull String str);

    @POST("authentication/refreshtoken")
    @NotNull
    Call<Token> getRefreshToken(@Body @NotNull RefreshTokenBody refreshTokenBody);

    @POST("verify")
    @NotNull
    Call<JwsResponse> getResult(@Body @Nullable JWSRequest jWSRequest, @Nullable @Query("key") String str);

    @GET("trip/{vinNum}/tripId/{tripId}")
    @NotNull
    Call<TripDetailsResponse> getTripDetailsByID(@Path(encoded = true, value = "vinNum") @NotNull String str, @Path(encoded = true, value = "tripId") @NotNull String str2);

    @GET("trip/{vinNum}/summary")
    @NotNull
    Call<DrivingBehaviourResponse> getTripSummary(@Path(encoded = true, value = "vinNum") @NotNull String str, @NotNull @Query(encoded = true, value = "starttime") String str2, @NotNull @Query(encoded = true, value = "endtime") String str3);

    @GET("notification/vin/{vinNum}/unReadCount")
    @NotNull
    Call<UnreadNotificationCount> getUnreadNotificationCount(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @GET("users/{username}")
    @NotNull
    Call<UserProfileResponse> getUserProfile(@Path(encoded = true, value = "username") @NotNull String str);

    @POST("app/users/login")
    @NotNull
    Call<Token> getUserToken(@Body @NotNull GetTokenBody getTokenBody);

    @GET("vehicleStatus/{vinNum}")
    @NotNull
    Call<VehicleStatus> getVehicleStatus(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @POST("app/secondaryuser/giveaccess")
    @NotNull
    Call<SecondaryUserCommonResponse> giveAccessSecondaryUser(@Body @NotNull CarAccessBody carAccessBody);

    @POST("v2/app/login")
    @NotNull
    Call<LoginResponse> loginUser(@Body @NotNull LoginBody loginBody);

    @POST("concurrentcommands/vinno")
    @NotNull
    Call<PostCommonResponse> newRefreshCommand(@Body @NotNull RefreshCommandBody refreshCommandBody);

    @POST("app/emergency/alerts")
    @NotNull
    Call<EmergencyAlertResponse> onEmergencyAlert(@Body @NotNull EmergencyAlertBody emergencyAlertBody);

    @GET("trip/{vinNum}/ongoing")
    @NotNull
    Call<OnGoingResponse> onGoing(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @PUT("notification/{notificationId}")
    @NotNull
    Call<ResponseBody> readNotification(@Path(encoded = true, value = "notificationId") @NotNull String str);

    @POST("ota/{vinNum}/refreshcommand")
    @NotNull
    Call<PostCommonResponse> refreshCommand(@Path(encoded = true, value = "vinNum") @NotNull String str);

    @POST("pushnotification/fcm")
    @NotNull
    Call<PostCommonResponse> registerFCMToken(@Body @NotNull RegisterFCMBody registerFCMBody);

    @POST("v2/app/signup")
    @NotNull
    Call<RegisterUserResponse> registerUser(@Body @NotNull RegisterUserBody registerUserBody);

    @POST("app/secondaryuser/revokeaccess")
    @NotNull
    Call<SecondaryUserCommonResponse> revokeAccessSecondaryUser(@Body @NotNull CarAccessBody carAccessBody);

    @POST("app/otp")
    @NotNull
    Call<SendOtpResponse> sendOTP(@Body @NotNull RegisterUserBody registerUserBody);

    @POST("app/users/forgotPassword/verifyOTP")
    @NotNull
    Call<VerifyOTPResponse> setNewForgotPassword(@Body @NotNull VerifyOTPBody verifyOTPBody);

    @POST("app/users/forgotPassword/reset")
    @NotNull
    Call<SetPasswordResponse> setNewPasswordForgotPassword(@Body @NotNull RegisterUserPassword registerUserPassword);

    @POST("v2/app/signup")
    @NotNull
    Call<SetPasswordResponse> setNewPasswordRegister(@Body @NotNull RegisterUserPassword registerUserPassword);

    @POST("app/setpin")
    @NotNull
    Call<SetPinResponse> setPin(@HeaderMap @NotNull Map<String, String> map, @Body @NotNull SetPinBody setPinBody);

    @PUT("users/emergencycontact")
    @NotNull
    Call<EmergencyContactResponse> updateEmergencyContact(@Body @NotNull EmergencyContactBody emergencyContactBody);

    @PUT("fence/{vinNum}/{fenceId}")
    @NotNull
    Call<PostGeoFenceResponse> updateGeoFence(@Path(encoded = true, value = "vinNum") @NotNull String str, @Path(encoded = true, value = "fenceId") @NotNull String str2, @Body @NotNull GeoFenceBody geoFenceBody);

    @PUT("notification/setting/{vinNum}")
    @NotNull
    Call<PostCommonResponse> updateNotificationSettings(@Path(encoded = true, value = "vinNum") @NotNull String str, @Body @NotNull NotificationSettings notificationSettings);

    @PUT("app/secondaryuser")
    @NotNull
    Call<SecondaryUserCommonResponse> updateSecondaryUserName(@Body @NotNull UpdateSecondaryUserNameBody updateSecondaryUserNameBody);

    @PUT("trip/{vinNum}/updateTripName")
    @NotNull
    Call<PostCommonResponse> updateTripName(@Path(encoded = true, value = "vinNum") @NotNull String str, @Body @NotNull UpdateTripNameBody updateTripNameBody);

    @PUT("users")
    @NotNull
    Call<UpdateUserProfileResponse> updateUser(@Body @NotNull UpdateProfileBody updateProfileBody);

    @POST("profile/image")
    @NotNull
    @Multipart
    Call<PostCommonResponse> uploadProfilePic(@Nullable @Part("userName") RequestBody requestBody, @Nullable @Part MultipartBody.Part part);

    @POST("app/otp/verify")
    @NotNull
    Call<VerifyOTPResponse> verifyOTP(@Body @NotNull VerifyOTPBody verifyOTPBody);

    @POST("v2/app/signup/otp/verify")
    @NotNull
    Call<VerifyOTPResponse> verifyOTPRegistration(@Body @NotNull VerifyOTPBody verifyOTPBody);

    @POST("app/users/changePassword/verifyOTP")
    @NotNull
    Call<VerifyOTPResponse> verifyOtpWithChangePassword(@Body @NotNull VerifyOTPBody verifyOTPBody);

    @POST("v2/app/signin/otp/verify")
    @NotNull
    Call<VerifyOTPResponse> verifyOtpWithPassword(@Body @NotNull VerifyOTPBody verifyOTPBody);

    @POST("app/invite/verify")
    @NotNull
    Call<SecondaryUserVerify> verifySecondaryUserInvitation(@Body @NotNull NewVerifySecondaryUserBody newVerifySecondaryUserBody);
}
