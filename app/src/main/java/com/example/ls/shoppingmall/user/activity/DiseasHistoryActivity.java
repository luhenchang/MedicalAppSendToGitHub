package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiseasHistoryActivity extends AppCompatActivity {

    @Bind(R.id.usermessage_back_iv)
    ImageView messageBackIv;
    @Bind(R.id.save_user_tv)
    TextView tvEdetor;
    @Bind(R.id.editSms)
    EditText editSms;
    @Bind(R.id.title_top)
    TextView titleTops;
    private String caseHistory="病历史";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_diseas_history);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        Intent intent = getIntent();
        if (intent != null) {
            if(intent.getStringExtra("disHearsh")!=null){
                caseHistory = intent.getStringExtra("disHearsh");
                editSms.setText(caseHistory);

            }

        }
        setLisenner();
        initView();
    }

    private void setLisenner() {
        InputFilter inputFilter = new InputFilter() {

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\.\\,\\!\\“\\”\\/\\@\\`\\~\\(\\)\\*\\&\\%\\#\\^\\-\\+\\[\\]\\?\\u4E00-\\u9FA5_]");

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.find()) {
                    return null;
                } else {
                    Toast.makeText(DiseasHistoryActivity.this, "只能输入汉子和字母数字", Toast.LENGTH_SHORT).show();
                    return "";
                }

            }
        };
        editSms.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(220)});

    }

    private void initView() {
        titleTops.setText("病历史");
        tvEdetor.setText("保存");
    }
    public static boolean isChinese(String inPut) {
        String regex = "^[\u4e00-\u9fa5]|[\\d|\\w]|[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(inPut);
        return match.matches();
    }
    @OnClick({R.id.usermessage_back_iv, R.id.save_user_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.usermessage_back_iv:
                finish();
                break;
            case R.id.save_user_tv:
                if (!TextUtils.isEmpty(editSms.getText().toString().trim())/*&&isChinese(editSms.getText().toString())*/) {
                    Intent mIntent = new Intent();
                    mIntent.putExtra("result", editSms.getText().toString().trim());
                    // 设置结果，并进行传送
                    this.setResult(1000, mIntent);
                    finish();
                }else{
                    Toast.makeText(this, "请输入汉字", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


}
