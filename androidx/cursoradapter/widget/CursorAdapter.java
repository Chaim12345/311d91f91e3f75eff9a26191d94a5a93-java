package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import androidx.annotation.RestrictTo;
import androidx.cursoradapter.widget.CursorFilter;
/* loaded from: classes.dex */
public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: a  reason: collision with root package name */
    protected boolean f2749a;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: b  reason: collision with root package name */
    protected boolean f2750b;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: c  reason: collision with root package name */
    protected Cursor f2751c;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: d  reason: collision with root package name */
    protected Context f2752d;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: e  reason: collision with root package name */
    protected int f2753e;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: f  reason: collision with root package name */
    protected ChangeObserver f2754f;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: g  reason: collision with root package name */
    protected DataSetObserver f2755g;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: h  reason: collision with root package name */
    protected CursorFilter f2756h;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})

    /* renamed from: i  reason: collision with root package name */
    protected FilterQueryProvider f2757i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ChangeObserver extends ContentObserver {
        ChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            CursorAdapter.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyDataSetObserver extends DataSetObserver {
        MyDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.f2749a = true;
            cursorAdapter.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.f2749a = false;
            cursorAdapter.notifyDataSetInvalidated();
        }
    }

    @Deprecated
    public CursorAdapter(Context context, Cursor cursor) {
        a(context, cursor, 1);
    }

    public CursorAdapter(Context context, Cursor cursor, int i2) {
        a(context, cursor, i2);
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    void a(Context context, Cursor cursor, int i2) {
        MyDataSetObserver myDataSetObserver;
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.f2750b = true;
        } else {
            this.f2750b = false;
        }
        boolean z = cursor != null;
        this.f2751c = cursor;
        this.f2749a = z;
        this.f2752d = context;
        this.f2753e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f2754f = new ChangeObserver();
            myDataSetObserver = new MyDataSetObserver();
        } else {
            myDataSetObserver = null;
            this.f2754f = null;
        }
        this.f2755g = myDataSetObserver;
        if (z) {
            ChangeObserver changeObserver = this.f2754f;
            if (changeObserver != null) {
                cursor.registerContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.f2755g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    protected void b() {
        Cursor cursor;
        if (!this.f2750b || (cursor = this.f2751c) == null || cursor.isClosed()) {
            return;
        }
        this.f2749a = this.f2751c.requery();
    }

    public abstract void bindView(View view, Context context, Cursor cursor);

    public void changeCursor(Cursor cursor) {
        Cursor swapCursor = swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f2749a || (cursor = this.f2751c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public Cursor getCursor() {
        return this.f2751c;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (this.f2749a) {
            this.f2751c.moveToPosition(i2);
            if (view == null) {
                view = newDropDownView(this.f2752d, this.f2751c, viewGroup);
            }
            bindView(view, this.f2752d, this.f2751c);
            return view;
        }
        return null;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f2756h == null) {
            this.f2756h = new CursorFilter(this);
        }
        return this.f2756h;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.f2757i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f2749a || (cursor = this.f2751c) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f2751c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.f2749a && (cursor = this.f2751c) != null && cursor.moveToPosition(i2)) {
            return this.f2751c.getLong(this.f2753e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (this.f2749a) {
            if (this.f2751c.moveToPosition(i2)) {
                if (view == null) {
                    view = newView(this.f2752d, this.f2751c, viewGroup);
                }
                bindView(view, this.f2752d, this.f2751c);
                return view;
            }
            throw new IllegalStateException("couldn't move cursor to position " + i2);
        }
        throw new IllegalStateException("this should only be called when the cursor is valid");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return newView(context, cursor, viewGroup);
    }

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.f2757i;
        return filterQueryProvider != null ? filterQueryProvider.runQuery(charSequence) : this.f2751c;
    }

    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        this.f2757i = filterQueryProvider;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.f2751c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            ChangeObserver changeObserver = this.f2754f;
            if (changeObserver != null) {
                cursor2.unregisterContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.f2755g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2751c = cursor;
        if (cursor != null) {
            ChangeObserver changeObserver2 = this.f2754f;
            if (changeObserver2 != null) {
                cursor.registerContentObserver(changeObserver2);
            }
            DataSetObserver dataSetObserver2 = this.f2755g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f2753e = cursor.getColumnIndexOrThrow("_id");
            this.f2749a = true;
            notifyDataSetChanged();
        } else {
            this.f2753e = -1;
            this.f2749a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }
}
