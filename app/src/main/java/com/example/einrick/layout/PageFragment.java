package com.example.einrick.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    //private ArrayList<HighScoreItem> list = new ArrayList<HighScoreItem>();
    //private RecyclerView recyclerView;
    //private HighScoreAdapter adapter;
    private int mPage;
    private DBHandler db;
    private static highscores hi = null;

    public static PageFragment newInstance(int page, highscores h) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        hi = h;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        db = hi.getDB();
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_game, container, false);
        ArrayList<HighScoreItem> list = new ArrayList<HighScoreItem>();
        RecyclerView recyclerView = view.findViewById(R.id.hsrecyclerview);
        HighScoreAdapter adapter = new HighScoreAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
        if (mPage != 0){
            highScoreModel(mPage, adapter, list);
        }
        return view;
    }

    private void highScoreModel(int n, HighScoreAdapter adapter, ArrayList<HighScoreItem> list){
        //parameter of getHighScores method is the game played (ex. patterngame, spotthebear, tapchloe, and bingobear)
        ArrayList<UserVersusUser> uvulist;
        if (n == 1){
            uvulist = db.getHighScores("patterngame");
            int id;
            for (int i=0; i<uvulist.size(); i++){
                UserVersusUser uvu = uvulist.get(i);
                id = getResources().getIdentifier(uvu.getWinner().getIcon(), "drawable", hi.getPackageName());
                list.add(new HighScoreItem(id, uvu.getWinner().getUsername(), uvu.getScoreWin()));
            }

            adapter.notifyDataSetChanged();
        }else if (n == 2){
            uvulist = db.getHighScores("tapchloe");
            int id;
            for (int i=0; i<uvulist.size(); i++){
                UserVersusUser uvu = uvulist.get(i);
                id = getResources().getIdentifier(uvu.getWinner().getIcon(), "drawable", hi.getPackageName());
                list.add(new HighScoreItem(id, uvu.getWinner().getUsername(), uvu.getScoreWin()));
            }

            adapter.notifyDataSetChanged();
        }else if (n == 3){
            uvulist = db.getHighScores("spotthebear");
            int id;
            for (int i=0; i<uvulist.size(); i++){
                UserVersusUser uvu = uvulist.get(i);
                id = getResources().getIdentifier(uvu.getWinner().getIcon(), "drawable", hi.getPackageName());
                list.add(new HighScoreItem(id, uvu.getWinner().getUsername(), uvu.getScoreWin()));
            }

            adapter.notifyDataSetChanged();
        }else if (n == 4){
            uvulist = db.getHighScoresBB();
            int id;
            for (int i=0; i<uvulist.size(); i++){
                UserVersusUser uvu = uvulist.get(i);
                id = getResources().getIdentifier(uvu.getWinner().getIcon(), "drawable", hi.getPackageName());
                list.add(new HighScoreItem(id, uvu.getWinner().getUsername(), uvu.getScoreWin()));
            }

            adapter.notifyDataSetChanged();
        }

    }
}