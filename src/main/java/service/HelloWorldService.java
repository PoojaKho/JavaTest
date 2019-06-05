package service;

public class HelloWorldService {
    public String printMessage(String name){
        return String.format("Hello %s!",name);
    }
}
