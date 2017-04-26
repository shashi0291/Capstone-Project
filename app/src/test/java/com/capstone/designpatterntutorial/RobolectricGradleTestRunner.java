package com.capstone.designpatterntutorial;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

public class RobolectricGradleTestRunner extends RobolectricTestRunner {
    private static final String BUILD_OUTPUT = "intermediates";

    public RobolectricGradleTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected AndroidManifest getAppManifest(Config config) {
        String moduleRoot = getModuleRootPath(config);
        FsFile androidManifestFile = FileFsFile.from(moduleRoot, "manifests/full/debug/AndroidManifest.xml");
        FsFile resDirectory = FileFsFile.from(moduleRoot, "res\\merged\\debug");
        FsFile assetsDirectory = FileFsFile.from(moduleRoot, "assets\\debug");
        return new AndroidManifest(androidManifestFile, resDirectory, assetsDirectory);
    }

    private String getModuleRootPath(Config config) {
        String moduleRoot = config.constants().getResource("").toString().replace("file:", "");
        return moduleRoot.substring(0, moduleRoot.indexOf("/classes"));
    }
}