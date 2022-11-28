package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private final File dataDirectory;
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private static final boolean IS_WINDOWS = StandardSystemProperty.OS_NAME.value().toLowerCase(Locale.ENGLISH).startsWith("windows");

    /* loaded from: classes2.dex */
    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory fileDataStoreFactory, File file, String str) {
            super(fileDataStoreFactory, str);
            File file2 = new File(file, str);
            this.dataFile = file2;
            if (IOUtils.isSymbolicLink(file2)) {
                throw new IOException("unable to use a symbolic link: " + file2);
            } else if (!file2.createNewFile()) {
                this.f8100a = (HashMap) IOUtils.deserialize(new FileInputStream(file2));
            } else {
                this.f8100a = Maps.newHashMap();
                save();
            }
        }

        @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory) super.getDataStoreFactory();
        }

        @Override // com.google.api.client.util.store.AbstractMemoryDataStore
        public void save() {
            IOUtils.serialize(this.f8100a, new FileOutputStream(this.dataFile));
        }
    }

    public FileDataStoreFactory(File file) {
        File canonicalFile = file.getCanonicalFile();
        if (IOUtils.isSymbolicLink(canonicalFile)) {
            throw new IOException("unable to use a symbolic link: " + canonicalFile);
        } else if (!canonicalFile.exists() && !canonicalFile.mkdirs()) {
            throw new IOException("unable to create directory: " + canonicalFile);
        } else {
            this.dataDirectory = canonicalFile;
            if (IS_WINDOWS) {
                setPermissionsToOwnerOnlyWindows(canonicalFile);
            } else {
                setPermissionsToOwnerOnly(canonicalFile);
            }
        }
    }

    private static void setPermissionsToOwnerOnly(File file) {
        HashSet hashSet = new HashSet();
        hashSet.add(PosixFilePermission.OWNER_READ);
        hashSet.add(PosixFilePermission.OWNER_WRITE);
        hashSet.add(PosixFilePermission.OWNER_EXECUTE);
        try {
            Files.setPosixFilePermissions(Paths.get(file.getAbsolutePath(), new String[0]), hashSet);
        } catch (RuntimeException e2) {
            throw new IOException("Unable to set permissions for " + file, e2);
        }
    }

    private static void setPermissionsToOwnerOnlyWindows(File file) {
        Path path = Paths.get(file.getAbsolutePath(), new String[0]);
        try {
            ((AclFileAttributeView) Files.getFileAttributeView(path, AclFileAttributeView.class, new LinkOption[0])).setAcl(ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(((FileOwnerAttributeView) Files.getFileAttributeView(path, FileOwnerAttributeView.class, new LinkOption[0])).getOwner()).setPermissions(ImmutableSet.of(AclEntryPermission.APPEND_DATA, AclEntryPermission.DELETE, AclEntryPermission.DELETE_CHILD, AclEntryPermission.READ_ACL, AclEntryPermission.READ_ATTRIBUTES, AclEntryPermission.READ_DATA, AclEntryPermission.READ_NAMED_ATTRS, AclEntryPermission.SYNCHRONIZE, AclEntryPermission.WRITE_ACL, AclEntryPermission.WRITE_ATTRIBUTES, AclEntryPermission.WRITE_DATA, AclEntryPermission.WRITE_NAMED_ATTRS, AclEntryPermission.WRITE_OWNER)).build()));
        } catch (SecurityException e2) {
            throw new IOException("Unable to set permissions for " + file, e2);
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStoreFactory
    protected DataStore a(String str) {
        return new FileDataStore(this, this.dataDirectory, str);
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }
}
