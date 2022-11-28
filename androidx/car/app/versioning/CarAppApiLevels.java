package androidx.car.app.versioning;

import androidx.annotation.RestrictTo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CarAppApiLevels {
    private static final String CAR_API_LEVEL_FILE = "car-app-api.level";
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int UNKNOWN = 0;

    private CarAppApiLevels() {
    }

    public static int getLatest() {
        ClassLoader classLoader = CarAppApiLevels.class.getClassLoader();
        Objects.requireNonNull(classLoader);
        InputStream resourceAsStream = classLoader.getResourceAsStream(CAR_API_LEVEL_FILE);
        if (resourceAsStream != null) {
            try {
                String readLine = new BufferedReader(new InputStreamReader(resourceAsStream)).readLine();
                int parseInt = Integer.parseInt(readLine);
                if (parseInt != 0) {
                    if (parseInt != 1) {
                        if (parseInt != 2) {
                            if (parseInt == 3) {
                                return 3;
                            }
                            throw new IllegalStateException("Undefined Car API level: " + readLine);
                        }
                        return 2;
                    }
                    return 1;
                }
                return 0;
            } catch (IOException unused) {
                throw new IllegalStateException("Unable to read Car API level file");
            }
        }
        throw new IllegalStateException(String.format("Car API level file %s not found", CAR_API_LEVEL_FILE));
    }

    public static int getOldest() {
        return 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean isValid(int i2) {
        return i2 >= getOldest() && i2 <= getLatest();
    }
}
