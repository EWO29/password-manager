import java.util.ArrayList;

public class PasswordStore {
    private ArrayList<PasswordEntry> allEntries;

    public PasswordStore() {
        this.allEntries = new ArrayList<>();
    }

    public void addEntry(PasswordEntry newEntry) {
        if (newEntry == null){
            throw new NullPointerException("Нельзя передавать null");
        }else if (allEntries.contains(newEntry)) {
            throw new DuplicateEntryException("Duplicate entry");
        } else{
            allEntries.add(newEntry);
        }

    }

    public void updatePassword(char[] newPassword, int index){
        if ( index > -1 && index < allEntries.size()){
            allEntries.get(index).setPassword(newPassword);
        } else{
            throw new IllegalArgumentException("Неправильный индекс");
        }
    }

    public ArrayList<PasswordEntry> getAll() {
        return new ArrayList<>(allEntries);

    }

    public int size(){

        return allEntries.size();

    }

    public PasswordEntry get(int index){
        if (index < allEntries.size() && index > -1){

            return allEntries.get(index);

        } else{
            return null;
        }
    }






}
