package com.example.waterfalls;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Flowlayout layout;
    private String[] str = {"支付宝钱包", "QQ", "微信", "美团", "大众点评", "星海教育学生端",
            "小米视频", "手机淘宝", "天猫", "唯品会", "ZAKER", "简书", "高德地图", "百度地图", "小米商城",
            "QQ音乐", "酷狗", "Google Play", "FaceBook", "Linkedin", "Google+",
            "易信", "QQ空间", "拉勾网", "FaceBook", "Google", "网易云音乐"};

    // , "FaceBook","Google","网易云音乐"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (Flowlayout) this.findViewById(R.id.label_parent);
        int backColor = 0xffcecece;
        Drawable pressedDrawable = DrawableUtils.creatshape(backColor);// 按下时显示的图片
        for (int i = 0; i < str.length; i++) {
            TextView textView = new TextView(this);
            System.out.println("第" + (i + 1) + "个控件");
            // 保证背景色部位白色跟黑色
            int red = new Random().nextInt(220) + 20;
            int green = new Random().nextInt(220) + 20;
            int blue = new Random().nextInt(220) + 20;
            int color = Color.rgb(red, green, blue);
            textView.setText(str[i]);
            textView.setPadding(UiUtils.dip2px(10), UiUtils.dip2px(10),
                    UiUtils.dip2px(10), UiUtils.dip2px(10));
            // 代码设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            StateListDrawable listDrawable = DrawableUtils
                    .createSelectorDrawable(pressedDrawable,
                            DrawableUtils.creatshape(color));
            textView.setBackgroundDrawable(listDrawable);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            // 这里得设为true，new出来的TextView默认是无法点击的
            textView.setClickable(true);
            layout.addView(textView);
        }
    }

}
