package Sync;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * 用于实现文件同步算法
 * @author jxy
 * @category 后端算法实现
 *
 */

public class File_SYNC {
	
	/**
	 * 利用NIO库复制，覆盖复制
	 * @param org 源路径
	 * @param aim 目标路径
	 * @return 成功为ture 失败为false
	 */
	public static boolean move(String org,String aim)
	{
		Path a=Paths.get(org),b=Paths.get(aim);
		try {
			Files.copy(a, b,  new CopyOption[] { REPLACE_EXISTING });
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 判断文件A,B是否相同
	 * @param A
	 * @param B
	 * @return 
	 */
	public static boolean is_same(String A,String B){
		if(A.equals("")||B.equals(""))return false;
		File a=new File(A),b=new File(B);
		if(a.getTotalSpace()==b.getTotalSpace()&&a.lastModified()==b.lastModified()){
			return true;
		}else{
			//TODO MD5
			return false;
		}
	}
	/**
	 * 得出A,B文件夹匹配结果
	 * @param A 文件夹目录
	 * @param B 文件夹目录
	 * @param kind 0 none 1 a->b 2 b->a 3 a->b,b->a
	 * @return  choose,patha,pathb,vector or null
	 */
	public static Vector diff(String A,String B,int kind){
		Vector ans=new Vector<>();
		File file_a,file_b;
		Map<String, String> Map_a=null,Map_b=null;
		if(!A.equals("")){ //A不为空
			file_a=new File(A);
			Map_a=new TreeMap<String, String>();	
			for(File s:file_a.listFiles()){
				Map_a.put(s.getName(),s.getAbsolutePath());
			}
		}
		if(!B.equals("")){ //B不为空
			file_b=new File(B);
			Map_b=new TreeMap<String, String>();
			for(File s:file_b.listFiles()){
				Map_b.put(s.getName(), s.getAbsolutePath());
			}
		}
		Vector tmp;
		
		if(Map_a!=null&&(kind==1||kind==3)){ //a->b
			Set<String> key=new TreeSet<String>(Map_a.keySet());
			for(String s:key){
				tmp=new Vector<>();
				tmp.add(true);tmp.add(Map_a.get(s));
				Map_a.remove(s);
				if(Map_b==null||Map_b.get(s)==null){ 
					tmp.add("");
				}else{
					tmp.add(Map_b.get(s));
					Map_b.remove(s);
				}
				if((new File((String)tmp.get(1))).isDirectory()){ //如果是文件夹
					tmp.add(diff((String)tmp.get(1),(String)tmp.get(2),kind));//递归查找
				}else{
					if(is_same((String)tmp.get(1),(String)tmp.get(2))){
						tmp.add(null);
					}else{
						if(kind==1||tmp.get(2).equals("")){
							tmp.add(1); //标记传输方式 a->b
						}else{
							tmp.add(0);//不确定
							tmp.set(0, false);
						}
							
					}
				}
				if(tmp.get(3)!=null){ans.add(tmp);} //如果为null就不要
			}
		}
		
		if(Map_b!=null&&(kind==2||kind==3)){ //b->a
			Set<String> key=new TreeSet<String>(Map_b.keySet());
			for(String s:key){
				tmp=new Vector<>();
				tmp.add(true);
				if(Map_a==null||Map_a.get(s)==null){ 
					tmp.add("");
				}else{
					tmp.add(Map_a.get(s));
					Map_a.remove(s);
				}
				tmp.add(Map_b.get(s));
				Map_b.remove(s);
				if((new File((String)tmp.get(1))).isDirectory()){ //如果是文件夹
					tmp.add(diff((String)tmp.get(1),(String)tmp.get(2),kind));//递归查找
				}else{
					if(is_same((String)tmp.get(1),(String)tmp.get(2))){
						tmp.add(null);
					}else{
						if(kind==2||tmp.get(1).equals("")){
							tmp.add(2); //标记传输方式 b->a
						}else{
							tmp.add(0);//不确定
							tmp.set(0, false);
						}
					}
				}
				if(tmp.get(3)!=null){ans.add(tmp);}
			}
		}
		
		
		if(ans.size()==0){ans=null;}//方便空文件夹自动忽略
		return ans;
	}
	

	public static void main(String[] args) {
		Vector ans=diff("C:\\Users\\jxy1\\Desktop\\test\\1", "C:\\Users\\jxy1\\Desktop\\test\\2", 3);
		int a=0;
		int b=a;
	}
}
