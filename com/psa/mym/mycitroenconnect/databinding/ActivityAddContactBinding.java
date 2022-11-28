package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityAddContactBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAddContact;
    @NonNull
    public final FrameLayout flParent;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutHeaderAddContact;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvContactList;
    @NonNull
    public final SearchView svContact;

    private ActivityAddContactBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull FrameLayout frameLayout2, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull ProgressBar progressBar, @NonNull RecyclerView recyclerView, @NonNull SearchView searchView) {
        this.rootView = frameLayout;
        this.btnAddContact = appCompatButton;
        this.flParent = frameLayout2;
        this.layoutHeaderAddContact = layoutDashboardModeHeaderBinding;
        this.progressBar = progressBar;
        this.rvContactList = recyclerView;
        this.svContact = searchView;
    }

    @NonNull
    public static ActivityAddContactBinding bind(@NonNull View view) {
        int i2 = R.id.btnAddContact;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAddContact);
        if (appCompatButton != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i2 = R.id.layoutHeaderAddContact;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutHeaderAddContact);
            if (findChildViewById != null) {
                LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                i2 = R.id.progressBar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressBar);
                if (progressBar != null) {
                    i2 = R.id.rvContactList;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvContactList);
                    if (recyclerView != null) {
                        i2 = R.id.svContact;
                        SearchView searchView = (SearchView) ViewBindings.findChildViewById(view, R.id.svContact);
                        if (searchView != null) {
                            return new ActivityAddContactBinding(frameLayout, appCompatButton, frameLayout, bind, progressBar, recyclerView, searchView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityAddContactBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityAddContactBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_add_contact, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
