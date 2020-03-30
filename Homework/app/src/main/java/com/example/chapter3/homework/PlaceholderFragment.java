package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private RecyclerView recyclerView;
    private ArrayList<String> data;
    private MyAdapter myAdapter;
    private AnimatorSet animatorSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // DONE ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view=inflater.inflate(R.layout.fragment_placeholder, container, false);
        data=new ArrayList<>();
        for (int i=1;i<=100;i++){
            data.add("item "+i);
        }
        animationView=view.findViewById(R.id.animation_view);
        recyclerView=view.findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(getActivity(),data);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // DONE ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,
                        "alpha",1,0);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(recyclerView,
                        "alpha",0,1);
                animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1,animator2);
                animatorSet.start();
            }
        }, 5000);
    }
}
