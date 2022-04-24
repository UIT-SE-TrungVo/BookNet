package com.booknet.api.sample_module.startup;

import com.booknet.api.sample_module.service.SampleService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationRunner {
    SampleService sampleService;

    @Autowired
    public SampleListener(SampleService service) {
        this.sampleService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.SAMPLE_EVENT, data -> onNotified());
        EventCenter.sub(EvId.SAMPLE_EVENT_WITH_ARGS, this::onNotifiedWithArguments);
    }

    void onNotified() {
        sampleService.onNotified();
    }

    void onNotifiedWithArguments(Object argument) {
        Integer number = (Integer)argument;
        sampleService.onNotifiedWithArgument(number);
    }
}
