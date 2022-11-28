package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ConstraintLayoutStates {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";

    /* renamed from: a  reason: collision with root package name */
    ConstraintSet f2281a;
    private final ConstraintLayout mConstraintLayout;

    /* renamed from: b  reason: collision with root package name */
    int f2282b = -1;

    /* renamed from: c  reason: collision with root package name */
    int f2283c = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class State {

        /* renamed from: a  reason: collision with root package name */
        int f2284a;

        /* renamed from: b  reason: collision with root package name */
        ArrayList f2285b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        int f2286c;

        /* renamed from: d  reason: collision with root package name */
        ConstraintSet f2287d;

        public State(Context context, XmlPullParser xmlPullParser) {
            this.f2286c = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.f2284a = obtainStyledAttributes.getResourceId(index, this.f2284a);
                } else if (index == R.styleable.State_constraints) {
                    this.f2286c = obtainStyledAttributes.getResourceId(index, this.f2286c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2286c);
                    context.getResources().getResourceName(this.f2286c);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f2287d = constraintSet;
                        constraintSet.clone(context, this.f2286c);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        void a(Variant variant) {
            this.f2285b.add(variant);
        }

        public int findMatch(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2285b.size(); i2++) {
                if (((Variant) this.f2285b.get(i2)).a(f2, f3)) {
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
        float f2288a;

        /* renamed from: b  reason: collision with root package name */
        float f2289b;

        /* renamed from: c  reason: collision with root package name */
        float f2290c;

        /* renamed from: d  reason: collision with root package name */
        float f2291d;

        /* renamed from: e  reason: collision with root package name */
        int f2292e;

        /* renamed from: f  reason: collision with root package name */
        ConstraintSet f2293f;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.f2288a = Float.NaN;
            this.f2289b = Float.NaN;
            this.f2290c = Float.NaN;
            this.f2291d = Float.NaN;
            this.f2292e = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.f2292e = obtainStyledAttributes.getResourceId(index, this.f2292e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2292e);
                    context.getResources().getResourceName(this.f2292e);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f2293f = constraintSet;
                        constraintSet.clone(context, this.f2292e);
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f2291d = obtainStyledAttributes.getDimension(index, this.f2291d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.f2289b = obtainStyledAttributes.getDimension(index, this.f2289b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f2290c = obtainStyledAttributes.getDimension(index, this.f2290c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f2288a = obtainStyledAttributes.getDimension(index, this.f2288a);
                }
            }
            obtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (Float.isNaN(this.f2288a) || f2 >= this.f2288a) {
                if (Float.isNaN(this.f2289b) || f3 >= this.f2289b) {
                    if (Float.isNaN(this.f2290c) || f2 <= this.f2290c) {
                        return Float.isNaN(this.f2291d) || f3 <= this.f2291d;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i2) {
        this.mConstraintLayout = constraintLayout;
        load(context, i2);
    }

    private void load(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        State state = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                boolean z = true;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                    continue;
                } else if (eventType != 2) {
                    continue;
                } else {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                z = true;
                                break;
                            }
                            z = true;
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                z = true;
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
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                z = false;
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
                        state = new State(context, xml);
                        this.mStateList.put(state.f2284a, state);
                        continue;
                    } else if (z) {
                        Variant variant = new Variant(context, xml);
                        if (state != null) {
                            state.a(variant);
                            continue;
                        } else {
                            continue;
                        }
                    } else if (!z) {
                        continue;
                    } else {
                        parseConstraintSet(context, xml);
                        continue;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    private void parseConstraintSet(Context context, XmlPullParser xmlPullParser) {
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (attributeName != null && attributeValue != null && "id".equals(attributeName)) {
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                constraintSet.load(context, xmlPullParser);
                this.mConstraintSetMap.put(identifier, constraintSet);
                return;
            }
        }
    }

    public boolean needsToChange(int i2, float f2, float f3) {
        int i3 = this.f2282b;
        if (i3 != i2) {
            return true;
        }
        State valueAt = i2 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i3);
        int i4 = this.f2283c;
        return (i4 == -1 || !((Variant) valueAt.f2285b.get(i4)).a(f2, f3)) && this.f2283c != valueAt.findMatch(f2, f3);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public void updateConstraints(int i2, float f2, float f3) {
        int findMatch;
        int i3 = this.f2282b;
        if (i3 == i2) {
            State valueAt = i2 == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i3);
            int i4 = this.f2283c;
            if ((i4 == -1 || !((Variant) valueAt.f2285b.get(i4)).a(f2, f3)) && this.f2283c != (findMatch = valueAt.findMatch(f2, f3))) {
                ConstraintSet constraintSet = findMatch == -1 ? this.f2281a : ((Variant) valueAt.f2285b.get(findMatch)).f2293f;
                int i5 = findMatch == -1 ? valueAt.f2286c : ((Variant) valueAt.f2285b.get(findMatch)).f2292e;
                if (constraintSet == null) {
                    return;
                }
                this.f2283c = findMatch;
                ConstraintsChangedListener constraintsChangedListener = this.mConstraintsChangedListener;
                if (constraintsChangedListener != null) {
                    constraintsChangedListener.preLayoutChange(-1, i5);
                }
                constraintSet.applyTo(this.mConstraintLayout);
                ConstraintsChangedListener constraintsChangedListener2 = this.mConstraintsChangedListener;
                if (constraintsChangedListener2 != null) {
                    constraintsChangedListener2.postLayoutChange(-1, i5);
                    return;
                }
                return;
            }
            return;
        }
        this.f2282b = i2;
        State state = this.mStateList.get(i2);
        int findMatch2 = state.findMatch(f2, f3);
        ConstraintSet constraintSet2 = findMatch2 == -1 ? state.f2287d : ((Variant) state.f2285b.get(findMatch2)).f2293f;
        int i6 = findMatch2 == -1 ? state.f2286c : ((Variant) state.f2285b.get(findMatch2)).f2292e;
        if (constraintSet2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("NO Constraint set found ! id=");
            sb.append(i2);
            sb.append(", dim =");
            sb.append(f2);
            sb.append(", ");
            sb.append(f3);
            return;
        }
        this.f2283c = findMatch2;
        ConstraintsChangedListener constraintsChangedListener3 = this.mConstraintsChangedListener;
        if (constraintsChangedListener3 != null) {
            constraintsChangedListener3.preLayoutChange(i2, i6);
        }
        constraintSet2.applyTo(this.mConstraintLayout);
        ConstraintsChangedListener constraintsChangedListener4 = this.mConstraintsChangedListener;
        if (constraintsChangedListener4 != null) {
            constraintsChangedListener4.postLayoutChange(i2, i6);
        }
    }
}
