package rs.laxsrbija.chores.core.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.data.entity.CategoryEntity;
import rs.laxsrbija.chores.shared.model.dto.Category;

@Component
public class CategoryMapper
{
	public Category toCategory(final CategoryEntity category)
	{
		return Category.builder()
			.id(category.getId())
			.name(category.getName())
			.build();
	}
}
