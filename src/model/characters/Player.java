package model.characters;

import java.util.ArrayList;

import model.items.*;

public class Player extends Character {
	private int gachaTickets;
	private Backpack backpack;
	
	public Player(int hp) {
		super(hp);
		gachaTickets = 5;
		attackChance = 0.6;
		dodgeChance = 0.8;
		backpack = new Backpack();
	}
	
	public Backpack getBackpack() {
		return backpack;
	}
	
	public int getGachaTickets() {
		return gachaTickets;
	}
	
	public void useGachaTickets() {
		gachaTickets--;
	}
	
	public void gainGachaTickets(int gainNum) {
		gachaTickets += gainNum;
	}
	
	public void curse(double reduceChance) {
		dodgeChance -= reduceChance;
	}
	
 }
