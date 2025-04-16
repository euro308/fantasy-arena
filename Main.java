import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File historie = new File("historie.txt");
        historie.delete();
        Scanner sc = new Scanner(System.in);
        List<Bojovnik> bojovnici = ArenaUtils.nactiBojovniky("bojovnici.txt");

        int volba = -1;
        while (volba != 5) {
            System.out.println("\n--- FANTASY ARÉNA ---");
            System.out.println("1 - Vypsat všechny bojovníky");
            System.out.println("2 - Přidat nového bojovníka");
            System.out.println("3 - Simulovat náhodný zápas");
            System.out.println("4 - Uložit bojovníky");
            System.out.println("5 - Konec");
            System.out.print("Zadej volbu: ");
            try {
                volba = sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                sc.nextLine();
                volba = -1;
            }

            switch (volba) {
                case 1:
                    for (Bojovnik b : bojovnici) {
                        System.out.println(b);
                    }
                    break;
                case 2:
                    System.out.print("Jméno: ");
                    String jmeno = sc.nextLine();
                    System.out.print("Životy: ");
                    int zivoty = sc.nextInt();
                    System.out.print("Útok: ");
                    int utok = sc.nextInt();
                    System.out.print("Obrana: ");
                    int obrana = sc.nextInt();
                    bojovnici.add(new Bojovnik(jmeno, zivoty, utok, obrana));
                    System.out.println("Bojovník přidán.");
                    break;
                case 3:
                    ArenaUtils.simulujZapas(bojovnici);
                    break;
                case 4:
                    ArenaUtils.ulozBojovniky(bojovnici, "bojovnici.txt");
                    System.out.println("Bojovníci uloženi.");
                    break;
                case 5:
                    System.out.println("Ukončuji program...");
                    break;
                default:
                    System.out.println("Neplatná volba.");
            }
        }
        sc.close();
    }
}