package io.grpc;

import io.grpc.Context;
import java.util.logging.Level;
import java.util.logging.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ThreadLocalContextStorage extends Context.Storage {
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());

    /* renamed from: a  reason: collision with root package name */
    static final ThreadLocal f10950a = new ThreadLocal();

    @Override // io.grpc.Context.Storage
    public Context current() {
        Context context = (Context) f10950a.get();
        return context == null ? Context.ROOT : context;
    }

    @Override // io.grpc.Context.Storage
    public void detach(Context context, Context context2) {
        ThreadLocal threadLocal;
        if (current() != context) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (context2 != Context.ROOT) {
            threadLocal = f10950a;
        } else {
            threadLocal = f10950a;
            context2 = null;
        }
        threadLocal.set(context2);
    }

    @Override // io.grpc.Context.Storage
    public Context doAttach(Context context) {
        Context current = current();
        f10950a.set(context);
        return current;
    }
}
