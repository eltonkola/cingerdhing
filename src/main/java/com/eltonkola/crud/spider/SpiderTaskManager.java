package com.eltonkola.crud.spider;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.service.BurimeServiceInterface;
import com.eltonkola.crud.spider.msg.TaskStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderTaskManager {

    public interface ClientStateUpdate{
        void update(String burimiId, String msg);
    }

    @Autowired
    BurimeServiceInterface mBurimeServiceInterface;

    private List<SpiderTask> mTasks = new ArrayList<>();
    private ClientStateUpdate mClientStateUpdate;

    public SpiderTaskManager(){
    }

    public void setClient(ClientStateUpdate clientStateUpdate){
        mClientStateUpdate = clientStateUpdate;
    }


    public void getSpiderForTask(String sId) {
        getTaskById(sId);
    }

    public void startSpiderForTask(String sId) {
        getTaskById(sId).start();
    }

    private SpiderTask getTaskById(String sId){
        SpiderTask myTask = null;

        Burim burim = mBurimeServiceInterface.findBurimById(Long.parseLong(sId));
        if(burim == null){
            return myTask;
        }


        if(mTasks.size() > 0){
            for(int i = 0; i < mTasks.size(); i++) {
                SpiderTask task = mTasks.get(i);
                if (sId.equalsIgnoreCase(String.valueOf(task.getBurimiId()))) {
                    myTask = task;
                }

            }
        }

        if(myTask==null){
            myTask =  new SpiderTask(burim, new SpiderTask.TaskStatusUpdate() {
                @Override
                public void onTaskStatus(TaskStatus state) {
                    mClientStateUpdate.update(sId, new Gson().toJson(state).toString());
                }
            });
            mTasks.add(myTask);
        }


        return myTask;
    }
}
