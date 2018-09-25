package com.swiftsynq.tedanews.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.swiftsynq.tedanews.Fragment.TopHeadlinesFragment;
import com.swiftsynq.tedanews.Fragment.TopStoriesFragment;
import com.swiftsynq.tedanews.R;

public class NewsCategoryAdapter extends FragmentPagerAdapter {

    private Resources resources;
    private Context context;

    public NewsCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context=context;
        resources = context.getResources();
    }

    /** Instantiate fragment based on user horizontal scroll position */
    @Override
    public Fragment getItem(int position) {
        // Url along with API key to query the API
        String category;

        // Fragment for different news section
        Fragment fragment;

        switch (position) {
            case 0:

                // Initialize fragment with news section as argument
                fragment = TopHeadlinesFragment.newInstance();
                Toast.makeText(context,String.valueOf(position),Toast.LENGTH_LONG).show();
                // Return the fragment
                return fragment;

            case 1:
                // Url for general news section, re-use the tab title text here
                category = resources.getString(R.string.sciencecategory);
                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 2:
                // Url for technology news section
                category = resources.getString(R.string.technologycategory);
                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 3:
                // Url for health news section
                category = resources.getString(R.string.healthcategory);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 4:
                // Url for sport news section
                category = resources.getString(R.string.sportscategory);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 5:
                // Url for sport news section
                category = resources.getString(R.string.entertainmentcategory);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            case 6:
                // Url for sport news section
                category = resources.getString(R.string.musiccategory);

                // Initialize fragment with news section as argument
                fragment = TopStoriesFragment.newInstance(category);

                // Return the fragment
                return fragment;
            default:
                return null;
        }
    }

    /** Informs the adapter of the total number of available fragments views */
    @Override
    public int getCount() {
        return 7;
    }

    /** Set tab title */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.hotnewscategory);
            case 1:
                return resources.getString(R.string.sciencecategory);
            case 2:
                return resources.getString(R.string.technologycategory);
            case 3:
                return resources.getString(R.string.healthcategory);
            case 4:
                return resources.getString(R.string.sportscategory);
            case 5:
                return resources.getString(R.string.entertainmentcategory);
            case 6:
                return resources.getString(R.string.musiccategory);
            default:
                return null;

        }
    }
}
