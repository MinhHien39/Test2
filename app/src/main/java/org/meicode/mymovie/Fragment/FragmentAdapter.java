package org.meicode.mymovie.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return ListMovieFragment.newInstance("Popular");
            case 2:
                return ListMovieFragment.newInstance("TopRated");
            case 3:
                return ListMovieFragment.newInstance("Upcoming");
        }
        return ListMovieFragment.newInstance("NowPlaying");
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
