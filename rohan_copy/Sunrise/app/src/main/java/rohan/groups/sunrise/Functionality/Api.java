package rohan.groups.sunrise.Functionality;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("orderssunrise.php")
    Call<ResponseBody> sendsms(
            @Field("amount") Integer amount
    );
}
