# viTool
视频工具集合
## m3u8
mp4转m3u8 demo
本项目主要是mp4转m3u8相关调用

为了在网站上传后能够实时查询视频转换状态，以此写本demo。
上传后，可以循环调用查询状态接口，当状态是成功，表示转换成功，提示用户消息。

**如何使用：**
1.  修改配置文件application.yml：
    - 修改mp4文件和输出保存m3u8的位置：
        ```
        video:
        #mp4视频文件
          source: E:\test\test.mp4
        #  转换输出路径：必须是xxx\xx.m3u8，否则可能会报错
          target: E:\test\test.m3u8
        ```
     - 修改ffmpeg执行路径：
        如果已经有了环境变量可以不用配置，否则必须指定ffmpeg具体路径
        ```
        # ffmpeg命令：不配置或ffmpeg，必须保证ffmpeg已经加了环境变量，否则需要指定ffmpeg路径：/usr/local/bin/ffmpeg或D:\\!Soft\\ffmpeg-20181127-1035206-win64-static\\bin\\ffmpeg.exe
        ffmpeg: ffmpeg
        ```
2.  启动springboot
3.  访问http://localhost:8080/video 返回以下字样表示启动成功。
``` hello, I'm heh ```
4.  开始转换: ``` http://localhost:8080/video/start ```
5.  转换状态: ``` http://localhost:8080/video/status ```
6.  停止转换: ``` http://localhost:8080/video/stop ```

**转换好了如何访问：**
1.  将转换好的m3u8和ts文件放在resource/static/t目录下（我这里是t下，也可以直接放在static下。只要保证能访问到即可）
2.  修改index.html，修改source标签src属性
    ```<source src="http://localhost:8080/data/test.m3u8" type="application/x-mpegURL">```
3.  播放：http://localhost:8080

