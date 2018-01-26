package com.ayj.chunmiao.view.AddAndSub;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;

import com.ayj.chunmiao.R;

public class MyCustomDialog extends Dialog {
	public EditText editText;
	public TextView positiveButton, negativeButton;
	public TextView tv_title;
	private MyAddAndSubEditTextView select_count;
	private int count = 1;

	private int min;
	private int max;
	
	
	public MyCustomDialog(Context context) {
		super(context, R.style.CustomProgressDialog);
		setCustomDialog();
	}

	
	public MyCustomDialog(Context context, int min, int max) {
		super(context, R.style.CustomProgressDialog);
		this.min = min;
		this.max = max;
		setCustomDialog();
	}


	public void setCustomDialog() {
		View mView = LayoutInflater.from(getContext()).inflate(R.layout.my_custom_dialog, null);
		select_count = (MyAddAndSubEditTextView) mView.findViewById(R.id.select_count);
		select_count.setMAX_MARK(max);
		select_count.setMIN_MARK(min);
		tv_title = (TextView) mView.findViewById(R.id.title);
		positiveButton = (TextView) mView.findViewById(R.id.positiveButton);
		negativeButton = (TextView) mView.findViewById(R.id.negativeButton);
		super.setContentView(mView);
	}

	public void setTitle(String title) {
		tv_title.setText(title);
	}

	@Override
	public void setContentView(int layoutResID) {
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
	}

	@Override
	public void setContentView(View view) {
	}

	/**
	 * 确定键监听器
	 */
	public void setOnPositiveListener(View.OnClickListener listener) {
		positiveButton.setOnClickListener(listener);
	}

	/**
	 * 取消键监听器
	 */
	public void setOnNegativeListener(View.OnClickListener listener) {
		negativeButton.setOnClickListener(listener);
	}

	public int getCount() {
		int count2 = select_count.getCount();
		if (count2>1) {
			return select_count.getCount();
		} else {
			return 1;
		}
	}

	public void setCount(int count) {
		select_count.setCount(count);
	}

}
