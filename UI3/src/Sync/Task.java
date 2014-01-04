package Sync;

import java.io.File;
import java.nio.file.WatchEvent.Kind;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
/**
 * 任务类
 * @author jxy
 * 没有设置锁，危险！！
 */
public class Task extends Thread {
	private String A,B;
	private File fa,fb;
	private long last_refresh;//上次确定同步计划时间
	private int kind;// kind 为任务方式
	private Vector detail;//同步详细信息，结构同diff
	
	public Task(String a,String b,int k){
		A=a;B=b;kind=k;
		fa=new File(A);fb=new File(B);
		refresh();
	}
	public Vector getDetail(){
		if(fa.lastModified()>last_refresh||fb.lastModified()>last_refresh){ //有新文件
			refresh();
		}
		return detail;
	}
	private void refresh(){
		detail=File_SYNC.diff(A, B, kind);
		last_refresh=new Date().getTime(); //最新更新时间
	}
	public void update(Vector now){//更新detail 注意格式，很危险！！
		detail=now;
	}
	/**
	 * 实现复制的方法,递归实现
	 * @param det 详情detail
	 * @return 成功个数
	 */
	private int work(Vector det){
		int sum=0;
		for(int i=0;i<det.size();i++){
			Vector now=(Vector) det.get(i);
			if((boolean)now.get(0)==false)continue;
			if(now.get(3) instanceof Vector){
				sum+=work((Vector) now.get(3));
			}else{
				switch ((int)now.get(3)){
					case 1:
						if(File_SYNC.move((String)now.get(1),B+((String)now.get(1)).substring(A.length()))){sum++;}//补全B的相对路径
						break;
					case 2:
						if(File_SYNC.move((String)now.get(2),A+((String)now.get(2)).substring(B.length()))){sum++;}//补全B的相对路径
						break;
				}
			}
		}
		return sum;
	}
	@Override
	public void run() {
		work(detail);
	}
	public static void main(String[] args) {

	}
}
