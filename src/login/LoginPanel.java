package login;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
	protected ImageIcon imageIcon;
	public int width,height;
	public LoginPanel()
	{
		super();
		imageIcon=new ImageIcon("");
		width=imageIcon.getIconWidth();
		height=imageIcon.getIconHeight();
		setSize(width,height);
	}
	//��ͼƬ������
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image image=imageIcon.getImage();
		g.drawImage(image, width, height, getParent());//int ����ָ����λ�ã�Ȼ�����
	}
}

