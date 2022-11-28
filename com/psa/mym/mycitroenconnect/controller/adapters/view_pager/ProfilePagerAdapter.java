package com.psa.mym.mycitroenconnect.controller.adapters.view_pager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ProfilePagerAdapter extends FragmentStateAdapter {
    @NotNull
    private List<? extends Fragment> fragments;
    @Nullable
    private String isGuestUser;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilePagerAdapter(@NotNull FragmentManager fragmentManager, @NotNull Lifecycle lifeCycle, @NotNull List<? extends Fragment> fragments) {
        super(fragmentManager, lifeCycle);
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(lifeCycle, "lifeCycle");
        Intrinsics.checkNotNullParameter(fragments, "fragments");
        this.fragments = fragments;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NotNull
    public Fragment createFragment(int i2) {
        return this.fragments.get(i2);
    }

    @NotNull
    public final List<Fragment> getFragments() {
        return this.fragments;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fragments.size();
    }

    @Nullable
    public final String isGuestUser() {
        return this.isGuestUser;
    }

    public final void setFragments(@NotNull List<? extends Fragment> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.fragments = list;
    }

    public final void setGuestUser(@Nullable String str) {
        this.isGuestUser = str;
    }
}
