package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {

    /* renamed from: o  reason: collision with root package name */
    final Loader.ForceLoadContentObserver f3285o;

    /* renamed from: p  reason: collision with root package name */
    Uri f3286p;

    /* renamed from: q  reason: collision with root package name */
    String[] f3287q;

    /* renamed from: r  reason: collision with root package name */
    String f3288r;

    /* renamed from: s  reason: collision with root package name */
    String[] f3289s;

    /* renamed from: t  reason: collision with root package name */
    String f3290t;
    Cursor u;
    CancellationSignal v;

    public CursorLoader(@NonNull Context context) {
        super(context);
        this.f3285o = new Loader.ForceLoadContentObserver();
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.f3285o = new Loader.ForceLoadContentObserver();
        this.f3286p = uri;
        this.f3287q = strArr;
        this.f3288r = str;
        this.f3289s = strArr2;
        this.f3290t = str2;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            CancellationSignal cancellationSignal = this.v;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void d() {
        super.d();
        f();
        Cursor cursor = this.u;
        if (cursor != null && !cursor.isClosed()) {
            this.u.close();
        }
        this.u = null;
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.u;
        this.u = cursor;
        if (isStarted()) {
            super.deliverResult((CursorLoader) cursor);
        }
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.f3286p);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.f3287q));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.f3288r);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.f3289s));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.f3290t);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.u);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.f3298h);
    }

    @Override // androidx.loader.content.Loader
    protected void e() {
        Cursor cursor = this.u;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.u == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void f() {
        cancelLoad();
    }

    @Nullable
    public String[] getProjection() {
        return this.f3287q;
    }

    @Nullable
    public String getSelection() {
        return this.f3288r;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.f3289s;
    }

    @Nullable
    public String getSortOrder() {
        return this.f3290t;
    }

    @NonNull
    public Uri getUri() {
        return this.f3286p;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.loader.content.AsyncTaskLoader
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            this.v = new CancellationSignal();
        }
        try {
            Cursor query = ContentResolverCompat.query(getContext().getContentResolver(), this.f3286p, this.f3287q, this.f3288r, this.f3289s, this.f3290t, this.v);
            if (query != null) {
                try {
                    query.getCount();
                    query.registerContentObserver(this.f3285o);
                } catch (RuntimeException e2) {
                    query.close();
                    throw e2;
                }
            }
            synchronized (this) {
                this.v = null;
            }
            return query;
        } catch (Throwable th) {
            synchronized (this) {
                this.v = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    public void setProjection(@Nullable String[] strArr) {
        this.f3287q = strArr;
    }

    public void setSelection(@Nullable String str) {
        this.f3288r = str;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.f3289s = strArr;
    }

    public void setSortOrder(@Nullable String str) {
        this.f3290t = str;
    }

    public void setUri(@NonNull Uri uri) {
        this.f3286p = uri;
    }
}
