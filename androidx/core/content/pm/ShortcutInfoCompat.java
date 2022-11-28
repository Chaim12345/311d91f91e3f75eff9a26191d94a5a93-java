package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class ShortcutInfoCompat {
    private static final String EXTRA_LOCUS_ID = "extraLocusId";
    private static final String EXTRA_LONG_LIVED = "extraLongLived";
    private static final String EXTRA_PERSON_ = "extraPerson_";
    private static final String EXTRA_PERSON_COUNT = "extraPersonCount";

    /* renamed from: a  reason: collision with root package name */
    Context f2481a;

    /* renamed from: b  reason: collision with root package name */
    String f2482b;

    /* renamed from: c  reason: collision with root package name */
    String f2483c;

    /* renamed from: d  reason: collision with root package name */
    Intent[] f2484d;

    /* renamed from: e  reason: collision with root package name */
    ComponentName f2485e;

    /* renamed from: f  reason: collision with root package name */
    CharSequence f2486f;

    /* renamed from: g  reason: collision with root package name */
    CharSequence f2487g;

    /* renamed from: h  reason: collision with root package name */
    CharSequence f2488h;

    /* renamed from: i  reason: collision with root package name */
    IconCompat f2489i;

    /* renamed from: j  reason: collision with root package name */
    boolean f2490j;

    /* renamed from: k  reason: collision with root package name */
    Person[] f2491k;

    /* renamed from: l  reason: collision with root package name */
    Set f2492l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    LocusIdCompat f2493m;

    /* renamed from: n  reason: collision with root package name */
    boolean f2494n;

    /* renamed from: o  reason: collision with root package name */
    int f2495o;

    /* renamed from: p  reason: collision with root package name */
    PersistableBundle f2496p;

    /* renamed from: q  reason: collision with root package name */
    long f2497q;

    /* renamed from: r  reason: collision with root package name */
    UserHandle f2498r;

    /* renamed from: s  reason: collision with root package name */
    boolean f2499s;

    /* renamed from: t  reason: collision with root package name */
    boolean f2500t;
    boolean u;
    boolean v;
    boolean w;
    boolean x = true;
    boolean y;
    int z;

    /* loaded from: classes.dex */
    public static class Builder {
        private final ShortcutInfoCompat mInfo;
        private boolean mIsConversation;

        @RequiresApi(25)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull Context context, @NonNull ShortcutInfo shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat;
            shortcutInfoCompat.f2481a = context;
            shortcutInfoCompat.f2482b = shortcutInfo.getId();
            shortcutInfoCompat.f2483c = shortcutInfo.getPackage();
            Intent[] intents = shortcutInfo.getIntents();
            shortcutInfoCompat.f2484d = (Intent[]) Arrays.copyOf(intents, intents.length);
            shortcutInfoCompat.f2485e = shortcutInfo.getActivity();
            shortcutInfoCompat.f2486f = shortcutInfo.getShortLabel();
            shortcutInfoCompat.f2487g = shortcutInfo.getLongLabel();
            shortcutInfoCompat.f2488h = shortcutInfo.getDisabledMessage();
            int i2 = Build.VERSION.SDK_INT;
            shortcutInfoCompat.z = i2 >= 28 ? shortcutInfo.getDisabledReason() : shortcutInfo.isEnabled() ? 0 : 3;
            shortcutInfoCompat.f2492l = shortcutInfo.getCategories();
            shortcutInfoCompat.f2491k = ShortcutInfoCompat.d(shortcutInfo.getExtras());
            shortcutInfoCompat.f2498r = shortcutInfo.getUserHandle();
            shortcutInfoCompat.f2497q = shortcutInfo.getLastChangedTimestamp();
            if (i2 >= 30) {
                shortcutInfoCompat.f2499s = shortcutInfo.isCached();
            }
            shortcutInfoCompat.f2500t = shortcutInfo.isDynamic();
            shortcutInfoCompat.u = shortcutInfo.isPinned();
            shortcutInfoCompat.v = shortcutInfo.isDeclaredInManifest();
            shortcutInfoCompat.w = shortcutInfo.isImmutable();
            shortcutInfoCompat.x = shortcutInfo.isEnabled();
            shortcutInfoCompat.y = shortcutInfo.hasKeyFieldsOnly();
            shortcutInfoCompat.f2493m = ShortcutInfoCompat.c(shortcutInfo);
            shortcutInfoCompat.f2495o = shortcutInfo.getRank();
            shortcutInfoCompat.f2496p = shortcutInfo.getExtras();
        }

        public Builder(@NonNull Context context, @NonNull String str) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat;
            shortcutInfoCompat.f2481a = context;
            shortcutInfoCompat.f2482b = str;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull ShortcutInfoCompat shortcutInfoCompat) {
            ShortcutInfoCompat shortcutInfoCompat2 = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat2;
            shortcutInfoCompat2.f2481a = shortcutInfoCompat.f2481a;
            shortcutInfoCompat2.f2482b = shortcutInfoCompat.f2482b;
            shortcutInfoCompat2.f2483c = shortcutInfoCompat.f2483c;
            Intent[] intentArr = shortcutInfoCompat.f2484d;
            shortcutInfoCompat2.f2484d = (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
            shortcutInfoCompat2.f2485e = shortcutInfoCompat.f2485e;
            shortcutInfoCompat2.f2486f = shortcutInfoCompat.f2486f;
            shortcutInfoCompat2.f2487g = shortcutInfoCompat.f2487g;
            shortcutInfoCompat2.f2488h = shortcutInfoCompat.f2488h;
            shortcutInfoCompat2.z = shortcutInfoCompat.z;
            shortcutInfoCompat2.f2489i = shortcutInfoCompat.f2489i;
            shortcutInfoCompat2.f2490j = shortcutInfoCompat.f2490j;
            shortcutInfoCompat2.f2498r = shortcutInfoCompat.f2498r;
            shortcutInfoCompat2.f2497q = shortcutInfoCompat.f2497q;
            shortcutInfoCompat2.f2499s = shortcutInfoCompat.f2499s;
            shortcutInfoCompat2.f2500t = shortcutInfoCompat.f2500t;
            shortcutInfoCompat2.u = shortcutInfoCompat.u;
            shortcutInfoCompat2.v = shortcutInfoCompat.v;
            shortcutInfoCompat2.w = shortcutInfoCompat.w;
            shortcutInfoCompat2.x = shortcutInfoCompat.x;
            shortcutInfoCompat2.f2493m = shortcutInfoCompat.f2493m;
            shortcutInfoCompat2.f2494n = shortcutInfoCompat.f2494n;
            shortcutInfoCompat2.y = shortcutInfoCompat.y;
            shortcutInfoCompat2.f2495o = shortcutInfoCompat.f2495o;
            Person[] personArr = shortcutInfoCompat.f2491k;
            if (personArr != null) {
                shortcutInfoCompat2.f2491k = (Person[]) Arrays.copyOf(personArr, personArr.length);
            }
            if (shortcutInfoCompat.f2492l != null) {
                shortcutInfoCompat2.f2492l = new HashSet(shortcutInfoCompat.f2492l);
            }
            PersistableBundle persistableBundle = shortcutInfoCompat.f2496p;
            if (persistableBundle != null) {
                shortcutInfoCompat2.f2496p = persistableBundle;
            }
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.mInfo.f2486f)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            ShortcutInfoCompat shortcutInfoCompat = this.mInfo;
            Intent[] intentArr = shortcutInfoCompat.f2484d;
            if (intentArr == null || intentArr.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            if (this.mIsConversation) {
                if (shortcutInfoCompat.f2493m == null) {
                    shortcutInfoCompat.f2493m = new LocusIdCompat(shortcutInfoCompat.f2482b);
                }
                this.mInfo.f2494n = true;
            }
            return this.mInfo;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.mInfo.f2485e = componentName;
            return this;
        }

        @NonNull
        public Builder setAlwaysBadged() {
            this.mInfo.f2490j = true;
            return this;
        }

        @NonNull
        public Builder setCategories(@NonNull Set<String> set) {
            this.mInfo.f2492l = set;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.mInfo.f2488h = charSequence;
            return this;
        }

        @NonNull
        public Builder setExtras(@NonNull PersistableBundle persistableBundle) {
            this.mInfo.f2496p = persistableBundle;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.mInfo.f2489i = iconCompat;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.mInfo.f2484d = intentArr;
            return this;
        }

        @NonNull
        public Builder setIsConversation() {
            this.mIsConversation = true;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable LocusIdCompat locusIdCompat) {
            this.mInfo.f2493m = locusIdCompat;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.mInfo.f2487g = charSequence;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setLongLived() {
            this.mInfo.f2494n = true;
            return this;
        }

        @NonNull
        public Builder setLongLived(boolean z) {
            this.mInfo.f2494n = z;
            return this;
        }

        @NonNull
        public Builder setPerson(@NonNull Person person) {
            return setPersons(new Person[]{person});
        }

        @NonNull
        public Builder setPersons(@NonNull Person[] personArr) {
            this.mInfo.f2491k = personArr;
            return this;
        }

        @NonNull
        public Builder setRank(int i2) {
            this.mInfo.f2495o = i2;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.mInfo.f2486f = charSequence;
            return this;
        }
    }

    ShortcutInfoCompat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static List b(@NonNull Context context, @NonNull List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Builder(context, (ShortcutInfo) it.next()).build());
        }
        return arrayList;
    }

    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    private PersistableBundle buildLegacyExtrasBundle() {
        if (this.f2496p == null) {
            this.f2496p = new PersistableBundle();
        }
        Person[] personArr = this.f2491k;
        if (personArr != null && personArr.length > 0) {
            this.f2496p.putInt(EXTRA_PERSON_COUNT, personArr.length);
            int i2 = 0;
            while (i2 < this.f2491k.length) {
                PersistableBundle persistableBundle = this.f2496p;
                StringBuilder sb = new StringBuilder();
                sb.append(EXTRA_PERSON_);
                int i3 = i2 + 1;
                sb.append(i3);
                persistableBundle.putPersistableBundle(sb.toString(), this.f2491k[i2].toPersistableBundle());
                i2 = i3;
            }
        }
        LocusIdCompat locusIdCompat = this.f2493m;
        if (locusIdCompat != null) {
            this.f2496p.putString(EXTRA_LOCUS_ID, locusIdCompat.getId());
        }
        this.f2496p.putBoolean(EXTRA_LONG_LIVED, this.f2494n);
        return this.f2496p;
    }

    @Nullable
    @RequiresApi(25)
    static LocusIdCompat c(@NonNull ShortcutInfo shortcutInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (shortcutInfo.getLocusId() == null) {
                return null;
            }
            return LocusIdCompat.toLocusIdCompat(shortcutInfo.getLocusId());
        }
        return getLocusIdFromExtra(shortcutInfo.getExtras());
    }

    @VisibleForTesting
    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static Person[] d(@NonNull PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey(EXTRA_PERSON_COUNT)) {
            return null;
        }
        int i2 = persistableBundle.getInt(EXTRA_PERSON_COUNT);
        Person[] personArr = new Person[i2];
        int i3 = 0;
        while (i3 < i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXTRA_PERSON_);
            int i4 = i3 + 1;
            sb.append(i4);
            personArr[i3] = Person.fromPersistableBundle(persistableBundle.getPersistableBundle(sb.toString()));
            i3 = i4;
        }
        return personArr;
    }

    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    private static LocusIdCompat getLocusIdFromExtra(@Nullable PersistableBundle persistableBundle) {
        String string;
        if (persistableBundle == null || (string = persistableBundle.getString(EXTRA_LOCUS_ID)) == null) {
            return null;
        }
        return new LocusIdCompat(string);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent a(Intent intent) {
        Intent[] intentArr = this.f2484d;
        intent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.f2486f.toString());
        if (this.f2489i != null) {
            Drawable drawable = null;
            if (this.f2490j) {
                PackageManager packageManager = this.f2481a.getPackageManager();
                ComponentName componentName = this.f2485e;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.f2481a.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.f2489i.addToShortcutIntent(intent, drawable, this.f2481a);
        }
        return intent;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.f2485e;
    }

    @Nullable
    public Set<String> getCategories() {
        return this.f2492l;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.f2488h;
    }

    public int getDisabledReason() {
        return this.z;
    }

    @Nullable
    public PersistableBundle getExtras() {
        return this.f2496p;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public IconCompat getIcon() {
        return this.f2489i;
    }

    @NonNull
    public String getId() {
        return this.f2482b;
    }

    @NonNull
    public Intent getIntent() {
        Intent[] intentArr = this.f2484d;
        return intentArr[intentArr.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = this.f2484d;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    public long getLastChangedTimestamp() {
        return this.f2497q;
    }

    @Nullable
    public LocusIdCompat getLocusId() {
        return this.f2493m;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.f2487g;
    }

    @NonNull
    public String getPackage() {
        return this.f2483c;
    }

    public int getRank() {
        return this.f2495o;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.f2486f;
    }

    @Nullable
    public UserHandle getUserHandle() {
        return this.f2498r;
    }

    public boolean hasKeyFieldsOnly() {
        return this.y;
    }

    public boolean isCached() {
        return this.f2499s;
    }

    public boolean isDeclaredInManifest() {
        return this.v;
    }

    public boolean isDynamic() {
        return this.f2500t;
    }

    public boolean isEnabled() {
        return this.x;
    }

    public boolean isImmutable() {
        return this.w;
    }

    public boolean isPinned() {
        return this.u;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.f2481a, this.f2482b).setShortLabel(this.f2486f).setIntents(this.f2484d);
        IconCompat iconCompat = this.f2489i;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon(this.f2481a));
        }
        if (!TextUtils.isEmpty(this.f2487g)) {
            intents.setLongLabel(this.f2487g);
        }
        if (!TextUtils.isEmpty(this.f2488h)) {
            intents.setDisabledMessage(this.f2488h);
        }
        ComponentName componentName = this.f2485e;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.f2492l;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.f2495o);
        PersistableBundle persistableBundle = this.f2496p;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Person[] personArr = this.f2491k;
            if (personArr != null && personArr.length > 0) {
                int length = personArr.length;
                android.app.Person[] personArr2 = new android.app.Person[length];
                for (int i2 = 0; i2 < length; i2++) {
                    personArr2[i2] = this.f2491k[i2].toAndroidPerson();
                }
                intents.setPersons(personArr2);
            }
            LocusIdCompat locusIdCompat = this.f2493m;
            if (locusIdCompat != null) {
                intents.setLocusId(locusIdCompat.toLocusId());
            }
            intents.setLongLived(this.f2494n);
        } else {
            intents.setExtras(buildLegacyExtrasBundle());
        }
        return intents.build();
    }
}
