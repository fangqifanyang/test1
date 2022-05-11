package com.wc.service.impl;

import com.wc.dao.GroupDao;
import com.wc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public String findname(Integer id) {
        return groupDao.findname(id);
    }

    @Override
    public Integer findID(String name) {
        return groupDao.findID(name);
    }
}
