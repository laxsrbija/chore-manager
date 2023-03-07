package net.lazars.chores.adapter.rest.mapper;

import net.lazars.chores.adapter.rest.dto.CategoryDto;
import net.lazars.chores.adapter.rest.dto.CompleteUserDto;
import net.lazars.chores.adapter.rest.dto.HouseholdDto;
import net.lazars.chores.adapter.rest.dto.ItemDto;
import net.lazars.chores.adapter.rest.dto.TaskDto;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.dto.recurrence.DynamicRecurrenceDto;
import net.lazars.chores.adapter.rest.dto.recurrence.FixedRecurrenceDto;
import net.lazars.chores.adapter.rest.dto.recurrence.RecurrenceDto;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.model.recurrence.DynamicRecurrence;
import net.lazars.chores.core.model.recurrence.FixedRecurrence;
import net.lazars.chores.core.model.recurrence.Recurrence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoMapper {

  DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

  Household toHousehold(HouseholdDto householdDto);

  HouseholdDto toHouseholdDto(Household household);

  Category toCategory(CategoryDto categoryDto);

  CategoryDto toCategoryDto(Category category);

  Item toItem(ItemDto itemDto);

  ItemDto toItemDto(Item item);

  @Mapping(source = "recurrence", target = "recurrence", qualifiedByName = "toRecurrence")
  Task toTask(TaskDto taskDto);

  @Mapping(source = "recurrence", target = "recurrence", qualifiedByName = "toRecurrenceDto")
  TaskDto toTaskDto(Task task);

  User toUser(CompleteUserDto userDto);

  @Named("toCompleteUserDto")
  CompleteUserDto toCompleteUserDto(User user);

  UserDto toUserDto(User user);

  @Named("toRecurrence")
  default Recurrence toRecurrence(RecurrenceDto recurrenceDto) {
    if (recurrenceDto instanceof FixedRecurrenceDto fixedRecurrenceDto) {
      return toFixedRecurrence(fixedRecurrenceDto);
    } else if (recurrenceDto instanceof DynamicRecurrenceDto dynamicRecurrenceDto) {
      return toDynamicRecurrence(dynamicRecurrenceDto);
    }

    return null;
  }

  FixedRecurrence toFixedRecurrence(FixedRecurrenceDto fixedRecurrenceDto);

  DynamicRecurrence toDynamicRecurrence(DynamicRecurrenceDto dynamicRecurrenceDto);

  @Named("toRecurrenceDto")
  default RecurrenceDto toRecurrenceDto(Recurrence recurrence) {
    if (recurrence instanceof FixedRecurrence fixedRecurrence) {
      return toFixedRecurrenceDto(fixedRecurrence);
    } else if (recurrence instanceof DynamicRecurrence dynamicRecurrence) {
      return toDynamicRecurrenceDto(dynamicRecurrence);
    }

    return null;
  }

  FixedRecurrenceDto toFixedRecurrenceDto(FixedRecurrence fixedRecurrence);

  DynamicRecurrenceDto toDynamicRecurrenceDto(DynamicRecurrence dynamicRecurrence);
}
