import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.util.*;

/**
 * This class SimpleGame initialize the game and set the layout, 
 * and it stores the inner class startgame().
 * 
 * @author Benny Wong UID:3035568881
 * @version 1.0
 */
public class SimpleGame {

	JTextField txt_inputbet;
	JFrame frame;
	JLabel label_info,label_bet,label_money;
	JLabel label_Image1,label_Image2,label_Image3,label_Image4,label_Image5,label_Image6;
	JButton btn_rpcard1,btn_rpcard2,btn_rpcard3,btn_start,btn_result;
	ImageIcon Image1,Image2,Image3,Image4,Image5,Image6;
	int money=100;
	public static void main(String[] args) {  
		SimpleGame game = new SimpleGame();  
		game.go();
	}

	/**
	 * This function constructs the game layout.
	 * It creates all the buttons and menus and panels needed.
	 * 
	 */
	public void go() {  
		label_Image1 = new JLabel();
		label_Image2 = new JLabel();
		label_Image3 = new JLabel();
		label_Image4 = new JLabel();
		label_Image5 = new JLabel();
		label_Image6 = new JLabel();
		
		btn_rpcard1 = new JButton("Replace Card 1");
		btn_rpcard2 = new JButton("Replace Card 2");
		btn_rpcard3 = new JButton("Replace Card 3");
		btn_rpcard1.setOpaque(false);
		btn_rpcard1.setContentAreaFilled(false);
		btn_rpcard2.setOpaque(false);
		btn_rpcard2.setContentAreaFilled(false);
		btn_rpcard3.setOpaque(false);
		btn_rpcard3.setContentAreaFilled(false);
		btn_start = new JButton("Start");
		btn_result = new JButton("Result");
		
		label_bet = new JLabel("Bet: $");
		label_info = new JLabel("Please place your bet! ");
		label_money = new JLabel("Amount of money you have: $"+money);
		
		txt_inputbet = new JTextField(10);
		
		Image1 = new ImageIcon("src//Images//card_back.gif");
		Image2 = new ImageIcon("src//Images//card_back.gif");
		Image3 = new ImageIcon("src//Images//card_back.gif");
		Image4 = new ImageIcon("src//Images//card_back.gif");
		Image5 = new ImageIcon("src//Images//card_back.gif");
		Image6 = new ImageIcon("src//Images//card_back.gif");
		
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);
		
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();
		
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);

		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		InfoPanel.add(label_info);
		InfoPanel.add(label_money);
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		
		// Optional background color setting
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuA = new JMenu("Control");
		JMenuItem menuItemA = new JMenuItem("Exit");
		
		/**
		 * This function gives the exit function to the Exit button.
		 * 
		 */
		menuItemA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuA.add(menuItemA);
		
		JMenu menuB = new JMenu("Help");
		JMenuItem menuItemB = new JMenuItem("Instruction");
		
		/**
		 * This function makes the Instruction button to display a pop up window showing the rules.
		 * 
		 */
		menuItemB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Rules to determine who has better cards:\r\n" + 
						"J, Q, K are regarded as special cards.\r\n" + 
						"Rule 1: The one with more special cards wins.\r\n" + 
						"Rule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).\r\n" + 
						"Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.");
			}
		});
		menuB.add(menuItemB);
		
		menuBar.add(menuA);
		menuBar.add(menuB);
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);
				
		btn_start.addActionListener(new startgame());
	}
	
	/**
	 * This class stores the functions to enable or disable buttons.
	 * It also determines the winner and does the calculation for money.
	 * 
	 */
	class startgame implements ActionListener { 
		int bet;
		int rp=0;
		boolean finish=false;
		
		/**
		 * This function is activated after the start button is clicked.
		 * 
		 */
		public void actionPerformed(ActionEvent event) {
			try {
				bet = Integer.parseInt(txt_inputbet.getText());
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame, "WARNING: The bet you place must be a positive integer!");
			}
			if (bet < 0) {
					JOptionPane.showMessageDialog(frame,"WARNING: The bet you place must be a positive integer!");
            	}
				else if(bet > money) {
    				JOptionPane.showMessageDialog(frame,"WARNING: You only have $"+money+'!');
            	}
				else if(bet > 0 && bet <= money) {
					label_info.setText("Your current bet is : $"+Integer.toString(bet)+" ");
					label_money.setText("Amount of money you have: $"+money);
					ArrayList<Integer> deck = new ArrayList<Integer>();
					ArrayList<String> order = new ArrayList<String>();
					for (int i = 0; i < 52; i++) {
						deck.add(i);
					}
					Collections.shuffle(deck);
					for (int i=0;i<8;i++) {
						int temp = deck.get(0);
						deck.remove(0);
						String combine= Integer.toString(temp/13+1)+Integer.toString(temp%13+1);
						order.add(combine);		
					}
					deck.clear();
					label_Image4.setIcon(new ImageIcon("src//Images//card_"+order.get(3)+".gif"));
					label_Image5.setIcon(new ImageIcon("src//Images//card_"+order.get(4)+".gif"));
					label_Image6.setIcon(new ImageIcon("src//Images//card_"+order.get(5)+".gif"));
					
					/**
					 * This function is activated after the "replace card 1" button is clicked.
					 * It gives a new card to slot 1.
					 * 
					 */
					btn_rpcard1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (rp>=2 || finish) {
								btn_rpcard1.removeActionListener(this);
							} else {
								label_Image4.setIcon(new ImageIcon("src//Images//card_"+order.get(6)+".gif"));
								order.set(3,order.get(6));
								order.remove(6);
								rp+=1;
								btn_rpcard1.removeActionListener(this);
							}
						}
					});
					
					/**
					 * This function is activated after the "replace card 2" button is clicked.
					 * It gives a new card to slot 2.
					 * 
					 */
					btn_rpcard2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (rp>=2 || finish) {
								btn_rpcard2.removeActionListener(this);
							} else {
								label_Image5.setIcon(new ImageIcon("src//Images//card_"+order.get(6)+".gif"));
								order.set(4,order.get(6));
								order.remove(6);
								rp+=1;
								btn_rpcard2.removeActionListener(this);
							}
						}
					});
					
					/**
					 * This function is activated after the "replace card 3" button is clicked.
					 * It gives a new card to slot 3.
					 * 
					 */
					btn_rpcard3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (rp>=2 || finish) {
								btn_rpcard3.removeActionListener(this);
							} else {
								label_Image6.setIcon(new ImageIcon("src//Images//card_"+order.get(6)+".gif"));
								order.set(5,order.get(6));
								order.remove(6);
								rp+=1;
								btn_rpcard3.removeActionListener(this);
							}
						}
					});
					
					/**
					 * This function is activated after the result button is clicked.
					 * It determines the winner and calculate the money left for player.
					 * If the player has no money left, it ends the game.
					 * Otherwise, it continues the game by starting a new round.
					 * 
					 */
					btn_result.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int dsp=0,psp=0,drm=0,prm=0;
							finish=true;
							label_Image1.setIcon(new ImageIcon("src//Images//card_"+order.get(0)+".gif"));
							label_Image2.setIcon(new ImageIcon("src//Images//card_"+order.get(1)+".gif"));
							label_Image3.setIcon(new ImageIcon("src//Images//card_"+order.get(2)+".gif"));
							for (int i = 0; i < 3; i++) {
								if((order.get(i).length()==3) && (order.get(i).charAt(2)!='0')) {
									dsp++;
								} else if((order.get(i).length()==3) && (order.get(i).charAt(2)=='0')){
									drm+=10;
								} else {
									drm+=Character.getNumericValue(order.get(i).charAt(1));
								}
							}
							drm%=10;
							for (int i = 3; i < 6; i++) {
								if((order.get(i).length()==3) && (order.get(i).charAt(2)!='0')) {
									psp++;
								} else if((order.get(i).length()==3) && (order.get(i).charAt(2)=='0')){
									prm+=10;
								} else {
									prm+=Character.getNumericValue(order.get(i).charAt(1));
								}
							}
							prm%=10;
							order.clear();
							if (dsp > psp) {
								JOptionPane.showMessageDialog(frame, "Sorry! The Dealer wins this round!");
								money -= bet;
							} else if (psp > dsp) {
								JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
								money += bet;
							} else if (drm > prm) {
								JOptionPane.showMessageDialog(frame, "Sorry! The Dealer wins this round!");
								money-=bet;
							} else {
								JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
								money+=bet;
							}
							label_info.setText("Please place your bet!");
							label_money.setText("Amount of money you have: $"+Integer.toString(money));
							if(money <= 0) {
								label_info.setText("You have no more money! ");
								label_money.setText("Please start a new game!");
								btn_result.removeActionListener(this);
								JOptionPane.showMessageDialog(frame, "Game over!\r\nYou have no more money!\r\nPlease start a new game!");
							} else {
								btn_start.addActionListener(new startgame());
								label_Image1.setIcon(new ImageIcon("src//Images//card_back.gif"));
								label_Image2.setIcon(new ImageIcon("src//Images//card_back.gif"));
								label_Image3.setIcon(new ImageIcon("src//Images//card_back.gif"));
								label_Image4.setIcon(new ImageIcon("src//Images//card_back.gif"));
								label_Image5.setIcon(new ImageIcon("src//Images//card_back.gif"));
								label_Image6.setIcon(new ImageIcon("src//Images//card_back.gif"));
							}
							btn_result.removeActionListener(this);					
						}
					});
					btn_start.removeActionListener(this);
				}
		}
	}
}