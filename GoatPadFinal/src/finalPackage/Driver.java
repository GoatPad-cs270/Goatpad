package finalPackage;

public class Driver {

	static DisplayPanel display;

	public static void main(String[] args) {
		// translateTests();
		display = new DisplayPanel(1000, 1000);
		display.textArea.setBounds(2, 40, display.getWidth(), 1000);
	}

	public static void translateTests() {

		String englishVerb = "fruit punch";
		String goatVerb = "AW brrybyaawb";
		toolbar toolbar = new toolbar();

		System.out.println(toolbar.translateToGoat(englishVerb));

		englishVerb = "Fruit Punch";
		System.out.println(toolbar.translateToGoat(englishVerb));

		englishVerb = "The quick brown fox jumps over the lazy dog";
		System.out.println(toolbar.translateToGoat(englishVerb));

		String goatEnglish = "BAAAA";
		String englishEnglish = toolbar.translateToEnglish(goatEnglish);
		System.out.println(englishEnglish);

		goatEnglish = "RBrwarbr";
		englishEnglish = toolbar.translateToEnglish(goatEnglish);
		System.out.println(englishEnglish);

		englishEnglish = toolbar.translateToEnglish(goatEnglish);
		System.out.println(englishEnglish);

		goatEnglish = "AW/wwawybyb/brrybyaawb/bayrbarryyarbyba";
		englishEnglish = toolbar.translateToEnglish(goatEnglish);
		System.out.println(englishEnglish);

		String englishText = "This will revolutionize the LLGHS community!";
		String goatText = "RWwbawrb/wwawybyb/rrbayrarybryrwawarbyawywba/rwwbba/YBYBWAWBRB/aaarayayrybyawrwyy";
		System.out.println(toolbar.translateToEnglish(goatText));
		System.out.println(toolbar.translateToGoat(englishText));
	}

}
