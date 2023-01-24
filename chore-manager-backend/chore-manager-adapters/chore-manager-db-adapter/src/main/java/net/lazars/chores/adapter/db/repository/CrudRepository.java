package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.exception.ChoreManagerException;

@RequiredArgsConstructor
abstract class CrudRepository<E> {

  private final JsonDBTemplate jsonDBTemplate;
  private final Class<E> type;

  public E findById(final String id) {
    return Optional.ofNullable(jsonDBTemplate.findById(id, type))
        .orElseThrow(() -> new ChoreManagerException("Entity with ID '" + id + "' not found"));
  }

  public List<E> findAll() {
    return jsonDBTemplate.findAll(type);
  }

  public void save(final E data) {
    jsonDBTemplate.upsert(data);
  }

  public void delete(String id) {
    jsonDBTemplate.remove(findById(id), type);
  }
}
