package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;
/* loaded from: classes.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    private static final String[] PREFERRED_BITMAP_ORDER;
    private static final String[] PREFERRED_DESCRIPTION_ORDER;
    private static final String[] PREFERRED_URI_ORDER;
    private static final String TAG = "MediaMetadata";

    /* renamed from: b  reason: collision with root package name */
    static final ArrayMap f74b;

    /* renamed from: a  reason: collision with root package name */
    final Bundle f75a;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface BitmapKey {
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final Bundle mBundle;

        public Builder() {
            this.mBundle = new Bundle();
        }

        public Builder(MediaMetadataCompat mediaMetadataCompat) {
            Bundle bundle = new Bundle(mediaMetadataCompat.f75a);
            this.mBundle = bundle;
            MediaSessionCompat.ensureClassLoader(bundle);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(MediaMetadataCompat mediaMetadataCompat, int i2) {
            this(mediaMetadataCompat);
            for (String str : this.mBundle.keySet()) {
                Object obj = this.mBundle.get(str);
                if (obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) obj;
                    if (bitmap.getHeight() > i2 || bitmap.getWidth() > i2) {
                        putBitmap(str, scaleBitmap(bitmap, i2));
                    }
                }
            }
        }

        private Bitmap scaleBitmap(Bitmap bitmap, int i2) {
            float f2 = i2;
            float min = Math.min(f2 / bitmap.getWidth(), f2 / bitmap.getHeight());
            return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * min), (int) (bitmap.getHeight() * min), true);
        }

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.mBundle);
        }

        public Builder putBitmap(String str, Bitmap bitmap) {
            ArrayMap arrayMap = MediaMetadataCompat.f74b;
            if (!arrayMap.containsKey(str) || ((Integer) arrayMap.get(str)).intValue() == 2) {
                this.mBundle.putParcelable(str, bitmap);
                return this;
            }
            throw new IllegalArgumentException("The " + str + " key cannot be used to put a Bitmap");
        }

        public Builder putLong(String str, long j2) {
            ArrayMap arrayMap = MediaMetadataCompat.f74b;
            if (!arrayMap.containsKey(str) || ((Integer) arrayMap.get(str)).intValue() == 0) {
                this.mBundle.putLong(str, j2);
                return this;
            }
            throw new IllegalArgumentException("The " + str + " key cannot be used to put a long");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v3, types: [android.os.Parcelable] */
        public Builder putRating(String str, RatingCompat ratingCompat) {
            RatingCompat ratingCompat2;
            Bundle bundle;
            ArrayMap arrayMap = MediaMetadataCompat.f74b;
            if (arrayMap.containsKey(str) && ((Integer) arrayMap.get(str)).intValue() != 3) {
                throw new IllegalArgumentException("The " + str + " key cannot be used to put a Rating");
            }
            if (Build.VERSION.SDK_INT >= 19) {
                bundle = this.mBundle;
                ratingCompat2 = (Parcelable) ratingCompat.getRating();
            } else {
                bundle = this.mBundle;
                ratingCompat2 = ratingCompat;
            }
            bundle.putParcelable(str, ratingCompat2);
            return this;
        }

        public Builder putString(String str, String str2) {
            ArrayMap arrayMap = MediaMetadataCompat.f74b;
            if (!arrayMap.containsKey(str) || ((Integer) arrayMap.get(str)).intValue() == 1) {
                this.mBundle.putCharSequence(str, str2);
                return this;
            }
            throw new IllegalArgumentException("The " + str + " key cannot be used to put a String");
        }

        public Builder putText(String str, CharSequence charSequence) {
            ArrayMap arrayMap = MediaMetadataCompat.f74b;
            if (!arrayMap.containsKey(str) || ((Integer) arrayMap.get(str)).intValue() == 1) {
                this.mBundle.putCharSequence(str, charSequence);
                return this;
            }
            throw new IllegalArgumentException("The " + str + " key cannot be used to put a CharSequence");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface LongKey {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface RatingKey {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface TextKey {
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        f74b = arrayMap;
        arrayMap.put(METADATA_KEY_TITLE, 1);
        arrayMap.put(METADATA_KEY_ARTIST, 1);
        arrayMap.put(METADATA_KEY_DURATION, 0);
        arrayMap.put(METADATA_KEY_ALBUM, 1);
        arrayMap.put(METADATA_KEY_AUTHOR, 1);
        arrayMap.put(METADATA_KEY_WRITER, 1);
        arrayMap.put(METADATA_KEY_COMPOSER, 1);
        arrayMap.put(METADATA_KEY_COMPILATION, 1);
        arrayMap.put(METADATA_KEY_DATE, 1);
        arrayMap.put(METADATA_KEY_YEAR, 0);
        arrayMap.put(METADATA_KEY_GENRE, 1);
        arrayMap.put(METADATA_KEY_TRACK_NUMBER, 0);
        arrayMap.put(METADATA_KEY_NUM_TRACKS, 0);
        arrayMap.put(METADATA_KEY_DISC_NUMBER, 0);
        arrayMap.put(METADATA_KEY_ALBUM_ARTIST, 1);
        arrayMap.put(METADATA_KEY_ART, 2);
        arrayMap.put(METADATA_KEY_ART_URI, 1);
        arrayMap.put(METADATA_KEY_ALBUM_ART, 2);
        arrayMap.put(METADATA_KEY_ALBUM_ART_URI, 1);
        arrayMap.put(METADATA_KEY_USER_RATING, 3);
        arrayMap.put(METADATA_KEY_RATING, 3);
        arrayMap.put(METADATA_KEY_DISPLAY_TITLE, 1);
        arrayMap.put(METADATA_KEY_DISPLAY_SUBTITLE, 1);
        arrayMap.put(METADATA_KEY_DISPLAY_DESCRIPTION, 1);
        arrayMap.put(METADATA_KEY_DISPLAY_ICON, 2);
        arrayMap.put(METADATA_KEY_DISPLAY_ICON_URI, 1);
        arrayMap.put(METADATA_KEY_MEDIA_ID, 1);
        arrayMap.put(METADATA_KEY_BT_FOLDER_TYPE, 0);
        arrayMap.put(METADATA_KEY_MEDIA_URI, 1);
        arrayMap.put(METADATA_KEY_ADVERTISEMENT, 0);
        arrayMap.put(METADATA_KEY_DOWNLOAD_STATUS, 0);
        PREFERRED_DESCRIPTION_ORDER = new String[]{METADATA_KEY_TITLE, METADATA_KEY_ARTIST, METADATA_KEY_ALBUM, METADATA_KEY_ALBUM_ARTIST, METADATA_KEY_WRITER, METADATA_KEY_AUTHOR, METADATA_KEY_COMPOSER};
        PREFERRED_BITMAP_ORDER = new String[]{METADATA_KEY_DISPLAY_ICON, METADATA_KEY_ART, METADATA_KEY_ALBUM_ART};
        PREFERRED_URI_ORDER = new String[]{METADATA_KEY_DISPLAY_ICON_URI, METADATA_KEY_ART_URI, METADATA_KEY_ALBUM_ART_URI};
        CREATOR = new Parcelable.Creator<MediaMetadataCompat>() { // from class: android.support.v4.media.MediaMetadataCompat.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaMetadataCompat createFromParcel(Parcel parcel) {
                return new MediaMetadataCompat(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaMetadataCompat[] newArray(int i2) {
                return new MediaMetadataCompat[i2];
            }
        };
    }

    MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.f75a = bundle2;
        MediaSessionCompat.ensureClassLoader(bundle2);
    }

    MediaMetadataCompat(Parcel parcel) {
        this.f75a = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public static MediaMetadataCompat fromMediaMetadata(Object obj) {
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        MediaMetadataCompatApi21.writeToParcel(obj, obtain, 0);
        obtain.setDataPosition(0);
        MediaMetadataCompat createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        createFromParcel.mMetadataObj = obj;
        return createFromParcel;
    }

    public boolean containsKey(String str) {
        return this.f75a.containsKey(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getBitmap(String str) {
        try {
            return (Bitmap) this.f75a.getParcelable(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public Bundle getBundle() {
        return new Bundle(this.f75a);
    }

    public MediaDescriptionCompat getDescription() {
        Bitmap bitmap;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String string = getString(METADATA_KEY_MEDIA_ID);
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence text = getText(METADATA_KEY_DISPLAY_TITLE);
        if (TextUtils.isEmpty(text)) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < 3) {
                String[] strArr = PREFERRED_DESCRIPTION_ORDER;
                if (i3 >= strArr.length) {
                    break;
                }
                int i4 = i3 + 1;
                CharSequence text2 = getText(strArr[i3]);
                if (!TextUtils.isEmpty(text2)) {
                    charSequenceArr[i2] = text2;
                    i2++;
                }
                i3 = i4;
            }
        } else {
            charSequenceArr[0] = text;
            charSequenceArr[1] = getText(METADATA_KEY_DISPLAY_SUBTITLE);
            charSequenceArr[2] = getText(METADATA_KEY_DISPLAY_DESCRIPTION);
        }
        int i5 = 0;
        while (true) {
            String[] strArr2 = PREFERRED_BITMAP_ORDER;
            if (i5 >= strArr2.length) {
                bitmap = null;
                break;
            }
            bitmap = getBitmap(strArr2[i5]);
            if (bitmap != null) {
                break;
            }
            i5++;
        }
        int i6 = 0;
        while (true) {
            String[] strArr3 = PREFERRED_URI_ORDER;
            if (i6 >= strArr3.length) {
                uri = null;
                break;
            }
            String string2 = getString(strArr3[i6]);
            if (!TextUtils.isEmpty(string2)) {
                uri = Uri.parse(string2);
                break;
            }
            i6++;
        }
        String string3 = getString(METADATA_KEY_MEDIA_URI);
        Uri parse = TextUtils.isEmpty(string3) ? null : Uri.parse(string3);
        MediaDescriptionCompat.Builder builder = new MediaDescriptionCompat.Builder();
        builder.setMediaId(string);
        builder.setTitle(charSequenceArr[0]);
        builder.setSubtitle(charSequenceArr[1]);
        builder.setDescription(charSequenceArr[2]);
        builder.setIconBitmap(bitmap);
        builder.setIconUri(uri);
        builder.setMediaUri(parse);
        Bundle bundle = new Bundle();
        if (this.f75a.containsKey(METADATA_KEY_BT_FOLDER_TYPE)) {
            bundle.putLong(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE, getLong(METADATA_KEY_BT_FOLDER_TYPE));
        }
        if (this.f75a.containsKey(METADATA_KEY_DOWNLOAD_STATUS)) {
            bundle.putLong(MediaDescriptionCompat.EXTRA_DOWNLOAD_STATUS, getLong(METADATA_KEY_DOWNLOAD_STATUS));
        }
        if (!bundle.isEmpty()) {
            builder.setExtras(bundle);
        }
        MediaDescriptionCompat build = builder.build();
        this.mDescription = build;
        return build;
    }

    public long getLong(String str) {
        return this.f75a.getLong(str, 0L);
    }

    public Object getMediaMetadata() {
        if (this.mMetadataObj == null && Build.VERSION.SDK_INT >= 21) {
            Parcel obtain = Parcel.obtain();
            writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(obtain);
            obtain.recycle();
        }
        return this.mMetadataObj;
    }

    public RatingCompat getRating(String str) {
        try {
            return Build.VERSION.SDK_INT >= 19 ? RatingCompat.fromRating(this.f75a.getParcelable(str)) : (RatingCompat) this.f75a.getParcelable(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getString(String str) {
        CharSequence charSequence = this.f75a.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence getText(String str) {
        return this.f75a.getCharSequence(str);
    }

    public Set<String> keySet() {
        return this.f75a.keySet();
    }

    public int size() {
        return this.f75a.size();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.f75a);
    }
}
