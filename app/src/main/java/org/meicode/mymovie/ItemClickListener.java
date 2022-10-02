package org.meicode.mymovie;

import android.view.View;

public interface ItemClickListener <T>{
    void onClick(View view , int position , T data);
}
