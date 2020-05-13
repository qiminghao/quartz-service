package com.example.quartzservice.dao;

import com.example.quartzservice.entity.TaskInfoEntity;

import java.util.List;

public interface TaskInfoDao {

    int insert(TaskInfoEntity object);

    int update(TaskInfoEntity object);

    List<TaskInfoEntity> selectByJobStatus(Integer status);

}
