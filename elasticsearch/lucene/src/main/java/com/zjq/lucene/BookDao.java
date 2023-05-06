package com.zjq.lucene;


import java.util.List;

/**
 * @author zjq
 * @date 2023/5/5 19:54
 * @description:
 */
public interface BookDao {
    List<Book> queryBooks();
}
