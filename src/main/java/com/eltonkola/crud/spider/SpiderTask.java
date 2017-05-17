package com.eltonkola.crud.spider;

import com.eltonkola.crud.domain.Burim;
import com.eltonkola.crud.spider.msg.TaskStatus;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class SpiderTask {

    public Long getBurimiId() {
        return mBurim.getId();
    }

    public interface TaskStatusUpdate {
        void onTaskStatus(TaskStatus state);
    }

    private Date started_time;

    private HashSet<String> mLinks;
    private HashSet<String> mSongs;
    private HashSet<String> mErrors;

    private TaskStatusUpdate mTaskStatusUpdate;

    private Burim mBurim;

    private boolean running = false;

    public SpiderTask(Burim burimi, TaskStatusUpdate taskStatusUpdate) {
        mLinks = new HashSet<String>();
        mSongs = new HashSet<String>();
        mErrors = new HashSet<String>();
        mBurim = burimi;
        mTaskStatusUpdate = taskStatusUpdate;
        started_time = new Date();
        updateState(TaskStatus.EVENT_TYPE.TASK_READY);
    }

    public void start() {
        running = true;
        updateState(TaskStatus.EVENT_TYPE.TASK_STARTED);
        getPageLinks(mBurim.getUrl_burimi(), true);
    }

    public void stop() {
        running = false;
        onDone(true);

    }

    public void getPageLinks(String URL, boolean done) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!mLinks.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (mLinks.add(URL)) {
                    System.out.println("added:" + URL);
                    updateState(TaskStatus.EVENT_TYPE.ADDED_PAGE, URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL)
                        .userAgent(mBurim.getUser_agent())
                        .referrer(mBurim.getReferrer())
                        .ignoreContentType(true)
                        .timeout(mBurim.getTimeout())
                        .followRedirects(true)
                        .get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                if(running) {

                    for (Element page : linksOnPage) {
                        String link = cleanUrl(page.attr("abs:href"));
                        if (link.endsWith(".mp3") || link.endsWith(".MP3") || link.endsWith(".Mp3") || link.endsWith(".mP3")) {
                            System.out.println("found song:" + link);
                            if (!mSongs.contains(link)) {
                                mSongs.add(link);
                                updateState(TaskStatus.EVENT_TYPE.FOUND_MP3, link);
                            }
                        } else if (link.contains(mBurim.getMp3_domain()) || link.contains(mBurim.getUrl_burimi())) {
                            getPageLinks(cleanUrl(link), false);
                        }
                    }
                }

            } catch (Exception e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
                e.printStackTrace();
                mErrors.add(URL);
            }
        }

        if (done) {
            onDone(false);
            running = false;
        }
    }

    private void onDone(boolean interruped){

        Gson gson = new Gson();
        String json = gson.toJson(mSongs);
        try {
            FileWriter writer = new FileWriter("kenget_" + getBurimiId() + ".json");
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.err.println("!!!Finished, pages:" + mLinks.size() + " songs:" + mSongs.size() + " errors:" + mErrors.size());
        if(interruped) {
            updateState(TaskStatus.EVENT_TYPE.TASK_STOPED);
        }else{
            updateState(TaskStatus.EVENT_TYPE.TASK_ENDED);
        }
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }

    private void updateState(TaskStatus.EVENT_TYPE type) {
        updateState(type, null);
    }

    private void updateState(TaskStatus.EVENT_TYPE type, String value) {
        if (mTaskStatusUpdate != null) {

            Date now = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");

            String elapsedTime = sdf.format(new Date(started_time.getTime() - now.getTime()));

            TaskStatus state = new TaskStatus();
            state.setType(type);
            state.setValue(value);
            state.setCurrent_time(now);
            state.setErrors(mErrors.size());
            state.setName("Spider scaping on " + mBurim.getTitle());
            state.setPages(mLinks.size());
            state.setSongs(mSongs.size());
            state.setStarted_time(started_time);
            state.setRunning(running);
            state.setTime_elapsed(elapsedTime);

            mTaskStatusUpdate.onTaskStatus(state);
        }
    }

    private String cleanUrl(String url) {
        return url.replaceAll(" ", "%20");
    }

    //for local testing only
    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        Burim bur = new Burim("shkarko.im", "http://www.shkarko.im", "http://shkarko.muzikpapare.com");

        new SpiderTask(bur, null).start();
    }


}