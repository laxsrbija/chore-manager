package rs.laxsrbija.chores.adapter.out.persistence.repository;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudRepository<T> {

  private final JsonDBTemplate _jsonDBTemplate;
  private final Class<T> _type;

  public T findById(final String id) {
    return _jsonDBTemplate.findById(id, _type);
  }

  public List<T> findAll() {
    return _jsonDBTemplate.findAll(_type);
  }

  public void save(final T data) {
    _jsonDBTemplate.upsert(data);
  }

  public void delete(String id) {
    _jsonDBTemplate.remove(findById(id), _type);
  }
}
