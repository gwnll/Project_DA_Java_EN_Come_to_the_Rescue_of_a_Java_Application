package com.hemebiotech.analytics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Puts all the symptoms in a list,
 * then counts them while getting rid of the duplicates
 * and puts them in a TreeMap where they are ordered alphabetically
 */
public class AnalyticsCounter {

	private final Map<Symptom, Integer> symptoms = new TreeMap<>();
	private final ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");

	/**
	 * Gets the list of all the symptoms unordered with duplicates
	 * for each item of the list, check if it's already registered in the Map
	 * if it is, increment the count of the symptom
	 * if it isn't, create the object and put it in the map with the value "1"
	 * the TreeMap will order them alphabetically
	 *
	 * @param symptomsList a list of all the symptoms
	 * @see symptoms
	 */
	public void count(List<String> symptomsList) {
		symptomsList.forEach(s -> {
			if (this.symptoms.containsKey(new Symptom (s))) {
				this.symptoms.put(new Symptom (s), this.symptoms.get(new Symptom (s)) + 1);
			}
			else {
				this.symptoms.put(new Symptom (s), 1);
			}
		});
	}

	/**
	 * Reads the symptoms
	 * puts them in a list, unordered with probable duplicates
	 *
	 * @see reader
	 * @see getSymptoms()
	 */
	public List<String> read() {
		return this.reader.getSymptoms();
	}

	/**
	 * Writes all the symptoms in a new file, ordered and without duplicates
	 *
	 * @see symptoms
	 */
	public void write() {
		String data = symptoms.entrySet().stream().map(e -> e.getKey().getName() + " " + e.getValue().toString()).reduce((a, b) -> a + "\n" + b).orElseThrow();
		try {
			Files.write(Paths.get("result.out"), data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		AnalyticsCounter counter = new AnalyticsCounter();
		counter.count(counter.read());
		counter.write();
	}
}