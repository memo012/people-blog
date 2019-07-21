package com.qiang.modules.sys.repository;

import com.qiang.modules.sys.pojo.es.EsBlogMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.repository
 * @Description: Blog存储库
 * @Date: 2019/7/13 0013 19:05
 **/
public interface EsBlogRepository extends ElasticsearchRepository<EsBlogMessage, Long>{

    /**
     * 分页查询blog（去重）
     * @param title 标题
     * @param name 作者
     * @param pageable
     * @return
     */
    Page<EsBlogMessage> findDistinctByTitleContainingOrNameContaining(String title, String name, Pageable pageable);

    /**
     * id查询博客
     * @param id
     * @return
     */
    EsBlogMessage findById(Long id);

}
