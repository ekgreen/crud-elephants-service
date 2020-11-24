package com.goodboy.edu.crud.elephants.service.impl.services.restrictions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodboy.edu.crud.elephants.service.api.services.restrictions.Restriction;
import com.goodboy.edu.crud.elephants.service.api.services.restrictions.RestrictionService;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocalRestrictionService implements RestrictionService {

    private final Map<String, List<String>> cached;

    @Autowired @SneakyThrows
    public LocalRestrictionService(ObjectMapper mapper) {
        this.cached = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("static/local-elephant-restrictions.json"), new TypeReference<Map<String, List<String>>>(){});
    }

    public @NonNull
    Restriction getAvailableElephantTypesByUserId(@NonNull String id) {
        final List<String> types = cached.get(id);

        if(types == null)
            return new Restriction().setAvailable(Restriction.Available.ALLOW);
        else if(types.isEmpty())
            return new Restriction().setAvailable(Restriction.Available.DENY);
        else {
            return new Restriction().setAvailable(Restriction.Available.PARTLY_ALLOW).setTypes(types);
        }
    }

}
