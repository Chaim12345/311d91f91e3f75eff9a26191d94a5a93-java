package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.R;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "FragmentManager";

    /* renamed from: a  reason: collision with root package name */
    final FragmentManager f2977a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.f2977a = fragmentManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0161  */
    @Override // android.view.LayoutInflater.Factory2
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        final FragmentStateManager m2;
        StringBuilder sb;
        String str2;
        View view2;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.f2977a);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(R.styleable.Fragment_android_name);
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.Fragment_android_id, -1);
        String string = obtainStyledAttributes.getString(R.styleable.Fragment_android_tag);
        obtainStyledAttributes.recycle();
        if (attributeValue == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment findFragmentById = resourceId != -1 ? this.f2977a.findFragmentById(resourceId) : null;
        if (findFragmentById == null && string != null) {
            findFragmentById = this.f2977a.findFragmentByTag(string);
        }
        if (findFragmentById == null && id != -1) {
            findFragmentById = this.f2977a.findFragmentById(id);
        }
        if (findFragmentById == null) {
            findFragmentById = this.f2977a.getFragmentFactory().instantiate(context.getClassLoader(), attributeValue);
            findFragmentById.mFromLayout = true;
            findFragmentById.mFragmentId = resourceId != 0 ? resourceId : id;
            findFragmentById.mContainerId = id;
            findFragmentById.mTag = string;
            findFragmentById.mInLayout = true;
            FragmentManager fragmentManager = this.f2977a;
            findFragmentById.mFragmentManager = fragmentManager;
            findFragmentById.mHost = fragmentManager.S();
            findFragmentById.onInflate(this.f2977a.S().b(), attributeSet, findFragmentById.mSavedFragmentState);
            m2 = this.f2977a.f(findFragmentById);
            if (FragmentManager.c0(2)) {
                sb = new StringBuilder();
                sb.append("Fragment ");
                sb.append(findFragmentById);
                str2 = " has been inflated via the <fragment> tag: id=0x";
                sb.append(str2);
                sb.append(Integer.toHexString(resourceId));
            }
            findFragmentById.mContainer = (ViewGroup) view;
            m2.l();
            m2.j();
            view2 = findFragmentById.mView;
            if (view2 != null) {
                throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
            }
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (findFragmentById.mView.getTag() == null) {
                findFragmentById.mView.setTag(string);
            }
            findFragmentById.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.fragment.app.FragmentLayoutInflaterFactory.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view3) {
                    Fragment k2 = m2.k();
                    m2.l();
                    SpecialEffectsController.j((ViewGroup) k2.mView.getParent(), FragmentLayoutInflaterFactory.this.f2977a).g();
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view3) {
                }
            });
            return findFragmentById.mView;
        } else if (findFragmentById.mInLayout) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
        } else {
            findFragmentById.mInLayout = true;
            FragmentManager fragmentManager2 = this.f2977a;
            findFragmentById.mFragmentManager = fragmentManager2;
            findFragmentById.mHost = fragmentManager2.S();
            findFragmentById.onInflate(this.f2977a.S().b(), attributeSet, findFragmentById.mSavedFragmentState);
            m2 = this.f2977a.m(findFragmentById);
            if (FragmentManager.c0(2)) {
                sb = new StringBuilder();
                sb.append("Retained Fragment ");
                sb.append(findFragmentById);
                str2 = " has been re-attached via the <fragment> tag: id=0x";
                sb.append(str2);
                sb.append(Integer.toHexString(resourceId));
            }
            findFragmentById.mContainer = (ViewGroup) view;
            m2.l();
            m2.j();
            view2 = findFragmentById.mView;
            if (view2 != null) {
            }
        }
    }

    @Override // android.view.LayoutInflater.Factory
    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
