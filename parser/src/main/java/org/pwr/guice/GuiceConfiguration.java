package org.pwr.guice;

import com.google.inject.AbstractModule;
import org.pwr.XmlToJsonConverter;
import org.pwr.XmlToJsonConverterImpl;
import org.pwr.visitor.XMLVisitor;

public class GuiceConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(XMLVisitor.class);
        bind(XmlToJsonConverter.class).to(XmlToJsonConverterImpl.class);
    }
}
