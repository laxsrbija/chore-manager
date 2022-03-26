package rs.laxsrbija.chores.core.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.data.entity.ObjectEntity;
import rs.laxsrbija.chores.shared.model.dto.Category;
import rs.laxsrbija.chores.shared.model.dto.Object;

@Component
public class ObjectMapper
{
	public Object toObject(final ObjectEntity object, final Category category)
	{
		return Object.builder()
			.name(object.getName())
			.category(category)
			.build();
	}
}
