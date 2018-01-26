package com.ayj.chunmiao.view.AddAndSub;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;

public class MyAddAndSubView extends LinearLayout implements OnClickListener {

	private Context mContext;
	private TextView tv_left_sub;
	private TextView tv_middle_content;
	private TextView tv_right_add;
	private int count = 1;

	private int min;
	private int max;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
		count = max == 0 ? 0 :1;
		setCount(count);
	}

	public MyAddAndSubView(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public MyAddAndSubView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		TypedArray obtainAttributes = context.obtainStyledAttributes(attrs, R.styleable.MyAddAndSubView);
		// TypedArray obtainAttributes2 = getResources().obtainAttributes(null,
		// R.styleable.MyAddAndSubView);
		max = obtainAttributes.getInt(R.styleable.MyAddAndSubView_max, 0);
		min = obtainAttributes.getInt(R.styleable.MyAddAndSubView_min, 0);
		obtainAttributes.recycle();
		initView();
	}

	private void initView() {
		View view = View.inflate(mContext, R.layout.subview, null);
		tv_left_sub = (TextView) view.findViewById(R.id.tv_left_sub);
		tv_middle_content = (TextView) view.findViewById(R.id.tv_middle_content);
		tv_right_add = (TextView) view.findViewById(R.id.tv_right_add);
		addView(view);
		// if ("1".equals(tv_middle_content.getText().toString())) {
		// tv_left_sub.setTextColor(Color.GREEN);
		// }
		tv_middle_content.setText(String.valueOf(count));
		tv_left_sub.setOnClickListener(this);
		tv_middle_content.setOnClickListener(this);
		tv_right_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 红色部分会提示它不是静态常量，咦？它怎么不是常量了？呵呵别担心，它还是常量，但是它不再是静态的了，
		// 因为库文件是要动态加载的，解决办法也很简单呢，就是把switch...case
		// 改成if...else就搞定，eclipse提示说摁下ctrl+1能够自动把switch...case改成是if...else，
		// 我试了下怎么不可以呢，你知道吗快捷键吗，tell me!
		// switch (v.getId()) {
		// case R.id.tv_left_sub:
		// // if (count == 1) {
		// // tv_left_sub.setTextColor(Color.GREEN);
		// // } else {
		// // count--;
		// // if (count == 1) {
		// // tv_left_sub.setTextColor(Color.GREEN);
		// // } else {
		// // tv_left_sub.setTextColor(Color.BLACK);
		// // }
		// // tv_middle_content.setText(String.valueOf(count));
		// // }
		// if (count > 1) {
		// count--;
		// tv_middle_content.setText(String.valueOf(count));
		// } else {
		// tv_middle_content.setText(String.valueOf(count));
		// }
		// onClickAddAndSubListener.clickSub(count);
		// break;
		// case R.id.tv_middle_content:
		// // 弹一个Dialog
		// final MyCustomDialog dialog = new MyCustomDialog(mContext, min, max);
		// dialog.setTitle("修改购买数量");
		// dialog.setCount(Integer.parseInt(tv_middle_content.getText().toString()));
		// dialog.setOnPositiveListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// int countPositive = dialog.getCount();
		// // if (countPositive == 1) {
		// // tv_left_sub.setTextColor(Color.GREEN);
		// // } else {
		// // tv_left_sub.setTextColor(Color.BLACK);
		// // }
		// tv_middle_content.setText(String.valueOf(countPositive));
		// count = countPositive;
		// // Log.e("countPositive", "countPositive=" + countPositive);
		// onPosNegListener.onMyPositiveListener(count);
		// dialog.dismiss();
		// }
		// });
		// dialog.setOnNegativeListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// int countNegative = dialog.getCount();
		// onPosNegListener.onMyPositiveListener(countNegative);
		// dialog.dismiss();
		// // Log.e("countNegative", "countNegative=" + countNegative);
		// }
		// });
		// dialog.setCancelable(true);
		// dialog.setCanceledOnTouchOutside(false);
		// dialog.show();
		//
		// break;
		// case R.id.tv_right_add:
		//
		// if (count >= 1) {
		// count++;
		// tv_middle_content.setText(String.valueOf(count));
		// }
		// onClickAddAndSubListener.clickAdd(count);
		// break;
		//
		// default:
		// break;
		// }

		if (v.getId() == R.id.tv_left_sub) {
			if (count > 1) {
				count--;
				tv_middle_content.setText(String.valueOf(count));
			} else {
				tv_middle_content.setText(String.valueOf(count));
			}
			onClickAddAndSubListener.clickSub(count);
		} else if (v.getId() == R.id.tv_middle_content) {
			// 弹一个Dialog
			final MyCustomDialog dialog = new MyCustomDialog(mContext, min, max);
			dialog.setTitle("修改购买数量");
			dialog.setCount(Integer.parseInt(tv_middle_content.getText().toString()));
			dialog.setOnPositiveListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int countPositive = dialog.getCount();
					// if (countPositive == 1) {
					// tv_left_sub.setTextColor(Color.GREEN);
					// } else {
					// tv_left_sub.setTextColor(Color.BLACK);
					// }
					tv_middle_content.setText(String.valueOf(countPositive));
					count = countPositive;
					// Log.e("countPositive", "countPositive=" + countPositive);
					onPosNegListener.onMyPositiveListener(count);
					dialog.dismiss();
				}
			});
			dialog.setOnNegativeListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int countNegative = dialog.getCount();
					onPosNegListener.onMyPositiveListener(countNegative);
					dialog.dismiss();
					// Log.e("countNegative", "countNegative=" + countNegative);
				}
			});
			dialog.setCancelable(true);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		} else if (v.getId() == R.id.tv_right_add) {
			if (count < max) {
                    count++;
                    tv_middle_content.setText(String.valueOf(count));
			}
			onClickAddAndSubListener.clickAdd(count);
		}
	}

	public interface OnPosNegListener {
		void onMyPositiveListener(int count);

		void onMyNegativeListener(int count);
	}

	public OnPosNegListener onPosNegListener;

	public OnPosNegListener getOnPosNegListener() {
		return onPosNegListener;
	}

	public void setOnPosNegListener(OnPosNegListener onPosNegListener) {
		this.onPosNegListener = onPosNegListener;
	}

	public int getCount() {
		return Integer.parseInt(tv_middle_content.getText().toString());
	}

	public void setCount(int count) {
		this.count = count;
		tv_middle_content.setText(String.valueOf(count));
	}

	public interface OnClickAddAndSubListener {
		void clickAdd(int count);

		void clickSub(int count);
	}

	public OnClickAddAndSubListener onClickAddAndSubListener;

	public OnClickAddAndSubListener getOnClickAddAndSubListener() {
		return onClickAddAndSubListener;
	}

	public void setOnClickAddAndSubListener(OnClickAddAndSubListener onClickAddAndSubListener) {
		this.onClickAddAndSubListener = onClickAddAndSubListener;
	}

}
