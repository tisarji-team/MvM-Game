/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tisarji
 */

public class CharacterClass {
	private String	nameCharacter;
	private int		healthPoint;
	private int		attackDamage;
	private int		speedAttack;
	private int		walkSpeed;
	
	public void	CharacterClass(String nameCharacter, int healthPoint, int attack, int speedAttack, int walkSpeed)
	{
		this.nameCharacter = nameCharacter;
		this.attackDamage = attack;
		this.healthPoint = healthPoint;
		this.speedAttack = speedAttack;
		this.walkSpeed = walkSpeed;
	}

	public String getName()
	{
		return (nameCharacter);
	}

	public int getDamage()
	{
		return (attackDamage);
	}

	public int getHealth()
	{
		return (healthPoint);
	}

	public int getSpeedAttack()
	{
		return (speedAttack);
	}

	public int getWalkSpeed()
	{
		return (walkSpeed);
	}

	
}
