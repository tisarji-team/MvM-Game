

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
	private int		xPosition;
	private int		yPosition;

	public void	CharacterClass(String nameCharacter, int healthPoint, int attackDamage, int speedAttack, int walkSpeed)
	{
		this.nameCharacter = nameCharacter;
		this.attackDamage = attackDamage;
		this.healthPoint = healthPoint;
		this.speedAttack = speedAttack;
		this.walkSpeed = walkSpeed;
		this.xPosition = -1;
		this.yPosition = -1;
	}

	public boolean isPlaceOnTable()
	{
		return (xPosition >= 0 && yPosition >= 0);
	}

	public void setPosition(int x, int y)
	{
		this.xPosition = x;
		this.yPosition = y;
	}

	public void placeCharacter(GameTable gameTable)
	{
		if (!isPlaceOnTable())
			gameTable.setCellValue(xPosition, yPosition, 'c');
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
	
	public void showStats()
	{
		System.out.println("Name : " + nameCharacter);
		System.out.println("Damage : " + attackDamage);
		System.out.println("Health :" + healthPoint);
		System.out.println("Speed :" + walkSpeed);
		System.out.println("Speed Attack : " + speedAttack);
	}
}
