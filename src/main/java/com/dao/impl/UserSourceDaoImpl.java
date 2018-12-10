package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;
import com.dao.UserSourceDao;
import com.pojo.User;
import com.vo.UserSource;

@Repository("sourceDao")
public class UserSourceDaoImpl extends BaseDao implements UserSourceDao {

    @Override
    public List<UserSource> getUserSources(final User user) {
        StringBuffer sql = new StringBuffer();
        sql.append("select u.`name`,r.rname,s.sname,s.supersid,s.url,s.sid from user u ");
        sql.append(" inner join user_role a inner join role r inner join role_source b inner join source s");
        sql.append(" on u.uid=a.uid and r.rid=a.rid and r.rid=b.rid and s.sid=b.sid and u.uid=:uid");
        Map<String,Object> mapParse=new HashMap<String, Object>();
        mapParse.put("uid", user.getUid());
        List<UserSource> list=this.findForList(sql.toString(), mapParse, UserSource.class);
        return list;
    }

}
