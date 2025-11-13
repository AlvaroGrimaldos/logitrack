package com.logitrack.logitrack.entities;

public enum Rol {
    ADMIN("admin"),
    EMPLEADO("empleado");
    
    private final String valor;
    
    Rol(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public static Rol fromString(String text) {
        for (Rol rol : Rol.values()) {
            if (rol.valor.equalsIgnoreCase(text)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no válido: " + text);
    }
}