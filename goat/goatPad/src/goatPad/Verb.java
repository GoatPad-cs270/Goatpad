package goatPad;

public class Verb {
	String englishText;
	boolean isVerb;

	public Verb(String text) {
		this.englishText = text;
		isVerb = false;
	}

	public String translateToGoat() {
		checkIsVerb();
		if (isVerb) {
		}
		return "BA BA";
	}

	private boolean checkIsVerb() {
		String temp = englishText;
		temp = temp.toLowerCase();

		containsPronoun(temp);
		return true;

	}

	private boolean containsPronoun(String str) {
		if (str.contains("i") || str.contains("you") || str.contains("he") || str.contains("she") || str.contains("we")
				|| str.contains("they")) {
			isVerb = true;
		}
		return true;
	}
}
