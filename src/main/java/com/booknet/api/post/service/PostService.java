package com.booknet.api.post.service;

import com.booknet.api.post.model.PostModel;
import com.booknet.api.post.repository.PostRepository;
import com.booknet.api.post.request.PostCreateRequest;
import com.booknet.api.post.request.PostNotifyRequest;
import com.booknet.api.post.request.PostUpdateRequest;
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
    PostRepository postRepository;

    public PostModel createSample(PostCreateRequest reqData) {
        String newId = IdGenerator.createNew(IdPrefix.SAMPLE);
        Integer number = reqData.getNumber();
        String text = reqData.getText();

        PostModel newDemoNumber = new PostModel(newId, number, text);
        postRepository.insert(newDemoNumber);

        logger.info("create SampleModel success {}", Utils.json.stringify(newDemoNumber));
        return newDemoNumber;
    }

    public Collection<PostModel> getAllSamples() {
        Collection<PostModel> samples = postRepository.findAll();
        logger.info("get all SampleModels {}", Utils.json.stringify(samples));
        return samples;
    }

    public PostModel getSample(String id) {
        PostModel sample = postRepository.findBy_id(id).orElse(null);
        logger.info("get SampleModel with id {} {}", id, Utils.json.stringify(sample));
        return sample;
    }

    public PostModel updateSample(String id, PostUpdateRequest reqData) {
        PostModel dbValue = this.getSample(id);

        if (dbValue != null) {
            String jsonOldData = Utils.json.stringify(dbValue);
            dbValue.setNumber(reqData.getNumber());
            dbValue.setText(reqData.getText());
            postRepository.save(dbValue);
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

    public PostModel removeSample(String id) {
        PostModel deleteData = this.getSample(id);
        if (deleteData != null) {
            postRepository.delete(deleteData);
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

    public void doNotifyWithArgument(@NotNull PostNotifyRequest req) {
        EventCenter.pub(EvId.SAMPLE_EVENT_WITH_ARGS, req.getNumber());
    }

    public void onNotified() {
        logger.info("has been notified");
    }

    public void onNotifiedWithArgument(Integer number) {
        logger.info("has been notified with argument {}", number);
    }
}