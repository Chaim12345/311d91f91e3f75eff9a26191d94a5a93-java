package com.chuckerteam.chucker.internal.support;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import com.chuckerteam.chucker.R;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001c\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a/\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001aA\u0010\u000f\u001a\u0004\u0018\u00010\n*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/content/Context;", "context", "", "kotlin.jvm.PlatformType", "toSharableUtf8Content", "Landroid/app/Activity;", "activity", "intentTitle", "intentSubject", "Landroid/content/Intent;", "shareAsUtf8Text", "(Lcom/chuckerteam/chucker/internal/support/Sharable;Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fileName", "clipDataLabel", "shareAsFile", "(Lcom/chuckerteam/chucker/internal/support/Sharable;Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class SharableKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0110 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0111  */
    /* JADX WARN: Type inference failed for: r11v1, types: [T, java.io.File] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object shareAsFile(@NotNull Sharable sharable, @NotNull Activity activity, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull Continuation<? super Intent> continuation) {
        SharableKt$shareAsFile$1 sharableKt$shareAsFile$1;
        Object coroutine_suspended;
        int i2;
        String str5;
        String str6;
        Sharable sharable2;
        Ref.ObjectRef objectRef;
        String str7;
        Activity activity2;
        File file;
        String str8;
        Logger logger;
        String str9;
        CoroutineDispatcher io2;
        SharableKt$shareAsFile$2 sharableKt$shareAsFile$2;
        String str10;
        String str11;
        Activity activity3;
        String str12;
        if (continuation instanceof SharableKt$shareAsFile$1) {
            sharableKt$shareAsFile$1 = (SharableKt$shareAsFile$1) continuation;
            int i3 = sharableKt$shareAsFile$1.f4913b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                sharableKt$shareAsFile$1.f4913b = i3 - Integer.MIN_VALUE;
                Object obj = sharableKt$shareAsFile$1.f4912a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = sharableKt$shareAsFile$1.f4913b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    File cacheDir = activity.getCacheDir();
                    if (cacheDir == null) {
                        logger = Logger.INSTANCE;
                        str9 = "Failed to obtain a valid cache directory for Chucker file export";
                    } else {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        ?? create = FileFactory.INSTANCE.create(cacheDir, str);
                        objectRef2.element = create;
                        if (((File) create) == null) {
                            logger = Logger.INSTANCE;
                            str9 = "Failed to create an export file for Chucker";
                        } else {
                            CoroutineDispatcher coroutineDispatcher = Dispatchers.getDefault();
                            SharableKt$shareAsFile$fileContent$1 sharableKt$shareAsFile$fileContent$1 = new SharableKt$shareAsFile$fileContent$1(sharable, activity, null);
                            sharableKt$shareAsFile$1.f4914c = sharable;
                            sharableKt$shareAsFile$1.f4915d = activity;
                            sharableKt$shareAsFile$1.f4916e = str;
                            sharableKt$shareAsFile$1.f4917f = str2;
                            sharableKt$shareAsFile$1.f4918g = str3;
                            str5 = str4;
                            sharableKt$shareAsFile$1.f4919h = str5;
                            sharableKt$shareAsFile$1.f4920i = cacheDir;
                            sharableKt$shareAsFile$1.f4921j = objectRef2;
                            sharableKt$shareAsFile$1.f4913b = 1;
                            Object withContext = BuildersKt.withContext(coroutineDispatcher, sharableKt$shareAsFile$fileContent$1, sharableKt$shareAsFile$1);
                            if (withContext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str6 = str;
                            sharable2 = sharable;
                            objectRef = objectRef2;
                            str7 = str2;
                            activity2 = activity;
                            file = cacheDir;
                            obj = withContext;
                            str8 = str3;
                        }
                    }
                    logger.warn(str9);
                    Toast.makeText(activity, R.string.chucker_export_no_file, 0).show();
                    return null;
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        Source source = (Source) sharableKt$shareAsFile$1.f4922k;
                        objectRef = (Ref.ObjectRef) sharableKt$shareAsFile$1.f4921j;
                        File file2 = (File) sharableKt$shareAsFile$1.f4920i;
                        str12 = (String) sharableKt$shareAsFile$1.f4919h;
                        str10 = (String) sharableKt$shareAsFile$1.f4918g;
                        str11 = (String) sharableKt$shareAsFile$1.f4917f;
                        String str13 = (String) sharableKt$shareAsFile$1.f4916e;
                        activity3 = (Activity) sharableKt$shareAsFile$1.f4915d;
                        Sharable sharable3 = (Sharable) sharableKt$shareAsFile$1.f4914c;
                        ResultKt.throwOnFailure(obj);
                        Uri uriForFile = FileProvider.getUriForFile(activity3, activity3.getPackageName() + ".com.chuckerteam.chucker.provider", (File) objectRef.element);
                        ShareCompat.IntentBuilder stream = ShareCompat.IntentBuilder.from(activity3).setType(activity3.getContentResolver().getType(uriForFile)).setChooserTitle(str11).setSubject(str10).setStream(uriForFile);
                        Intrinsics.checkNotNullExpressionValue(stream, "ShareCompat.IntentBuilde…)\n        .setStream(uri)");
                        Intent intent = stream.getIntent();
                        Intrinsics.checkNotNullExpressionValue(intent, "ShareCompat.IntentBuilde…ream(uri)\n        .intent");
                        intent.setClipData(ClipData.newRawUri(str12, uriForFile));
                        intent.addFlags(1);
                        return Intent.createChooser(intent, str11);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    objectRef = (Ref.ObjectRef) sharableKt$shareAsFile$1.f4921j;
                    file = (File) sharableKt$shareAsFile$1.f4920i;
                    str8 = (String) sharableKt$shareAsFile$1.f4918g;
                    str7 = (String) sharableKt$shareAsFile$1.f4917f;
                    str6 = (String) sharableKt$shareAsFile$1.f4916e;
                    activity2 = (Activity) sharableKt$shareAsFile$1.f4915d;
                    sharable2 = (Sharable) sharableKt$shareAsFile$1.f4914c;
                    ResultKt.throwOnFailure(obj);
                    str5 = (String) sharableKt$shareAsFile$1.f4919h;
                }
                Source source2 = (Source) obj;
                io2 = Dispatchers.getIO();
                sharableKt$shareAsFile$2 = new SharableKt$shareAsFile$2(objectRef, source2, null);
                sharableKt$shareAsFile$1.f4914c = sharable2;
                sharableKt$shareAsFile$1.f4915d = activity2;
                sharableKt$shareAsFile$1.f4916e = str6;
                sharableKt$shareAsFile$1.f4917f = str7;
                sharableKt$shareAsFile$1.f4918g = str8;
                sharableKt$shareAsFile$1.f4919h = str5;
                sharableKt$shareAsFile$1.f4920i = file;
                sharableKt$shareAsFile$1.f4921j = objectRef;
                sharableKt$shareAsFile$1.f4922k = source2;
                sharableKt$shareAsFile$1.f4913b = 2;
                if (BuildersKt.withContext(io2, sharableKt$shareAsFile$2, sharableKt$shareAsFile$1) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                str10 = str8;
                str11 = str7;
                activity3 = activity2;
                str12 = str5;
                Uri uriForFile2 = FileProvider.getUriForFile(activity3, activity3.getPackageName() + ".com.chuckerteam.chucker.provider", (File) objectRef.element);
                ShareCompat.IntentBuilder stream2 = ShareCompat.IntentBuilder.from(activity3).setType(activity3.getContentResolver().getType(uriForFile2)).setChooserTitle(str11).setSubject(str10).setStream(uriForFile2);
                Intrinsics.checkNotNullExpressionValue(stream2, "ShareCompat.IntentBuilde…)\n        .setStream(uri)");
                Intent intent2 = stream2.getIntent();
                Intrinsics.checkNotNullExpressionValue(intent2, "ShareCompat.IntentBuilde…ream(uri)\n        .intent");
                intent2.setClipData(ClipData.newRawUri(str12, uriForFile2));
                intent2.addFlags(1);
                return Intent.createChooser(intent2, str11);
            }
        }
        sharableKt$shareAsFile$1 = new SharableKt$shareAsFile$1(continuation);
        Object obj2 = sharableKt$shareAsFile$1.f4912a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = sharableKt$shareAsFile$1.f4913b;
        if (i2 != 0) {
        }
        Source source22 = (Source) obj2;
        io2 = Dispatchers.getIO();
        sharableKt$shareAsFile$2 = new SharableKt$shareAsFile$2(objectRef, source22, null);
        sharableKt$shareAsFile$1.f4914c = sharable2;
        sharableKt$shareAsFile$1.f4915d = activity2;
        sharableKt$shareAsFile$1.f4916e = str6;
        sharableKt$shareAsFile$1.f4917f = str7;
        sharableKt$shareAsFile$1.f4918g = str8;
        sharableKt$shareAsFile$1.f4919h = str5;
        sharableKt$shareAsFile$1.f4920i = file;
        sharableKt$shareAsFile$1.f4921j = objectRef;
        sharableKt$shareAsFile$1.f4922k = source22;
        sharableKt$shareAsFile$1.f4913b = 2;
        if (BuildersKt.withContext(io2, sharableKt$shareAsFile$2, sharableKt$shareAsFile$1) != coroutine_suspended) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object shareAsUtf8Text(@NotNull Sharable sharable, @NotNull Activity activity, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super Intent> continuation) {
        SharableKt$shareAsUtf8Text$1 sharableKt$shareAsUtf8Text$1;
        Object coroutine_suspended;
        int i2;
        if (continuation instanceof SharableKt$shareAsUtf8Text$1) {
            sharableKt$shareAsUtf8Text$1 = (SharableKt$shareAsUtf8Text$1) continuation;
            int i3 = sharableKt$shareAsUtf8Text$1.f4930b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                sharableKt$shareAsUtf8Text$1.f4930b = i3 - Integer.MIN_VALUE;
                Object obj = sharableKt$shareAsUtf8Text$1.f4929a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = sharableKt$shareAsUtf8Text$1.f4930b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getDefault();
                    SharableKt$shareAsUtf8Text$content$1 sharableKt$shareAsUtf8Text$content$1 = new SharableKt$shareAsUtf8Text$content$1(sharable, activity, null);
                    sharableKt$shareAsUtf8Text$1.f4931c = sharable;
                    sharableKt$shareAsUtf8Text$1.f4932d = activity;
                    sharableKt$shareAsUtf8Text$1.f4933e = str;
                    sharableKt$shareAsUtf8Text$1.f4934f = str2;
                    sharableKt$shareAsUtf8Text$1.f4930b = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, sharableKt$shareAsUtf8Text$content$1, sharableKt$shareAsUtf8Text$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    str2 = (String) sharableKt$shareAsUtf8Text$1.f4934f;
                    str = (String) sharableKt$shareAsUtf8Text$1.f4933e;
                    activity = (Activity) sharableKt$shareAsUtf8Text$1.f4932d;
                    Sharable sharable2 = (Sharable) sharableKt$shareAsUtf8Text$1.f4931c;
                    ResultKt.throwOnFailure(obj);
                }
                Intent createChooserIntent = ShareCompat.IntentBuilder.from(activity).setType(HTTP.PLAIN_TEXT_TYPE).setChooserTitle(str).setSubject(str2).setText((String) obj).createChooserIntent();
                Intrinsics.checkNotNullExpressionValue(createChooserIntent, "ShareCompat.IntentBuilde…   .createChooserIntent()");
                return createChooserIntent;
            }
        }
        sharableKt$shareAsUtf8Text$1 = new SharableKt$shareAsUtf8Text$1(continuation);
        Object obj2 = sharableKt$shareAsUtf8Text$1.f4929a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = sharableKt$shareAsUtf8Text$1.f4930b;
        if (i2 != 0) {
        }
        Intent createChooserIntent2 = ShareCompat.IntentBuilder.from(activity).setType(HTTP.PLAIN_TEXT_TYPE).setChooserTitle(str).setSubject(str2).setText((String) obj2).createChooserIntent();
        Intrinsics.checkNotNullExpressionValue(createChooserIntent2, "ShareCompat.IntentBuilde…   .createChooserIntent()");
        return createChooserIntent2;
    }

    public static final String toSharableUtf8Content(@NotNull Sharable toSharableUtf8Content, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(toSharableUtf8Content, "$this$toSharableUtf8Content");
        Intrinsics.checkNotNullParameter(context, "context");
        BufferedSource buffer = Okio.buffer(toSharableUtf8Content.toSharableContent(context));
        try {
            String readUtf8 = buffer.readUtf8();
            CloseableKt.closeFinally(buffer, null);
            return readUtf8;
        } finally {
        }
    }
}
