package rs.laxsrbija.chores.adapter.in.web.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.laxsrbija.chores.application.port.in.CategoryInboundPort;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.Category;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryRestController implements CrudOperations<Category> {

  public final CategoryInboundPort categoryInboundPort;

  @Override
  @GetMapping("{id}")
  public Category get(@PathVariable final String id) {
    return categoryInboundPort.get(id);
  }

  @Override
  @GetMapping
  public List<Category> getAll() {
    return categoryInboundPort.getAll();
  }

  @Override
  @PutMapping
  public Category save(@RequestBody final Category category) {
    return categoryInboundPort.save(category);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    categoryInboundPort.delete(id);
  }
}
