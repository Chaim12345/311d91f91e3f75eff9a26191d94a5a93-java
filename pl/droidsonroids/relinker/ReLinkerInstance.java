package pl.droidsonroids.relinker;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import pl.droidsonroids.relinker.ReLinker;
import pl.droidsonroids.relinker.elf.ElfParser;
/* loaded from: classes4.dex */
public class ReLinkerInstance {
    private static final String LIB_DIR = "lib";

    /* renamed from: a  reason: collision with root package name */
    protected final Set f15279a;

    /* renamed from: b  reason: collision with root package name */
    protected final ReLinker.LibraryLoader f15280b;

    /* renamed from: c  reason: collision with root package name */
    protected final ReLinker.LibraryInstaller f15281c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f15282d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f15283e;

    /* renamed from: f  reason: collision with root package name */
    protected ReLinker.Logger f15284f;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReLinkerInstance() {
        this(new SystemLibraryLoader(), new ApkLibraryInstaller());
    }

    protected ReLinkerInstance(ReLinker.LibraryLoader libraryLoader, ReLinker.LibraryInstaller libraryInstaller) {
        this.f15279a = new HashSet();
        if (libraryLoader == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        }
        if (libraryInstaller == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
        this.f15280b = libraryLoader;
        this.f15281c = libraryInstaller;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadLibraryInternal(Context context, String str, String str2) {
        if (this.f15279a.contains(str) && !this.f15282d) {
            log("%s already loaded previously!", str);
            return;
        }
        try {
            this.f15280b.loadLibrary(str);
            this.f15279a.add(str);
            log("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e2) {
            log("Loading the library normally failed: %s", Log.getStackTraceString(e2));
            log("%s (%s) was not loaded normally, re-linking...", str, str2);
            File d2 = d(context, str, str2);
            if (!d2.exists() || this.f15282d) {
                if (this.f15282d) {
                    log("Forcing a re-link of %s (%s)...", str, str2);
                }
                b(context, str, str2);
                this.f15281c.installLibrary(context, this.f15280b.supportedAbis(), this.f15280b.mapLibraryName(str), d2, this);
            }
            try {
                if (this.f15283e) {
                    ElfParser elfParser = null;
                    try {
                        ElfParser elfParser2 = new ElfParser(d2);
                        try {
                            List<String> parseNeededDependencies = elfParser2.parseNeededDependencies();
                            elfParser2.close();
                            for (String str3 : parseNeededDependencies) {
                                loadLibrary(context, this.f15280b.unmapLibraryName(str3));
                            }
                        } catch (Throwable th) {
                            th = th;
                            elfParser = elfParser2;
                            elfParser.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException unused) {
            }
            this.f15280b.loadPath(d2.getAbsolutePath());
            this.f15279a.add(str);
            log("%s (%s) was re-linked!", str, str2);
        }
    }

    protected void b(Context context, String str, String str2) {
        File c2 = c(context);
        File d2 = d(context, str, str2);
        final String mapLibraryName = this.f15280b.mapLibraryName(str);
        File[] listFiles = c2.listFiles(new FilenameFilter(this) { // from class: pl.droidsonroids.relinker.ReLinkerInstance.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str3) {
                return str3.startsWith(mapLibraryName);
            }
        });
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if (this.f15282d || !file.getAbsolutePath().equals(d2.getAbsolutePath())) {
                file.delete();
            }
        }
    }

    protected File c(Context context) {
        return context.getDir(LIB_DIR, 0);
    }

    protected File d(Context context, String str, String str2) {
        String mapLibraryName = this.f15280b.mapLibraryName(str);
        if (TextUtils.isEmpty(str2)) {
            return new File(c(context), mapLibraryName);
        }
        File c2 = c(context);
        return new File(c2, mapLibraryName + "." + str2);
    }

    public ReLinkerInstance force() {
        this.f15282d = true;
        return this;
    }

    public void loadLibrary(Context context, String str) {
        loadLibrary(context, str, null, null);
    }

    public void loadLibrary(Context context, String str, String str2) {
        loadLibrary(context, str, str2, null);
    }

    public void loadLibrary(final Context context, final String str, final String str2, final ReLinker.LoadListener loadListener) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        log("Beginning load of %s...", str);
        if (loadListener == null) {
            loadLibraryInternal(context, str, str2);
        } else {
            new Thread(new Runnable() { // from class: pl.droidsonroids.relinker.ReLinkerInstance.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ReLinkerInstance.this.loadLibraryInternal(context, str, str2);
                        loadListener.success();
                    } catch (UnsatisfiedLinkError | MissingLibraryException e2) {
                        loadListener.failure(e2);
                    }
                }
            }).start();
        }
    }

    public void loadLibrary(Context context, String str, ReLinker.LoadListener loadListener) {
        loadLibrary(context, str, null, loadListener);
    }

    public ReLinkerInstance log(ReLinker.Logger logger) {
        this.f15284f = logger;
        return this;
    }

    public void log(String str) {
        ReLinker.Logger logger = this.f15284f;
        if (logger != null) {
            logger.log(str);
        }
    }

    public void log(String str, Object... objArr) {
        log(String.format(Locale.US, str, objArr));
    }

    public ReLinkerInstance recursively() {
        this.f15283e = true;
        return this;
    }
}
