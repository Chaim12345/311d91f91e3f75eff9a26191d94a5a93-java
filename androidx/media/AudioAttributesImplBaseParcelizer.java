package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f3321a = versionedParcel.readInt(audioAttributesImplBase.f3321a, 1);
        audioAttributesImplBase.f3322b = versionedParcel.readInt(audioAttributesImplBase.f3322b, 2);
        audioAttributesImplBase.f3323c = versionedParcel.readInt(audioAttributesImplBase.f3323c, 3);
        audioAttributesImplBase.f3324d = versionedParcel.readInt(audioAttributesImplBase.f3324d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(audioAttributesImplBase.f3321a, 1);
        versionedParcel.writeInt(audioAttributesImplBase.f3322b, 2);
        versionedParcel.writeInt(audioAttributesImplBase.f3323c, 3);
        versionedParcel.writeInt(audioAttributesImplBase.f3324d, 4);
    }
}
