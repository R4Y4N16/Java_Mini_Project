
//The inventory will add the words key or pendant even if the user presses not accepted
//Add delay
//Fix the story structure somehow :(
package OOP.ec22431.A8;

import javax.swing.*;
import OOP.ec22431.A8.contributions.*;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUIVisitor_ec22431 implements Visitor{
    static JFrame frame = new JFrame("Room (ec22431)");
    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JButton option4;
    private JPanel panelMain;
    private JPanel Inventory;
    private JTextArea storyText;
    private JLabel inventoryN;
    private JLabel goldC;
    private JLabel itemC;
    private JLabel Title;

    private boolean lightsOn; //ON or OFF
    //Empty or Not
    private final boolean ghostFriendly; //Friendly or Grumpy
    private boolean isCold; //Chilly or Hot (Stuffy)
    //Calm or Chaotic
    private int purse;
    static final Item key = new Item("Key");
    static final Item pendant = new Item("Pendant");

    public Direction optionD;

    public static void main(String[] args) {
        frame.setContentPane(new GUIVisitor_ec22431().panelMain);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public GUIVisitor_ec22431() {
        lightsOn = false;
        ghostFriendly = false;
        isCold = true;
        purse = 5;
        storyText.setBackground(Color.black);
        storyText.setForeground(Color.white);
        storyText.setLineWrap(true);
        storyText.setWrapStyleWord(true);
        storyText.setEditable(false);


        Visitor v = new IOVisitor(System.out,System.in);
        visit(v,Direction.FROM_SOUTH);

    }

    public Direction visit(Visitor visitor, Direction direction) {
        AtomicBoolean tester = new AtomicBoolean(false);
        if (direction == Direction.FROM_NORTH) {
            storyText.setText("You entered the room from the north.");
        } else if (direction == Direction.FROM_EAST) {
            storyText.setText("You entered the room from the east.");
        } else if (direction == Direction.FROM_SOUTH) {
            storyText.setText("You entered the room from the south.");
        } else if (direction == Direction.FROM_WEST) {
            storyText.setText("You entered the room from the west.");
        }
        if (!lightsOn) {
            tell("\nIt's very hard to see anything inside of the room. Using the moonlight, through the broken window you can make out a few things inside the room: A yellowing light switch, A heavy looking, wooden chest covered in a thick layer of dust");
        } else {
            tell("\nThe room is basked in an unnatural, unnerving light, occasionally flickering. The window is shattered outwards as if someone jumped out of it. You look around the room and see A yellowing light switch, A heavy looking, wooden chest covered in a thick layer of dust");
        }
        if (isCold) {
            tell("\nAs you take a deep breath, you feel a chill run down your spine. The air in the room feels crisp and icy, as though there is a sudden drop in temperature.");
        } else {
            tell("\nAs you take a deep breath, an intense wave of heat washes over you, and you immediately start to sweat." +
                    "It's almost as if the heat is coming from within the walls.");
        }

        option1.setText("Flick the Light Switch");
        option2.setText("Open the Chest");
        option3.setText("Look Around the Room");
        option4.setVisible(false);

        option1.addActionListener(actionEvent -> {
            if (lightsOn) {
                lightsOn = false;
                tell("\nNow that the light is off you can see a blurry, glowing shape, it's back is towards you so you cannot make out its face, if it even has one. You decide to walk towards it, as if suddenly possessed to reach out and touch this glowing entity. However you trip and collapse over an old table, balancing on three legs. The table breaks under you,and the trance is broken. As you desperately try to scramble away, your heart racing with fear, the creature suddenly turns around, fixating its gaze upon you,and without warning starts to float towards you.");
                if (!ghostFriendly) {
                    isCold = false;
                    tell("\nThe air grows heavy and stale and suddenly the temperature in the room rises rapidly. Your clothes begin sticking to you as the heat comes unbearable. The ghost begins to screech at you, it's voice shattering the stillness of the night. Its voice is deafening, seemly coming from all around you. \"YOU KILLED HER!\", the ghost shrieks with unmistakable clarity. You attempt to defend yourself but your words catch in your throat. With the ghost's accusing scream still ringing in your ears, you panic and look for an escape. You search for a way out, but the door is locked, and there's no way to escape. As you desperately search for a way out");
                    if (visitor.hasIdenticalItem(Room_ec22923.crystal)) {
                        tell("\nThe crystal glows brightly in your hand, guiding you to an old chest in the corner of the room and you begin searching through it with trembling hands, hoping to find the key to unlock the door.");
                    }
                    TrunkContent(visitor);

                } else {
                    tell("\nAs the ghostly figure floats towards you, your heart races with fear. You're still curious about the entity despite the sudden movement that caused you to fall over the table. As it draws closer, you can start to make out its features, you can now see the kind and gentle expressions on her pale face. You realise she is not just any ghost but a mother who must of once lived in this house, she must have died inside this house and now as a ghost is free to watch over the house.");
                    if (visitor.hasIdenticalItem(Room_ec22923.crystal)) {
                        tell("\nShe points to your crystal and then to the chest in the corner of the room. You take the hint and walk towards the chest");
                    }
                    TrunkContent(visitor);

                }
            } else {
                lightsOn = true;
                tell("\nYou flip the light switch and the room is suddenly bathed in a bright, unnatural and unnerving light. You can see everything clearly now. You look around and see a painting and the chest");
                option1.setText("Go to the Painting");
                option2.setText("Open the Chest");
                option3.setVisible(false);
                option4.setVisible(false);
                option1.addActionListener(actionEvent1 -> {
                    tell("\nYou walk towards the painting, and as you get closer, you can see that it's a portrait of a family. The painting is old, and the colors have faded, but the family's faces are still visible. You see a blacked-out section where the father should be in the portrait, as if he was purposely erased. The mother and three children in the painting seem to be looking off to the side, as though they're ignoring the empty space.");
                    tell("\nSince you can't decipher anything else out of the portrait, you turn and walk to the chest.");
                    TrunkContent(visitor);
                });
                option2.addActionListener(actionEvent12 -> TrunkContent(visitor));
            }
            tester.set(true);
        });

        option2.addActionListener(actionEvent -> {
            TrunkContent(visitor);
            tester.set(true);
        });

        option3.addActionListener(actionEvent -> {
            tell("\nYou look around the room and see a painting and the chest");
            option1.setText("Go to the Painting");
            option2.setText("Open the Chest");
            option1.addActionListener(actionEvent16 -> {
                tell("\nYou walk towards the painting, and as you get closer, you can see that it's a portrait of a family. The painting is old, and the colors have faded, but the family's faces are still visible. You see a blacked-out section where the father should be in the portrait, as if he was purposely erased. The mother and three children in the painting seem to be looking off to the side, as though they're ignoring the empty space.");
                tell("\nSince you can't decipher anything else out of the portrait, you turn and walk to the chest.");
                TrunkContent(visitor);
            });
            option2.addActionListener(actionEvent15 -> TrunkContent(visitor));
            tester.set(true);
        });
        if (tester.get()) {
            tell("\nYou use the key and leave the room, then you enter a tiny hallway, you can either go left, right or forwards");
            option3.setVisible(true);
            option4.setVisible(true);
            option1.setText("Go Left");
            option2.setText("Go Right");
            option3.setText("Go Forwards");
            option4.setText("Go Backwards");
            option1.addActionListener(actionEvent -> optionD = Direction.TO_EAST);
            option2.addActionListener(actionEvent -> optionD = Direction.TO_WEST);
            option3.addActionListener(actionEvent -> optionD = Direction.TO_NORTH);
            option4.addActionListener(actionEvent -> optionD = Direction.TO_SOUTH);
        }
        return optionD;
    }
    public void TrunkContent(Visitor visitor) {
            Random rand = new Random();
            if (rand.nextInt(2) == 1) {
                storyText.append("\nYou open the chest and find gold ");
                if(purse<10) {
                    int temp = 10-purse;
                    purse = rand.nextInt(temp)+1;
                    visitor.giveGold(pos);
                    storyText.append("\nYou take " + purse + "gold bars");
                    goldC.setText("Gold: "+purse);
                    storyText.append("\nYou also find a key and a pendant inside.");
                    option1.setText("Take the Pendant");
                    option2.setText("Take the key");
                    option3.setVisible(false);
                    option4.setVisible(false);

                    if (purse >= 10) {
                        storyText.append("\nYou have too much gold, in order to take an item you drop a gold bar");
                        visitor.takeGold(1);
                        purse--;
                        goldC.setText("Gold: "+purse);

                    }
                    option1.addActionListener(actionEvent ->{
                        visitor.giveItem(pendant);
                        if(visitor.hasEqualItem(key)){
                            itemC.setText("Items: Key, Pendant");
                        }
                        else{itemC.setText("Items: Pendant");}
                    });
                    option2.addActionListener(actionEvent -> {
                        visitor.giveItem(key);
                        if(visitor.hasEqualItem(pendant)){
                            itemC.setText("Items: Pendant, Key");
                        }
                        else{itemC.setText("Items: Key");}
                    });
                }
                else {
                    storyText.append("\nYou cannot take anymore gold");
                    storyText.append("\nYou also find a key and a pendant inside.");
                    if (purse >= 10) {
                        storyText.append("\nYou have too much gold, in order to take an item you drop a gold bar");
                        visitor.takeGold(1);
                        purse--;
                        goldC.setText("Gold: "+purse);

                    }
                    option1.addActionListener(actionEvent ->{
                        visitor.giveItem(pendant);
                        if(visitor.hasEqualItem(key)){
                            itemC.setText("Items: Key, Pendant");
                        }
                        else{itemC.setText("Items: Pendant");}
                    });
                    option2.addActionListener(actionEvent -> {
                        visitor.giveItem(key);
                        if(visitor.hasEqualItem(pendant)){
                            itemC.setText("Items: Pendant, Key");
                        }
                        else{itemC.setText("Items: Key");}
                    });
                }
            } else {
                storyText.append("\nYou open the chest and find a key and a pendant inside.");
                if (purse >= 10) {
                    storyText.append("\nYou have too much gold, in order to take an item you drop a gold bar");
                    visitor.takeGold(1);
                    purse--;
                    goldC.setText("Gold: "+purse);

                }
                option1.addActionListener(actionEvent ->{
                    visitor.giveItem(pendant);
                    if(visitor.hasEqualItem(key)){
                        itemC.setText("Items: Key, Pendant");
                    }
                    else{itemC.setText("Items: Pendant");}
                });
                option2.addActionListener(actionEvent -> {
                    visitor.giveItem(key);
                    if(visitor.hasEqualItem(pendant)){
                        itemC.setText("Items: Pendant, Key");
                    }
                    else{itemC.setText("Items: Key");}
                });
            }
    }

    @Override
    public void tell(String s) {
        storyText.append(" "+s+"\n");
    }
    private int pos;
    public char getChoice(String d, char[] a) {
        tell("\n"+d);
        option1.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(false);
        option4.setVisible(false);

        option1.addActionListener(actionEvent -> pos = 0);

        option2.addActionListener(actionEvent -> pos = 1);
        return a[pos];
    }


    private Item[] items = new Item[1000];
    private int next;
    //Give user option of accept an item and add to inventory
    private static final char[] yOrN = { 'y', 'n'};
    @Override
    public boolean giveItem(Item x) {
        tell("\nYou have: ");
        for (int i=0;i<next;i++) tell(items[i] + ", ");
        tell("\nYou are being offered: "+x.name);
        if (next >= items.length) {
            tell("\nBut you have no space and must decline.");
            return false;
        }
        if (getChoice("Do you accept (y/n)?", yOrN) == 'y') {
            items[next] = x;
            next++;
            return true;
        } else return false;
    }

    @Override
    public boolean hasIdenticalItem(Item x) {
        for (int i=0; i<next;i++)
            if (x == items[i])
                return true;
        return false;
    }

    @Override
    public boolean hasEqualItem(Item x) {
        for (int i=0; i<next;i++)
            if (x.equals(items[i]))
                return true;
        return false;
    }

    @Override
    public void giveGold(int n) {
        tell("You are given "+n+" gold pieces.");
        purse += n;
    }

    @Override
    public int takeGold(int n) {
        if (n<0) {
            tell("A scammer tried to put you in debt to the tune off "+(-n)+"pieces of gold,");
            tell("but you didn't fall for it and returned the 'loan'.");
            return 0;
        }

        int t = Math.min(n, purse);

        tell(t+" pieces of gold are taken from you.");
        purse -= t;
        tell("You now have "+purse+" pieces of gold.");

        return t;
    }
}