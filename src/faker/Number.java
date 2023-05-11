package faker;

import java.util.Random;

/**
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *
 */
public class Number {
	
	private Random random;
	
	public Number(){
		random = new Random();
	}
	
	public int numberBetween(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }

	public int randomDigit()
    {
        return numberBetween(0, 9);
    }

	public int randomDigitNot(int except)
    {
        int result = numberBetween(0, 8);

        if (result >= except) {
            ++result;
        }

        return result;
    }

	public int randomDigitNotZero()
    {
        return numberBetween(1, 9);
    }

	public float randomFloat(float min, float max)
    {
		return (float) (random.nextDouble() * ( max - min ) + min);
    }

	public long randomNumber(int digits)
    {
        int min = (int) Math.pow(10, digits - 1); // minimum value
        int max = (int) Math.pow(10, digits) - 1; // maximum value

        Random random = new Random();
        
        return random.nextInt(max - min + 1) + min;
    }
}
