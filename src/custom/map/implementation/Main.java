package custom.map.implementation;

public class Main {
    public static void main(String[] args) {
        HarshithMap<String, Integer> map = new HarshithMap<>();
        map.put("we", 12);
        map.put("are", 53);
        map.put("not", 23);
        map.put("Hi", 875675);
        map.put("there", 657);
        map.put("test", 1234);
        map.put("test", 74353);
        map.put("test", 3532);
        map.put("harshith", 2314);
        map.put("bhanu", 62189);
        map.put("Pewdiepie", 782);
        map.put("Tseries", 3258);
        map.put("CinnamonToastKen", 83945);
        map.put("CinnamonToastKen", 829100);
        map.put("Pewdiepie", 4315);
        System.out.println(map.get("test"));
        System.out.println(map.get("not"));
        System.out.println(map.get("123123"));
        System.out.println(map.get("there"));
        System.out.println(map.get("Pewdiepie"));
        map.remove("Pewdiepie");
        System.out.println(map.get("Pewdiepie"));
        System.out.println(map.containsKey("Pewdiepie"));
        System.out.println(map.containsKey("harshith"));

    }
}