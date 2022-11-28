package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.MyTripsSummaryAdapter;
import com.psa.mym.mycitroenconnect.model.trip.OnGoingResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripResponseDTO;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyTripsSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_ONGOING = 0;
    @NotNull
    private final Context context;
    public OnItemClickListener mListener;
    @NotNull
    private List<OnGoingResponse> ongoingTrip;
    @NotNull
    private List<TripResponseDTO> tripList;

    /* loaded from: classes3.dex */
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final Unit tag;
        @NotNull
        private final TextView tripDistanceCovered;
        @NotNull
        private final TextView tripDuration;
        @NotNull
        private final TextView tripSummaryDest;
        @NotNull
        private final TextView tripSummarySource;
        @NotNull
        private final TextView tripTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CardViewHolder(@NotNull View itemView, @NotNull final OnItemClickListener mListener) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            Intrinsics.checkNotNullParameter(mListener, "mListener");
            View findViewById = itemView.findViewById(R.id.tvTripTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvTripTitle)");
            this.tripTitle = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvTripSummarySource);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvTripSummarySource)");
            this.tripSummarySource = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvTripSummaryDest);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvTripSummaryDest)");
            this.tripSummaryDest = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tvTripDistanceCovered);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.tvTripDistanceCovered)");
            this.tripDistanceCovered = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tvTripDuration);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.tvTripDuration)");
            this.tripDuration = (TextView) findViewById5;
            itemView.setOnClickListener(new View.OnClickListener() { // from class: j.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyTripsSummaryAdapter.CardViewHolder.m116tag$lambda0(MyTripsSummaryAdapter.OnItemClickListener.this, this, view);
                }
            });
            this.tag = Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: tag$lambda-0  reason: not valid java name */
        public static final void m116tag$lambda0(OnItemClickListener mListener, CardViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(mListener, "$mListener");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.getAbsoluteAdapterPosition() != -1) {
                OnItemClickListener.DefaultImpls.onItemClick$default(mListener, this$0.getAbsoluteAdapterPosition(), 0, 2, null);
            }
        }

        @NotNull
        public final Unit getTag() {
            return this.tag;
        }

        @NotNull
        public final TextView getTripDistanceCovered() {
            return this.tripDistanceCovered;
        }

        @NotNull
        public final TextView getTripDuration() {
            return this.tripDuration;
        }

        @NotNull
        public final TextView getTripSummaryDest() {
            return this.tripSummaryDest;
        }

        @NotNull
        public final TextView getTripSummarySource() {
            return this.tripSummarySource;
        }

        @NotNull
        public final TextView getTripTitle() {
            return this.tripTitle;
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
    public interface OnItemClickListener {

        /* loaded from: classes3.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void onItemClick$default(OnItemClickListener onItemClickListener, int i2, int i3, int i4, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onItemClick");
                }
                if ((i4 & 2) != 0) {
                    i3 = 1;
                }
                onItemClickListener.onItemClick(i2, i3);
            }
        }

        void onItemClick(int i2, int i3);
    }

    /* loaded from: classes3.dex */
    public static final class OngoingCardViewHolder extends RecyclerView.ViewHolder {
        @NotNull
        private final CardView cvTripCard;
        @NotNull
        private final Unit tag;
        @NotNull
        private final TextView tripDistanceCovered;
        @NotNull
        private final TextView tripDuration;
        @NotNull
        private final TextView tripSummarySource;
        @NotNull
        private final TextView tripTitle;
        @NotNull
        private final AppCompatTextView tvOngoingIdlingChip;
        @NotNull
        private final AppCompatTextView tvOngoingRunningChip;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OngoingCardViewHolder(@NotNull View itemView, @NotNull final OnItemClickListener mListener) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            Intrinsics.checkNotNullParameter(mListener, "mListener");
            View findViewById = itemView.findViewById(R.id.cvTripCard);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.cvTripCard)");
            this.cvTripCard = (CardView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvTripTitle);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvTripTitle)");
            this.tripTitle = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvTripSummarySource);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvTripSummarySource)");
            this.tripSummarySource = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tvTripDistanceCovered);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.tvTripDistanceCovered)");
            this.tripDistanceCovered = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tvTripDuration);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.tvTripDuration)");
            this.tripDuration = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.tvOngoingRunningChip);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "itemView.findViewById(R.id.tvOngoingRunningChip)");
            this.tvOngoingRunningChip = (AppCompatTextView) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.tvOngoingIdlingChip);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "itemView.findViewById(R.id.tvOngoingIdlingChip)");
            this.tvOngoingIdlingChip = (AppCompatTextView) findViewById7;
            itemView.setOnClickListener(new View.OnClickListener() { // from class: j.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyTripsSummaryAdapter.OngoingCardViewHolder.m117tag$lambda0(MyTripsSummaryAdapter.OnItemClickListener.this, this, view);
                }
            });
            this.tag = Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: tag$lambda-0  reason: not valid java name */
        public static final void m117tag$lambda0(OnItemClickListener mListener, OngoingCardViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(mListener, "$mListener");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.getAbsoluteAdapterPosition() != -1) {
                mListener.onItemClick(this$0.getAbsoluteAdapterPosition(), 0);
            }
        }

        @NotNull
        public final CardView getCvTripCard() {
            return this.cvTripCard;
        }

        @NotNull
        public final Unit getTag() {
            return this.tag;
        }

        @NotNull
        public final TextView getTripDistanceCovered() {
            return this.tripDistanceCovered;
        }

        @NotNull
        public final TextView getTripDuration() {
            return this.tripDuration;
        }

        @NotNull
        public final TextView getTripSummarySource() {
            return this.tripSummarySource;
        }

        @NotNull
        public final TextView getTripTitle() {
            return this.tripTitle;
        }

        @NotNull
        public final AppCompatTextView getTvOngoingIdlingChip() {
            return this.tvOngoingIdlingChip;
        }

        @NotNull
        public final AppCompatTextView getTvOngoingRunningChip() {
            return this.tvOngoingRunningChip;
        }
    }

    public MyTripsSummaryAdapter(@NotNull Context context, @NotNull List<TripResponseDTO> tripList, @NotNull List<OnGoingResponse> ongoingTrip) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tripList, "tripList");
        Intrinsics.checkNotNullParameter(ongoingTrip, "ongoingTrip");
        this.context = context;
        this.tripList = tripList;
        this.ongoingTrip = ongoingTrip;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ongoingTrip.size() + this.tripList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (i2 < this.ongoingTrip.size()) {
            return 0;
        }
        this.ongoingTrip.size();
        this.tripList.size();
        return 1;
    }

    @NotNull
    public final OnItemClickListener getMListener() {
        OnItemClickListener onItemClickListener = this.mListener;
        if (onItemClickListener != null) {
            return onItemClickListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @SuppressLint({"SetTextI18n"})
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int i2) {
        TextView tripDistanceCovered;
        String str;
        TextView tripDistanceCovered2;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (!(holder instanceof OngoingCardViewHolder)) {
            CardViewHolder cardViewHolder = (CardViewHolder) holder;
            int size = i2 - this.ongoingTrip.size();
            TextView tripTitle = cardViewHolder.getTripTitle();
            StringBuilder sb = new StringBuilder();
            sb.append(this.tripList.get(size).getTripName());
            sb.append(TokenParser.SP);
            AppUtil.Companion companion = AppUtil.Companion;
            sb.append(companion.convertDate(this.tripList.get(size).getTripStartDate()));
            tripTitle.setText(sb.toString());
            if (this.tripList.get(size).getTripSourceName().length() > 0) {
                cardViewHolder.getTripSummarySource().setText(this.tripList.get(size).getTripSourceName());
            }
            if (this.tripList.get(size).getTripDestName().length() > 0) {
                cardViewHolder.getTripSummaryDest().setText(this.tripList.get(size).getTripDestName());
            }
            if (this.tripList.get(size).getDistanceTravelled() > 1.0d) {
                tripDistanceCovered = cardViewHolder.getTripDistanceCovered();
                str = ExtensionsKt.toOneDecimal(this.tripList.get(size).getDistanceTravelled()) + this.context.getString(R.string.lbl_kms_value);
            } else {
                tripDistanceCovered = cardViewHolder.getTripDistanceCovered();
                str = ExtensionsKt.toOneDecimal(this.tripList.get(size).getDistanceTravelled()) + this.context.getString(R.string.lbl_km_value);
            }
            tripDistanceCovered.setText(str);
            cardViewHolder.getTripDuration().setText(companion.convertSeconds((long) (this.tripList.get(size).getTotalTimeTravelled() * 60)));
            return;
        }
        OngoingCardViewHolder ongoingCardViewHolder = (OngoingCardViewHolder) holder;
        TextView tripTitle2 = ongoingCardViewHolder.getTripTitle();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.ongoingTrip.get(i2).getTripName());
        sb2.append(TokenParser.SP);
        AppUtil.Companion companion2 = AppUtil.Companion;
        sb2.append(companion2.convertDate(this.ongoingTrip.get(i2).getTripStartDate()));
        tripTitle2.setText(sb2.toString());
        if (this.ongoingTrip.get(i2).getTripSourceName().length() > 0) {
            ongoingCardViewHolder.getTripSummarySource().setText(this.ongoingTrip.get(i2).getTripSourceName());
        }
        if (this.ongoingTrip.get(i2).getDistanceTravelled() > 1.0d) {
            tripDistanceCovered2 = ongoingCardViewHolder.getTripDistanceCovered();
            str2 = ExtensionsKt.toOneDecimal(this.ongoingTrip.get(i2).getDistanceTravelled()) + this.context.getString(R.string.lbl_kms_value);
        } else {
            tripDistanceCovered2 = ongoingCardViewHolder.getTripDistanceCovered();
            str2 = ExtensionsKt.toOneDecimal(this.ongoingTrip.get(i2).getDistanceTravelled()) + this.context.getString(R.string.lbl_km_value);
        }
        tripDistanceCovered2.setText(str2);
        ongoingCardViewHolder.getTripDuration().setText(companion2.convertSeconds((long) (this.ongoingTrip.get(i2).getTotalTimeTravelled() * 60)));
        if (this.ongoingTrip.get(i2).getIgnitionStatus() != null) {
            String ignitionStatus = this.ongoingTrip.get(i2).getIgnitionStatus();
            if (ignitionStatus != null) {
                str3 = ignitionStatus.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str3 = null;
            }
            String lowerCase = AppConstants.IGNITION_STATUS_STOP.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(str3, lowerCase)) {
                ongoingCardViewHolder.getCvTripCard().setCardBackgroundColor(ContextCompat.getColor(this.context, R.color.white));
                ExtensionsKt.hide(ongoingCardViewHolder.getTvOngoingRunningChip());
                ExtensionsKt.hide(ongoingCardViewHolder.getTvOngoingIdlingChip());
                return;
            }
        }
        ongoingCardViewHolder.getCvTripCard().setCardBackgroundColor(ContextCompat.getColor(this.context, R.color.pastel_yellow));
        if (Intrinsics.areEqual(this.ongoingTrip.get(i2).getTripCurrentStatus(), AppConstants.ONGOING_TRIP_STATUS_RUNNING)) {
            ExtensionsKt.show(ongoingCardViewHolder.getTvOngoingRunningChip());
            ExtensionsKt.hide(ongoingCardViewHolder.getTvOngoingIdlingChip());
            return;
        }
        ExtensionsKt.hide(ongoingCardViewHolder.getTvOngoingRunningChip());
        ExtensionsKt.show(ongoingCardViewHolder.getTvOngoingIdlingChip());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        RecyclerView.ViewHolder ongoingCardViewHolder;
        RecyclerView.ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i2 == 0) {
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.cell_ongoing_trip, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …oing_trip, parent, false)");
            ongoingCardViewHolder = new OngoingCardViewHolder(inflate, getMListener());
        } else if (i2 != 1) {
            viewHolder = null;
            Intrinsics.checkNotNull(viewHolder);
            return viewHolder;
        } else {
            View inflate2 = LayoutInflater.from(this.context).inflate(R.layout.layout_trip_summary_card, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "from(context)\n          …mary_card, parent, false)");
            ongoingCardViewHolder = new CardViewHolder(inflate2, getMListener());
        }
        viewHolder = ongoingCardViewHolder;
        Intrinsics.checkNotNull(viewHolder);
        return viewHolder;
    }

    public final void setMListener(@NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "<set-?>");
        this.mListener = onItemClickListener;
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        setMListener(listener);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateOngoingTripList(@NotNull List<OnGoingResponse> ongoingTrips) {
        Intrinsics.checkNotNullParameter(ongoingTrips, "ongoingTrips");
        this.ongoingTrip = ongoingTrips;
        notifyDataSetChanged();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void updateTripList(@NotNull List<TripResponseDTO> trips) {
        Intrinsics.checkNotNullParameter(trips, "trips");
        this.tripList = trips;
        notifyDataSetChanged();
    }
}
