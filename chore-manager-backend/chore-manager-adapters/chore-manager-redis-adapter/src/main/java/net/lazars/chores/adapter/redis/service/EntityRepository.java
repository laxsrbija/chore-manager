package net.lazars.chores.adapter.redis.service;

import net.lazars.chores.adapter.redis.util.IdGenerator;
import net.lazars.chores.core.model.BaseModel;
import net.lazars.chores.core.port.CrudOperations;

public abstract class EntityRepository<E extends BaseModel> implements CrudOperations<E> {

  @Override
  public final E save(final E object) {
    IdGenerator.validateId(object);
    saveEntity(object);
    return get(object.getId());
  }

  protected abstract void saveEntity(final E entity);
}
