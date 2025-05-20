
package src.Repository;

import src.Entities.Disease;
import src.Utils.DiseaseType;

import java.util.ArrayList;

public class DiseaseRepo {
    private ArrayList<Disease> diseases;

    public DiseaseRepo() {
        this.diseases = new ArrayList<>();
        this.fetchDiseases();
    }

    private void fetchDiseases() {
        Disease disease1 = new Disease("Hypertension", DiseaseType.CHRONIC);
        Disease disease2 = new Disease("Flu", DiseaseType.INFECTIOUS);
        Disease disease3 = new Disease("Osteoarthritis", DiseaseType.DEGENERATIVE);

        diseases.add(disease1);
        diseases.add(disease2);
        diseases.add(disease3);
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }
}
    