package cn.edu.jxnu.awesome_campus.ui.education;

import android.util.Log;

import cn.edu.jxnu.awesome_campus.InitApp;
import cn.edu.jxnu.awesome_campus.R;
import cn.edu.jxnu.awesome_campus.event.EVENT;
import cn.edu.jxnu.awesome_campus.event.EventModel;
import cn.edu.jxnu.awesome_campus.model.education.ExamTimeModel;
import cn.edu.jxnu.awesome_campus.support.adapter.education.ExamTimeAdapter;
import cn.edu.jxnu.awesome_campus.ui.base.BaseListFragment;

/**
 * Created by MummyDing on 16-2-1.
 * GitHub: https://github.com/MummyDing
 * Blog: http://blog.csdn.net/mummyding
 */
public class ExamFragment extends BaseListFragment {

    private ExamTimeModel model;

    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.exam);
    }


    @Override
    public void onDataRefresh() {
        model.loadFromNet();
    }

    @Override
    public void bindAdapter() {
        model = new ExamTimeModel();
        adapter = new ExamTimeAdapter(getContext(),model);
        recyclerView.setAdapter(adapter);
        model.loadFromNet();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()){
            case EVENT.EXAM_TIME_REFRESH_SUCCESS:
                Log.d("size: ",eventModel.getDataList().size()+" ");
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.EXAM_TIME_REFRESH_FAILURE:
                hideLoading();
                displayNetworkError();
                break;
        }
    }
}
