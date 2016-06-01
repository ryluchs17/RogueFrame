import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * A class to display the help text for RogueFrame
 * @author Ryan Luchs
 *
 */
public class HelpFrame extends JFrame {
		
		private static final long serialVersionUID = 1L;
		
		JTextArea text;
		JScrollPane scroll;
		
		/**
		 * HelpFrame constructor
		 */
		HelpFrame() {
			super("Help");
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//			this.setVisible(true);
			this.setBackground(Color.BLACK);

			text = new JTextArea(20, 65);
			text.setBackground(Color.BLACK);
			text.setForeground(Color.WHITE);
			text.setFont(MapPanel.font);
			
			StringBuilder s = new StringBuilder();
			
			try {
				BufferedReader read = new BufferedReader(new FileReader("about.txt"));
				while(read.ready()) {
					s.append(' ');
					s.append(read.readLine());
					s.append('\n');
				}
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			text.setText(s.toString());
			
			text.setEditable(false);
			
			scroll = new JScrollPane(text);
//			scroll.getHorizontalScrollBar().setBackground(Color.BLACK);
//			scroll.getVerticalScrollBar().setBackground(Color.BLACK);
			add(scroll);

			pack();
		}
}
