package com.util;

import com.pojo.User;

public interface ISearchCondition {
    /**
     * 
     * Description:[通过key值获取值val]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月12日
     * @param key
     * @return
     */
    public String getValue(String key);
    /**
     * 
     * Description:[设置k-v键值对]<br>
     * @author liufq
     * @update 2018年6月12日
     * @param key
     * @param val
     */
    public void setValue(String key,String val);
    /**
     * 
     * Description:[获取当前登录用户]<br>
     * @author liufq
     * @update 2018年6月12日
     * @return
     */
    public User getCurUser();
    
    /**
     * 
     * Description:[记录当前对象]<br>
     * @author liufq
     * @update 2018年6月12日
     * @param user
     */
    public void setCurUser(User user);
}
