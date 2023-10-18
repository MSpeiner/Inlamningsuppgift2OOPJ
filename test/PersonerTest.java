import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonerTest {


    @Test
    void läsInKundFrånInput() {
        Personer pers = new Personer();
        String input = "9702174930\nMicke Speiner\n2023-10-17\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        pers.läsInKundFrånInput();
        String namnFörväntat = "Micke Speiner";
        String personnummerFörväntat = "9702174930";
        String datumFörväntat = "2023-10-17";
        String EjnamnFörväntat = "Martin Svensson";
        String EjpersonnummerFörväntat = "193020113030";
        String EjdatumFörväntat = "2013-11-20";

        assertEquals(personnummerFörväntat, pers.getPersonnummer());
        assertEquals(namnFörväntat, pers.getFörEfternamn());
        assertEquals(datumFörväntat, pers.getDatumInput());

        assertNotEquals(EjpersonnummerFörväntat, pers.getPersonnummer());
        assertNotEquals(EjnamnFörväntat, pers.getFörEfternamn());
        assertNotEquals(EjdatumFörväntat, pers.getDatumInput());
    }

    @Test
    void kontrolleraKund() {
        List<Personer> inlästaPersonerFil = new ArrayList<>();
        Personer person = new Personer("7805211234", "Nahema Ninsson", "2023-08-04");
        Personer person2 = new Personer("9702174930", "Michael Speiner", null);

        inlästaPersonerFil.add(person);
        inlästaPersonerFil.add(person2);

        List<int[]> datumIntArray = new ArrayList<>();
        int årMånDag[] = {2023, 8, 4};
        datumIntArray.add(årMånDag);

        person.kontrolleraKund(inlästaPersonerFil, datumIntArray);

        boolean kundFinnsActualPerson = person.kundFinns;
        boolean kundFinnsActualPerson2 = person2.kundFinns;

        boolean kundFinns = true;
        boolean kundFinnsEj = false;


        assert (kundFinnsActualPerson == kundFinns);
        assert (kundFinnsActualPerson != kundFinnsEj);

        assert (kundFinnsActualPerson2 == kundFinnsEj);
        assert (kundFinnsActualPerson2 != kundFinns);

    }

    @Test
    void testKontrolleraKund() {
        List<Personer> inlästaPersonerFil = new ArrayList<>();
        Personer person = new Personer("8906138493", "Ida Idylle", "2018-03-07");

        inlästaPersonerFil.add(person);

        List<int[]> datumIntArray = new ArrayList<>();
        int årMånDag[] = {2018, 3, 7};
        datumIntArray.add(årMånDag);


        person.kontrolleraKund(inlästaPersonerFil, datumIntArray);

        boolean kundFinnsActualPerson = person.kundFinns;
        boolean kundFinns = true;
        boolean kundFinnsEj = false;

        assert (kundFinnsActualPerson == kundFinns);
        assert (kundFinnsActualPerson != kundFinnsEj);

    }

    @Test
    void läsInPersonerFrånFil() {
        String kundUppgifter = "test/testFile.txt";

        Personer personen = new Personer();
        String inteNamn = "Nils Pettersson";
        String intePersNr = "3912243031";
        String inteDatum = "1921-01-01";

        personen.läsInPersonerFrånFil(kundUppgifter);

        assertEquals(3, personen.inlästaPersonerFil.size());

        Personer persCheck = personen.inlästaPersonerFil.get(0);
        assertEquals("7703021234", persCheck.getPersonnummer());
        assertEquals("Alhambra Aromes", persCheck.getFörEfternamn());
        assertEquals("2023-07-01", persCheck.getDatum());
        assertNotEquals(intePersNr, persCheck.getPersonnummer());
        assertNotEquals(inteNamn, persCheck.getFörEfternamn());
        assertNotEquals(inteDatum, persCheck.getDatum());

        Personer persCheck2 = personen.inlästaPersonerFil.get(1);
        assertEquals("8204021234", persCheck2.getPersonnummer());
        assertEquals("Bear Belle", persCheck2.getFörEfternamn());
        assertEquals("2019-12-02", persCheck2.getDatum());
        assertNotEquals(intePersNr, persCheck2.getPersonnummer());
        assertNotEquals(inteNamn, persCheck2.getFörEfternamn());
        assertNotEquals(inteDatum, persCheck2.getDatum());

        Personer persCheck3 = personen.inlästaPersonerFil.get(2);
        assertEquals("8512021234", persCheck3.getPersonnummer());
        assertEquals("Chamade Coriola", persCheck3.getFörEfternamn());
        assertEquals("2018-03-12", persCheck3.getDatum());
        assertNotEquals(intePersNr, persCheck3.getPersonnummer());
        assertNotEquals(inteNamn, persCheck3.getFörEfternamn());
        assertNotEquals(inteDatum, persCheck3.getDatum());
    }
}