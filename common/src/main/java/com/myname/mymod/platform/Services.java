package com.myname.mymod.platform;

import java.util.ServiceLoader;

public class Services {
    public static final PlatformHelperInterface PLATFORM = load(PlatformHelperInterface.class);

    private static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No implementation for: " + clazz.getName()));
    }

}
