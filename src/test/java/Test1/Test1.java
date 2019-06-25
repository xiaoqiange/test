package Test1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.service.RoleService;
import com.service.UserService;
import com.service.UserSourceService;
import com.util.JdbcUtil;
import com.vo.RoleVo;
import com.vo.UserSource;
import com.vo.UserVo;
import com.vo.ZTree;

public class Test1 {
    JdbcUtil jdbc=new JdbcUtil();
    private JdbcTemplate jdbcTemplate=null;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RoleService roleService;
    private UserSourceService sourceService;
    private UserService userService;
    {
        ClassPathXmlApplicationContext cx=new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-mvc.xml"});
        jdbcTemplate=(JdbcTemplate) cx.getBean("jdbcTemplate");
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
        roleService=(RoleService) cx.getBean("roleService");
        sourceService=(UserSourceService) cx.getBean("sourceService");
        userService=(UserService) cx.getBean("userService");
    }
    @Test
    public void test() {
        Logger logger=Logger.getLogger(Test1.class);
        ClassPathXmlApplicationContext cx=new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-mvc.xml"});
        UserService service=(UserService) cx.getBean("userService");
        List<User> list=service.getUserList();
        if(list.size()!=0)
        System.out.println(JSON.toJSONString(list,true));
    }
    @Test
    public void test2() throws SQLException {
        Logger logger=Logger.getLogger(Test1.class);
        String sql="select * from user";
        ResultSet rs=jdbc.selectList(sql, null);
        List<User> list= new ArrayList<User>();
        while(rs.next()){
            User user=new User();
            user.setUid(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setCode(rs.getString(4));
            user.setPhone(rs.getString(5));
            user.setSex(rs.getInt(6));
            user.setAge(rs.getInt(7));
            user.setBirthday(rs.getDate(8));
            list.add(user);
        }
        if(list.size()!=0){
            System.out.println(JSON.toJSONString(list,true));
        }
    }
    @Test
    public void test3() throws SQLException {
        String sql="select * from user";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        List<User> list=jdbcTemplate.query(sql, rowMapper);
        System.out.println("Student------->"+JSON.toJSONString(list,true));
    }
    @Test
    public void test4(){
        String sql="select count(1) from user";
        int i=jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("共有---------->" + i + "用户");
    }
    
    @Test
    public void test5(){
        List<User> list=new ArrayList<User>();
        SqlRowSet rs=jdbcTemplate.queryForRowSet("select * from user");
        while(rs.next()){
            User user =new User();
            user.setUid(rs.getLong("uid"));
            user.setName(rs.getString("name"));
            user.setSex(rs.getInt("sex"));
            user.setAge(rs.getInt("age"));
            user.setBirthday(rs.getDate("birthday"));
            user.setCode(rs.getString("code"));
            list.add(user);
        }
        System.out.println(JSON.toJSONString(list,true));
    }
    
    @Test
    public void batchInsert(){
        String sql="insert into user(name,password,code,phone,age,sex,birthday) values(?,?,?,?,?,?,?)";
        List<Object[]> list= new ArrayList<Object[]>();
        list.add(new Object[]{"王五","000000","u003","12735423782",18,1,"2001-09-04"});
        list.add(new Object[]{"赵柳","000000","u004","13258383748",22,2,"1995-10-04"});
        list.add(new Object[]{"佳琪","000000","u005","18928844782",21,1,"1996-09-04"});
        int[] i=jdbcTemplate.batchUpdate(sql, list);
        System.out.println("---------->"+i.length);
    }
    /**
     * 
     * Description:[方法功能中文描述]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月11日
     */
    @Test
    public void batchUpdate(){
        String sql="update user set password=?,phone=? where name=?";
        List<Object[]> list=new ArrayList<Object[]>();
        list.add(new Object[]{"666666","18928844782","刘柳"});
        list.add(new Object[]{"888888","18835423782","赵柳"});
        list.add(new Object[]{"111111","13258383748","佳琪"});
        int [] i = jdbcTemplate.batchUpdate(sql, list);
        System.out.println("共修改---------->"+i.length);
    }
    
    /**
     * 
     * Description:[使用BatchPreparedStatementSetter批量修改]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月11日
     */
    @Test
    public void batchPrepared(){
        String sql="update user set password=?,code=?,phone=?,sex=?,age=?,birthday=? where name=?";
        final List<User> list=new ArrayList<User>();
        list.add(new User("刘柳","6666666", "u006","17742324323", 2, 19,new Date(1998-1900,5-1,31)));
        list.add(new User("七七","77777", "u007", "17623546214",2, 17,new Date(2001-1900,5-1,31)));
        list.add(new User("小八","444444", "u008","18936457321", 2,15,new Date(2003-1900, 3-1, 05)));
        int[] i =jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // TODO Auto-generated method stub
                ps.setString(7, list.get(i).getName());
                ps.setString(1, list.get(i).getPassword());
                ps.setString(2, list.get(i).getCode());
                ps.setString(3, list.get(i).getPhone());
                ps.setInt(4, list.get(i).getSex());
                ps.setInt(5, list.get(i).getAge());
                ps.setDate(6,new java.sql.Date(list.get(i).getBirthday().getTime()));
            }
            @Override
            public int getBatchSize() {
                // TODO Auto-generated method stub
                return list.size();
            }
        });
        System.out.println("共修改----->"+i.length);
    }
    
    
    @SuppressWarnings("unused")
    @Test
    public void selectUserVo(){
        StringBuffer sql=new StringBuffer();
        sql.append("select a.*,c.rname from user a,user_role b,role c where a.uid=b.uid and b.rid=c.rid");
        RowMapper<UserVo> rowMapper=new BeanPropertyRowMapper<UserVo>(UserVo.class);
        UserVo userVo=new UserVo();
        userVo.setUid(1L);
        List<UserVo> list=null;
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("uid", 1);
        if(paramMap!=null){
            sql.append(" and a.uid=:uid");
//            SqlParameterSource paramSource=new BeanPropertySqlParameterSource(userVo);
//            list=namedParameterJdbcTemplate.query(sql.toString(), paramSource, rowMapper);
            list=namedParameterJdbcTemplate.query(sql.toString(), paramMap, rowMapper);
        }else{
            list=jdbcTemplate.query(sql.toString(), rowMapper);
        }
        System.out.println(list.get(0).getRname());
        System.out.println(JSON.toJSONString(list, true));
    }
    
    /**
     * 
     * Description:[easyui tree]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月8日
     */
    @Test
    public void tree(){
        List<RoleVo> list=roleService.selectAllRole();
        System.out.println(JSON.toJSONString(list,true));
    }
    
    /**
     * 
     * Description:[ztree]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月8日
     */
    @Test
    public void Ztree(){
        List<ZTree> list=roleService.selectZtree();
        System.out.println(JSON.toJSONString(list,true));
    }
    @Test
    public void Source(){
        User user=new User();
        user.setUid(10L);
        List<UserSource> list=sourceService.getUserSources(user);
        System.out.println(JSON.toJSONString(list,true));
    }
    @Test
    public void loginTest(){
        User user=new User();
        user.setName("佳佳");
        user.setPassword("jiajia");
        User us=userService.loginUser(user);
        System.out.println(JSON.toJSONString(us,true));
    }
    @Test
    public void test10(){
        try{
            System.out.println("try run");
            throw new Exception();
        }catch (Exception e){
            System.out.println("catch");
            return;
        }finally{
            System.out.println("finally");
            return;
        }
    }
}
