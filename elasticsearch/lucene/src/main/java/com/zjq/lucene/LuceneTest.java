package com.zjq.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.IOException;
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

    /**
     * 删除索引
     * @throws Exception
     */
    @Test
    public void deleteIndex() throws Exception {
        // 1、指定索引库目录
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        // 2、创建IndexWriterConfig
        IndexWriterConfig cfg = new IndexWriterConfig(new StandardAnalyzer());
        // 3、 创建IndexWriter
        IndexWriter writer = new IndexWriter(directory, cfg);
        // 4、通过IndexWriter来删除索引
        // b)、删除指定索引
        writer.deleteDocuments(new Term("filename", "apache"));
        // 5、关闭IndexWriter
        writer.close();
    }

    /**
     * 删除全部索引
     * @throws Exception
     */
    @Test
    public void deleteAllIndex() throws Exception {
        // 1、指定索引库目录
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        // 2、创建IndexWriterConfig
        IndexWriterConfig cfg = new IndexWriterConfig(new StandardAnalyzer());
        // 3、 创建IndexWriter
        IndexWriter writer = new IndexWriter(directory, cfg);
        // 4、通过IndexWriter来删除索引
        // a)、删除全部索引
        writer.deleteAll();
        // 5、关闭IndexWriter
        writer.close();
    }

    /**
     * 修改索引
     * @throws Exception
     */
    @Test
    public void updateIndex() throws Exception {
        // 1、指定索引库目录
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        // 2、创建IndexWriterConfig
        IndexWriterConfig cfg = new IndexWriterConfig(new StandardAnalyzer());
        // 3、 创建IndexWriter
        IndexWriter writer = new IndexWriter(directory, cfg);
        // 4、通过IndexWriter来修改索引
        // a)、创建修改后的文档对象
        Document document = new Document();

        // 文件名称
        Field filenameField = new StringField("filename", "updateIndex", Field.Store.YES);
        document.add(filenameField);

        // 修改指定索引为新的索引
        writer.updateDocument(new Term("filename", "apache"), document);

        // 5、关闭IndexWriter
        writer.close();
    }

    /**
     * 搜索
     * @param query
     */
    private void doSearch(Query query) {
        IndexReader reader = null;
        try {
            // a) 指定索引库目录
            Path path = Paths.get("D:\\usr\\lucene\\");
            Directory directory = FSDirectory.open(path);
            // b) 创建IndexReader对象
            reader = DirectoryReader.open(directory);
            // c) 创建IndexSearcher对象
            IndexSearcher searcher = new IndexSearcher(reader);
            // d) 通过IndexSearcher对象执行查询索引库，返回TopDocs对象
            // 第一个参数：查询对象
            // 第二个参数：最大的n条记录
            TopDocs topDocs = searcher.search(query, 10);
            // e) 提取TopDocs对象中的文档ID，如何找出对应的文档
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("总共查询出的结果总数为：" + topDocs.totalHits);
            Document doc;
            for (ScoreDoc scoreDoc : scoreDocs) {
                // 文档对象ID
                int docId = scoreDoc.doc;
                doc = searcher.doc(docId);
                // f) 输出文档内容
                System.out.println("商品ID：" + doc.get("id"));
                System.out.println("商品名称：" + doc.get("name"));
                System.out.println("商品价格：" + doc.get("price"));
                System.out.println("商品图片地址：" + doc.get("pic"));
                System.out.println("==========================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * TermQuery搜索
     * @throws Exception
     */
    @Test
    public void testTermQuery() throws Exception {
        // 1、 创建查询（Query对象）
        Query query = new TermQuery(new Term("description", "mybatis"));
        // 2、 执行搜索
        doSearch(query);
    }

    @Test
    public void testRangeQuery() throws Exception {
        // 创建查询
        // 第一个参数：域名
        // 第二个参数：最小值
        // 第三个参数：最大值
        Query query = FloatPoint.newRangeQuery("price", 0, 500);
        // 2、 执行搜索
        doSearch(query);
    }

    @Test
    public void booleanQuery() throws Exception {
    }

    @Test
    public void testQueryParser() throws Exception {
        // 创建QueryParser
        // 第一个参数：默认域名
        // 第二个参数：分词器
        QueryParser queryParser = new QueryParser("name", new StandardAnalyzer());
        // 指定查询语法 ，如果不指定域，就搜索默认的域
        Query query = queryParser.parse("lucene");
        System.out.println(query);
        // 2、 执行搜索
        doSearch(query);

    }

    @Test
    public void testMultiFieldQueryParser() throws Exception {
        // 可以指定默认搜索的域是多个
        String[] fields = { "name", "description" };
        // 创建一个MulitFiledQueryParser对象
        QueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
        // 指定查询语法 ，如果不指定域，就搜索默认的域
        Query query = parser.parse("lucene");
        // 2、 执行搜索
        doSearch(query);
    }

    @Test
    public void setBoost4createIndex() throws Exception {
        // 创建分词器
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
        Path path = Paths.get("D:\\usr\\lucene\\");
        Directory directory = FSDirectory.open(path);
        // 创建IndexWriter对象，通过它把分好的词写到索引库中
        IndexWriter writer = new IndexWriter(directory, cfg);

        Document doc = new Document();
        Field id = new StringField("id", "11", Field.Store.YES);
        Field description = new TextField("description", "测试设置BOOST值 lucene",
                Field.Store.YES);
        // 把域添加到文档中
        doc.add(id);
        doc.add(description);
        writer.addDocument(doc);
        // 关闭IndexWriter
        writer.close();
    }

}
