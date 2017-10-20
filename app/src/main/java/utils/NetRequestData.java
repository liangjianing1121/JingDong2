package utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by DELL on 2017/10/9.
 */

public class NetRequestData {
    public static  void Call(String url, Map<String,String> params, final CallBck callBck){

        OkHttpClient client=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {

            builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        RequestBody body = builder.build();
        Request request=new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(callBck!=null)
                {
                    callBck.onFailure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(callBck!=null){

                    callBck.onSuccess(call,response);
                }
            }
        });


    }



  /*  public void upload(Map<String,Object> params){

        OkHttpClient okHttpClient=new OkHttpClient();
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {

            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if(value instanceof File)
            {

                File file= (File) value;


            }


        }











    }

*/

}
