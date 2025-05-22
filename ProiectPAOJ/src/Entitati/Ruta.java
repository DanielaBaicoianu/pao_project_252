package Entitati;

import Exceptii.ExceptieGariPermise;
import Utile.UtileRuta;

import java.util.List;

public class Ruta {
    private final int idRuta;
    private List<Gara> destinatii;
    private int oraPlecare;
    private int oraSosire;
    private Tren tren;

    public Ruta(List<Gara> destinatii, int oraPlecare, int oraSosire) {
        this.idRuta = UtileRuta.idCurent;
        UtileRuta.idCurent++;
        this.destinatii = destinatii;
        this.oraPlecare = oraPlecare;
        this.oraSosire = oraSosire;
    }

    public List<Gara> getDestinatii() {
        return destinatii;
    }

    public int getOraPlecare() {
        return oraPlecare;
    }

    public int getOraSosire() {
        return oraSosire;
    }

    public Tren getTren() {
        return tren;
    }

    public void setDestinatii(List<Gara> destinatii) {
        this.destinatii = destinatii;
    }

    public void setOraPlecare(int oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public void setOraSosire(int oraSosire) {
        this.oraSosire = oraSosire;
    }

    public void setTren(Tren tren) {
        List<Gara> gariPermise = tren.getOperator().getGariPermise();
        for(Gara g1 : this.getDestinatii()){
            boolean gasit = false;
            for(Gara g2 : gariPermise){
                if(g1.equals(g2)){
                    gasit = true;
                    break;
                }
            }
            if(!gasit){
                throw new ExceptieGariPermise("Operatorul " + tren.getOperator().getNume() + " nu poate opera trenuri in gara " + g1.getNume());
            }
        }
        this.tren = tren;
        tren.setEsteFolosit(true);
    }

    @Override
    public String toString() {
        String ret = "";
        for (Gara g : destinatii) {
            ret += g.getNume() + "--->";
        }
        ret += "\n" + this.oraPlecare + "-" + this.oraSosire + "\n";
        return ret;
    }
}
