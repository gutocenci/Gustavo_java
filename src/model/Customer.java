package model;

import java.util.Comparator;

/**
 *
 * @author Gustavo Montanini Cenci
 */
public class Customer implements Comparable<Customer> {

    private int id_customer;
    private String cpf_cnpj;
    private String nm_customer;
    private boolean is_active;
    private Double vl_total;

    public Customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNm_customer() {
        return nm_customer;
    }

    public void setNm_customer(String nm_customer) {
        this.nm_customer = nm_customer;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Double getVl_total() {
        return vl_total;
    }

    public void setVl_total(Double vl_total) {
        this.vl_total = vl_total;
    }

    @Override
    public int compareTo(Customer outroCustomer) {
        if (this.getVl_total() > outroCustomer.getVl_total()) {
            return -1;
        } else if (this.getVl_total() < outroCustomer.getVl_total()) {
            return 1;
        } else {
            return 0;
        }
    }
}
