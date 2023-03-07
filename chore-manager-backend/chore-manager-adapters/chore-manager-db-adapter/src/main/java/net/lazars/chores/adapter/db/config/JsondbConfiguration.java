package net.lazars.chores.adapter.db.config;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.adapter.db.entity.CategoryDocument;
import net.lazars.chores.adapter.db.entity.HouseholdDocument;
import net.lazars.chores.adapter.db.entity.ItemDocument;
import net.lazars.chores.adapter.db.entity.TaskDocument;
import net.lazars.chores.adapter.db.entity.UserDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JsondbConfiguration {
  private static final String ENTITIES_PACKAGE = UserDocument.class.getPackageName();

  @Value("${chores.db.store:/tmp}")
  private String storeDirectory;

  private static void registerCollections(final JsonDBTemplate jsonDBTemplate) {
    final List<Class<?>> classes =
        List.of(
            UserDocument.class,
            TaskDocument.class,
            CategoryDocument.class,
            ItemDocument.class,
            HouseholdDocument.class);

    for (final Class<?> clazz : classes) {
      if (!jsonDBTemplate.collectionExists(clazz)) {
        jsonDBTemplate.createCollection(clazz);
      }
    }
  }

  @Bean
  public JsonDBTemplate getJsonDBTemplate() {
    final JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(storeDirectory, ENTITIES_PACKAGE);
    registerCollections(jsonDBTemplate);

    return jsonDBTemplate;
  }
}
