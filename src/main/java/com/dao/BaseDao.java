package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport{

    private SessionFactory sessionFactory;
    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory){  
       super.setSessionFactory(sessionFactory);  
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
    protected void testMapIsNull(Map<String, Object> mapParam) {
        if (!(mapParam instanceof HashMap) || !(mapParam instanceof Map)) {
            throw new RuntimeException("Map 空值");
        }
    }

    /**
     * 
     * Description:[具名参数入参，查询多行数据]<br>
     * @author liufq
     * @update 2018年6月11日
     * @param sql
     * @param mapParse
     * @return
     */
    public List<Map<String, Object>> findForList(String sql, Map<String, Object> mapParse) {
        this.testMapIsNull(mapParse);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (!mapParse.isEmpty()) {
            result = this.getNamedParameterJdbcTemplate().queryForList(sql, mapParse);
        } else {
            result = this.getJdbcTemplate().queryForList(sql);
        }
        return result;
    }

    /**
     * 无参查询多行数据
     */
    public <T> List<T> findList(String sql, Class<T> clazz) {
        return this.findList(sql, null, clazz);
    }

    /**
     * 
     * Description:[具名参数，实体入参，查询多行数据]<br>
     * @author liufq
     * @update 2018年6月11日
     * @param sql
     * @param sqlRource
     * @param clazz
     * @return
     */
    public <T> List<T> findList(String sql, SqlParameterSource sqlRource, Class<T> clazz) {
        List<T> list = null;
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clazz);
        if (sqlRource != null) {
            list = this.getNamedParameterJdbcTemplate().query(sql, sqlRource, rowMapper);
        } else {
            //            boolean bol=false;
            //            if(jdbcTemplate!=null)
            //                bol=true;
            //            System.out.println("jdbcTemplate------->"+bol);
            list = this.getJdbcTemplate().query(sql, rowMapper);
        }
        return list;
    }
    
    public <T> List<T> findForList(String sql, Map<String, Object> mapParse, Class<T> clazz) {
        List<T> list = null;
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(clazz);
        if (mapParse != null) {
            list = this.getNamedParameterJdbcTemplate().query(sql, mapParse, rowMapper);
        } else {
            //            boolean bol=false;
            //            if(jdbcTemplate!=null)
            //                bol=true;
            //            System.out.println("jdbcTemplate------->"+bol);
            list = this.getJdbcTemplate().query(sql, rowMapper);
        }
        return list;
    }
    
    
    /**
     * 
     * Description:[查询一个整型数值]<br>
     * @author liufq
     * @update 2018年6月13日
     * @param sql
     * @param mapParse
     * @return
     */
    public int findForInteger(String sql, Map<String, Object> mapParse) {
        int i;
        if (mapParse != null) {
            i = this.getNamedParameterJdbcTemplate().queryForObject(sql, mapParse, Integer.class);
        } else {
            i = this.jdbcTemplate.queryForObject(sql, Integer.class);
        }
        return i;
    }

    /**
     * 
     * Description:[对象数组批量添加和修改]<br>
     * @author liufq
     * @update 2018年6月11日
     * @param sql
     * @param list
     * @return
     */
    public int[] insertObjects(String sql, List<Object[]> list) {
        if (list == null) {
            return jdbcTemplate.batchUpdate(sql);
        }
        return jdbcTemplate.batchUpdate(sql, list);
    }
    
    
    /**
     * 
     * Description:[查询单个实体]<br>
     * @author liufq
     * @update 2018年6月13日
     * @param sql
     * @param args
     * @param clazz
     * @return
     */
    public <T> T findForObject(String sql,Object [] args, Class<T> clazz){
        RowMapper<T> rowMapper=new BeanPropertyRowMapper<T>(clazz);
        T t=jdbcTemplate.queryForObject(sql, rowMapper, args);
        return t;
    }
}
