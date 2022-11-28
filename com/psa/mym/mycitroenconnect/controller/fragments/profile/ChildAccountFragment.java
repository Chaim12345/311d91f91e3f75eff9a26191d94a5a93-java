package com.psa.mym.mycitroenconnect.controller.fragments.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddContactActivity;
import com.psa.mym.mycitroenconnect.controller.activities.EditContactActivity;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUser;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SharedVehicle;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ChildAccountFragment extends BusBaseFragment implements View.OnClickListener, ContactListInterface, OnDeleteDialogDismiss {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private ContactListAdapter childContactAdapter;
    private long lastClickTime;
    @Nullable
    private MainActivity parentActivity;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<EmergencyDetailsItem> contactList = new ArrayList<>();
    @NotNull
    private ArrayList<SecondaryUser> statusMapList = new ArrayList<>();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChildAccountFragment newInstance() {
            ChildAccountFragment childAccountFragment = new ChildAccountFragment();
            childAccountFragment.setArguments(new Bundle());
            return childAccountFragment;
        }
    }

    private final void deleteChildAccount(EmergencyDetailsItem emergencyDetailsItem) {
        showDeleteConfirmDialog(emergencyDetailsItem.getName(), emergencyDetailsItem.getContactNum(), this.contactList);
    }

    private final void displayChildAccountList(int i2) {
        AppCompatTextView tvChildAccountCount;
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList == null || arrayList.isEmpty()) {
            AppCompatImageView ivChildAcct = (AppCompatImageView) _$_findCachedViewById(R.id.ivChildAcct);
            Intrinsics.checkNotNullExpressionValue(ivChildAcct, "ivChildAcct");
            ExtensionsKt.show(ivChildAcct);
            AppCompatTextView tvChildAcctDesc = (AppCompatTextView) _$_findCachedViewById(R.id.tvChildAcctDesc);
            Intrinsics.checkNotNullExpressionValue(tvChildAcctDesc, "tvChildAcctDesc");
            ExtensionsKt.show(tvChildAcctDesc);
            RecyclerView rvChildAccount = (RecyclerView) _$_findCachedViewById(R.id.rvChildAccount);
            Intrinsics.checkNotNullExpressionValue(rvChildAccount, "rvChildAccount");
            ExtensionsKt.hide(rvChildAccount);
            tvChildAccountCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvChildAccountCount);
            Intrinsics.checkNotNullExpressionValue(tvChildAccountCount, "tvChildAccountCount");
        } else {
            updateCount();
            ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
            ArrayList<EmergencyDetailsItem> arrayList3 = new ArrayList();
            for (Object obj : arrayList2) {
                if (((EmergencyDetailsItem) obj).getViewType() != 6) {
                    arrayList3.add(obj);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList3) {
                emergencyDetailsItem.setViewType(6);
            }
            ArrayList<EmergencyDetailsItem> arrayList4 = this.contactList;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ContactListAdapter contactListAdapter = new ContactListAdapter(requireContext, arrayList4, this, 0, 8, null);
            this.childContactAdapter = contactListAdapter;
            contactListAdapter.setTotalCarCount(i2);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvChildAccount);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "");
            ExtensionsKt.show(recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(this.childContactAdapter);
            AppCompatImageView ivChildAcct2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivChildAcct);
            Intrinsics.checkNotNullExpressionValue(ivChildAcct2, "ivChildAcct");
            ExtensionsKt.hide(ivChildAcct2);
            tvChildAccountCount = (AppCompatTextView) _$_findCachedViewById(R.id.tvChildAcctDesc);
            Intrinsics.checkNotNullExpressionValue(tvChildAccountCount, "tvChildAcctDesc");
        }
        ExtensionsKt.hide(tvChildAccountCount);
    }

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayLoading() {
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getChildAccountList() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        displayLoading();
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        secondaryUserService.getChildAccountList(requireContext2, substring);
    }

    private final void initViews() {
        MainActivity mainActivity;
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddChildAcct)).setOnClickListener(this);
    }

    private final int isContactAdded(String str) {
        int i2 = 0;
        for (Object obj : this.contactList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getContactNum(), str)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    @JvmStatic
    @NotNull
    public static final ChildAccountFragment newInstance() {
        return Companion.newInstance();
    }

    private final void showDeleteConfirmDialog(String str, String str2, ArrayList<EmergencyDetailsItem> arrayList) {
        DeleteConfirmationFragment newInstance;
        newInstance = DeleteConfirmationFragment.Companion.newInstance(str, str2, 2, (r16 & 8) != 0 ? null : arrayList, (r16 & 16) != 0 ? null : null, (r16 & 32) != 0 ? -1 : 0);
        newInstance.setOnDeleteDialogDismiss(this);
        newInstance.show(getChildFragmentManager(), DeleteConfirmationFragment.TAG);
        newInstance.setCancelable(false);
    }

    @SuppressLint({"SetTextI18n"})
    private final void updateCount() {
        int size = this.contactList.size();
        int i2 = R.id.tvChildAccountCount;
        AppCompatTextView tvChildAccountCount = (AppCompatTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(tvChildAccountCount, "tvChildAccountCount");
        ExtensionsKt.show(tvChildAccountCount);
        ((AppCompatTextView) _$_findCachedViewById(i2)).setText('(' + size + "/2)");
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAddChildAcct)).setEnabled(size < 2);
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
    public final void getError(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        displayData();
        displayChildAccountList(0);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull SecondaryUserResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        displayData();
        this.contactList.clear();
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ArrayList<RegisteredVehicleItem> registeredVehicleList = companion.getRegisteredVehicleList(requireContext);
        for (SecondaryUser secondaryUser : response) {
            EmergencyDetailsItem emergencyDetailsItem = new EmergencyDetailsItem(null, null, null, false, false, 0, null, null, null, null, null, 2047, null);
            emergencyDetailsItem.setName(secondaryUser.getSecondaryUserFullName());
            String substring = secondaryUser.getSecondaryUsername().substring(2, secondaryUser.getSecondaryUsername().length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            emergencyDetailsItem.setContactNum(substring);
            emergencyDetailsItem.setStatus(secondaryUser.getInviteStatus());
            emergencyDetailsItem.setCountryCode("+91");
            emergencyDetailsItem.setChildContact(true);
            emergencyDetailsItem.setViewType(6);
            this.statusMapList.add(secondaryUser);
            for (RegisteredVehicleItem registeredVehicleItem : registeredVehicleList) {
                for (SharedVehicle sharedVehicle : secondaryUser.getSharedVehicle()) {
                    if (Intrinsics.areEqual(registeredVehicleItem.getVinNum(), sharedVehicle.getVinNum())) {
                        registeredVehicleItem.setAccessible(Intrinsics.areEqual(registeredVehicleItem.getVinNum(), sharedVehicle.getVinNum()));
                        registeredVehicleItem.setVehicleImage(Integer.valueOf((int) uat.psa.mym.mycitroenconnect.R.drawable.citroen_nav_bar_car));
                        registeredVehicleItem.setInviteStatus(emergencyDetailsItem.getStatus());
                        emergencyDetailsItem.getCarAccess().add(registeredVehicleItem);
                    }
                }
            }
            int isContactAdded = isContactAdded(emergencyDetailsItem.getContactNum());
            if (isContactAdded > -1) {
                for (RegisteredVehicleItem registeredVehicleItem2 : this.contactList.get(isContactAdded).getCarAccess()) {
                    for (RegisteredVehicleItem registeredVehicleItem3 : emergencyDetailsItem.getCarAccess()) {
                        if (!Intrinsics.areEqual(registeredVehicleItem2.getVinNum(), registeredVehicleItem3.getVinNum())) {
                            this.contactList.get(isContactAdded).getCarAccess().add(registeredVehicleItem3);
                        }
                    }
                }
            } else {
                this.contactList.add(emergencyDetailsItem);
            }
        }
        displayChildAccountList(registeredVehicleList.size());
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss
    public void onCancel() {
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onCheckedChange(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
        Intrinsics.checkNotNullParameter(contactList, "contactList");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnAddChildAcct))) {
            Intent intent = new Intent(requireContext(), AddContactActivity.class);
            intent.putExtra(AppConstants.ARG_PAGE_MODE, 2);
            intent.putParcelableArrayListExtra(AppConstants.ARG_CONTACT_LIST, this.contactList);
            startActivity(intent);
        }
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_child_account, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnDeleteDialogDismiss
    public void onDeleteSuccess(int i2) {
        getChildAccountList();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.parentActivity = null;
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onItemClick(@NotNull EmergencyDetailsItem objContact, boolean z) {
        Intrinsics.checkNotNullParameter(objContact, "objContact");
        if (z) {
            deleteChildAccount(objContact);
            return;
        }
        Intent intent = new Intent(requireActivity(), EditContactActivity.class);
        intent.putParcelableArrayListExtra(AppConstants.ARG_CONTACT_LIST, this.contactList);
        intent.putExtra(AppConstants.ARG_CONTACT, objContact);
        intent.putExtra(AppConstants.ARG_PAGE_MODE, 2);
        intent.putParcelableArrayListExtra(AppConstants.ARG_STATUS_LIST, this.statusMapList);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getChildAccountList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initViews();
    }
}
