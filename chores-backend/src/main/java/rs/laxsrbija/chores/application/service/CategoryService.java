package rs.laxsrbija.chores.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.application.port.in.CategoryInboundPort;
import rs.laxsrbija.chores.application.port.out.CategoryOutboundPort;
import rs.laxsrbija.chores.domain.Category;

@Service
@RequiredArgsConstructor
class CategoryService implements CategoryInboundPort {

  private final CategoryOutboundPort categoryOutboundPort;

  @Override
  public Category get(final String id) {
    return categoryOutboundPort.get(id);
  }

  @Override
  public List<Category> getAll() {
    return categoryOutboundPort.getAll();
  }

  @Override
  public Category save(final Category object) {
    return categoryOutboundPort.save(object);
  }

  @Override
  public void delete(final String id) {
    categoryOutboundPort.delete(id);
  }
}
