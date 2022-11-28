package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompatApi21;
import android.support.v4.media.MediaBrowserCompatApi23;
import android.support.v4.media.MediaBrowserCompatApi26;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserCompatUtils;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";

    /* renamed from: a  reason: collision with root package name */
    static final boolean f8a = Log.isLoggable("MediaBrowserCompat", 3);
    private final MediaBrowserImpl mImpl;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.mCallbackImplRef = new WeakReference<>(mediaBrowserServiceCallbackImpl);
        }

        void a(Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.mCallbacksMessengerRef;
            if (weakReference == null || weakReference.get() == null || this.mCallbackImplRef.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.ensureClassLoader(data);
            MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl = this.mCallbackImplRef.get();
            Messenger messenger = this.mCallbacksMessengerRef.get();
            try {
                int i2 = message.what;
                if (i2 == 1) {
                    Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(bundle);
                    mediaBrowserServiceCallbackImpl.onServiceConnected(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), bundle);
                } else if (i2 == 2) {
                    mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger);
                } else if (i2 != 3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled message: ");
                    sb.append(message);
                    sb.append("\n  Client version: ");
                    sb.append(1);
                    sb.append("\n  Service version: ");
                    sb.append(message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    mediaBrowserServiceCallbackImpl.onLoadChildren(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    mediaBrowserServiceCallbackImpl.onConnectionFailed(messenger);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ConnectionCallback {

        /* renamed from: a  reason: collision with root package name */
        final Object f9a;

        /* renamed from: b  reason: collision with root package name */
        ConnectionCallbackInternal f10b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        /* loaded from: classes.dex */
        private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
            StubApi21() {
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi21.ConnectionCallback
            public void onConnected() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.f10b;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi21.ConnectionCallback
            public void onConnectionFailed() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.f10b;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnectionFailed();
                }
                ConnectionCallback.this.onConnectionFailed();
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi21.ConnectionCallback
            public void onConnectionSuspended() {
                ConnectionCallbackInternal connectionCallbackInternal = ConnectionCallback.this.f10b;
                if (connectionCallbackInternal != null) {
                    connectionCallbackInternal.onConnectionSuspended();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }
        }

        public ConnectionCallback() {
            this.f9a = Build.VERSION.SDK_INT >= 21 ? MediaBrowserCompatApi21.createConnectionCallback(new StubApi21()) : null;
        }

        void a(ConnectionCallbackInternal connectionCallbackInternal) {
            this.f10b = connectionCallbackInternal;
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    /* loaded from: classes.dex */
    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.mAction = str;
            this.mExtras = bundle;
            this.mCallback = customActionCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i2, Bundle bundle) {
            if (this.mCallback == null) {
                return;
            }
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i2 == -1) {
                this.mCallback.onError(this.mAction, this.mExtras, bundle);
            } else if (i2 == 0) {
                this.mCallback.onResult(this.mAction, this.mExtras, bundle);
            } else if (i2 == 1) {
                this.mCallback.onProgressUpdate(this.mAction, this.mExtras, bundle);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown result code: ");
                sb.append(i2);
                sb.append(" (extras=");
                sb.append(this.mExtras);
                sb.append(", resultData=");
                sb.append(bundle);
                sb.append(")");
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback {

        /* renamed from: a  reason: collision with root package name */
        final Object f12a;

        /* loaded from: classes.dex */
        private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
            StubApi23() {
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi23.ItemCallback
            public void onError(@NonNull String str) {
                ItemCallback.this.onError(str);
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi23.ItemCallback
            public void onItemLoaded(Parcel parcel) {
                MediaItem createFromParcel;
                ItemCallback itemCallback;
                if (parcel == null) {
                    itemCallback = ItemCallback.this;
                    createFromParcel = null;
                } else {
                    parcel.setDataPosition(0);
                    createFromParcel = MediaItem.CREATOR.createFromParcel(parcel);
                    parcel.recycle();
                    itemCallback = ItemCallback.this;
                }
                itemCallback.onItemLoaded(createFromParcel);
            }
        }

        public ItemCallback() {
            this.f12a = Build.VERSION.SDK_INT >= 23 ? MediaBrowserCompatApi23.createItemCallback(new StubApi23()) : null;
        }

        public void onError(@NonNull String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.mMediaId = str;
            this.mCallback = itemCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i2, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i2 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                this.mCallback.onError(this.mMediaId);
                return;
            }
            Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
            if (parcelable == null || (parcelable instanceof MediaItem)) {
                this.mCallback.onItemLoaded((MediaItem) parcelable);
            } else {
                this.mCallback.onError(this.mMediaId);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface MediaBrowserImpl {
        void connect();

        void disconnect();

        @Nullable
        Bundle getExtras();

        void getItem(@NonNull String str, @NonNull ItemCallback itemCallback);

        @Nullable
        Bundle getNotifyChildrenChangedOptions();

        @NonNull
        String getRoot();

        ComponentName getServiceComponent();

        @NonNull
        MediaSessionCompat.Token getSessionToken();

        boolean isConnected();

        void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback);

        void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback);

        void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback);
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    static class MediaBrowserImplApi21 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {

        /* renamed from: a  reason: collision with root package name */
        final Context f14a;

        /* renamed from: b  reason: collision with root package name */
        protected final Object f15b;

        /* renamed from: c  reason: collision with root package name */
        protected final Bundle f16c;

        /* renamed from: e  reason: collision with root package name */
        protected int f18e;

        /* renamed from: f  reason: collision with root package name */
        protected ServiceBinderWrapper f19f;

        /* renamed from: g  reason: collision with root package name */
        protected Messenger f20g;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;

        /* renamed from: d  reason: collision with root package name */
        protected final CallbackHandler f17d = new CallbackHandler(this);
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap<>();

        MediaBrowserImplApi21(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.f14a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.f16c = bundle2;
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            connectionCallback.a(this);
            this.f15b = MediaBrowserCompatApi21.createBrowser(context, componentName, connectionCallback.f9a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void connect() {
            MediaBrowserCompatApi21.connect(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void disconnect() {
            Messenger messenger;
            ServiceBinderWrapper serviceBinderWrapper = this.f19f;
            if (serviceBinderWrapper != null && (messenger = this.f20g) != null) {
                try {
                    serviceBinderWrapper.i(messenger);
                } catch (RemoteException unused) {
                }
            }
            MediaBrowserCompatApi21.disconnect(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @Nullable
        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void getItem(@NonNull final String str, @NonNull final ItemCallback itemCallback) {
            CallbackHandler callbackHandler;
            Runnable runnable;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            }
            if (!MediaBrowserCompatApi21.isConnected(this.f15b)) {
                callbackHandler = this.f17d;
                runnable = new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                };
            } else if (this.f19f != null) {
                try {
                    this.f19f.d(str, new ItemReceiver(str, itemCallback, this.f17d), this.f20g);
                    return;
                } catch (RemoteException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Remote error getting media item: ");
                    sb.append(str);
                    this.f17d.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.3
                        @Override // java.lang.Runnable
                        public void run() {
                            itemCallback.onError(str);
                        }
                    });
                    return;
                }
            } else {
                callbackHandler = this.f17d;
                runnable = new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.2
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                };
            }
            callbackHandler.post(runnable);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @NonNull
        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (this.mMediaSessionToken == null) {
                this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.f15b));
            }
            return this.mMediaSessionToken;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.f15b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnected() {
            Bundle extras = MediaBrowserCompatApi21.getExtras(this.f15b);
            if (extras == null) {
                return;
            }
            this.f18e = extras.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
            IBinder binder = BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
            if (binder != null) {
                this.f19f = new ServiceBinderWrapper(binder, this.f16c);
                Messenger messenger = new Messenger(this.f17d);
                this.f20g = messenger;
                this.f17d.a(messenger);
                try {
                    this.f19f.e(this.f14a, this.f20g);
                } catch (RemoteException unused) {
                }
            }
            IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
            if (asInterface != null) {
                this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.f15b), asInterface);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionFailed() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onConnectionFailed(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
        public void onConnectionSuspended() {
            this.f19f = null;
            this.f20g = null;
            this.mMediaSessionToken = null;
            this.f17d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.f20g != messenger) {
                return;
            }
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                if (MediaBrowserCompat.f8a) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onLoadChildren for id that isn't subscribed id=");
                    sb.append(str);
                    return;
                }
                return;
            }
            SubscriptionCallback callback = subscription.getCallback(bundle);
            if (callback != null) {
                if (bundle == null) {
                    if (list == null) {
                        callback.onError(str);
                        return;
                    } else {
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list);
                    }
                } else if (list == null) {
                    callback.onError(str, bundle);
                    return;
                } else {
                    this.mNotifyChildrenChangedOptions = bundle2;
                    callback.onChildrenLoaded(str, list, bundle);
                }
                this.mNotifyChildrenChangedOptions = null;
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void search(@NonNull final String str, final Bundle bundle, @NonNull final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            }
            if (this.f19f == null) {
                this.f17d.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.4
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
                return;
            }
            try {
                this.f19f.g(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.f17d), this.f20g);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error searching items with query: ");
                sb.append(str);
                this.f17d.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.5
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void sendCustomAction(@NonNull final String str, final Bundle bundle, @Nullable final CustomActionCallback customActionCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
            }
            if (this.f19f == null && customActionCallback != null) {
                this.f17d.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.6
                    @Override // java.lang.Runnable
                    public void run() {
                        customActionCallback.onError(str, bundle, null);
                    }
                });
            }
            try {
                this.f19f.h(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.f17d), this.f20g);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error sending a custom action: action=");
                sb.append(str);
                sb.append(", extras=");
                sb.append(bundle);
                if (customActionCallback != null) {
                    this.f17d.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21.7
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(str, subscription);
            }
            subscriptionCallback.a(subscription);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            ServiceBinderWrapper serviceBinderWrapper = this.f19f;
            if (serviceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.f15b, str, subscriptionCallback.f66a);
                return;
            }
            try {
                serviceBinderWrapper.a(str, subscriptionCallback.f67b, bundle2, this.f20g);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error subscribing media item: ");
                sb.append(str);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
            if (r1.size() == 0) goto L8;
         */
        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                return;
            }
            ServiceBinderWrapper serviceBinderWrapper = this.f19f;
            if (serviceBinderWrapper == null) {
                if (subscriptionCallback != null) {
                    List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                    List<Bundle> optionsList = subscription.getOptionsList();
                    for (int size = callbacks.size() - 1; size >= 0; size--) {
                        if (callbacks.get(size) == subscriptionCallback) {
                            callbacks.remove(size);
                            optionsList.remove(size);
                        }
                    }
                }
                MediaBrowserCompatApi21.unsubscribe(this.f15b, str);
            } else {
                try {
                    if (subscriptionCallback == null) {
                        serviceBinderWrapper.f(str, null, this.f20g);
                    } else {
                        List<SubscriptionCallback> callbacks2 = subscription.getCallbacks();
                        List<Bundle> optionsList2 = subscription.getOptionsList();
                        for (int size2 = callbacks2.size() - 1; size2 >= 0; size2--) {
                            if (callbacks2.get(size2) == subscriptionCallback) {
                                this.f19f.f(str, subscriptionCallback.f67b, this.f20g);
                                callbacks2.remove(size2);
                                optionsList2.remove(size2);
                            }
                        }
                    }
                } catch (RemoteException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("removeSubscription failed with RemoteException parentId=");
                    sb.append(str);
                }
            }
            if (subscription.isEmpty() || subscriptionCallback == null) {
                this.mSubscriptions.remove(str);
            }
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        MediaBrowserImplApi23(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21, android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (this.f19f == null) {
                MediaBrowserCompatApi23.getItem(this.f15b, str, itemCallback.f12a);
            } else {
                super.getItem(str, itemCallback);
            }
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        MediaBrowserImplApi26(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21, android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            if (this.f19f != null && this.f18e >= 2) {
                super.subscribe(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                MediaBrowserCompatApi21.subscribe(this.f15b, str, subscriptionCallback.f66a);
            } else {
                MediaBrowserCompatApi26.subscribe(this.f15b, str, bundle, subscriptionCallback.f66a);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImplApi21, android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            if (this.f19f != null && this.f18e >= 2) {
                super.unsubscribe(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                MediaBrowserCompatApi21.unsubscribe(this.f15b, str);
            } else {
                MediaBrowserCompatApi26.unsubscribe(this.f15b, str, subscriptionCallback.f66a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {

        /* renamed from: a  reason: collision with root package name */
        final Context f39a;

        /* renamed from: b  reason: collision with root package name */
        final ComponentName f40b;

        /* renamed from: c  reason: collision with root package name */
        final ConnectionCallback f41c;

        /* renamed from: d  reason: collision with root package name */
        final Bundle f42d;

        /* renamed from: g  reason: collision with root package name */
        MediaServiceConnection f45g;

        /* renamed from: h  reason: collision with root package name */
        ServiceBinderWrapper f46h;

        /* renamed from: i  reason: collision with root package name */
        Messenger f47i;
        private Bundle mExtras;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        private String mRootId;

        /* renamed from: e  reason: collision with root package name */
        final CallbackHandler f43e = new CallbackHandler(this);
        private final ArrayMap<String, Subscription> mSubscriptions = new ArrayMap<>();

        /* renamed from: f  reason: collision with root package name */
        int f44f = 1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class MediaServiceConnection implements ServiceConnection {
            MediaServiceConnection() {
            }

            private void postOrRun(Runnable runnable) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.f43e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    MediaBrowserImplBase.this.f43e.post(runnable);
                }
            }

            boolean a(String str) {
                int i2;
                MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                if (mediaBrowserImplBase.f45g != this || (i2 = mediaBrowserImplBase.f44f) == 0 || i2 == 1) {
                    int i3 = mediaBrowserImplBase.f44f;
                    if (i3 == 0 || i3 == 1) {
                        return false;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" for ");
                    sb.append(MediaBrowserImplBase.this.f40b);
                    sb.append(" with mServiceConnection=");
                    sb.append(MediaBrowserImplBase.this.f45g);
                    sb.append(" this=");
                    sb.append(this);
                    return false;
                }
                return true;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
                postOrRun(new Runnable() { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.1
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean z = MediaBrowserCompat.f8a;
                        if (z) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("MediaServiceConnection.onServiceConnected name=");
                            sb.append(componentName);
                            sb.append(" binder=");
                            sb.append(iBinder);
                            MediaBrowserImplBase.this.a();
                        }
                        if (MediaServiceConnection.this.a("onServiceConnected")) {
                            MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                            mediaBrowserImplBase.f46h = new ServiceBinderWrapper(iBinder, mediaBrowserImplBase.f42d);
                            MediaBrowserImplBase.this.f47i = new Messenger(MediaBrowserImplBase.this.f43e);
                            MediaBrowserImplBase mediaBrowserImplBase2 = MediaBrowserImplBase.this;
                            mediaBrowserImplBase2.f43e.a(mediaBrowserImplBase2.f47i);
                            MediaBrowserImplBase.this.f44f = 2;
                            if (z) {
                                try {
                                    MediaBrowserImplBase.this.a();
                                } catch (RemoteException unused) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("RemoteException during connect for ");
                                    sb2.append(MediaBrowserImplBase.this.f40b);
                                    if (MediaBrowserCompat.f8a) {
                                        MediaBrowserImplBase.this.a();
                                        return;
                                    }
                                    return;
                                }
                            }
                            MediaBrowserImplBase mediaBrowserImplBase3 = MediaBrowserImplBase.this;
                            mediaBrowserImplBase3.f46h.b(mediaBrowserImplBase3.f39a, mediaBrowserImplBase3.f47i);
                        }
                    }
                });
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(final ComponentName componentName) {
                postOrRun(new Runnable() { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaBrowserCompat.f8a) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("MediaServiceConnection.onServiceDisconnected name=");
                            sb.append(componentName);
                            sb.append(" this=");
                            sb.append(this);
                            sb.append(" mServiceConnection=");
                            sb.append(MediaBrowserImplBase.this.f45g);
                            MediaBrowserImplBase.this.a();
                        }
                        if (MediaServiceConnection.this.a("onServiceDisconnected")) {
                            MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                            mediaBrowserImplBase.f46h = null;
                            mediaBrowserImplBase.f47i = null;
                            mediaBrowserImplBase.f43e.a(null);
                            MediaBrowserImplBase mediaBrowserImplBase2 = MediaBrowserImplBase.this;
                            mediaBrowserImplBase2.f44f = 4;
                            mediaBrowserImplBase2.f41c.onConnectionSuspended();
                        }
                    }
                });
            }
        }

        public MediaBrowserImplBase(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (connectionCallback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }
            this.f39a = context;
            this.f40b = componentName;
            this.f41c = connectionCallback;
            this.f42d = bundle == null ? null : new Bundle(bundle);
        }

        private static String getStateLabel(int i2) {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                return "UNKNOWN/" + i2;
                            }
                            return "CONNECT_STATE_SUSPENDED";
                        }
                        return "CONNECT_STATE_CONNECTED";
                    }
                    return "CONNECT_STATE_CONNECTING";
                }
                return "CONNECT_STATE_DISCONNECTED";
            }
            return "CONNECT_STATE_DISCONNECTING";
        }

        private boolean isCurrent(Messenger messenger, String str) {
            int i2;
            if (this.f47i != messenger || (i2 = this.f44f) == 0 || i2 == 1) {
                int i3 = this.f44f;
                if (i3 == 0 || i3 == 1) {
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" for ");
                sb.append(this.f40b);
                sb.append(" with mCallbacksMessenger=");
                sb.append(this.f47i);
                sb.append(" this=");
                sb.append(this);
                return false;
            }
            return true;
        }

        void a() {
            StringBuilder sb = new StringBuilder();
            sb.append("  mServiceComponent=");
            sb.append(this.f40b);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  mCallback=");
            sb2.append(this.f41c);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("  mRootHints=");
            sb3.append(this.f42d);
            StringBuilder sb4 = new StringBuilder();
            sb4.append("  mState=");
            sb4.append(getStateLabel(this.f44f));
            StringBuilder sb5 = new StringBuilder();
            sb5.append("  mServiceConnection=");
            sb5.append(this.f45g);
            StringBuilder sb6 = new StringBuilder();
            sb6.append("  mServiceBinderWrapper=");
            sb6.append(this.f46h);
            StringBuilder sb7 = new StringBuilder();
            sb7.append("  mCallbacksMessenger=");
            sb7.append(this.f47i);
            StringBuilder sb8 = new StringBuilder();
            sb8.append("  mRootId=");
            sb8.append(this.mRootId);
            StringBuilder sb9 = new StringBuilder();
            sb9.append("  mMediaSessionToken=");
            sb9.append(this.mMediaSessionToken);
        }

        void b() {
            MediaServiceConnection mediaServiceConnection = this.f45g;
            if (mediaServiceConnection != null) {
                this.f39a.unbindService(mediaServiceConnection);
            }
            this.f44f = 1;
            this.f45g = null;
            this.f46h = null;
            this.f47i = null;
            this.f43e.a(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void connect() {
            int i2 = this.f44f;
            if (i2 == 0 || i2 == 1) {
                this.f44f = 2;
                this.f43e.post(new Runnable() { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                        if (mediaBrowserImplBase.f44f == 0) {
                            return;
                        }
                        mediaBrowserImplBase.f44f = 2;
                        if (MediaBrowserCompat.f8a && mediaBrowserImplBase.f45g != null) {
                            throw new RuntimeException("mServiceConnection should be null. Instead it is " + MediaBrowserImplBase.this.f45g);
                        } else if (mediaBrowserImplBase.f46h != null) {
                            throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + MediaBrowserImplBase.this.f46h);
                        } else if (mediaBrowserImplBase.f47i != null) {
                            throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + MediaBrowserImplBase.this.f47i);
                        } else {
                            Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                            intent.setComponent(MediaBrowserImplBase.this.f40b);
                            MediaBrowserImplBase mediaBrowserImplBase2 = MediaBrowserImplBase.this;
                            mediaBrowserImplBase2.f45g = new MediaServiceConnection();
                            boolean z = false;
                            try {
                                MediaBrowserImplBase mediaBrowserImplBase3 = MediaBrowserImplBase.this;
                                z = mediaBrowserImplBase3.f39a.bindService(intent, mediaBrowserImplBase3.f45g, 1);
                            } catch (Exception unused) {
                                Log.e("MediaBrowserCompat", "Failed binding to service " + MediaBrowserImplBase.this.f40b);
                            }
                            if (!z) {
                                MediaBrowserImplBase.this.b();
                                MediaBrowserImplBase.this.f41c.onConnectionFailed();
                            }
                            if (MediaBrowserCompat.f8a) {
                                MediaBrowserImplBase.this.a();
                            }
                        }
                    }
                });
                return;
            }
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + getStateLabel(this.f44f) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void disconnect() {
            this.f44f = 0;
            this.f43e.post(new Runnable() { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.2
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowserImplBase mediaBrowserImplBase = MediaBrowserImplBase.this;
                    Messenger messenger = mediaBrowserImplBase.f47i;
                    if (messenger != null) {
                        try {
                            mediaBrowserImplBase.f46h.c(messenger);
                        } catch (RemoteException unused) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("RemoteException during connect for ");
                            sb.append(MediaBrowserImplBase.this.f40b);
                        }
                    }
                    MediaBrowserImplBase mediaBrowserImplBase2 = MediaBrowserImplBase.this;
                    int i2 = mediaBrowserImplBase2.f44f;
                    mediaBrowserImplBase2.b();
                    if (i2 != 0) {
                        MediaBrowserImplBase.this.f44f = i2;
                    }
                    if (MediaBrowserCompat.f8a) {
                        MediaBrowserImplBase.this.a();
                    }
                }
            });
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @Nullable
        public Bundle getExtras() {
            if (isConnected()) {
                return this.mExtras;
            }
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.f44f) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void getItem(@NonNull final String str, @NonNull final ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            }
            if (!isConnected()) {
                this.f43e.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.3
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
                return;
            }
            try {
                this.f46h.d(str, new ItemReceiver(str, itemCallback, this.f43e), this.f47i);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error getting media item: ");
                sb.append(str);
                this.f43e.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.4
                    @Override // java.lang.Runnable
                    public void run() {
                        itemCallback.onError(str);
                    }
                });
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @NonNull
        public String getRoot() {
            if (isConnected()) {
                return this.mRootId;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.f44f) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @NonNull
        public ComponentName getServiceComponent() {
            if (isConnected()) {
                return this.f40b;
            }
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.f44f + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f44f + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public boolean isConnected() {
            return this.f44f == 3;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onConnectionFailed(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.f40b);
            if (isCurrent(messenger, "onConnectFailed")) {
                if (this.f44f == 2) {
                    b();
                    this.f41c.onConnectionFailed();
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("onConnect from service while mState=");
                sb.append(getStateLabel(this.f44f));
                sb.append("... ignoring");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (isCurrent(messenger, "onLoadChildren")) {
                boolean z = MediaBrowserCompat.f8a;
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onLoadChildren for ");
                    sb.append(this.f40b);
                    sb.append(" id=");
                    sb.append(str);
                }
                Subscription subscription = this.mSubscriptions.get(str);
                if (subscription == null) {
                    if (z) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("onLoadChildren for id that isn't subscribed id=");
                        sb2.append(str);
                        return;
                    }
                    return;
                }
                SubscriptionCallback callback = subscription.getCallback(bundle);
                if (callback != null) {
                    if (bundle == null) {
                        if (list == null) {
                            callback.onError(str);
                            return;
                        } else {
                            this.mNotifyChildrenChangedOptions = bundle2;
                            callback.onChildrenLoaded(str, list);
                        }
                    } else if (list == null) {
                        callback.onError(str, bundle);
                        return;
                    } else {
                        this.mNotifyChildrenChangedOptions = bundle2;
                        callback.onChildrenLoaded(str, list, bundle);
                    }
                    this.mNotifyChildrenChangedOptions = null;
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserServiceCallbackImpl
        public void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (isCurrent(messenger, "onConnect")) {
                if (this.f44f != 2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onConnect from service while mState=");
                    sb.append(getStateLabel(this.f44f));
                    sb.append("... ignoring");
                    return;
                }
                this.mRootId = str;
                this.mMediaSessionToken = token;
                this.mExtras = bundle;
                this.f44f = 3;
                if (MediaBrowserCompat.f8a) {
                    a();
                }
                this.f41c.onConnected();
                try {
                    for (Map.Entry<String, Subscription> entry : this.mSubscriptions.entrySet()) {
                        String key = entry.getKey();
                        Subscription value = entry.getValue();
                        List<SubscriptionCallback> callbacks = value.getCallbacks();
                        List<Bundle> optionsList = value.getOptionsList();
                        for (int i2 = 0; i2 < callbacks.size(); i2++) {
                            this.f46h.a(key, callbacks.get(i2).f67b, optionsList.get(i2), this.f47i);
                        }
                    }
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void search(@NonNull final String str, final Bundle bundle, @NonNull final SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected (state=" + getStateLabel(this.f44f) + ")");
            }
            try {
                this.f46h.g(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.f43e), this.f47i);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error searching items with query: ");
                sb.append(str);
                this.f43e.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.5
                    @Override // java.lang.Runnable
                    public void run() {
                        searchCallback.onError(str, bundle);
                    }
                });
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void sendCustomAction(@NonNull final String str, final Bundle bundle, @Nullable final CustomActionCallback customActionCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
            }
            try {
                this.f46h.h(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.f43e), this.f47i);
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Remote error sending a custom action: action=");
                sb.append(str);
                sb.append(", extras=");
                sb.append(bundle);
                if (customActionCallback != null) {
                    this.f43e.post(new Runnable(this) { // from class: android.support.v4.media.MediaBrowserCompat.MediaBrowserImplBase.6
                        @Override // java.lang.Runnable
                        public void run() {
                            customActionCallback.onError(str, bundle, null);
                        }
                    });
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(str, subscription);
            }
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            subscription.putCallback(bundle2, subscriptionCallback);
            if (isConnected()) {
                try {
                    this.f46h.a(str, subscriptionCallback.f67b, bundle2, this.f47i);
                } catch (RemoteException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("addSubscription failed with RemoteException parentId=");
                    sb.append(str);
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.MediaBrowserImpl
        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(str);
            if (subscription == null) {
                return;
            }
            try {
                if (subscriptionCallback != null) {
                    List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                    List<Bundle> optionsList = subscription.getOptionsList();
                    for (int size = callbacks.size() - 1; size >= 0; size--) {
                        if (callbacks.get(size) == subscriptionCallback) {
                            if (isConnected()) {
                                this.f46h.f(str, subscriptionCallback.f67b, this.f47i);
                            }
                            callbacks.remove(size);
                            optionsList.remove(size);
                        }
                    }
                } else if (isConnected()) {
                    this.f46h.f(str, null, this.f47i);
                }
            } catch (RemoteException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("removeSubscription failed with RemoteException parentId=");
                sb.append(str);
            }
            if (subscription.isEmpty() || subscriptionCallback == null) {
                this.mSubscriptions.remove(str);
            }
        }
    }

    /* loaded from: classes.dex */
    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);
    }

    /* loaded from: classes.dex */
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() { // from class: android.support.v4.media.MediaBrowserCompat.MediaItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaItem[] newArray(int i2) {
                return new MediaItem[i2];
            }
        };
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface Flags {
        }

        MediaItem(Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i2) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.mFlags = i2;
            this.mDescription = mediaDescriptionCompat;
        }

        public static MediaItem fromMediaItem(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(obj)), MediaBrowserCompatApi21.MediaItem.getFlags(obj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fromMediaItem(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public int getFlags() {
            return this.mFlags;
        }

        @Nullable
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }

        public boolean isBrowsable() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.mFlags & 2) != 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.mFlags + ", mDescription=" + this.mDescription + AbstractJsonLexerKt.END_OBJ;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, i2);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SearchCallback {
        public void onError(@NonNull String str, Bundle bundle) {
        }

        public void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaItem> list) {
        }
    }

    /* loaded from: classes.dex */
    private static class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.mQuery = str;
            this.mExtras = bundle;
            this.mCallback = searchCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        protected void a(int i2, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i2 != 0 || bundle == null || !bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                this.mCallback.onError(this.mQuery, this.mExtras);
                return;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
            ArrayList arrayList = null;
            if (parcelableArray != null) {
                arrayList = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    arrayList.add((MediaItem) parcelable);
                }
            }
            this.mCallback.onSearchResult(this.mQuery, this.mExtras, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder iBinder, Bundle bundle) {
            this.mMessenger = new Messenger(iBinder);
            this.mRootHints = bundle;
        }

        private void sendRequest(int i2, Bundle bundle, Messenger messenger) {
            Message obtain = Message.obtain();
            obtain.what = i2;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.mMessenger.send(obtain);
        }

        void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle2, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            sendRequest(3, bundle2, messenger);
        }

        void b(Context context, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(1, bundle, messenger);
        }

        void c(Messenger messenger) {
            sendRequest(2, null, messenger);
        }

        void d(String str, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(5, bundle, messenger);
        }

        void e(Context context, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(6, bundle, messenger);
        }

        void f(String str, IBinder iBinder, Messenger messenger) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            sendRequest(4, bundle, messenger);
        }

        void g(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_SEARCH_QUERY, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(8, bundle2, messenger);
        }

        void h(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_CUSTOM_ACTION, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            sendRequest(9, bundle2, messenger);
        }

        void i(Messenger messenger) {
            sendRequest(7, null, messenger);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Subscription {
        private final List<SubscriptionCallback> mCallbacks = new ArrayList();
        private final List<Bundle> mOptionsList = new ArrayList();

        public SubscriptionCallback getCallback(Bundle bundle) {
            for (int i2 = 0; i2 < this.mOptionsList.size(); i2++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i2), bundle)) {
                    return this.mCallbacks.get(i2);
                }
            }
            return null;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i2 = 0; i2 < this.mOptionsList.size(); i2++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i2), bundle)) {
                    this.mCallbacks.set(i2, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SubscriptionCallback {

        /* renamed from: a  reason: collision with root package name */
        final Object f66a;

        /* renamed from: b  reason: collision with root package name */
        final IBinder f67b = new Binder();

        /* renamed from: c  reason: collision with root package name */
        WeakReference f68c;

        /* loaded from: classes.dex */
        private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
            StubApi21() {
            }

            List a(List list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int i3 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (i2 == -1 && i3 == -1) {
                    return list;
                }
                int i4 = i3 * i2;
                int i5 = i4 + i3;
                if (i2 < 0 || i3 < 1 || i4 >= list.size()) {
                    return Collections.emptyList();
                }
                if (i5 > list.size()) {
                    i5 = list.size();
                }
                return list.subList(i4, i5);
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi21.SubscriptionCallback
            public void onChildrenLoaded(@NonNull String str, List<?> list) {
                WeakReference weakReference = SubscriptionCallback.this.f68c;
                Subscription subscription = weakReference == null ? null : (Subscription) weakReference.get();
                if (subscription == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List<MediaItem> fromMediaItemList = MediaItem.fromMediaItemList(list);
                List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                List<Bundle> optionsList = subscription.getOptionsList();
                for (int i2 = 0; i2 < callbacks.size(); i2++) {
                    Bundle bundle = optionsList.get(i2);
                    if (bundle == null) {
                        SubscriptionCallback.this.onChildrenLoaded(str, fromMediaItemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(str, a(fromMediaItemList, bundle), bundle);
                    }
                }
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi21.SubscriptionCallback
            public void onError(@NonNull String str) {
                SubscriptionCallback.this.onError(str);
            }
        }

        /* loaded from: classes.dex */
        private class StubApi26 extends StubApi21 implements MediaBrowserCompatApi26.SubscriptionCallback {
            StubApi26() {
                super();
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi26.SubscriptionCallback
            public void onChildrenLoaded(@NonNull String str, List<?> list, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }

            @Override // android.support.v4.media.MediaBrowserCompatApi26.SubscriptionCallback
            public void onError(@NonNull String str, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onError(str, bundle);
            }
        }

        public SubscriptionCallback() {
            int i2 = Build.VERSION.SDK_INT;
            this.f66a = i2 >= 26 ? MediaBrowserCompatApi26.a(new StubApi26()) : i2 >= 21 ? MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21()) : null;
        }

        void a(Subscription subscription) {
            this.f68c = new WeakReference(subscription);
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void onError(@NonNull String str) {
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        this.mImpl = i2 >= 26 ? new MediaBrowserImplApi26(context, componentName, connectionCallback, bundle) : i2 >= 23 ? new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle) : i2 >= 21 ? new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle) : new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
        this.mImpl.getItem(str, itemCallback);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Bundle getNotifyChildrenChangedOptions() {
        return this.mImpl.getNotifyChildrenChangedOptions();
    }

    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        if (searchCallback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.search(str, bundle, searchCallback);
    }

    public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.mImpl.sendCustomAction(str, bundle, customActionCallback);
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.subscribe(str, bundle, subscriptionCallback);
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.subscribe(str, null, subscriptionCallback);
    }

    public void unsubscribe(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.mImpl.unsubscribe(str, null);
    }

    public void unsubscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.unsubscribe(str, subscriptionCallback);
    }
}
