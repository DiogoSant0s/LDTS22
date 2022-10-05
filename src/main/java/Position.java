public class Position {
    private Hero hero= new Hero(get_x(), get_y());
    private int x_;
    private int y_;
    public int get_x(){
        return x_;
    }
    public int get_y(){
        return y_;
    }

    public void setPosition(Position p){
        this.x_ = p.x_;
        this.y_ = p.y_;
    }
    public Position(int x, int y){

    }
}
