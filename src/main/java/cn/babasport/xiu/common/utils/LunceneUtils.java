package cn.babasport.xiu.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.fasterxml.jackson.core.Version;

import cn.babasport.xiu.core.bean.Product;
import net.paoding.analysis.analyzer.PaodingAnalyzer;

/**
 * lucene的工具类
 * @author xieqixiu
 *
 */
public class LunceneUtils {
	//获取分词器
	public static Analyzer getAnalyzer(){
		//return new StandardAnalyzer();
		//简单的中文分词器（分词效果不是很好）
		//return new SmartChineseAnalyzer();
		//庖丁分词器 (高版本的luncene不支持)
		return new PaodingAnalyzer();
	}
	//获取目录
	public static Directory getDirectory(String path){
		try {
			//luncene 2.4.1
			return FSDirectory.getDirectory(path);
			//luncene 5.3.1
			//return  FSDirectory.open(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取IndexWriter(创建索引的核心类)
	public static IndexWriter getIndexWriter(String path){
		//luncene 5.3.1
		//IndexWriterConfig config = new IndexWriterConfig(getAnalyzer());
		System.out.println("lucene的索引路径： "+new File(path).getAbsolutePath());
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(getDirectory(path), getAnalyzer());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	//获取IndexSearch(搜索操作的核心类)
	public static IndexSearcher getIndexSearcher(String path){
		IndexSearcher searcher = null;
		try {
			//luncene 5.3.1 
			//IndexReader reader = DirectoryReader.open(getDirectory(path));
			//searcher = new IndexSearcher(reader)
			//luncene 2.4.1
		    searcher = new IndexSearcher(getDirectory(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searcher;
	}
	
	
	//查看使用特定分词器后的分词结果 5.3.1版本
	/*
	public static List<String> showAnalyzerResult(Analyzer analyzer,String text){
		List<String> result = new ArrayList<>();
		try {
			TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(text));
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while(tokenStream.incrementToken()){
				result.add(charTermAttribute.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
	
	public static List<String> showPaoingAnalyzer(String text){
		Analyzer analyzer = getAnalyzer();
	    TokenStream tokenStream = analyzer.tokenStream(text, new StringReader(text));
        
	    List<String> strs = new ArrayList<String>();
	 
	    Token t = null;
	    try {
			while ((t = tokenStream.next()) != null)
			{
			    strs.add(t.term());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	    return strs;
	}
	
	
}
