package wiki.heh.color.rocket.vitool.controller;

import wiki.heh.color.rocket.vitool.util.VideoConvert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * 视频处理
 *
 * @author heh
 * @version 1.0
 * @date 2021/1/21
 */
@RestController
@RequestMapping("video")
public class VideoController {
    VideoConvert videoConvert;

    @Value("${video.source}")
    private String source;
    @Value("${video.target}")
    private String target;
    @Value("${ffmpeg:ffmpeg}")
    private String ffmpeg;


    @RequestMapping("")
    public String main() {
        return "hello, I'm heh";
    }

    @RequestMapping("start")
    public String start() throws FileNotFoundException, InterruptedException {
        String source = this.source;
        String target = this.target;
        String ffmpegPath = this.ffmpeg;
        VideoConvert convert = new VideoConvert(ffmpegPath);
        convert.start(source, target);
        videoConvert = convert;
        return "已经开始。。。";
    }

    @RequestMapping("status")
    public String isAlive() {
        return videoConvert.getStatus();
    }

    @RequestMapping("stop")
    public String stop() {
        videoConvert.stop();
        return "stoped";
    }

}
