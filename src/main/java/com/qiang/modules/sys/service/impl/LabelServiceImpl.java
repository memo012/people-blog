package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import com.qiang.modules.sys.mapper.LabelMapper;
import com.qiang.modules.sys.pojo.Label;
import com.qiang.modules.sys.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    private RedisOperator redisOperator;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Label> selAllLabel() {
        List<Label> list = null;
        if(redisOperator.hasKey(Constant.LABEL_ALL)){
            long length = redisOperator.llen(Constant.LABEL_ALL);
            list = (List<Label>)redisOperator.range(Constant.LABEL_ALL, 0, length);
        }else{
            list = labelMapper.selAllLabel();
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insLabel(String[] label) {
        int flag = 0;
        for(int i = 0; i < label.length; i++){
            if(labelMapper.selByLabelName(label[i]) == 0){
                Label tags = new Label();
                String id = UUID.randomUUID().toString();
                tags.setId(id);
                tags.setLabelName(label[i]);
                flag = labelMapper.insLabel(tags);
                // 把标签加入缓存
                redisOperator.lpush(Constant.LABEL_ALL, tags);
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
