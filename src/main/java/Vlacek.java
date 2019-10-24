import java.util.LinkedList;
import java.util.List;

public class Vlacek {

    private Vagonek lokomotiva = new Vagonek(VagonekType.LOKOMOTIVA);
    private Vagonek posledni = new Vagonek(VagonekType.POSTOVNI);

    private int delka = 2;

    public Vlacek(){
        lokomotiva.setNasledujici(posledni);
        lokomotiva.setUmisteni(1);
        posledni.setPredchozi(lokomotiva);
        posledni.setUmisteni(2);
    }

    /**
     * Přidávejte vagonky do vlaku
     * Podmínka je že vagonek první třídy musí být vždy řazen za předchozí vagonek toho typu, pokud žádný takový není je řazen rovnou za lokomotivu
     * vagonek 2 třídy musí být vždy řazen až za poslední vagonek třídy první
     * Poštovní vagonek musí být vždy poslední vagonek lokomotivy
     * Při vkládání vagonku nezapomeňte vagonku přiřadit danou pozici ve vlaku
     * !!!!!!! POZOR !!!!!! pokud přidáváte vagonek jinak než na konec vlaku musíte všem následujícím vagonkům zvýšit jejich umístění - doporučuji si pro tento účel vytvořit privátní metodu
     * @param type
     */
    public void pridatVagonek(VagonekType type) {
        Vagonek newVagonek = new Vagonek(type);

        switch (type)
        {
            case PRVNI_TRIDA:

                newVagonek.setPredchozi(lokomotiva);
                newVagonek.setNasledujici(lokomotiva.getNasledujici());

                lokomotiva.getNasledujici().setPredchozi(newVagonek);
                lokomotiva.setNasledujici(newVagonek);

                setUmisteni();
                break;

            case DRUHA_TRIDA:

                newVagonek.setNasledujici(posledni);
                newVagonek.setPredchozi(posledni.getPredchozi());

                posledni.getPredchozi().setNasledujici(newVagonek);
                posledni.setPredchozi(newVagonek);

                setUmisteni();
                break;

        }
        delka ++;
    }

    public Vagonek getVagonekByIndex(int index) {
        int i = 1;
        Vagonek atIndex = lokomotiva;
        while(i < index) {
            atIndex = atIndex.getNasledujici();
            i++;
        }
        return atIndex;
    }


    public void setUmisteni() {
            Vagonek zaLokomotivou = lokomotiva.getNasledujici();
            for (int i = 0; i < delka; i++) {
                zaLokomotivou.setUmisteni(zaLokomotivou.getPredchozi().getUmisteni() + 1);
                zaLokomotivou = zaLokomotivou.getNasledujici();

            }
    }

    public Vagonek getLastVagonekByType(VagonekType type) {

         return null;
    }

    public Vagonek najdiPosledniPrvniTridy(){

        int i = 2;

        while (getVagonekByIndex(i).getType() == VagonekType.PRVNI_TRIDA) {

            i++;
        }

        return getVagonekByIndex(i);

    }


    /**
     * Touto metodou si můžete vrátit poslední vagonek daného typu
     * Pokud tedy budu chtít vrátit vagonek typu lokomotiva dostanu hned první vagonek
     * @param type
     * @return
     */



    /**
     * Tato funkce přidá jídelní vagonek za poslední vagonek první třídy, pokud jídelní vagonek za vagonkem první třídy již existuje
     * tak se další vagonek přidá nejblíže středu vagonků druhé třídy
     * tzn: pokud budu mít č osobních vagonků tak zařadím jídelní vagonek za 2 osobní vagónek
     * pokud budu mít osobních vagonků 5 zařadím jídelní vagonek za 3 osobní vagonek
     */
    public void pridatJidelniVagonek() {
        Vagonek jidelni = new Vagonek(VagonekType.JIDELNI);

        int i = 2;

        while (getVagonekByIndex(i).getType() != VagonekType.PRVNI_TRIDA){
            System.out.println("1");
            i++;
        }

        Vagonek pred = getVagonekByIndex(i);
        pred.getPredchozi().setNasledujici(jidelni);
        jidelni.setNasledujici(pred);
        jidelni.setPredchozi(pred.getPredchozi());
        jidelni.setNasledujici(pred);
        setUmisteni();
        delka ++;



    }

    /**
     * Funkce vrátí počet vagonků daného typu
     * Dobré využití se najde v metodě @method(addJidelniVagonek)
     * @param type
     * @return
     */
    public int getDelkaByType(VagonekType type) {
        int delkaTypu = 0;
        switch (type) {
            case PRVNI_TRIDA:
                for (int i = 0; i < delka; i++) {
                    if (getVagonekByIndex(i).getType() != VagonekType.PRVNI_TRIDA) {
                        delkaTypu++;
                    }
                }
                break;
            case DRUHA_TRIDA:
        }
        return delkaTypu;
    }

    /**
     * Hledejte jidelni vagonky
     * @return
     */
    public List<Vagonek> getJidelniVozy() {
        List<Vagonek> jidelniVozy = new LinkedList<>();

        return jidelniVozy;
    }

    /**
     * Odebere poslední vagonek daného typu
     * !!!! POZOR !!!!! pokud odebíráme z prostředku vlaku musíme zbývající vagonky projít a snížit jejich umístění ve vlaku
     * @param type
     */
    public void odebratPosledniVagonekByType(VagonekType type) {

    }

    public int getDelka() {
        return delka;
    }
}
