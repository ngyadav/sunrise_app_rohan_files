package sunrise.release.rohan.sunrise.Functionality;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("sendsms")
    Call<ResponseBody> sendsms(
            @Field("username")String username,
            @Field("password")String password,
            @Field("to")String to,
            @Field("from")String from,
            @Field("udh")String udh,
            @Field("text")String text,
            @Field("dlr-mask")String dlrmask,
            @Field("dlr-url")String dlrurl
    );
}
