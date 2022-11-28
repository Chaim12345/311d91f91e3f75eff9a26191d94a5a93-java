package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt__CharJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int VIEW_TYPE_ADD_CHILD_CONTACT = 5;
    public static final int VIEW_TYPE_ADD_CONTACT_CONFIRMATION = 4;
    public static final int VIEW_TYPE_ADD_EMERGENCY_CONTACT = 3;
    public static final int VIEW_TYPE_CHILD_CONTACT = 6;
    public static final int VIEW_TYPE_PROFILE_EMERGENCY_CONTACT = 2;
    public static final int VIEW_TYPE_SOS_EMERGENCY = 1;
    @NotNull
    private ArrayList<EmergencyDetailsItem> contactList;
    @NotNull
    private Context context;
    private int lastCheckedPos;
    private int maxSelectionCount;
    @Nullable
    private ContactListInterface onContactListener;
    private int totalCar;

    /* loaded from: classes3.dex */
    public final class AddChildContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ContactListAdapter f10454a;
        @Nullable
        private ConstraintLayout llSelectAccount;
        @Nullable
        private MaterialRadioButton rbSelect;
        @Nullable
        private AppCompatTextView tvName;
        @Nullable
        private AppCompatTextView tvNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AddChildContactViewHolder(@NotNull ContactListAdapter contactListAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10454a = contactListAdapter;
            this.rbSelect = (MaterialRadioButton) view.findViewById(R.id.rbSelect);
            this.tvName = (AppCompatTextView) view.findViewById(R.id.tvName);
            this.tvNumber = (AppCompatTextView) view.findViewById(R.id.tvNumber);
            this.llSelectAccount = (ConstraintLayout) view.findViewById(R.id.llSelectAccount);
            MaterialRadioButton materialRadioButton = this.rbSelect;
            if (materialRadioButton != null) {
                materialRadioButton.setOnClickListener(this);
            }
            ConstraintLayout constraintLayout = this.llSelectAccount;
            if (constraintLayout != null) {
                constraintLayout.setOnClickListener(this);
            }
        }

        @Nullable
        public final MaterialRadioButton getRbSelect() {
            return this.rbSelect;
        }

        @Nullable
        public final AppCompatTextView getTvName() {
            return this.tvName;
        }

        @Nullable
        public final AppCompatTextView getTvNumber() {
            return this.tvNumber;
        }

        @Override // android.view.View.OnClickListener
        @SuppressLint({"NotifyDataSetChanged"})
        public void onClick(@Nullable View view) {
            if (Intrinsics.areEqual(view, this.rbSelect) ? true : Intrinsics.areEqual(view, this.llSelectAccount)) {
                this.f10454a.lastCheckedPos = getAbsoluteAdapterPosition();
                this.f10454a.notifyDataSetChanged();
            }
        }

        public final void setRbSelect(@Nullable MaterialRadioButton materialRadioButton) {
            this.rbSelect = materialRadioButton;
        }

        public final void setTvName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvName = appCompatTextView;
        }

        public final void setTvNumber(@Nullable AppCompatTextView appCompatTextView) {
            this.tvNumber = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public final class AddEmergencyContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ContactListAdapter f10455a;
        @Nullable
        private AppCompatCheckBox chkContact;
        @Nullable
        private ConstraintLayout layoutRootView;
        @Nullable
        private AppCompatTextView tvAddContactName;
        @Nullable
        private AppCompatTextView tvAddContactNo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AddEmergencyContactViewHolder(@NotNull ContactListAdapter contactListAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10455a = contactListAdapter;
            this.tvAddContactName = (AppCompatTextView) view.findViewById(R.id.tvAddContactName);
            this.tvAddContactNo = (AppCompatTextView) view.findViewById(R.id.tvAddContactNo);
            this.chkContact = (AppCompatCheckBox) view.findViewById(R.id.chkAddEmergencyContact);
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.layoutAddEmergContact);
            this.layoutRootView = constraintLayout;
            if (constraintLayout != null) {
                constraintLayout.setOnClickListener(this);
            }
            AppCompatCheckBox appCompatCheckBox = this.chkContact;
            if (appCompatCheckBox != null) {
                appCompatCheckBox.setOnCheckedChangeListener(this);
            }
        }

        @Nullable
        public final AppCompatCheckBox getChkContact() {
            return this.chkContact;
        }

        @Nullable
        public final AppCompatTextView getTvAddContactName() {
            return this.tvAddContactName;
        }

        @Nullable
        public final AppCompatTextView getTvAddContactNo() {
            return this.tvAddContactNo;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
            AppCompatTextView appCompatTextView;
            int color;
            if (this.f10455a.isMaximumCountItemSelected()) {
                AppCompatCheckBox appCompatCheckBox = this.chkContact;
                if (appCompatCheckBox != null) {
                    appCompatCheckBox.setChecked(false);
                }
                ((EmergencyDetailsItem) this.f10455a.contactList.get(getAbsoluteAdapterPosition())).setEmergencyContact(false);
                AppCompatTextView appCompatTextView2 = this.tvAddContactName;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTextColor(this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                }
                AppCompatTextView appCompatTextView3 = this.tvAddContactNo;
                if (appCompatTextView3 != null) {
                    appCompatTextView3.setTextColor(this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                }
            } else {
                AppCompatTextView appCompatTextView4 = this.tvAddContactName;
                if (z) {
                    if (appCompatTextView4 != null) {
                        appCompatTextView4.setTextColor(this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                    }
                    appCompatTextView = this.tvAddContactNo;
                    if (appCompatTextView != null) {
                        color = this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.black_600);
                        appCompatTextView.setTextColor(color);
                    }
                    ((EmergencyDetailsItem) this.f10455a.contactList.get(getAbsoluteAdapterPosition())).setEmergencyContact(z);
                } else {
                    if (appCompatTextView4 != null) {
                        appCompatTextView4.setTextColor(this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                    }
                    appCompatTextView = this.tvAddContactNo;
                    if (appCompatTextView != null) {
                        color = this.f10455a.context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70);
                        appCompatTextView.setTextColor(color);
                    }
                    ((EmergencyDetailsItem) this.f10455a.contactList.get(getAbsoluteAdapterPosition())).setEmergencyContact(z);
                }
            }
            ContactListInterface onContactListener = this.f10455a.getOnContactListener();
            if (onContactListener != null) {
                onContactListener.onCheckedChange(this.f10455a.contactList);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            ContactListInterface onContactListener;
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf == null || valueOf.intValue() != uat.psa.mym.mycitroenconnect.R.id.layoutRootView || (onContactListener = this.f10455a.getOnContactListener()) == null) {
                return;
            }
            Object obj = this.f10455a.contactList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "contactList[absoluteAdapterPosition]");
            ContactListInterface.DefaultImpls.onItemClick$default(onContactListener, (EmergencyDetailsItem) obj, false, 2, null);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(@Nullable View view) {
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf == null) {
                return true;
            }
            valueOf.intValue();
            return true;
        }

        public final void setChkContact(@Nullable AppCompatCheckBox appCompatCheckBox) {
            this.chkContact = appCompatCheckBox;
        }

        public final void setTvAddContactName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvAddContactName = appCompatTextView;
        }

        public final void setTvAddContactNo(@Nullable AppCompatTextView appCompatTextView) {
            this.tvAddContactNo = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public final class ChildContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ContactListAdapter f10456a;
        @Nullable
        private View divider;
        @Nullable
        private AppCompatImageView ivDelete;
        @Nullable
        private AppCompatImageView ivEdit;
        @Nullable
        private LinearLayoutCompat llContactDetails;
        @Nullable
        private View status;
        @Nullable
        private AppCompatTextView tvCarAccessCount;
        @Nullable
        private AppCompatTextView tvName;
        @Nullable
        private AppCompatTextView tvNumber;
        @Nullable
        private AppCompatTextView tvStatus;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChildContactViewHolder(@NotNull ContactListAdapter contactListAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10456a = contactListAdapter;
            this.tvName = (AppCompatTextView) view.findViewById(R.id.tvName);
            this.tvNumber = (AppCompatTextView) view.findViewById(R.id.tvNumber);
            this.tvStatus = (AppCompatTextView) view.findViewById(R.id.tvStatus);
            this.tvCarAccessCount = (AppCompatTextView) view.findViewById(R.id.tvCarAccessCount);
            this.divider = view.findViewById(R.id.divider);
            this.status = view.findViewById(R.id.status);
            this.ivEdit = (AppCompatImageView) view.findViewById(R.id.ivEdit);
            this.ivDelete = (AppCompatImageView) view.findViewById(R.id.ivDelete);
            this.llContactDetails = (LinearLayoutCompat) view.findViewById(R.id.llContactDetails);
            AppCompatImageView appCompatImageView = this.ivEdit;
            if (appCompatImageView != null) {
                appCompatImageView.setOnClickListener(this);
            }
            AppCompatImageView appCompatImageView2 = this.ivDelete;
            if (appCompatImageView2 != null) {
                appCompatImageView2.setOnClickListener(this);
            }
            LinearLayoutCompat linearLayoutCompat = this.llContactDetails;
            if (linearLayoutCompat != null) {
                linearLayoutCompat.setOnLongClickListener(this);
            }
        }

        @Nullable
        public final View getDivider() {
            return this.divider;
        }

        @Nullable
        public final AppCompatImageView getIvDelete() {
            return this.ivDelete;
        }

        @Nullable
        public final AppCompatImageView getIvEdit() {
            return this.ivEdit;
        }

        @Nullable
        public final View getStatus() {
            return this.status;
        }

        @Nullable
        public final AppCompatTextView getTvCarAccessCount() {
            return this.tvCarAccessCount;
        }

        @Nullable
        public final AppCompatTextView getTvName() {
            return this.tvName;
        }

        @Nullable
        public final AppCompatTextView getTvNumber() {
            return this.tvNumber;
        }

        @Nullable
        public final AppCompatTextView getTvStatus() {
            return this.tvStatus;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            ContactListInterface onContactListener;
            if (getAbsoluteAdapterPosition() <= -1 || this.f10456a.getOnContactListener() == null) {
                return;
            }
            if (Intrinsics.areEqual(view, this.ivEdit)) {
                ContactListInterface onContactListener2 = this.f10456a.getOnContactListener();
                if (onContactListener2 != null) {
                    Object obj = this.f10456a.contactList.get(getAbsoluteAdapterPosition());
                    Intrinsics.checkNotNullExpressionValue(obj, "contactList[absoluteAdapterPosition]");
                    ContactListInterface.DefaultImpls.onItemClick$default(onContactListener2, (EmergencyDetailsItem) obj, false, 2, null);
                }
            } else if (!Intrinsics.areEqual(view, this.ivDelete) || (onContactListener = this.f10456a.getOnContactListener()) == null) {
            } else {
                Object obj2 = this.f10456a.contactList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj2, "contactList[absoluteAdapterPosition]");
                onContactListener.onItemClick((EmergencyDetailsItem) obj2, true);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(@Nullable View view) {
            ContactListInterface onContactListener;
            if (Intrinsics.areEqual(view, this.llContactDetails)) {
                if (getAbsoluteAdapterPosition() <= -1 || this.f10456a.getOnContactListener() == null || (onContactListener = this.f10456a.getOnContactListener()) == null) {
                    return true;
                }
                Object obj = this.f10456a.contactList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj, "contactList[absoluteAdapterPosition]");
                ContactListInterface.DefaultImpls.onItemClick$default(onContactListener, (EmergencyDetailsItem) obj, false, 2, null);
                return true;
            }
            return false;
        }

        public final void setDivider(@Nullable View view) {
            this.divider = view;
        }

        public final void setIvDelete(@Nullable AppCompatImageView appCompatImageView) {
            this.ivDelete = appCompatImageView;
        }

        public final void setIvEdit(@Nullable AppCompatImageView appCompatImageView) {
            this.ivEdit = appCompatImageView;
        }

        public final void setStatus(@Nullable View view) {
            this.status = view;
        }

        public final void setTvCarAccessCount(@Nullable AppCompatTextView appCompatTextView) {
            this.tvCarAccessCount = appCompatTextView;
        }

        public final void setTvName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvName = appCompatTextView;
        }

        public final void setTvNumber(@Nullable AppCompatTextView appCompatTextView) {
            this.tvNumber = appCompatTextView;
        }

        public final void setTvStatus(@Nullable AppCompatTextView appCompatTextView) {
            this.tvStatus = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final class SOSEmergencyContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ContactListAdapter f10457a;
        @Nullable
        private AppCompatImageView ivEndImage;
        @Nullable
        private ConstraintLayout layoutRootView;
        @Nullable
        private AppCompatTextView tvContactName;
        @Nullable
        private AppCompatTextView tvContactNoImage;
        @Nullable
        private AppCompatTextView tvContactNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SOSEmergencyContactViewHolder(@NotNull ContactListAdapter contactListAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10457a = contactListAdapter;
            this.tvContactName = (AppCompatTextView) view.findViewById(R.id.tvContactName);
            this.tvContactNumber = (AppCompatTextView) view.findViewById(R.id.tvContactNumber);
            this.tvContactNoImage = (AppCompatTextView) view.findViewById(R.id.tvContactNoImage);
            this.ivEndImage = (AppCompatImageView) view.findViewById(R.id.ivCall);
            this.layoutRootView = (ConstraintLayout) view.findViewById(R.id.layoutRootView);
            AppCompatImageView appCompatImageView = this.ivEndImage;
            if (appCompatImageView != null) {
                appCompatImageView.setOnClickListener(this);
            }
            ConstraintLayout constraintLayout = this.layoutRootView;
            if (constraintLayout != null) {
                constraintLayout.setOnLongClickListener(this);
            }
        }

        @Nullable
        public final AppCompatImageView getIvEndImage() {
            return this.ivEndImage;
        }

        @Nullable
        public final ConstraintLayout getLayoutRootView() {
            return this.layoutRootView;
        }

        @Nullable
        public final AppCompatTextView getTvContactName() {
            return this.tvContactName;
        }

        @Nullable
        public final AppCompatTextView getTvContactNoImage() {
            return this.tvContactNoImage;
        }

        @Nullable
        public final AppCompatTextView getTvContactNumber() {
            return this.tvContactNumber;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            ContactListInterface onContactListener;
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if (valueOf == null || valueOf.intValue() != uat.psa.mym.mycitroenconnect.R.id.ivCall || (onContactListener = this.f10457a.getOnContactListener()) == null) {
                return;
            }
            Object obj = this.f10457a.contactList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "contactList[absoluteAdapterPosition]");
            ContactListInterface.DefaultImpls.onItemClick$default(onContactListener, (EmergencyDetailsItem) obj, false, 2, null);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(@Nullable View view) {
            ContactListInterface onContactListener;
            if (!Intrinsics.areEqual(view, this.layoutRootView) || getAbsoluteAdapterPosition() <= -1 || this.f10457a.getOnContactListener() == null || (onContactListener = this.f10457a.getOnContactListener()) == null) {
                return true;
            }
            Object obj = this.f10457a.contactList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "contactList[absoluteAdapterPosition]");
            ContactListInterface.DefaultImpls.onItemClick$default(onContactListener, (EmergencyDetailsItem) obj, false, 2, null);
            return true;
        }

        public final void setIvEndImage(@Nullable AppCompatImageView appCompatImageView) {
            this.ivEndImage = appCompatImageView;
        }

        public final void setLayoutRootView(@Nullable ConstraintLayout constraintLayout) {
            this.layoutRootView = constraintLayout;
        }

        public final void setTvContactName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvContactName = appCompatTextView;
        }

        public final void setTvContactNoImage(@Nullable AppCompatTextView appCompatTextView) {
            this.tvContactNoImage = appCompatTextView;
        }

        public final void setTvContactNumber(@Nullable AppCompatTextView appCompatTextView) {
            this.tvContactNumber = appCompatTextView;
        }
    }

    public ContactListAdapter(@NotNull Context context, @NotNull ArrayList<EmergencyDetailsItem> contactList, @Nullable ContactListInterface contactListInterface, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contactList, "contactList");
        this.context = context;
        this.contactList = contactList;
        this.onContactListener = contactListInterface;
        this.maxSelectionCount = i2;
        this.lastCheckedPos = -1;
    }

    public /* synthetic */ ContactListAdapter(Context context, ArrayList arrayList, ContactListInterface contactListInterface, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, arrayList, (i3 & 4) != 0 ? null : contactListInterface, (i3 & 8) != 0 ? 2 : i2);
    }

    private final String getAccessCarCount(ArrayList<RegisteredVehicleItem> arrayList) {
        int i2 = 0;
        for (RegisteredVehicleItem registeredVehicleItem : arrayList) {
            if (registeredVehicleItem.isAccessible()) {
                i2++;
            }
        }
        return String.valueOf(i2);
    }

    private final boolean isMaximumChildAccountSelected() {
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((EmergencyDetailsItem) obj).isChildContact()) {
                arrayList2.add(obj);
            }
        }
        if (this.maxSelectionCount == 0) {
            this.maxSelectionCount = 2;
        }
        return arrayList2.size() >= this.maxSelectionCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMaximumCountItemSelected() {
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((EmergencyDetailsItem) obj).isEmergencyContact()) {
                arrayList2.add(obj);
            }
        }
        if (this.maxSelectionCount == 0) {
            this.maxSelectionCount = 2;
        }
        return arrayList2.size() >= this.maxSelectionCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.contactList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        switch (this.contactList.get(i2).getViewType()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            default:
                return -1;
        }
    }

    public final int getMaxSelectionCount() {
        return this.maxSelectionCount;
    }

    @Nullable
    public final ContactListInterface getOnContactListener() {
        return this.onContactListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0131  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        int i3;
        AppCompatTextView tvNumber;
        Context context;
        View status;
        int i4;
        AppCompatTextView tvCarAccessCount;
        View divider;
        String valueOf;
        Intrinsics.checkNotNullParameter(holder, "holder");
        switch (this.contactList.get(i2).getViewType()) {
            case 1:
                SOSEmergencyContactViewHolder sOSEmergencyContactViewHolder = (SOSEmergencyContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem = this.contactList.get(sOSEmergencyContactViewHolder.getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem, "contactList[holderSOSEme….absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem2 = emergencyDetailsItem;
                AppCompatTextView tvContactName = sOSEmergencyContactViewHolder.getTvContactName();
                if (tvContactName != null) {
                    tvContactName.setText(emergencyDetailsItem2.getName());
                }
                AppCompatTextView tvContactNumber = sOSEmergencyContactViewHolder.getTvContactNumber();
                if (tvContactNumber != null) {
                    tvContactNumber.setText(emergencyDetailsItem2.getContactNum());
                }
                AppCompatImageView ivEndImage = sOSEmergencyContactViewHolder.getIvEndImage();
                if (ivEndImage != null) {
                    ivEndImage.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_call);
                }
                if (!emergencyDetailsItem2.isEmergencyContact()) {
                    AppCompatTextView tvContactNoImage = sOSEmergencyContactViewHolder.getTvContactNoImage();
                    if (tvContactNoImage == null) {
                        return;
                    }
                    tvContactNoImage.setVisibility(8);
                    return;
                }
                AppCompatTextView tvContactNoImage2 = sOSEmergencyContactViewHolder.getTvContactNoImage();
                if (tvContactNoImage2 != null) {
                    tvContactNoImage2.setVisibility(0);
                }
                AppCompatTextView tvContactNoImage3 = sOSEmergencyContactViewHolder.getTvContactNoImage();
                if (tvContactNoImage3 == null) {
                    return;
                }
                tvContactNoImage3.setText(emergencyDetailsItem2.getContactNum());
                return;
            case 2:
                SOSEmergencyContactViewHolder sOSEmergencyContactViewHolder2 = (SOSEmergencyContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem3 = this.contactList.get(sOSEmergencyContactViewHolder2.getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem3, "contactList[holderProEme….absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem4 = emergencyDetailsItem3;
                AppCompatTextView tvContactName2 = sOSEmergencyContactViewHolder2.getTvContactName();
                if (tvContactName2 != null) {
                    tvContactName2.setText(emergencyDetailsItem4.getName());
                }
                AppCompatTextView tvContactNumber2 = sOSEmergencyContactViewHolder2.getTvContactNumber();
                if (tvContactNumber2 != null) {
                    tvContactNumber2.setText(emergencyDetailsItem4.getContactNum());
                }
                AppCompatTextView tvContactNoImage4 = sOSEmergencyContactViewHolder2.getTvContactNoImage();
                if (tvContactNoImage4 != null) {
                    tvContactNoImage4.setVisibility(8);
                }
                AppCompatImageView ivEndImage2 = sOSEmergencyContactViewHolder2.getIvEndImage();
                if (ivEndImage2 != null) {
                    ivEndImage2.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_edit);
                    return;
                }
                return;
            case 3:
                AddEmergencyContactViewHolder addEmergencyContactViewHolder = (AddEmergencyContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem5 = this.contactList.get(addEmergencyContactViewHolder.getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem5, "contactList[holderAddEme….absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem6 = emergencyDetailsItem5;
                AppCompatTextView tvAddContactName = addEmergencyContactViewHolder.getTvAddContactName();
                if (tvAddContactName != null) {
                    tvAddContactName.setText(emergencyDetailsItem6.getName());
                }
                AppCompatTextView tvAddContactNo = addEmergencyContactViewHolder.getTvAddContactNo();
                if (tvAddContactNo != null) {
                    tvAddContactNo.setText(emergencyDetailsItem6.getContactNum());
                }
                AppCompatCheckBox chkContact = addEmergencyContactViewHolder.getChkContact();
                if (chkContact == null) {
                    return;
                }
                chkContact.setChecked(emergencyDetailsItem6.isEmergencyContact());
                return;
            case 4:
                SOSEmergencyContactViewHolder sOSEmergencyContactViewHolder3 = (SOSEmergencyContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem7 = this.contactList.get(sOSEmergencyContactViewHolder3.getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem7, "contactList[holderConfir….absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem8 = emergencyDetailsItem7;
                AppCompatTextView tvContactName3 = sOSEmergencyContactViewHolder3.getTvContactName();
                if (tvContactName3 != null) {
                    tvContactName3.setText(emergencyDetailsItem8.getName());
                }
                AppCompatTextView tvContactNumber3 = sOSEmergencyContactViewHolder3.getTvContactNumber();
                if (tvContactNumber3 != null) {
                    tvContactNumber3.setText(emergencyDetailsItem8.getContactNum());
                }
                AppCompatTextView tvContactNoImage5 = sOSEmergencyContactViewHolder3.getTvContactNoImage();
                if (tvContactNoImage5 != null) {
                    tvContactNoImage5.setVisibility(8);
                }
                AppCompatImageView ivEndImage3 = sOSEmergencyContactViewHolder3.getIvEndImage();
                if (ivEndImage3 == null) {
                    return;
                }
                ivEndImage3.setVisibility(8);
                return;
            case 5:
                AddChildContactViewHolder addChildContactViewHolder = (AddChildContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem9 = this.contactList.get(addChildContactViewHolder.getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem9, "contactList[childContact….absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem10 = emergencyDetailsItem9;
                AppCompatTextView tvName = addChildContactViewHolder.getTvName();
                if (tvName != null) {
                    tvName.setText(emergencyDetailsItem10.getName());
                }
                AppCompatTextView tvNumber2 = addChildContactViewHolder.getTvNumber();
                if (tvNumber2 != null) {
                    tvNumber2.setText(emergencyDetailsItem10.getContactNum());
                }
                if (this.lastCheckedPos > -1) {
                    MaterialRadioButton rbSelect = addChildContactViewHolder.getRbSelect();
                    if (rbSelect != null) {
                        rbSelect.setChecked(this.lastCheckedPos == addChildContactViewHolder.getAbsoluteAdapterPosition());
                    }
                    emergencyDetailsItem10.setChildContact(this.lastCheckedPos == addChildContactViewHolder.getAbsoluteAdapterPosition());
                } else {
                    MaterialRadioButton rbSelect2 = addChildContactViewHolder.getRbSelect();
                    if (rbSelect2 != null) {
                        rbSelect2.setChecked(false);
                    }
                    emergencyDetailsItem10.setChildContact(false);
                }
                if (emergencyDetailsItem10.isChildContact()) {
                    AppCompatTextView tvName2 = addChildContactViewHolder.getTvName();
                    if (tvName2 != null) {
                        tvName2.setTextColor(this.context.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                    }
                    tvNumber = addChildContactViewHolder.getTvNumber();
                    if (tvNumber == null) {
                        return;
                    }
                    context = this.context;
                    i3 = uat.psa.mym.mycitroenconnect.R.color.black_600;
                } else {
                    AppCompatTextView tvName3 = addChildContactViewHolder.getTvName();
                    i3 = uat.psa.mym.mycitroenconnect.R.color.hot_grey_70;
                    if (tvName3 != null) {
                        tvName3.setTextColor(this.context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                    }
                    tvNumber = addChildContactViewHolder.getTvNumber();
                    if (tvNumber == null) {
                        return;
                    }
                    context = this.context;
                }
                tvNumber.setTextColor(context.getColor(i3));
                return;
            case 6:
                ChildContactViewHolder childContactViewHolder = (ChildContactViewHolder) holder;
                EmergencyDetailsItem emergencyDetailsItem11 = this.contactList.get(i2);
                Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem11, "contactList[position]");
                EmergencyDetailsItem emergencyDetailsItem12 = emergencyDetailsItem11;
                AppCompatTextView tvName4 = childContactViewHolder.getTvName();
                if (tvName4 != null) {
                    tvName4.setText(emergencyDetailsItem12.getName());
                }
                AppCompatTextView tvNumber3 = childContactViewHolder.getTvNumber();
                if (tvNumber3 != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.context.getString(uat.psa.mym.mycitroenconnect.R.string.full_mobile_number);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.full_mobile_number)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{emergencyDetailsItem12.getCountryCode(), emergencyDetailsItem12.getContactNum()}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    tvNumber3.setText(format);
                }
                AppCompatTextView tvStatus = childContactViewHolder.getTvStatus();
                if (tvStatus != null) {
                    String status2 = emergencyDetailsItem12.getStatus();
                    if (status2.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        char charAt = status2.charAt(0);
                        if (Character.isLowerCase(charAt)) {
                            Locale ROOT = Locale.ROOT;
                            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                            valueOf = CharsKt__CharJVMKt.titlecase(charAt, ROOT);
                        } else {
                            valueOf = String.valueOf(charAt);
                        }
                        sb.append((Object) valueOf);
                        String substring = status2.substring(1);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        sb.append(substring);
                        status2 = sb.toString();
                    }
                    tvStatus.setText(status2);
                }
                Context context2 = this.context;
                String status3 = emergencyDetailsItem12.getStatus();
                Locale locale = Locale.ROOT;
                String lowerCase = status3.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String lowerCase2 = AppConstants.SECONDARY_USER_STATE_VERIFIED.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                    status = childContactViewHolder.getStatus();
                    if (status != null) {
                        i4 = uat.psa.mym.mycitroenconnect.R.drawable.ic_active;
                        status.setBackground(ContextCompat.getDrawable(context2, i4));
                    }
                    tvCarAccessCount = childContactViewHolder.getTvCarAccessCount();
                    if (tvCarAccessCount != null) {
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        String string2 = context2.getString(uat.psa.mym.mycitroenconnect.R.string.access_of_car);
                        Intrinsics.checkNotNullExpressionValue(string2, "it.getString(R.string.access_of_car)");
                        String format2 = String.format(string2, Arrays.copyOf(new Object[]{getAccessCarCount(emergencyDetailsItem12.getCarAccess()), Integer.valueOf(this.totalCar)}, 2));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                        tvCarAccessCount.setText(format2);
                    }
                    divider = childContactViewHolder.getDivider();
                    if (divider == null) {
                        if (i2 == this.contactList.size() - 1) {
                            ExtensionsKt.hide(divider);
                            return;
                        } else {
                            ExtensionsKt.show(divider);
                            return;
                        }
                    }
                    return;
                }
                status = childContactViewHolder.getStatus();
                if (status != null) {
                    i4 = uat.psa.mym.mycitroenconnect.R.drawable.ic_pending;
                    status.setBackground(ContextCompat.getDrawable(context2, i4));
                }
                tvCarAccessCount = childContactViewHolder.getTvCarAccessCount();
                if (tvCarAccessCount != null) {
                }
                divider = childContactViewHolder.getDivider();
                if (divider == null) {
                }
            default:
                return;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        RecyclerView.ViewHolder sOSEmergencyContactViewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        switch (i2) {
            case 1:
                View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_contact_list, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …tact_list, parent, false)");
                sOSEmergencyContactViewHolder = new SOSEmergencyContactViewHolder(this, inflate);
                break;
            case 2:
                View inflate2 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_contact_list, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate2, "from(parent.context)\n   …tact_list, parent, false)");
                sOSEmergencyContactViewHolder = new SOSEmergencyContactViewHolder(this, inflate2);
                break;
            case 3:
                View inflate3 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_add_emergency_contact, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate3, "from(parent.context)\n   …y_contact, parent, false)");
                sOSEmergencyContactViewHolder = new AddEmergencyContactViewHolder(this, inflate3);
                break;
            case 4:
                View inflate4 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.cell_contact_list, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate4, "from(parent.context)\n   …tact_list, parent, false)");
                sOSEmergencyContactViewHolder = new SOSEmergencyContactViewHolder(this, inflate4);
                break;
            case 5:
                View inflate5 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_single_account_selection, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate5, "from(parent.context)\n   …selection, parent, false)");
                sOSEmergencyContactViewHolder = new AddChildContactViewHolder(this, inflate5);
                break;
            case 6:
                View inflate6 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_child_account, parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate6, "from(parent.context)\n   …d_account, parent, false)");
                sOSEmergencyContactViewHolder = new ChildContactViewHolder(this, inflate6);
                break;
            default:
                sOSEmergencyContactViewHolder = null;
                break;
        }
        Intrinsics.checkNotNull(sOSEmergencyContactViewHolder);
        return sOSEmergencyContactViewHolder;
    }

    public final void setMaxSelectionCount(int i2) {
        this.maxSelectionCount = i2;
    }

    public final void setOnContactListener(@Nullable ContactListInterface contactListInterface) {
        this.onContactListener = contactListInterface;
    }

    public final void setTotalCarCount(int i2) {
        this.totalCar = i2;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateContactList(@NotNull ArrayList<EmergencyDetailsItem> contactArrayList) {
        Intrinsics.checkNotNullParameter(contactArrayList, "contactArrayList");
        this.lastCheckedPos = -1;
        this.contactList = contactArrayList;
        notifyDataSetChanged();
    }
}
