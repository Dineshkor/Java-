package lec21;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tictactoe extends JFrame implements ActionListener  {
	public boolean crossturn = true;

	public static String Xmove = "X";
	public static String Omove = "0";

	public final int xwin = 0;
	public final int owin = 1;
	public final int tie = 2;
	public final int incomplete = 3;
	public final int board_size = 3;

	JButton[][] board = new JButton[board_size][board_size];

	public Tictactoe() {
		super.setTitle("tic");
		super.setSize(250, 250);
		GridLayout layout = new GridLayout(board_size, board_size);
		super.setLayout(layout);
		Font font = new Font("Time New Roman", 1, 200);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = new JButton("");
				
				button.setFont(font);
				button.addActionListener(this);
				
				board[i][j] = button;
				super.add(button);
			}

		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();
		makemove(btn);
		int gs = gamestatus();
		if(gs!=incomplete){
			declarewinner(gs);
			int res=JOptionPane.showConfirmDialog(null, "restart?");
			
			if(res==JOptionPane.YES_OPTION){
				
				for(int i=0;i<board.length;i++){
					for(int j=0;j<board[i].length;j++){
						board[i][j].setText("");
					}
				}
				crossturn=true;
			}
			else{
				super.dispose();
			}
		}

	}

	public void makemove(JButton btn) {
		if (btn.getText().length() == 0) {
			if (crossturn) {
				btn.setText(Xmove);
			} else {
				btn.setText(Omove);
			}
			crossturn = !crossturn;
		} else {
			JOptionPane.showMessageDialog(this, "invalid move");
		}
	}

	public int gamestatus() {
		int row = 0;
		int col = 0;
		String text1 = "", text2 = "";

		// check row
		while (row < board_size - 1) {
			col = 0;
			while (col < board_size - 1) {
				text1 = board[row][col].getText();
				text2 = board[row][col + 1].getText();
				if (text1.length() == 0 || text1 != text2) {
					break;
				}
				col++;
			}
			if (col == board_size - 1) {
				return text1.equals(Omove) ? owin : xwin;
			}

			row++;
		}
		// check col
		row = 0;
		col = 0;
		while (col < board_size - 1) {
			row = 0;
			while (row < board_size - 1) {
				text1 = board[row][col].getText();
				text2 = board[row + 1][col].getText();
				if (text1.length() == 0 || text1 != text2) {
					break;
				}
				row++;
			}
			if (row == board_size - 1) {
				return text1.equals(Omove) ? owin : xwin;
			}

			col++;
		}
		// dia1
		row = 0;
		col = 0;
		while (col < board_size - 1&&row<board_size-1) {

			text1 = board[row][col].getText();
			text2 = board[row + 1][col + 1].getText();
			if (text1.length() == 0 || text1 != text2) {
				break;
			}
			row++;
			col++;
		}
		if (row == board_size - 1) {
			return text1.equals(Omove) ? owin : xwin;
		}
		row = 0;
		col = board_size-1;
		while (row < board_size - 1 && col > 0) {

			text1 = board[row][col].getText();
			text2 = board[row + 1][col - 1].getText();
			if (text1.length() == 0 || text1 != text2) {
				break;
			}
			row++;
			col++;
		}
		if (row == board_size - 1) {
			return text1.equals(Omove) ? owin : xwin;
		}

		return tie;
	}
	public void declarewinner(int gs){
		if(gs==xwin){
			JOptionPane.showMessageDialog(null, "X wins");
		}
		else if(gs==owin){
			JOptionPane.showMessageDialog(null, "O wins ");
		}
		else{
			JOptionPane.showMessageDialog(null, "its a tie");
		}
	}
}
