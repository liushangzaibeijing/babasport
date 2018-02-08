package cn.babasport.xiu.core.lucene.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Service;

import cn.babasport.xiu.common.utils.LunceneUtils;
import cn.babasport.xiu.core.bean.Product;

/**
 * 使用luncene的对product进行增删改查操作的索引操作
 * @author xieqixiu
 *
 */
@Service
public class LunceneService {
	
	 //获取log4j的日志实例
	public static Logger logger = Logger.getLogger(LunceneService.class);
	
	//添加单个product的索引
	public  void indexProduct(Product product){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/lunceneIndex");
		
		Document doc = product2Doc(product);
		
		try {
			writer.addDocument(doc);
		} catch (IOException e) {
			logger.warn("索引创建失败");
			try {
				writer.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	 
	//添加多个product的索引
	public  void indexProducts(List<Product> products){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/lunceneIndex");
		try {
		  for(Product product:products){
			  Document doc = product2Doc(product);
			  writer.addDocument(doc);
		  }
		
		} catch (IOException e) {
			logger.warn("索引创建失败");
			try {
				writer.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//删除单个product的索引
	public void deleteProductIndex(Integer productId){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/lunceneIndex");
		try {
			writer.deleteDocuments(new Term("id",String.valueOf(productId)));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//删除多个product的索引
	public void deleteProductsIndex(List<Integer> productIds){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/lunceneIndex");
		try {
			for(Integer productId:productIds){
				writer.deleteDocuments(new Term("id",String.valueOf(productId)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//修改单个product的索引(先删除后创建)
	public void updateProductIndex(Product product){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/lunceneIndex");
		try {
			writer.deleteDocuments(new Term("id",String.valueOf(product.getId())));
			indexProduct(product);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//查询操作（查询name description）
	public List<Product> search(String keyword){
		IndexSearcher searcher = LunceneUtils.getIndexSearcher("WEB-INF/classes/lunceneIndex");
		
		//查询的关键字可以是商品的name或者商品的description字段
		
		//先构造两个TermQuery对象
		Query nameQuery = new TermQuery(new Term("name",keyword));
		Query descQuery = new TermQuery(new Term("keywords",keyword));
		
		
		//再构造一个BooleanQuery 前面的两个TermQuery为或的关系
		BooleanQuery booleanQuery = new BooleanQuery();
	    
		booleanQuery.add(nameQuery, Occur.SHOULD);
		booleanQuery.add(descQuery, Occur.SHOULD);
		
		List<Product> lists = new ArrayList<Product>();
		try {
			TopDocs topDocs = searcher.search(booleanQuery, 10);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for(ScoreDoc scoreDoc:scoreDocs){
				Product product = doc2Procuct(searcher.doc(scoreDoc.doc));
			    lists.add(product);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lists;
		
	}
	
	//为指定的字段创建索引 （用户搜索时瑜伽，显示指定的瑜伽 瑜伽服 瑜伽垫，等）
	public void indexText(List<String> keywords){
		IndexWriter writer = LunceneUtils.getIndexWriter("WEB-INF/classes/textIndex");
		
		Document doc = null;
		try {
			for (String keyword:keywords) {
				doc = new Document();
				doc.add(new Field("keyword", keyword, Field.Store.YES, Field.Index.ANALYZED));
				writer.addDocument(doc);
			}
			writer.optimize();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public List<String> searchText(String keyword){
		IndexSearcher searcher = LunceneUtils.getIndexSearcher("WEB-INF/classes/textIndex");
		System.out.println("lucene的索引 所在位置："+new File("WEB-INF/classes/textIndex").getAbsolutePath());
		
		TermQuery termQuery = new TermQuery(new Term("keyword", keyword));
		List<String> results = new ArrayList<String>();
		try {
			TopDocs topDocs = searcher.search(termQuery, 10);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for(ScoreDoc scoreDoc:scoreDocs){
				Document doc = searcher.doc(scoreDoc.doc);
				String result  = doc.get("keyword");
				results.add(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return results;
		
	}
	
	
	
	
	
	//将对应的product转换为document对象
	//进行索引的字段有id，name,description,imgUrl,maxPrice,minPrice
	private Document product2Doc(Product product){
		if(product==null){
			logger.warn("sorry the product is null");
			return null;
		}
		
		Document doc = new Document();
		
		if(product.getId()!=null){
			Field id = new Field("id", String.valueOf(product.getId()), Field.Store.YES, Field.Index.ANALYZED);
			doc.add(id);
		}
		if(product.getName()!=null){
			Field name = new Field("name", product.getName(), Field.Store.YES, Field.Index.ANALYZED);
			doc.add(name);
		}
		if(product.getDescription()!=null){
			Field desc = new Field("description", product.getDescription(), Field.Store.YES, Field.Index.ANALYZED);
			doc.add(desc);
		}
		if(product.getKeywords()!=null){
			Field keywords = new Field("keywords", product.getKeywords(), Field.Store.YES, Field.Index.ANALYZED);
			doc.add(keywords);
		}
		if(product.getImgUrl()!=null){
			Field imgUrl = new Field("imgUrl", product.getImgUrl(), Field.Store.YES, Field.Index.ANALYZED);
			doc.add(imgUrl);
		}
		
		Field maxPrice = new Field("maxPrice", String.valueOf(product.getMaxPrice()), Field.Store.YES, Field.Index.ANALYZED);
		doc.add(maxPrice);
		
		Field minPrice = new Field("minPrice", String.valueOf(product.getMinPrice()), Field.Store.YES, Field.Index.ANALYZED);
		doc.add(minPrice);
		
		return doc; 
	}
	
	//将document转换为product对象
	public Product doc2Procuct(Document doc){
	    Product product = new Product();
	    
	    if(doc==null){
	    	logger.warn("sorry the doc is not null");
	    	return null;
	    }
	    
	    product.setId(Integer.parseInt(doc.get("id")));
	    product.setName(doc.get("name"));
	    product.setDescription(doc.get("description"));
	    product.setKeywords(doc.get("keywords"));
	    product.setImgUrl(doc.get("imgUrl"));
	    product.setMaxPrice(Double.parseDouble(doc.get("maxPrice")));
	    product.setMinPrice(Double.parseDouble(doc.get("minPrice")));
	    
	    
	    return product;
	}
	
	
	

}
