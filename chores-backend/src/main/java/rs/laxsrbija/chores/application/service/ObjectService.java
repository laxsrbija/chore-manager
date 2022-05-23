package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ObjectEntity;
import rs.laxsrbija.chores.adapter.out.persistence.mapper.ObjectMapper;
import rs.laxsrbija.chores.adapter.out.persistence.repository.ObjectRepository;
import rs.laxsrbija.chores.domain.model.dto.Category;
import rs.laxsrbija.chores.domain.model.dto.Object;

@Service
@RequiredArgsConstructor
public class ObjectService {

  private final CategoryService _categoryService;
  private final ObjectRepository _objectRepository;
  private final ObjectMapper _objectMapper;

  public Object getObject(final String id) {
    final ObjectEntity object = _objectRepository.findById(id);
    final Category category = _categoryService.getCategory(object.getCategoryId());
    return _objectMapper.toObject(object, category);
  }
}
