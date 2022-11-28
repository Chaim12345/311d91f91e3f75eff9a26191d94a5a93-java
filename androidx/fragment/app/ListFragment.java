package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ListFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    ListAdapter f3125a;

    /* renamed from: b  reason: collision with root package name */
    ListView f3126b;

    /* renamed from: c  reason: collision with root package name */
    View f3127c;

    /* renamed from: d  reason: collision with root package name */
    TextView f3128d;

    /* renamed from: e  reason: collision with root package name */
    View f3129e;

    /* renamed from: f  reason: collision with root package name */
    View f3130f;

    /* renamed from: g  reason: collision with root package name */
    CharSequence f3131g;

    /* renamed from: h  reason: collision with root package name */
    boolean f3132h;
    private final Handler mHandler = new Handler();
    private final Runnable mRequestFocus = new Runnable() { // from class: androidx.fragment.app.ListFragment.1
        @Override // java.lang.Runnable
        public void run() {
            ListView listView = ListFragment.this.f3126b;
            listView.focusableViewAvailable(listView);
        }
    };
    private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() { // from class: androidx.fragment.app.ListFragment.2
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            ListFragment.this.onListItemClick((ListView) adapterView, view, i2, j2);
        }
    };

    private void ensureList() {
        if (this.f3126b != null) {
            return;
        }
        View view = getView();
        if (view == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (view instanceof ListView) {
            this.f3126b = (ListView) view;
        } else {
            TextView textView = (TextView) view.findViewById(16711681);
            this.f3128d = textView;
            if (textView == null) {
                this.f3127c = view.findViewById(16908292);
            } else {
                textView.setVisibility(8);
            }
            this.f3129e = view.findViewById(16711682);
            this.f3130f = view.findViewById(16711683);
            View findViewById = view.findViewById(16908298);
            if (!(findViewById instanceof ListView)) {
                if (findViewById != null) {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            }
            ListView listView = (ListView) findViewById;
            this.f3126b = listView;
            View view2 = this.f3127c;
            if (view2 == null) {
                CharSequence charSequence = this.f3131g;
                if (charSequence != null) {
                    this.f3128d.setText(charSequence);
                    listView = this.f3126b;
                    view2 = this.f3128d;
                }
            }
            listView.setEmptyView(view2);
        }
        this.f3132h = true;
        this.f3126b.setOnItemClickListener(this.mOnClickListener);
        ListAdapter listAdapter = this.f3125a;
        if (listAdapter != null) {
            this.f3125a = null;
            setListAdapter(listAdapter);
        } else if (this.f3129e != null) {
            setListShown(false, false);
        }
        this.mHandler.post(this.mRequestFocus);
    }

    private void setListShown(boolean z, boolean z2) {
        ensureList();
        View view = this.f3129e;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.f3132h == z) {
            return;
        }
        this.f3132h = z;
        if (z) {
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
                this.f3130f.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
            } else {
                view.clearAnimation();
                this.f3130f.clearAnimation();
            }
            this.f3129e.setVisibility(8);
            this.f3130f.setVisibility(0);
            return;
        }
        if (z2) {
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
            this.f3130f.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        } else {
            view.clearAnimation();
            this.f3130f.clearAnimation();
        }
        this.f3129e.setVisibility(0);
        this.f3130f.setVisibility(8);
    }

    @Nullable
    public ListAdapter getListAdapter() {
        return this.f3125a;
    }

    @NonNull
    public ListView getListView() {
        ensureList();
        return this.f3126b;
    }

    public long getSelectedItemId() {
        ensureList();
        return this.f3126b.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        ensureList();
        return this.f3126b.getSelectedItemPosition();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Context requireContext = requireContext();
        FrameLayout frameLayout = new FrameLayout(requireContext);
        LinearLayout linearLayout = new LinearLayout(requireContext);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(requireContext, null, 16842874), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(requireContext);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(requireContext);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(requireContext);
        listView.setId(16908298);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.f3126b = null;
        this.f3132h = false;
        this.f3130f = null;
        this.f3129e = null;
        this.f3127c = null;
        this.f3128d = null;
        super.onDestroyView();
    }

    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int i2, long j2) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ensureList();
    }

    @NonNull
    public final ListAdapter requireListAdapter() {
        ListAdapter listAdapter = getListAdapter();
        if (listAdapter != null) {
            return listAdapter;
        }
        throw new IllegalStateException("ListFragment " + this + " does not have a ListAdapter.");
    }

    public void setEmptyText(@Nullable CharSequence charSequence) {
        ensureList();
        TextView textView = this.f3128d;
        if (textView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        textView.setText(charSequence);
        if (this.f3131g == null) {
            this.f3126b.setEmptyView(this.f3128d);
        }
        this.f3131g = charSequence;
    }

    public void setListAdapter(@Nullable ListAdapter listAdapter) {
        boolean z = this.f3125a != null;
        this.f3125a = listAdapter;
        ListView listView = this.f3126b;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (this.f3132h || z) {
                return;
            }
            setListShown(true, requireView().getWindowToken() != null);
        }
    }

    public void setListShown(boolean z) {
        setListShown(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        setListShown(z, false);
    }

    public void setSelection(int i2) {
        ensureList();
        this.f3126b.setSelection(i2);
    }
}
