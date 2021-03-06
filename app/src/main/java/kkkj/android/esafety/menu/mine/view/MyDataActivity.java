package kkkj.android.esafety.menu.mine.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guoqi.actionsheet.ActionSheet;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.BindView;
import kkkj.android.esafety.R;
import kkkj.android.esafety.app.ESafety;
import kkkj.android.esafety.bean.Auth_UserProfile;
import kkkj.android.esafety.http.ApiConfig;
import kkkj.android.esafety.http.api.UploadCallbacks;
import kkkj.android.esafety.menu.mine.contract.MyDataContract;
import kkkj.android.esafety.menu.mine.model.UserSetProfileModel;
import kkkj.android.esafety.menu.mine.presenter.MyDataPresenter;
import kkkj.android.esafety.model.UpLoadFileModel;
import kkkj.android.esafety.mvpInterface.MvpBaseActivity;
import kkkj.android.esafety.utils.ImageLoader;

public class MyDataActivity extends MvpBaseActivity<MyDataPresenter> implements MyDataContract.View, View.OnClickListener, ActionSheet.OnActionSheetSelected {
    @BindView(R.id.ll_touxiang)
    LinearLayout ll_touxiang;
    @BindView(R.id.iv_touxiang)
    ImageView iv_touxiang;
    @BindView(R.id.btn_sub)
    Button btn_sub;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_tel)
    EditText ed_tel;
    ImagePicker imagePicker;
    ImagePicker.Callback callback;
    Uri mImageUri;

    Auth_UserProfile userProfile;

    @Override
    protected int getLayout() {
        return R.layout.activity_mydata;
    }

    @Override
    protected MyDataPresenter getPresenter() {
        return new MyDataPresenter();
    }

    @Override
    protected void initMonitorAndData() {
        action_bar_title.setText("????????????");
        ll_touxiang.setOnClickListener(this);
        btn_sub.setOnClickListener(this);

        userProfile = ESafety.getInstance().getUserProfile();
        if (userProfile != null) {
            if (!TextUtils.isEmpty(userProfile.getCNName())) {
                ed_name.setText(userProfile.getCNName());
            }

            if (!TextUtils.isEmpty(userProfile.getTel())) {
                ed_tel.setText(userProfile.getTel());
            }

            if (!TextUtils.isEmpty(userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL))) {
                ImageLoader.load(mContext, iv_touxiang, userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL), 20);
            }
        }


        imagePicker = new ImagePicker();
        // ????????????
        imagePicker.setTitle("????????????");
        // ????????????????????????
        imagePicker.setCropImage(true);
        callback = new ImagePicker.Callback() {
            // ??????????????????
            @Override
            public void onPickImage(Uri imageUri) {

            }

            // ??????????????????
            @Override
            public void onCropImage(Uri imageUri) {
                mImageUri = imageUri;
                ImageLoader.load(mContext, iv_touxiang, mImageUri, 20);
            }

            // ?????????????????????
            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // ????????????????????????
                        .setMultiTouchEnabled(false)
                        // ????????????????????????
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // ??????/??????
                        .setCropShape(CropImageView.CropShape.OVAL)
                        // ????????????????????????????????????
//                        .setRequestedSize(960, 540)
                        // ?????????
                        .setAspectRatio(16, 16);
            }

            // ????????????????????????
            @Override
            public void onPermissionDenied(int requestCode, String[] permissions,
                                           int[] grantResults) {
                showToast("?????????????????????");
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_touxiang:
                ActionSheet.showSheet(mContext, this, null);
                break;
            case R.id.btn_sub:
                if (!TextUtils.isEmpty(ed_name.getText().toString().trim())) {
                    userProfile.setCNName(ed_name.getText().toString().trim());
                } else {
                    showToast("????????????????????????");
                    return;
                }
                userProfile.setTel(ed_tel.getText().toString().trim());
                if (mImageUri != null) {
                    UpLoadFileModel.Request request = new UpLoadFileModel.Request();
                    if (!TextUtils.isEmpty(mImageUri.getPath())) {
                        request.setFile(new File(mImageUri.getPath()));
                    }
                    request.setMediaType("image");
                    UploadCallbacks mListener = new UploadCallbacks() {
                        @Override
                        public void onProgressUpdate(int percentage) {
//                        Logger.d(percentage);
                        }

                        @Override
                        public void onError() {
                            Logger.d("??????");
                        }

                        @Override
                        public void onFinish() {

                        }
                    };
                    request.setListener(mListener);
                    mPresenter.uploadfile(request);
                } else {
                    setProFile();
                }
        }
    }

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case ActionSheet.CHOOSE_PICTURE:
                //??????
//                choosePic();
                imagePicker.startGallery(mActivity, callback);
                break;
            case ActionSheet.TAKE_PICTURE:
                //??????
//                takePic();
                imagePicker.startCamera(mActivity, callback);
                break;
            case ActionSheet.CANCEL:
                //??????
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(mActivity, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(mActivity, requestCode, permissions, grantResults);
    }

    @Override
    public void uploadfileSuc(UpLoadFileModel.Response response) {
        Logger.d(response.getData());
        userProfile.setHeadIMG(response.getData());
        setProFile();
    }

    public void setProFile() {
        UserSetProfileModel.Request request = new UserSetProfileModel.Request();
        request.setHeadIMG(userProfile.getHeadIMG());
        request.setCNName(userProfile.getCNName());
        request.setLogin(userProfile.getLogin());
        request.setTel(userProfile.getTel());
        mPresenter.setprofile(request);
    }

    @Override
    public void setProFileSuc() {
        showToast("??????????????????");
        ESafety.getInstance().setUserProfile(userProfile);
//        if (userProfile != null) {
//            if (!TextUtils.isEmpty(userProfile.getCNName())) {
//                ed_name.setText(userProfile.getCNName());
//            }
//
//            if (!TextUtils.isEmpty(userProfile.getTel())) {
//                ed_tel.setText(userProfile.getTel());
//            }
//
//            if (!TextUtils.isEmpty(userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL))) {
//                ImageLoader.load(mContext, iv_touxiang, userProfile.getHeadIMG().replace("~", ApiConfig.BASE_URL), 20);
//            }
//        }
    }

}
