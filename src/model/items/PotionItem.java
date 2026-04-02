package model.items;

import model.characters.Player;
import model.characters.Monster;

public class PotionItem extends Item {

  

  private int hpChange;

  public PotionItem(String name, int hpChange) {
	  super(name);
	  this.hpChange = hpChange;
  }

  // Potion affects only effects Player
  // Will either heal or hurt player depending on value given

  @Override
  public void use(Player player, Monster monster) {
	  if (player == null) { 
		  return;
	  }
	  player.changeHp(hpChange);

	  
  }

  public int getHpChange() {
	  return hpChange;
  }
  
}
