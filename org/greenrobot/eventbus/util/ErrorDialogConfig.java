package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes4.dex */
public class ErrorDialogConfig {

    /* renamed from: a  reason: collision with root package name */
    final Resources f15200a;

    /* renamed from: b  reason: collision with root package name */
    final int f15201b;

    /* renamed from: c  reason: collision with root package name */
    final int f15202c;

    /* renamed from: e  reason: collision with root package name */
    EventBus f15204e;

    /* renamed from: g  reason: collision with root package name */
    String f15206g;

    /* renamed from: h  reason: collision with root package name */
    int f15207h;

    /* renamed from: i  reason: collision with root package name */
    Class f15208i;

    /* renamed from: f  reason: collision with root package name */
    boolean f15205f = true;

    /* renamed from: d  reason: collision with root package name */
    final ExceptionToResourceMapping f15203d = new ExceptionToResourceMapping();

    public ErrorDialogConfig(Resources resources, int i2, int i3) {
        this.f15200a = resources;
        this.f15201b = i2;
        this.f15202c = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBus a() {
        EventBus eventBus = this.f15204e;
        return eventBus != null ? eventBus : EventBus.getDefault();
    }

    public ErrorDialogConfig addMapping(Class<? extends Throwable> cls, int i2) {
        this.f15203d.addMapping(cls, i2);
        return this;
    }

    public void disableExceptionLogging() {
        this.f15205f = false;
    }

    public int getMessageIdForThrowable(Throwable th) {
        Integer mapThrowable = this.f15203d.mapThrowable(th);
        if (mapThrowable != null) {
            return mapThrowable.intValue();
        }
        String str = EventBus.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("No specific message ressource ID found for ");
        sb.append(th);
        return this.f15202c;
    }

    public void setDefaultDialogIconId(int i2) {
        this.f15207h = i2;
    }

    public void setDefaultEventTypeOnDialogClosed(Class<?> cls) {
        this.f15208i = cls;
    }

    public void setEventBus(EventBus eventBus) {
        this.f15204e = eventBus;
    }

    public void setTagForLoggingExceptions(String str) {
        this.f15206g = str;
    }
}
