package com.yin.myhealthy.view.manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;

import com.example.myhealthy.R;

public class TopBarManager implements OnTabChangeListener, OnPageChangeListener {

	/**
	 * Tab选项卡的文字
	 */
	private String mTextviewArray[] = { "资讯", "饮食", "药品", "百科" };

	/*
	 * 导航条对应的文字
	 */

	// 定义常量表示当前处于哪个页面,常量与标题名的顺序必须一致
	private final int NEWS = 0;
	private final int DIET = 1;
	private final int MEDICINE = 2;
	private final int KNOWLEDGE = 3;

	// 当前页面号
	private int currentPage = NEWS;

	private Activity activity;

	/*
	 * 各个页面处理
	 */

	// 新闻页面方面
	private ViewPager vp_news;
	private RadioGroup rg_news;
	private LinearLayout ll_news;

	/*
	 * 对应列表名: "企业要闻", // id=1 "医疗新闻", // id=2 "生活贴士", // id=3 "药品新闻", // id=4
	 * "食品新闻", // id=5 "社会热点", // id=6 "疾病快讯" // id=7
	 */
	private int rb_news_id_arry[] = { R.id.rb_topbar_news_1,
			R.id.rb_topbar_news_2, R.id.rb_topbar_news_3,
			R.id.rb_topbar_news_4, R.id.rb_topbar_news_5,
			R.id.rb_topbar_news_6, R.id.rb_topbar_news_7, };

	private List<RadioButton> rb_news_list = new ArrayList<RadioButton>();

	// 饮食方面

	/*
	 * 亮发 id:1 健脑 id:2 温肺 id:3 有益心血管 id:4 明目 id:5 益乳 id:6 健脾 id:7 和胃 id:8 益肝
	 * id:9 补肾 id:10 润肠 id:11 强筋 id:12 壮骨 id:13 养颜护肤 id:14 通血 id:15 发汗解表 id:16
	 * 消炎止痛 id:17 降糖消渴 id:18 抑癌抗瘤 id:19 调经 id:20 养阴补虚 id:21 抗衰抗辐射 id:22 其他功效
	 * id:23 补充能量 id:24 提高免疫力 id:25 减肥 id:26 安神除烦 id:27 疗头痛头晕 id:28 受孕 id:29
	 */

	private ViewPager vp_diet;
	private RadioGroup rg_diet;
	private LinearLayout ll_diet;

	private int rb_diet_id_arry[] = { R.id.rb_topbar_diet_1,
			R.id.rb_topbar_diet_2, R.id.rb_topbar_diet_3,
			R.id.rb_topbar_diet_4, R.id.rb_topbar_diet_5,
			R.id.rb_topbar_diet_6, R.id.rb_topbar_diet_7, };

	private List<RadioButton> rb_diet_list = new ArrayList<RadioButton>();

	// 药箱方面
	private ViewPager vp_medicine;
	private RadioGroup rg_medicine;
	private LinearLayout ll_medicine;

	// 百科方面
	private ViewPager vp_knowledge;
	private RadioGroup rg_knowledge;
	private LinearLayout ll_knowledge;

	// 单例模式
	// 1.定义静态变量
	private static TopBarManager instance = null;

	// 2.构造函数私有化
	private TopBarManager() {
	}

	// 3.定义共有方法获取TopBarManager对象
	public static TopBarManager getInstance() {
		if (instance == null) {
			instance = new TopBarManager();
		}
		return instance;
	}

	/*
	 * 初始化各个Viewpager
	 */
	public void setNewsVp(ViewPager vp) {
		this.vp_news = vp;
	}

	public void setDietVp(ViewPager vp) {
		this.vp_diet = vp;
	}

	public void setMedicineVp(ViewPager vp) {
		this.vp_medicine = vp;
	}

	public void setKnoweledgeVp(ViewPager vp) {
		this.vp_knowledge = vp;
	}

	// 初始化按钮控件
	public void initView(Activity activity) {
		this.activity = activity;

		// 新闻页面
		ll_news = (LinearLayout) activity.findViewById(R.id.ll_topbar_news);
		rg_news = (RadioGroup) activity.findViewById(R.id.rg_topbar_news);
		ll_news.setVisibility(View.GONE); // 设置不可见

		for (int i = 0; i < rb_news_id_arry.length; i++) {
			rb_news_list.add((RadioButton) activity
					.findViewById(rb_news_id_arry[i]));
		}

		// 饮食页面
		ll_diet = (LinearLayout) activity.findViewById(R.id.ll_topbar_diet);
		rg_diet = (RadioGroup) activity.findViewById(R.id.rg_topbar_diet);
		ll_diet.setVisibility(View.GONE); // 设置不可见

		for (int i = 0; i < rb_diet_id_arry.length; i++) {
			rb_diet_list.add((RadioButton) activity
					.findViewById(rb_diet_id_arry[i]));
		}

		setListener(); // 设置监听
		reflashView(); // 刷新页面

	}

	// 设置监听
	private void setListener() {
		rg_news.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_news_id_arry.length; i++) {
					if (checkedId == rb_news_id_arry[i]) {
						vp_news.setCurrentItem(i);
					}
				}
			}
		});

		rg_diet.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < rb_diet_id_arry.length; i++) {
					if (checkedId == rb_diet_id_arry[i]) {
						vp_diet.setCurrentItem(i);
					}
				}
			}
		});

	}

	private void reflashView() {
		// 清楚所有已有
		// ll_news.removeAllViews();

		// 根据当前页面号来设置标题
		switch (currentPage) {
		case NEWS:
			ll_diet.setVisibility(View.GONE);

			ll_news.setVisibility(View.VISIBLE);

			break;

		case DIET:
			ll_news.setVisibility(View.GONE);

			ll_diet.setVisibility(View.VISIBLE);

			break;

		case MEDICINE:
			ll_medicine.setVisibility(View.VISIBLE);

			break;
		case KNOWLEDGE:
			break;

		}
	}

	
	//对底部菜单tabHost进行监听
	@Override
	public void onTabChanged(String tabId) {
		// 如果页面设置当前页面号
		for (int i = 0; i < mTextviewArray.length; i++) {
			if (tabId == mTextviewArray[i]) {
				currentPage = i;
				Log.d("onTabChanged", "当前页号： " + String.valueOf(i));
			}
		}

		// 刷新标题栏
		reflashView();
	}

	@Override
	public void onPageScrollStateChanged(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// 通过handler
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			int id = (Integer) msg.obj;

			/*
			 * 假如直接从radiogroup设置里面的radiobutton选中，会导致viewpager不停地切换
			 * 这是因为从radiogroup里面设置check，会导致radiobutton的click被重复调用很多次
			 * radiobutton的点击事件能让viewpager切换，所以viewpager就会不停切换
			 * 
			 * 解决方法：直接调用对应的radiobutton的setChecked
			 */

			if (currentPage == NEWS) {
				// rg_news.check(rb_news_arry[id]);
				rb_news_list.get(id).setChecked(true);
			}
			
			
			if (currentPage == DIET) {
				rb_diet_list.get(id).setChecked(true);
			}
		};
	};

	@Override
	public void onPageSelected(int id) {
		// 假如直接改RadioGroup会导致滑动不流畅,所以采用message+handler
		// if (currentPage == NEWS) {
		// rg_news.check(rb_news_arry[id]);
		// }

		Message msg = new Message();
		msg.obj = id;
		handler.sendMessage(msg);
	}

}
