/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println("shaked to check::::::::::::::::");
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// prepare the text
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		// remove spaces 
		String noSpace1 = "";
		String noSpace2 = "";

		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != ' ') {
				noSpace1 += str1.charAt(i);
			}
		}

		for (int i = 0; i < str2.length(); i++) {
			if (str2.charAt(i) != ' ') {
				noSpace2 += str2.charAt(i);
			}
		}

		str1 = noSpace1;
		str2 = noSpace2;

		if (str1.length() != str2.length()) {
			return false;
		}

		String chars = str2;

		for (int i = 0; i < str1.length(); i++) {
			char c = str1.charAt(i);
			boolean found = false;
			for (int j = 0; j < chars.length(); j++) {
				if (chars.charAt(j) == c) { // match letters
					chars = chars.substring(0, j) + chars.substring(j + 1);
					// delete char 'J' after i match
					found = true;
					break;
				} // end if
			} // end inside loop

			if (!found)
				return false; // if once in the nest loop we didn't find a match
		} // end outside loop

		return true;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
    String answer = "";
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);

        // if char is a Capital letter so add 32 : uppercase -> lowercase
        if (c >= 'A' && c <= 'Z') {
            c = (char) (c + 32);
        }

        // if lowercase letter or space so keep it
        if ((c >= 'a' && c <= 'z') || c == ' ') {
            answer = answer + c;
        }
        // everything which is not letter or space we skip
    }
    return answer;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		String result = "";

		while (str.length() > 0) {
			int r = (int) (Math.random() * str.length()); // random index in str
			char c = str.charAt(r); // the random letter with the r index
			result = result + c; // we add the random letter to a new string 
			str = str.substring(0, r) + str.substring(r + 1); // delete the random letter from str
		}

		return result;
	}
}
