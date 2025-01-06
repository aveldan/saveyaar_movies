package com.saveyaar.saveyaar_movies.domain.DTOs;

import com.saveyaar.saveyaar_movies.model.Ott;
import com.saveyaar.saveyaar_movies.model.OttRecord;

import lombok.Data;

@Data
public class OttRecordDTO {
    private String watch_type;
    private String ott_name;    
    private int ott_id;
    private String logo_path;

    public OttRecordDTO(OttRecord ott_record) {
        Ott ott = ott_record.getOtt();
        this.ott_name = ott.getName();
        this.logo_path = ott.getLogo_path();
        this.ott_id = ott.getId();
        this.watch_type = ott_record.getWatch_type();
    }
}
