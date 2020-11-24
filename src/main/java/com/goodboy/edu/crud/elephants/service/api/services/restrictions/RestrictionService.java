package com.goodboy.edu.crud.elephants.service.api.services.restrictions;


import lombok.NonNull;

public interface RestrictionService {

    @NonNull Restriction getAvailableElephantTypesByUserId(@NonNull String id);
}
