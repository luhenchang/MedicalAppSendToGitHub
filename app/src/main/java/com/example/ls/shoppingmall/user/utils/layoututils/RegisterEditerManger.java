package com.example.ls.shoppingmall.user.utils.layoututils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 路很长~ on 2017/12/11.
 */
/*来管理自己登陆和注册时候的那个输入框*/
public class RegisterEditerManger {
    private Context context;
    private EditText editText;
    private String inputString;
    private Button delet_button;
    public RegisterEditerManger(Context context, EditText editText, String inputString,Button delet_button) {
        this.context = context;
        this.editText = editText;
        this.inputString = inputString;
        this.delet_button=delet_button;
    }

    public void deletInput() {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    delet_button.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && inputString.length() > 0) {
                    delet_button.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
