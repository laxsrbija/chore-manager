package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.ItemDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.ItemService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGE')")
@RequestMapping(path = "/api/rest/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemRestController implements CrudOperations<ItemDto> {

  private final ItemService itemService;

  @Override
  @GetMapping("{id}")
  public ItemDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toItemDto(itemService.get(id));
  }

  @Override
  @GetMapping
  public List<ItemDto> getAll() {
    return forEach(itemService.getAll(), DtoMapper.INSTANCE::toItemDto);
  }

  @Override
  @PutMapping
  public ItemDto save(@RequestBody final ItemDto itemDto) {
    final Item item = DtoMapper.INSTANCE.toItem(itemDto);
    return DtoMapper.INSTANCE.toItemDto(itemService.save(item));
  }

  @PostMapping
  public List<ItemDto> saveAll(@RequestBody final List<ItemDto> items) {
    return items.stream().map(this::save).toList();
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    itemService.delete(id);
  }
}
