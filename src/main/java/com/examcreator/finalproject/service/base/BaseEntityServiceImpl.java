package com.examcreator.finalproject.service.base;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseEntityServiceImpl<Entity,ID extends Number,EntityDAO extends JpaRepository<Entity,ID>> implements BaseEntityService<Entity,ID> {
    private final ApplicationContext context;

    private final EntityDAO entityDAO;

    public BaseEntityServiceImpl(EntityDAO entityDAO,ApplicationContext context) {
        this.entityDAO = entityDAO;
        this.context = context;
    }

    @Override
    public Optional<Entity> findByID(ID id) {
        return entityDAO.findById(id);
    }

    @Override
    public List<Entity> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public Entity save(Entity entity) {
        return entityDAO.save(entity);
    }

    @Override
    public void delete(Entity entity) {
        entityDAO.delete(entity);
    }
}
