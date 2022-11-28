package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(23)
/* loaded from: classes.dex */
class MediaBrowserCompatApi23 {

    /* loaded from: classes.dex */
    interface ItemCallback {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    /* loaded from: classes.dex */
    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {

        /* renamed from: a  reason: collision with root package name */
        protected final ItemCallback f73a;

        public ItemCallbackProxy(T t2) {
            this.f73a = t2;
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onError(@NonNull String str) {
            this.f73a.onError(str);
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
            Parcel obtain;
            ItemCallback itemCallback;
            if (mediaItem == null) {
                itemCallback = this.f73a;
                obtain = null;
            } else {
                obtain = Parcel.obtain();
                mediaItem.writeToParcel(obtain, 0);
                itemCallback = this.f73a;
            }
            itemCallback.onItemLoaded(obtain);
        }
    }

    private MediaBrowserCompatApi23() {
    }

    public static Object createItemCallback(ItemCallback itemCallback) {
        return new ItemCallbackProxy(itemCallback);
    }

    public static void getItem(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (MediaBrowser.ItemCallback) obj2);
    }
}
