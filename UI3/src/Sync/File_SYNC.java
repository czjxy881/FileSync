package Sync;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;

/**
 * ����ʵ���ļ�ͬ���㷨
 * @author jxy
 * @category ����㷨ʵ��
 *
 */

public class File_SYNC {
	
	/**
	 * ���ڱȽ������ļ����в����ļ�������ļ��в������򷵻�null
	 * @param org ԴĿ¼·��
	 * @param aim Ŀ��·��
	 * @return ���ز����ļ�����һ��ΪԴ�ļ�·�����ڶ���Ŀ���ļ�·����������Ϊ�ļ����ͻ��߲����СVector��0�����޲��죬1�����org��aim��2Ϊaim��org��3Ϊ��������Ҫ�û�ѡ��
	 * 
	 */
	public static Vector  diff_floder(String org,String aim){
		Vector ans=new Vector<>();
		return ans;
	}
	
	public static boolean move(String org,String aim)
	{
		Path a=Paths.get(org),b=Paths.get(aim);
		try {
			Files.copy(a, b,  new CopyOption[] { REPLACE_EXISTING });
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	class t extends Thread{
		String o,g;
		public t(String a,String b){
			o=a;g=b;
		}
		public void run(){
			File_SYNC.move(o, g);
		}
	}
	public static void main(String[] args) {
		Date time=new Date();
		long t=time.getTime();
		System.out.println(time.getTime());
		for(int i=0;i<100;i++){
			//File_SYNC.move("C:\\Users\\jxy1\\Desktop\\test\\1.txt","C:\\Users\\jxy1\\Desktop\\test\\1\\"+String.valueOf(i)+".txt");
			//System.out.println(i);
		}
		System.out.println("test1 "+time.getTime()+","+t);
		
		t=time.getTime();
		Thread []s=new Thread[100];
		for(int i=0;i<100;i++){
			s[i]=new File_SYNC().new t("C:\\Users\\jxy1\\Desktop\\test\\1.txt","C:\\Users\\jxy1\\Desktop\\test\\2\\"+String.valueOf(i)+".txt");
		}
		for(int i=0;i<100;i++){
			s[i].start();
			//System.out.println(i);
		}
		System.out.println("test2 "+time.getTime()+","+t);
		}
}
