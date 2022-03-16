package rs.laxsrbija.chores.data.repository;

import java.util.List;
import io.jsondb.JsonDBTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudRepository<T>
{
	private final JsonDBTemplate _jsonDBTemplate;
	private final Class<T> type;

	public T findById(final String id)
	{
		return _jsonDBTemplate.findById(id, type);
	}

	public List<T> findAll()
	{
		return _jsonDBTemplate.findAll(type);
	}

	public void save(final T data)
	{
		_jsonDBTemplate.upsert(data);
	}

	public void delete(String id)
	{
		_jsonDBTemplate.remove(findById(id), type);
	}
}
