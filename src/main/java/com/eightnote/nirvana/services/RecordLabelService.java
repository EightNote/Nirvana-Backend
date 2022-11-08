package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.RecordLabelDAO;
import com.eightnote.nirvana.models.RecordLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordLabelService {
    @Autowired
    private final RecordLabelDAO recordLabelDAO;

    public RecordLabelService(RecordLabelDAO recordLabelDAO) {
        this.recordLabelDAO = recordLabelDAO;
    }

    public RecordLabel getRecordLabel(Integer id) {
        return recordLabelDAO.getRecordLabel(id);
    }

    public List<RecordLabel> getAllRecordLabel(){return recordLabelDAO.getAllRecordLabel();}

    public void createRecordLabel(RecordLabel recordLabel) {
        recordLabelDAO.createRecordLabel(recordLabel);
    }


}
