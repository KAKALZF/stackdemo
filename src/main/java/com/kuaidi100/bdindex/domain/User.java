package com.kuaidi100.bdindex.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class User {
    String username;
    String name;
    String password;
    String country;
    List<String> hobbies;
    Map<String, String> secrets;

}
