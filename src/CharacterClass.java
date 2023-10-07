/**
 *
 * @author Tisarji
 */

import java.awt.image.BufferedImage;

public class CharacterClass {
	private String	nameCharacter;
	private int	healthPoint;
	private int	attackDamage;
	private int	speedAttack;
	private int	walkSpeed = 10;
	private int	xPosition;
	private int	yPosition;
	private BufferedImage characterImage;

	public CharacterClass(String nameCharacter, int healthPoint, int attackDamage, int speedAttack, int walkSpeed, BufferedImage characterImage) {
		this.nameCharacter = nameCharacter;
		this.attackDamage = attackDamage;
		this.healthPoint = healthPoint;
		this.speedAttack = speedAttack;
		this.walkSpeed = walkSpeed;
		this.characterImage = characterImage;
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
		if (!isPlaceOnTable()) {
			gameTable.setCellValue(xPosition, yPosition, 'c');
		}
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
