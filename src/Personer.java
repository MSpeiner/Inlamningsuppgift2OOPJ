import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Personer {
    protected String personnummer;
    protected String förEfternamn;
    protected String datum;
    protected String datumInput;
    protected String ingenFil = "Filen hittades inte!";
    boolean kundFinns = false;
    protected String persUppgifter = "src/personer.txt";
    protected Path utSkriftPT = Paths.get("src/PTFil.txt");
    protected List<Personer> inlästaPersonerFil = new ArrayList<>();
    protected List<int[]> datumIntArray = new ArrayList<>();

    public Personer() {
    }

    public Personer(String personnummer, String förEfternamn, String datum) {
        this.personnummer = personnummer;
        this.förEfternamn = förEfternamn;
        this.datum = datum;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public String getFörEfternamn() {

        return förEfternamn;
    }

    public String getDatum() {
        return datum;
    }

    public String getDatumInput() {
        return datumInput;
    }

    public void läsInPersonerFrånFil(String persUppgifter) {
        try (Scanner scan = new Scanner(new FileReader(persUppgifter))) {
            String förstaMening;
            String andraMening;
            String persNr;
            String namn;
            String datum;

            while (scan.hasNext()) {
                förstaMening = scan.nextLine().trim();
                String[] förstameningArray = förstaMening.split(",");
                persNr = förstameningArray[0].trim();
                namn = förstameningArray[1].trim();

                andraMening = scan.nextLine().trim();
                datum = andraMening.trim();

                Personer person = new Personer(persNr, namn, datum);

                inlästaPersonerFil.add(person);
            }

        } catch (FileNotFoundException e) {
            System.out.println(ingenFil);
        }
    }

    public void läsInKundFrånInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ange personnummer 10 siffror. " +
                "\n!OBS! Använd inte bindesträck innan dem fyra sista:");

        String persnummer = scan.nextLine().trim();
        if (persnummer.isBlank() || persnummer == null) {
            System.out.println("Ett personnummer måste skrivas in");
            System.exit(0);
        }
        System.out.println("Ange för & efternamn:");
        String förOchEfternamn = scan.nextLine().trim();
        if (förOchEfternamn.isBlank() || förOchEfternamn == null) {
            System.out.println("Ett Namn måste skrivas in");
            System.exit(0);
        }
        LocalDate idag = LocalDate.now();
        DateTimeFormatter formatterar = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = idag.format(formatterar);

        String personInput[] = {persnummer, förOchEfternamn, dateString};

        this.personnummer = personInput[0].trim();
        this.förEfternamn = personInput[1].trim();
        this.datumInput = personInput[2].trim();
    }

    public void kontrolleraKund(List<Personer> inlästaPersonerFil, List<int[]> datumIntArray) {
        try {
            double persNrInskrivet = Double.parseDouble(this.personnummer);

            double persNrLista;
            int antalMånaderSedanBetalning = 0;

            for (int i = 0; i < inlästaPersonerFil.size(); i++) {
                String persNrS = inlästaPersonerFil.get(i).getPersonnummer();
                persNrLista = Double.parseDouble(persNrS);

                if (persNrInskrivet == persNrLista) {
                    LocalDate dagensDatum = LocalDate.now();
                    LocalDate specifiktDatum;

                    for (int k = 0; k < datumIntArray.size(); k++) {
                        int[] datumArrayLista = datumIntArray.get(i);
                        int targetDateÅr = datumArrayLista[0];
                        int targetDateMån = datumArrayLista[1];
                        int targetDateDag = datumArrayLista[2];
                        specifiktDatum = LocalDate.of(targetDateÅr, targetDateMån, targetDateDag);
                        Period period = Period.between(dagensDatum, specifiktDatum);
                        antalMånaderSedanBetalning = period.getYears() * 12 + period.getMonths();
                    }

                    if (antalMånaderSedanBetalning >= -12) {
                        NuvarandeKund nK = new NuvarandeKund(personnummer, förEfternamn, datum);
                        nK.NuvarandeKundLista.add(nK);
                        nK.skrivTillPTFil(utSkriftPT, nK.NuvarandeKundLista);
                        System.out.println("Detta är en aktiv kund");

                    } else if (antalMånaderSedanBetalning < -12) {
                        System.out.println("Detta är en föredetta kund som inte längre betalar!");

                    }
                    kundFinns = true;
                    break;
                }
            }
            if (!kundFinns) {
                System.out.println("Inmatad person har aldrig varit kund");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ditt personnummer måste skrivas med Siffror mellan 1-10, enbart 10 siffror (exl 4 sista).");
        }
    }

    public void skapaMånadArray(List<Personer> inlästaPersonerFil) {
        for (int i = 0; i < inlästaPersonerFil.size(); i++) {
            Personer personCheck = inlästaPersonerFil.get(i);
            String personCheckDatum = personCheck.getDatum();
            String värdenPCD[] = personCheckDatum.split("-");
            String årS = värdenPCD[0];
            String månS = värdenPCD[1];
            String dagS = värdenPCD[2];
            int år = Integer.parseInt(årS);
            int mån = Integer.parseInt(månS);
            int dag = Integer.parseInt(dagS);
            int årMånDag[] = {år, mån, dag};
            datumIntArray.add(årMånDag);
        }
    }
}