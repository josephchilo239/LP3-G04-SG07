package Actividad1Punto2;

class SinDescuento implements EstrategiaDescuento {
    public double aplicar(double precio) { return precio; }
}

class Descuento10 implements EstrategiaDescuento {
    public double aplicar(double precio) { return precio * 0.90; }
}

class Descuento20 implements EstrategiaDescuento {
    public double aplicar(double precio) { return precio * 0.80; }
}
