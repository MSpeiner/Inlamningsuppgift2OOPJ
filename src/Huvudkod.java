public class Huvudkod {
    Huvudkod() {
        Personer pers = new Personer();
        NuvarandeKund nK = new NuvarandeKund();
        pers.läsInPersonerFrånFil(pers.persUppgifter);
        pers.läsInKundFrånInput();
        pers.skapaMånadArray(pers.inlästaPersonerFil);
        pers.kontrolleraKund(pers.inlästaPersonerFil, pers.datumIntArray);
    }

    public static void main(String[] args) {
        Huvudkod hk = new Huvudkod();
    }
}