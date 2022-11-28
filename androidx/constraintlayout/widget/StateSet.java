package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class StateSet {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";

    /* renamed from: a  reason: collision with root package name */
    int f2322a = -1;

    /* renamed from: b  reason: collision with root package name */
    int f2323b = -1;

    /* renamed from: c  reason: collision with root package name */
    int f2324c = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class State {

        /* renamed from: a  reason: collision with root package name */
        int f2325a;

        /* renamed from: b  reason: collision with root package name */
        ArrayList f2326b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        int f2327c;

        public State(Context context, XmlPullParser xmlPullParser) {
            this.f2327c = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.f2325a = obtainStyledAttributes.getResourceId(index, this.f2325a);
                } else if (index == R.styleable.State_constraints) {
                    this.f2327c = obtainStyledAttributes.getResourceId(index, this.f2327c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2327c);
                    context.getResources().getResourceName(this.f2327c);
                    "layout".equals(resourceTypeName);
                }
            }
            obtainStyledAttributes.recycle();
        }

        void a(Variant variant) {
            this.f2326b.add(variant);
        }

        public int findMatch(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2326b.size(); i2++) {
                if (((Variant) this.f2326b.get(i2)).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Variant {

        /* renamed from: a  reason: collision with root package name */
        float f2328a;

        /* renamed from: b  reason: collision with root package name */
        float f2329b;

        /* renamed from: c  reason: collision with root package name */
        float f2330c;

        /* renamed from: d  reason: collision with root package name */
        float f2331d;

        /* renamed from: e  reason: collision with root package name */
        int f2332e;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.f2328a = Float.NaN;
            this.f2329b = Float.NaN;
            this.f2330c = Float.NaN;
            this.f2331d = Float.NaN;
            this.f2332e = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.f2332e = obtainStyledAttributes.getResourceId(index, this.f2332e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2332e);
                    context.getResources().getResourceName(this.f2332e);
                    "layout".equals(resourceTypeName);
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f2331d = obtainStyledAttributes.getDimension(index, this.f2331d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.f2329b = obtainStyledAttributes.getDimension(index, this.f2329b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f2330c = obtainStyledAttributes.getDimension(index, this.f2330c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f2328a = obtainStyledAttributes.getDimension(index, this.f2328a);
                }
            }
            obtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (Float.isNaN(this.f2328a) || f2 >= this.f2328a) {
                if (Float.isNaN(this.f2329b) || f3 >= this.f2329b) {
                    if (Float.isNaN(this.f2330c) || f2 <= this.f2330c) {
                        return Float.isNaN(this.f2331d) || f3 <= this.f2331d;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        load(context, xmlPullParser);
    }

    private void load(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.StateSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.StateSet_defaultState) {
                this.f2322a = obtainStyledAttributes.getResourceId(index, this.f2322a);
            }
        }
        obtainStyledAttributes.recycle();
        State state = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (true) {
                boolean z = true;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                break;
                            }
                            z = true;
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        default:
                            z = true;
                            break;
                    }
                    if (z) {
                        state = new State(context, xmlPullParser);
                        this.mStateList.put(state.f2325a, state);
                    } else if (z) {
                        Variant variant = new Variant(context, xmlPullParser);
                        if (state != null) {
                            state.a(variant);
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public int convertToConstraintSet(int i2, int i3, float f2, float f3) {
        State state = this.mStateList.get(i3);
        if (state == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (state.f2327c == i2) {
                return i2;
            }
            Iterator it = state.f2326b.iterator();
            while (it.hasNext()) {
                if (i2 == ((Variant) it.next()).f2332e) {
                    return i2;
                }
            }
            return state.f2327c;
        }
        Variant variant = null;
        Iterator it2 = state.f2326b.iterator();
        while (it2.hasNext()) {
            Variant variant2 = (Variant) it2.next();
            if (variant2.a(f2, f3)) {
                if (i2 == variant2.f2332e) {
                    return i2;
                }
                variant = variant2;
            }
        }
        return variant != null ? variant.f2332e : state.f2327c;
    }

    public boolean needsToChange(int i2, float f2, float f3) {
        int i3 = this.f2323b;
        if (i3 != i2) {
            return true;
        }
        State valueAt = i2 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i3);
        int i4 = this.f2324c;
        return (i4 == -1 || !((Variant) valueAt.f2326b.get(i4)).a(f2, f3)) && this.f2324c != valueAt.findMatch(f2, f3);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public int stateGetConstraintID(int i2, int i3, int i4) {
        return updateConstraints(-1, i2, i3, i4);
    }

    public int updateConstraints(int i2, int i3, float f2, float f3) {
        int findMatch;
        if (i2 == i3) {
            State valueAt = i3 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(this.f2323b);
            if (valueAt == null) {
                return -1;
            }
            return ((this.f2324c == -1 || !((Variant) valueAt.f2326b.get(i2)).a(f2, f3)) && i2 != (findMatch = valueAt.findMatch(f2, f3))) ? findMatch == -1 ? valueAt.f2327c : ((Variant) valueAt.f2326b.get(findMatch)).f2332e : i2;
        }
        State state = this.mStateList.get(i3);
        if (state == null) {
            return -1;
        }
        int findMatch2 = state.findMatch(f2, f3);
        return findMatch2 == -1 ? state.f2327c : ((Variant) state.f2326b.get(findMatch2)).f2332e;
    }
}
