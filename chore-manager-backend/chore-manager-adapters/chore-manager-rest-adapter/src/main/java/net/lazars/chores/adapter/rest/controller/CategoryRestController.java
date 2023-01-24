package net.lazars.chores.adapter.rest.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryRestController implements CrudOperations<Category> {

  public final CategoryService categoryService;

  @Override
  @GetMapping("{id}")
  public Category get(@PathVariable final String id) {
    return categoryService.get(id);
  }

  @Override
  @GetMapping
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @Override
  @PutMapping
  public Category save(@RequestBody final Category category) {
    return categoryService.save(category);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    categoryService.delete(id);
  }
}
