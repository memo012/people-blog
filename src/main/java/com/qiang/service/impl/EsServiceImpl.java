package com.qiang.service.impl;

import com.qiang.pojo.es.EsBlogMessage;
import com.qiang.repository.EsBlogRepository;
import com.qiang.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.service.impl
 * @Description: Es业务逻辑接口
 * @Date: 2019/7/13 0013 19:42
 **/
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private EsBlogRepository esBlogRepository;



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Page<EsBlogMessage> findAllBlog(Integer page, Integer pageSize, String key) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(page-1, pageSize, sort);
        Page<EsBlogMessage> es = esBlogRepository.findDistinctByTitleContainingOrNameContaining(key, key, pageable);
        return es;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EsBlogMessage saveBlog(EsBlogMessage esBlogMessage) {
        return esBlogRepository.index(esBlogMessage);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public EsBlogMessage findById(long id) {
        return esBlogRepository.findById(id);
    }
}
