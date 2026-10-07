
package com.mycompany.proyectoparcial1;

import java.util.Scanner;

public class Proyecto_Parcial {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        int op;
        // Repite el menu hasta salir.
        do {
            System.out.println("MENU PRINCIPAL\n1. String, Math, printf, Regex\n2. Registro de ventas\n3. Matematica avanzada\n4. Salir");
            op = ent("Seleccione una opcion (1-4): ", 1, 4);

            switch (op) {
                case 1:
                    op1();
                    break;
                case 2:
                    op2();
                    break;
                case 3:
                    op3();
                    break;
                default:
                    break;
            }
        } while (op != 4);
        System.out.println("\nPrograma finalizado.");
    }

    // Imprime una linea "metodo : valor" con el mismo ancho.
    public static void print(String metodo, Object valor) {
        System.out.printf("%-26s: %s%n", metodo, valor);
    }

    public static void op1() {
        do {
            System.out.println("OPCION 1");
            String txt = txt("Ingrese un texto (de 1 a 50 caracteres): ", "^[\\p{L}\\p{N} ]{1,50}$");

            // Metodos String vistos en clase.
            String limpio = txt.trim();
            String minus = limpio.toLowerCase();
            int len = limpio.length();

            System.out.println("\nSTRING");
            print("Texto Original", txt);
            print("trim()", limpio);
            print("length()", len);
            print("charAt(0)", limpio.charAt(0));
            print("substring(0,3)", limpio.substring(0, Math.min(3, len)));
            print("equals(\"Hola\")", limpio.equals("Hola"));
            print("equalsIgnoreCase(\"hola\")", limpio.equalsIgnoreCase("hola"));
            print("contains(\"a\")", minus.contains("a"));
            print("indexOf('a')", minus.indexOf('a'));
            print("lastIndexOf('a')", minus.lastIndexOf('a'));
            print("startsWith(\"a\")", minus.startsWith("a"));
            print("endsWith(\"a\")", minus.endsWith("a"));
            print("toUpperCase()", limpio.toUpperCase());
            print("toLowerCase()", minus);
            print("replace()", limpio.replace('a', '0').replace('A', '0'));
            print("concat()", limpio.concat(" - Java"));
            print("split()", limpio.split(" ").length + " palabra(s)");
            print("toCharArray()", limpio.toCharArray().length + " caracter(es)");
            print("String.valueOf()", String.valueOf(len));

            // Metodos Math.
            double num = dbl("\nIngrese un numero (puede ser negativo): ");
            System.out.println("\nMATH");
            System.out.printf("Math.abs()                : %.2f%n", Math.abs(num));
            System.out.printf("Math.sqrt()               : %.2f%n", Math.sqrt(Math.abs(num)));
            System.out.printf("Math.pow()                : %.2f%n", Math.pow(num, 2));
            System.out.printf("Math.round()              : %d%n", Math.round(num));
            System.out.printf("Math.max(numero, 10)      : %.2f%n", Math.max(num, 10));
            System.out.printf("Math.min(numero, 10)      : %.2f%n", Math.min(num, 10));
            System.out.printf("Math.sin()                : %.3f%n", Math.sin(num));
            System.out.printf("Math.cos()                : %.3f%n", Math.cos(num));

            System.out.println("\nSTRING + MATH");
            System.out.printf("Texto: %s | Longitud: %d | Longitud^2: %d | Raiz: %.2f%n",
                    limpio.toUpperCase(), len, (int) Math.pow(len, 2), Math.sqrt(len));

            // Formatos printf.
            System.out.println("\nPRINTF");
            System.out.printf("Normal                  : %s%n", limpio);
            System.out.printf("Ancho 20                : %20s%n", limpio);
            System.out.printf("Alineado a la izquierda: %-20s%n", limpio);
            System.out.printf("Mayusculas              : %S%n", limpio);
            System.out.printf("Entero                  : %d%n", len);
            System.out.printf("Decimal 2               : %.2f%n", num);
            System.out.printf("Decimal 3               : %.3f%n", Math.sqrt(Math.abs(num)));

            // Validaciones con Regex.
            System.out.println("\nREGEX");
            String mail = txt("Ingrese un correo: ", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
            String ced = txt("Ingrese una cedula de 10 digitos: ", "^\\d{10}$");
            String tel = txt("Ingrese un telefono de 10 digitos: ", "^\\d{10}$");
            System.out.println("\nDatos validados mediante Regex:");
            System.out.println("Correo   : " + mail + "\nCedula   : " + ced + "\nTelefono : " + tel);
        } while (rep());
    }

    public static void op2() {
        do {
            double totPago = 0, totAhorro = 0;
            int cantProd = 0, minMax = -1;
            String prodMax = "";
            System.out.println("OPCION 2: VENTAS");

            do {
                String prod = txt("\nProducto: ", "^[\\p{L}\\p{N} ]{1,30}$");
                double pre = dblPos("Precio (> 0): ", 0);
                int[] f = fecha(); // dia, mes, anio, hora, minuto
                double pctDesc = desc(pre, f[3], f[4], prod);
                double valDesc = pre * pctDesc;
                double valPago = pre - valDesc;

                totPago += valPago;
                totAhorro += valDesc;
                cantProd++;
                if (f[4] > minMax) {
                    minMax = f[4];
                    prodMax = prod;
                }

                System.out.printf("%nFACTURA%nProducto       : %s%nPrecio         : $%.2f%nDescuento      : %.2f%%%n"
                        + "Valor ahorrado : $%.2f%nValor pagado   : $%.2f%nFecha          : %02d/%02d/%04d%nHora           : %02d:%02d%n",
                        prod, pre, pctDesc * 100, valDesc, valPago, f[0], f[1], f[2], f[3], f[4]);
            } while (siNo("Desea ingresar otro producto? (s/n): "));

            System.out.printf("%nRESUMEN%nValor pagado          : %.2f%nValor ahorrado        : %.2f%n"
                    + "Numero de productos   : %d%nProducto mayor minuto : %s%nMayor numero de minuto: %d%n",
                    totPago, totAhorro, cantProd, prodMax, minMax);
        } while (rep());
    }

    // Pide la fecha y hora hasta que sea valida. Devuelve {dia, mes, anio, hora, minuto}.
    public static int[] fecha() {
        while (true) {
            String[] p = txt("Fecha y hora (dd/MM/yyyy HH:mm): ", "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}$").split("[/: ]");
            int[] f = {0, 0, 0, 0, 0};
            for (int i = 0; i < 5; i++) {
                f[i] = Integer.parseInt(p[i]);
            }
            if (valFecha(f[0], f[1], f[2], f[3], f[4])) {
                return f;
            }
            System.out.println("Fecha u hora invalida. Intente nuevamente.");
        }
    }

    public static boolean valFecha(int dia, int mes, int anio, int h, int min) {
        int dias = 31;
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            dias = 30;
        } else if (mes == 2) {
            // Anio bisiesto: 29 dias.
            if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                dias = 29;
            } else {
                dias = 28;
            }
        }
        return anio >= 2000 && anio <= 2100 && mes >= 1 && mes <= 12
                && dia >= 1 && dia <= dias && h <= 23 && min <= 59;
    }

    public static double desc(double pre, int hor, int minu, String prod) {
        double d = 0.05; // Compra normal: 5%.
        // Cinco condiciones anidadas.
        if (pre > 100) {
            d = 0.10;
            if (hor >= 14) {
                d = 0.15;
                if (minu >= 30) {
                    d = 0.20;
                    if (prod.length() >= 5) {
                        d = 0.25;
                        if (prod.toUpperCase().startsWith("Z")) {
                            d = 0.30;
                        }
                    }
                }
            }
        }
        return d;
    }

    public static void op3() {
        do {
            System.out.println("\nOPCION 3: MATEMATICA\n1. Derivadas\n2. Ecuacion cuadratica\n3. Sucesiones\n4. Tablas de valores");
            int op = ent("Opcion (1-4): ", 1, 4);
            switch (op) {
                case 1:
                    derivadas();
                    break;
                case 2:
                    cuadratica();
                    break;
                case 3:
                    sucesion();
                    break;
                default:
                    tabla();
                    break;
            }
        } while (rep());
    }

    public static void derivadas() {
        System.out.println("\nDERIVADAS\n1. Potencia\n2. Suma\n3. Resta\n4. Producto\n5. Cociente");
        int op = ent("Opcion (1-5): ", 1, 5);
        double a, b, u, up, v, vp;

        switch (op) {
            case 1:
                a = dbl("Coeficiente: ");
                b = dbl("Exponente: ");
                System.out.println("f(x)=nu^n-1");
                System.out.printf("y= %.2f*x^%.2f%nf'(x) = %.2f*x^%.2f%n", a, b, a * b, b - 1);
                break;
            case 2:
                a = dbl("Coeficiente de x^2: ");
                b = dbl("Coeficiente de x: ");
                System.out.println("y= 2u^2-1--(c.x)=(c)");
                System.out.printf("f(x) = %.2f*x^2 + %.2f*x%nf'(x) = %.2f*x + %.2f%n", a, b, 2 * a, b);
                break;
            case 3:
                a = dbl("Coeficiente de x^3: ");
                b = dbl("Coeficiente de x^2: ");
                System.out.print("y= 3u^3-1--2u^2-1");
                System.out.printf("f(x) = %.2f*x^3 - %.2f*x^2%nf'(x) = %.2f*x^2 - %.2f*x%n", a, b, 3 * a, 2 * b);
                break;
            case 4:
                u = dbl("u: ");
                up = dbl("u': ");
                v = dbl("v: ");
                vp = dbl("v': ");
                System.out.println("y=(u.v)´--(u´v.uv´");
                System.out.printf("(u*v)' = %.2f%n", up * v + u * vp);
                break;
            default:
                u = dbl("u: ");
                up = dbl("u': ");
                v = dblNoCero("v: ");
                vp = dbl("v': ");
                System.out.printf("(u/v)' = %.2f%n", (up * v - u * vp) / Math.pow(v, 2));
        }
    }

    public static void cuadratica() {
        System.out.println("\nECUACION CUADRATICA");
        double a = dblNoCero("a (diferente de 0): ");
        double b = dbl("b: ");
        double c = dbl("c: ");
        double d = Math.pow(b, 2) - 4 * a * c;

        System.out.printf("Discriminante = %.2f%n", d);
        if (d > 0) {
            System.out.printf("x1 = %.3f%nx2 = %.3f%n", (-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d)) / (2 * a));
        } else if (d == 0) {
            System.out.printf("x = %.3f%n", -b / (2 * a));
        } else {
            double real = -b / (2 * a);
            double imag = Math.sqrt(-d) / Math.abs(2 * a);
            System.out.printf("x1 = %.3f + %.3fi%nx2 = %.3f - %.3fi%n", real, imag, real, imag);
        }
    }

    public static void sucesion() {
        System.out.println("\nSUCESIONES");
        double a = dbl("Primer termino: ");
        double d = dbl("Diferencia: ");
        int n = ent("Numero de terminos: ", 1, 100);
        double an = a + (n - 1) * d;

        System.out.printf("Termino %d = %.3f%n", n, an);
        System.out.printf("Suma de los %d terminos = %.3f%n", n, n * (a + an) / 2);
        System.out.print("Sucesion: ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%.2f ", a + i * d);
        }
        System.out.println();
    }

    public static void tabla() {
        System.out.println("\nTABLAS DE VALORES\n1. Tabla de suma\n2. Tabla de multiplicacion\n3. Tabla de potencia");
        int op = ent("Opcion (1-3): ", 1, 3);
        int n = ent("Ingrese un numero: ", -100, 100);

        System.out.println("\nVALORES");
        for (int i = 1; i <= 10; i++) {
            switch (op) {
                case 1:
                    System.out.printf("%d + %d = %d%n", n, i, n + i);
                    break;
                case 2:
                    System.out.printf("%d x %d = %d%n", n, i, n * i);
                    break;
                default:
                    System.out.printf("%d ^ %d = %.0f%n", n, i, Math.pow(n, i));
                    break;
            }
        }
    }

    // Metodos de entrada.
   public static int ent(String msg, int min, int max) {
    int num = 0;
    boolean valido = false;

    do {
        System.out.print(msg);
        String in = sc.nextLine().trim();

        if (!in.matches("-?\\d+")) {
            System.out.println("Ingrese solamente numeros enteros.");
        } else {
            try {
                num = Integer.parseInt(in);
                if (num >= min && num <= max) {
                    valido = true; // El número cumple todas las condiciones
                } else {
                    System.out.println("Ingrese un valor entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("El numero esta fuera del rango permitido.");
            }
        }
    } while (!valido);

    return num;
}

    public static double dbl(String msg) {
        while (true) {
            System.out.print(msg);
            String in = sc.nextLine().trim().replace(',', '.');
            if (in.matches("-?\\d+(\\.\\d+)?")) {
                double num = Double.parseDouble(in);
                if (Double.isFinite(num)) {
                    return num;
                }
            }
            System.out.println("Ingrese un numero valido.");
        }
    }

    public static double dblPos(String msg, double min) {
        while (true) {
            double num = dbl(msg);
            if (num > min) {
                return num;
            }
            System.out.println("El valor debe ser mayor que " + min + ".");
        }
    }

    public static double dblNoCero(String msg) {
        while (true) {
            double num = dbl(msg);
            if (num != 0) {
                return num;
            }
            System.out.println("El valor no puede ser cero.");
        }
    }

    public static String txt(String msg, String regex) {
        while (true) {
            System.out.print(msg);
            String txt = sc.nextLine().trim();
            if (txt.matches(regex)) {
                return txt;
            }
            System.out.println("Formato invalido. Intente nuevamente.");
        }
    }

    public static boolean siNo(String msg) {
        while (true) {
            System.out.print(msg);
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s") || r.equals("n")) {
                return r.equals("s");
            }
            System.out.println("Ingrese solamente s o n.");
        }
    }

    public static boolean rep() {
        return siNo("\nDesea ejecutar esta opcion una vez mas? (s/n): ");
    }
}

