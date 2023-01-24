package net.lazars.chores.core.port;

import java.util.List;

public interface CrudOperations<E> {

  E get(String id);

  List<E> getAll();

  E save(E object);

  void delete(String id);
}
