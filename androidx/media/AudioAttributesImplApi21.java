package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@TargetApi(21)
/* loaded from: classes.dex */
class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";

    /* renamed from: c  reason: collision with root package name */
    static Method f3318c;

    /* renamed from: a  reason: collision with root package name */
    AudioAttributes f3319a;

    /* renamed from: b  reason: collision with root package name */
    int f3320b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplApi21() {
        this.f3320b = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i2) {
        this.f3320b = -1;
        this.f3319a = audioAttributes;
        this.f3320b = i2;
    }

    static Method a() {
        try {
            if (f3318c == null) {
                f3318c = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
            }
            return f3318c;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        AudioAttributes audioAttributes;
        if (bundle == null || (audioAttributes = (AudioAttributes) bundle.getParcelable("androidx.media.audio_attrs.FRAMEWORKS")) == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f3319a.equals(((AudioAttributesImplApi21) obj).f3319a);
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return this.f3319a;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.f3319a.getContentType();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        return this.f3319a.getFlags();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        StringBuilder sb;
        String str;
        int i2 = this.f3320b;
        if (i2 != -1) {
            return i2;
        }
        Method a2 = a();
        if (a2 == null) {
            sb = new StringBuilder();
            str = "No AudioAttributes#toLegacyStreamType() on API: ";
        } else {
            try {
                return ((Integer) a2.invoke(null, this.f3319a)).intValue();
            } catch (IllegalAccessException | InvocationTargetException unused) {
                sb = new StringBuilder();
                str = "getLegacyStreamType() failed on API: ";
            }
        }
        sb.append(str);
        sb.append(Build.VERSION.SDK_INT);
        return -1;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.f3320b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.f3319a.getUsage();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return Build.VERSION.SDK_INT >= 26 ? this.f3319a.getVolumeControlStream() : AudioAttributesCompat.a(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.f3319a.hashCode();
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.f3319a);
        int i2 = this.f3320b;
        if (i2 != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i2);
        }
        return bundle;
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f3319a;
    }
}
