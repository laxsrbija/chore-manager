package rs.laxsrbija.chores.adapter.in.web;

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
import rs.laxsrbija.chores.application.port.in.UserInboundPort;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.User;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements CrudOperations<User> {

  public final UserInboundPort userInboundPort;

  @Override
  @GetMapping("{id}")
  public User get(@PathVariable final String id) {
    return userInboundPort.get(id);
  }

  @Override
  @GetMapping
  public List<User> getAll() {
    return userInboundPort.getAll();
  }

  @Override
  @PutMapping
  public User save(@RequestBody final User user) {
    return userInboundPort.save(user);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    userInboundPort.delete(id);
  }
}
