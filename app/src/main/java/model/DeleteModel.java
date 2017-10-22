package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Delete;
import common.Api;
import okhttp3.Call;
import okhttp3.Response;
import utils.CallBck;
import utils.NetRequestData;

/**
 * Created by DELL on 2017/10/21.
 */

public class DeleteModel {

    private onDelete onDelete;
    public void getDelete(int uid ,int pid){
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid+"");
        params.put("pid",pid+"");
        NetRequestData.Call(Api.DELETE, params, new CallBck() {
            @Override
            public void onFailure(Call call, IOException e) {

                onDelete.onFailure(call,e);
            }

            @Override
            public void onSuccess(Call call, Response response) {

                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    Delete delete = gson.fromJson(result, Delete.class);
                    String code = delete.code;
                    String msg = delete.msg;
                    if("0".equals(code))
                    {
                        onDelete.onDeleteSuccess(msg);
                    }

                    else
                    {
                        onDelete.onDeleteFailure(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setOnDelete(DeleteModel.onDelete onDelete) {
        this.onDelete = onDelete;
    }

    public interface onDelete{
        void onDeleteSuccess(String msg);
        void onDeleteFailure(String msg);
        void onFailure(Call call,IOException e);
    }


}
