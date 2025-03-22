package org.pwr.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjector {

    private static final Injector INJECTOR;

    static {
        INJECTOR = Guice.createInjector(new GuiceConfiguration());
    }

    public static Injector getInjector() {
        return INJECTOR;
    }
}
