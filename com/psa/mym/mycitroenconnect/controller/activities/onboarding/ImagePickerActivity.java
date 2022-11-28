package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.ImagePickerActivity;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import org.apache.http.cookie.ClientCookie;
import org.bouncycastle.tls.CipherSuite;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public class ImagePickerActivity extends BaseActivity {
    public static final String INTENT_ASPECT_RATIO_X = "aspect_ratio_x";
    public static final String INTENT_ASPECT_RATIO_Y = "aspect_ratio_Y";
    public static final String INTENT_BITMAP_MAX_HEIGHT = "max_height";
    public static final String INTENT_BITMAP_MAX_WIDTH = "max_width";
    public static final String INTENT_IMAGE_COMPRESSION_QUALITY = "compression_quality";
    public static final String INTENT_IMAGE_PICKER_OPTION = "image_picker_option";
    public static final String INTENT_LOCK_ASPECT_RATIO = "lock_aspect_ratio";
    public static final String INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT = "set_bitmap_max_width_height";
    public static final int REQUEST_GALLERY_IMAGE = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final String TAG = ImagePickerActivity.class.getSimpleName();
    public static String fileName;
    private boolean lockAspectRatio = false;
    private boolean setBitmapMaxWidthHeight = false;
    private int ASPECT_RATIO_X = 16;
    private int ASPECT_RATIO_Y = 9;
    private int bitmapMaxWidth = 1000;
    private int bitmapMaxHeight = 1000;
    private int IMAGE_COMPRESSION = 80;

    /* loaded from: classes2.dex */
    public interface PickerOptionListener {
        void onChooseGallerySelected();

        void onTakeCameraSelected();
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i2, int i3) {
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i3 || i5 > i2) {
            int min = Math.min(Math.round(i4 / i3), Math.round(i5 / i2));
            while ((i5 * i4) / (min * min) > i2 * i3 * 2) {
                min++;
            }
            return min;
        }
        return 1;
    }

    private void chooseImageFromGallery() {
        Dexter.withActivity(this).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.ImagePickerActivity.2
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    ImagePickerActivity.this.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
                }
            }
        }).check();
    }

    public static void clearCache(Context context) {
        File file = new File(context.getExternalCacheDir(), "camera");
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Objects.requireNonNull(listFiles);
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
        File file3 = new File(context.getExternalCacheDir(), Scopes.PROFILE);
        if (file3.exists() && file3.isDirectory()) {
            File[] listFiles2 = file3.listFiles();
            Objects.requireNonNull(listFiles2);
            for (File file4 : listFiles2) {
                file4.delete();
            }
        }
    }

    private void cropImage(Uri uri) {
        Uri fromFile = Uri.fromFile(new File(getCacheDir(), queryName(getContentResolver(), uri)));
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(this.IMAGE_COMPRESSION);
        options.setToolbarColor(ContextCompat.getColor(this, R.color.primary_color_1));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color_1));
        options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.primary_color_1));
        if (this.lockAspectRatio) {
            options.withAspectRatio(this.ASPECT_RATIO_X, this.ASPECT_RATIO_Y);
        }
        if (this.setBitmapMaxWidthHeight) {
            options.withMaxResultSize(this.bitmapMaxWidth, this.bitmapMaxHeight);
        }
        UCrop.of(uri, fromFile).withOptions(options).start(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getCacheImagePath(String str) {
        File file = new File(getExternalCacheDir(), "camera");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        return FileProvider.getUriForFile(this, getPackageName() + ".provider", file2);
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        query.close();
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private Uri getImageUri(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            File file = new File(getExternalCacheDir(), Scopes.PROFILE);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(file2));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return FileProvider.getUriForFile(this, getPackageName() + ".provider", file2);
            } catch (IOException e2) {
                e2.printStackTrace();
                return Uri.parse("");
            }
        }
        return Uri.parse("");
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_image_intent_null), 1).show();
            return;
        }
        this.ASPECT_RATIO_X = intent.getIntExtra(INTENT_ASPECT_RATIO_X, this.ASPECT_RATIO_X);
        this.ASPECT_RATIO_Y = intent.getIntExtra(INTENT_ASPECT_RATIO_Y, this.ASPECT_RATIO_Y);
        this.IMAGE_COMPRESSION = intent.getIntExtra(INTENT_IMAGE_COMPRESSION_QUALITY, this.IMAGE_COMPRESSION);
        this.lockAspectRatio = intent.getBooleanExtra(INTENT_LOCK_ASPECT_RATIO, false);
        this.setBitmapMaxWidthHeight = intent.getBooleanExtra(INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, false);
        this.bitmapMaxWidth = intent.getIntExtra(INTENT_BITMAP_MAX_WIDTH, this.bitmapMaxWidth);
        this.bitmapMaxHeight = intent.getIntExtra(INTENT_BITMAP_MAX_HEIGHT, this.bitmapMaxHeight);
        if (intent.getIntExtra(INTENT_IMAGE_PICKER_OPTION, -1) == 0) {
            takeCameraImage();
        } else {
            chooseImageFromGallery();
        }
    }

    @SuppressLint({"NewApi"})
    public static String getPathFromURI(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if (BuildConfig.ACCOUNTID.equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(DocumentsContract.getDocumentId(uri))), null, null);
            } else {
                if (isMediaDocument(uri)) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        } else {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    private void handleUCropResult(Intent intent) {
        if (intent == null) {
            setResultCancelled();
        } else {
            setResultOk(UCrop.getOutput(intent));
        }
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showImagePickerOptions$0(PickerOptionListener pickerOptionListener, DialogInterface dialogInterface, int i2) {
        if (i2 == 0) {
            pickerOptionListener.onTakeCameraSelected();
        } else if (i2 != 1) {
        } else {
            pickerOptionListener.onChooseGallerySelected();
        }
    }

    public static Bitmap lessResolution(String str, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String queryName(ContentResolver contentResolver, Uri uri) {
        Cursor query = contentResolver.query(uri, null, null, null, null);
        int columnIndex = query.getColumnIndex("_display_name");
        query.moveToFirst();
        String string = query.getString(columnIndex);
        query.close();
        return string;
    }

    private static Bitmap rotateImage(Bitmap bitmap, int i2) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    private static Bitmap rotateImageIfRequired(Context context, Bitmap bitmap, Uri uri) {
        int i2;
        int attributeInt = (Build.VERSION.SDK_INT > 23 ? new ExifInterface(context.getContentResolver().openInputStream(uri)) : new ExifInterface(uri.getPath())).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 3) {
            i2 = CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
        } else if (attributeInt == 6) {
            i2 = 90;
        } else if (attributeInt != 8) {
            return bitmap;
        } else {
            i2 = 270;
        }
        return rotateImage(bitmap, i2);
    }

    private void setResultCancelled() {
        setResult(0, new Intent());
        finish();
    }

    private void setResultOk(Uri uri) {
        Intent intent = new Intent();
        intent.putExtra(ClientCookie.PATH_ATTR, uri);
        setResult(-1, intent);
        finish();
    }

    public static void showImagePickerOptions(Context context, final PickerOptionListener pickerOptionListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.lbl_set_profile_photo));
        builder.setItems(new String[]{context.getString(R.string.lbl_take_camera_picture), context.getString(R.string.lbl_choose_from_gallery)}, new DialogInterface.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ImagePickerActivity.lambda$showImagePickerOptions$0(ImagePickerActivity.PickerOptionListener.this, dialogInterface, i2);
            }
        });
        builder.create().show();
    }

    private void showToast() {
        Toast.makeText(this, "Please Try Again!", 0).show();
        setResultCancelled();
    }

    private void takeCameraImage() {
        Dexter.withActivity(this).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.ImagePickerActivity.1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    ImagePickerActivity.fileName = System.currentTimeMillis() + ".jpg";
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra("output", ImagePickerActivity.this.getCacheImagePath(ImagePickerActivity.fileName));
                    if (intent.resolveActivity(ImagePickerActivity.this.getPackageManager()) != null) {
                        ImagePickerActivity.this.startActivityForResult(intent, 0);
                    }
                }
            }
        }).check();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:146:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0111 A[Catch: Exception -> 0x0204, TryCatch #5 {Exception -> 0x0204, blocks: (B:16:0x002b, B:18:0x0037, B:20:0x0041, B:21:0x0052, B:23:0x0058, B:24:0x005c, B:40:0x009d, B:42:0x00a3, B:44:0x00ab, B:45:0x00b0, B:31:0x0081, B:34:0x0089, B:36:0x0090, B:38:0x0097, B:97:0x017a, B:50:0x00be, B:53:0x00d5, B:55:0x00db, B:49:0x00b9, B:69:0x0111, B:71:0x0117, B:81:0x0144, B:91:0x0167, B:93:0x016d, B:95:0x0175, B:78:0x013d, B:84:0x0151, B:86:0x0158, B:89:0x0160, B:66:0x010b, B:57:0x00e2, B:48:0x00b6, B:99:0x0181, B:101:0x0189, B:118:0x01d1, B:120:0x01e5, B:122:0x01eb, B:124:0x01f3, B:125:0x01f7, B:108:0x01af, B:111:0x01b7, B:112:0x01bb, B:114:0x01c0, B:116:0x01c7, B:117:0x01cc, B:127:0x01fc, B:128:0x0200, B:105:0x01a7, B:75:0x0133, B:59:0x00f4, B:61:0x00fe, B:63:0x0104), top: B:142:0x0009, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0167 A[Catch: Exception -> 0x0204, TryCatch #5 {Exception -> 0x0204, blocks: (B:16:0x002b, B:18:0x0037, B:20:0x0041, B:21:0x0052, B:23:0x0058, B:24:0x005c, B:40:0x009d, B:42:0x00a3, B:44:0x00ab, B:45:0x00b0, B:31:0x0081, B:34:0x0089, B:36:0x0090, B:38:0x0097, B:97:0x017a, B:50:0x00be, B:53:0x00d5, B:55:0x00db, B:49:0x00b9, B:69:0x0111, B:71:0x0117, B:81:0x0144, B:91:0x0167, B:93:0x016d, B:95:0x0175, B:78:0x013d, B:84:0x0151, B:86:0x0158, B:89:0x0160, B:66:0x010b, B:57:0x00e2, B:48:0x00b6, B:99:0x0181, B:101:0x0189, B:118:0x01d1, B:120:0x01e5, B:122:0x01eb, B:124:0x01f3, B:125:0x01f7, B:108:0x01af, B:111:0x01b7, B:112:0x01bb, B:114:0x01c0, B:116:0x01c7, B:117:0x01cc, B:127:0x01fc, B:128:0x0200, B:105:0x01a7, B:75:0x0133, B:59:0x00f4, B:61:0x00fe, B:63:0x0104), top: B:142:0x0009, inners: #1, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x017a A[Catch: Exception -> 0x0204, TryCatch #5 {Exception -> 0x0204, blocks: (B:16:0x002b, B:18:0x0037, B:20:0x0041, B:21:0x0052, B:23:0x0058, B:24:0x005c, B:40:0x009d, B:42:0x00a3, B:44:0x00ab, B:45:0x00b0, B:31:0x0081, B:34:0x0089, B:36:0x0090, B:38:0x0097, B:97:0x017a, B:50:0x00be, B:53:0x00d5, B:55:0x00db, B:49:0x00b9, B:69:0x0111, B:71:0x0117, B:81:0x0144, B:91:0x0167, B:93:0x016d, B:95:0x0175, B:78:0x013d, B:84:0x0151, B:86:0x0158, B:89:0x0160, B:66:0x010b, B:57:0x00e2, B:48:0x00b6, B:99:0x0181, B:101:0x0189, B:118:0x01d1, B:120:0x01e5, B:122:0x01eb, B:124:0x01f3, B:125:0x01f7, B:108:0x01af, B:111:0x01b7, B:112:0x01bb, B:114:0x01c0, B:116:0x01c7, B:117:0x01cc, B:127:0x01fc, B:128:0x0200, B:105:0x01a7, B:75:0x0133, B:59:0x00f4, B:61:0x00fe, B:63:0x0104), top: B:142:0x0009, inners: #1, #2, #3, #4 }] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri imageUri;
        Uri imageUri2;
        String string;
        File file;
        Bitmap lessResolution;
        Uri imageUri3;
        super.onActivityResult(i2, i3, intent);
        Bitmap bitmap = null;
        try {
            if (i2 == 0) {
                if (i3 == -1) {
                    Uri cacheImagePath = getCacheImagePath(fileName);
                    if (cacheImagePath != null) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        InputStream openInputStream = getContentResolver().openInputStream(cacheImagePath);
                        BitmapFactory.decodeStream(openInputStream, null, options);
                        openInputStream.close();
                        int i4 = options.outHeight;
                        int i5 = options.outWidth;
                        if (i4 > 1024 || i5 > 1024) {
                            options.inSampleSize = (i4 <= 1024 || i5 <= 1024) ? i5 > 1024 ? calculateInSampleSize(options, 1024, i4) : i4 > 1024 ? calculateInSampleSize(options, i5, 1024) : calculateInSampleSize(options, i5, i4) : calculateInSampleSize(options, 1024, 1024);
                        } else {
                            try {
                                options.inSampleSize = calculateInSampleSize(options, i5, i4);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        options.inJustDecodeBounds = false;
                        bitmap = rotateImageIfRequired(this, BitmapFactory.decodeStream(getContentResolver().openInputStream(cacheImagePath), null, options), cacheImagePath);
                        if (bitmap == null || (imageUri = getImageUri(bitmap)) == null || Uri.EMPTY.equals(imageUri)) {
                            showToast();
                        } else {
                            cropImage(imageUri);
                        }
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                        return;
                    } else {
                        showToast();
                        return;
                    }
                }
                setResultCancelled();
            }
            if (i2 != 1) {
                if (i2 != 69) {
                    if (i2 == 96) {
                        UCrop.getError(intent);
                    }
                } else if (i3 == -1) {
                    handleUCropResult(intent);
                    return;
                }
            } else if (i3 == -1) {
                Uri data = intent.getData();
                if (data.toString().startsWith("content://com.google.android.apps.photos.content")) {
                    try {
                        InputStream openInputStream2 = getContentResolver().openInputStream(data);
                        if (openInputStream2 != null) {
                            File createTempFile = File.createTempFile("google_photos", ".tmp");
                            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = openInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            String path = createTempFile.getPath();
                            BitmapFactory.Options options2 = new BitmapFactory.Options();
                            options2.inJustDecodeBounds = true;
                            BitmapFactory.decodeFile(createTempFile.getAbsolutePath(), options2);
                            int i6 = options2.outHeight;
                            int i7 = options2.outWidth;
                            if (i6 <= 1024 && i7 <= 1024) {
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data);
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            } else if (i6 > 1024 && i7 > 1024) {
                                bitmap = lessResolution(path, 1024, 1024);
                            } else if (i7 > 1024) {
                                bitmap = lessResolution(path, 1024, i6);
                            } else if (i6 > 1024) {
                                bitmap = lessResolution(path, i7, 1024);
                            }
                            if (bitmap == null || (imageUri2 = getImageUri(bitmap)) == null || Uri.EMPTY.equals(imageUri2)) {
                                showToast();
                            } else {
                                cropImage(data);
                            }
                        }
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (bitmap == null) {
                        bitmap.recycle();
                        return;
                    }
                    return;
                }
                String[] strArr = {"_data"};
                Cursor query = getContentResolver().query(data, strArr, null, null, null);
                if (query == null) {
                    string = data.getPath() != null ? data.getPath() : "";
                } else {
                    query.moveToFirst();
                    string = query.getString(query.getColumnIndex(strArr[0]));
                    query.close();
                }
                if (string == null) {
                    try {
                        string = getPathFromURI(getApplicationContext(), data);
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    if (string != null && !string.equalsIgnoreCase("")) {
                        file = new File(string);
                        if (string != null) {
                            if (!string.equalsIgnoreCase("")) {
                                BitmapFactory.Options options3 = new BitmapFactory.Options();
                                options3.inJustDecodeBounds = true;
                                BitmapFactory.decodeFile(new File(string).getAbsolutePath(), options3);
                                int i8 = options3.outHeight;
                                int i9 = options3.outWidth;
                                if (i8 > 1024 || i9 > 1024) {
                                    if (i8 > 1024 && i9 > 1024) {
                                        lessResolution = lessResolution(string, 1024, 1024);
                                    } else if (i9 > 1024) {
                                        lessResolution = lessResolution(string, 1024, i8);
                                    } else {
                                        if (i8 > 1024) {
                                            lessResolution = lessResolution(string, i9, 1024);
                                        }
                                        if (bitmap != null && (imageUri3 = getImageUri(bitmap)) != null && !Uri.EMPTY.equals(imageUri3)) {
                                            cropImage(imageUri3);
                                        }
                                    }
                                    bitmap = lessResolution;
                                    if (bitmap != null) {
                                        cropImage(imageUri3);
                                    }
                                } else {
                                    try {
                                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data);
                                    } catch (Exception e6) {
                                        e6.printStackTrace();
                                    }
                                    if (bitmap == null && file != null) {
                                        lessResolution = BitmapFactory.decodeFile(file.getAbsolutePath());
                                        bitmap = lessResolution;
                                    }
                                    if (bitmap != null) {
                                    }
                                }
                            }
                            if (bitmap == null) {
                            }
                        }
                        showToast();
                        if (bitmap == null) {
                        }
                    }
                }
                file = null;
                if (string != null) {
                }
                showToast();
                if (bitmap == null) {
                }
            }
            setResultCancelled();
        } catch (Exception e7) {
            e7.printStackTrace();
            showToast();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_picker);
        getIntentData();
    }
}
