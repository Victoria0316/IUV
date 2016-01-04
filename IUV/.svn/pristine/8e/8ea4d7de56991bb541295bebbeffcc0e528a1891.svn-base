package bluemobi.iuv.fragment;

import java.util.HashMap;

import android.support.v4.app.Fragment;

/**
 * liufy  Fragment 工厂类
 */
public class FragmentFactory {
	private static final int Title_HOME = 0;
	private static final int Title_APP = 1;

	private static FragmentFactory sFragmentFactory;

	private FragmentFactory() {
	}

	public synchronized static FragmentFactory get() {
		if (null == sFragmentFactory) {
			sFragmentFactory = new FragmentFactory();
		}
		return sFragmentFactory;
	}

	private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

	public  BaseFragment createFragment(int position) {

		BaseFragment fragment = mFragments.get(position);

		if(fragment == null){
			switch (position) {
			case Title_HOME:
	            fragment = new HomeMineFragment();
				break;

			case Title_APP:
	            fragment = new HomeSearchFragment();
				break;


			}
			mFragments.put(position, fragment);
		}


		return fragment;
	}
}
