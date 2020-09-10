package com.hemebiotech.analytics;

/**
 * Each symptom is an object
 * which makes it possible to compare them with their hashCode
 * and therefore eliminate the duplicates
 */
public class Symptom implements Comparable<Symptom> {
    private final String name;

    public Symptom(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Symptom) && !(obj instanceof String)) {
            return false;
        }
        if (obj instanceof Symptom) {
            return this.name.equals(((Symptom) obj).getName());
        }
        else {
            return this.name.equals(obj);
        }
    }

    @Override
    public int compareTo(Symptom o) {
        return this.name.compareTo(o.getName());
    }
}
