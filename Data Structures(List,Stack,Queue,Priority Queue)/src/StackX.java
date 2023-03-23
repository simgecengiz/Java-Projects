import java.util.ArrayList;

public class StackX {
    private  final ArrayList<MilliPark> stack;
    public StackX() {
        this.stack = new ArrayList<>();
    }

    public void push(MilliPark j){
        stack.add(j);
    }

    public MilliPark pop(){
        if(!stack.isEmpty()){
            MilliPark top = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return top;
        }
        else{
            return null;
        }
    }

    public MilliPark peek(){
        if (stack.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty(){
        return (stack.isEmpty());
    }
}



