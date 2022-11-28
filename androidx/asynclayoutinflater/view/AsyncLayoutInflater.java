package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.util.Pools;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
/* loaded from: classes.dex */
public final class AsyncLayoutInflater {
    private static final String TAG = "AsyncLayoutInflater";

    /* renamed from: a  reason: collision with root package name */
    LayoutInflater f618a;
    private Handler.Callback mHandlerCallback = new Handler.Callback() { // from class: androidx.asynclayoutinflater.view.AsyncLayoutInflater.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            InflateRequest inflateRequest = (InflateRequest) message.obj;
            if (inflateRequest.f625d == null) {
                inflateRequest.f625d = AsyncLayoutInflater.this.f618a.inflate(inflateRequest.f624c, inflateRequest.f623b, false);
            }
            inflateRequest.f626e.onInflateFinished(inflateRequest.f625d, inflateRequest.f624c, inflateRequest.f623b);
            AsyncLayoutInflater.this.f620c.releaseRequest(inflateRequest);
            return true;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    Handler f619b = new Handler(this.mHandlerCallback);

    /* renamed from: c  reason: collision with root package name */
    InflateThread f620c = InflateThread.getInstance();

    /* loaded from: classes.dex */
    private static class BasicInflater extends LayoutInflater {
        private static final String[] sClassPrefixList = {"android.widget.", "android.webkit.", "android.app."};

        BasicInflater(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new BasicInflater(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            for (String str2 : sClassPrefixList) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InflateRequest {

        /* renamed from: a  reason: collision with root package name */
        AsyncLayoutInflater f622a;

        /* renamed from: b  reason: collision with root package name */
        ViewGroup f623b;

        /* renamed from: c  reason: collision with root package name */
        int f624c;

        /* renamed from: d  reason: collision with root package name */
        View f625d;

        /* renamed from: e  reason: collision with root package name */
        OnInflateFinishedListener f626e;

        InflateRequest() {
        }
    }

    /* loaded from: classes.dex */
    private static class InflateThread extends Thread {
        private static final InflateThread sInstance;
        private ArrayBlockingQueue<InflateRequest> mQueue = new ArrayBlockingQueue<>(10);
        private Pools.SynchronizedPool<InflateRequest> mRequestPool = new Pools.SynchronizedPool<>(10);

        static {
            InflateThread inflateThread = new InflateThread();
            sInstance = inflateThread;
            inflateThread.start();
        }

        private InflateThread() {
        }

        public static InflateThread getInstance() {
            return sInstance;
        }

        public void enqueue(InflateRequest inflateRequest) {
            try {
                this.mQueue.put(inflateRequest);
            } catch (InterruptedException e2) {
                throw new RuntimeException("Failed to enqueue async inflate request", e2);
            }
        }

        public InflateRequest obtainRequest() {
            InflateRequest acquire = this.mRequestPool.acquire();
            return acquire == null ? new InflateRequest() : acquire;
        }

        public void releaseRequest(InflateRequest inflateRequest) {
            inflateRequest.f626e = null;
            inflateRequest.f622a = null;
            inflateRequest.f623b = null;
            inflateRequest.f624c = 0;
            inflateRequest.f625d = null;
            this.mRequestPool.release(inflateRequest);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                runInner();
            }
        }

        public void runInner() {
            try {
                InflateRequest take = this.mQueue.take();
                try {
                    take.f625d = take.f622a.f618a.inflate(take.f624c, take.f623b, false);
                } catch (RuntimeException unused) {
                }
                Message.obtain(take.f622a.f619b, 0, take).sendToTarget();
            } catch (InterruptedException unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View view, @LayoutRes int i2, @Nullable ViewGroup viewGroup);
    }

    public AsyncLayoutInflater(@NonNull Context context) {
        this.f618a = new BasicInflater(context);
    }

    @UiThread
    public void inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup, @NonNull OnInflateFinishedListener onInflateFinishedListener) {
        Objects.requireNonNull(onInflateFinishedListener, "callback argument may not be null!");
        InflateRequest obtainRequest = this.f620c.obtainRequest();
        obtainRequest.f622a = this;
        obtainRequest.f624c = i2;
        obtainRequest.f623b = viewGroup;
        obtainRequest.f626e = onInflateFinishedListener;
        this.f620c.enqueue(obtainRequest);
    }
}
