package com.example.jsq;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	public TextView jieguo;
	char zfc[] = new char[100], zhifu[] = new char[100];
	int zfc1 = 1, zhifu1 = 1, shuzhi1 = 1, k = 0;
	double shuzhi[] = new double[100];

	public void dj0(View view) {
		zfc[zfc1] = '0';
		zfc1++;
		xianshi();

	}

	public void dj1(View view) {
		zfc[zfc1] = '1';
		zfc1++;
		xianshi();
	}

	public void dj2(View view) {
		zfc[zfc1] = '2';
		zfc1++;
		xianshi();
	}

	public void dj3(View view) {
		zfc[zfc1] = '3';
		zfc1++;
		xianshi();
	}

	public void dj4(View view) {
		zfc[zfc1] = '4';
		zfc1++;
		xianshi();
	}

	public void dj5(View view) {
		zfc[zfc1] = '5';
		zfc1++;
		xianshi();
	}

	public void dj6(View view) {
		zfc[zfc1] = '6';
		zfc1++;
		xianshi();
	}

	public void dj7(View view) {
		zfc[zfc1] = '7';
		zfc1++;
		xianshi();
	}

	public void dj8(View view) {
		zfc[zfc1] = '8';
		zfc1++;
		xianshi();
	}

	public void dj9(View view) {
		zfc[zfc1] = '9';
		zfc1++;
		xianshi();
	}

	public void djjia(View view) {
		zfc[zfc1] = '+';
		zfc1++;
		xianshi();
	}

	public void djjian(View view) {
		zfc[zfc1] = '-';
		zfc1++;
		xianshi();
	}

	public void djcheng(View view) {
		zfc[zfc1] = '*';
		zfc1++;
		xianshi();
	}

	public void djchu(View view) {
		zfc[zfc1] = '/';
		zfc1++;
		xianshi();
	}

	public void djdengyu(View view) {
		zfc[zfc1] = '=';
		zfc1++;
		xianshi();
		jishuan();
	}

	public void djtuige(View view) {
		if (zfc1>1) {
			zfc1--;
		}
		xianshi();
	}

	public void djqingping(View view) {
		zfc1 = 1;
		zhifu1 = 1;
		shuzhi1 = 1;
		k = 0;
		xianshi();
		TextView lieshi = (TextView) findViewById(R.id.lieshi);
		lieshi.setText("");
		TextView jieguo = (TextView) findViewById(R.id.jieguo);
		jieguo.setText("");
		
	}

	public void djkuohao(View view) {
		if (k == 0) {
			zfc[zfc1] = '(';
			zfc1++;
			k = 1;
			xianshi();
		} else {
			zfc[zfc1] = ')';
			zfc1++;
			k = 0;
			xianshi();

		}
	}

	public void xianshi() {
		int i;
		String s = "";
		for (i = 1; i < zfc1; i++) {
			s = s + zfc[i];
		}
		TextView lieshi = (TextView) findViewById(R.id.lieshi);
		lieshi.setText(s);

	}

	public void jishuan() {

		int sz = 0, i = 0, j = 0, ii;
		double jg = 0;

		try {
			for (ii = 1; ii <= zfc1; ii++) {
				if (zfc[ii] >= '0' && zfc[ii] <= '9') {
					sz += (int) zfc[ii] - (int) '0';
					sz *= 10;
				} else {
					if (zfc[ii] != '(' && zfc[ii] != ')'
							&& zhifu[zhifu1 - 1] != ')') {
						zhifu[zhifu1] = zfc[ii];
						shuzhi[shuzhi1] = sz / 10;
						shuzhi1++;
						zhifu1++;
						sz = 0;
					} else if (zfc[ii] != '(' && zfc[ii] != ')'
							&& zhifu[zhifu1 - 1] == ')') {
						zhifu[zhifu1] = zfc[ii];
						shuzhi[shuzhi1] = 99999;
						shuzhi1++;
						zhifu1++;

					} else if (zfc[ii] == '(') {
						shuzhi[shuzhi1] = 99999;
						zhifu[zhifu1] = zfc[ii];
						shuzhi1++;
						zhifu1++;
					} else if (zfc[ii] == ')') {
						shuzhi[shuzhi1] = sz / 10;
						zhifu[zhifu1] = zfc[ii];
						shuzhi1++;
						zhifu1++;
						shuzhi[shuzhi1] = 99999;
						sz = 0;
					}

				}
			}
		} catch (Exception e) {

			Log.e("ERROR", "jishuan ");
			TextView jieguo = (TextView) findViewById(R.id.jieguo);
			e.printStackTrace();
			System.out.println(e);
			jieguo.setText("异常");
		}

		try {

			while (true)
			{

				j = 1;
				while (zhifu[j] != '=')
					j++;
				i = 1;
				while (zhifu[i] != '(' && zhifu[i] != '=')
					i++;
				if (i != j) {
					j = 1;
					while (zhifu[j] != ')')
					{
						j++;
					}
					js(i + 1, j);

					shuzhi[i + 2] = shuzhi[i + 1];
					yiwei(i, 2);
				} else
					break;
			}
			i = 1;
			while (zhifu[i] != '=')
				i++;
			jg = js(1, i);

			TextView jieguo = (TextView) findViewById(R.id.jieguo);
			jieguo.setText(jg + "");
		} catch (Exception e) {
			// TextView jieguo = (TextView)findViewById(R.id.jieguo);

			// jieguo.setText("有异常");
		}

	}

	public double js(int low, int tou) {
		int i, j;

		for (i = low; i <= tou;)
		{
			if (zhifu[i] == '*') {
				shuzhi[i + 1] = shuzhi[i] * shuzhi[i + 1];
				yiwei(i, 1);
				tou--;
			} else if (zhifu[i] == '/') {
				shuzhi[i + 1] = shuzhi[i] / shuzhi[i + 1];
				yiwei(i, 1);
				tou--;
			} else if (zhifu[1] == '=' || zhifu[1] == ')') {
				return shuzhi[i];
			} else
				i++;

		}

		for (j = low; j <= tou;)
		{
			if (zhifu[j] == '+') {
				shuzhi[j + 1] = shuzhi[j] + shuzhi[j + 1];
				yiwei(j, 1);
				tou--;
			} else if (zhifu[j] == '-') {
				shuzhi[j + 1] = shuzhi[j] - shuzhi[j + 1];
				yiwei(j, 1);
				tou--;
			} else if (zhifu[1] == '=' || zhifu[1] == ')') {
				return shuzhi[j];
			} else
				j++;

		}

		return 0;

	}

	public void yiwei(int i, int j) {
		while (zhifu[i + j] != '=')
		{
			zhifu[i] = zhifu[i + j];
			shuzhi[i] = shuzhi[i + j];
			i++;
		}

		zhifu[i] = zhifu[i + j];
		shuzhi[i] = shuzhi[i + j];

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

}
