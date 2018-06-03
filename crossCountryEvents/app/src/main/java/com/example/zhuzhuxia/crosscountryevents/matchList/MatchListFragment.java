package com.example.zhuzhuxia.crosscountryevents.matchList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.crosscountryevents.CrossCountryApplication;
import com.example.zhuzhuxia.crosscountryevents.MatchMap.MatchIndexActivity;
import com.example.zhuzhuxia.crosscountryevents.R;
import com.example.zhuzhuxia.crosscountryevents.SignMatch.SignMatchRequest;
import com.example.zhuzhuxia.crosscountryevents.model.SignInfo;
import com.example.zhuzhuxia.crosscountryevents.model.matchInfo;
import com.example.zhuzhuxia.crosscountryevents.utils.ImgUtils;
import com.example.zhuzhuxia.crosscountryevents.utils.request.baseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhuxia on 2018/5/22.
 */

public class MatchListFragment extends Fragment {
    private ListView mMatchListView;
    private MatchListAdapter mMatchListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static final int flag_all =  0;
    public static final int flag_my =  1;

    public void setFlag(int flag) {
        this.flag = flag;
        requestLiveList();
    }

    private int flag = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);

        findAllViews(view);
        requestLiveList();

        return view;
    }

    public void setTitle(String title){
        Toolbar titlebar = (Toolbar) getActivity().findViewById(R.id.titlebar);
        titlebar.setTitle(title);
        titlebar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(titlebar);
    }

    private void requestLiveList() {
        if(flag == flag_all) {

            //请求前20个数据
            GetMatchListRequest liveListRequest = new GetMatchListRequest();
            liveListRequest.setOnResultListener(new baseRequest.OnResultListener<List<matchInfo>>() {
                @Override
                public void onSuccess(List<matchInfo> roomInfos) {

                    mMatchListAdapter.removeAllRoomInfos();//下拉刷新，先移除掉之前的room信息
                    mMatchListAdapter.addRoomInfos(roomInfos);//再添加新的信息

                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFail(int code, String msg) {
                    Toast.makeText(getActivity(), "请求列表失败：" + msg, Toast.LENGTH_SHORT).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
            GetMatchListRequest.MatchListParam param = new GetMatchListRequest.MatchListParam();

            param.action = "SearchMatchList";


//        param.pageIndex = 0;//从0开始，也就是第一页。
            String url = liveListRequest.getUrl(param);
            liveListRequest.request(url);
        }else{

            GetMatchListRequest liveListRequest = new GetMatchListRequest();
            liveListRequest.setOnResultListener(new baseRequest.OnResultListener<List<matchInfo>>() {
                @Override
                public void onSuccess(List<matchInfo> roomInfos) {

                    mMatchListAdapter.removeAllRoomInfos();//下拉刷新，先移除掉之前的room信息
                    mMatchListAdapter.addRoomInfos(roomInfos);//再添加新的信息

                    mSwipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFail(int code, String msg) {
                    Toast.makeText(getActivity(), "请求列表失败：" + msg, Toast.LENGTH_SHORT).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
            GetMatchListRequest.MyMatchListParam param = new GetMatchListRequest.MyMatchListParam();

            param.action = "MyMatchList";
            param.username = CrossCountryApplication.getApplication().getSelfProfile().username;
            param.password = CrossCountryApplication.getApplication().getSelfProfile().password;



//        param.pageIndex = 0;//从0开始，也就是第一页。
            String url = liveListRequest.getUrl(param);
            liveListRequest.request(url);
//            param.action = "";
        }

    }

    private void findAllViews(View view) {

        Toolbar titlebar = (Toolbar) view.findViewById(R.id.titlebar);
        titlebar.setTitle("所有比赛");
        titlebar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(titlebar);

        mMatchListView = (ListView) view.findViewById(R.id.live_list);
        mMatchListAdapter = new MatchListAdapter(getContext());
        mMatchListView.setAdapter(mMatchListAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_list);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //请求服务器，获取直播列表
                requestLiveList();
            }
        });
    }

    private class MatchListAdapter extends BaseAdapter {

        private Context mContext;
        private List<matchInfo> matchs = new ArrayList<matchInfo>();

        public MatchListAdapter(Context context) {
            this.mContext = context;
        }

        public void removeAllRoomInfos() {
            matchs.clear();
        }

        public void addRoomInfos(List<matchInfo> matchInfos) {
            if (matchInfos != null) {
                matchs.clear();
                matchs.addAll(matchInfos);
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return matchs.size();
        }

        @Override
        public matchInfo getItem(int position) {
            return matchs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RoomHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_match_list, null);
                holder = new RoomHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (RoomHolder) convertView.getTag();
            }

            holder.bindData(matchs.get(position));

            return convertView;
        }


        private class RoomHolder {

            View itemView;
            TextView liveTitle;
            ImageView liveCover;
//            ImageView hostAvatar;
            TextView hostName;
            TextView watchNums;

            public RoomHolder(View view) {
                itemView = view;
                liveTitle = (TextView) view.findViewById(R.id.live_title);
                liveCover = (ImageView) view.findViewById(R.id.live_cover);
                hostName = (TextView) view.findViewById(R.id.host_name);
//                hostAvatar = (ImageView) view.findViewById(R.id.host_avatar);
                watchNums = (TextView) view.findViewById(R.id.watch_nums);
            }

            public void bindData(final matchInfo matchInfo) {


                hostName.setText("地点：  "+matchInfo.matchsite);

                String liveTitleStr = matchInfo.matchname;

                this.liveTitle.setText(liveTitleStr);

                String url = "";
                if (TextUtils.isEmpty(url)) {
                    ImgUtils.load(R.drawable.default_cover, liveCover);
                } else {
                    ImgUtils.load(url, liveCover);
                }


                int watchers = 100;
                String status = matchInfo.matchstate;
                String watchText = "";
                if (status.equals(matchInfo.MATCH_STATE_WAITING)){
                    watchText = "比赛还未开始";
                }else if(status.equals(matchInfo.MATCH_STATE_ING) ){
                    watchText = "比赛正在进行中";
                }else{
                    watchText = "已结束";
                }
                final  String toastText = watchText;

                watchNums.setText(watchText);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(matchInfo.matchstate.equals(matchInfo.MATCH_STATE_ING)){
                            if(flag == flag_all){
                                signRequest(CrossCountryApplication.getApplication().getSelfProfile().username,
                                        CrossCountryApplication.getApplication().getSelfProfile().password,
                                        matchInfo.matchname);
                            Toast.makeText(getActivity(),CrossCountryApplication.getApplication().getSelfProfile().password,Toast.LENGTH_SHORT).show();
                            }else{
                                gotoMatch(matchInfo.matchname);
                            }
                        }else{
                            Toast.makeText(getActivity(),toastText,Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        }
    }

    private void gotoMatch(String matchname) {
        //todo...
        Toast.makeText(getActivity(),"去比赛",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                intent.setClass(getActivity(), MatchIndexActivity.class);
                intent.putExtra("matchname",matchname);
                startActivity(intent);
    }

    private void signRequest(String username, String password, String matchname) {

        SignMatchRequest.MatchListParam param = new SignMatchRequest.MatchListParam();



        param.action = "signUpMatch";
        param.username = username;
        param.password = password;
        param.matchname = matchname;


        //想服务器进行申请
        SignMatchRequest request = new SignMatchRequest();
        request.setOnResultListener(new baseRequest.OnResultListener<SignInfo>() {
            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                register_request(name,pass);
            }

            @Override
            public void onSuccess(SignInfo info) {


                Toast.makeText(getActivity(), "报名成功，你的编号是: "+info.id, Toast.LENGTH_SHORT).show();
                CrossCountryApplication.getApplication().getSelfProfile().match_times  = (CrossCountryApplication.getApplication().getSelfProfile().match_times+1) ;
//                Intent intent = new Intent();
//                intent.setClass(RegisterActivity.this, MainActivity.class);
////                intent.putExtra("roomId", roomInfo.roomId);
//                startActivity(intent);
//
//                finish();
            }
        });

        String requestUrl = request.getUrl(param);
        request.request(requestUrl);
    }


}
