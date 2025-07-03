package threading;

import java.util.Random;

class RandomStringThread extends Thread {
    public static String randomString = "";

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 10; i++) {
            char randomChar = (char) (random.nextInt(94) + 33); ///// ASCII 33 ('!') to 126 ('~')
            sb.append(randomChar);
        }
        
        randomString = sb.toString();
        System.out.println("Generated Random String: " + randomString);
    }
}

class StringCleanerThread extends Thread {
    @Override
    public void run() {
        while (RandomStringThread.randomString.equals("")) {
            try {
                Thread.sleep(100);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        String cleanedString = RandomStringThread.randomString.replaceAll("[^a-zA-Z]", "");
        System.out.println("String after cleaning: " + cleanedString);
    }
}

public class RandomStringGenerator {

    public static void main(String[] args) {
        RandomStringThread randomStringThread = new RandomStringThread();
        StringCleanerThread stringCleanerThread = new StringCleanerThread();
        
        randomStringThread.start();  
        stringCleanerThread.start(); 
    }
}
