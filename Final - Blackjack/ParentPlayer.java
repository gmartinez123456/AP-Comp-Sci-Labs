import java.util.*;

public class ParentPlayer {
    private String name;
    private int    money;
    private int    bet;
    private ArrayList<Card> hand = new ArrayList<Card>();
    
    public ParentPlayer(String name, int money) {
        this.name = name;
        this.money = money;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMoney() {
        return this.money;
    }
    
    public void bet(int bet) {
        loseMoney(bet);
    }
    
    public void winMoney(int winmoney) {
        money += winmoney*2;
    }
    
    public void loseMoney(int money) {
        this.money -= money;
    }
    
    public Card showCard(int index) {
        return hand.get(index);
    }
    
    public Card showFirstCard() {
        return this.showCard(0);
    }
    
    public String showCards() {
        String cards = "";
        for(int i = 0; i < hand.size(); i++) {
            cards += this.showCard(i) + " ";
        }
        return cards;
    }
    
    public void hit() {
        hand.add(Game.deck.deal());
    }
    
    public void newHand() {
        hand.clear();
    }
    
    public int cardTotal() {
        int total = 0;
        for(int i = 0; i < hand.size(); i++) {
            total += hand.get(i).getPointValue();
        }
        return total;
    }
    
    public boolean checkBust() {
        if(this.cardTotal() > 21) {
            return true;
        }
        return false;
    }
    
    public boolean loseCondition() {
        if(this.money <= 0) {
            return true;
        }
        return false;
    }
}