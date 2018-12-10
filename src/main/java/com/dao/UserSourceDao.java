package com.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.pojo.User;
import com.vo.UserSource;

public interface UserSourceDao {
    public List<UserSource> getUserSources(User user);
}
