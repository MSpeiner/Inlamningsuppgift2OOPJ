import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NuvarandeKundTest {

    @Test
    void skrivTillPTFil() {
        NuvarandeKund nuvarandeKund1 = new NuvarandeKund("7703021234","Alhambra Aromes","2023-07-01");
        List<NuvarandeKund> NuvarandeKundLista = new ArrayList<>();
        NuvarandeKundLista.add(nuvarandeKund1);

        Path utSkriftPTtest = Paths.get("test/testOutputFile.txt");
        String utSkriftPTtestString = "test/testOutputFile.txt";

        nuvarandeKund1.skrivTillPTFil(utSkriftPTtest, NuvarandeKundLista);

        Personer person = new Personer();

        String inteNamn = "Nils Pettersson";
        String intePersNr = "3912243031";
        String inteDatum = "1921-01-01";
        String Namn = "Alhambra Aromes";
        String PersNr = "7703021234";
        LocalDate idag = LocalDate.now();
        String Datum = idag.toString();

        person.läsInPersonerFrånFil(utSkriftPTtestString);

        Personer persCheck = person.inlästaPersonerFil.get(0);
        assertEquals(PersNr, persCheck.getPersonnummer());
        assertEquals(Namn, persCheck.getFörEfternamn());
        assertEquals(Datum, persCheck.getDatum());
        assertNotEquals(intePersNr, persCheck.getPersonnummer());
        assertNotEquals(inteNamn, persCheck.getFörEfternamn());
        assertNotEquals(inteDatum, persCheck.getDatum());
        }
    }
