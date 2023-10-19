import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NuvarandeKund extends Personer {

    List<NuvarandeKund> NuvarandeKundLista = new ArrayList<>();

    public NuvarandeKund(String personnummer, String förEfternamn, String datum) {
        super(personnummer, förEfternamn, datum);
    }

    public void skrivTillPTFil(Path utSkriftPT, List<NuvarandeKund> NuvarandeKundLista) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(utSkriftPT, StandardOpenOption.APPEND)) {
            if (!Files.exists(utSkriftPT))
                System.out.println(ingenFil);

            for (int i = 0; i < NuvarandeKundLista.size(); i++) {
                NuvarandeKund nKn = NuvarandeKundLista.get(i);
                bufferedWriter.write("" + nKn.getPersonnummer() + ", ");
                bufferedWriter.write(nKn.getFörEfternamn());
                bufferedWriter.write("\n"+ LocalDate.now() + "\n");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("FEL! Ange personnummret med 10 siffror utan bindesträck." +
                    "\n Namnet skrivs in på följande sätt: Förnamn Efternamn");
        } catch (FileNotFoundException e) {
            System.out.println(ingenFil);
        } catch (IOException e) {
            System.out.println("IOEXception!");
        } catch (Exception e) {
            System.out.println("Okänt fel! Försök igen!");
        }
    }
}

