class FreshJuice {
    enum FreshJuiceSize{SMALL,MEDIUM,LARGE}
    FreshJuiceSize size;
}

public class FreshJuiceTest {
    public static void main(String args[]) {
        juice.size = FreshJuice.FreshJuiceSize.MENIUM;
        System.out.println("Size:" + juice.size);
    }
}
