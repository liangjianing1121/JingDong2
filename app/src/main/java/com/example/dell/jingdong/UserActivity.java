package com.example.dell.jingdong;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.User;
import common.Api;
import common.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import presenter.UserPresenter;
import view.UserView;

import static java.lang.String.valueOf;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private RelativeLayout rl_icon;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_head;
    private ImageView iv_fanhui;
    private TextView tv_yhm;
    private RelativeLayout rl_username;
    private TextView tv_username;
    private RelativeLayout rl_nc;

    @Override
    protected void onResume() {
        super.onResume();
        UserPresenter presenter=new UserPresenter(this,this);
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter.requestUser(uid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initData();
    }

    private void initData() {
        UserPresenter presenter=new UserPresenter(this,this);
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        presenter.requestUser(uid);


    }

    private void initView() {
        rl_icon = (RelativeLayout) findViewById(R.id.rl_icon);
        iv_fanhui = (ImageView) findViewById(R.id.iv_fanhui);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        tv_yhm = (TextView) findViewById(R.id.tv_yhm);
        rl_username = (RelativeLayout) findViewById(R.id.rl_username);
        rl_username.setOnClickListener(this);
        tv_username = (TextView) findViewById(R.id.tv_username);
        rl_nc = (RelativeLayout) findViewById(R.id.rl_nc);
        rl_icon.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);
        rl_nc.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.iv_fanhui:
                finish();
                break;
            case  R.id.rl_icon:
                showChoosePicDialog();
                break;
            case R.id.rl_nc:

                Intent intent=new Intent(this,ChangeActivity.class);
                startActivity(intent);

                break;
        }
    }
    private void showChoosePicDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Utils utils=new Utils();
            photo = utils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            iv_head.setImageBitmap(photo);
            setFile(photo);
            uploadPic();

        }
    }
    private void setFile(Bitmap photo) {
        File file=new File("mnt/sdcard/mo.jpg");
        try {
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bout);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传头像
     * @param
     */
    private void  uploadPic() {

        File img=new File("mnt/sdcard/mo.jpg");
        SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        System.out.println(img.getName() + img.toString() + "===========================");
        OkHttpClient okHttpClient1 = new OkHttpClient();


        MultipartBody.Builder builder1 = new MultipartBody.Builder();
        builder1.setType(MultipartBody.FORM);

        builder1.addFormDataPart("uid", uid + "");
        builder1.addFormDataPart("file", img.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), img));

        Request request1 = new Request.Builder().post(builder1.build()).url(Api.UPLOAD).build();

        okHttpClient1.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("++++++++++++Fileure++++++++++++++");
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    System.out.println("++++++++++++Ssuccess++++++++++++++");
                    System.out.println("fileuploadsuccess：" + response.body().string());
                }
            }
        });
    }

    @Override
    public void getUserSuccess(final User.DataBean data) {
        if(this!=null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String icon = data.icon;
                    tv_username.setText(data.username);
                    Glide.with(UserActivity.this).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv_head);
                    tv_yhm.setText(data.nickname);
                }
            });
        }

    }

    @Override
    public void getUserFailure(String msg) {

    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
