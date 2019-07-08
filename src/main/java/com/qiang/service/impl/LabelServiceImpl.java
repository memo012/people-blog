package com.qiang.service.impl;

import com.qiang.mapper.LabelMapper;
import com.qiang.pojo.Label;
import com.qiang.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service
 * @Description: 标签业务逻辑层
 * @Date: 2019/7/6 0006 11:50
 **/
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insLabel(String[] label) {
        int flag = 0;
        for(int i = 0; i < label.length; i++){
            if(labelMapper.selByLabelName(label[i]) == 0){
                Label tags = new Label();
                String id = UUID.randomUUID().toString();
                tags.setId(id);
                System.out.println(id);
                tags.setLabelName(label[i]);
                flag = labelMapper.insLabel(tags);
            }
        }
        return flag;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int selByLabelName(String labelName) {
        return labelMapper.selByLabelName(labelName);
    }
}
