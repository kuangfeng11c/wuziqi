package wuziqi1;
import javax.swing.JFrame;
import javax.swing.JButton;
public class wuziqi2 {
	//��Ҫ������ά��������
	public static void main(String[] args) {
		wuziqi2 frame=new wuziqi2();
		System.out.println("Hello World");
	}
	public wuziqi2()
	{
		JFrame newframe=new JFrame("������");
		newframe.setSize(640,480);
		newframe.setVisible(true);
		newframe.setLayout(null);
		JButton start=new JButton("��ʼ");
		start.setBounds(500,200,120,50);
		newframe.add(start);
		
		
	}

}