package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;
/* loaded from: classes4.dex */
public class SubstituteLoggerFactory implements ILoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    boolean f15242a = false;

    /* renamed from: b  reason: collision with root package name */
    final Map f15243b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    final LinkedBlockingQueue f15244c = new LinkedBlockingQueue();

    public void clear() {
        this.f15243b.clear();
        this.f15244c.clear();
    }

    public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
        return this.f15244c;
    }

    @Override // org.slf4j.ILoggerFactory
    public synchronized Logger getLogger(String str) {
        SubstituteLogger substituteLogger;
        substituteLogger = (SubstituteLogger) this.f15243b.get(str);
        if (substituteLogger == null) {
            substituteLogger = new SubstituteLogger(str, this.f15244c, this.f15242a);
            this.f15243b.put(str, substituteLogger);
        }
        return substituteLogger;
    }

    public List<String> getLoggerNames() {
        return new ArrayList(this.f15243b.keySet());
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList(this.f15243b.values());
    }

    public void postInitialization() {
        this.f15242a = true;
    }
}
