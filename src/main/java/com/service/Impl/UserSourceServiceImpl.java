package com.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserSourceDao;
import com.pojo.User;
import com.service.UserSourceService;
import com.vo.UserSource;

@Service("sourceService")
public class UserSourceServiceImpl implements UserSourceService {
    
    
    @Autowired
    private UserSourceDao sourceDao;
    
    @Override
    public List<UserSource> getUserSources(User user) {
        List<UserSource> list=sourceDao.getUserSources(user);
        List<UserSource> result=new ArrayList<UserSource>();
        Map<Integer, Object> map=new HashMap<Integer, Object>();
        for (UserSource userSource : list) {
            map.put(userSource.getSid(), userSource);
        }
        
        for (Integer sid : map.keySet()) {
            UserSource userSource=(UserSource) map.get(sid);
            Integer supersid=userSource.getSupersid();
            if(supersid==null)
                result.add(userSource);
            else{
                if(map.containsKey(supersid))
                ((UserSource)(map.get(supersid))).addOptions(userSource);
            }
        }
        return result;
        
    }

}
