package src.Entities;

import src.Utils.DiseaseType;

public class Disease {
    private String name;
    private DiseaseType type;

    public Disease(String name, DiseaseType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
