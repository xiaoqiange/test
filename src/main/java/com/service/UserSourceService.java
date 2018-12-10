package com.service;

import java.util.List;

import com.pojo.User;
import com.vo.UserSource;

public interface UserSourceService {
    public List<UserSource> getUserSources(User user);
}
