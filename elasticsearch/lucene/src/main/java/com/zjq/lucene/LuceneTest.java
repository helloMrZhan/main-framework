package com.zjq.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zjq
 * @date 2023/5/6 9:27
 * @description:
 */
public class LuceneTest {

    @Test
    public void createIndex() throws Exception {
        // 采集数据
        BookDao dao = new BookDaoImpl();
        List<Book> list = dao.queryBooks();

        // 将采集到的数据封装到Document对象中
        List<Document> docList = new ArrayList<>();
        Document document;
        for (Book book : list) {
            document = new Document();
            // store:如果是yes，则说明存储到文档域中
            // 图书ID
            Field id = new TextField("id", book.getId().toString(), Field.Store.YES);
            // 图书名称
            Field name = new TextField("name", book.getName(), Field.Store.YES);
            // 图书价格
            Field price = new TextField("price", book.getPrice().toString(),	Field.Store.YES);
            // 图书图片地址
            Field pic = new TextField("pic", book.getPic(), Field.Store.YES);
            // 图书描述
            Field description = new TextField("description",
                    book.getDescription(), Field.Store.YES);

            // 将field域设置到Document对象中
            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);

            docList.add(document);
        }

        // 创建分词器，标准分词器
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 创建IndexWriter
        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        // 指定索引库的地址
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        IndexWriter writer = new IndexWriter(directory, cfg);

        // 通过IndexWriter对象将Document写入到索引库中
        for (Document doc : docList) {
            writer.addDocument(doc);
        }

        // 关闭writer
        writer.close();
    }

    @Test
    public void indexSearch() throws Exception {
        // 创建query对象
        // 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
        // 第一个参数：默认搜索的域的名称
        QueryParser parser = new QueryParser("description",
                new StandardAnalyzer());

        // 通过queryparser来创建query对象
        // 参数：输入的lucene的查询语句(关键字一定要大写)
        Query query = parser.parse("description:java AND lucene");

        // 创建IndexSearcher
        // 指定索引库的地址
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        // 通过searcher来搜索索引库
        // 第二个参数：指定需要显示的顶部记录的N条
        TopDocs topDocs = searcher.search(query, 10);

        // 根据查询条件匹配出的记录总数
        long count = topDocs.totalHits.value;
        System.out.println("匹配出的记录总数:" + count);
        // 根据查询条件匹配出的记录
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            // 获取文档的ID
            int docId = scoreDoc.doc;

            // 通过ID获取文档
            Document doc = searcher.doc(docId);
            System.out.println("商品ID：" + doc.get("id"));
            System.out.println("商品名称：" + doc.get("name"));
            System.out.println("商品价格：" + doc.get("price"));
            System.out.println("商品图片地址：" + doc.get("pic"));
            System.out.println("==========================");
            // System.out.println("商品描述：" + doc.get("description"));
        }
        // 关闭资源
        reader.close();
    }
}
