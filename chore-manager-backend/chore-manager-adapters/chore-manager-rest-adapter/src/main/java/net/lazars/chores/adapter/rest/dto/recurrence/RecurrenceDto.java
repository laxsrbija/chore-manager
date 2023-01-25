package net.lazars.chores.adapter.rest.dto.recurrence;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = DynamicRecurrenceDto.class, name = "dynamic"),
  @Type(value = FixedRecurrenceDto.class, name = "fixed")
})
public interface RecurrenceDto {}
