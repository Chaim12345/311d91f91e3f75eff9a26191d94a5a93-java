package org.greenrobot.eventbus.util;
/* loaded from: classes4.dex */
public class ThrowableFailureEvent implements HasExecutionScope {

    /* renamed from: a  reason: collision with root package name */
    protected final Throwable f15214a;

    /* renamed from: b  reason: collision with root package name */
    protected final boolean f15215b;
    private Object executionContext;

    public ThrowableFailureEvent(Throwable th) {
        this.f15214a = th;
        this.f15215b = false;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.f15214a = th;
        this.f15215b = z;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    public Object getExecutionScope() {
        return this.executionContext;
    }

    public Throwable getThrowable() {
        return this.f15214a;
    }

    public boolean isSuppressErrorUi() {
        return this.f15215b;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    public void setExecutionScope(Object obj) {
        this.executionContext = obj;
    }
}
