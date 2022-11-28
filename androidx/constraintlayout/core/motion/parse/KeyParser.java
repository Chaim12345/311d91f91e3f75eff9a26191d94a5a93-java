package androidx.constraintlayout.core.motion.parse;

import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParser;
import androidx.constraintlayout.core.parser.CLParsingException;
import java.io.PrintStream;
/* loaded from: classes.dex */
public class KeyParser {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface DataType {
        int get(int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface Ids {
        int get(String str);
    }

    public static void main(String[] strArr) {
        parseAttributes("{frame:22,\ntarget:'widget1',\neasing:'easeIn',\ncurveFit:'spline',\nprogress:0.3,\nalpha:0.2,\nelevation:0.7,\nrotationZ:23,\nrotationX:25.0,\nrotationY:27.0,\npivotX:15,\npivotY:17,\npivotTarget:'32',\npathRotate:23,\nscaleX:0.5,\nscaleY:0.7,\ntranslationX:5,\ntranslationY:7,\ntranslationZ:11,\n}");
    }

    private static TypedBundle parse(String str, Ids ids, DataType dataType) {
        PrintStream printStream;
        String str2;
        TypedBundle typedBundle = new TypedBundle();
        try {
            CLObject parse = CLParser.parse(str);
            int size = parse.size();
            for (int i2 = 0; i2 < size; i2++) {
                CLKey cLKey = (CLKey) parse.get(i2);
                String content = cLKey.content();
                CLElement value = cLKey.getValue();
                int i3 = ids.get(content);
                if (i3 == -1) {
                    System.err.println("unknown type " + content);
                } else {
                    int i4 = dataType.get(i3);
                    if (i4 != 1) {
                        if (i4 == 2) {
                            typedBundle.add(i3, value.getInt());
                            printStream = System.out;
                            str2 = "parse " + content + " INT_MASK > " + value.getInt();
                        } else if (i4 == 4) {
                            typedBundle.add(i3, value.getFloat());
                            printStream = System.out;
                            str2 = "parse " + content + " FLOAT_MASK > " + value.getFloat();
                        } else if (i4 == 8) {
                            typedBundle.add(i3, value.content());
                            printStream = System.out;
                            str2 = "parse " + content + " STRING_MASK > " + value.content();
                        }
                        printStream.println(str2);
                    } else {
                        typedBundle.add(i3, parse.getBoolean(i2));
                    }
                }
            }
        } catch (CLParsingException e2) {
            e2.printStackTrace();
        }
        return typedBundle;
    }

    public static TypedBundle parseAttributes(String str) {
        return parse(str, b.f1729a, a.f1728a);
    }
}
