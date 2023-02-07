package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.CategoryDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
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
public class CategoryRestController implements CrudOperations<CategoryDto> {

  private final CategoryService categoryService;

  @Override
  @GetMapping("{id}")
  public CategoryDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toCategoryDto(categoryService.get(id));
  }

  @Override
  @GetMapping
  public List<CategoryDto> getAll() {
    return forEach(categoryService.getAll(), DtoMapper.INSTANCE::toCategoryDto);
  }

  @Override
  @PutMapping
  public CategoryDto save(@RequestBody final CategoryDto categoryDto) {
    final Category category = DtoMapper.INSTANCE.toCategory(categoryDto);
    return DtoMapper.INSTANCE.toCategoryDto(categoryService.save(category));
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    categoryService.delete(id);
  }
}
