package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.android.FragmentCompatUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class ViewGroupDescriptor extends AbstractChainedDescriptor<ViewGroup> implements HighlightableDescriptor<ViewGroup> {
    private final Map<View, Object> mViewToElementMap = Collections.synchronizedMap(new WeakHashMap());

    private Object getElement(View view, Object obj) {
        return obj == this ? view : ((WeakReference) obj).get();
    }

    private Object getElementForView(ViewGroup viewGroup, View view) {
        Object obj = this.mViewToElementMap.get(view);
        if (obj != null) {
            Object element = getElement(view, obj);
            if (element != null && view.getParent() == viewGroup) {
                return element;
            }
            this.mViewToElementMap.remove(view);
        }
        Object findFragmentForView = FragmentCompatUtil.findFragmentForView(view);
        if (findFragmentForView == null || FragmentCompatUtil.isDialogFragment(findFragmentForView)) {
            this.mViewToElementMap.put(view, this);
            return view;
        }
        this.mViewToElementMap.put(view, new WeakReference(findFragmentForView));
        return findFragmentForView;
    }

    private boolean isChildVisible(View view) {
        return !(view instanceof DocumentHiddenView);
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    @Nullable
    public Object getElementToHighlightAtPosition(ViewGroup viewGroup, int i2, int i3, Rect rect) {
        View view;
        int childCount = viewGroup.getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                view = null;
                break;
            }
            view = viewGroup.getChildAt(childCount);
            if (isChildVisible(view) && view.getVisibility() == 0) {
                view.getHitRect(rect);
                if (rect.contains(i2, i3)) {
                    break;
                }
            }
            childCount--;
        }
        if (view != null) {
            return view;
        }
        rect.set(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
        return viewGroup;
    }

    @Override // com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
    @Nullable
    public View getViewAndBoundsForHighlighting(ViewGroup viewGroup, Rect rect) {
        return viewGroup;
    }

    /* renamed from: onGetChildren  reason: avoid collision after fix types in other method */
    protected void onGetChildren2(ViewGroup viewGroup, Accumulator<Object> accumulator) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (isChildVisible(childAt)) {
                accumulator.store(getElementForView(viewGroup, childAt));
            }
        }
    }

    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected /* bridge */ /* synthetic */ void onGetChildren(ViewGroup viewGroup, Accumulator accumulator) {
        onGetChildren2(viewGroup, (Accumulator<Object>) accumulator);
    }
}
