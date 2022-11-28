package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;
/* loaded from: classes4.dex */
public class SubstituteLoggingEvent implements LoggingEvent {

    /* renamed from: a  reason: collision with root package name */
    Level f15232a;

    /* renamed from: b  reason: collision with root package name */
    Marker f15233b;

    /* renamed from: c  reason: collision with root package name */
    String f15234c;

    /* renamed from: d  reason: collision with root package name */
    SubstituteLogger f15235d;

    /* renamed from: e  reason: collision with root package name */
    String f15236e;

    /* renamed from: f  reason: collision with root package name */
    String f15237f;

    /* renamed from: g  reason: collision with root package name */
    Object[] f15238g;

    /* renamed from: h  reason: collision with root package name */
    long f15239h;

    /* renamed from: i  reason: collision with root package name */
    Throwable f15240i;

    @Override // org.slf4j.event.LoggingEvent
    public Object[] getArgumentArray() {
        return this.f15238g;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Level getLevel() {
        return this.f15232a;
    }

    public SubstituteLogger getLogger() {
        return this.f15235d;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getLoggerName() {
        return this.f15234c;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Marker getMarker() {
        return this.f15233b;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getMessage() {
        return this.f15237f;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getThreadName() {
        return this.f15236e;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Throwable getThrowable() {
        return this.f15240i;
    }

    @Override // org.slf4j.event.LoggingEvent
    public long getTimeStamp() {
        return this.f15239h;
    }

    public void setArgumentArray(Object[] objArr) {
        this.f15238g = objArr;
    }

    public void setLevel(Level level) {
        this.f15232a = level;
    }

    public void setLogger(SubstituteLogger substituteLogger) {
        this.f15235d = substituteLogger;
    }

    public void setLoggerName(String str) {
        this.f15234c = str;
    }

    public void setMarker(Marker marker) {
        this.f15233b = marker;
    }

    public void setMessage(String str) {
        this.f15237f = str;
    }

    public void setThreadName(String str) {
        this.f15236e = str;
    }

    public void setThrowable(Throwable th) {
        this.f15240i = th;
    }

    public void setTimeStamp(long j2) {
        this.f15239h = j2;
    }
}
