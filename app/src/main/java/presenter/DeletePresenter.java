package presenter;

import java.io.IOException;

import model.DeleteModel;
import okhttp3.Call;
import view.DeleteView;

/**
 * Created by DELL on 2017/10/21.
 */

public class DeletePresenter implements DeleteModel.onDelete {

    private DeleteModel deleteModel;
    private DeleteView deleteView;


    public DeletePresenter(DeleteView deleteView) {
        this.deleteView = deleteView;
        deleteModel=new DeleteModel();
        deleteModel.setOnDelete(this);
    }


    public void requestDelete(int uid,int pid){
        deleteModel.getDelete(uid,pid);
    }


    @Override
    public void onDeleteSuccess(String msg) {
        deleteView.onDeleteSuccess(msg);
    }

    @Override
    public void onDeleteFailure(String msg) {
        deleteView.onDeleteFailure(msg);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        deleteView.onFailure(call,e);
    }
}
