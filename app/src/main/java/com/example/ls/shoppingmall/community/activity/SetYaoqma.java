package com.example.ls.shoppingmall.community.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.user.bean.UserMessageBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetYaoqma extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.tv_search_home)
    EditText tvSearchHome;
    @Bind(R.id.medical_search_tv)
    TextView medicalSearchTv;
    @Bind(R.id.heaer_padter)
    LinearLayout heaerPadter;
    private Map<String, Object> parames;
    private String userId;
    private Map<String, Object> userInter;
    private String invcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_yaoqma);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID").toString();
        isGray();
        titleTop.setText("填写邀请码");
    }
    //这里初始化数据
    private void isGray() {
        //Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        Map<String, Object> map = new HashedMap();
        map.put("id", userId);
        FrameHttpHelper.getInstance().post(NetConfig.USER_MESSAGE_GET, map, new FrameHttpCallback<UserMessageBean>() {
            @Override
            public void onSuccess(UserMessageBean us) {
                Log.e("ur.tosting", us.toString());
                if (us.getRESCOD().equals("000000")){
                   if(us.getRESOBJ().getInvcode()!=null&&us.getRESOBJ().getInvcode().length()>0){
                       medicalSearchTv.setBackgroundResource(R.drawable.search_home_shape_gray);
                       medicalSearchTv.setTextColor(Color.GRAY);
                       tvSearchHome.setText(us.getRESOBJ().getInvcode());
                       tvSearchHome.setCursorVisible(false);
                       tvSearchHome.setEnabled(false);
                   }
                } else {

                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    @OnClick({R.id.back_to_after, R.id.medical_search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.medical_search_tv:
                invcode = tvSearchHome.getText().toString().trim();
                if (!TextUtils.isEmpty(invcode)) {
                    sendYaoqmToServer();

                } else {
                    Toast.makeText(this, "请输入邀请码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void sendYaoqmToServer() {
        parames = new HashMap<>();
        Log.e("yhm","https://qy.healthinfochina.com:8080/USR000010010?id=" + userId + "&invcode=" + invcode);
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/USR000010010?id=" + userId + "&invcode=" + invcode, parames, new FrameHttpCallback<RegisterResultBean>() {
            @Override
            public void onSuccess(RegisterResultBean o) {
                if(o.getRESCOD().equals("000000")){
                    medicalSearchTv.setBackgroundResource(R.drawable.search_home_shape_gray);
                    medicalSearchTv.setTextColor(Color.GRAY);
                    Toast.makeText(SetYaoqma.this,o.getRESMSG()+"", Toast.LENGTH_SHORT).show();
                    tvSearchHome.setCursorVisible(false);
                    tvSearchHome.setEnabled(false);
                }else{
                    Toast.makeText(SetYaoqma.this,o.getRESMSG()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }
}
