package com.lmk.core.commons.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 验证码工具类
 */
public class CaptchaUtils {

    static{
        System.setProperty("java.awt.headless", "true");
    }

    /**
     * 生成Base64图片验证码
     *
     * @param code
     * @param width
     * @param height
     * @return
     */
    public static String createBase64(String code, int width, int height) {
        // 图片对象
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = buffImg.createGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);

        int fontHeight = (int) (height * 0.7);
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        graphics.setFont(font);

        // 初始化参数
        int codeX = width / (code.length() + 2);
        int codeY = (int) (height * 0.75);

        // 绘制字符
        drawText(graphics, codeX, codeY, code);

        // 绘制干扰线
        drawLine(graphics, width, height, 6);

        // 转为字符串
        String imageData = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(buffImg, "jpeg", baos);
            imageData = Encodes.getStringBase64(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/jpeg;base64," + imageData;
    }

    /**
     * 绘制字符串
     * @param graphics
     * @param text
     */
    private static void drawText(Graphics graphics, int codeX, int codeY, String text){
        int len = text.length();
        for (int i = 0; i < len; i++) {
            graphics.setColor(getColor(200));
            graphics.drawString(String.valueOf(text.charAt(i)), (i + 1) * codeX, codeY);
        }
    }

    /**
     * 绘制干扰线
     * @param graphics
     * @param width
     * @param height
     * @param total
     */
    private static void drawLine(Graphics graphics, int width, int height, int total) {  // 随机产生干扰线条
        for (int i = 0; i < total; i++) {
            int x1 = RandomUtils.nextInt((int) (width * 0.6));
            int y1 = RandomUtils.nextInt((int) (height * 0.6));
            int x2 = RandomUtils.nextInt(width) + (int) (width * 0.4);
            int y2 = RandomUtils.nextInt(height) + (int) (height * 0.2);
            graphics.setColor(getColor(128));
            graphics.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 产生随机颜色
     *
     * @param alpha
     * @return
     */
    private static Color getColor(int alpha) {
        int R = RandomUtils.nextInt(255);
        int G = RandomUtils.nextInt(255);
        int B = RandomUtils.nextInt(255);
        return new Color(R, G, B, alpha);
    }

}
