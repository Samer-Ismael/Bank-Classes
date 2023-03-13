import java.util.ArrayList;
public class Statement {
    ArrayList<Object> statement;
    public Statement() {
        this.statement = new ArrayList<>();
    }
    public void addToList(String name, double amount) {
        statement.add(name);
        statement.add(amount);
    }
    public void printStatement() {
        int counter = 1;
        for (int i = 0; i < statement.size(); i += 2) {
            System.out.print(counter + "-" + statement.get(i) + " --> " + statement.get(i + 1));
            System.out.println();
            counter++;
        }
    }
}
