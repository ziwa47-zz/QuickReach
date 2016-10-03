package test;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class test1 {
	public static void main(String[] args) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String path = fsv.getDefaultDirectory().getAbsolutePath()+File.separator+"QuickReach"+File.separator+"pics"+File.separator;
		System.out.println(path);
	}
	}
	
