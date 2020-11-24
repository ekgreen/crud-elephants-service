package com.goodboy.edu.crud.elephants.service.api.services.restrictions;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Restriction {

    private Available available;

    private List<String> types;

    public enum Available {
        ALLOW, PARTLY_ALLOW, DENY
    }
}
