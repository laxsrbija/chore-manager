package net.lazars.chores.adapter.chanify.client;

import net.lazars.chores.adapter.chanify.model.ChanifyPayload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChanifyClient {

  @POST("v1/sender/{token}")
  Call<Void> publishNotification(@Path("token") String token, @Body ChanifyPayload payload);
}
