package rs.laxsrbija.chores.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ObjectEntity;
import rs.laxsrbija.chores.domain.model.dto.Category;
import rs.laxsrbija.chores.domain.model.dto.Object;

@Component
public class ObjectMapper {

  public Object toObject(final ObjectEntity object, final Category category) {
    return Object.builder()
        .id(object.getId())
        .name(object.getName())
        .category(category)
        .build();
  }
}
