package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "", AppConstants.ARG_POSITION, "Landroidx/fragment/app/Fragment;", "getItem", "getCount", "", "getPageTitle", "", "", "titles", "[Ljava/lang/String;", "Landroid/content/Context;", "context", "Landroidx/fragment/app/FragmentManager;", "fm", "<init>", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionPagerAdapter extends FragmentStatePagerAdapter {
    private final String[] titles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionPagerAdapter(@NotNull Context context, @NotNull FragmentManager fm) {
        super(fm, 1);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fm, "fm");
        this.titles = new String[]{context.getString(R.string.chucker_overview), context.getString(R.string.chucker_request), context.getString(R.string.chucker_response)};
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.titles.length;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NotNull
    public Fragment getItem(int i2) {
        TransactionPayloadFragment.Companion companion;
        PayloadType payloadType;
        if (i2 != 0) {
            if (i2 == 1) {
                companion = TransactionPayloadFragment.Companion;
                payloadType = PayloadType.REQUEST;
            } else if (i2 != 2) {
                throw new IllegalArgumentException("no item");
            } else {
                companion = TransactionPayloadFragment.Companion;
                payloadType = PayloadType.RESPONSE;
            }
            return companion.newInstance(payloadType);
        }
        return new TransactionOverviewFragment();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i2) {
        return this.titles[i2];
    }
}
