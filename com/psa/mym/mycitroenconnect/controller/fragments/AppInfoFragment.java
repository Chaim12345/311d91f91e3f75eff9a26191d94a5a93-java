package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AppInfoFragment extends Fragment implements View.OnClickListener, InstallStateUpdatedListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int RC_APP_UPDATE = 11;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private AppUpdateManager appUpdateManager;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AppInfoFragment newInstance() {
            AppInfoFragment appInfoFragment = new AppInfoFragment();
            appInfoFragment.setArguments(new Bundle());
            return appInfoFragment;
        }
    }

    private final void getAppUpdateInfo() {
        Task<AppUpdateInfo> appUpdateInfo;
        AppUpdateManager appUpdateManager = this.appUpdateManager;
        if (appUpdateManager == null || appUpdateManager == null || (appUpdateInfo = appUpdateManager.getAppUpdateInfo()) == null) {
            return;
        }
        appUpdateInfo.addOnCompleteListener(b.f10513a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAppUpdateInfo$lambda-4  reason: not valid java name */
    public static final void m126getAppUpdateInfo$lambda4(Task task) {
        Logger logger;
        StringBuilder sb;
        String str;
        Logger logger2;
        String str2;
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            int updateAvailability = ((AppUpdateInfo) task.getResult()).updateAvailability();
            if (updateAvailability == 0) {
                logger2 = Logger.INSTANCE;
                str2 = "The state of update is unknown!";
            } else if (updateAvailability == 1) {
                logger2 = Logger.INSTANCE;
                str2 = "No update available!";
            } else if (updateAvailability == 2) {
                logger = Logger.INSTANCE;
                logger.e("Update available!");
                str = ((AppUpdateInfo) task.getResult()).isUpdateTypeAllowed(0) ? "Can install flexible update!" : ((AppUpdateInfo) task.getResult()).isUpdateTypeAllowed(1) ? "Can install immediate update!" : "Can not install update!";
                logger.e(str);
            } else if (updateAvailability != 3) {
                return;
            } else {
                int installStatus = ((AppUpdateInfo) task.getResult()).installStatus();
                if (installStatus == 2) {
                    logger2 = Logger.INSTANCE;
                    str2 = "Update in progress!";
                } else if (installStatus != 11) {
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("Install Status: ");
                    sb.append(((AppUpdateInfo) task.getResult()).installStatus());
                } else {
                    logger2 = Logger.INSTANCE;
                    str2 = "Update downloaded!";
                }
            }
            logger2.e(str2);
            return;
        }
        logger = Logger.INSTANCE;
        logger.e("The update info: " + task.getResult());
        sb = new StringBuilder();
        sb.append("Task failed exception: ");
        sb.append(task.getException());
        str = sb.toString();
        logger.e(str);
    }

    private final void initInAppUpdate() {
        final AppUpdateManager create = AppUpdateManagerFactory.create(requireContext());
        create.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.c
            @Override // com.google.android.play.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                AppInfoFragment.m127initInAppUpdate$lambda2$lambda1(AppInfoFragment.this, create, (AppUpdateInfo) obj);
            }
        });
        this.appUpdateManager = create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initInAppUpdate$lambda-2$lambda-1  reason: not valid java name */
    public static final void m127initInAppUpdate$lambda2$lambda1(AppInfoFragment this$0, AppUpdateManager this_apply, AppUpdateInfo appUpdateInfo) {
        Logger logger;
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ((AppCompatTextView) this$0._$_findCachedViewById(R.id.tvAppUpdateDesc)).setText(appUpdateInfo.availableVersionCode() == 0 ? "Application is not published in play store yet!" : this$0.getString(uat.psa.mym.mycitroenconnect.R.string.lbl_app_info_update_app, String.valueOf(appUpdateInfo.availableVersionCode())));
        ((AppCompatButton) this$0._$_findCachedViewById(R.id.btnUpdateApp)).setEnabled(appUpdateInfo.updateAvailability() == 2);
        try {
            if (appUpdateInfo.updateAvailability() == 2 && appUpdateInfo.isUpdateTypeAllowed(0)) {
                this_apply.registerListener(this$0);
                this_apply.startUpdateFlowForResult(appUpdateInfo, 0, this$0.requireActivity(), 11);
            } else if (appUpdateInfo.updateAvailability() != 2 || !appUpdateInfo.isUpdateTypeAllowed(1)) {
                if (appUpdateInfo.installStatus() == 11) {
                    if (appUpdateInfo.isUpdateTypeAllowed(0)) {
                        this$0.popUpSnackBarForCompleteUpdate();
                        return;
                    }
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    str = "Install Downloaded: ";
                } else if (appUpdateInfo.updateAvailability() == 3) {
                    this_apply.startUpdateFlowForResult(appUpdateInfo, 1, this$0.requireActivity(), 11);
                } else {
                    logger = Logger.INSTANCE;
                    sb = new StringBuilder();
                    str = "Else : ";
                }
                sb.append(str);
                sb.append(appUpdateInfo);
                logger.e(sb.toString());
            } else {
                this_apply.startUpdateFlowForResult(appUpdateInfo, 1, this$0.requireActivity(), 11);
            }
        } catch (IntentSender.SendIntentException e2) {
            e2.printStackTrace();
        }
    }

    private final void initView() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String versionName = companion.getVersionName(requireContext);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_app_info_version);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_app_info_version)");
        String format = String.format(string, Arrays.copyOf(new Object[]{versionName}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvVersion)).setText(format);
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_app_info_update_app);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.lbl_app_info_update_app)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{versionName}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvAppUpdateDesc)).setText(format2);
    }

    @JvmStatic
    @NotNull
    public static final AppInfoFragment newInstance() {
        return Companion.newInstance();
    }

    private final void popUpSnackBarForCompleteUpdate() {
        Snackbar make = Snackbar.make(requireActivity().findViewById(16908290), "Application download completed. \nRestart to update.", -2);
        Intrinsics.checkNotNullExpressionValue(make, "make(\n            requir…NGTH_INDEFINITE\n        )");
        make.setAction("RESTART", new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppInfoFragment.m128popUpSnackBarForCompleteUpdate$lambda3(AppInfoFragment.this, view);
            }
        });
        make.setActionTextColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.primary_color_1));
        make.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: popUpSnackBarForCompleteUpdate$lambda-3  reason: not valid java name */
    public static final void m128popUpSnackBarForCompleteUpdate$lambda3(AppInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUpdateManager appUpdateManager = this$0.appUpdateManager;
        if (appUpdateManager == null || appUpdateManager == null) {
            return;
        }
        appUpdateManager.completeUpdate();
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnUpdateApp)).setOnClickListener(this);
    }

    private final void unregisterAppUpdateListener() {
        AppUpdateManager appUpdateManager = this.appUpdateManager;
        if (appUpdateManager == null || appUpdateManager == null) {
            return;
        }
        appUpdateManager.unregisterListener(this);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        Logger logger;
        String str;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 11) {
            Logger logger2 = Logger.INSTANCE;
            logger2.e("Request Code: " + i2);
            return;
        }
        if (i3 == -1) {
            logger = Logger.INSTANCE;
            str = "Application download starts!";
        } else if (i3 == 0) {
            Logger.INSTANCE.e("Application Download Failed");
            return;
        } else {
            logger = Logger.INSTANCE;
            str = "Application download cancelled!";
        }
        logger.e(str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnUpdateApp));
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_app_info, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        unregisterAppUpdateListener();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initInAppUpdate();
    }

    @Override // com.google.android.play.core.listener.StateUpdatedListener
    public void onStateUpdate(@NotNull InstallState state) {
        Logger logger;
        String str;
        Logger logger2;
        StringBuilder sb;
        Intrinsics.checkNotNullParameter(state, "state");
        int installStatus = state.installStatus();
        if (installStatus == 10) {
            logger = Logger.INSTANCE;
            str = "Update needs an UI Intent!";
        } else if (installStatus == 11) {
            Logger.INSTANCE.e("The update has been downloaded!");
            popUpSnackBarForCompleteUpdate();
            return;
        } else {
            switch (installStatus) {
                case 0:
                    logger = Logger.INSTANCE;
                    str = "Unknown install state";
                    break;
                case 1:
                    logger = Logger.INSTANCE;
                    str = "Waiting for update to start!";
                    break;
                case 2:
                    float bytesDownloaded = (((float) state.bytesDownloaded()) * 100.0f) / ((float) state.totalBytesToDownload());
                    logger2 = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("The update is downloading! Progress: ");
                    sb.append(bytesDownloaded);
                    logger2.e(sb.toString());
                    return;
                case 3:
                    logger = Logger.INSTANCE;
                    str = "The update is being installed!";
                    break;
                case 4:
                    Logger.INSTANCE.e("The update has been installed!");
                    unregisterAppUpdateListener();
                    return;
                case 5:
                    logger2 = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("The update failed! Reason: ");
                    sb.append(state.installErrorCode());
                    logger2.e(sb.toString());
                    return;
                case 6:
                    logger = Logger.INSTANCE;
                    str = "The user canceled the flexible update!";
                    break;
                default:
                    logger2 = Logger.INSTANCE;
                    sb = new StringBuilder();
                    sb.append("Else: ");
                    sb.append(state);
                    logger2.e(sb.toString());
                    return;
            }
        }
        logger.e(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        setListener();
    }
}
