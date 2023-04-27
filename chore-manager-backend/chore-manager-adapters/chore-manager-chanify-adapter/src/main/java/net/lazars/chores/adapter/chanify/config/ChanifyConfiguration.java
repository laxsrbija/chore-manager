package net.lazars.chores.adapter.chanify.config;

import net.lazars.chores.adapter.chanify.client.ChanifyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class ChanifyConfiguration {

  @Bean
  public ChanifyClient chanifyClient() {
    final Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://api.chanify.net/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    return retrofit.create(ChanifyClient.class);
  }
}
