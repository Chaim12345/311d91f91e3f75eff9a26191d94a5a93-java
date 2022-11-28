package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;
/* loaded from: classes4.dex */
abstract class NamedLoggerBase implements Logger, Serializable {
    private static final long serialVersionUID = 7535258609338176893L;

    /* renamed from: a  reason: collision with root package name */
    protected String f15241a;

    @Override // org.slf4j.Logger
    public String getName() {
        return this.f15241a;
    }
}
