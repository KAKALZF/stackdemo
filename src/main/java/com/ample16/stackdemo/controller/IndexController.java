package com.ample16.stackdemo.controller;

import com.ample16.stackdemo.domain.News;
import com.ample16.stackdemo.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class IndexController {

    @GetMapping("home")
    public String index(String page, Model model) {

        List userList = new ArrayList<User>();
        User user = new User();
        user.setUsername("jack");
        user.setPassword("112233");
        user.setHobbies(Arrays.asList(new String[]{"singing", "dancing", "football"}));
        Map<String, String> maps = new HashMap<>();
        maps.put("1", "o");
        maps.put("2", "g");
        maps.put("3", "a");
        maps.put("4", "j");
        user.setSecrets(maps);
        user.setCountry("中国");
        userList.add(user);
        for (int i = 0; i < 10; i++) {
            userList.add(user);
        }
        model.addAttribute("userList", userList);
        model.addAttribute("total", 100);
        model.addAttribute("page", page);
//        return "index";
        return "page";
    }

    @GetMapping("/page/news")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> news(@RequestParam("page") int page) {
        Map<String, Object> map = new HashMap<String, Object>();

        int pageSize = 3;// 每页显示条数

        try {
            int count = 100;// 获取总条目数
            int totalPage = count / pageSize;// 计算总页数
            if (count % pageSize != 0) {// 不满一页的数据按一页计算
                totalPage++;
            }

            if (page > totalPage)// 当页数大于总页数，直接返回
                return null;

            int offset = (page - 1) * pageSize;// 计算sql需要的起始索引
//            List<News> list = newsService.getPage(offset, pageSize);// 根据起始索引和页面大小去查询数据
            ArrayList<News> list = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                News news = new News();
                news.setId((long) new Random().nextInt(100));
                news.setNewsDate("2021-01-30");
                news.setNewsTitle("test" + new Random().nextInt(100));
                news.setNewsUrl("http://www.test.com");
                news.setNewsTitle("test" + new Random().nextInt(100));
                list.add(news);
            }
            // 封装数据，并返回
            map.put("page", page);
            map.put("pageSize", pageSize);
            map.put("totalPage", totalPage);
            map.put("list", list);

            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (Exception e) {
//            log.error("获取分页数据失败", e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
