package OOP.ec22431.A8.contributions;

abstract class Room implements Visitable {
    public abstract char getChoice(String d, char[] a);

    public abstract boolean giveItem(Item x);
}