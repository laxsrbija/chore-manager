package net.lazars.chores.adapter.sqlite.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;

@Converter
public class LocalDateIsoConverter implements AttributeConverter<LocalDate, String> {

  @Override
  public String convertToDatabaseColumn(final LocalDate date) {
    return date == null ? null : date.toString();
  }

  @Override
  public LocalDate convertToEntityAttribute(final String value) {
    return value == null ? null : LocalDate.parse(value);
  }
}
