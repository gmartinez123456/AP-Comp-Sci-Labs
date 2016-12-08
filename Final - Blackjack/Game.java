import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/* To-do list
 * Stand
 * Bots
 * Bot Difficulty
 * 
*/

public class Game {
    
    static Deck deck = new Deck();
    static ArrayList<Player> players = new ArrayList<Player>();
    
    public Game() {
        JFrame window                = new JFrame("Blackjack Initialization");
        
        JPanel rulesPanel            = new JPanel();
        JButton rulesButton          = new JButton("Rules");
        String rules                 = "Who needs rules?";
        
        JPanel dealerPanel           = new JPanel();
        JLabel dealerLabel           = new JLabel("Dealer");
        JLabel dealerNameLabel       = new JLabel("Name:");
        JTextField dealerNameInput   = new JTextField();
        
        JPanel playerPanel           = new JPanel();
        JLabel playerLabel           = new JLabel("Player");
        JLabel playerNameLabel       = new JLabel("Name:");
        JTextField playerNameInput   = new JTextField();
        JLabel playerMoneyLabel      = new JLabel("Starting Money:");
        JTextField playerMoneyInput  = new JTextField();
        
        JPanel botMasterPanel        = new JPanel();
        JPanel botPanel              = new JPanel();
        JLabel botLabel              = new JLabel("Bot");
        JLabel botNameLabel          = new JLabel("Name:");
        JTextField botNameInput      = new JTextField();
        JLabel botMoneyLabel         = new JLabel("Starting Money:");
        JTextField botMoneyInput     = new JTextField();
        JLabel botDifficultyLabel    = new JLabel("Difficulty:");
        JComboBox botDifficultyInput = new JComboBox();
        JButton deleteBotButton      = new JButton("Delete");
        JButton addBotButton         = new JButton("Add");
        
        JPanel gameInitPanel         = new JPanel();
        JButton startButton          = new JButton("Start Game");
        JButton exitButton           = new JButton("Quit Game");
        
        rulesPanel.add(rulesButton);
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JFrame(), rules, "Blackjack Rules", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        GridLayout dealerLayout = new GridLayout(1,7);
        dealerPanel.setLayout(dealerLayout);
        dealerPanel.add(dealerLabel);
        dealerPanel.add(dealerNameLabel);
        dealerPanel.add(dealerNameInput);
        dealerPanel.add(new JPanel());
        dealerPanel.add(new JPanel());
        dealerPanel.add(new JPanel());
        dealerPanel.add(new JPanel());
        
        GridLayout playerLayout = new GridLayout(1,6);
        playerPanel.setLayout(playerLayout);
        playerPanel.add(playerLabel);
        playerPanel.add(playerNameLabel);
        playerPanel.add(playerNameInput);
        playerPanel.add(playerMoneyLabel);
        playerPanel.add(playerMoneyInput);
        playerPanel.add(new JPanel());
        playerPanel.add(new JPanel());
        
        GridLayout botLayout = new GridLayout(1,6);
        botPanel.setLayout(botLayout);
        botPanel.add(botLabel);
        botPanel.add(botNameLabel);
        botPanel.add(botNameInput);
        botPanel.add(botMoneyLabel);
        botPanel.add(botMoneyInput);
        botPanel.add(botDifficultyLabel);
        botPanel.add(botDifficultyInput);
        botPanel.add(deleteBotButton);
        
        botMasterPanel.add(addBotButton);
        addBotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botMasterPanel.add(botPanel);
                botMasterPanel.revalidate();
                botMasterPanel.repaint();
            }
        });
        deleteBotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println((JButton)(e.getSource()).getParent());
                //botMasterPanel.remove(e.getSource().getParent());
            }
        });
        
        gameInitPanel.add(exitButton);
        gameInitPanel.add(startButton);
        
        GridLayout mainLayout = new GridLayout(5,1);
        window.setLayout(mainLayout);
        window.add(rulesPanel);
        window.add(dealerPanel);
        window.add(playerPanel);
        window.add(botMasterPanel);
        window.add(gameInitPanel);
        
        window.setSize(600, 165);
        //window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // spaghetti code starts here
                
                Scanner playerInput = new Scanner(System.in);
                
                if(!dealerNameInput.getText().equals("") && !playerNameInput.getText().equals("") && !playerMoneyInput.getText().equals("")) {
                    Dealer dealer = new Dealer(dealerNameInput.getText());
                    Player player = new Player();
                    try {
                        player = new Player(playerNameInput.getText(), Integer.parseInt(playerMoneyInput.getText()));
                    } catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    window.setVisible(false);
                    
                    System.out.println("Welcome to Blackjack");
                    System.out.println();
                    System.out.println(dealer.getClass().getName() + ": " + dealer.getName() + "\tMoney: $" + dealer.getMoney());
                    System.out.println(player.getClass().getName() + ": " + player.getName() + "\tMoney: $" + player.getMoney());
                    System.out.println();
                    while(player.loseCondition() == false) {
                        System.out.println("You have $" + player.getMoney() + ". How much would you like to bet?");
                        int bet = playerInput.nextInt();
                        if(bet <= player.getMoney()) {
                            player.bet(bet);
                            System.out.println("You now have $" + player.getMoney() + ".");
                            System.out.println("Dealing cards...");
                            dealer.hit();
                            player.hit();
                            dealer.hit();
                            player.hit();
                            System.out.println(dealer.getName() + " has a " + dealer.showFirstCard());
                            System.out.println(player.getName() + " has " + player.showCards());
                            do {
                                if(dealer.cardTotal() > 21) {
                                    System.out.println("Dealer has lost");
                                    player.winMoney(bet);
                                    break;
                                } else if(player.cardTotal() > 21) {
                                    System.out.println("You got busted. You lose this round.");
                                    break;
                                }
                                System.out.println("Hit or stand?");
                                String choice = playerInput.next();
                                if(choice.toLowerCase().equals("hit")) {
                                    player.hit();
                                    System.out.println(player.getName() + " has " + player.showCards());
                                    if(dealer.cardTotal() < 17) {
                                        dealer.hit();
                                    }
                                } else if(choice.toLowerCase().equals("stand")) {
                                    while(dealer.cardTotal() < 17) {
                                        dealer.hit();
                                    }
                                    System.out.println(dealer.getName() + "has " + dealer.showCards());
                                    System.out.println(player.getName() + "has " + player.showCards());
                                    if(dealer.cardTotal() == player.cardTotal()) {
                                        System.out.println("Pass");
                                        player.winMoney(bet);
                                    } else if(dealer.cardTotal() > player.cardTotal()) {
                                        System.out.println("You lose this round");
                                    } else if(dealer.cardTotal() < player.cardTotal()) {
                                        System.out.println("You win this round");
                                        player.winMoney(bet);
                                    }
                                    break;
                                }
                            } while(true);
                            dealer.newHand();
                            player.newHand();
                        } else {
                            System.out.println("You do not have enough money!");
                        }
                    }
                    System.out.println("You Lose");
                    window.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Fill in all the text forms.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } // 
        });
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(new JFrame(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (InstantiationException e) {
            JOptionPane.showMessageDialog(new JFrame(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(new JFrame(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        Game game = new Game();
    }
}