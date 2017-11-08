
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;

/**
 * @author code4crafer@gmail.com
 */
public class OneFilePipeline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private PrintWriter printWriter;

    public OneFilePipeline(String path)  {//throws FileNotFoundException, UnsupportedEncodingException
        setPath(path);
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path)), "UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void process(ResultItems result, Task task) {
        logger.info("saving url:" + result.toString());
        printWriter.write(JSON.toJSONString(result.getAll()));
        printWriter.flush();
    }
}