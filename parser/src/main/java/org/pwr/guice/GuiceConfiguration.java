package org.pwr.guice;

import com.google.inject.AbstractModule;
import org.pwr.parser.XMLVisitor;

public class GuiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(XMLVisitor.class);
    }
}
