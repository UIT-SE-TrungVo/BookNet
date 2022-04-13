package com.booknet.api.sample_module.service;

import com.booknet.api.sample_module.model.SampleModel;
import com.booknet.api.sample_module.payload.request.SampleCreateRequest;
import com.booknet.api.sample_module.payload.request.SampleUpdateRequest;
import com.booknet.api.sample_module.repository.SampleRepository;
import com.booknet.utils.IdGenerator;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class SampleService {
    @Autowired
    SampleRepository demoNumberRepository;

    public SampleModel createSample(SampleCreateRequest reqData) {
        String newId = IdGenerator.createNew(IdPrefix.SAMPLE);
        Integer number = reqData.getNumber();
        String text = reqData.getText();

        SampleModel newDemoNumber = new SampleModel(newId, number, text);
        demoNumberRepository.insert(newDemoNumber);

        Utils.log.print(this, "create SampleModel success", Utils.json.stringify(newDemoNumber));
        return newDemoNumber;
    }

    public Collection<SampleModel> getAllSamples() {
        Collection<SampleModel> samples = demoNumberRepository.findAll();
        Utils.log.print(this, "Get all SampleModels");
        return samples;
    }

    public SampleModel getSample(String id) {
        SampleModel sample = demoNumberRepository.findBy_id(id).orElse(null);
        Utils.log.print(this, "Get SampleModel with id", id, Utils.json.stringify(sample));
        return sample;
    }

    public SampleModel updateSample(String id, SampleUpdateRequest reqData) {
        SampleModel dbValue = this.getSample(id);
        if (!id.equals(reqData.get_id())) {
            Utils.log.print(this, "id not match - updateSample halted", id, reqData.get_id());
            return null;
        }

        if (dbValue != null) {
            String jsonOldData = Utils.json.stringify(dbValue);
            dbValue.setNumber(reqData.getNumber());
            dbValue.setText(reqData.getText());
            demoNumberRepository.save(dbValue);
            Utils.log.print(this,
                    "update SampleModel success -\n OLD: @old -\n @NEW: @new"
                            .replace("@old", jsonOldData)
                            .replace("@new", Utils.json.stringify(reqData)
                            )
            );
            return dbValue;
        } else {
            Utils.log.print(this, "cannot update non-exist SampleModel");
            return null;
        }
    }

    public SampleModel removeSample(String id) {
        SampleModel deleteData = this.getSample(id);
        if (deleteData != null) {
            demoNumberRepository.delete(deleteData);
            Utils.log.print(this, "remove SampleModel success", Utils.json.stringify(deleteData));
            return deleteData;
        } else {
            Utils.log.print(this, "cannot delete non-exist SampleModel");
            return null;
        }
    }
}
