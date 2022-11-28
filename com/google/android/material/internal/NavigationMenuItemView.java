package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView.ItemView {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private FrameLayout actionArea;

    /* renamed from: c  reason: collision with root package name */
    boolean f7359c;
    private Drawable emptyDrawable;
    private boolean hasIconTintList;
    private int iconSize;
    private ColorStateList iconTintList;
    private MenuItemImpl itemData;
    private boolean needsEmptyIcon;
    private final CheckedTextView textView;

    public NavigationMenuItemView(@NonNull Context context) {
        this(context, null);
    }

    public NavigationMenuItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        AccessibilityDelegateCompat accessibilityDelegateCompat = new AccessibilityDelegateCompat() { // from class: com.google.android.material.internal.NavigationMenuItemView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(NavigationMenuItemView.this.f7359c);
            }
        };
        this.accessibilityDelegate = accessibilityDelegateCompat;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.textView = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView, accessibilityDelegateCompat);
    }

    private void adjustAppearance() {
        LinearLayoutCompat.LayoutParams layoutParams;
        int i2;
        if (shouldExpandActionArea()) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout = this.actionArea;
            if (frameLayout == null) {
                return;
            }
            layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
            i2 = -1;
        } else {
            this.textView.setVisibility(0);
            FrameLayout frameLayout2 = this.actionArea;
            if (frameLayout2 == null) {
                return;
            }
            layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            i2 = -2;
        }
        ((LinearLayout.LayoutParams) layoutParams).width = i2;
        this.actionArea.setLayoutParams(layoutParams);
    }

    @Nullable
    private StateListDrawable createDefaultBackground() {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorControlHighlight, typedValue, true)) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
            stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            return stateListDrawable;
        }
        return null;
    }

    private void setActionView(@Nullable View view) {
        if (view != null) {
            if (this.actionArea == null) {
                this.actionArea = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(view);
        }
    }

    private boolean shouldExpandActionArea() {
        return this.itemData.getTitle() == null && this.itemData.getIcon() == null && this.itemData.getActionView() != null;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(@NonNull MenuItemImpl menuItemImpl, int i2) {
        this.itemData = menuItemImpl;
        if (menuItemImpl.getItemId() > 0) {
            setId(menuItemImpl.getItemId());
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            ViewCompat.setBackground(this, createDefaultBackground());
        }
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.getTitle());
        setIcon(menuItemImpl.getIcon());
        setActionView(menuItemImpl.getActionView());
        setContentDescription(menuItemImpl.getContentDescription());
        TooltipCompat.setTooltipText(this, menuItemImpl.getTooltipText());
        adjustAppearance();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            ViewGroup.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void recycle() {
        FrameLayout frameLayout = this.actionArea;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.textView.setCompoundDrawables(null, null, null, null);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.f7359c != z) {
            this.f7359c = z;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setChecked(boolean z) {
        refreshDrawableState();
        this.textView.setChecked(z);
    }

    public void setHorizontalPadding(int i2) {
        setPadding(i2, 0, i2, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            if (this.hasIconTintList) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = DrawableCompat.wrap(drawable).mutate();
                DrawableCompat.setTintList(drawable, this.iconTintList);
            }
            int i2 = this.iconSize;
            drawable.setBounds(0, 0, i2, i2);
        } else if (this.needsEmptyIcon) {
            if (this.emptyDrawable == null) {
                Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.navigation_empty_icon, getContext().getTheme());
                this.emptyDrawable = drawable2;
                if (drawable2 != null) {
                    int i3 = this.iconSize;
                    drawable2.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.emptyDrawable;
        }
        TextViewCompat.setCompoundDrawablesRelative(this.textView, drawable, null, null, null);
    }

    public void setIconPadding(int i2) {
        this.textView.setCompoundDrawablePadding(i2);
    }

    public void setIconSize(@Dimension int i2) {
        this.iconSize = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        this.hasIconTintList = colorStateList != null;
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null) {
            setIcon(menuItemImpl.getIcon());
        }
    }

    public void setMaxLines(int i2) {
        this.textView.setMaxLines(i2);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.needsEmptyIcon = z;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setShortcut(boolean z, char c2) {
    }

    public void setTextAppearance(int i2) {
        TextViewCompat.setTextAppearance(this.textView, i2);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.textView.setTextColor(colorStateList);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setTitle(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }
}
