package rs.laxsrbija.chores.core.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.core.mapper.ObjectMapper;
import rs.laxsrbija.chores.data.entity.ObjectEntity;
import rs.laxsrbija.chores.data.repository.ObjectRepository;
import rs.laxsrbija.chores.shared.model.dto.Category;
import rs.laxsrbija.chores.shared.model.dto.Object;

@Service
@RequiredArgsConstructor
public class ObjectService
{
	private final CategoryService _categoryService;
	private final ObjectRepository _objectRepository;
	private final ObjectMapper _objectMapper;

	public Object getObject(final String id)
	{
		final ObjectEntity object = _objectRepository.findById(id);
		final Category category = _categoryService.getCategory(object.getCategoryId());
		return _objectMapper.toObject(object, category);
	}
}
