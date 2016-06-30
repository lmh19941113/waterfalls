package com.example.waterfalls;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class Flowlayout extends ViewGroup {

	// @SuppressLint("NewApi")
	// public Flowlayout(Context context, AttributeSet attrs, int defStyleAttr,
	// int defStyleRes) {
	// super(context, attrs, defStyleAttr, defStyleRes);
	// }

	public Flowlayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public Flowlayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Flowlayout(Context context) {
		super(context);
	}

	// 每一列之间的间距
	private int horizontolSpacing = UiUtils.dip2px(13);
	// 每一行之间的间距
	private int verticalSpacing = UiUtils.dip2px(13);
	private Line currentline;// 当前的行
	private int useWidth = 0;// 当前行使用的宽度
	private List<Line> mLines = new ArrayList<Line>();

	private int width;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mLines.clear();
		useWidth = 0;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 获取当前父容器(Flowlayout)的模式

		width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
				- getPaddingRight();
		int height = MeasureSpec.getSize(heightMeasureSpec)
				- getPaddingBottom() - getPaddingTop();// 获取当前父容器的宽高

		int childeWidthMode;
		int childeHeightMode;

		// MeasureSpec.EXACTLY代表这个控件是一个精确地值（比如宽高为某一具体值或者父控件的宽高为match_parent），MeasureSpec.AT_MOST代表这个控件的值不确定（一般是宽高为某一不确定的值，比如控件的宽高为wrap_content）
		// 为了测量每个孩子 需要指定每个孩子测量规则
		childeWidthMode = widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
				: widthMode;

		childeHeightMode = heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST
				: heightMode;

		int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
				childeWidthMode, width);
		int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
				childeHeightMode, height);

		currentline = new Line();
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.measure(childWidthMeasureSpec, childHeightMeasureSpec);
			int childWidth = v.getMeasuredWidth();
			useWidth += childWidth;// 让当前行加上使用的长度
			if (useWidth < width) {
				currentline.addChild(v);// 这时候证明当前的孩子是可以放进当前的行里,放进去
				useWidth += horizontolSpacing;
				if (useWidth > width) {
					newLine();
				}
			} else {
				if (currentline.getChildCount() < 1) {// 保证当前行里面最少有一个孩子
					currentline.addChild(v);
				} else {
					i--;// 这里需要i--，否则会有一个不能讲当前view添加到下一行去
				}
				newLine();
			}

		}

		if (!mLines.contains(currentline)) {
			mLines.add(currentline);// 添加最后一行
		}

		int totalheight = 0;
		for (Line line : mLines) {
			totalheight += line.getHeight();
		}
		totalheight += verticalSpacing * (mLines.size() - 1) + getPaddingTop()
				+ getPaddingBottom();

		setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(),
				resolveSize(totalheight, heightMeasureSpec));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		l += getPaddingLeft();
		t += getPaddingTop();
		for (int i = 0; i < mLines.size(); i++) {
			Line line = mLines.get(i);
			line.layout(l, t); // 交给每一行去分配
			t += line.getHeight() + verticalSpacing;
		}
	}

	// 换行
	private void newLine() {
		mLines.add(currentline);// 记录之前的行
		currentline = new Line(); // 创建新的一行
		useWidth = 0;
	}

	private class Line {
		int height = 0; // 当前行的高度
		int lineWidth = 0;// 行的总高度
		private List<View> children = new ArrayList<View>();

		/**
		 * 添加一个孩子
		 * 
		 * @param child
		 */
		public void addChild(View v) {
			children.add(v);
			// 当一行中用一个控件比其他的控件要高，则高度以这个为准
			if (v.getMeasuredHeight() > height) {
				height = v.getMeasuredHeight();
			}
			lineWidth += v.getMeasuredWidth();
		}

		public void layout(int l, int t) {
			lineWidth += horizontolSpacing * (children.size() - 1);
			int surplusChild = 0;
			// 查看每一行是否有剩余的距离
			int surplus = width - lineWidth;
			if (surplus > 0) {
				// 如果有剩余的距离则平均分配给每一个子孩子
				if (children.size() != 0)
					surplusChild = surplus / children.size();
			}
			for (int i = 0; i < children.size(); i++) {
				View child = children.get(i);
				// getMeasuredWidth() 控件实际的大小
				// getWidth() 控件显示的大小i
				child.layout(l, t, l + child.getMeasuredWidth() + surplusChild,
						t + child.getMeasuredHeight());// 将多余出来的距离加到view上面
				// child.layout(l + surplusChild / 2, t,
				// l + child.getMeasuredWidth() + surplusChild / 2, t
				// + child.getMeasuredHeight());//将多余的距离没有加到view上面
				l += child.getMeasuredWidth() + surplusChild;
				l += horizontolSpacing;
			}
		}

		public int getHeight() {
			return height;
		}

		/**
		 * 返回孩子的数量
		 * 
		 * @return
		 */
		public int getChildCount() {
			return children.size();
		}

	}

}
