package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    @NotNull
    private ArrayList<EmergencyDetailsItem> contactList;
    private int contactType;
    @Nullable
    private Context context;
    @NotNull
    private ArrayList<EmergencyDetailsItem> filterContactList;
    private int maxSelectionCount;
    @Nullable
    private AddContactSelectListener onAddContactSelectListener;
    @NotNull
    private ArrayList<String> selectedContact;

    /* loaded from: classes3.dex */
    public final class ChildContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddContactAdapter f10450a;
        @NotNull
        private ConstraintLayout llSelectAccount;
        @NotNull
        private AppCompatRadioButton rbSelect;
        @NotNull
        private AppCompatTextView tvName;
        @NotNull
        private AppCompatTextView tvNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChildContactViewHolder(@NotNull AddContactAdapter addContactAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10450a = addContactAdapter;
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.llSelectAccount);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.llSelectAccount");
            this.llSelectAccount = constraintLayout;
            MaterialRadioButton materialRadioButton = (MaterialRadioButton) itemView.findViewById(R.id.rbSelect);
            Intrinsics.checkNotNullExpressionValue(materialRadioButton, "itemView.rbSelect");
            this.rbSelect = materialRadioButton;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvName);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvName");
            this.tvName = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvNumber);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvNumber");
            this.tvNumber = appCompatTextView2;
            this.llSelectAccount.setOnClickListener(this);
            this.rbSelect.setOnClickListener(this);
        }

        @NotNull
        public final ConstraintLayout getLlSelectAccount() {
            return this.llSelectAccount;
        }

        @NotNull
        public final AppCompatRadioButton getRbSelect() {
            return this.rbSelect;
        }

        @NotNull
        public final AppCompatTextView getTvName() {
            return this.tvName;
        }

        @NotNull
        public final AppCompatTextView getTvNumber() {
            return this.tvNumber;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            if (getAbsoluteAdapterPosition() <= -1 || !(!this.f10450a.filterContactList.isEmpty()) || this.f10450a.filterContactList.size() <= 0) {
                return;
            }
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if ((valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.llSelectAccount) || (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.rbSelect)) {
                this.f10450a.unSelectAll();
                ((EmergencyDetailsItem) this.f10450a.filterContactList.get(getAbsoluteAdapterPosition())).setChildContact(true);
                AddContactAdapter addContactAdapter = this.f10450a;
                addContactAdapter.setChildSelectContact(((EmergencyDetailsItem) addContactAdapter.filterContactList.get(getAbsoluteAdapterPosition())).getContactNum());
                AddContactSelectListener addContactSelectListener = this.f10450a.onAddContactSelectListener;
                if (addContactSelectListener != null) {
                    addContactSelectListener.onAddContactSelect(this.f10450a.selectedContact.size());
                }
                this.f10450a.notifyDataSetChanged();
            }
        }

        public final void setLlSelectAccount(@NotNull ConstraintLayout constraintLayout) {
            Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
            this.llSelectAccount = constraintLayout;
        }

        public final void setRbSelect(@NotNull AppCompatRadioButton appCompatRadioButton) {
            Intrinsics.checkNotNullParameter(appCompatRadioButton, "<set-?>");
            this.rbSelect = appCompatRadioButton;
        }

        public final void setTvName(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvName = appCompatTextView;
        }

        public final void setTvNumber(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvNumber = appCompatTextView;
        }
    }

    /* loaded from: classes3.dex */
    public final class EmergencyContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddContactAdapter f10451a;
        @NotNull
        private AppCompatCheckBox chkAddEmergencyContact;
        @NotNull
        private ConstraintLayout layoutAddEmergContact;
        @NotNull
        private AppCompatTextView tvAddContactName;
        @NotNull
        private AppCompatTextView tvAddContactNo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EmergencyContactViewHolder(@NotNull AddContactAdapter addContactAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10451a = addContactAdapter;
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layoutAddEmergContact);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.layoutAddEmergContact");
            this.layoutAddEmergContact = constraintLayout;
            AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) itemView.findViewById(R.id.chkAddEmergencyContact);
            Intrinsics.checkNotNullExpressionValue(appCompatCheckBox, "itemView.chkAddEmergencyContact");
            this.chkAddEmergencyContact = appCompatCheckBox;
            AppCompatTextView appCompatTextView = (AppCompatTextView) itemView.findViewById(R.id.tvAddContactName);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView, "itemView.tvAddContactName");
            this.tvAddContactName = appCompatTextView;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) itemView.findViewById(R.id.tvAddContactNo);
            Intrinsics.checkNotNullExpressionValue(appCompatTextView2, "itemView.tvAddContactNo");
            this.tvAddContactNo = appCompatTextView2;
            this.layoutAddEmergContact.setOnClickListener(this);
            this.chkAddEmergencyContact.setOnClickListener(this);
        }

        @NotNull
        public final AppCompatCheckBox getChkAddEmergencyContact() {
            return this.chkAddEmergencyContact;
        }

        @NotNull
        public final ConstraintLayout getLayoutAddEmergContact() {
            return this.layoutAddEmergContact;
        }

        @NotNull
        public final AppCompatTextView getTvAddContactName() {
            return this.tvAddContactName;
        }

        @NotNull
        public final AppCompatTextView getTvAddContactNo() {
            return this.tvAddContactNo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            if (getAbsoluteAdapterPosition() <= -1 || !(!this.f10451a.filterContactList.isEmpty()) || this.f10451a.filterContactList.size() <= 0) {
                return;
            }
            Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
            if ((valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.chkAddEmergencyContact) || (valueOf != null && valueOf.intValue() == uat.psa.mym.mycitroenconnect.R.id.layoutAddEmergContact)) {
                Object obj = this.f10451a.filterContactList.get(getAbsoluteAdapterPosition());
                Intrinsics.checkNotNullExpressionValue(obj, "filterContactList[absoluteAdapterPosition]");
                EmergencyDetailsItem emergencyDetailsItem = (EmergencyDetailsItem) obj;
                if (this.f10451a.isMaximumSelectedEmergencyContact()) {
                    this.f10451a.removeFromSelection(emergencyDetailsItem.getContactNum());
                    this.chkAddEmergencyContact.setChecked(false);
                    emergencyDetailsItem.setEmergencyContact(false);
                } else if (emergencyDetailsItem.isEmergencyContact()) {
                    this.f10451a.removeFromSelection(emergencyDetailsItem.getContactNum());
                    emergencyDetailsItem.setEmergencyContact(false);
                    this.chkAddEmergencyContact.setChecked(false);
                } else {
                    this.f10451a.selectedContact.add(emergencyDetailsItem.getContactNum());
                    emergencyDetailsItem.setEmergencyContact(true);
                    this.chkAddEmergencyContact.setChecked(true);
                }
                AddContactSelectListener addContactSelectListener = this.f10451a.onAddContactSelectListener;
                if (addContactSelectListener != null) {
                    addContactSelectListener.onAddContactSelect(this.f10451a.selectedContact.size());
                }
                this.f10451a.notifyItemChanged(getAbsoluteAdapterPosition());
            }
        }

        public final void setChkAddEmergencyContact(@NotNull AppCompatCheckBox appCompatCheckBox) {
            Intrinsics.checkNotNullParameter(appCompatCheckBox, "<set-?>");
            this.chkAddEmergencyContact = appCompatCheckBox;
        }

        public final void setLayoutAddEmergContact(@NotNull ConstraintLayout constraintLayout) {
            Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
            this.layoutAddEmergContact = constraintLayout;
        }

        public final void setTvAddContactName(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAddContactName = appCompatTextView;
        }

        public final void setTvAddContactNo(@NotNull AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAddContactNo = appCompatTextView;
        }
    }

    public AddContactAdapter(@NotNull ArrayList<EmergencyDetailsItem> contactList, int i2, int i3) {
        Intrinsics.checkNotNullParameter(contactList, "contactList");
        this.contactList = contactList;
        this.contactType = i2;
        this.maxSelectionCount = i3;
        this.filterContactList = new ArrayList<>();
        this.selectedContact = new ArrayList<>();
        this.filterContactList = this.contactList;
    }

    public /* synthetic */ AddContactAdapter(ArrayList arrayList, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, i2, (i4 & 4) != 0 ? 2 : i3);
    }

    private final boolean checkFromSelection(String str) {
        for (String str2 : this.selectedContact) {
            if (Intrinsics.areEqual(str, str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeFromSelection(String str) {
        Iterator<String> it = this.selectedContact.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "selectedContact.iterator()");
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next(), str)) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChildSelectContact(String str) {
        this.selectedContact.clear();
        this.selectedContact.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unSelectAll() {
        Iterator<EmergencyDetailsItem> it = this.filterContactList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "filterContactList.iterator()");
        while (it.hasNext()) {
            it.next().setChildContact(false);
        }
    }

    @Override // android.widget.Filterable
    @NotNull
    public Filter getFilter() {
        return new Filter() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.AddContactAdapter$getFilter$1
            @Override // android.widget.Filter
            @NotNull
            protected Filter.FilterResults performFiltering(@Nullable CharSequence charSequence) {
                ArrayList arrayList;
                ArrayList arrayList2;
                int size;
                ArrayList<EmergencyDetailsItem> arrayList3;
                boolean contains$default;
                boolean contains$default2;
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (charSequence != null) {
                    if (charSequence.length() > 0) {
                        ArrayList arrayList4 = new ArrayList();
                        arrayList3 = AddContactAdapter.this.contactList;
                        for (EmergencyDetailsItem emergencyDetailsItem : arrayList3) {
                            String name = emergencyDetailsItem.getName();
                            Locale locale = Locale.ROOT;
                            String lowerCase = name.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            String lowerCase2 = charSequence.toString().toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            contains$default = StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null);
                            if (!contains$default) {
                                String lowerCase3 = emergencyDetailsItem.getContactNum().toLowerCase(locale);
                                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                String lowerCase4 = charSequence.toString().toLowerCase(locale);
                                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                                contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) lowerCase4, false, 2, (Object) null);
                                if (contains$default2) {
                                }
                            }
                            arrayList4.add(emergencyDetailsItem);
                        }
                        filterResults.values = arrayList4;
                        size = arrayList4.size();
                        filterResults.count = size;
                        return filterResults;
                    }
                }
                arrayList = AddContactAdapter.this.contactList;
                filterResults.values = arrayList;
                arrayList2 = AddContactAdapter.this.contactList;
                size = arrayList2.size();
                filterResults.count = size;
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(@Nullable CharSequence charSequence, @Nullable Filter.FilterResults filterResults) {
                AddContactAdapter addContactAdapter = AddContactAdapter.this;
                Object obj = filterResults != null ? filterResults.values : null;
                Objects.requireNonNull(obj, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem> }");
                addContactAdapter.filterContactList = (ArrayList) obj;
                AddContactAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.filterContactList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        int i3 = this.contactType;
        return (i3 == 1 || i3 == 2) ? i3 : super.getItemViewType(i2);
    }

    public final int getMaxSelectionCount() {
        return this.maxSelectionCount;
    }

    @NotNull
    public final ArrayList<EmergencyDetailsItem> getSelectedContact() {
        ArrayList<EmergencyDetailsItem> arrayList = new ArrayList<>();
        for (String str : this.selectedContact) {
            for (EmergencyDetailsItem emergencyDetailsItem : this.contactList) {
                if (Intrinsics.areEqual(str, emergencyDetailsItem.getContactNum())) {
                    arrayList.add(emergencyDetailsItem);
                }
            }
        }
        return arrayList;
    }

    public final boolean isMaximumSelectedEmergencyContact() {
        int i2 = this.maxSelectionCount;
        if (i2 == 0 || i2 == -1) {
            this.maxSelectionCount = 2;
        }
        return this.selectedContact.size() >= this.maxSelectionCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        Context context;
        AppCompatTextView tvNumber;
        Context context2;
        int color;
        Intrinsics.checkNotNullParameter(holder, "holder");
        EmergencyDetailsItem emergencyDetailsItem = this.filterContactList.get(i2);
        Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem, "filterContactList[position]");
        EmergencyDetailsItem emergencyDetailsItem2 = emergencyDetailsItem;
        if (holder instanceof EmergencyContactViewHolder) {
            EmergencyContactViewHolder emergencyContactViewHolder = (EmergencyContactViewHolder) holder;
            emergencyContactViewHolder.getTvAddContactName().setText(emergencyDetailsItem2.getName());
            emergencyContactViewHolder.getTvAddContactNo().setText(emergencyDetailsItem2.getContactNum());
            if (emergencyDetailsItem2.isEmergencyContact() && !this.selectedContact.contains(emergencyDetailsItem2.getContactNum())) {
                this.selectedContact.add(emergencyDetailsItem2.getContactNum());
            }
            if (checkFromSelection(emergencyDetailsItem2.getContactNum())) {
                emergencyContactViewHolder.getChkAddEmergencyContact().setChecked(true);
                context2 = this.context;
                if (context2 == null) {
                    return;
                }
                emergencyContactViewHolder.getTvAddContactName().setTextColor(context2.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                tvNumber = emergencyContactViewHolder.getTvAddContactNo();
                color = context2.getColor(uat.psa.mym.mycitroenconnect.R.color.black_600);
            } else {
                emergencyContactViewHolder.getChkAddEmergencyContact().setChecked(false);
                context = this.context;
                if (context == null) {
                    return;
                }
                emergencyContactViewHolder.getTvAddContactName().setTextColor(context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                tvNumber = emergencyContactViewHolder.getTvAddContactNo();
                color = context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70);
            }
        } else if (!(holder instanceof ChildContactViewHolder)) {
            return;
        } else {
            ChildContactViewHolder childContactViewHolder = (ChildContactViewHolder) holder;
            childContactViewHolder.getTvName().setText(emergencyDetailsItem2.getName());
            childContactViewHolder.getTvNumber().setText(emergencyDetailsItem2.getContactNum());
            if (emergencyDetailsItem2.isChildContact() && !this.selectedContact.contains(emergencyDetailsItem2.getContactNum())) {
                this.selectedContact.add(emergencyDetailsItem2.getContactNum());
            }
            if (checkFromSelection(emergencyDetailsItem2.getContactNum())) {
                childContactViewHolder.getRbSelect().setChecked(true);
                context2 = this.context;
                if (context2 == null) {
                    return;
                }
                childContactViewHolder.getTvName().setTextColor(context2.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                tvNumber = childContactViewHolder.getTvNumber();
                color = context2.getColor(uat.psa.mym.mycitroenconnect.R.color.black_600);
            } else {
                childContactViewHolder.getRbSelect().setChecked(false);
                context = this.context;
                if (context == null) {
                    return;
                }
                childContactViewHolder.getTvName().setTextColor(context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70));
                tvNumber = childContactViewHolder.getTvNumber();
                color = context.getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_70);
            }
        }
        tvNumber.setTextColor(color);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        RecyclerView.ViewHolder viewHolder = null;
        if (i2 == 1) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_add_emergency_contact, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …y_contact, parent, false)");
            viewHolder = new EmergencyContactViewHolder(this, inflate);
        } else if (i2 == 2) {
            View inflate2 = LayoutInflater.from(parent.getContext()).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_single_account_selection, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "from(parent.context)\n   …selection, parent, false)");
            viewHolder = new ChildContactViewHolder(this, inflate2);
        }
        Intrinsics.checkNotNull(viewHolder);
        return viewHolder;
    }

    public final void setMaxSelectionCount(int i2) {
        this.maxSelectionCount = i2;
    }

    public final void setOnAddContactSelectListener(@NotNull AddContactSelectListener onAddContactSelectListener) {
        Intrinsics.checkNotNullParameter(onAddContactSelectListener, "onAddContactSelectListener");
        this.onAddContactSelectListener = onAddContactSelectListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateContactList(@NotNull ArrayList<EmergencyDetailsItem> contactArrayList) {
        Intrinsics.checkNotNullParameter(contactArrayList, "contactArrayList");
        this.contactList = contactArrayList;
        this.filterContactList = contactArrayList;
        notifyDataSetChanged();
    }
}
