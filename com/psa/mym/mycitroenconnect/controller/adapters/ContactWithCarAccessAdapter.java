package com.psa.mym.mycitroenconnect.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ContactWithCarAccessAdapter extends RecyclerView.Adapter<ContactWithCarAccessViewHolder> {
    @NotNull
    private final HashMap<Integer, ArrayList<Boolean>> carAccessMap;
    @NotNull
    private ArrayList<EmergencyDetailsItem> contactList;
    @Nullable
    private Context context;

    /* loaded from: classes3.dex */
    public final class ContactWithCarAccessViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        private View divider;
        @Nullable
        private RecyclerView rvCarAccess;
        @Nullable
        private AppCompatTextView tvName;
        @Nullable
        private AppCompatTextView tvNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContactWithCarAccessViewHolder(@NotNull ContactWithCarAccessAdapter contactWithCarAccessAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.tvName = (AppCompatTextView) itemView.findViewById(R.id.tvName);
            this.tvNumber = (AppCompatTextView) itemView.findViewById(R.id.tvNumber);
            this.rvCarAccess = (RecyclerView) itemView.findViewById(R.id.rvCarAccess);
            this.divider = itemView.findViewById(R.id.divider);
        }

        @Nullable
        public final View getDivider() {
            return this.divider;
        }

        @Nullable
        public final RecyclerView getRvCarAccess() {
            return this.rvCarAccess;
        }

        @Nullable
        public final AppCompatTextView getTvName() {
            return this.tvName;
        }

        @Nullable
        public final AppCompatTextView getTvNumber() {
            return this.tvNumber;
        }

        public final void setDivider(@Nullable View view) {
            this.divider = view;
        }

        public final void setRvCarAccess(@Nullable RecyclerView recyclerView) {
            this.rvCarAccess = recyclerView;
        }

        public final void setTvName(@Nullable AppCompatTextView appCompatTextView) {
            this.tvName = appCompatTextView;
        }

        public final void setTvNumber(@Nullable AppCompatTextView appCompatTextView) {
            this.tvNumber = appCompatTextView;
        }
    }

    public ContactWithCarAccessAdapter(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
        Intrinsics.checkNotNullParameter(contactList, "contactList");
        this.contactList = contactList;
        this.carAccessMap = new HashMap<>();
    }

    @NotNull
    public final HashMap<Integer, ArrayList<Boolean>> getCarAccessMap() {
        return this.carAccessMap;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.contactList.size();
    }

    public final boolean isCarAccessGiven() {
        for (Map.Entry<Integer, ArrayList<Boolean>> entry : this.carAccessMap.entrySet()) {
            int intValue = entry.getKey().intValue();
            ArrayList<Boolean> value = entry.getValue();
            Logger logger = Logger.INSTANCE;
            logger.e("CarAccess at " + intValue + TokenParser.SP + value);
            if (!value.contains(Boolean.TRUE)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ContactWithCarAccessViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        EmergencyDetailsItem emergencyDetailsItem = this.contactList.get(i2);
        Intrinsics.checkNotNullExpressionValue(emergencyDetailsItem, "contactList[position]");
        EmergencyDetailsItem emergencyDetailsItem2 = emergencyDetailsItem;
        AppCompatTextView tvName = holder.getTvName();
        if (tvName != null) {
            tvName.setText(emergencyDetailsItem2.getName());
        }
        AppCompatTextView tvNumber = holder.getTvNumber();
        if (tvNumber != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Context context = this.context;
            String string = context != null ? context.getString(uat.psa.mym.mycitroenconnect.R.string.full_mobile_number) : null;
            Intrinsics.checkNotNull(string);
            String format = String.format(string, Arrays.copyOf(new Object[]{emergencyDetailsItem2.getCountryCode(), emergencyDetailsItem2.getContactNum()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            tvNumber.setText(format);
        }
        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (RegisteredVehicleItem registeredVehicleItem : emergencyDetailsItem2.getCarAccess()) {
            arrayList.add(Boolean.valueOf(registeredVehicleItem.isAccessible()));
        }
        this.carAccessMap.put(Integer.valueOf(i2), arrayList);
        SelectCarAccessAdapter selectCarAccessAdapter = new SelectCarAccessAdapter(emergencyDetailsItem2.getCarAccess());
        selectCarAccessAdapter.setCarAccessListener(new CarAccessListener() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.ContactWithCarAccessAdapter$onBindViewHolder$2
            @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarAccessListener
            public void onCarAccessButtonTap(int i3, @NotNull RegisteredVehicleItem car) {
                Intrinsics.checkNotNullParameter(car, "car");
            }

            @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarAccessListener
            public void onCarAccessChanged(int i3, @NotNull RegisteredVehicleItem car) {
                Intrinsics.checkNotNullParameter(car, "car");
                ArrayList<Boolean> arrayList2 = ContactWithCarAccessAdapter.this.getCarAccessMap().get(Integer.valueOf(holder.getAbsoluteAdapterPosition()));
                Intrinsics.checkNotNull(arrayList2);
                ArrayList<Boolean> arrayList3 = arrayList2;
                int i4 = 0;
                for (Object obj : arrayList3) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    ((Boolean) obj).booleanValue();
                    if (i4 == i3) {
                        arrayList3.set(i4, Boolean.valueOf(car.isAccessible()));
                    }
                    i4 = i5;
                }
                ContactWithCarAccessAdapter.this.getCarAccessMap().put(Integer.valueOf(holder.getAbsoluteAdapterPosition()), arrayList3);
            }
        });
        RecyclerView rvCarAccess = holder.getRvCarAccess();
        if (rvCarAccess != null) {
            rvCarAccess.setAdapter(selectCarAccessAdapter);
            rvCarAccess.setLayoutManager(new LinearLayoutManager(this.context));
        }
        View divider = holder.getDivider();
        if (divider != null) {
            if (i2 == this.contactList.size() - 1) {
                ExtensionsKt.hide(divider);
            } else {
                ExtensionsKt.show(divider);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ContactWithCarAccessViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (this.context == null) {
            this.context = parent.getContext();
        }
        View inflate = LayoutInflater.from(this.context).inflate(uat.psa.mym.mycitroenconnect.R.layout.item_view_car_access_confirm, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …s_confirm, parent, false)");
        return new ContactWithCarAccessViewHolder(this, inflate);
    }
}
