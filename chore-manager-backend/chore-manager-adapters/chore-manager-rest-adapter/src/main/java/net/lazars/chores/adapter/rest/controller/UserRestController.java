package net.lazars.chores.adapter.rest.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.UserService;
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
@RequestMapping(path = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements CrudOperations<User> {

  public final UserService userService;

  @Override
  @GetMapping("{id}")
  public User get(@PathVariable final String id) {
    return userService.get(id);
  }

  @Override
  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @Override
  @PutMapping
  public User save(@RequestBody final User user) {
    return userService.save(user);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    userService.delete(id);
  }
}
