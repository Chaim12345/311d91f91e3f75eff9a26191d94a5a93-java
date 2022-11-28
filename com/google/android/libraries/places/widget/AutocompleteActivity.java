package com.google.android.libraries.places.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzfl;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Objects;
/* loaded from: classes2.dex */
public class AutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener {
    public static final int RESULT_ERROR = 2;
    @VisibleForTesting
    static boolean zza = true;
    @LayoutRes
    private int zzb;
    @StyleRes
    private int zzc;
    private boolean zzd;

    public AutocompleteActivity() {
        super(R.layout.places_autocomplete_activity);
        this.zzd = false;
    }

    private final void zzc(int i2, @Nullable Place place, Status status) {
        try {
            Intent intent = new Intent();
            if (place != null) {
                intent.putExtra("places/selected_place", place);
            }
            intent.putExtra("places/status", status);
            setResult(i2, intent);
            finish();
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097 A[Catch: Error | RuntimeException -> 0x00a6, Error -> 0x00a8, TRY_LEAVE, TryCatch #2 {Error | RuntimeException -> 0x00a6, blocks: (B:2:0x0000, B:4:0x000f, B:8:0x0018, B:9:0x001d, B:11:0x002c, B:18:0x004b, B:21:0x0070, B:23:0x0097, B:15:0x003b, B:16:0x0041, B:17:0x0044), top: B:30:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(@Nullable Bundle bundle) {
        int i2;
        try {
            zzha.zzi(Places.isInitialized(), "Places must be initialized.");
            if (zza) {
                zzha.zzi(getCallingActivity() != null, "Cannot find caller. startActivityForResult should be used.");
            }
            zzfl zzflVar = (zzfl) getIntent().getParcelableExtra("places/AutocompleteOptions");
            Objects.requireNonNull(zzflVar);
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            int ordinal = zzflVar.zzh().ordinal();
            if (ordinal == 0) {
                this.zzb = R.layout.places_autocomplete_impl_fragment_fullscreen;
                i2 = R.style.PlacesAutocompleteFullscreen;
            } else if (ordinal != 1) {
                getSupportFragmentManager().setFragmentFactory(new com.google.android.libraries.places.widget.internal.ui.zzh(this.zzb, this, zzflVar));
                setTheme(this.zzc);
                super.onCreate(bundle);
                final AutocompleteImplFragment autocompleteImplFragment = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_content);
                zzha.zzh(autocompleteImplFragment != null);
                autocompleteImplFragment.zzh(this);
                final View findViewById = findViewById(16908290);
                findViewById.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.places.widget.zzb
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return AutocompleteActivity.this.zzb(autocompleteImplFragment, findViewById, view, motionEvent);
                    }
                });
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zza
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AutocompleteActivity.this.zza(view);
                    }
                });
                if (zzflVar.zzj().isEmpty()) {
                    return;
                }
                zzc(2, null, new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty."));
                return;
            } else {
                this.zzb = R.layout.places_autocomplete_impl_fragment_overlay;
                i2 = R.style.PlacesAutocompleteOverlay;
            }
            this.zzc = i2;
            getSupportFragmentManager().setFragmentFactory(new com.google.android.libraries.places.widget.internal.ui.zzh(this.zzb, this, zzflVar));
            setTheme(this.zzc);
            super.onCreate(bundle);
            final AutocompleteImplFragment autocompleteImplFragment2 = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_content);
            zzha.zzh(autocompleteImplFragment2 != null);
            autocompleteImplFragment2.zzh(this);
            final View findViewById2 = findViewById(16908290);
            findViewById2.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.places.widget.zzb
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return AutocompleteActivity.this.zzb(autocompleteImplFragment2, findViewById2, view, motionEvent);
                }
            });
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zza
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AutocompleteActivity.this.zza(view);
                }
            });
            if (zzflVar.zzj().isEmpty()) {
            }
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onError(@RecentlyNonNull Status status) {
        zzc(true != status.isCanceled() ? 2 : 0, null, status);
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onPlaceSelected(@RecentlyNonNull Place place) {
        zzc(-1, place, Status.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view) {
        if (this.zzd) {
            zzc(0, null, new Status(16));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzb(AutocompleteImplFragment autocompleteImplFragment, View view, View view2, MotionEvent motionEvent) {
        this.zzd = false;
        View view3 = autocompleteImplFragment.getView();
        if (view3 != null && motionEvent.getY() > view3.getBottom()) {
            this.zzd = true;
            view.performClick();
            return true;
        }
        return false;
    }
}
