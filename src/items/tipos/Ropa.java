package items.tipos;

import items.Item;

public class Ropa extends Item {

    private String talla;
    private String sexo;

    public Ropa() {
    }

    public Ropa(int identificador, double precio, String nombre, String descripcion, int cantidad, String talla, String sexo) {
        super(identificador, precio, nombre, descripcion, cantidad);
        this.talla = talla;
        this.sexo = sexo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
