package org.pwr;

import com.google.inject.Inject;

public class SomeService {

    private final SomeSecondService someSecondService;

    @Inject
    public SomeService(SomeSecondService someSecondService) {
        this.someSecondService = someSecondService;
    }

    public String print() {
        return someSecondService.print();
    }
}
