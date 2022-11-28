package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class KeyFrames {
    private static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    private static final String CUSTOM_METHOD = "CustomMethod";
    private static final String TAG = "KeyFrames";
    public static final int UNSET = -1;

    /* renamed from: a  reason: collision with root package name */
    static HashMap f2046a;
    private HashMap<Integer, ArrayList<Key>> mFramesMap = new HashMap<>();

    static {
        HashMap hashMap = new HashMap();
        f2046a = hashMap;
        try {
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            f2046a.put(TypedValues.Position.NAME, KeyPosition.class.getConstructor(new Class[0]));
            f2046a.put(TypedValues.Cycle.NAME, KeyCycle.class.getConstructor(new Class[0]));
            f2046a.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            f2046a.put(TypedValues.Trigger.NAME, KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "unable to load", e2);
        }
    }

    public KeyFrames() {
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        Key key;
        Exception e2;
        Constructor constructor;
        Key key2 = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (f2046a.containsKey(name)) {
                        try {
                            constructor = (Constructor) f2046a.get(name);
                        } catch (Exception e3) {
                            key = key2;
                            e2 = e3;
                        }
                        if (constructor == null) {
                            throw new NullPointerException("Keymaker for " + name + " not found");
                            break;
                        }
                        key = (Key) constructor.newInstance(new Object[0]);
                        try {
                            key.load(context, Xml.asAttributeSet(xmlPullParser));
                            addKey(key);
                        } catch (Exception e4) {
                            e2 = e4;
                            Log.e(TAG, "unable to create ", e2);
                            key2 = key;
                            continue;
                            eventType = xmlPullParser.next();
                        }
                        key2 = key;
                        continue;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (key2 == null) {
                            continue;
                        } else {
                            HashMap hashMap = key2.f2045e;
                            if (hashMap == null) {
                                continue;
                            }
                            ConstraintAttribute.parse(context, xmlPullParser, hashMap);
                            continue;
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && key2 != null && (hashMap = key2.f2045e) != null) {
                        ConstraintAttribute.parse(context, xmlPullParser, hashMap);
                        continue;
                    }
                } else if (eventType == 3 && ViewTransition.KEY_FRAME_SET_TAG.equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        } catch (XmlPullParserException e6) {
            e6.printStackTrace();
        }
    }

    public void addAllFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.mFramesMap.get(-1);
        if (arrayList != null) {
            motionController.a(arrayList);
        }
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.mFramesMap.get(Integer.valueOf(motionController.f2070c));
        if (arrayList != null) {
            motionController.a(arrayList);
        }
        ArrayList<Key> arrayList2 = this.mFramesMap.get(-1);
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            while (it.hasNext()) {
                Key next = it.next();
                if (next.a(((ConstraintLayout.LayoutParams) motionController.f2069b.getLayoutParams()).constraintTag)) {
                    motionController.addKey(next);
                }
            }
        }
    }

    public void addKey(Key key) {
        if (!this.mFramesMap.containsKey(Integer.valueOf(key.f2042b))) {
            this.mFramesMap.put(Integer.valueOf(key.f2042b), new ArrayList<>());
        }
        ArrayList<Key> arrayList = this.mFramesMap.get(Integer.valueOf(key.f2042b));
        if (arrayList != null) {
            arrayList.add(key);
        }
    }

    public ArrayList<Key> getKeyFramesForView(int i2) {
        return this.mFramesMap.get(Integer.valueOf(i2));
    }

    public Set<Integer> getKeys() {
        return this.mFramesMap.keySet();
    }
}
