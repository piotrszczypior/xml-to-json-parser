package org.pwr.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pwr.guice.GuiceInjector;

public class Main {

    public static void main(String[] args) {
//Injector injector = GuiceInjector.getInjector();
        String result = GuiceInjector.getInjector().getInstance(SomeService.class).print();
        System.out.println(result);
    }
}