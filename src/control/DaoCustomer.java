package control;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Gustavo Montanini Cenci
 */
public class DaoCustomer {

    private Connection connection;

    public DaoCustomer(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Customer customer) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tb_customer_account(Id_customer, cpf_cnpj, Nm_customer, active, vl_total) "
                    + "VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, customer.getId_customer());
            ps.setString(2, customer.getCpf_cnpj());
            ps.setString(3, customer.getNm_customer());
            if (customer.isIs_active()) {
                ps.setInt(4, 1);
            } else {
                ps.setInt(4, 0);
            }
            ps.setDouble(5, customer.getVl_total());

        } catch (SQLException ex) {
            System.out.println(ex.toString() + " localizado no daoCustomer");
        }
    }

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer;
        boolean active;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from tb_customer_account");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getInt("active") == 1) {
                    active = true;
                } else {
                    active = false;
                }

                customer = new Customer(rs.getInt("id_customer"));
                customer.setCpf_cnpj(rs.getString("cpf_cnpj"));
                customer.setNm_customer(rs.getString("nm_customer"));
                customer.setIs_active(active);
                customer.setVl_total(rs.getDouble("vl_total"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString() + " localizado no daoCustomer");
        }

        return customers;
    }

    public boolean consultarCpfCnpj(String CpfCnpj) {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("SELECT * from tb_customer_account where cpf_cnpj = ?");
            ps.setString(1, CpfCnpj);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString() + " localizado no daoCustomer");
        }

        return false;
    }

    public boolean consultarID(int id) {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("SELECT * from tb_customer_account where Id_customer = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString() + " localizado no daoCustomer");
        }

        return false;
    }
}
