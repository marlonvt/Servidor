package servidor;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Servidor {

    protected static class Thread1 implements Runnable {

        Thread t;
        Socket c;

        public Thread1(Socket cliente) {
            c = cliente;
            t = new Thread(this, "T1");
            t.start();
        }

        public void run() {

            try {
                Scanner leitura = new Scanner(c.getInputStream());
                while (leitura.hasNextLine()) {
                    System.out.println(leitura.nextLine());
                }
                leitura.close();
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(27000);
        System.out.println("Porta 27000 aguardando...");

        Socket cliente = servidor.accept();
        System.out.println(cliente.getInetAddress().toString());

        Thread1 t1 = new Thread1(cliente);

        PrintStream conexao = new PrintStream(cliente.getOutputStream());

        /*
         conexao.println("UHUUUUUU");
         Thread.sleep(1000);
         conexao.println("AHAA");
         Thread.sleep(1000);
         conexao.println("TCHAU");
         */
        Scanner ler = new Scanner(System.in);
        while (ler.hasNextLine()) {
            conexao.println(ler.nextLine());
        }

//        Scanner leitura = new Scanner(cliente.getInputStream());
//        while (leitura.hasNextLine()) {
//            System.out.println(leitura.nextLine());
//        }
//        leitura.close();
        conexao.close();
        cliente.close();
        servidor.close();

    }

}
