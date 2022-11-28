package com.psa.mym.mycitroenconnect.controller.adapters.view_pager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class IntroSliderAdapter extends FragmentStateAdapter {
    @NotNull
    private final ArrayList<Fragment> fragmentList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntroSliderAdapter(@NotNull FragmentActivity fa) {
        super(fa);
        Intrinsics.checkNotNullParameter(fa, "fa");
        this.fragmentList = new ArrayList<>();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NotNull
    public Fragment createFragment(int i2) {
        Fragment fragment = this.fragmentList.get(i2);
        Intrinsics.checkNotNullExpressionValue(fragment, "fragmentList[position]");
        return fragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fragmentList.size();
    }

    public final void setFragmentList(@NotNull List<? extends Fragment> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.fragmentList.clear();
        this.fragmentList.addAll(list);
        notifyDataSetChanged();
    }
}
