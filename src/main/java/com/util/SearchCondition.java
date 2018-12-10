package com.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.pojo.User;

public class SearchCondition implements ISearchCondition {
    private User user;

    private Map<String, Object> values = new HashMap<String, Object>();

    public SearchCondition(HttpServletRequest req) {
        if (req == null) {
            return;
        }
        for (Iterator<?> i = req.getParameterMap().keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            values.put(key, req.getParameter(key));
        }
    }

    public Map<String, Object> getParameters() {
        return values;
    }

    @Override
    public String getValue(String key) {
        String value = (String) values.get(key);
        return value;
    }

    @Override
    public void setValue(String key, String val) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        values.put(key, val);
    }

    @Override
    public User getCurUser() {
        return this.user;
    }

    @Override
    public void setCurUser(User user) {
        this.user = user;

    }

}
