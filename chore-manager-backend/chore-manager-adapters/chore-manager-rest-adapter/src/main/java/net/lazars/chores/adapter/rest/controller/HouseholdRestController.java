package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.HouseholdDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.HouseholdService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGE')")
@RequestMapping(path = "/api/rest/households", produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseholdRestController implements CrudOperations<HouseholdDto> {

  private final HouseholdService householdService;

  @Override
  @GetMapping("{id}")
  public HouseholdDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toHouseholdDto(householdService.get(id));
  }

  @Override
  @GetMapping
  public List<HouseholdDto> getAll() {
    return forEach(householdService.getAll(), DtoMapper.INSTANCE::toHouseholdDto);
  }

  @Override
  @PutMapping
  public HouseholdDto save(final HouseholdDto householdDto) {
    final Household household = DtoMapper.INSTANCE.toHousehold(householdDto);
    return DtoMapper.INSTANCE.toHouseholdDto(householdService.save(household));
  }

  @Override
  @DeleteMapping
  public void delete(final String id) {
    householdService.delete(id);
  }
}
