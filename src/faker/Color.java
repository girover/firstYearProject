package faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class Color {

	private ArrayList<String> safeColorNames = new ArrayList<>(Arrays.asList("black", "maroon", "green", "navy",
			"olive", "purple", "teal", "lime", "blue", "silver", "gray", "yellow", "fuchsia", "aqua", "white"));

	private ArrayList<String> allColorNames = new ArrayList<>(Arrays.asList("AliceBlue", "AntiqueWhite", "Aqua",
			"Aquamarine", "Azure", "Beige", "Bisque", "Black", "BlanchedAlmond", "Blue", "BlueViolet", "Brown",
			"BurlyWood", "CadetBlue", "Chartreuse", "Chocolate", "Coral", "CornflowerBlue", "Cornsilk", "Crimson",
			"Cyan", "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray", "DarkGreen", "DarkKhaki", "DarkMagenta",
			"DarkOliveGreen", "Darkorange", "DarkOrchid", "DarkRed", "DarkSalmon", "DarkSeaGreen", "DarkSlateBlue",
			"DarkSlateGray", "DarkTurquoise", "DarkViolet", "DeepPink", "DeepSkyBlue", "DimGray", "DimGrey",
			"DodgerBlue", "FireBrick", "FloralWhite", "ForestGreen", "Fuchsia", "Gainsboro", "GhostWhite", "Gold",
			"GoldenRod", "Gray", "Green", "GreenYellow", "HoneyDew", "HotPink", "IndianRed", "Indigo", "Ivory", "Khaki",
			"Lavender", "LavenderBlush", "LawnGreen", "LemonChiffon", "LightBlue", "LightCoral", "LightCyan",
			"LightGoldenRodYellow", "LightGray", "LightGreen", "LightPink", "LightSalmon", "LightSeaGreen",
			"LightSkyBlue", "LightSlateGray", "LightSteelBlue", "LightYellow", "Lime", "LimeGreen", "Linen", "Magenta",
			"Maroon", "MediumAquaMarine", "MediumBlue", "MediumOrchid", "MediumPurple", "MediumSeaGreen",
			"MediumSlateBlue", "MediumSpringGreen", "MediumTurquoise", "MediumVioletRed", "MidnightBlue", "MintCream",
			"MistyRose", "Moccasin", "NavajoWhite", "Navy", "OldLace", "Olive", "OliveDrab", "Orange", "OrangeRed",
			"Orchid", "PaleGoldenRod", "PaleGreen", "PaleTurquoise", "PaleVioletRed", "PapayaWhip", "PeachPuff", "Peru",
			"Pink", "Plum", "PowderBlue", "Purple", "Red", "RosyBrown", "RoyalBlue", "SaddleBrown", "Salmon",
			"SandyBrown", "SeaGreen", "SeaShell", "Sienna", "Silver", "SkyBlue", "SlateBlue", "SlateGray", "Snow",
			"SpringGreen", "SteelBlue", "Tan", "Teal", "Thistle", "Tomato", "Turquoise", "Violet", "Wheat", "White",
			"WhiteSmoke", "Yellow", "YellowGreen"));

	/**
	 * @example "#fa3cc2"
	 */
	public String hexColor() {
		Random random = new Random();
		// generate three random numbers between 0 and 255
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);

		// convert the numbers to their hexadecimal representation
		return String.format("#%02x%02x%02x", r, g, b);
	}

	/**
	 * @example "[0,255,122]"
	 *
	 * @return int[]
	 */
	public int[] rgbColorAsArray() {
		Random random = new Random();
		int[] colors = new int[3];

		// generate three random numbers between 0 and 255
		colors[0] = random.nextInt(256);
		colors[1] = random.nextInt(256);
		colors[2] = random.nextInt(256);

		return colors;
	}

	/**
	 * @example "0,255,122"
	 */
	public String rgbColor() {
		StringBuilder sb = new StringBuilder();

		for (int value : this.rgbColorAsArray()) {
			sb.append(value).append(",");
		}
		String result = sb.toString();

		// removing the last comma
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	/**
	 * @example "rgb(0,255,122)"
	 */
	public String rgbCssColor() {
		return String.format("rgb(%s)", rgbColor());
	}

	/**
	 * @example "rgba(0,255,122,0.8)"
	 */
	public String rgbaCssColor() {
		Number number = new Number();

		return String.format("rgba(%s,%s)", rgbColor(), number.randomFloat(0, 1));
	}

	/**
	 * @example "blue"
	 */
	public String safeColorName() {
		Number number = new Number();
		int n = number.numberBetween(0, safeColorNames.size());

		return safeColorNames.get(n);
	}

	/**
	 * @example "NavajoWhite"
	 */
	public String colorName() {
		Number number = new Number();
		int n = number.numberBetween(0, allColorNames.size());

		return allColorNames.get(n);
	}

	/**
	 * @example "340,50,20"
	 */
	public String hslColor() {
		Number number = new Number();

		return String.format("%s,%s,%s", number.numberBetween(0, 360), number.numberBetween(0, 100),
				number.numberBetween(0, 100));
	}

	/**
	 * @example array(340, 50, 20)
	 *
	 * @return int[]
	 */
	public int[] hslColorAsArray() {
		Number number = new Number();
		int[] colors = new int[3];

		colors[0] = number.numberBetween(0, 360);
		colors[1] = number.numberBetween(0, 100);
		colors[2] = number.numberBetween(0, 100);

		return colors;
	}
}
