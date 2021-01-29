package com.ample16.stackdemo.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class User {
    String username;
    String password;
    List<String> hobbies;
    Map<String, String> secrets;

}
