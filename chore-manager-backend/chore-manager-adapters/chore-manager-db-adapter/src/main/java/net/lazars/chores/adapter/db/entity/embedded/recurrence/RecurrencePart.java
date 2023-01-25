package net.lazars.chores.adapter.db.entity.embedded.recurrence;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = DynamicRecurrencePart.class, name = "dynamic"),
  @Type(value = FixedRecurrencePart.class, name = "fixed")
})
public interface RecurrencePart {}
