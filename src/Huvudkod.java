public class Huvudkod {
    Huvudkod() {
        Personer pers = new Personer();
        pers.läsInPersonerFrånFil(pers.persUppgifter);
        pers.läsInKundFrånInput();
        pers.kontrolleraKund(pers.inlästaPersonerFil, pers.datumIntArray);
    }

    public static void main(String[] args) {
        Huvudkod hk = new Huvudkod();
    }
}