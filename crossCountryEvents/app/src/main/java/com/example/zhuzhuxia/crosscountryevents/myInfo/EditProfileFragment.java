package com.example.zhuzhuxia.crosscountryevents.myInfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.MainActivity;
import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.model.userInfo;
import com.example.zhuzhuxia.crosscountryevents.myMatchList.myMatchActivity;
import com.example.zhuzhuxia.crosscountryevents.utils.ImgUtils;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.util.Map;



/**
 * Created by Administrator.
 */

public class EditProfileFragment extends Fragment {

    private static final int FROM_CAMERA = 2;
    private static final int FROM_ALBUM = 1;
    private static final int CROP = 0;


    private Toolbar mTitlebar;
    private View mAvatarView;
    private ImageView mAvatarImg;
    private ProfileEdit mNickNameEdt;
    private ProfileEdit mGenderEdt;
    private ProfileEdit mSignEdt;
//    private ProfileEdit mRenzhengEdt;
    private ProfileEdit mLocationEdt;

    private ProfileEdit mIdEdit;
    private ProfileTextView mLevelView;
    private ProfileEdit mGetNumsView;
    private ProfileTextView mSendNumsView;

    private Button mCompleteBtn;


    private boolean change=false;
//    private PicChooserHelper mPicChooserHelper;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            getSelfInfo();
        } else {
            //相当于Fragment的onPause
            getSelfInfo();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        findAllViews(mainView);
        setListeners();
        setTitleBar();
        setIconKey();//设置字段和icon
        getSelfInfo();

        return mainView;
    }

    private void getSelfInfo() {
        updateViews(CrossCountryApplication.getApplication().getSelfProfile());
//            }
//                updateViews(timUserProfile);
//        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
//            @Override
//            public void onError(int i, String s) {
//                Toast.makeText(getActivity(), "获取信息失败：" + s, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess(TIMUserProfile timUserProfile) {
//                //获取自己信息成功
//                mUserProfile = timUserProfile;
//                updateViews(timUserProfile);
//            }
//        });
    }

    private void updateViews(userInfo userInfo) {
        //更新界面
        String faceUrl = "";
//        String faceUrl = "http://b.hiphotos.baidu.com/image/pic/item/314e251f95cad1c85e377d83733e6709c83d5182.jpg";
        if (TextUtils.isEmpty(faceUrl)) {
            ImgUtils.loadRound(R.drawable.default_avatar, mAvatarImg);
        } else {
            ImgUtils.loadRound(faceUrl, mAvatarImg);
        }
        mNickNameEdt.updateValue(userInfo.nickname);
//        long genderValue = timUserProfile.getGender().getValue();
//        String genderStr = genderValue == 1 ? "男" : "女";
        mGenderEdt.updateValue(userInfo.sex);

        mSignEdt.updateValue(userInfo.sign);
        mLocationEdt.updateValue(userInfo.email);
        mIdEdit.updateValue(userInfo.telPhone);
        mLevelView.updateValue(userInfo.match_times+"");
        if(userInfo.match_times>0){
//            mGetNumsView.set(R.drawable.ic_info_get, "", "我的比赛");
        }else{
            mGetNumsView.setVisibility(View.INVISIBLE);
        }
//        Map<String, byte[]> customInfo = timUserProfile.getCustomInfo();
//        mRenzhengEdt.updateValue(getValue(customInfo, CustomProfile.CUSTOM_RENZHENG, "未知"));
//        mLevelView.updateValue(getValue(customInfo, CustomProfile.CUSTOM_LEVEL, "0"));
//        mGetNumsView.updateValue(getValue(customInfo, CustomProfile.CUSTOM_GET, "0"));
//        mSendNumsView.updateValue(getValue(customInfo, CustomProfile.CUSTOM_SEND, "0"));
    }

    private String getValue(Map<String, byte[]> customInfo, String key, String defaultValue) {
        if (customInfo != null) {
            byte[] valueBytes = customInfo.get(key);
            if (valueBytes != null) {
                return new String(valueBytes);
            }
        }
        return defaultValue;
    }

    private void setIconKey() {
        mNickNameEdt.set(R.drawable.ic_info_nickname, "昵称", "");
        mGenderEdt.set(R.drawable.ic_info_gender, "性别", "");
        mSignEdt.set(R.drawable.ic_info_sign, "签名", "无");
//        mRenzhengEdt.set(R.drawable.ic_info_renzhen, "认证", "未知");
        mLocationEdt.set(R.drawable.ic_info_location, "email", "未知");
        mIdEdit.set(R.drawable.ic_info_id, "telPhone", "");
        mLevelView.set(R.drawable.ic_info_level, "参加比赛次数", "0");
        mGetNumsView.set(R.drawable.ic_info_get, "", "我的比赛");
//        mSendNumsView.set(R.drawable.ic_info_send, "送出票数", "0");
    }

    private void findAllViews(View view) {
        mTitlebar = (Toolbar) view.findViewById(R.id.title_bar);

        mAvatarView = view.findViewById(R.id.avatar);
        mAvatarImg = (ImageView) view.findViewById(R.id.avatar_img);
        mNickNameEdt = (ProfileEdit) view.findViewById(R.id.nick_name);
        mGenderEdt = (ProfileEdit) view.findViewById(R.id.gender);
        mSignEdt = (ProfileEdit) view.findViewById(R.id.sign);
//        mRenzhengEdt = (ProfileEdit) view.findViewById(R.id.renzheng);
        mLocationEdt = (ProfileEdit) view.findViewById(R.id.location);

        mIdEdit = (ProfileEdit) view.findViewById(R.id.id);


        mLevelView = (ProfileTextView) view.findViewById(R.id.level);
        mGetNumsView = (ProfileEdit) view.findViewById(R.id.get_nums);
//        mSendNumsView = (ProfileTextView) view.findViewById(R.id.send_nums);

        mCompleteBtn = (Button) view.findViewById(R.id.complete);
    }

    private void setListeners() {
        mAvatarView.setOnClickListener(clickListener);
        mNickNameEdt.setOnClickListener(clickListener);
        mGenderEdt.setOnClickListener(clickListener);
        mSignEdt.setOnClickListener(clickListener);
//        mRenzhengEdt.setOnClickListener(clickListener);
        mIdEdit.setOnClickListener(clickListener);
        mLocationEdt.setOnClickListener(clickListener);
        mCompleteBtn.setOnClickListener(clickListener);
        mGetNumsView.setOnClickListener(clickListener);
    }

    private void setTitleBar() {
        mTitlebar.setTitle("编辑个人信息");
        mTitlebar.setTitleTextColor(Color.WHITE);
        Activity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).setSupportActionBar(mTitlebar);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.avatar) {
                //修改头像
//                choosePic();
            } else if (id == R.id.nick_name) {
                //修改昵称
                showEditNickNameDialog();
            } else if (id == R.id.gender) {
                //修改性别
                showEditGenderDialog();
            } else if(id == R.id.id){
                //修改电话
                showEditTelDialog();
            }else if (id == R.id.sign) {
                //修改签名
                showEditSignDialog();
            } else if (id == R.id.location) {
                //修改位置
                showEditLocationDialog();
            } else if (id == R.id.complete) {
                //完成，点击跳转到主界面
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
            }else if(id == R.id.get_nums){
                //查看自己报名的比赛
                //todo
                Intent intent = new Intent();
                intent.setClass(getContext(), myMatchActivity.class);
//                CrossCountryApplication.getApplication().setSelfProfile(userInfo);
//                intent.putExtra("roomId", roomInfo.roomId);
                startActivity(intent);

            }
        }
    };

    private void showEditTelDialog() {
        EditStrProfileDialog dialog = new EditStrProfileDialog(getActivity());
        dialog.setOnOKListener(new EditStrProfileDialog.OnOKListener() {
            @Override
            public void onOk(String title, final String content) {
                edit_request(CrossCountryApplication.getApplication().getSelfProfile().username,"telphone",content);

            }
        });

        dialog.show("电话", R.drawable.ic_info_location, mIdEdit.getValue());
    }

//    private void choosePic() {
//        if (mPicChooserHelper == null) {
//            mPicChooserHelper = new PicChooserHelper(this, PicChooserHelper.PicType.Avatar);
//            mPicChooserHelper.setOnChooseResultListener(new PicChooserHelper.OnChooseResultListener() {
//                @Override
//                public void onSuccess(String url) {
//                    updateAvatar(url);
//                }
//
//                @Override
//                public void onFail(String msg) {
//                    Toast.makeText(getActivity(), "选择失败：" + msg, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        mPicChooserHelper.showPicChooserDialog();
//    }

    private void showEditLocationDialog() {
        EditStrProfileDialog dialog = new EditStrProfileDialog(getActivity());
        dialog.setOnOKListener(new EditStrProfileDialog.OnOKListener() {
            @Override
            public void onOk(String title, final String content) {
                edit_request(CrossCountryApplication.getApplication().getSelfProfile().username,"email",content);

            }
        });

        dialog.show("邮箱", R.drawable.ic_info_location, mLocationEdt.getValue());

    }

    private void showEditSignDialog() {
        EditStrProfileDialog dialog = new EditStrProfileDialog(getActivity());
        dialog.setOnOKListener(new EditStrProfileDialog.OnOKListener() {
            @Override
            public void onOk(String title, final String content) {
                edit_request(CrossCountryApplication.getApplication().getSelfProfile().username,"sign",content);
            }
        });
        dialog.show("签名", R.drawable.ic_info_sign, mSignEdt.getValue());
    }

    private void showEditGenderDialog() {
        EditGenderDialog dialog = new EditGenderDialog(getActivity());
        dialog.setOnChangeGenderListener(new EditGenderDialog.OnChangeGenderListener() {
            @Override
            public void onChangeGender(boolean isMale) {
                edit_request(CrossCountryApplication.getApplication().getSelfProfile().username,"sex",isMale?"男":"女");
            }
        });
        dialog.show(mGenderEdt.getValue().equals("男"));
    }



    private void showEditNickNameDialog() {
        EditStrProfileDialog dialog = new EditStrProfileDialog(getActivity());
        dialog.setOnOKListener(new EditStrProfileDialog.OnOKListener() {
            @Override
            public void onOk(String title, final String content) {
                edit_request(CrossCountryApplication.getApplication().getSelfProfile().username,"nickname",content);

            }
        });
        dialog.show("昵称", R.drawable.ic_info_nickname, mNickNameEdt.getValue());
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (mPicChooserHelper != null) {
//            mPicChooserHelper.onActivityResult(requestCode, resultCode, data);
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//
//    }


//    private void updateAvatar(String url) {
//        TIMFriendshipManager.getInstance().setFaceUrl(url, new TIMCallBack() {
//
//            @Override
//            public void onError(int i, String s) {
//                Toast.makeText(getActivity(), "头像更新失败：" + s, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess() {
//                //更新头像成功
//                getSelfInfo();
//            }
//        });
//    }

    public void edit_request(String username,String changeFiled,String changeValue){

        EdtiRequest.EditParam param = new EdtiRequest.EditParam();

        param.action = "Edit";
        param.username = username;
        param.changeFiled = changeFiled;
        param.changeValue = changeValue;

        //想服务器进行申请
        EdtiRequest request = new EdtiRequest();
        request.setOnResultListener(new baseRequest.OnResultListener<userInfo>() {
            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(getContext(), "修改失败:" + msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(userInfo info) {

                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                intent.setClass(getContext(), MainActivity.class);
//                CrossCountryApplication.getApplication().setSelfProfile(userInfo);
////                intent.putExtra("roomId", roomInfo.roomId);
//                startActivity(intent);
                CrossCountryApplication.getApplication().setSelfProfile(info);
                getSelfInfo();
//                finish();
            }
        });

        String requestUrl = request.getUrl(param);
        request.request(requestUrl);
    }


}
