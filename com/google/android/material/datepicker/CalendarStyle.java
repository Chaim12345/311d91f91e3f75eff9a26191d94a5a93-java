package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class CalendarStyle {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    final CalendarItemStyle f7223a;
    @NonNull

    /* renamed from: b  reason: collision with root package name */
    final CalendarItemStyle f7224b;
    @NonNull

    /* renamed from: c  reason: collision with root package name */
    final CalendarItemStyle f7225c;
    @NonNull

    /* renamed from: d  reason: collision with root package name */
    final CalendarItemStyle f7226d;
    @NonNull

    /* renamed from: e  reason: collision with root package name */
    final CalendarItemStyle f7227e;
    @NonNull

    /* renamed from: f  reason: collision with root package name */
    final CalendarItemStyle f7228f;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    final CalendarItemStyle f7229g;
    @NonNull

    /* renamed from: h  reason: collision with root package name */
    final Paint f7230h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CalendarStyle(@NonNull Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), R.styleable.MaterialCalendar);
        this.f7223a = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayStyle, 0));
        this.f7229g = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.f7224b = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_daySelectedStyle, 0));
        this.f7225c = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.MaterialCalendar_rangeFillColor);
        this.f7226d = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearStyle, 0));
        this.f7227e = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.f7228f = CalendarItemStyle.a(context, obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f7230h = paint;
        paint.setColor(colorStateList.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
