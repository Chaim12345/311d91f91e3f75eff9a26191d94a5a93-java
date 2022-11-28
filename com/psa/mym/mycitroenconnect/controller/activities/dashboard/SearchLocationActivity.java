package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.textfield.TextInputEditText;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.controller.adapters.PlaceAutoCompleteAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.PlaceAutoCompleteClickListener;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SearchLocationActivity extends BaseActivity implements TextWatcher, PlaceAutoCompleteClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private PlaceAutoCompleteAdapter placeAutoCompleteAdapter;

    @SuppressLint({"NotifyDataSetChanged"})
    private final void init() {
        Places.initialize(this, BuildConfig.GOOGLE_PLACES_ANDROID_API_KEY);
        int i2 = R.id.rvLocation;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        Drawable drawable = ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_horizontal_line);
        Intrinsics.checkNotNull(drawable);
        dividerItemDecoration.setDrawable(drawable);
        ((RecyclerView) _$_findCachedViewById(i2)).addItemDecoration(dividerItemDecoration);
        PlaceAutoCompleteAdapter placeAutoCompleteAdapter = new PlaceAutoCompleteAdapter(this);
        this.placeAutoCompleteAdapter = placeAutoCompleteAdapter;
        placeAutoCompleteAdapter.setPlaceAutoCompleteClickListener(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        PlaceAutoCompleteAdapter placeAutoCompleteAdapter2 = this.placeAutoCompleteAdapter;
        PlaceAutoCompleteAdapter placeAutoCompleteAdapter3 = null;
        if (placeAutoCompleteAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("placeAutoCompleteAdapter");
            placeAutoCompleteAdapter2 = null;
        }
        recyclerView.setAdapter(placeAutoCompleteAdapter2);
        PlaceAutoCompleteAdapter placeAutoCompleteAdapter4 = this.placeAutoCompleteAdapter;
        if (placeAutoCompleteAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("placeAutoCompleteAdapter");
        } else {
            placeAutoCompleteAdapter3 = placeAutoCompleteAdapter4;
        }
        placeAutoCompleteAdapter3.notifyDataSetChanged();
        ((TextInputEditText) _$_findCachedViewById(R.id.edtSearchLocation)).addTextChangedListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@Nullable Editable editable) {
        RecyclerView recyclerView;
        int i2;
        if (Intrinsics.areEqual(String.valueOf(editable), "")) {
            recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvLocation);
            i2 = 8;
        } else {
            PlaceAutoCompleteAdapter placeAutoCompleteAdapter = this.placeAutoCompleteAdapter;
            if (placeAutoCompleteAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("placeAutoCompleteAdapter");
                placeAutoCompleteAdapter = null;
            }
            placeAutoCompleteAdapter.getFilter().filter(String.valueOf(editable));
            recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvLocation);
            i2 = 0;
        }
        recyclerView.setVisibility(i2);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBack))) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_search_location);
        init();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.PlaceAutoCompleteClickListener
    public void onPlaceClick(@NotNull Place place) {
        Intrinsics.checkNotNullParameter(place, "place");
        AppUtil.Companion.hideKeyboard(this);
        Intent intent = new Intent();
        LatLng latLng = place.getLatLng();
        intent.putExtra("latitude", latLng != null ? Double.valueOf(latLng.latitude) : null);
        LatLng latLng2 = place.getLatLng();
        intent.putExtra("longitude", latLng2 != null ? Double.valueOf(latLng2.longitude) : null);
        intent.putExtra("address", place.getAddress());
        setResult(-1, intent);
        finish();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
    }
}
