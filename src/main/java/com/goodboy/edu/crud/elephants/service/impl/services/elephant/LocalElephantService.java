package com.goodboy.edu.crud.elephants.service.impl.services.elephant;

import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantRepository;
import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantService;
import com.goodboy.edu.crud.elephants.service.api.services.restrictions.Restriction;
import com.goodboy.edu.crud.elephants.service.api.services.restrictions.RestrictionService;
import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class LocalElephantService implements ElephantService {

    private final ElephantRepository repository;
    private final RestrictionService restrictionService;

    public @NonNull List<Elephant> getElephantOfferByUserId(@NonNull String id) {
        final Restriction restriction = restrictionService.getAvailableElephantTypesByUserId(id);

        final List<Elephant> cached = repository.getAll();

        switch (restriction.getAvailable()){
            case ALLOW:
                return new ArrayList<>(cached);
            case PARTLY_ALLOW:
                final Set<String> available = new HashSet<>(restriction.getTypes());
                return cached.stream().filter(el -> available.contains(el.getType().getValue())).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }
}
