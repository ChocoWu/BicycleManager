package internalFrame;

import java.net.URL;

import javax.swing.ImageIcon;

import main.BicycleManager;

public class CreatedIcon {
	public static ImageIcon add(String ImageName){
		String IconUrl = "res/ActionIcon/"+ImageName;
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
