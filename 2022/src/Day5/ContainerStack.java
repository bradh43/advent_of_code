package src.Day5;

import java.util.Stack;

public class ContainerStack extends Stack<Character> {

    public Stack<Character> stack;

    public ContainerStack() {
        super();
        this.stack = new Stack<>();
    }

    public ContainerStack(String container) {
        super();
        this.stack = new Stack<>();
        this.stack.push(container.charAt(0));
    }

    public ContainerStack(char container) {
        super();
        this.stack = new Stack<>();
        this.stack.add(container);
    }

    public ContainerStack(Stack<Character> stack) {
        super();
        this.stack = stack;
    }

    public Character push(String container) {
        return this.stack.push(container.charAt(0));
    }

    public Character push(char container) {
        return this.stack.push(container);
    }

    public Character pop() {
        return this.stack.pop();
    }

    public Character peek() {
        return this.stack.peek();
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void reverse() {
        Stack<Character> reverseStack = new Stack<>();
        while (!this.stack.isEmpty()) {
            reverseStack.push(this.stack.pop());
        }
        this.stack = reverseStack;
    }

    @Override
    public ContainerStack clone() {
        return new ContainerStack((Stack<Character>) this.stack.clone());
    }

}
