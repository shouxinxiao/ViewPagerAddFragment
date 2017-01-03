package com.xin.viewpageraddfragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;


/**
 * 封装好了ViewPager和指示器的BaseFragment
 * 
 * @author xin
 * @date 2016-9-22
 */
public abstract class BaseViewPagerFragment extends Fragment {
	protected PagerSlidingTabStrip slidingTab;
	protected ViewPager mViewPager;
	protected GridView mGridView;
	protected ImageButton ib_more;
//	protected EmptyLayout mErrorLayout; // 布局加载异常，显示的空布局

	protected ViewPageFragmentAdapter mAdapter;
	private PopupWindow mPopupWindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_viewpage, null);
		slidingTab = (PagerSlidingTabStrip) view
				.findViewById(R.id.pager_tabstrip);
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		ib_more = (ImageButton) view.findViewById(R.id.ib_more);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// 空布局
//		mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);

		mAdapter = new ViewPageFragmentAdapter(getActivity(),getChildFragmentManager(),slidingTab);
		addFragment(mAdapter);
		mViewPager.setAdapter(mAdapter);
		slidingTab.setViewPager(mViewPager);

		// 设置ViewPager左右两边保留页面的个数, 这里为空实现, 子类可以重写此方法进行设置
		setScreenPageLimit();

		ib_more.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View paramView) {
				setGridViewShow();// 显示或隐藏GridView
			}
		});

	}

	private void setGridViewShow() { // 显示GridView
		View view = View.inflate(getActivity(),R.layout.gridview,null);
		mGridView = (GridView) view.findViewById(R.id.gridview);
		mGridView.setNumColumns(setGridViewNumColumns());
		mGridView.setAdapter(mAdapter.mGridViewAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mViewPager.setCurrentItem(position);
				mPopupWindow.dismiss();
			}
		});
		int[] location = new int[2];
		mViewPager.getLocationOnScreen(location);
		mPopupWindow =new PopupWindow(view,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null)); //设置背景
		mPopupWindow.setAnimationStyle(R.style.AnimationCategory);
		mPopupWindow.showAtLocation(mViewPager,Gravity.NO_GRAVITY, location[0], location[1]-mPopupWindow.getHeight()-2);	
		
	}

	public abstract void addFragment(ViewPageFragmentAdapter adapter); // 给ViewPager添加fragment

	protected int setGridViewNumColumns() { // 可用于更改GridView列数的方法
		return 5;
	}

	protected void setScreenPageLimit() { // 设置ViewPager左右两边保留页面的个数
	}



}
