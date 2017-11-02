package view;

import control.Conexao;
import control.DaoCustomer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.Customer;

/**
 *
 * @author Gustavo Montanini Cenci
 */
public class Aplic {

    public static void main(String[] args) {

        Conexao conexao;
        DaoCustomer daocustomer;

        conexao = new Conexao("BD1521018", "BD1521018");
        conexao.setDriver("oracle.jdbc.driver.OracleDriver");
        conexao.setConnectionString("jdbc:oracle:thin:@apolo:1521:xe");
        daocustomer = new DaoCustomer(conexao.conectar());

        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");

        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer = null;

        int idCustomer;
        String CpfCnpj;
        Double vlMedia = 0.0;
        int statusActive;
        String respContinue = "S";

        while (respContinue.equals("S") || respContinue.equals("s")) {
            while (true) {
                System.out.println("Digite o codigo do cliente a cadastrar");
                idCustomer = input.nextInt();

                if (daocustomer.consultarID(idCustomer)) {
                    System.out.println("Codigo ja em uso, digite novamente");
                } else {
                    break;
                }

                customer = new Customer(idCustomer);

                System.out.println("Digite os seguintes dados do Cliente");
                while (true) {
                    System.out.println("CPF ou CNPJ");
                    CpfCnpj = input.next();

                    if (daocustomer.consultarCpfCnpj(CpfCnpj)) {
                        System.out.println("CPF ou CNPPJ ja em uso, digite novamente");
                    } else {
                        break;
                    }
                }

                System.out.println("Nome");
                customer.setNm_customer(input.next());
                System.out.println("0: Para conta inativa ou 1: para conta ativa");
                statusActive = input.nextInt();
                customer.setIs_active(statusActive == 1);
                System.out.println("Valor total da conta");
                customer.setVl_total(input.nextDouble());

                daocustomer.inserir(customer);

                System.out.println("Digite S se deseja continuar a cadastro");
                respContinue = input.next();
            }
        }

        customers = daocustomer.getCustomers();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getVl_total() <= 560 || customers.get(i).getId_customer() < 1500 || customers.get(i).getId_customer() > 2700) {
                customers.remove(i);
            }
        }

        System.out.println("Clintes validos:");

        for (int i = 0; i < customers.size(); i++) {
            vlMedia += customers.get(i).getVl_total();
        }

        System.out.println("\nMédia dos clientes utilizadas: " + df.format(vlMedia / customers.size()));

        Collections.sort(customers);
        System.out.println(customers);

        /*for (int i = 0; i < customers.size(); i++) {
            System.out.println("\nID: " + customers.get(i).getId_customer()
                    + "\nNome: " + customers.get(i).getNm_customer()
                    + "\nValor Total: " + df.format(customers.get(i).getVl_total()));
         */
    }

}
