package wiki.heh.color.rocket.vitool.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
/**
 * 文件转base64及
 * 
 *
 * @author heh
 * @version 1.0
 * @date 2021/1/22
 */
public class ImageBase64Converter {
    /**
     * 本地文件（图片、excel等）转换成Base64字符串
     *
     * @param imgPath     
     */
    public static String convertFileToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            System.out.println("文件大小（字节）=" + in.available());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(data);
        return base64Str;
    }

    /**
     * 将base64字符串，生成文件
     */
    public static File convertBase64ToFile(String fileBase64String, String filePath, String fileName) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bfile = decoder.decodeBuffer(fileBase64String);

            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String imgBase64Str = ImageBase64Converter.convertFileToBase64("/home/kugs/IdeaProjects/Learning/bsd-data-forwarding/data/123.jpeg");
//        System.out.println("本地图片转换Base64:" + imgBase64Str);
        System.out.println("Base64字符串length=" + imgBase64Str.length());
        ImageBase64Converter.convertBase64ToFile(imgBase64Str, "/home/kugs/IdeaProjects/Learning/bsd-data-forwarding/data/", "test.jpg");
        System.out.println("duration:" + (System.currentTimeMillis() - start));

//        start=System.currentTimeMillis();
//        String fileBase64Str= ImageBase64Converter.convertFileToBase64("D:\\Pictures\\科技\\PayOrderList200109075516581.xlsx");
////        System.out.println("本地excel转换Base64:" + fileBase64Str);
//        System.out.println("Base64字符串length="+fileBase64Str.length());
//        ImageBase64Converter.convertBase64ToFile(fileBase64Str,"D:\\Pictures\\科技","test.xlsx");
//        System.out.println("duration:"+(System.currentTimeMillis()-start));

    }
}
