package com.ayj.chunmiao.view.AddAndSub;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;

public class MyAddAndSubEditTextView extends LinearLayout implements OnClickListener, TextWatcher {

	private Context mContext;
	private TextView tv_left_sub;
	private EditText et_middle_content;
	private TextView tv_right_add;
	private int count = 1;
	private OnAmountChangeListener onAmountChangeListener;

	public int getCount() {
		return Integer.parseInt(et_middle_content.getText().toString());
	}

	public void setCount(int count) {
		this.count = count;
		et_middle_content.setText(String.valueOf(count));
		// if (count == 1) {
		// tv_left_sub.setTextColor(Color.GREEN);
		// } else {
		// tv_left_sub.setTextColor(Color.BLACK);
		// }
	}

	public MyAddAndSubEditTextView(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public MyAddAndSubEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initView();
	}

	private void initView() {
		View view = View.inflate(mContext, R.layout.subview_edittext, null);
		tv_left_sub = (TextView) view.findViewById(R.id.tv_left_sub);
		et_middle_content = (EditText) view.findViewById(R.id.et_middle_content);
		tv_right_add = (TextView) view.findViewById(R.id.tv_right_add);
		et_middle_content.addTextChangedListener(this);
		addView(view);
		tv_left_sub.setOnClickListener(this);
		tv_right_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// switch (v.getId()) {
		// 红色部分会提示它不是静态常量，咦？它怎么不是常量了？呵呵别担心，它还是常量，但是它不再是静态的了，
		// 因为库文件是要动态加载的，解决办法也很简单呢，就是把switch...case
		// 改成if...else就搞定，eclipse提示说摁下ctrl+1能够自动把switch...case改成是if...else，
		// 我试了下怎么不可以呢，你知道吗快捷键吗，tell me!

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
		// // et_middle_content.setText(String.valueOf(count));
		// // }
		// if (count > 1) {
		// count--;
		// et_middle_content.setText(String.valueOf(count));
		// } else {
		// et_middle_content.setText(String.valueOf(count));
		// }
		// break;
		// case R.id.tv_right_add:
		// // count++;
		// // if (count == 1) {
		// // tv_left_sub.setTextColor(Color.GREEN);
		// // } else {
		// // tv_left_sub.setTextColor(Color.BLACK);
		// // et_middle_content.setText(String.valueOf(count));
		// // }
		// if (count >= 1) {
		// count++;
		// et_middle_content.setText(String.valueOf(count));
		// }
		// break;
		//
		// default:
		// break;
		// }

		if (v.getId() == R.id.tv_left_sub) {
			if (count > 1) {
				count--;
				et_middle_content.setText(String.valueOf(count));
			} else {
				et_middle_content.setText(String.valueOf(count));
			}
		} else if (v.getId() == R.id.tv_right_add) {
			if (count >= 1) {
				count++;
				et_middle_content.setText(String.valueOf(count));
			}
		}
		et_middle_content.clearFocus();
		if (onAmountChangeListener!=null){
			onAmountChangeListener.onAmountChange(this,count);
		}
	}

	private int MIN_MARK;
	private int MAX_MARK;

	public int getMIN_MARK() {
		return MIN_MARK;
	}

	public void setMIN_MARK(int mIN_MARK) {
		MIN_MARK = mIN_MARK;
	}

	public int getMAX_MARK() {
		return MAX_MARK;
	}

	public void setMAX_MARK(int mAX_MARK) {
		MAX_MARK = mAX_MARK;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//		Log.e("beforeTextChanged,s=", s.toString());
//		Log.e("beforeTextChanged,start=", String.valueOf(start));
//		Log.e("beforeTextChanged,count=", String.valueOf(count));
//		Log.e("beforeTextChanged,after=", String.valueOf(after));
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		/*if (start > 1) {
			if (MIN_MARK != -1 && MAX_MARK != -1) {
				int num = Integer.parseInt(s.toString());
				if (num > MAX_MARK) {
					s = String.valueOf(MAX_MARK);
					et_middle_content.setText(s);
				} else if (num < MIN_MARK)
					s = String.valueOf(MIN_MARK);
				return;
			}
		}*/
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.toString().isEmpty())
			return;
		count = Integer.valueOf(s.toString());
		if (count > MAX_MARK) {
			et_middle_content.setText(MAX_MARK + "");
			return;
		}

		if (onAmountChangeListener != null) {
			onAmountChangeListener.onAmountChange(this, count);
		}
		/*if (s != null && !"".equals(s)) {
			if (MIN_MARK != -1 && MAX_MARK != -1) {
				int markVal = 0;
				try {
					markVal = Integer.parseInt(s.toString());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if (markVal > MAX_MARK) {
					et_middle_content.setText(String.valueOf(MAX_MARK));
				}
				return;
			}
			if (onAmountChangeListener!=null){
				onAmountChangeListener.onAmountChange(this,count);
			}
		}*/
	}

	// public String getCount() {
	// return et_middle_content.getText().toString();
	// }
	//
	// public void setCount(int count) {
	// et_middle_content.setText(String.valueOf(count));
	// }
	public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
		this.onAmountChangeListener = onAmountChangeListener;
	}

	public interface OnAmountChangeListener {
		void onAmountChange(View view, int count);
	}


}
