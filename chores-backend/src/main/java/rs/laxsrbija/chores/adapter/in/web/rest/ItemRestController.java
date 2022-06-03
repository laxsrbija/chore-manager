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
import rs.laxsrbija.chores.application.port.in.ItemInboundPort;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.Item;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemRestController implements CrudOperations<Item> {

  private final ItemInboundPort itemInboundPort;

  @Override
  @GetMapping("{id}")
  public Item get(@PathVariable final String id) {
    return itemInboundPort.get(id);
  }

  @Override
  @GetMapping
  public List<Item> getAll() {
    return itemInboundPort.getAll();
  }

  @Override
  @PutMapping
  public Item save(@RequestBody final Item object) {
    return itemInboundPort.save(object);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    itemInboundPort.delete(id);
  }
}
