package androidx.core.app;

import android.app.Person;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
/* loaded from: classes.dex */
public class Person {
    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    CharSequence f2468a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    IconCompat f2469b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    String f2470c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    String f2471d;

    /* renamed from: e  reason: collision with root package name */
    boolean f2472e;

    /* renamed from: f  reason: collision with root package name */
    boolean f2473f;

    /* loaded from: classes.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        CharSequence f2474a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        IconCompat f2475b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        String f2476c;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        String f2477d;

        /* renamed from: e  reason: collision with root package name */
        boolean f2478e;

        /* renamed from: f  reason: collision with root package name */
        boolean f2479f;

        public Builder() {
        }

        Builder(Person person) {
            this.f2474a = person.f2468a;
            this.f2475b = person.f2469b;
            this.f2476c = person.f2470c;
            this.f2477d = person.f2471d;
            this.f2478e = person.f2472e;
            this.f2479f = person.f2473f;
        }

        @NonNull
        public Person build() {
            return new Person(this);
        }

        @NonNull
        public Builder setBot(boolean z) {
            this.f2478e = z;
            return this;
        }

        @NonNull
        public Builder setIcon(@Nullable IconCompat iconCompat) {
            this.f2475b = iconCompat;
            return this;
        }

        @NonNull
        public Builder setImportant(boolean z) {
            this.f2479f = z;
            return this;
        }

        @NonNull
        public Builder setKey(@Nullable String str) {
            this.f2477d = str;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.f2474a = charSequence;
            return this;
        }

        @NonNull
        public Builder setUri(@Nullable String str) {
            this.f2476c = str;
            return this;
        }
    }

    Person(Builder builder) {
        this.f2468a = builder.f2474a;
        this.f2469b = builder.f2475b;
        this.f2470c = builder.f2476c;
        this.f2471d = builder.f2477d;
        this.f2472e = builder.f2478e;
        this.f2473f = builder.f2479f;
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person fromAndroidPerson(@NonNull android.app.Person person) {
        return new Builder().setName(person.getName()).setIcon(person.getIcon() != null ? IconCompat.createFromIcon(person.getIcon()) : null).setUri(person.getUri()).setKey(person.getKey()).setBot(person.isBot()).setImportant(person.isImportant()).build();
    }

    @NonNull
    public static Person fromBundle(@NonNull Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(ICON_KEY);
        return new Builder().setName(bundle.getCharSequence("name")).setIcon(bundle2 != null ? IconCompat.createFromBundle(bundle2) : null).setUri(bundle.getString(URI_KEY)).setKey(bundle.getString(KEY_KEY)).setBot(bundle.getBoolean(IS_BOT_KEY)).setImportant(bundle.getBoolean(IS_IMPORTANT_KEY)).build();
    }

    @NonNull
    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person fromPersistableBundle(@NonNull PersistableBundle persistableBundle) {
        return new Builder().setName(persistableBundle.getString("name")).setUri(persistableBundle.getString(URI_KEY)).setKey(persistableBundle.getString(KEY_KEY)).setBot(persistableBundle.getBoolean(IS_BOT_KEY)).setImportant(persistableBundle.getBoolean(IS_IMPORTANT_KEY)).build();
    }

    @Nullable
    public IconCompat getIcon() {
        return this.f2469b;
    }

    @Nullable
    public String getKey() {
        return this.f2471d;
    }

    @Nullable
    public CharSequence getName() {
        return this.f2468a;
    }

    @Nullable
    public String getUri() {
        return this.f2470c;
    }

    public boolean isBot() {
        return this.f2472e;
    }

    public boolean isImportant() {
        return this.f2473f;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public String resolveToLegacyUri() {
        String str = this.f2470c;
        if (str != null) {
            return str;
        }
        if (this.f2468a != null) {
            return "name:" + ((Object) this.f2468a);
        }
        return "";
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public android.app.Person toAndroidPerson() {
        return new Person.Builder().setName(getName()).setIcon(getIcon() != null ? getIcon().toIcon() : null).setUri(getUri()).setKey(getKey()).setBot(isBot()).setImportant(isImportant()).build();
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("name", this.f2468a);
        IconCompat iconCompat = this.f2469b;
        bundle.putBundle(ICON_KEY, iconCompat != null ? iconCompat.toBundle() : null);
        bundle.putString(URI_KEY, this.f2470c);
        bundle.putString(KEY_KEY, this.f2471d);
        bundle.putBoolean(IS_BOT_KEY, this.f2472e);
        bundle.putBoolean(IS_IMPORTANT_KEY, this.f2473f);
        return bundle;
    }

    @NonNull
    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PersistableBundle toPersistableBundle() {
        PersistableBundle persistableBundle = new PersistableBundle();
        CharSequence charSequence = this.f2468a;
        persistableBundle.putString("name", charSequence != null ? charSequence.toString() : null);
        persistableBundle.putString(URI_KEY, this.f2470c);
        persistableBundle.putString(KEY_KEY, this.f2471d);
        persistableBundle.putBoolean(IS_BOT_KEY, this.f2472e);
        persistableBundle.putBoolean(IS_IMPORTANT_KEY, this.f2473f);
        return persistableBundle;
    }
}
