package pt.technic.apps.minesfinder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class RankingChart extends JFrame {
	public RankingChart() {
		setTitle("Ranking Chart");

		
		GridLayout grid = new GridLayout(6, 5);
		grid.setVgap(5);
		
		Container c = getContentPane();
		c.setLayout(grid);
		
		c.add(new JLabel(" "));
		c.add(new JLabel("Easy"));
		c.add(new JLabel("Mdeium"));
		c.add(new JLabel("Hard"));
		c.add(new JLabel("Battle"));
		
		c.add(new JLabel("1"));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));

		c.add(new JLabel("2"));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		
		c.add(new JLabel("3"));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		
		c.add(new JLabel("4"));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		
		c.add(new JLabel("5"));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));
		c.add(new JLabel(""));

		
		


		

//		setPreferredSize(new java.awt.Dimension(600, 450));  
		//preferredSize 쓰면 더 이상해짐... 
		
		setSize(1000, 500);
		setResizable(false);
		setVisible(true);
	}

}
//왜 JFrame으로 하면 오류가 뜨지..? 