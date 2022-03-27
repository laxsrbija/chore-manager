package rs.laxsrbija.chores.data.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.jsondb.JsonDBTemplate;
import lombok.extern.slf4j.Slf4j;
import rs.laxsrbija.chores.data.entity.*;

@Slf4j
@Configuration
public class JsondbConfiguration
{
	private static final String STORE_DIRECTORY = "/c/chores/store";
	private static final String ENTITIES_PACKAGE = UserEntity.class.getPackageName();

	@Bean
	public JsonDBTemplate getJsonDBTemplate()
	{
		final JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(STORE_DIRECTORY, ENTITIES_PACKAGE);
		registerCollections(jsonDBTemplate);

		return jsonDBTemplate;
	}

	private static void registerCollections(final JsonDBTemplate jsonDBTemplate)
	{
		final List<Class<?>> classes = List.of(UserEntity.class, TaskEntity.class, CategoryEntity.class, ObjectEntity.class);

		for (final Class<?> clazz : classes)
		{
			if (!jsonDBTemplate.collectionExists(clazz))
			{
				jsonDBTemplate.createCollection(clazz);
			}
		}
	}
}
