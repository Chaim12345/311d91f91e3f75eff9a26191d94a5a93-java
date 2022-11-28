package com.psa.mym.mycitroenconnect.controller.fragments.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.ProfilePagerAdapter;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.ProfileFragment;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ProfileFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private ProfilePagerAdapter profilePagerAdapter;
    @Nullable
    private String selectedTab;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ProfileFragment newInstance() {
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(new Bundle());
            return profileFragment;
        }
    }

    private final ArrayList<String> getFragmentNames(boolean z) {
        ArrayList<String> arrayListOf;
        String string = getResources().getString(R.string.label_personal);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.label_personal)");
        String string2 = getResources().getString(R.string.label_my_cars);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.label_my_cars)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(string, string2);
        if (z) {
            arrayListOf.add(getResources().getString(R.string.label_child_account));
        }
        return arrayListOf;
    }

    private final ArrayList<Fragment> getFragments(boolean z) {
        ArrayList<Fragment> arrayListOf;
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new PersonalDetailsFragment(), new MyCarsFragment());
        if (z) {
            arrayListOf.add(new ChildAccountFragment());
        }
        return arrayListOf;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initTabLayout() {
        final ArrayList<String> fragmentNames;
        ArrayList<Fragment> fragments;
        ViewPager2 viewPager2;
        String str;
        boolean z;
        int i2;
        TabLayout tabLayout;
        boolean z2;
        ViewPager2 viewPager22;
        boolean isBlank;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        int i3 = 0;
        if (companion.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!companion.isPrimaryUser(requireContext2)) {
                fragmentNames = getFragmentNames(false);
                fragments = getFragments(false);
                FragmentManager childFragmentManager = getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                Lifecycle lifecycle = getViewLifecycleOwner().getLifecycle();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "viewLifecycleOwner.lifecycle");
                this.profilePagerAdapter = new ProfilePagerAdapter(childFragmentManager, lifecycle, fragments);
                int i4 = com.psa.mym.mycitroenconnect.R.id.proPager;
                viewPager2 = (ViewPager2) _$_findCachedViewById(i4);
                if (viewPager2 != null) {
                    viewPager2.setAdapter(this.profilePagerAdapter);
                }
                ((ViewPager2) _$_findCachedViewById(i4)).setUserInputEnabled(false);
                str = this.selectedTab;
                if (str != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(str);
                    if (!isBlank) {
                        z = false;
                        if (!z) {
                            String str2 = this.selectedTab;
                            if (str2 != null) {
                                if (str2.length() > 0) {
                                    z2 = true;
                                    if (z2) {
                                        String str3 = this.selectedTab;
                                        if (!Intrinsics.areEqual(str3, getResources().getString(R.string.label_personal))) {
                                            if (Intrinsics.areEqual(str3, getResources().getString(R.string.label_my_cars))) {
                                                ((ViewPager2) _$_findCachedViewById(i4)).setCurrentItem(1, true);
                                            } else if (Intrinsics.areEqual(str3, getResources().getString(R.string.label_child_account))) {
                                                viewPager22 = (ViewPager2) _$_findCachedViewById(i4);
                                                i3 = 2;
                                            }
                                            i2 = com.psa.mym.mycitroenconnect.R.id.proTabLayout;
                                            if (((TabLayout) _$_findCachedViewById(i2)) != null && ((ViewPager2) _$_findCachedViewById(i4)) != null) {
                                                new TabLayoutMediator((TabLayout) _$_findCachedViewById(i2), (ViewPager2) _$_findCachedViewById(i4), new TabLayoutMediator.TabConfigurationStrategy() { // from class: o.d
                                                    @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                                                    public final void onConfigureTab(TabLayout.Tab tab, int i5) {
                                                        ProfileFragment.m160initTabLayout$lambda2(fragmentNames, tab, i5);
                                                    }
                                                }).attach();
                                            }
                                            tabLayout = (TabLayout) _$_findCachedViewById(i2);
                                            if (tabLayout != null) {
                                                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.profile.ProfileFragment$initTabLayout$2
                                                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                                                    public void onTabReselected(@Nullable TabLayout.Tab tab) {
                                                        ProfileFragment.this.selectedTab = (String) (tab != null ? tab.getText() : null);
                                                    }

                                                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                                                    public void onTabSelected(@Nullable TabLayout.Tab tab) {
                                                        ProfileFragment.this.selectedTab = (String) (tab != null ? tab.getText() : null);
                                                    }

                                                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                                                    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
                                                        ProfileFragment.this.selectedTab = "";
                                                    }
                                                });
                                                return;
                                            }
                                            return;
                                        }
                                        viewPager22 = (ViewPager2) _$_findCachedViewById(i4);
                                        viewPager22.setCurrentItem(i3, true);
                                        i2 = com.psa.mym.mycitroenconnect.R.id.proTabLayout;
                                        if (((TabLayout) _$_findCachedViewById(i2)) != null) {
                                            new TabLayoutMediator((TabLayout) _$_findCachedViewById(i2), (ViewPager2) _$_findCachedViewById(i4), new TabLayoutMediator.TabConfigurationStrategy() { // from class: o.d
                                                @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                                                public final void onConfigureTab(TabLayout.Tab tab, int i5) {
                                                    ProfileFragment.m160initTabLayout$lambda2(fragmentNames, tab, i5);
                                                }
                                            }).attach();
                                        }
                                        tabLayout = (TabLayout) _$_findCachedViewById(i2);
                                        if (tabLayout != null) {
                                        }
                                    }
                                }
                            }
                            z2 = false;
                            if (z2) {
                            }
                        }
                        this.selectedTab = getResources().getString(R.string.label_personal);
                        i2 = com.psa.mym.mycitroenconnect.R.id.proTabLayout;
                        if (((TabLayout) _$_findCachedViewById(i2)) != null) {
                        }
                        tabLayout = (TabLayout) _$_findCachedViewById(i2);
                        if (tabLayout != null) {
                        }
                    }
                }
                z = true;
                if (!z) {
                }
                this.selectedTab = getResources().getString(R.string.label_personal);
                i2 = com.psa.mym.mycitroenconnect.R.id.proTabLayout;
                if (((TabLayout) _$_findCachedViewById(i2)) != null) {
                }
                tabLayout = (TabLayout) _$_findCachedViewById(i2);
                if (tabLayout != null) {
                }
            }
        }
        fragmentNames = getFragmentNames(true);
        fragments = getFragments(true);
        FragmentManager childFragmentManager2 = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
        Lifecycle lifecycle2 = getViewLifecycleOwner().getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "viewLifecycleOwner.lifecycle");
        this.profilePagerAdapter = new ProfilePagerAdapter(childFragmentManager2, lifecycle2, fragments);
        int i42 = com.psa.mym.mycitroenconnect.R.id.proPager;
        viewPager2 = (ViewPager2) _$_findCachedViewById(i42);
        if (viewPager2 != null) {
        }
        ((ViewPager2) _$_findCachedViewById(i42)).setUserInputEnabled(false);
        str = this.selectedTab;
        if (str != null) {
        }
        z = true;
        if (!z) {
        }
        this.selectedTab = getResources().getString(R.string.label_personal);
        i2 = com.psa.mym.mycitroenconnect.R.id.proTabLayout;
        if (((TabLayout) _$_findCachedViewById(i2)) != null) {
        }
        tabLayout = (TabLayout) _$_findCachedViewById(i2);
        if (tabLayout != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initTabLayout$lambda-2  reason: not valid java name */
    public static final void m160initTabLayout$lambda2(ArrayList fragmentNames, TabLayout.Tab tab, int i2) {
        Intrinsics.checkNotNullParameter(fragmentNames, "$fragmentNames");
        Intrinsics.checkNotNullParameter(tab, "tab");
        Object obj = fragmentNames.get(i2);
        Intrinsics.checkNotNullExpressionValue(obj, "fragmentNames[position]");
        String str = (String) obj;
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char charAt = str.charAt(0);
            sb.append((Object) (Character.isLowerCase(charAt) ? CharsKt__CharKt.titlecase(charAt) : String.valueOf(charAt)));
            String substring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            sb.append(substring);
            str = sb.toString();
        }
        tab.setText(str);
    }

    @JvmStatic
    @NotNull
    public static final ProfileFragment newInstance() {
        return Companion.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_profile, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initTabLayout();
    }
}
