public class test {
    public static void main(String[] args) {
        System.out.println("Worked");

        // Diagram of mansion:
        System.out.println("-----------------------------------");
        System.out.println("|                |                |");
        System.out.println("|                |                |");
        System.out.println("|  Laboratory    | Frankenstein's |");
        System.out.println("|                |      Room      |");
        System.out.println("|                |                |");
        System.out.println("-----------------------------------");
        System.out.println("|                |                |");
        System.out.println("|                |                |");
        System.out.println("|    Kitchen     |   Living Room  |");
        System.out.println("|                |                |");
        System.out.println("|                |                |");
        System.out.println("-----------------------------------");

        String map = """
-----------------------------------
|                |                |
|                |                |
|  Laboratory    | Frankenstein's |
|                |      Room      |
|                |                |
-----------------------------------
|                |                |
|                |                |
|    Kitchen     |   Living Room  |
|                |    (has exit)  |
|                |                |
-----------------------------------
""";

System.out.println(map);


String worldMap = """

------------------
|                |
|                |
|     Mansion    |  
|                | 
|                |
------------------
       | |
       | |
       | |
       | |
       | |
       | |
       | |   
------------------------------------------------------
|                |                |                  |                  
|                |                |                  |-------------
| Beginning Woods|     Middle     |      Deep        |---------|  |
|                |      Woods     |      Woods       |         |  |
|                |                |                  |         |  |
------------------------------------------------------         |  |
                            | |                                |  |
                            | |                                |  |     |-----
                            | |                                |  |     |     -----
                            | |                                |  |     |          ----
                            | |                                |  |     |     -----
                            | |                                |  |     |-----
                            | |                                |  |     |
                    ------------------                    ------------------
                    |                |                    |                |
                    |                |                    |                |
                    |     Cottage    |                    |      Ship      |  
                    |                |                    |                |
                    |                |                    |                |
                    ------------------                     ----------------


        """;

String cottageMap = """
-----------------------------------
|                |                |
|                |                |
| Your Dwelling  |  Main Cottage  |
|  (has exit)    |                |
|                |                |
-----------------------------------

        """;

String shipMap = """
-----------------------------------
|                |                |
|                |                |
|      Deck      |      Cabin     |
|   (has exit)   |                |
|                |                |
----------------------------------- 
                 |                |
                 |                |
                 |     Room       |
                 |                |
                 |                |
                 ------------------

        """;
    }
}