package com.example.admanagement.ServletPackage;

import com.example.admanagement.DAOPackage.Advertisement;
import com.example.admanagement.DAOPackage.AdvertiserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "AdResponse", value = "/AdResponse-servlet")
public class AdResponseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 空的POST方法，按需填充
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "http://10.100.164.14:8080");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 设置响应类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
        List<Advertisement> ads = advertiserImpl.getAllAdvertisement();

        String tag = request.getParameter("tag"); // 获取请求中的tag参数
        Cookie[] cookies = request.getCookies();
        int flag = 0;

        if (tag != null && !tag.isEmpty()) {
            // 处理 cookies 中的 tag
            if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tag)) {
                    int tmp = Integer.parseInt(cookie.getValue()) + 1;
                    cookie.setValue(Integer.toString(tmp)); // 更新 cookie 中的 tag 权重
                    flag = 1;
                }
            }
        }
            if (flag == 0) {
                Cookie cookie = new Cookie(tag, "1"); // 新 tag 增加值为 1

                response.addCookie(cookie); // 将 cookie 添加到响应中
            }
        }

        if (ads == null || ads.isEmpty()) {
            System.out.println("广告数据为空");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        // 根据 tag 随机选择广告
        List<String> countTag = new ArrayList<>();
        for (Advertisement ad : ads) {
            if (!countTag.contains(ad.getAdTag())) {
                countTag.add(ad.getAdTag());
            }
        }

        int[] count = new int[countTag.size()];
        Arrays.fill(count, 1); // 初始化权重，全为 1
        if (cookies!=null) {
        for (Cookie cookie : cookies) {
            if (countTag.contains(cookie.getName())) {
                int index = countTag.indexOf(cookie.getName());
                count[index] += Integer.parseInt(cookie.getValue());
            }
        }
     }
        // 计算加权后随机选择 tag
        String resultTag = "";
        int sum = Arrays.stream(count).sum();
        int ranIndex = (int) (Math.random() * sum) + 1;
        for (int i = 0; i < count.length; i++) {
            if (ranIndex - count[i] <= 0) {
                resultTag = countTag.get(i);
                break;
            } else {
                ranIndex -= count[i];
            }
        }

        // 获取特定 tag 的广告
        List<Advertisement> adWithTag = new ArrayList<>();
        for (Advertisement ad : ads) {
            if (ad.getAdTag().equals(resultTag)) {
                adWithTag.add(ad);
            }
        }

        if (adWithTag.isEmpty()) {
            System.out.println("没有找到符合 tag 的广告");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        ranIndex = (int) (Math.random() * adWithTag.size());

        // 读取广告图片并转为 Base64 编码
        byte[] photoData = adWithTag.get(ranIndex).getphoto().readAllBytes();
        String base64Photo = Base64.getEncoder().encodeToString(photoData);
        adWithTag.get(ranIndex).setAdsend(adWithTag.get(ranIndex).getAdsend()+1);
        advertiserImpl.updateAdsend(adWithTag.get(ranIndex).getAdsend(),adWithTag.get(ranIndex).getAdid());
        // 返回 JSON 响应
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("base64photo", base64Photo);
        jsonResponse.put("adid", adWithTag.get(ranIndex).getAdid());
        jsonResponse.put("adurl", "http://116.62.49.213:8080/");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
