package com.xin.viewpageraddfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**用于装载BaseViewPagerFragment
 * @author xin
 * @date 2016-9-22
 */
public class ViewPageFragmentAdapter extends FragmentPagerAdapter {
	private List<String> titles;// 指示器集合
	private List<Integer> icons;//用于装载icon的集合
	private List<Fragment> fragments;// 填充ViewPager 的fragment集合
	private PagerSlidingTabStrip slidingTab;
	private Activity mActivity;
	
	public GridViewAdapter mGridViewAdapter;

	public ViewPageFragmentAdapter(Activity activity,FragmentManager fm, PagerSlidingTabStrip slidingTab) {
		super(fm);
		this.mActivity = activity;
		this.slidingTab = slidingTab;
		this.titles = new ArrayList<String>();
		this.icons = new ArrayList<Integer>();
		this.fragments = new ArrayList<Fragment>();
		mGridViewAdapter = new GridViewAdapter();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles.get(position);
	}
	/**
	 * @param title	标题
	 * @param icon	图标
	 * @param fragment 对应的Fragment
	 */
	public void addPager(String title,int icon,Fragment fragment){	//添加fragment
		titles.add(title);
		icons.add(icon);
		fragments.add(fragment);
		 View v = LayoutInflater.from(mActivity).inflate(R.layout.fragment_base_viewpage_tab_item, null, false);
	        TextView titl = (TextView) v.findViewById(R.id.tab_title);
	        titl.setText(title);
	        slidingTab.addTab(v);
	        notifyDataSetChanged();
	}
	
	class GridViewAdapter extends BaseAdapter{//用于装载Title下拉出来的GridView
		@Override
		public int getCount() {
			return titles.size();
		}

		@Override
		public Object getItem(int position) {
			return titles.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View paramView,
				ViewGroup paramViewGroup) {
			View view = View.inflate(mActivity, R.layout.tab_indicator, null);
			RelativeLayout rl_tab_indicator_root =  (RelativeLayout) view.findViewById(R.id.rl_tab_indicator_root);
			rl_tab_indicator_root.setPadding(0, 0, 0,30);
			TextView title = (TextView) view.findViewById(R.id.tab_title);
			title.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);	//修改下拉Dialog  中title 的文字大小
			Drawable drawable = mActivity.getResources().getDrawable(icons.get(position));
			title.setCompoundDrawablesWithIntrinsicBounds(null, drawable,null, null);
			title.setCompoundDrawablePadding(10);
			title.setText(titles.get(position));
			return view;
		}
	}
}
