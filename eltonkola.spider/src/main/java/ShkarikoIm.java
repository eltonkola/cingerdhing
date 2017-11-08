import org.apache.log4j.BasicConfigurator;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.ArrayList;
import java.util.List;

public class ShkarikoIm implements PageProcessor {

    private final static Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> res = getSongs(page.getHtml().getDocument().getAllElements());
        if(res.size()>0) {
            page.putField("songs", res);
        }
        page.addTargetRequests(filter(page.getHtml().links().all()));
    }

    private List<String> getSongs(Elements allElements) {
        List<String> res = new ArrayList<>();
        Elements mp3s = allElements.select("audio > source[src$=.mp3]");
        for(int i = 0; i < mp3s.size() ; i ++) {
            String song = mp3s.get(i).attr("src");
            if(song!=null){
                res.add(song);
            }
        }
        System.out.println("found:" + res);
        return res;

    }

    private List<String> filter(List<String> all) {
        List<String> allNew = new ArrayList<>();
        for (String s : all) {
            if (s.contains("shkarko.im")) {
                allNew.add(s);
            }
        }

        return allNew;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String url = "http://www.shkarko.im/";

        BasicConfigurator.configure();

        Spider.create(new ShkarikoIm())
                .addUrl(url)
                .thread(1)
                .addPipeline(new OneFilePipeline("/home/elton/Desktop/aaaaaaaaaaaaa/aa.json"))
//                .setScheduler(new QueueScheduler()
//                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)) //10000000 is the estimate value of urls
//                )
                .run();

    }
}