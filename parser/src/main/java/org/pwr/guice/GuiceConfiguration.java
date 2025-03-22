package org.pwr.guice;

import com.google.inject.AbstractModule;
import org.pwr.SomeSecondService;
import org.pwr.SomeService;

public class GuiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(SomeService.class);
        bind(SomeSecondService.class);
    }
}
