package com.psa.mym.mycitroenconnect.controller.adapters.view_pager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.DrivingBehaviourFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripSummaryFragment;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class MyTripsPagerAdapter extends FragmentStateAdapter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyTripsPagerAdapter(@NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NotNull
    public Fragment createFragment(int i2) {
        return i2 == 1 ? new DrivingBehaviourFragment() : new TripSummaryFragment();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 2;
    }
}
