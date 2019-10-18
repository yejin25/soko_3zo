package pt.technic.apps.minesfinder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class RankingBoard extends JFrame {
	public RankingBoard() {
		int rankingNum=1;

		setTitle("Ranking Chart");

		GridLayout grid = new GridLayout(21, 4);
		grid.setVgap(5);

		Container c = getContentPane();
		c.setLayout(grid);

		c.add(new JLabel("Ranking"));
		c.add(new JLabel("Easy"));
		c.add(new JLabel("Mdeium"));
		c.add(new JLabel("Hard"));

//		for (rankingNum = 1; rankingNum == 21; rankingNum++) {
//			c.add(new JLabel(String.valueOf(rankingNum)));
//			c.add(new JLabel(" "));
//			c.add(new JLabel(" "));
//			c.add(new JLabel(" "));
//		}

		while (rankingNum == 20) {
			c.add(new JLabel(String.valueOf(rankingNum)));
			c.add(new JLabel("a "));
			c.add(new JLabel("a "));
			c.add(new JLabel("a "));
			rankingNum++;
		
		}
		setSize(1000, 500);
		setResizable(false);
		setVisible(true);
	}

}