package androidx.camera.core;

import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
final class SettableImageProxyBundle implements ImageProxyBundle {
    private final List<Integer> mCaptureIdList;
    private String mTagBundleKey;

    /* renamed from: a  reason: collision with root package name */
    final Object f1051a = new Object();
    @GuardedBy("mLock")

    /* renamed from: b  reason: collision with root package name */
    final SparseArray f1052b = new SparseArray();
    @GuardedBy("mLock")
    private final SparseArray<ListenableFuture<ImageProxy>> mFutureResults = new SparseArray<>();
    @GuardedBy("mLock")
    private final List<ImageProxy> mOwnedImageProxies = new ArrayList();
    @GuardedBy("mLock")
    private boolean mClosed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SettableImageProxyBundle(List list, String str) {
        this.mTagBundleKey = null;
        this.mCaptureIdList = list;
        this.mTagBundleKey = str;
        setup();
    }

    private void setup() {
        synchronized (this.f1051a) {
            for (Integer num : this.mCaptureIdList) {
                final int intValue = num.intValue();
                this.mFutureResults.put(intValue, CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver<ImageProxy>() { // from class: androidx.camera.core.SettableImageProxyBundle.1
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<ImageProxy> completer) {
                        synchronized (SettableImageProxyBundle.this.f1051a) {
                            SettableImageProxyBundle.this.f1052b.put(intValue, completer);
                        }
                        return "getImageProxy(id: " + intValue + ")";
                    }
                }));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ImageProxy imageProxy) {
        synchronized (this.f1051a) {
            if (this.mClosed) {
                return;
            }
            Integer tag = imageProxy.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
            if (tag == null) {
                throw new IllegalArgumentException("CaptureId is null.");
            }
            CallbackToFutureAdapter.Completer completer = (CallbackToFutureAdapter.Completer) this.f1052b.get(tag.intValue());
            if (completer != null) {
                this.mOwnedImageProxies.add(imageProxy);
                completer.set(imageProxy);
                return;
            }
            throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + tag);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        synchronized (this.f1051a) {
            if (this.mClosed) {
                return;
            }
            for (ImageProxy imageProxy : this.mOwnedImageProxies) {
                imageProxy.close();
            }
            this.mOwnedImageProxies.clear();
            this.mFutureResults.clear();
            this.f1052b.clear();
            this.mClosed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        synchronized (this.f1051a) {
            if (this.mClosed) {
                return;
            }
            for (ImageProxy imageProxy : this.mOwnedImageProxies) {
                imageProxy.close();
            }
            this.mOwnedImageProxies.clear();
            this.mFutureResults.clear();
            this.f1052b.clear();
            setup();
        }
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public List<Integer> getCaptureIds() {
        return Collections.unmodifiableList(this.mCaptureIdList);
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public ListenableFuture<ImageProxy> getImageProxy(int i2) {
        ListenableFuture<ImageProxy> listenableFuture;
        synchronized (this.f1051a) {
            if (this.mClosed) {
                throw new IllegalStateException("ImageProxyBundle already closed.");
            }
            listenableFuture = this.mFutureResults.get(i2);
            if (listenableFuture == null) {
                throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + i2);
            }
        }
        return listenableFuture;
    }
}
