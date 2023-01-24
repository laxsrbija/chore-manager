package net.lazars.chores.core.model.recurrence;

// @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
// @JsonSubTypes({
//    @Type(value = DynamicRecurrence.class, name = "dynamic"),
//    @Type(value = FixedRecurrence.class, name = "fixed")
// })
public interface Recurrence {}
