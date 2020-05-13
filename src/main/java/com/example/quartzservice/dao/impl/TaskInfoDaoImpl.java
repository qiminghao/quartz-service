package com.example.quartzservice.dao.impl;

import com.example.quartzservice.dao.TaskInfoDao;
import com.example.quartzservice.entity.TaskInfoEntity;
import com.example.quartzservice.entity.TaskInfoEntityExample;
import com.example.quartzservice.mapper.TaskInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class TaskInfoDaoImpl implements TaskInfoDao {

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public int insert(TaskInfoEntity object) {
        object.setCreateTime(new Timestamp(System.currentTimeMillis()));
        object.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return taskInfoMapper.insert(object);
    }

    @Override
    public int update(TaskInfoEntity object) {
        object.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return taskInfoMapper.updateByPrimaryKey(object);
    }

    @Override
    public List<TaskInfoEntity> selectByJobStatus(Integer status) {
        TaskInfoEntityExample example = new TaskInfoEntityExample();
        example.createCriteria().andJobStatusEqualTo(status);
        return taskInfoMapper.selectByExample(example);
    }

}
