import java.io.*;
import java.util.*;

public class ArenaUtils {

    public static List<Bojovnik> nactiBojovniky(String soubor) {
        List<Bojovnik> bojovnici = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(soubor));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                bojovnici.add(new Bojovnik(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor nenalezen");
        }
        return bojovnici;
    }

    public static void ulozBojovniky(List<Bojovnik> bojovnici, String soubor) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(soubor));

            for (Bojovnik bojovnik : bojovnici) {
                bw.write(bojovnik.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Soubor nenalezen");
        }
    }

    public static void ulozDoHistorie(String zaznam, String soubor) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(soubor, true));
            bw.write(zaznam);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException("Soubor nenalezen");
        }
    }

    public static void simulujZapas(List<Bojovnik> bojovnici) {
        int random1 = (int) (Math.random() * bojovnici.size());
        int random2 = (int) (Math.random() * bojovnici.size());
        int kdoPraveUtoci = 0; // 0 je první bojovník, 1 je druhý

        while (random1 == random2) {
            random2 = (int) (Math.random() * bojovnici.size());
        }

        Bojovnik bojovnik1 = bojovnici.get(random1);
        Bojovnik bojovnik2 = bojovnici.get(random2);

        while (bojovnik1.getZivoty() > 0 && bojovnik2.getZivoty() > 0) {
            if (kdoPraveUtoci == 0) {
                bojovnik1.utocNa(bojovnik2);
                kdoPraveUtoci++;
            } else if (kdoPraveUtoci == 1) {
                bojovnik2.utocNa(bojovnik1);
                kdoPraveUtoci--;
            }
        }

        Bojovnik vyherce = null;

        if (bojovnik1.jeNaZivu() && !bojovnik2.jeNaZivu()) {
            vyherce = bojovnik1;
        } else if (bojovnik2.jeNaZivu() && !bojovnik1.jeNaZivu()) {
            vyherce = bojovnik2;
        }

        String zaznam = null;
        if (vyherce != null) {
            zaznam = "Zápas mezi " + bojovnik1.getJmeno() + " a " + bojovnik2.getJmeno() + ": \n --> " + vyherce.getJmeno() + " vyhrál/a s " + vyherce.getZivoty() + " životy.";
        }
        ulozDoHistorie(zaznam + "\n", "historie.txt");
        System.out.println(zaznam);
    }
}