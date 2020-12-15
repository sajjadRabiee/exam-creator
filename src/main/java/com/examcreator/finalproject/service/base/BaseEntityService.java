package com.examcreator.finalproject.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseEntityService<Entity,ID extends Number> {
    Optional<Entity> findByID(ID id);

    List<Entity> findAll();

    Entity save(Entity entity);

    void delete(Entity entity);
}
