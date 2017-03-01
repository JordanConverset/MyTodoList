package jordan.converset.mytodolist;

import java.util.ArrayList;

/**
 * Created by jconverset on 01/03/2017.
 */
public final class Singleton {

    // L'utilisation du mot clé volatile, en Java version 5 et supérieure,
    // permet d'éviter le cas où "Singleton.instance" est non nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile Singleton instance = null;

    // D'autres attributs, classiques et non "static".
    private ArrayList<String> myArrayOfStrings = new ArrayList<>();
    private ArrayList<String> myArrayOfBooleans = new ArrayList<>();

    /**
     * Constructeur de l'objet.
     */
    private Singleton() {
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui-même.
        super();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static Singleton getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (Singleton.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }

    public ArrayList<String> getMyArrayOfStrings() {
        return myArrayOfStrings;
    }

    public void setMyArrayOfStrings(String text) {
        myArrayOfStrings.add(text);
    }

    public void clearMyArrayOfStrings() {
        myArrayOfStrings.clear();
    }

    public void initMyArrayOfStrings(ArrayList<String> myArray) {
        this.myArrayOfStrings = myArray;
    }

    public ArrayList<String> getMyArrayOfBooleans() {
        return myArrayOfBooleans;
    }

    public void setMyArrayOfBooleans(Boolean bool) {
        myArrayOfBooleans.add(bool.toString());
    }

    public void clearMyArrayOfBooleans() {
        myArrayOfBooleans.clear();
    }

    public void initMyArrayOfBooleans(ArrayList<String> myArray) {
        this.myArrayOfBooleans = myArray;
    }
}
