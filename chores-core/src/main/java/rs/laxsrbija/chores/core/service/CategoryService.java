package rs.laxsrbija.chores.core.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.core.mapper.CategoryMapper;
import rs.laxsrbija.chores.data.entity.CategoryEntity;
import rs.laxsrbija.chores.data.repository.CategoryRepository;
import rs.laxsrbija.chores.shared.model.dto.Category;

@Service
@RequiredArgsConstructor
public class CategoryService
{
	private final CategoryRepository _categoryRepository;
	private final CategoryMapper _categoryMapper;

	public Category getCategory(final String id)
	{
		final CategoryEntity categoryEntity = _categoryRepository.findById(id);
		return _categoryMapper.toCategory(categoryEntity);
	}
}
