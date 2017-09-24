package llc.taurusgroup.exampleapp.data.rest.api;

import io.reactivex.Observable;
import llc.taurusgroup.exampleapp.mvp.models.rest.GuruRequest;
import llc.taurusgroup.exampleapp.mvp.models.rest.GuruResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestServiceSimple {

    @POST("/guru")
    Observable<GuruResponse> getGuruResponse(@Body GuruRequest guruRequest);
}
