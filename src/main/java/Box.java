public class Box {

    private char token;
    private int position;
    private int column;
    private int row;

    public Box(char token, int position) {
        this.token = token;
        this.position = position;
    }

    public char getToken() {
        return token;
    }

    public int getPosition() {
        return position;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
