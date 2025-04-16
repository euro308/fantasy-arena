public class Bojovnik {
    private final String jmeno;
    private int zivoty; // 0 až 100
    private final int utok; // 0 až 30
    private final int obrana; // 0 až 20

    public Bojovnik(String jmeno, int zivoty, int utok, int obrana) {
        this.jmeno = jmeno;

        if (zivoty <= 100) {
            this.zivoty = zivoty;
        } else {
            this.zivoty = (int) (Math.random() * (100 - 1) + 1);
        }

        if (utok <= 30) {
            this.utok = utok;
        } else {
            this.utok = (int) (Math.random() * (30));
        }

        if (obrana <= 20) {
            this.obrana = obrana;
        } else {
            this.obrana = (int) (Math.random() * (20));
        }
    }

    public void utocNa(Bojovnik cil) {
        int zraneni = this.utok - cil.obrana;

        if (zraneni >= 0) {
            cil.zivoty -= zraneni;
        }

        if (cil.zivoty < 0) {
            cil.zivoty = 0;
        }
    }

    public boolean jeNaZivu() {
        return this.zivoty > 0;
    }

    @Override
    public String toString() {
        return "Jméno postavy: " + jmeno + "\n" + "HP postavy: " + zivoty + "\n" + "Útok postavy: " + utok + "\n" + "Obrana postavy: " + obrana + "\n";

    }

    public String toFileFormat() {
        return jmeno + "," + zivoty + "," + utok + "," + obrana;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getZivoty() {
        return zivoty;
    }
}