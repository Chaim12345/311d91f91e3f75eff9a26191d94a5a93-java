package org.hamcrest.io;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.io.IOException;
import org.apache.http.cookie.ClientCookie;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public final class FileMatchers {
    public static final FileStatus CAN_WRITE = new FileStatus() { // from class: org.hamcrest.io.FileMatchers.5
        @Override // org.hamcrest.io.FileMatchers.FileStatus
        public boolean check(File file) {
            return file.canWrite();
        }
    };
    public static final FileStatus CAN_READ = new FileStatus() { // from class: org.hamcrest.io.FileMatchers.6
        @Override // org.hamcrest.io.FileMatchers.FileStatus
        public boolean check(File file) {
            return file.canRead();
        }
    };
    public static final FileStatus IS_FILE = new FileStatus() { // from class: org.hamcrest.io.FileMatchers.7
        @Override // org.hamcrest.io.FileMatchers.FileStatus
        public boolean check(File file) {
            return file.isFile();
        }
    };
    public static final FileStatus IS_DIRECTORY = new FileStatus() { // from class: org.hamcrest.io.FileMatchers.8
        @Override // org.hamcrest.io.FileMatchers.FileStatus
        public boolean check(File file) {
            return file.isDirectory();
        }
    };
    public static final FileStatus EXISTS = new FileStatus() { // from class: org.hamcrest.io.FileMatchers.9
        @Override // org.hamcrest.io.FileMatchers.FileStatus
        public boolean check(File file) {
            return file.exists();
        }
    };

    /* loaded from: classes4.dex */
    public interface FileStatus {
        boolean check(File file);
    }

    public static Matcher<File> aFileNamed(Matcher<String> matcher) {
        return new FeatureMatcher<File, String>(matcher, "A file with name", AppMeasurementSdk.ConditionalUserProperty.NAME) { // from class: org.hamcrest.io.FileMatchers.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.hamcrest.FeatureMatcher
            /* renamed from: c */
            public String b(File file) {
                return file.getName();
            }
        };
    }

    public static Matcher<File> aFileWithAbsolutePath(Matcher<String> matcher) {
        return new FeatureMatcher<File, String>(matcher, "A file with absolute path", ClientCookie.PATH_ATTR) { // from class: org.hamcrest.io.FileMatchers.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.hamcrest.FeatureMatcher
            /* renamed from: c */
            public String b(File file) {
                return file.getAbsolutePath();
            }
        };
    }

    public static Matcher<File> aFileWithCanonicalPath(Matcher<String> matcher) {
        return new FeatureMatcher<File, String>(matcher, "A file with canonical path", ClientCookie.PATH_ATTR) { // from class: org.hamcrest.io.FileMatchers.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.hamcrest.FeatureMatcher
            /* renamed from: c */
            public String b(File file) {
                try {
                    return file.getCanonicalPath();
                } catch (IOException e2) {
                    return "Exception: " + e2.getMessage();
                }
            }
        };
    }

    public static Matcher<File> aFileWithSize(long j2) {
        return aFileWithSize(IsEqual.equalTo(Long.valueOf(j2)));
    }

    public static Matcher<File> aFileWithSize(Matcher<Long> matcher) {
        return new FeatureMatcher<File, Long>(matcher, "A file with size", "size") { // from class: org.hamcrest.io.FileMatchers.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.hamcrest.FeatureMatcher
            /* renamed from: c */
            public Long b(File file) {
                return Long.valueOf(file.length());
            }
        };
    }

    public static Matcher<File> aReadableFile() {
        return fileChecker(CAN_READ, "a readable File", "cannot be read");
    }

    public static Matcher<File> aWritableFile() {
        return fileChecker(CAN_WRITE, "a writable File", "cannot be written to");
    }

    public static Matcher<File> anExistingDirectory() {
        return fileChecker(IS_DIRECTORY, "an existing directory", "is not a directory");
    }

    public static Matcher<File> anExistingFile() {
        return fileChecker(IS_FILE, "an existing File", "is not a file");
    }

    public static Matcher<File> anExistingFileOrDirectory() {
        return fileChecker(EXISTS, "an existing file or directory", "does not exist");
    }

    private static Matcher<File> fileChecker(final FileStatus fileStatus, final String str, final String str2) {
        return new TypeSafeDiagnosingMatcher<File>() { // from class: org.hamcrest.io.FileMatchers.10
            @Override // org.hamcrest.SelfDescribing
            public void describeTo(Description description) {
                description.appendText(str);
            }

            @Override // org.hamcrest.TypeSafeDiagnosingMatcher
            public boolean matchesSafely(File file, Description description) {
                boolean check = FileStatus.this.check(file);
                if (!check) {
                    description.appendText(str2);
                }
                return check;
            }
        };
    }
}
