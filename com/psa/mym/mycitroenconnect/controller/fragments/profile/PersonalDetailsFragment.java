package com.psa.mym.mycitroenconnect.controller.fragments.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddContactActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.ImagePickerActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.ProfileDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ProfilePhotoSelectionDlgFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.PersonalDetailsFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.dashboard.GetEmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.p000interface.OnDialogProfilePhotoClickListener;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.ProfileService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.FileUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.apache.http.cookie.ClientCookie;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PersonalDetailsFragment extends BusBaseFragment implements View.OnClickListener, ContactListInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private ContactListAdapter contactAdapter;
    private Skeleton emergencyContactSkeleton;
    private boolean isImageUpload;
    private long lastClickTime;
    @Nullable
    private ArrayList<EmergencyDetailsItem> mEmergencyContactList;
    private Skeleton profilePicSkeleton;
    @NotNull
    private final ActivityResultLauncher<Intent> selectImageResult;
    private Skeleton skeleton;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final PersonalDetailsFragment newInstance() {
            PersonalDetailsFragment personalDetailsFragment = new PersonalDetailsFragment();
            personalDetailsFragment.setArguments(new Bundle());
            return personalDetailsFragment;
        }
    }

    public PersonalDetailsFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: o.c
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                PersonalDetailsFragment.m159selectImageResult$lambda3(PersonalDetailsFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…}\n            }\n        }");
        this.selectImageResult = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteProfilePic() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String mobileNumber = companion2.getMobileNumber(requireContext2);
        ProfileService profileService = new ProfileService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        profileService.deleteProfilePic(requireContext3, substring);
    }

    private final void displayContactList() {
        showPrimaryUserUI();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), 1, false);
        ArrayList<EmergencyDetailsItem> arrayList = this.mEmergencyContactList;
        if (arrayList != null) {
            ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((EmergencyDetailsItem) obj).getViewType() != 2) {
                    arrayList2.add(obj);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList2) {
                emergencyDetailsItem.setViewType(2);
            }
        }
        ArrayList<EmergencyDetailsItem> arrayList3 = this.mEmergencyContactList;
        if (arrayList3 != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.contactAdapter = new ContactListAdapter(requireContext, arrayList3, this, 0, 8, null);
        }
        int i2 = R.id.rvProContact;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(linearLayoutManager);
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.contactAdapter);
    }

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayEmergencyData() {
        ConstraintLayout clEmergencyContact = (ConstraintLayout) _$_findCachedViewById(R.id.clEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(clEmergencyContact, "clEmergencyContact");
        ExtensionsKt.show(clEmergencyContact);
    }

    private final void displayEmergencyLoading() {
        ConstraintLayout clEmergencyContact = (ConstraintLayout) _$_findCachedViewById(R.id.clEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(clEmergencyContact, "clEmergencyContact");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clEmergencyContact, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.emergencyContactSkeleton = createSkeleton;
    }

    private final void displayLoading() {
        ScrollView pdScrollView = (ScrollView) _$_findCachedViewById(R.id.pdScrollView);
        Intrinsics.checkNotNullExpressionValue(pdScrollView, "pdScrollView");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(pdScrollView, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void displayProfilePicData() {
        Skeleton skeleton = this.profilePicSkeleton;
        if (skeleton != null) {
            if (skeleton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("profilePicSkeleton");
                skeleton = null;
            }
            ExtensionsKt.showData$default(skeleton, 0L, 1, null);
        }
        ConstraintLayout clChildImageView = (ConstraintLayout) _$_findCachedViewById(R.id.clChildImageView);
        Intrinsics.checkNotNullExpressionValue(clChildImageView, "clChildImageView");
        ExtensionsKt.show(clChildImageView);
    }

    private final void displayProfilePicLoading() {
        ConstraintLayout clChildImageView = (ConstraintLayout) _$_findCachedViewById(R.id.clChildImageView);
        Intrinsics.checkNotNullExpressionValue(clChildImageView, "clChildImageView");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clChildImageView, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.profilePicSkeleton = createSkeleton;
    }

    private final void getEmergencyContacts() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        dashboardService.callGetEmergencyContactAPI(requireActivity, substring);
    }

    private final void getProfileDetails() {
        displayLoading();
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(requireActivity, substring);
    }

    private final void getProfilePic() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        ProfileService profileService = new ProfileService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        profileService.getProfilePic(requireContext2, substring);
    }

    private final void hideEmergencyDetails() {
        AppCompatTextView tvPdEmergencyContact = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergencyContact, "tvPdEmergencyContact");
        ExtensionsKt.hide(tvPdEmergencyContact);
        AppCompatTextView tvPdEmergencyContactCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContactCount);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergencyContactCount, "tvPdEmergencyContactCount");
        ExtensionsKt.hide(tvPdEmergencyContactCount);
        AppCompatTextView tvPdEmergeSubTitle = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergeSubTitle);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergeSubTitle, "tvPdEmergeSubTitle");
        ExtensionsKt.hide(tvPdEmergeSubTitle);
        RecyclerView rvProContact = (RecyclerView) _$_findCachedViewById(R.id.rvProContact);
        Intrinsics.checkNotNullExpressionValue(rvProContact, "rvProContact");
        ExtensionsKt.hide(rvProContact);
        AppCompatButton btnAddEmergencyContact = (AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(btnAddEmergencyContact, "btnAddEmergencyContact");
        ExtensionsKt.hide(btnAddEmergencyContact);
    }

    private final void init() {
        showPrimaryUserUI();
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivPdEdit)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage)).setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchCameraIntent() {
        this.isImageUpload = true;
        Intent intent = new Intent(requireActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, 0);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
        this.selectImageResult.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchGalleryIntent() {
        this.isImageUpload = true;
        Intent intent = new Intent(requireActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, 1);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        this.selectImageResult.launch(intent);
    }

    private final void loadProfile(Uri uri) {
        Logger logger = Logger.INSTANCE;
        logger.d("Image cache path: " + uri);
        int i2 = R.id.imgProfile;
        Glide.with(this).load(uri).placeholder((int) uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile).error(uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile).into((CircleImageView) _$_findCachedViewById(i2));
        ((CircleImageView) _$_findCachedViewById(i2)).setColorFilter(ContextCompat.getColor(requireContext(), 17170445));
        updateChangeImageLabel(true);
    }

    @JvmStatic
    @NotNull
    public static final PersonalDetailsFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: selectImageResult$lambda-3  reason: not valid java name */
    public static final void m159selectImageResult$lambda3(PersonalDetailsFragment this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getData() == null || activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null || !data.hasExtra(ClientCookie.PATH_ATTR)) {
            return;
        }
        Parcelable parcelableExtra = data.getParcelableExtra(ClientCookie.PATH_ATTR);
        Intrinsics.checkNotNull(parcelableExtra);
        Uri uri = (Uri) parcelableExtra;
        FileUtil fileUtil = FileUtil.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        File from = fileUtil.from(requireContext, uri);
        Logger logger = Logger.INSTANCE;
        logger.e("File Size: " + fileUtil.getReadableFileSize(from));
        logger.e("File Size: " + fileUtil.getSizeInMb(from));
        logger.e("File Path: " + from.getAbsolutePath());
        this$0.loadProfile(uri);
        this$0.uploadProfilePic(from);
    }

    private final void setNoProfilePicData() {
        displayProfilePicData();
        updateChangeImageLabel(false);
    }

    private final void showEmergencyDetails() {
        AppCompatTextView tvPdEmergencyContact = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergencyContact, "tvPdEmergencyContact");
        ExtensionsKt.show(tvPdEmergencyContact);
        AppCompatTextView tvPdEmergencyContactCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContactCount);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergencyContactCount, "tvPdEmergencyContactCount");
        ExtensionsKt.show(tvPdEmergencyContactCount);
        AppCompatTextView tvPdEmergeSubTitle = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergeSubTitle);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergeSubTitle, "tvPdEmergeSubTitle");
        ExtensionsKt.show(tvPdEmergeSubTitle);
        RecyclerView rvProContact = (RecyclerView) _$_findCachedViewById(R.id.rvProContact);
        Intrinsics.checkNotNullExpressionValue(rvProContact, "rvProContact");
        ExtensionsKt.show(rvProContact);
        AppCompatButton btnAddEmergencyContact = (AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact);
        Intrinsics.checkNotNullExpressionValue(btnAddEmergencyContact, "btnAddEmergencyContact");
        ExtensionsKt.show(btnAddEmergencyContact);
    }

    private final void showPrimaryUserUI() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!companion.isPrimaryUser(requireContext2)) {
                hideEmergencyDetails();
                return;
            }
        }
        showEmergencyDetails();
    }

    private final void updateChangeImageLabel(boolean z) {
        AppCompatTextView appCompatTextView;
        int i2;
        if (z) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_change_image;
        } else {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_upload_image;
        }
        appCompatTextView.setText(getString(i2));
    }

    private final void uploadProfilePic(File file) {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String mobileNumber = companion2.getMobileNumber(requireContext2);
        ProfileService profileService = new ProfileService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "profileFile.absolutePath");
        profileService.uploadProfilePic(requireContext3, substring, absolutePath);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse response) {
        Context requireActivity;
        Logger logger;
        StringBuilder sb;
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        switch (apiName.hashCode()) {
            case -1865078432:
                if (apiName.equals(ProfileService.deleteProfilePic)) {
                    requireActivity = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireContext()");
                    ExtensionsKt.showToast(requireActivity, response.getMsg());
                    return;
                }
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
            case -429203274:
                if (apiName.equals(ProfileService.uploadProfilePic)) {
                    this.isImageUpload = false;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    ExtensionsKt.showToast(requireContext, response.getMsg());
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("API: ");
                    sb.append(response.getApiName());
                    sb.append(" Response Code: ");
                    sb.append(response.getStatusCode());
                    logger.e(sb.toString());
                    return;
                }
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
            case 974301412:
                if (apiName.equals("EmergencyContacts")) {
                    displayEmergencyData();
                    getProfilePic();
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                    ExtensionsKt.showToast(requireActivity2, response.getMsg());
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("API: ");
                    sb.append(response.getApiName());
                    sb.append(" Response Code: ");
                    sb.append(response.getStatusCode());
                    logger.e(sb.toString());
                    return;
                }
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
            case 1018169995:
                if (apiName.equals(ProfileService.getProfilePic)) {
                    setNoProfilePicData();
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("API: ");
                    sb.append(response.getApiName());
                    sb.append(" Response Code: ");
                    sb.append(response.getStatusCode());
                    logger.e(sb.toString());
                    return;
                }
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
            case 2041413192:
                if (apiName.equals("GetUserProfile")) {
                    displayData();
                    getEmergencyContacts();
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                    ExtensionsKt.showToast(requireActivity3, response.getMsg());
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("API: ");
                    sb.append(response.getApiName());
                    sb.append(" Response Code: ");
                    sb.append(response.getStatusCode());
                    logger.e(sb.toString());
                    return;
                }
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
            default:
                requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionsKt.showToast(requireActivity, response.getMsg());
                return;
        }
    }

    @Subscribe
    @SuppressLint({"SetTextI18n"})
    public final void getMessage(@NotNull GetEmergencyContactResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayEmergencyData();
        ArrayList<EmergencyDetailsItem> emergencyDetails = event.getEmergencyDetails();
        this.mEmergencyContactList = emergencyDetails;
        if (emergencyDetails != null) {
            Intrinsics.checkNotNull(emergencyDetails);
            if (emergencyDetails.size() > 0) {
                ArrayList<EmergencyDetailsItem> arrayList = this.mEmergencyContactList;
                Intrinsics.checkNotNull(arrayList);
                int size = arrayList.size();
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContactCount)).setText(" (" + size + "/2)");
                ((AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact)).setEnabled(size != 2);
                displayContactList();
                getProfilePic();
            }
        }
        AppCompatTextView tvPdEmergencyContactCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvPdEmergencyContactCount);
        Intrinsics.checkNotNullExpressionValue(tvPdEmergencyContactCount, "tvPdEmergencyContactCount");
        ExtensionsKt.hide(tvPdEmergencyContactCount);
        RecyclerView rvProContact = (RecyclerView) _$_findCachedViewById(R.id.rvProContact);
        Intrinsics.checkNotNullExpressionValue(rvProContact, "rvProContact");
        ExtensionsKt.hide(rvProContact);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact)).setEnabled(true);
        getProfilePic();
    }

    @Subscribe
    public final void getMessage(@NotNull UserProfileResponse event) {
        TextInputEditText textInputEditText;
        String mobile;
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        String fullName = event.getFullName();
        if (fullName != null) {
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.setUserFirstName(requireContext, fullName);
        }
        ((TextInputEditText) _$_findCachedViewById(R.id.edtPdFullName)).setText(event.getFullName());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtPdDob)).setText(event.getDateOfBirth());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtPdEmailId)).setText(event.getEmail());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtPdBloodGroup)).setText(event.getBloodGroup());
        if (event.getMobile() == null) {
            textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edtPdPhone);
            mobile = event.getUserName();
        } else {
            textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edtPdPhone);
            mobile = event.getMobile();
        }
        textInputEditText.setText(mobile);
        ((TextInputEditText) _$_findCachedViewById(R.id.edtPdGender)).setText(event.getGender());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtStreetName)).setText(event.getAddress());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtCity)).setText(event.getCity());
        getEmergencyContacts();
    }

    @Subscribe
    public final void getResponse(@NotNull FailResponse response) {
        Logger logger;
        StringBuilder sb;
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        int hashCode = apiName.hashCode();
        if (hashCode != -1865078432) {
            if (hashCode != -429203274) {
                if (hashCode == 1018169995 && apiName.equals(ProfileService.getProfilePic)) {
                    setNoProfilePicData();
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                }
            } else if (apiName.equals(ProfileService.uploadProfilePic)) {
                this.isImageUpload = false;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                ExtensionsKt.showToast(requireContext, response.getMessage());
                logger = Logger.INSTANCE;
                sb = new StringBuilder();
            }
            sb.append("API: ");
            sb.append(response.getApiName());
            sb.append(" Response Code: ");
            sb.append(response.getStatusCode());
            logger.e(sb.toString());
        } else if (apiName.equals(ProfileService.deleteProfilePic)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ExtensionsKt.showToast(requireContext2, response.getMessage());
            return;
        }
        logger = Logger.INSTANCE;
        sb = new StringBuilder();
        sb.append("API: ");
        sb.append(response.getApiName());
        sb.append(" Response Code: ");
        sb.append(response.getStatusCode());
        logger.e(sb.toString());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, ProfileService.uploadProfilePic)) {
            this.isImageUpload = false;
            AppUtil.Companion.dismissDialog();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, response.getMessage());
        } else if (Intrinsics.areEqual(apiName, ProfileService.deleteProfilePic)) {
            AppUtil.Companion.dismissDialog();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ExtensionsKt.showToast(requireContext2, response.getMessage());
            ((CircleImageView) _$_findCachedViewById(R.id.imgProfile)).setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_upload_image));
        } else {
            Logger logger = Logger.INSTANCE;
            logger.e("API: " + response.getApiName() + " Response Code: " + response.getStatusCode() + " Message: " + response.getMessage());
        }
    }

    @Subscribe
    public final void getResponse(@NotNull ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        updateChangeImageLabel(true);
        try {
            try {
                byte[] decode = Base64.decode(responseBody.bytes(), 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                Looper.getMainLooper();
                ((CircleImageView) _$_findCachedViewById(R.id.imgProfile)).setImageBitmap(decodeByteArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            displayProfilePicData();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onCheckedChange(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
        Intrinsics.checkNotNullParameter(contactList, "contactList");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intent intent;
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnAddEmergencyContact))) {
            intent = new Intent(requireContext(), AddContactActivity.class);
            intent.putExtra(AppConstants.ARG_PAGE_MODE, 1);
            ArrayList<EmergencyDetailsItem> arrayList = this.mEmergencyContactList;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            intent.putParcelableArrayListExtra(AppConstants.ARG_CONTACT_LIST, arrayList);
        } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivPdEdit))) {
            if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage))) {
                ProfilePhotoSelectionDlgFragment profilePhotoSelectionDlgFragment = new ProfilePhotoSelectionDlgFragment();
                profilePhotoSelectionDlgFragment.show(getChildFragmentManager(), ProfilePhotoSelectionDlgFragment.TAG);
                profilePhotoSelectionDlgFragment.setListener(new OnDialogProfilePhotoClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.profile.PersonalDetailsFragment$onClick$1
                    @Override // com.psa.mym.mycitroenconnect.p000interface.OnDialogProfilePhotoClickListener
                    public void onDialogProfilePhotoClickListener(@NotNull String option) {
                        DexterBuilder.MultiPermissionListener withPermissions;
                        MultiplePermissionsListener multiplePermissionsListener;
                        Intrinsics.checkNotNullParameter(option, "option");
                        int hashCode = option.hashCode();
                        if (hashCode != 521667378) {
                            if (hashCode != 1980544805) {
                                if (hashCode == 2012838315 && option.equals("DELETE")) {
                                    PersonalDetailsFragment.this.deleteProfilePic();
                                    return;
                                }
                                return;
                            } else if (!option.equals(AppConstants.PROFILE_CAMERA)) {
                                return;
                            } else {
                                withPermissions = Dexter.withActivity(PersonalDetailsFragment.this.requireActivity()).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
                                final PersonalDetailsFragment personalDetailsFragment = PersonalDetailsFragment.this;
                                multiplePermissionsListener = new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.profile.PersonalDetailsFragment$onClick$1$onDialogProfilePhotoClickListener$1
                                    @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                                    public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                                        Intrinsics.checkNotNullParameter(token, "token");
                                        token.continuePermissionRequest();
                                    }

                                    @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                                    public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                                        Intrinsics.checkNotNullParameter(report, "report");
                                        if (report.areAllPermissionsGranted()) {
                                            PersonalDetailsFragment.this.launchCameraIntent();
                                            return;
                                        }
                                        FragmentActivity requireActivity = PersonalDetailsFragment.this.requireActivity();
                                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                        String string = PersonalDetailsFragment.this.getString(uat.psa.mym.mycitroenconnect.R.string.permission_required_for_profile_pic);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permi…required_for_profile_pic)");
                                        ExtensionsKt.showToast(requireActivity, string);
                                    }
                                };
                            }
                        } else if (!option.equals(AppConstants.PROFILE_GALLERY)) {
                            return;
                        } else {
                            withPermissions = Dexter.withActivity(PersonalDetailsFragment.this.requireActivity()).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
                            final PersonalDetailsFragment personalDetailsFragment2 = PersonalDetailsFragment.this;
                            multiplePermissionsListener = new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.profile.PersonalDetailsFragment$onClick$1$onDialogProfilePhotoClickListener$2
                                @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                                public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                                    Intrinsics.checkNotNullParameter(token, "token");
                                    token.continuePermissionRequest();
                                }

                                @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                                public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                                    Intrinsics.checkNotNullParameter(report, "report");
                                    if (report.areAllPermissionsGranted()) {
                                        PersonalDetailsFragment.this.launchGalleryIntent();
                                        return;
                                    }
                                    Context requireContext = PersonalDetailsFragment.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    String string = PersonalDetailsFragment.this.getString(uat.psa.mym.mycitroenconnect.R.string.permission_required_for_profile_pic);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permi…required_for_profile_pic)");
                                    ExtensionsKt.showToast(requireContext, string);
                                }
                            };
                        }
                        withPermissions.withListener(multiplePermissionsListener).check();
                    }
                });
                profilePhotoSelectionDlgFragment.setCancelable(true);
                return;
            }
            return;
        } else {
            intent = new Intent(requireActivity(), ProfileDetailsActivity.class);
            intent.putExtra("login", AppConstants.PAGE_MODE_EDIT_PROFILE_DETAILS);
        }
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_personal_details, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onItemClick(@NotNull EmergencyDetailsItem objContact, boolean z) {
        Intrinsics.checkNotNullParameter(objContact, "objContact");
        Intent intent = new Intent(requireActivity(), ProfileDetailsActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.EMERGENCY_CONTACT_LIST, this.mEmergencyContactList);
        intent.putExtra(AppConstants.EMERGENCY_CONTACT_BUNDLE_NAME, objContact);
        intent.putExtra("login", AppConstants.PAGE_MODE_EDIT_EMERGENCY_CONTACT);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ImagePickerActivity.clearCache(requireActivity());
        if (this.isImageUpload) {
            return;
        }
        getProfileDetails();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        init();
    }
}
