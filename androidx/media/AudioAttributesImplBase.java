package androidx.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.Arrays;
/* loaded from: classes.dex */
class AudioAttributesImplBase implements AudioAttributesImpl {

    /* renamed from: a  reason: collision with root package name */
    int f3321a;

    /* renamed from: b  reason: collision with root package name */
    int f3322b;

    /* renamed from: c  reason: collision with root package name */
    int f3323c;

    /* renamed from: d  reason: collision with root package name */
    int f3324d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplBase() {
        this.f3321a = 0;
        this.f3322b = 0;
        this.f3323c = 0;
        this.f3324d = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplBase(int i2, int i3, int i4, int i5) {
        this.f3321a = 0;
        this.f3322b = 0;
        this.f3323c = 0;
        this.f3324d = -1;
        this.f3322b = i2;
        this.f3323c = i3;
        this.f3321a = i4;
        this.f3324d = i5;
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt("androidx.media.audio_attrs.CONTENT_TYPE", 0), bundle.getInt("androidx.media.audio_attrs.FLAGS", 0), bundle.getInt("androidx.media.audio_attrs.USAGE", 0), bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplBase) {
            AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
            return this.f3322b == audioAttributesImplBase.getContentType() && this.f3323c == audioAttributesImplBase.getFlags() && this.f3321a == audioAttributesImplBase.getUsage() && this.f3324d == audioAttributesImplBase.f3324d;
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return null;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.f3322b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        int i2 = this.f3323c;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i2 |= 4;
        } else if (legacyStreamType == 7) {
            i2 |= 1;
        }
        return i2 & 273;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i2 = this.f3324d;
        return i2 != -1 ? i2 : AudioAttributesCompat.a(false, this.f3323c, this.f3321a);
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.f3324d;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.f3321a;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.a(true, this.f3323c, this.f3321a);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f3322b), Integer.valueOf(this.f3323c), Integer.valueOf(this.f3321a), Integer.valueOf(this.f3324d)});
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("androidx.media.audio_attrs.USAGE", this.f3321a);
        bundle.putInt("androidx.media.audio_attrs.CONTENT_TYPE", this.f3322b);
        bundle.putInt("androidx.media.audio_attrs.FLAGS", this.f3323c);
        int i2 = this.f3324d;
        if (i2 != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i2);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f3324d != -1) {
            sb.append(" stream=");
            sb.append(this.f3324d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.c(this.f3321a));
        sb.append(" content=");
        sb.append(this.f3322b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f3323c).toUpperCase());
        return sb.toString();
    }
}
