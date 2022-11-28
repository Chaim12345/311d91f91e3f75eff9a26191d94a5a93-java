package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata;
/* loaded from: classes2.dex */
interface MetadataSource {
    Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i2);

    Phonemetadata.PhoneMetadata getMetadataForRegion(String str);
}
