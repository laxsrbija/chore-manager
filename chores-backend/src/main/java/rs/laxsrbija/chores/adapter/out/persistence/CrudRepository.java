package rs.laxsrbija.chores.adapter.out.persistence;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudRepository<E> {

  private final JsonDBTemplate jsonDBTemplate;
  private final Class<E> type;

  public E findById(final String id) {
    return jsonDBTemplate.findById(id, type);
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
