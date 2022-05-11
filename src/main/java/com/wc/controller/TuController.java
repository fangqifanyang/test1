package com.wc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@RestController
@CrossOrigin
public class TuController {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 30;
    
    @RequestMapping("/showImage")
    public void showImage(String url, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        FileInputStream fis = new FileInputStream(url);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
    
    @GetMapping("/getYzCode")
    public ServletOutputStream GetYzCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        //2.得到图片
        Graphics g = bi.getGraphics();
        //3.设置图片的背影色
        setBackGround(g);
        //4.设置图片的边框
        setBorder(g);
        //5.在图片上画干扰线
        drawRandomLine(g);
        String random = drawRandomNum((Graphics2D) g);
        System.out.println(random);
        System.out.println(random);
        System.out.println(random);
        System.out.println(random);
        
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", random);
        response.setHeader("Yz-Code", random);
        //10.将图片写给浏览器
        ImageIO.write(bi, "jpg", response.getOutputStream());
        return response.getOutputStream();
    }
    
    //设置图片的背景色
    private void setBackGround(Graphics g) {
        // 设置颜色
        g.setColor(Color.WHITE);
        // 填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    
    //设置图片的边框
    private void setBorder(Graphics g) {
        // 设置边框颜色
        g.setColor(Color.BLUE);
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }
    
    //在图片上画随机线条
    private void drawRandomLine(Graphics g) {
        // 设置颜色
        g.setColor(Color.GREEN);
        // 设置线条个数并画线
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }
    
    
    //画随机字符
    private String drawRandomNum(Graphics2D g) {
        // 设置颜色
        g.setColor(Color.RED);
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));
        //数字和字母的组合
        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        return createRandomChar(g, baseNumLetter);
    }
    
    //创建随机字符
    private String createRandomChar(Graphics2D g, String baseChar) {
        StringBuffer sb = new StringBuffer();
        int x = 5;
        String ch ="";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }
}
