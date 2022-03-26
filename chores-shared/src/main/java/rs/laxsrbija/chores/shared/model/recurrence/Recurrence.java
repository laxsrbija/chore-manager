package rs.laxsrbija.chores.shared.model.recurrence;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
	@Type(value = DynamicRecurrence.class, name = "dynamic"),
	@Type(value = FixedRecurrence.class, name = "fixed")
})
public interface Recurrence
{

}
