import service.HelloWorldService;

public class HelloWorld {
    public static void main(String args[]) {
        HelloWorldService service = new HelloWorldService();
        System.out.println(service.printMessage("World"));
    }
}
