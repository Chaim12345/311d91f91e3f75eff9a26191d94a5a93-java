package com.psa.mym.mycitroenconnect.controller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.textview.MaterialTextView;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.PlaceAutoCompleteAdapter;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class PlaceAutoCompleteAdapter extends RecyclerView.Adapter<PlaceAutoCompleteViewHolder> implements Filterable {
    @NotNull
    private CharacterStyle STYLE_BOLD;
    @NotNull
    private CharacterStyle STYLE_NORMAL;
    @NotNull
    private Context context;
    @Nullable
    private PlaceAutoCompleteClickListener placeAutoCompleteClickListener;
    @NotNull
    private PlacesClient placeClient;
    @NotNull
    private ArrayList<PlaceAutoComplete> resultList;

    /* loaded from: classes3.dex */
    public static final class PlaceAutoComplete {
        @NotNull
        private String address;
        @NotNull
        private String location;
        @NotNull
        private String placeId;

        public PlaceAutoComplete(@NotNull String placeId, @NotNull String location, @NotNull String address) {
            Intrinsics.checkNotNullParameter(placeId, "placeId");
            Intrinsics.checkNotNullParameter(location, "location");
            Intrinsics.checkNotNullParameter(address, "address");
            this.placeId = placeId;
            this.location = location;
            this.address = address;
        }

        public static /* synthetic */ PlaceAutoComplete copy$default(PlaceAutoComplete placeAutoComplete, String str, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = placeAutoComplete.placeId;
            }
            if ((i2 & 2) != 0) {
                str2 = placeAutoComplete.location;
            }
            if ((i2 & 4) != 0) {
                str3 = placeAutoComplete.address;
            }
            return placeAutoComplete.copy(str, str2, str3);
        }

        @NotNull
        public final String component1() {
            return this.placeId;
        }

        @NotNull
        public final String component2() {
            return this.location;
        }

        @NotNull
        public final String component3() {
            return this.address;
        }

        @NotNull
        public final PlaceAutoComplete copy(@NotNull String placeId, @NotNull String location, @NotNull String address) {
            Intrinsics.checkNotNullParameter(placeId, "placeId");
            Intrinsics.checkNotNullParameter(location, "location");
            Intrinsics.checkNotNullParameter(address, "address");
            return new PlaceAutoComplete(placeId, location, address);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof PlaceAutoComplete) {
                PlaceAutoComplete placeAutoComplete = (PlaceAutoComplete) obj;
                return Intrinsics.areEqual(this.placeId, placeAutoComplete.placeId) && Intrinsics.areEqual(this.location, placeAutoComplete.location) && Intrinsics.areEqual(this.address, placeAutoComplete.address);
            }
            return false;
        }

        @NotNull
        public final String getAddress() {
            return this.address;
        }

        @NotNull
        public final String getLocation() {
            return this.location;
        }

        @NotNull
        public final String getPlaceId() {
            return this.placeId;
        }

        public int hashCode() {
            return (((this.placeId.hashCode() * 31) + this.location.hashCode()) * 31) + this.address.hashCode();
        }

        public final void setAddress(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.address = str;
        }

        public final void setLocation(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.location = str;
        }

        public final void setPlaceId(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.placeId = str;
        }

        @NotNull
        public String toString() {
            return "PlaceAutoComplete(placeId=" + this.placeId + ", location=" + this.location + ", address=" + this.address + ')';
        }
    }

    /* loaded from: classes3.dex */
    public final class PlaceAutoCompleteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ PlaceAutoCompleteAdapter f10470a;
        @NotNull
        private final MaterialTextView tvAddress;
        @NotNull
        private final MaterialTextView tvLocation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaceAutoCompleteViewHolder(@NotNull PlaceAutoCompleteAdapter placeAutoCompleteAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f10470a = placeAutoCompleteAdapter;
            View findViewById = itemView.findViewById(R.id.tvLocation);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.tvLocation)");
            this.tvLocation = (MaterialTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvAddress);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvAddress)");
            this.tvAddress = (MaterialTextView) findViewById2;
            itemView.setOnClickListener(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onClick$lambda-0  reason: not valid java name */
        public static final void m119onClick$lambda0(PlaceAutoCompleteAdapter this$0, FetchPlaceResponse fetchPlaceResponse) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            PlaceAutoCompleteClickListener placeAutoCompleteClickListener = this$0.placeAutoCompleteClickListener;
            if (placeAutoCompleteClickListener != null) {
                Place place = fetchPlaceResponse.getPlace();
                Intrinsics.checkNotNullExpressionValue(place, "response.place");
                placeAutoCompleteClickListener.onPlaceClick(place);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onClick$lambda-1  reason: not valid java name */
        public static final void m120onClick$lambda1(PlaceAutoCompleteAdapter this$0, Exception exception) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(exception, "exception");
            if (exception instanceof ApiException) {
                Context context = this$0.context;
                ExtensionsKt.showToast(context, exception.getMessage() + "");
            }
        }

        @NotNull
        public final MaterialTextView getTvAddress() {
            return this.tvAddress;
        }

        @NotNull
        public final MaterialTextView getTvLocation() {
            return this.tvLocation;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@Nullable View view) {
            List mutableListOf;
            if (getAbsoluteAdapterPosition() <= -1 || this.f10470a.resultList.size() <= 0) {
                return;
            }
            Object obj = this.f10470a.resultList.get(getAbsoluteAdapterPosition());
            Intrinsics.checkNotNullExpressionValue(obj, "resultList[absoluteAdapterPosition]");
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
            Task<FetchPlaceResponse> fetchPlace = this.f10470a.placeClient.fetchPlace(FetchPlaceRequest.builder(((PlaceAutoComplete) obj).getPlaceId(), mutableListOf).build());
            final PlaceAutoCompleteAdapter placeAutoCompleteAdapter = this.f10470a;
            Task<FetchPlaceResponse> addOnSuccessListener = fetchPlace.addOnSuccessListener(new OnSuccessListener() { // from class: j.e
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj2) {
                    PlaceAutoCompleteAdapter.PlaceAutoCompleteViewHolder.m119onClick$lambda0(PlaceAutoCompleteAdapter.this, (FetchPlaceResponse) obj2);
                }
            });
            final PlaceAutoCompleteAdapter placeAutoCompleteAdapter2 = this.f10470a;
            addOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: j.d
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    PlaceAutoCompleteAdapter.PlaceAutoCompleteViewHolder.m120onClick$lambda1(PlaceAutoCompleteAdapter.this, exc);
                }
            });
        }
    }

    public PlaceAutoCompleteAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.resultList = new ArrayList<>();
        this.STYLE_BOLD = new StyleSpan(1);
        this.STYLE_NORMAL = new StyleSpan(0);
        PlacesClient createClient = Places.createClient(this.context);
        Intrinsics.checkNotNullExpressionValue(createClient, "createClient(context)");
        this.placeClient = createClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<PlaceAutoComplete> getPredictions(CharSequence charSequence) {
        ArrayList<PlaceAutoComplete> arrayList = new ArrayList<>();
        Task<FindAutocompletePredictionsResponse> findAutocompletePredictions = this.placeClient.findAutocompletePredictions(FindAutocompletePredictionsRequest.builder().setCountry(AppConstants.GEO_FENCE_TRANSITION_IN).setSessionToken(AutocompleteSessionToken.newInstance()).setQuery(charSequence.toString()).build());
        Intrinsics.checkNotNullExpressionValue(findAutocompletePredictions, "placeClient.findAutocompletePredictions(request)");
        try {
            Tasks.await(findAutocompletePredictions, 60L, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        } catch (ExecutionException e3) {
            e3.printStackTrace();
        } catch (TimeoutException e4) {
            e4.printStackTrace();
        }
        if (findAutocompletePredictions.isSuccessful()) {
            for (AutocompletePrediction autocompletePrediction : findAutocompletePredictions.getResult().getAutocompletePredictions()) {
                Logger logger = Logger.INSTANCE;
                logger.i("Place ID: " + autocompletePrediction.getPlaceId());
                String placeId = autocompletePrediction.getPlaceId();
                Intrinsics.checkNotNullExpressionValue(placeId, "prediction.placeId");
                String spannableString = autocompletePrediction.getPrimaryText(this.STYLE_NORMAL).toString();
                Intrinsics.checkNotNullExpressionValue(spannableString, "prediction.getPrimaryText(STYLE_NORMAL).toString()");
                String spannableString2 = autocompletePrediction.getFullText(this.STYLE_BOLD).toString();
                Intrinsics.checkNotNullExpressionValue(spannableString2, "prediction.getFullText(STYLE_BOLD).toString()");
                arrayList.add(new PlaceAutoComplete(placeId, spannableString, spannableString2));
            }
        }
        return arrayList;
    }

    @Override // android.widget.Filterable
    @NotNull
    public Filter getFilter() {
        return new Filter() { // from class: com.psa.mym.mycitroenconnect.controller.adapters.PlaceAutoCompleteAdapter$getFilter$1
            @Override // android.widget.Filter
            @NotNull
            protected Filter.FilterResults performFiltering(@Nullable CharSequence charSequence) {
                ArrayList predictions;
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (charSequence != null) {
                    PlaceAutoCompleteAdapter placeAutoCompleteAdapter = PlaceAutoCompleteAdapter.this;
                    predictions = placeAutoCompleteAdapter.getPredictions(charSequence);
                    placeAutoCompleteAdapter.resultList = predictions;
                    filterResults.values = PlaceAutoCompleteAdapter.this.resultList;
                    filterResults.count = PlaceAutoCompleteAdapter.this.resultList.size();
                }
                return filterResults;
            }

            @Override // android.widget.Filter
            @SuppressLint({"NotifyDataSetChanged"})
            protected void publishResults(@Nullable CharSequence charSequence, @Nullable Filter.FilterResults filterResults) {
                if (filterResults == null || filterResults.count <= 0) {
                    return;
                }
                PlaceAutoCompleteAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.resultList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull PlaceAutoCompleteViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvLocation().setText(this.resultList.get(i2).getLocation());
        holder.getTvAddress().setText(this.resultList.get(i2).getAddress());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public PlaceAutoCompleteViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_place_api_location, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …_location, parent, false)");
        return new PlaceAutoCompleteViewHolder(this, inflate);
    }

    public final void setPlaceAutoCompleteClickListener(@NotNull PlaceAutoCompleteClickListener placeAutoCompleteClickListener) {
        Intrinsics.checkNotNullParameter(placeAutoCompleteClickListener, "placeAutoCompleteClickListener");
        this.placeAutoCompleteClickListener = placeAutoCompleteClickListener;
    }
}
