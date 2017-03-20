import java.util.ArrayList;
import java.util.Iterator;

public class SongGroup extends SongComponent {
    ArrayList songs = new ArrayList();

    String groupName;
    String groupDescription;

    public SongGroup(String name, String desc) {
        groupName = name;
        groupDescription = desc;
    }

    public String getGroupName() { return groupName; }
    public String getGroupDesc() { return groupDescription; }

    public void add(SongComponent c) {
        songs.add(c);
    }

    public void remove(SongComponent c) {
        songs.remove(c);
    }

    public SongComponent getComponent(int index) {
        return (SongComponent)songs.get(index);
    }

    public void displaySongInfo() {
        System.out.println("[+] " + getGroupName());
        System.out.println("  " + getGroupDesc());

        Iterator songIterator = songs.iterator();
        while (songIterator.hasNext()) {
            SongComponent info = (SongComponent) songIterator.next();
            info.displaySongInfo();
        }
    }
}