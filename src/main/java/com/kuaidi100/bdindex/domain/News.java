package com.kuaidi100.bdindex.domain;

import lombok.Data;

@Data
public class News {
    private Long id;
    private String newsUrl;
    private String newsTitle;
    private String newsDate;
}
