package com.zjq.es.repositories;

import com.zjq.es.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 查询
 * @author zjq
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
    /**
     * 通过标题查询
     * @param title
     * @return
     */
    List<Article> findByTitle(String title);

    /**
     * 通过标题或内容查询
     * @param title
     * @param content
     * @return
     */
    List<Article> findByTitleOrContent(String title, String content);

    /**
     * 通过标题或内容分页查询
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    List<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
