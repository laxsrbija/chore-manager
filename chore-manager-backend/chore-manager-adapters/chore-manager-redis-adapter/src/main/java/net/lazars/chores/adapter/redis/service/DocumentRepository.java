package net.lazars.chores.adapter.redis.service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.lazars.chores.adapter.redis.util.IdGenerator;
import net.lazars.chores.core.model.BaseModel;
import net.lazars.chores.core.port.CrudOperations;

public abstract class DocumentRepository<E extends BaseModel> implements CrudOperations<E> {

  @Override
  public final E save(final E object) {
    IdGenerator.validateId(object);
    saveDocument(object);
    return get(object.getId());
  }

  public abstract void saveDocument(final E object);

  protected <T extends BaseModel> Map<String, T> collectById(final Collection<T> objects) {
    return objects.stream().collect(Collectors.toMap(BaseModel::getId, Function.identity()));
  }
}
