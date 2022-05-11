package com.booknet.api.book.service;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.book.repository.BookRepository;
import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.request.BookNotifyRequest;
import com.booknet.api.book.request.BookUpdateRequest;
import com.booknet.constants.EvId;
import com.booknet.constants.IdPrefix;
import com.booknet.system.EventCenter;
import com.booknet.utils.IdGenerator;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    BookRepository sampleRepository;

    public BookModel createSample(BookCreateRequest reqData) {
        String newId = IdGenerator.createNew(IdPrefix.SAMPLE);
        Integer number = reqData.getNumber();
        String text = reqData.getText();

        BookModel newDemoNumber = new BookModel(newId, number, text);
        sampleRepository.insert(newDemoNumber);

        logger.info("create SampleModel success {}", Utils.json.stringify(newDemoNumber));
        return newDemoNumber;
    }

    public Collection<BookModel> getAllSamples() {
        Collection<BookModel> samples = sampleRepository.findAll();
        logger.info("get all SampleModels {}", Utils.json.stringify(samples));
        return samples;
    }

    public BookModel getSample(String id) {
        BookModel sample = sampleRepository.findBy_id(id).orElse(null);
        logger.info("get SampleModel with id {} {}", id, Utils.json.stringify(sample));
        return sample;
    }

    public BookModel updateSample(String id, BookUpdateRequest reqData) {
        BookModel dbValue = this.getSample(id);

        if (dbValue != null) {
            String jsonOldData = Utils.json.stringify(dbValue);
            dbValue.setNumber(reqData.getNumber());
            dbValue.setText(reqData.getText());
            sampleRepository.save(dbValue);
            logger.info("update SampleModel success OLD: {} - NEW: {}"
                    , jsonOldData
                    , Utils.json.stringify(reqData)
            );
            return dbValue;
        } else {
            logger.info("cannot update non-exist SampleModel");
            return null;
        }
    }

    public BookModel removeSample(String id) {
        BookModel deleteData = this.getSample(id);
        if (deleteData != null) {
            sampleRepository.delete(deleteData);
            logger.info("remove SampleModel success {}", Utils.json.stringify(deleteData));
            return deleteData;
        } else {
            logger.info("cannot delete non-exist SampleModel");
            return null;
        }
    }

    public void doNotify() {
        EventCenter.pub(EvId.SAMPLE_EVENT);
    }

    public void doNotifyWithArgument(@NotNull BookNotifyRequest req) {
        EventCenter.pub(EvId.SAMPLE_EVENT_WITH_ARGS, req.getNumber());
    }

    public void onNotified() {
        logger.info("has been notified");
    }

    public void onNotifiedWithArgument(Integer number) {
        logger.info("has been notified with argument {}", number);
    }
}