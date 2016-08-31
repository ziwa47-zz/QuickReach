package tw.iii.qr;

import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;


public class CWebTree {
	private static JTree tree;
	public static void main(String[] args) {
	
			DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("QuickReach");
			  createNodes(dmtn);
			    tree = new JTree(dmtn);
			
					
					
		
		
	
	
	
	

	}
	private static void createNodes(DefaultMutableTreeNode top) {
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode book = null;
	    
	    category = new DefaultMutableTreeNode("QRProduct");
	    top.add(category);
	    
}
}
