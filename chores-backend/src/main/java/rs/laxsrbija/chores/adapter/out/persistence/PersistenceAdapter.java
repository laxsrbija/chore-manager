package rs.laxsrbija.chores.adapter.out.persistence;

import rs.laxsrbija.chores.application.util.IdGenerator;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.BaseDto;

public abstract class PersistenceAdapter<E extends BaseDto> implements CrudOperations<E> {

  @Override
  public final E save(final E object) {
    IdGenerator.validateId(object);
    saveEntity(object);
    return get(object.getId());
  }

  protected abstract void saveEntity(final E entity);
}
