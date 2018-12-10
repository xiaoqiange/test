package com.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dao.RoleDao;
import com.pojo.User;
import com.service.RoleService;
import com.vo.RoleVo;
import com.vo.ZTree;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<RoleVo> selectAllRole() {
        List<RoleVo> list=roleDao.selectAllRole();
        List<RoleVo> result= new ArrayList<RoleVo>();
        Map<Long,Object> map=new HashMap<Long, Object>();
        if(list!=null){
            for (RoleVo roleVo : list) {
                map.put(roleVo.getRid(), roleVo);
            }
        }
        for (Long rid : map.keySet()) {
            RoleVo roleVo=(RoleVo) map.get(rid);
            Long superId=roleVo.getSuperId();
            if(superId==null){
                result.add(roleVo);
            }else{
                if(map.containsKey(superId)){
                    String superName=((RoleVo)map.get(superId)).getrName();
                    roleVo.setSuperName(superName);
                    ((RoleVo)map.get(superId)).addOption(roleVo);
                }
            }
        }
        return result;
    }
    @Override
    public List<ZTree> selectZtree() {
        //获取数据
        List<ZTree> list=roleDao.selectZtree();
        //返回结果
        List<ZTree> result=new ArrayList<ZTree>();
        //用于将对应的子节点放入父节点的children中
        Map<Long,Object> map=new HashMap<Long, Object>();
        for (ZTree zTree : list) {
            map.put(zTree.getRid(), zTree);
        }
        for (Long rid : map.keySet()) {
            ZTree zTree=(ZTree) map.get(rid);
            Long superid=zTree.getSuperId();
            if(superid==null){
                zTree.setOpen("true");
                result.add(zTree);
            }else{
                if(map.containsKey(superid)){
                    ((ZTree)map.get(superid)).addOption(zTree);
                }
            }
            if(zTree.getChildren()!=null){
                zTree.setIsParent("true");
            }
        }
        return result;
    }
//    @Override
//    public List<ZTree> selectUserSource(User user) {
//        List<ZTree> list=roleDao.selectUserSource(user);
//        System.out.println("共查出"+list.size()+"条数据");
//        List<ZTree> result = new ArrayList<ZTree>();
//        Map<Long, Object> map = new HashMap<Long, Object>();
//
//        for (ZTree zTree : list) {
//            map.put(zTree.getUid(), zTree);
//        }
//
//        for (Long uid : map.keySet()) {
//            ZTree zTree = (ZTree) map.get(uid);
//            Long superid = zTree.getSuperId();
//            System.out.println("superId------------>"+superid);
//            if (superid == null) {
//                System.out.println("superid == null,name------------>"+zTree.getName());
//                result.add(zTree);
//            } else {
//                if (map.containsKey(superid)) {
//                    System.out.println("superid != null,name------------>"+zTree.getName());
//                    ((ZTree) map.get(superid)).addOption(zTree);
//                }
//            }
//        }
//        return list;
//    }
//    
}
