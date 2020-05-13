package com.example.quartzservice.mapper;

import com.example.quartzservice.entity.TaskInfoEntity;
import com.example.quartzservice.entity.TaskInfoEntityExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskInfoMapper {
    int countByExample(TaskInfoEntityExample example);

    int deleteByExample(TaskInfoEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskInfoEntity record);

    int insertSelective(TaskInfoEntity record);

    List<TaskInfoEntity> selectByExample(TaskInfoEntityExample example);

    TaskInfoEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskInfoEntity record);

    int updateByPrimaryKey(TaskInfoEntity record);
}