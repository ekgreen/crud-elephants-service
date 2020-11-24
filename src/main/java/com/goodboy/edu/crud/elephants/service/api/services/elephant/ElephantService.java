package com.goodboy.edu.crud.elephants.service.api.services.elephant;

import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;
import lombok.NonNull;

import java.util.List;


public interface ElephantService {

    @NonNull List<Elephant> getElephantOfferByUserId(@NonNull String id);

}
