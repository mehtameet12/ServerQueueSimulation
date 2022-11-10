public class Customer {
    int stage;
    Customer(){
        stage = 0;
    }
    boolean incStage(){
        stage++;
        return true;
    }
}
