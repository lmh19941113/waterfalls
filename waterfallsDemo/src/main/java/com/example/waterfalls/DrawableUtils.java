package com.example.waterfalls;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {
	public static GradientDrawable creatshape(int color) {
		GradientDrawable drawable = new GradientDrawable();
		// 设置控件的弧度，就是xml文件里shape的代码实现
		drawable.setCornerRadius(UiUtils.dip2px(5));
		drawable.setColor(color);
		return drawable;
	}
	
	public static StateListDrawable createSelectorDrawable(Drawable pressedDrawable,Drawable normalDrawable){
		//代码实现xml文件里面的selector
		StateListDrawable drawable=new StateListDrawable();
		//按下时显示的图片
		drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
		//没有按下时显示的图片
		drawable.addState(new int[]{}, normalDrawable);
		return drawable;
	}
}
