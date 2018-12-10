package com.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;
import com.dao.RoleDao;
import com.pojo.Role;
import com.pojo.User;
import com.vo.RoleVo;
import com.vo.ZTree;

@Repository(value = "roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public int addRole(Role role) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<RoleVo> selectAllRole() {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from role");
        List<RoleVo> list = this.findList(sql.toString(), RoleVo.class);
        return list;
    }

    @Override
    public List<ZTree> selectZtree() {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from role");
        List<ZTree> list = this.findList(sql.toString(), ZTree.class);
        return list;
    }

//    @Override
//    public List<ZTree> selectUserSource(User user) {
//        StringBuffer sql = new StringBuffer();
//        sql.append("select u.uid,u.`name`,r.rname,r.superid,r.rid from user u,role r, user_role a where ");
//        sql.append("u.uid=a.uid and r.rid=a.rid and r.superid=(select c.rid from role c,user_role d where c.rid=d.rid and d.uid=:uid)");
//        sql.append(" or u.uid=:uid GROUP BY u.uid");
//        SqlParameterSource sqlSource = new BeanPropertySqlParameterSource(user);
//        List<ZTree> list = this.findList(sql.toString(), sqlSource, ZTree.class);
//        return list;
//    }

}
