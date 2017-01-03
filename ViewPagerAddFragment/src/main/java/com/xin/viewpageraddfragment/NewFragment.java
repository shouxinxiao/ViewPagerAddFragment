package com.xin.viewpageraddfragment;

/**
 * 测试fragment继承BaseViewPagerFragment
 * 
 * @author xin
 * @date 2016-9-22
 */
public class NewFragment extends BaseViewPagerFragment {

	@Override
	public void addFragment(ViewPageFragmentAdapter adapter) {
		String[] titles = {"第一","第二","第三","第四","第五","第六","第七","第八","第九"};
		adapter.addPager(titles[2], R.drawable.tuijian,	new OneFragment());
		adapter.addPager(titles[1], R.drawable.paihangbang,	new OneFragment());
		adapter.addPager(titles[4], R.drawable.xiaoshuo,new OneFragment());
		adapter.addPager(titles[3], R.drawable.jingpin,new OneFragment());
		adapter.addPager(titles[6], R.drawable.pinglun,new OneFragment());
		adapter.addPager(titles[5], R.drawable.sanwen,new OneFragment());
		adapter.addPager(titles[8], R.drawable.zhuanti,new OneFragment());
		adapter.addPager(titles[7], R.drawable.shige,new OneFragment());

	}

	@Override
	protected int setGridViewNumColumns() { // 设置GridView的显示列数
		return 6;
	}
}
