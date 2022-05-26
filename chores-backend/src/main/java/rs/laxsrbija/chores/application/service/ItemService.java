package rs.laxsrbija.chores.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.application.port.in.ItemInboundPort;
import rs.laxsrbija.chores.application.port.out.ItemOutboundPort;
import rs.laxsrbija.chores.domain.Item;

@Service
@RequiredArgsConstructor
class ItemService implements ItemInboundPort {

  private final ItemOutboundPort itemOutboundPort;

  @Override
  public Item get(final String id) {
    return itemOutboundPort.get(id);
  }

  @Override
  public List<Item> getAll() {
    return itemOutboundPort.getAll();
  }

  @Override
  public Item save(final Item object) {
    return itemOutboundPort.save(object);
  }

  @Override
  public void delete(final String id) {
    itemOutboundPort.delete(id);
  }
}
