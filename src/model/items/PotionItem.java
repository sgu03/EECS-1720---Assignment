package model.items;

import model.characters.Player;
import model.characters.Monster;

public class PotionItem extends Item {

  

  private int hpChange;

  public PotionItem(String name, int hpChange) {
	  super(name);
	  this.hpChange = hpChange;
	  if (hpChange > 0) {
		  this.msg = "Player +" + hpChange + " HP";
	  } else {
		  this.msg = "Player " + hpChange + " HP";
	  }
  }

  // Potion affects only effects Player
  // Will either heal or hurt player depending on value given

  @Override
  public void use(Player player, Monster monster) {
	  if (player == null) { 
		  return;
	  }
	  player.changeHP(hpChange);

	  
  }

  public int getHpChange() {
	  return hpChange;
  }
  
}
