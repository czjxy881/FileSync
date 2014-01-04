package Sync;

import java.io.File;
import java.nio.file.WatchEvent.Kind;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
/**
 * ������
 * @author jxy
 * û����������Σ�գ���
 */
public class Task extends Thread {
	private String A,B;
	private File fa,fb;
	private long last_refresh;//�ϴ�ȷ��ͬ���ƻ�ʱ��
	private int kind;// kind Ϊ����ʽ
	private Vector detail;//ͬ����ϸ��Ϣ���ṹͬdiff
	
	public Task(String a,String b,int k){
		A=a;B=b;kind=k;
		fa=new File(A);fb=new File(B);
		refresh();
	}
	public Vector getDetail(){
		if(fa.lastModified()>last_refresh||fb.lastModified()>last_refresh){ //�����ļ�
			refresh();
		}
		return detail;
	}
	private void refresh(){
		detail=File_SYNC.diff(A, B, kind);
		last_refresh=new Date().getTime(); //���¸���ʱ��
	}
	public void update(Vector now){//����detail ע���ʽ����Σ�գ���
		detail=now;
	}
	/**
	 * ʵ�ָ��Ƶķ���,�ݹ�ʵ��
	 * @param det ����detail
	 * @return �ɹ�����
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
						if(File_SYNC.move((String)now.get(1),B+((String)now.get(1)).substring(A.length()))){sum++;}//��ȫB�����·��
						break;
					case 2:
						if(File_SYNC.move((String)now.get(2),A+((String)now.get(2)).substring(B.length()))){sum++;}//��ȫB�����·��
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
