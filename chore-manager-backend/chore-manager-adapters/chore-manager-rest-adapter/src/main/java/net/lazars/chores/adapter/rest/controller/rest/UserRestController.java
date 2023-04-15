package net.lazars.chores.adapter.rest.controller.rest;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.CompleteUserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.UserService;
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
@RequestMapping(path = "/api/rest/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements CrudOperations<CompleteUserDto> {

  private final UserService userService;

  @Override
  @GetMapping("{id}")
  public CompleteUserDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toCompleteUserDto(userService.get(id));
  }

  @Override
  @GetMapping
  public List<CompleteUserDto> getAll() {
    return forEach(userService.getAll(), DtoMapper.INSTANCE::toCompleteUserDto);
  }

  @Override
  @PutMapping
  public CompleteUserDto save(@RequestBody final CompleteUserDto userDto) {
    final User user = DtoMapper.INSTANCE.toUser(userDto);
    return DtoMapper.INSTANCE.toCompleteUserDto(userService.save(user));
  }

  @PostMapping
  public List<CompleteUserDto> saveAll(@RequestBody final List<CompleteUserDto> users) {
    return users.stream().map(this::save).toList();
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    userService.delete(id);
  }
}
